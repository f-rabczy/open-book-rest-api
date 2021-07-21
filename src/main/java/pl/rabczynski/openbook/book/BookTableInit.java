package pl.rabczynski.openbook.book;

import lombok.SneakyThrows;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.rabczynski.openbook.author.AuthorEntity;
import pl.rabczynski.openbook.author.AuthorService;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
class BookTableInit implements ApplicationListener<ContextRefreshedEvent> {

    private static final String FILE_NAME = "E:\\IdeaProjects\\open-book\\src\\main\\resources\\static\\books_database.csv";
    private static final String AUTHORS_COLUMN = "authors";
    private static final String ISBN_COLUMN = "isbn";
    private static final String PUBLICATION_YEAR_COLUMN = "original_publication_year";
    private static final String TITLE_COLUMN = "title";
    private static final String AVERAGE_RATING_COLUMN = "average_rating";
    private static final String RATINGS_COUNT_COLUMN = "work_ratings_count";
    private static final String ONE_STAR_RATING_COLUMN = "ratings_1";
    private static final String TWO_STAR_RATING_COLUMN = "ratings_2";
    private static final String THREE_STAR_RATING_COLUMN = "ratings_3";
    private static final String FOUR_STAR_RATING_COLUMN = "ratings_4";
    private static final String FIVE_STAR_RATING_COLUMN = "ratings_5";
    private static final String IMAGE_URL_COLUMN = "image_url";

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final Set<String> optimizationAuthorsSet = new HashSet<>();

    BookTableInit(final BookRepository bookRepository,
                  final AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }


    @SneakyThrows
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        readRecordsAndCreateEntities();
    }


    private void readRecordsAndCreateEntities() throws IOException {
        Iterable<CSVRecord> csvRecords = getCSVRecords();

        for (CSVRecord csvRecord : csvRecords) {

            BookEntity bookEntity = convertCsvRecordToBook(csvRecord);
            String authorsColumn = csvRecord.get(AUTHORS_COLUMN);

            if (authorsColumn.contains(",")) {
                addMultipleAuthors(authorsColumn, bookEntity);
            } else {
                addSingleAuthor(authorsColumn, bookEntity);
            }

            bookRepository.save(bookEntity);
            authorService.saveAll(bookEntity.getAuthors());
            addToOptimizationSet(bookEntity.getAuthors());
        }
    }

    private void addSingleAuthor(String authorName, BookEntity bookEntity) {
        var author = new AuthorEntity();
        if (optimizationAuthorsSet.contains(authorName)) {
            author = authorService.getAuthorByFullName(authorName);
        } else {
            author.setFullName(authorName);
        }
        author.addBook(bookEntity);

    }

    private void addMultipleAuthors(String authorsNames, BookEntity bookEntity) {
        String[] namesArray = authorsNames.split(",");
        for (String authorName : namesArray) {
            addSingleAuthor(authorName, bookEntity);
        }
    }

    private BookEntity convertCsvRecordToBook(CSVRecord csvRecord) {
        var book = new BookEntity();
        book.setTitle(csvRecord.get(TITLE_COLUMN));
        book.setIsbn(csvRecord.get(ISBN_COLUMN));
        book.setPublicationYear(Integer.parseInt(csvRecord.get(PUBLICATION_YEAR_COLUMN)));
        book.setAverageRating(Double.parseDouble(csvRecord.get(AVERAGE_RATING_COLUMN)));
        book.setImageUrl(csvRecord.get(IMAGE_URL_COLUMN));
        book.initializeBookRating(
                Integer.parseInt(csvRecord.get(ONE_STAR_RATING_COLUMN)),
                Integer.parseInt(csvRecord.get(TWO_STAR_RATING_COLUMN)),
                Integer.parseInt(csvRecord.get(THREE_STAR_RATING_COLUMN)),
                Integer.parseInt(csvRecord.get(FOUR_STAR_RATING_COLUMN)),
                Integer.parseInt(csvRecord.get(FIVE_STAR_RATING_COLUMN))
        );
        return book;
    }

    private Iterable<CSVRecord> getCSVRecords() throws IOException {
        File csvFile = new File(FILE_NAME);
        var reader = new BufferedReader(new FileReader(csvFile));
        CSVParser parser = new CSVParser(reader, CSVFormat
                .EXCEL
                .withFirstRecordAsHeader()
                .withDelimiter(';')
                .withIgnoreHeaderCase()
                .withTrim());

        return parser.getRecords();
    }

    private void addToOptimizationSet(Set<AuthorEntity> source) {
        optimizationAuthorsSet.addAll(
                source.stream()
                        .map(AuthorEntity::getFullName)
                        .collect(Collectors.toSet()));
    }


}
