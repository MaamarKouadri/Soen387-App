-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 15, 2021 at 03:28 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `soen-387 polls`
--

-- --------------------------------------------------------

--
-- Table structure for table `haspoll`
--

CREATE TABLE `haspoll` (
  `PollId` varchar(255) NOT NULL,
  `UserId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `poll`
--

CREATE TABLE `poll` (
  `Pin` int(11) DEFAULT NULL,
  `PollName` varchar(255) DEFAULT NULL,
  `Question` varchar(255) DEFAULT NULL,
  `Choices` varchar(255) DEFAULT NULL,
  `PollId` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `UserId` int(11) NOT NULL,
  `UserName` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `UserPassword` varchar(255) DEFAULT NULL,
  `Pin` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`UserId`, `UserName`, `Email`, `UserPassword`, `Pin`) VALUES
(1, 'highnoonice', 'taco@hotmail.com', '1234', 1200000000),
(2, 'zephyrdory', 'cheese@hotmail.com', '1234', 1000000000),
(3, 'beeredwood', 'hotdog@gmail.com', '12354', 1230000000),
(4, 'Superman', 'Hero@gmail.com', '123123', 1234000000),
(5, 'TeslaModel3', 'Tesla@Tesla.com', '1238901', 123400000),
(6, 'Chevy', 'Chevy@gm.com', '54321', 1234500000),
(7, 'BMW', 'BMW@Hyundai.com', '1234', 123456000),
(8, 'Vegeta', 'Goku@myhero.com', '543456', 1234567000),
(9, 'Speedo', 'Speedo@speedo.com', '726349', 123456780),
(10, 'hyperball', 'cat@dogs.com', '123454231', 1234567980);

-- --------------------------------------------------------

--
-- Table structure for table `vote`
--

CREATE TABLE `vote` (
  `Pin` int(11) NOT NULL,
  `PollId` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `haspoll`
--
ALTER TABLE `haspoll`
  ADD PRIMARY KEY (`PollId`,`UserId`),
  ADD KEY `UserId` (`UserId`);

--
-- Indexes for table `poll`
--
ALTER TABLE `poll`
  ADD PRIMARY KEY (`PollId`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`UserId`);

--
-- Indexes for table `vote`
--
ALTER TABLE `vote`
  ADD PRIMARY KEY (`PollId`,`Pin`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `haspoll`
--
ALTER TABLE `haspoll`
  ADD CONSTRAINT `haspoll_ibfk_1` FOREIGN KEY (`UserId`) REFERENCES `users` (`UserId`),
  ADD CONSTRAINT `haspoll_ibfk_2` FOREIGN KEY (`PollId`) REFERENCES `poll` (`PollId`);

--
-- Constraints for table `vote`
--
ALTER TABLE `vote`
  ADD CONSTRAINT `vote_ibfk_1` FOREIGN KEY (`PollId`) REFERENCES `poll` (`PollId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
