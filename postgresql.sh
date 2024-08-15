#!/bin/bash

# Step 1: Update package list and install Docker
sudo apt-get update
sudo apt-get install -y docker.io

# Step 2: Start Docker service
sudo systemctl start docker
sudo systemctl enable docker

# Step 3: Pull PostgreSQL image
sudo docker pull postgres:latest

# Step 4: Run PostgreSQL container with environment variables
sudo docker run -d -p 5432:5432 --name postgres-container -e POSTGRES_PASSWORD=postgres123 -e POSTGRES_DB=productsdb postgres:latest

# Step 5: Wait for PostgreSQL to fully initialize
sleep 30  # Adjust this if needed

# Step 6: Create an SQL file with the table creation and insert statements
cat <<EOF > init.sql
CREATE TABLE IF NOT EXISTS products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    description TEXT
);

INSERT INTO products (name, price, description) VALUES
('Product 1', 19.99, 'Description for Product 1'),
('Product 2', 29.99, 'Description for Product 2'),
('Product 3', 39.99, 'Description for Product 3'),
('Product 4', 49.99, 'Description for Product 4'),
('Product 5', 59.99, 'Description for Product 5');
EOF

# Step 7: Copy SQL file to container and execute it
sudo docker cp init.sql postgres-container:/init.sql
sudo docker exec -i postgres-container psql -U postgres -d productsdb -f /init.sql

echo "PostgreSQL container has been initialized with a 'products' table in the 'productsdb' database."