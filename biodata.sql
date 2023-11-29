-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Nov 29, 2023 at 03:59 PM
-- Server version: 10.11.5-MariaDB-3
-- PHP Version: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `java_biodata`
--

-- --------------------------------------------------------

--
-- Table structure for table `biodata`
--

CREATE TABLE `biodata` (
  `id` varchar(255) NOT NULL,
  `nama` varchar(255) DEFAULT NULL,
  `no_telp` varchar(255) DEFAULT NULL,
  `alamat` varchar(255) DEFAULT NULL,
  `jenis_kelamin` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `biodata`
--

INSERT INTO `biodata` (`id`, `nama`, `no_telp`, `alamat`, `jenis_kelamin`) VALUES
('1c2d6f0e-92d8-4376-aea2-be13abac9d53', 'koji v2', '11101723123', 'asd v2', 'Laki-Laki'),
('7d348ccf-6a59-471c-850b-d49dfbb4c3ad', 'alfarozi', '181268174381', 'jln no1', 'Laki-Laki');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `biodata`
--
ALTER TABLE `biodata`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
