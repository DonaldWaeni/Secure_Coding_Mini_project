-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 23, 2024 at 10:33 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mini_project`
--

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `name` varchar(100) DEFAULT NULL,
  `reg_number` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`name`, `reg_number`, `password`) VALUES
('ef', 'ev', '*0Wk8t(aZ9wS'),
('', '', ''),
('q', 'qd', 'Aje0(Xk00lE5'),
('qd', 'qdt', 'Aje0(Xk00lE5'),
('sa', 'sds', '(f@DQtx8GasK'),
('sad', 'sdsc', '(f@DQtx8GasK'),
('sadcac', 'sdscf', 'YDBGil5OX#Yy'),
('h', 'u', 'V&tfSqa6rGYC'),
('WAENI', 'H230768J', '8SZ*20Zv37L&'),
('tANYA', 'AJSGUOS', 'Mj&Rkir2oxj2'),
('SD', 'FD', '^rF#dwsPJ2qS'),
('we', 'rf', 'ZlUB$H@U6M7N'),
('q', 'q', 'q'),
('r', 'r1', 'r2'),
('Donald', 'H230769J', 'DON'),
('Don', 'H239769J', '9t^(lzBs2o(V'),
('Waeni', 'H230769J', 'fuck'),
('Robert\'); DROP TABLE students;', 'h34', ')3wHV#FwZhng'),
('22q', 'ww', 'makapenga'),
('Dkb', 'cfc', 'wasp'),
('Ding', 'dong', 'dong'),
('Wangu', 'H230769J', 'we');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
