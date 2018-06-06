-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 06, 2018 at 11:14 PM
-- Server version: 10.1.31-MariaDB
-- PHP Version: 7.2.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `order_system_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

CREATE TABLE `client` (
  `clientID` int(11) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT 'Nenurodyta',
  `phoneNumber` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT 'Nenurodyta'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_lithuanian_ci;

--
-- Dumping data for table `client`
--

INSERT INTO `client` (`clientID`, `name`, `phoneNumber`) VALUES
(129, 'Dovydas Auryla', '865429430'),
(131, 'Vardenis Pavardenis', '865429444'),
(132, 'Jonas Jonaitis', '865987632'),
(133, 'Dovydas Auryla', '865429430'),
(134, 'Jonas Jonaitis', '865987632'),
(135, 'Ona Onute', '869843298'),
(136, 'Jonas Jonas', '898789825'),
(137, 'Andrius Andrius', '865496255'),
(138, 'Rokas Rokas', '8654882654'),
(139, 'Aivaras Aivaras', '865425489'),
(140, 'Odeta Odeta', '865485696'),
(141, 'Sigitas Sigitas', '865495548'),
(142, 'Antanas Antanas', '8654569877'),
(143, 'Reneta Reneta', '865456545'),
(144, 'Linas Linas', '865456544'),
(145, 'Giedre Giedre', '865421568'),
(153, 'Mantas Mantas', '865429434'),
(155, 'Monika Monika', '865879878');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `orderId` int(11) NOT NULL,
  `descriptionOfOrder` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT 'Nenurodyta',
  `manager` varchar(25) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT 'Nenurodyta',
  `status` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT 'Vykdomas',
  `deliveryDate` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT 'Nenurodyta',
  `orderDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_lithuanian_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`orderId`, `descriptionOfOrder`, `manager`, `status`, `deliveryDate`, `orderDate`) VALUES
(129, 'Šviestuvas 8794312', 'Jonas', 'baigtas', '2018-06-04', '2018-06-05 15:22:45'),
(131, 'Laidas 3x4 Cyky', 'Petras', 'vykdomas', '2018-06-07', '2018-06-05 15:01:41'),
(132, 'Laistymo žarna 30d', 'Antanas', 'baigtas', '2018-06-03', '2018-06-05 15:02:49'),
(133, 'Vonia 150x150', 'Petras', 'vykdomas', '2018-06-19', '2018-06-05 15:03:36'),
(134, 'Linoleumas 1596587', 'Petras', 'vykdomas', '2018-06-07', '2018-06-05 15:04:49'),
(135, 'Maišytuvas m-5621', 'Jonas', 'nutrauktas', '2018-06-20', '2018-06-05 15:05:44'),
(136, 'tvoros skydas 180*250', 'Petras', 'vykdomas', '2018-06-20', '2018-06-05 15:07:09'),
(137, 'Laidas 3x2.5 cyky', 'Petras', 'vykdomas', '2018-06-07', '2018-06-05 15:08:08'),
(138, 'Dušo kabina a-50534', 'Antanas', 'vykdomas', '2018-06-20', '2018-06-05 15:09:09'),
(139, 'Klozetas et-51564', 'Antanas', 'vykdomas', '2018-06-27', '2018-06-05 15:09:51'),
(140, 'Šviestuvas 1648', 'Petras', 'baigtas', '2018-06-13', '2018-06-05 15:10:51'),
(141, 'Cementas', 'Jonas', 'vykdomas', '2018-06-20', '2018-06-05 15:12:02'),
(142, 'Betini mišinys 25 kg', 'Petras', 'baigtas', '2018-05-30', '2018-06-05 15:12:52'),
(143, 'Dušo kabina 94156815', 'Jonas', 'vykdomas', '2018-07-26', '2018-06-05 15:13:46'),
(144, 'Vonia ET-541520', 'Petras', 'nutrauktas', '2018-06-20', '2018-06-05 15:14:53'),
(145, 'Dažai', 'Petras', 'vykdomas', '2018-06-27', '2018-06-05 15:15:53'),
(153, 'Laidas 3x4', 'Petras', 'vykdomas', '2018-06-20', '2018-06-06 21:13:21'),
(155, 'Tapetai 524532', 'Jonas', 'Vykdomas', '2018-06-07', '2018-06-06 21:11:01');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `productID` int(11) NOT NULL,
  `amount` float DEFAULT NULL,
  `price` float NOT NULL,
  `supplier` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL DEFAULT 'Nenurodyta'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_lithuanian_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`productID`, `amount`, `price`, `supplier`) VALUES
(129, 1, 100, 'Nenurodyta'),
(131, 25, 8.9, 'Elstila'),
(132, 55, 101, 'Dauguva'),
(133, 1, 290, 'Ravak'),
(134, 38.5, 109, 'Iris'),
(135, 1, 22, 'Merlana'),
(136, 6, 85, 'Metalo pasaulis'),
(137, 500, 625, 'Elstila'),
(138, 1, 199, 'Aikada'),
(139, 1, 55, 'Etovis'),
(140, 1, 232, 'Konstanta'),
(141, 25, 5, 'Betono Mozaika'),
(142, 50, 199, 'Betono Mozaika'),
(143, 1, 599, 'Ravak'),
(144, 1, 100, 'Etovis'),
(145, 10, 135, 'Spalvu mozaika'),
(153, 123, 1099, 'Etovis'),
(155, 1, 10, 'Roch');

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `companyCode` int(50) NOT NULL,
  `companyName` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `country` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `address` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phoneNumber` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `person` text COLLATE utf8_unicode_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`companyCode`, `companyName`, `country`, `address`, `phoneNumber`, `person`) VALUES
(8564321, 'ė', 'dsv', 'dfgd', 'dfg', 'dfg'),
(30249095, 'Eltido', 'Lietuva', 'Vandžiogalos plentas 106G, Domeikava, LT-54358 Kauno r.', '+37037553070', 'Ovidijus Bartkus'),
(111708172, 'Ravak baltic', 'Lietuva', 'Butrimonių g. 5-205, LT-50203 Kaunas', '+37021562561', 'Audrius Rudminas'),
(111799581, 'Makanada', 'Lietuva', 'Šilutės pl. 51, LT-94105 Klaipėda', '+37045613245', 'Yun Tang'),
(144625759, 'Metalo prekyba', 'Lietuva', 'V. Bielskio g. 32a, LT-76135 Šiauliai', '+37012345678', 'Jurgis Čepulis'),
(162767856, 'Etovis', 'Lietuva', 'Mažūnų g. 2, Mažūnų k., LT-86120 Kelmės r.', '+37042761456', 'Tomas Adomaitis'),
(257657640, 'Kalvis', 'Lietuva', 'Pramonės g. 15, LT-78137 Šiauliai', '+37045234546', 'Arnas Goberi'),
(268428970, 'Raguvos baldai', 'Lietuva', 'Nevėžio g. 9, Raguva, LT-38154 Panevėžio r.', '+37065431546', 'Robertas Vaitkevičius'),
(302431326, 'Elgrandas', 'Lietuva', 'Žemgulio g. 2, LT-44151 Kaunas', '+37065429340', 'Evaldas Kondrotas'),
(302454521, 'Konstanta', 'Latvija', 'Kiekava, Pledarų g. 22 , Rigos rajonas, LV-2123. Latvija', '+37167205159', 'Juri Bagdins');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`clientID`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`orderId`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`productID`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`companyCode`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `clientID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=156;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `orderId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=156;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `productID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=156;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
