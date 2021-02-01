DROP TABLE IF EXISTS customers;

CREATE TABLE customers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name_ VARCHAR(100) NOT NULL,
    gender VARCHAR(6) NOT NULL,
    dob TIMESTAMP NOT NULL
);

INSERT INTO customers (name_, gender, dob) VALUES
    ('Фёдор Михайлович Достоевский', 'MALE', '1821-11-11'),
    ('Кнут Гамсун', 'MALE', '1859-08-04'),
    ('Джон Голсуорси', 'MALE', '1867-08-14');