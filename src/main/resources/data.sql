DROP TABLE IF EXISTS customers;

CREATE TABLE customers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name_ VARCHAR(100) NOT NULL,
    gender VARCHAR(1) NOT NULL,
    dob DATA NOT NULL
);

INSERT INTO customers (name_, gender, dob) VALUES
    ('Фёдор Михайлович Достоевский', 'M', '1821-11-11'),
    ('Кнут Гамсун', 'M', '1859-08-04'),
    ('Джон Голсуорси', 'M', '1867-08-14');