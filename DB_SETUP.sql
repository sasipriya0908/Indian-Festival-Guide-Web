-- =========================================================
-- WEB_FINAL - INDIAN FESTIVAL GUIDE COMPLETE DATABASE SETUP
-- =========================================================

-- 1) Registration database
CREATE DATABASE IF NOT EXISTS indian_festival;
USE indian_festival;

CREATE TABLE IF NOT EXISTS registrations (
    id INT PRIMARY KEY AUTO_INCREMENT,
    registration_id VARCHAR(30) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(15),
    age INT,
    favourite_festival VARCHAR(50)
);

-- 2) E-commerce database
CREATE DATABASE IF NOT EXISTS indian_festival_store;
USE indian_festival_store;

CREATE TABLE IF NOT EXISTS products (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    festival VARCHAR(50),
    price DECIMAL(10,2),
    image VARCHAR(200),
    description VARCHAR(500)
);

INSERT INTO products (name, festival, price, image, description)
SELECT 'Decorative Diya Set', 'Diwali', 199.00, 'diwali.jpg', 'Beautiful traditional diyas for Diwali decoration'
WHERE NOT EXISTS (SELECT 1 FROM products WHERE name='Decorative Diya Set');

INSERT INTO products (name, festival, price, image, description)
SELECT 'Rangoli Colour Kit', 'Diwali', 149.00, 'diwali.jpg', 'Colorful rangoli powder kit for festive decoration'
WHERE NOT EXISTS (SELECT 1 FROM products WHERE name='Rangoli Colour Kit');

INSERT INTO products (name, festival, price, image, description)
SELECT 'Holi Colour Pack', 'Holi', 249.00, 'Holi.jpg', 'Bright and safe colors for Holi celebration'
WHERE NOT EXISTS (SELECT 1 FROM products WHERE name='Holi Colour Pack');

INSERT INTO products (name, festival, price, image, description)
SELECT 'Pongal Decoration Kit', 'Pongal', 299.00, 'pongal.jfif', 'Traditional Pongal decoration materials'
WHERE NOT EXISTS (SELECT 1 FROM products WHERE name='Pongal Decoration Kit');

INSERT INTO products (name, festival, price, image, description)
SELECT 'Onam Flower Decoration Kit', 'Onam', 349.00, 'onam.webp', 'Beautiful decoration kit for Onam celebrations'
WHERE NOT EXISTS (SELECT 1 FROM products WHERE name='Onam Flower Decoration Kit');

INSERT INTO products (name, festival, price, image, description)
SELECT 'Raksha Bandhan Gift Set', 'Raksha Bandhan', 399.00, 'raksha bandhan.avif', 'Special gift set for Raksha Bandhan'
WHERE NOT EXISTS (SELECT 1 FROM products WHERE name='Raksha Bandhan Gift Set');

INSERT INTO products (name, festival, price, image, description)
SELECT 'Christmas Decoration Set', 'Christmas', 499.00, 'christmas.jfif', 'Decorative items for Christmas celebrations'
WHERE NOT EXISTS (SELECT 1 FROM products WHERE name='Christmas Decoration Set');

INSERT INTO products (name, festival, price, image, description)
SELECT 'Eid Gift Box', 'Eid', 449.00, 'eid.jfif', 'Festive gift box for Eid celebrations'
WHERE NOT EXISTS (SELECT 1 FROM products WHERE name='Eid Gift Box');

CREATE TABLE IF NOT EXISTS orders (
    id INT PRIMARY KEY AUTO_INCREMENT,
    customer_name VARCHAR(100),
    email VARCHAR(100),
    address VARCHAR(300),
    product_name VARCHAR(100),
    quantity INT,
    total_price DECIMAL(10,2),
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Verify setup
SELECT 'indian_festival registrations' AS table_info, COUNT(*) AS rows_count
FROM indian_festival.registrations;

SELECT 'indian_festival_store products' AS table_info, COUNT(*) AS rows_count
FROM indian_festival_store.products;

SELECT 'indian_festival_store orders' AS table_info, COUNT(*) AS rows_count
FROM indian_festival_store.orders;
