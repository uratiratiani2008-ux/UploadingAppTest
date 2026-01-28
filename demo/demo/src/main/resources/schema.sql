CREATE TABLE IF NOT EXISTS Messages(
    id INTEGER AUTO_INCREMENT,
    username TEXT NOT NULL,
    body TEXT,
    date_created TIMESTAMP NOT NULL,
    date_updated TIMESTAMP,
    primary key (id)
);

CREATE TABLE IF NOT EXISTS Users(
    username TEXT NOT NULL,
    password TEXT NOT NULL,
    primary key (username)
);

INSERT INTO Messages(id,username,body,date_created,date_updated) VALUES
(1, 'rati', 'Hello, this is the first message!', CURRENT_TIMESTAMP, NULL),
(2, 'masho', 'This is another message in the database.', CURRENT_TIMESTAMP, NULL),
(3, 'rati', 'Yet another message for testing purposes.', CURRENT_TIMESTAMP, NULL),
(4, 'gio', '3rd Users First Message.', CURRENT_TIMESTAMP, NULL),
(5, 'masho', 'Yet another message for testing purposes 2.0.', CURRENT_TIMESTAMP, NULL);

INSERT INTO Users(username,password) VALUES
('rati', 'ratibati'),
('masho', 'mashomasho'),
('gio', 'giopio');