-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 19, 2021 at 03:27 AM
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
-- Database: `soen-387_option2`
--

-- --------------------------------------------------------

--
-- Table structure for table `choice`
--

CREATE TABLE `choice` (
  `PollId` varchar(255) NOT NULL,
  `ChoiceID` varchar(255) NOT NULL,
  `choiceName` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `choice`
--

INSERT INTO `choice` (`PollId`, `ChoiceID`, `choiceName`) VALUES
('Air2021', '1', 'Air Canada'),
('Air2021', '2', 'WestJet'),
('Air2021', '3', 'Sunwing'),
('Air2021', '4', 'Air Transat'),
('DBS2021', '1', 'Goku'),
('DBS2021', '2', 'Vegeta'),
('DBS2021', '3', 'Yamcha'),
('ELECTRIC2021', '1', 'Tesla'),
('ELECTRIC2021', '2', 'GM'),
('ELECTRIC2021', '3', 'Ford'),
('ELECTRIC2021', '4', 'Audi'),
('ELECTRIC2021', '5', 'Hyndai'),
('ELECTRIC2021', '6', 'Kia'),
('VAC2021', '1', 'Yes'),
('VAC2021', '2', 'No'),
('VAC2021', '3', 'Prefer not to say');

-- --------------------------------------------------------

--
-- Table structure for table `haspoll`
--

CREATE TABLE `haspoll` (
  `PollId` varchar(255) NOT NULL,
  `UserId` int(11) NOT NULL,
  `isActive` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `haspoll`
--

INSERT INTO `haspoll` (`PollId`, `UserId`, `isActive`) VALUES
('Air2021', 9, 'No'),
('DBS2021', 8, 'Yes'),
('ELECTRIC2021', 4, 'Yes'),
('VAC2021', 4, 'Yes');

-- --------------------------------------------------------

--
-- Table structure for table `poll`
--

CREATE TABLE `poll` (
  `PollName` varchar(255) DEFAULT NULL,
  `Question` varchar(255) DEFAULT NULL,
  `Choices` varchar(255) DEFAULT NULL,
  `PollId` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `poll`
--

INSERT INTO `poll` (`PollName`, `Question`, `Choices`, `PollId`) VALUES
('AirLine', 'What is your Favorite Airline', 'Air Canada, WestJet, Sunwing, Air Transat', 'Air2021'),
('Dragon Ball Super Favorite Character ', 'Who is your favorite character in Dragon Ball Super', 'Goku, Vegeta, Yamcha', 'DBS2021'),
('Electric Vehicle', 'What is your favorite electric vehicle company?', 'Tesla, GM, Ford, Audi, Hyundai, Kia', 'ELECTRIC2021'),
('Vaccine', 'Have you been vaccinated?', 'Yes, No, Prefer not to say', 'VAC2021');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `UserId` int(11) NOT NULL,
  `UserName` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `UserPassword` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`UserId`, `UserName`, `Email`, `UserPassword`) VALUES
(1, 'highnoonice', 'taco@hotmail.com', '1234'),
(2, 'zephyrdory', 'cheese@hotmail.com', '1234'),
(3, 'beeredwood', 'hotdog@gmail.com', '12354'),
(4, 'Superman', 'Hero@gmail.com', '123123'),
(5, 'TeslaModel3', 'Tesla@Tesla.com', '1238901'),
(6, 'Chevy', 'Chevy@gm.com', '54321'),
(7, 'BMW', 'BMW@Hyundai.com', '1234'),
(8, 'Vegeta', 'Goku@myhero.com', '543456'),
(9, 'Speedo', 'Speedo@speedo.com', '726349'),
(10, 'hyperball', 'cat@dogs.com', '123454231');

-- --------------------------------------------------------

--
-- Table structure for table `vote`
--

CREATE TABLE `vote` (
  `Pin` int(11) NOT NULL,
  `PollId` varchar(255) NOT NULL,
  `choiceID` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `vote`
--

INSERT INTO `vote` (`Pin`, `PollId`, `choiceID`) VALUES
(30405, 'Air2021', '4'),
(1238901, 'Air2021', '2'),
(2890491, 'Air2021', '3'),
(4323421, 'Air2021', '4'),
(123456000, 'Air2021', '2'),
(1200000000, 'Air2021', '4'),
(1234000000, 'Air2021', '1'),
(1234567980, 'Air2021', '1'),
(5423, 'DBS2021', '1'),
(5678, 'DBS2021', '1'),
(7765, 'DBS2021', '1'),
(1234000000, 'DBS2021', '2'),
(1234567980, 'DBS2021', '2'),
(874, 'ELECTRIC2021', '3'),
(10121, 'ELECTRIC2021', '5'),
(66234, 'ELECTRIC2021', '1'),
(86541, 'ELECTRIC2021', '1'),
(90986, 'ELECTRIC2021', '5'),
(92034, 'ELECTRIC2021', '5'),
(129102, 'ELECTRIC2021', '3'),
(349098, 'ELECTRIC2021', '6'),
(1238901, 'ELECTRIC2021', '3'),
(4343432, 'ELECTRIC2021', '5'),
(4759731, 'ELECTRIC2021', '1'),
(5736204, 'ELECTRIC2021', '6'),
(7850490, 'ELECTRIC2021', '6'),
(7890987, 'ELECTRIC2021', '4'),
(39458797, 'ELECTRIC2021', '1'),
(123456000, 'ELECTRIC2021', '2'),
(1200000000, 'ELECTRIC2021', '1'),
(1234000000, 'ELECTRIC2021', '4'),
(34246, 'VAC2021', '2'),
(75763, 'VAC2021', '2'),
(858373, 'VAC2021', '1'),
(8764313, 'VAC2021', '3'),
(94875628, 'VAC2021', '1'),
(456789876, 'VAC2021', '1'),
(1200000000, 'VAC2021', '1'),
(1234000000, 'VAC2021', '1'),
(1234567980, 'VAC2021', '2');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `choice`
--
ALTER TABLE `choice`
  ADD PRIMARY KEY (`PollId`,`ChoiceID`);

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
  ADD PRIMARY KEY (`PollId`,`Pin`,`choiceID`) USING BTREE;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `choice`
--
ALTER TABLE `choice`
  ADD CONSTRAINT `choice_ibfk_1` FOREIGN KEY (`PollId`) REFERENCES `poll` (`PollId`);

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
