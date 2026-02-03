CREATE TABLE IF NOT EXISTS MESSAGE(
    id INTEGER AUTO_INCREMENT,
    user_id Integer NOT NULL,
    body TEXT,
    date_created TIMESTAMP NOT NULL,
    date_updated TIMESTAMP,
    primary key (id)
);

CREATE TABLE IF NOT EXISTS Users(
    id INTEGER AUTO_INCREMENT,
    username TEXT NOT NULL,
    password TEXT NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO MESSAGE(id,user_id,body,date_created,date_updated) VALUES
(1, 1, 'Hello, this is the first message!', CURRENT_TIMESTAMP, NULL),
(2, 2, 'This is another message in the database.', CURRENT_TIMESTAMP, NULL),
(3, 1, 'Yet another message for testing purposes.', CURRENT_TIMESTAMP, NULL),
(4, 3, '3rd Users First Message.', CURRENT_TIMESTAMP, NULL),
(5, 2, 'Yet another message for testing purposes 2.0.', CURRENT_TIMESTAMP, NULL);

INSERT INTO Users(id,username,password) VALUES
(1, 'rati', 'ratibati'),
(2, 'masho', 'mashomasho'),
(3, 'gio', 'giopio');