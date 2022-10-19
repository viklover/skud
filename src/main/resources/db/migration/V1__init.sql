
CREATE TABLE card (
    id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL
);

CREATE TABLE event (
    id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
    card_id INTEGER NOT NULL,
    date INTEGER,
    event_type varchar(64) NOT NULL,
    FOREIGN KEY (card_id) references card (id) on delete CASCADE
);

CREATE TABLE student (
    id VARCHAR(8) PRIMARY KEY NOT NULL,
    card_id INTEGER,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    FOREIGN KEY (card_id) references card (id) on delete SET NULL
);

CREATE TABLE parent (
    id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    student_id VARCHAR(8) NOT NULL,
    telephone_number VARCHAR(12) NOT NULL,
    FOREIGN KEY (student_id) references student (id) on delete CASCADE
);

CREATE TABLE notification_settings (
    id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
    parent_id INTEGER NOT NULL,
    FOREIGN KEY (parent_id) references parent (id) on delete CASCADE
);

