CREATE TABLE ratings
(
    id INT NOT NULL PRIMARY KEY  AUTO_INCREMENT,
    value DOUBLE NOT NULL,
    issue_date DATE NOT NULL,
    book_id INT NOT NULL,
    user_id INT NOT NULL,
    FOREIGN KEY (book_id) REFERENCES books(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);