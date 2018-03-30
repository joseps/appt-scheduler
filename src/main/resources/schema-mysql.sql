CREATE TABLE IF NOT EXISTS patient (
  id INT UNSIGNED NOT NULL,
  first_name VARCHAR(64),
  last_name  VARCHAR(64),
  created_date DATETIME,
  updated_date DATETIME,
  PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS provider (
  id INT UNSIGNED NOT NULL,
  first_name VARCHAR(64),
  last_name  VARCHAR(64),
  created_date DATETIME,
  updated_date DATETIME,
  PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS department (
  id INT UNSIGNED NOT NULL,
  department_name VARCHAR(255),
  created_date DATETIME,
  updated_date DATETIME,
  PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS appointment (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  start_date_time DATETIME,
  duration INT UNSIGNED,
  patient_id INT UNSIGNED NOT NULL,
  provider_id INT UNSIGNED NOT NULL,
  department_id INT UNSIGNED NOT NULL,
  created_date DATETIME,
  updated_date DATETIME,
  PRIMARY KEY (id),
  CONSTRAINT appointment_uq UNIQUE (patient_id, provider_id, start_date_time),
  CONSTRAINT patient_fk FOREIGN KEY (patient_id) REFERENCES patient(id),
  CONSTRAINT provider_fk FOREIGN KEY (provider_id) REFERENCES provider(id),
  CONSTRAINT department_fk FOREIGN KEY (department_id) REFERENCES department(id),
  KEY patient_index (patient_id),
  KEY provider_index (provider_id),
  KEY department_index (department_id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS webhook (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  url VARCHAR(255),
  company_name  VARCHAR(64),
  created_date DATETIME,
  updated_date DATETIME,
  PRIMARY KEY (id)
) ENGINE=InnoDB;


INSERT INTO patient (id, first_name, last_name, created_date, updated_date) VALUES (1000000001, 'John', 'Smith', NOW(), NOW()) ON DUPLICATE KEY UPDATE updated_date=NOW();
INSERT INTO patient (id, first_name, last_name, created_date, updated_date) VALUES (1000000002, 'Thomas', 'Edison', NOW(), NOW()) ON DUPLICATE KEY UPDATE updated_date=NOW();
INSERT INTO patient (id, first_name, last_name, created_date, updated_date) VALUES (1000000003, 'Pamela', 'Roberts', NOW(), NOW()) ON DUPLICATE KEY UPDATE updated_date=NOW();
INSERT INTO patient (id, first_name, last_name, created_date, updated_date) VALUES (1000000004, 'Melissa', 'Watson', NOW(), NOW()) ON DUPLICATE KEY UPDATE updated_date=NOW();
INSERT INTO patient (id, first_name, last_name, created_date, updated_date) VALUES (1000000005, 'Lauren', 'Femi', NOW(), NOW()) ON DUPLICATE KEY UPDATE updated_date=NOW();
INSERT INTO patient (id, first_name, last_name, created_date, updated_date) VALUES (1000000006, 'Dorothy', 'King', NOW(), NOW()) ON DUPLICATE KEY UPDATE updated_date=NOW();
INSERT INTO patient (id, first_name, last_name, created_date, updated_date) VALUES (1000000007, 'Megan', 'Cook',  NOW(), NOW()) ON DUPLICATE KEY UPDATE updated_date=NOW();


INSERT INTO provider (id, first_name, last_name, created_date, updated_date) VALUES (2000000001, 'Edward', 'Taylor', NOW(), NOW()) ON DUPLICATE KEY UPDATE updated_date=NOW();
INSERT INTO provider (id, first_name, last_name, created_date, updated_date) VALUES (2000000002, 'Grace', 'Rodriguez', NOW(), NOW()) ON DUPLICATE KEY UPDATE updated_date=NOW();
INSERT INTO provider (id, first_name, last_name, created_date, updated_date) VALUES (2000000003, 'Joan', 'Torres', NOW(), NOW()) ON DUPLICATE KEY UPDATE updated_date=NOW();
INSERT INTO provider (id, first_name, last_name, created_date, updated_date) VALUES (2000000004, 'Emma', 'Varma', NOW(), NOW()) ON DUPLICATE KEY UPDATE updated_date=NOW();
INSERT INTO provider (id, first_name, last_name, created_date, updated_date) VALUES (2000000005, 'Kayla', 'Gray', NOW(), NOW()) ON DUPLICATE KEY UPDATE updated_date=NOW();
INSERT INTO provider (id, first_name, last_name, created_date, updated_date) VALUES (2000000006, 'Jacob', 'Chen', NOW(), NOW()) ON DUPLICATE KEY UPDATE updated_date=NOW();
INSERT INTO provider (id, first_name, last_name, created_date, updated_date) VALUES (2000000007, 'Rachel', 'Bailey',  NOW(), NOW()) ON DUPLICATE KEY UPDATE updated_date=NOW();


INSERT INTO department (id, department_name, created_date, updated_date) VALUES (501, 'Family Medicine', NOW(), NOW()) ON DUPLICATE KEY UPDATE updated_date=NOW();
INSERT INTO department (id, department_name, created_date, updated_date) VALUES (502, 'Cardiology', NOW(), NOW()) ON DUPLICATE KEY UPDATE updated_date=NOW();
INSERT INTO department (id, department_name, created_date, updated_date) VALUES (503, 'Neurology', NOW(), NOW()) ON DUPLICATE KEY UPDATE updated_date=NOW();
INSERT INTO department (id, department_name, created_date, updated_date) VALUES (504, 'Oncology', NOW(), NOW()) ON DUPLICATE KEY UPDATE updated_date=NOW();
INSERT INTO department (id, department_name, created_date, updated_date) VALUES (505, 'Pediatrics', NOW(), NOW()) ON DUPLICATE KEY UPDATE updated_date=NOW();
INSERT INTO department (id, department_name, created_date, updated_date) VALUES (506, 'Orthopedics', NOW(), NOW()) ON DUPLICATE KEY UPDATE updated_date=NOW();
INSERT INTO department (id, department_name, created_date, updated_date) VALUES (507, 'Dermatology', NOW(), NOW()) ON DUPLICATE KEY UPDATE updated_date=NOW();

INSERT INTO webhook (id, url, company_name, created_date, updated_date) VALUES (101, 'http://localhost:5000/appointments/eventListener', 'Company 1', NOW(), NOW()) ON DUPLICATE KEY UPDATE updated_date=NOW();