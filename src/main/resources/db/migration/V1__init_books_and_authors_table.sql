DROP TABLE IF EXISTS  author_books  ;
DROP TABLE IF EXISTS  authorEntities  ;
DROP TABLE IF EXISTS  books  ;

CREATE TABLE author_books
(
    author_id INT NOT NULL,
    book_id INT NOT NULL,
    PRIMARY KEY(author_id,book_id)
);

CREATE TABLE authorEntities
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(30) NOT NULL

);

CREATE TABLE books
(
    id INT PRIMARY KEY AUTO_INCREMENT,
    isbn VARCHAR(30) NOT NULL,
    publication_year INT NOT NULL,
    title VARCHAR(100) NOT NULL,
    image_url VARCHAR(100),
    average_rating DOUBLE,
    one_star INT,
    two_star INT,
    three_star INT,
    four_star INT,
    five_star INT,
    ratings_count DOUBLE
);


ALTER TABLE author_books ADD CONSTRAINT FK_BOOK_AUTHOR FOREIGN KEY (book_id) references books(id);
ALTER TABLE author_books ADD CONSTRAINT FK_AUTHOR_BOOK FOREIGN KEY (author_id) references authorEntities(id);