create TABLE enquires(
id INT AUTO_INCREMENT PRIMARY KEY,
student_name VARCHAR(30) NOT NULL,
student_phno VARCHAR(10) UNIQUE NOT NULL,
course_name VARCHAR(30),
class_mode VARCHAR(30),
enq_status VARCHAR(10),
create_at DATE,
update_at DATE,
counsellor_id INT,
FOREIGN KEY (counsellor_id) REFERENCES counsellor_details(counsellor_id)
);