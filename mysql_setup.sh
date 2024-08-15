#!/bin/bash

# Step 1: Update package list and install Docker
sudo apt-get update
sudo apt-get install -y docker.io

# Step 2: Start Docker service
sudo systemctl start docker
sudo systemctl enable docker

# Step 3: Pull MySQL image
sudo docker pull mysql:latest

# Step 4: Run MySQL container with environment variables
sudo docker run -d -p 3306:3306 --name mysql-container -e MYSQL_ROOT_PASSWORD=root123 mysql:latest

# Wait for MySQL to fully initialize
sleep 30  # Adjust this if needed

# Step 5: Create an SQL file with the database and table creation, and insert statements
cat <<EOF > init.sql
CREATE DATABASE IF NOT EXISTS bookdb;
USE bookdb;

DROP TABLE IF EXISTS books;
CREATE TABLE books (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    ISBN VARCHAR(255),
    publicationDate DATE,
    authorName VARCHAR(255)
);

INSERT INTO books (title, ISBN, publicationDate, authorName) VALUES
('Book Title 1', '123-4567890123', '2024-07-21', 'Author Name 1'),
('Book Title 2', '234-5678901234', '2023-05-15', 'Author Name 2'),
('Book Title 3', '345-6789012345', '2022-11-30', 'Author Name 3');
EOF

# Step 6: Copy SQL file to container and execute it
sudo docker cp init.sql mysql-container:/init.sql
sudo docker exec -i mysql-container mysql -uroot -proot123 < /init.sql

echo "MySQL container has been initialized with a 'books' table in the 'bookdb' database."
