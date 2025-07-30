USE MsList

-- Drop and recreate database
IF DB_ID('DannysDinerDB') IS NOT NULL
BEGIN
    ALTER DATABASE DannysDinerDB SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
    DROP DATABASE DannysDinerDB;
END
GO

CREATE DATABASE DannysDinerDB;
GO

USE DannysDinerDB;
GO

-- Create schema
CREATE SCHEMA dannys_diner;
GO

-- Create members table
CREATE TABLE dannys_diner.members (
  customer_id CHAR(1) PRIMARY KEY,
  join_date DATETIME NOT NULL
);

-- Create menu table
CREATE TABLE dannys_diner.menu (
  product_id INT PRIMARY KEY,
  product_name VARCHAR(5) NOT NULL,
  price INT NOT NULL CHECK (price >= 0)
);

-- Create sales table
CREATE TABLE dannys_diner.sales (
  sales_id INT IDENTITY(1,1) PRIMARY KEY,
  customer_id CHAR(1) NOT NULL,
  order_date DATE NOT NULL,
  product_id INT NOT NULL,
  FOREIGN KEY (customer_id) REFERENCES dannys_diner.members(customer_id),
  FOREIGN KEY (product_id) REFERENCES dannys_diner.menu(product_id)
);

-- Insert data into members (including customer 'C')
INSERT INTO dannys_diner.members (customer_id, join_date)
VALUES
  ('A', '2021-01-07'),
  ('B', '2021-01-09'),
  ('C', '2021-01-01'); -- added for FK consistency

-- Insert data into menu
INSERT INTO dannys_diner.menu (product_id, product_name, price)
VALUES
  (1, 'sushi', 10),
  (2, 'curry', 15),
  (3, 'ramen', 12);

-- Insert data into sales
INSERT INTO dannys_diner.sales (customer_id, order_date, product_id)
VALUES
  ('A', '2021-01-01', 1),
  ('A', '2021-01-01', 2),
  ('A', '2021-01-07', 2),
  ('A', '2021-01-10', 3),
  ('A', '2021-01-11', 3),
  ('A', '2021-01-11', 3),
  ('B', '2021-01-01', 2),
  ('B', '2021-01-02', 2),
  ('B', '2021-01-04', 1),
  ('B', '2021-01-11', 1),
  ('B', '2021-01-16', 3),
  ('B', '2021-02-01', 3),
  ('C', '2021-01-01', 3),
  ('C', '2021-01-01', 3),
  ('C', '2021-01-07', 3);
