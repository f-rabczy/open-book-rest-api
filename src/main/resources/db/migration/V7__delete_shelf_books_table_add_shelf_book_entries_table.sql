DROP TABLE IF EXISTS shelf_books;

CREATE TABLE IF NOT EXISTS bookshelf_entries
(
    id                 INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    book_shelf_id      INT NOT NULL,
    book_review_id     INT,
    book_rating_id     INT,
    reading_start_date DATE,
    reading_end_date   DATE,
    note               VARCHAR(128),
    FOREIGN KEY (book_shelf_id) REFERENCES bookshelves (id),
    FOREIGN KEY (book_review_id) REFERENCES reviews (id),
    FOREIGN KEY (book_rating_id) REFERENCES ratings (id)

)
