
create TABLE counsellor_details(
counsellor_id INT AUTO_INCREMENT PRIMARY KEY,
counsellor_name VARCHAR(50) NOT NULL,
counsellor_email VARCHAR(50) UNIQUE NOT NULL,
password VARCHAR(50) NOT NULL,
phone_number VARCHAR(10) UNIQUE NOT NULL,
create_at DATE,
update_at DATE
);