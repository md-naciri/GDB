CREATE DATABASE IF NOT EXISTS gdb;
USE gdb;

CREATE TABLE `book` (
  `isbn` varchar(255) NOT NULL PRIMARY KEY,
  `title` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `quantity` int NOT NULL
);

DELIMITER //
CREATE TRIGGER `author_check` 
BEFORE INSERT ON `book` 
FOR EACH ROW 
BEGIN
	IF new.author = "" or new.author IS NULL THEN SET new.author = "The writer is unknown";
	END IF; 
END //

CREATE TABLE `borrower` (
  `memberId` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL
);

CREATE TABLE `borrowing` (
  `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `copies_copyId` int NOT NULL,
  `borrower_memberId` int NOT NULL,
  `borrowingDate` date NOT NULL,
  `returnDate` date NOT NULL
);

CREATE TABLE `copies` (
  `copyId` int KEY NOT NULL AUTO_INCREMENT PRIMARY,
  `status` varchar(255) NOT NULL,
  `isbn_book` varchar(255) NOT NULL
);

ALTER TABLE `borrowing`
  ADD KEY `memID` (`borrower_memberId`),
  ADD KEY `copID` (`copies_copyId`);

ALTER TABLE `copies`
  ADD KEY `isbn_book` (`isbn_book`);

ALTER TABLE `borrowing`
  ADD CONSTRAINT `copID` FOREIGN KEY (`copies_copyId`) REFERENCES `copies` (`copyId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `memID` FOREIGN KEY (`borrower_memberId`) REFERENCES `borrower` (`memberId`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `copies`
  ADD CONSTRAINT `isbn_book` FOREIGN KEY (`isbn_book`) REFERENCES `book` (`isbn`) ON DELETE RESTRICT ON UPDATE RESTRICT;