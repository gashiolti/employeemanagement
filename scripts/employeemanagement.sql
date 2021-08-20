DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` char(68) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- A generation tool is avail at: http://www.luv2code.com/generate-bcrypt-password
--
-- Default passwords here are: fun123
--

INSERT INTO `users` 
VALUES 
('admin','{bcrypt}$2a$10$xqHFuUxthUFfQvbU7vC24.56tC8h5xDNZBC4dHMaCZiXXMbXbZIVW',1),
('admin2','{bcrypt}$2a$10$y6xOI9TnlQeZtj9LO/ksOOqiJkNmk8vKPO1Gw0Xa/Yig/5P35bcr6',1),
('admin3','{bcrypt}$2a$10$azsMxg.//QupJOySwj2ds.UZ/L9t3X8lBYbVw55HZC1O1TRfFYeoy',1);


--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `authorities`
--

INSERT INTO `authorities` 
VALUES 
('admin','ROLE_ADMIN'),
('admin2','ROLE_ADMIN'),
('admin3','ROLE_ADMIN');


DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
	`id` int(11) NOT NULL PRIMARY KEY,
    `first_name` varchar(45) DEFAULT NULL,
    `last_name` varchar(45) DEFAULT NULL,
    `email` varchar(45) DEFAULT NULL,
    `address` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


--
-- Dumping data for table `employee`
--

INSERT INTO `employee` 
VALUES 
(1,'Timber','Patterson','timberpaterson@gmail.com','4201 Owen Lane'),
(2,'John','Doe','johndoe@gmail.com','2187 University Street'),
(3,'Ajay','Reo','ajayreo@gmail.com','2464 Emily Renzelli Boulevard'),
(4,'Mary','Public','marypublic@gmail.com','3485 Traction Street'),
(5,'Aron','Gates','arongates@gmail.com','3028 Harvest Lane');