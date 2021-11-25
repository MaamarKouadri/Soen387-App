-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 21, 2021 at 03:15 AM
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
('Arrrrrrrrr', '1', 'AirCanada'),
('Arrrrrrrrr', '2', 'WestJet'),
('Arrrrrrrrr', '3', 'Sunwing'),
('Arrrrrrrrr', '4', 'AirTransat'),
('Dsssssssss', '1', 'Goku'),
('Dsssssssss', '2', 'Vegeta'),
('Dsssssssss', '3', 'Yamcha'),
('Eccccccccc', '1', 'Tesla'),
('Eccccccccc', '2', 'GM'),
('Eccccccccc', '3', 'Ford'),
('Eccccccccc', '4', 'Audi'),
('Eccccccccc', '5', 'Hyndai'),
('Eccccccccc', '6', 'Kia'),
('Vccccccccc', '1', 'Yes'),
('Vccccccccc', '2', 'No'),
('Vccccccccc', '3', 'Other');

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
('Arrrrrrrrr', 9, 'Yes'),
('Dsssssssss', 8, 'Yes'),
('Eccccccccc', 4, 'Yes'),
('Vccccccccc', 4, 'No');

-- --------------------------------------------------------

--
-- Table structure for table `poll`
--

CREATE TABLE `poll` (
  `PollName` varchar(255) DEFAULT NULL,
  `Question` varchar(255) DEFAULT NULL,
  `Choices` varchar(255) DEFAULT NULL,
  `PollId` varchar(255) NOT NULL,
  `isDelete` varchar(255) DEFAULT NULL,
  `timeStamp` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `poll`
--

INSERT INTO `poll` (`PollName`, `Question`, `Choices`, `PollId`, `isDelete`, `timeStamp`) VALUES
('AirLine', 'What is your Favorite Airline', 'Air Canada, WestJet, Sunwing, Air Transat', 'Arrrrrrrrr', 'No', NULL),
('Dragon Ball Super Favorite Character ', 'Who is your favorite character in Dragon Ball Super', 'Goku, Vegeta, Yamcha', 'Dsssssssss', 'No', NULL),
('Electric Vehicle', 'What is your favorite electric vehicle company?', 'Tesla, GM, Ford, Audi, Hyundai, Kia', 'Eccccccccc', 'No', NULL),
('Vaccine', 'Have you been vaccinated?', 'Yes, No, Other', 'Vccccccccc', 'Yes', NULL);

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
(1, 'highnoonice', 'taco@hotmail.com', 'c4ca021841ba7b4b93c06f4d34cc36eb'),
(2, 'zephyrdory', 'cheese@hotmail.com', 'f06fb03019aa2b906f669ad6e4968760'),
(3, 'beeredwood', 'hotdog@gmail.com', 'deb292a5bbfaf348ea1aff8b120cdcde'),
(4, 'Superman', 'Hero@gmail.com', 'aa71fe3b8a0841d53db13fff1dfb407b'),
(5, 'TeslaModel3', 'Tesla@Tesla.com', 'dd335f498ed05ba3881a0fbbaf777697'),
(6, 'Chevy', 'Chevy@gm.com', '75d3bc3200859ca956d5bf9b96966b72'),
(7, 'BMW', 'BMW@Hyundai.com', 'f561fb745755338d6a3e557a51b5e718'),
(8, 'Vegeta', 'Goku@myhero.com', 'b5fa69e3db79b9ad0bb89219cab3be6e'),
(9, 'Speedo', 'Speedo@speedo.com', '276a74368ea1b0f374f4d9f00dca6993'),
(10, 'hyperball', 'cat@dogs.com', '84183a282004875264fa2509e27e1b89');

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
(304056, 'Arrrrrrrrr', '4'),
(123890, 'Arrrrrrrrr', '2'),
(289049, 'Arrrrrrrrr', '3'),
(432342, 'Arrrrrrrrr', '4'),
(123456, 'Arrrrrrrrr', '2'),
(120000, 'Arrrrrrrrr', '4'),
(123400, 'Arrrrrrrrr', '1'),
(123456, 'Arrrrrrrrr', '1'),
(542344, 'Dsssssssss', '1'),
(567834, 'Dsssssssss', '1'),
(776512, 'Dsssssssss', '1'),
(123404, 'Dsssssssss', '2'),
(123456, 'Dsssssssss', '2'),
(874456, 'Eccccccccc', '3'),
(101212, 'Eccccccccc', '5'),
(662343, 'Eccccccccc', '1'),
(865412, 'Eccccccccc', '1'),
(909864, 'Eccccccccc', '5'),
(920344, 'Eccccccccc', '5'),
(129103, 'Eccccccccc', '3'),
(349098, 'Eccccccccc', '6'),
(123891, 'Eccccccccc', '3'),
(434342, 'Eccccccccc', '5'),
(475971, 'Eccccccccc', '1'),
(573604, 'Eccccccccc', '6'),
(785490, 'Eccccccccc', '6'),
(780987, 'Eccccccccc', '4'),
(345879, 'Eccccccccc', '1'),
(123456, 'Eccccccccc', '2'),
(120000, 'Eccccccccc', '1'),
(123400, 'Eccccccccc', '4'),
(342461, 'Vccccccccc', '2'),
(757635, 'Vccccccccc', '2'),
(858373, 'Vccccccccc', '1'),
(876431, 'Vccccccccc', '3'),
(948756, 'Vccccccccc', '1'),
(456789, 'Vccccccccc', '1'),
(120000, 'Vccccccccc', '1'),
(123400, 'Vccccccccc', '1'),
(123456, 'Vccccccccc', '2');

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
