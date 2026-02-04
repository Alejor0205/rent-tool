CREATE DATABASE IF NOT EXISTS rent_tool CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE rent_tool;

CREATE TABLE IF NOT EXISTS users (
    id CHAR(36) PRIMARY KEY,
    full_name VARCHAR(160) NOT NULL,
    email VARCHAR(180) NOT NULL UNIQUE,
    phone VARCHAR(40),
    role VARCHAR(30) NOT NULL,
    password_hash VARCHAR(120)
);

CREATE TABLE IF NOT EXISTS categories (
    id CHAR(36) PRIMARY KEY,
    name VARCHAR(120) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS tools (
    id CHAR(36) PRIMARY KEY,
    name VARCHAR(140) NOT NULL,
    category_id CHAR(36) NOT NULL,
    hourly_rate DECIMAL(10,2),
    daily_rate DECIMAL(10,2),
    status VARCHAR(30) NOT NULL,
    CONSTRAINT fk_tools_category FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE IF NOT EXISTS rentals (
    id CHAR(36) PRIMARY KEY,
    tool_id CHAR(36) NOT NULL,
    customer_id CHAR(36) NOT NULL,
    provider_id CHAR(36),
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    total_amount DECIMAL(12,2) NOT NULL,
    status VARCHAR(30) NOT NULL,
    created_at DATETIME NOT NULL,
    CONSTRAINT fk_rentals_tool FOREIGN KEY (tool_id) REFERENCES tools(id),
    CONSTRAINT fk_rentals_customer FOREIGN KEY (customer_id) REFERENCES users(id),
    CONSTRAINT fk_rentals_provider FOREIGN KEY (provider_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS payments (
    id CHAR(36) PRIMARY KEY,
    rental_id CHAR(36) NOT NULL,
    amount DECIMAL(12,2) NOT NULL,
    status VARCHAR(30) NOT NULL,
    method VARCHAR(40) NOT NULL,
    created_at DATETIME NOT NULL,
    CONSTRAINT fk_payments_rental FOREIGN KEY (rental_id) REFERENCES rentals(id)
);

CREATE TABLE IF NOT EXISTS damage_reports (
    id CHAR(36) PRIMARY KEY,
    rental_id CHAR(36) NOT NULL,
    tool_id CHAR(36) NOT NULL,
    description VARCHAR(500) NOT NULL,
    resolved TINYINT(1) NOT NULL,
    created_at DATETIME NOT NULL,
    CONSTRAINT fk_damage_rental FOREIGN KEY (rental_id) REFERENCES rentals(id),
    CONSTRAINT fk_damage_tool FOREIGN KEY (tool_id) REFERENCES tools(id)
);

CREATE TABLE IF NOT EXISTS invoices (
    id CHAR(36) PRIMARY KEY,
    rental_id CHAR(36) NOT NULL UNIQUE,
    pdf_data LONGBLOB NOT NULL,
    created_at DATETIME NOT NULL,
    CONSTRAINT fk_invoices_rental FOREIGN KEY (rental_id) REFERENCES rentals(id)
);

CREATE INDEX idx_tools_status ON tools(status);
CREATE INDEX idx_tools_category ON tools(category_id);
CREATE INDEX idx_rentals_status ON rentals(status);
CREATE INDEX idx_rentals_customer ON rentals(customer_id);
CREATE INDEX idx_rentals_provider ON rentals(provider_id);
CREATE INDEX idx_rentals_tool ON rentals(tool_id);
CREATE INDEX idx_payments_status ON payments(status);
CREATE INDEX idx_payments_rental ON payments(rental_id);
CREATE INDEX idx_damage_rental ON damage_reports(rental_id);
