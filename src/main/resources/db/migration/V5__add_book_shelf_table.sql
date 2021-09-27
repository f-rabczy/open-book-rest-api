CREATE TABLE bookshelves
(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(32) NOT NULL,
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE shelf_books
(
    shelf_id INT NOT NULL,
    book_id INT NOT NULL,
    PRIMARY KEY(shelf_id,book_id)
);