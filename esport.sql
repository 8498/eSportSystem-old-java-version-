-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: localhost:3307
-- Czas generowania: 11 Wrz 2016, 22:50
-- Wersja serwera: 10.1.10-MariaDB
-- Wersja PHP: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `esport`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `employees`
--

CREATE TABLE `employees` (
  `id` int(11) NOT NULL,
  `firstname` varchar(128) NOT NULL,
  `lastname` varchar(128) NOT NULL,
  `email` varchar(128) NOT NULL,
  `birthdate` date NOT NULL,
  `phone` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `employees`
--

INSERT INTO `employees` (`id`, `firstname`, `lastname`, `email`, `birthdate`, `phone`) VALUES
(9, 'test', 'test', 'test@test.pl', '1995-05-05', 600600600),
(16, 'test1', 'test1', 'test1@test.pl', '1995-05-05', 600600600),
(17, 'Jan', '"Kowalski', 'jankowalski@gmail.com', '1990-01-01', 500500500),
(18, 'Janx', 'Kowalskix', 'jankowalskix@gmail.com', '1990-01-01', 500500501),
(19, 'Andrzej', 'Nyga', 'andrzej@gmail.com', '1995-03-03', 545555555),
(20, 'Andrzej', 'Nyga', 'andrzej@gmail.com', '1990-03-03', 545500500),
(21, 'Andrzej', 'Nyga', 'andrzej@gmail.com', '1990-03-03', 545500500),
(22, 'Janek', 'Dolas', 'dolas@gmail.com', '1990-02-04', 500500500),
(23, 'Ziom', 'Ziomek', 'ziomek@gmail.com', '1997-09-25', 500500500),
(24, 'jacek', 'placek', 'placek@gmail.com', '1996-09-18', 900900900);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `offices`
--

CREATE TABLE `offices` (
  `id` int(11) NOT NULL,
  `name` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `offices`
--

INSERT INTO `offices` (`id`, `name`) VALUES
(12, 'test'),
(13, 'test3'),
(14, 'test2');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `office_employee`
--

CREATE TABLE `office_employee` (
  `employee_id` int(11) NOT NULL,
  `office_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `office_employee`
--

INSERT INTO `office_employee` (`employee_id`, `office_id`) VALUES
(9, 12),
(16, 12),
(17, 12),
(18, 12),
(19, 12),
(22, 13),
(23, 14),
(24, 13);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `name` varchar(128) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
(1, 'admin'),
(2, 'trainer'),
(3, 'standard');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `roles_users`
--

CREATE TABLE `roles_users` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `roles_users`
--

INSERT INTO `roles_users` (`user_id`, `role_id`) VALUES
(5, 3),
(4, 1),
(6, 1),
(7, 1),
(8, 3);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `login` varchar(128) NOT NULL,
  `email` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  `lastlogin` datetime NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `users`
--

INSERT INTO `users` (`id`, `login`, `email`, `password`, `lastlogin`) VALUES
(4, 'admin', 'admin1@admin.com', 'ea847988ba59727dbf4e34ee75726dc3', '2016-09-11 22:33:53'),
(5, 'test', 'test@tescik.com', '5557fe7da1467963c56b63a65216fc28', '2016-07-13 07:55:13'),
(7, 'admin2', 'admin2@admin.com', 'ea847988ba59727dbf4e34ee75726dc3', '2016-09-11 16:10:49'),
(8, 'user', 'user@user.com', 'ea847988ba59727dbf4e34ee75726dc3', '2016-09-11 17:37:36');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `offices`
--
ALTER TABLE `offices`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `office_employee`
--
ALTER TABLE `office_employee`
  ADD KEY `employee_id` (`employee_id`),
  ADD KEY `office_id` (`office_id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `employees`
--
ALTER TABLE `employees`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
--
-- AUTO_INCREMENT dla tabeli `offices`
--
ALTER TABLE `offices`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT dla tabeli `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT dla tabeli `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
