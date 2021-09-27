CREATE TABLE reviews
(
    id INT NOT NULL PRIMARY KEY  AUTO_INCREMENT,
    content VARCHAR(256) NOT NULL,
    issue_date DATE NOT NULL,
    start_date DATE,
    end_date DATE,
    book_id INT NOT NULL,
    user_id INT NOT NULL,
    FOREIGN KEY (book_id) REFERENCES books(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);