-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 15 Lut 2021, 17:27
-- Wersja serwera: 10.4.16-MariaDB
-- Wersja PHP: 7.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `support`
--
CREATE DATABASE IF NOT EXISTS `support` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `support`;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `application`
--

DROP TABLE IF EXISTS `application`;
CREATE TABLE IF NOT EXISTS `application` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Zrzut danych tabeli `application`
--

INSERT INTO `application` (`id`, `name`, `type`, `description`) VALUES
(1, 'dark souls', 'Game', 'Gra ktora kazdy polubi'),
(2, 'photoshop', 'Graphic', 'Application to graphically edit photos'),
(3, 'spring', 'Web', 'Application that handles updating and installing all types of \"spring\" plugins for you'),
(4, 'Experimental', 'Test', 'Experimental application just to add it');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `error`
--

DROP TABLE IF EXISTS `error`;
CREATE TABLE IF NOT EXISTS `error` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `app_id` int(11) DEFAULT NULL,
  `state` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  `title` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtb5tu5vhmwwucj6hnwwp7427k` (`app_id`),
  KEY `FKq9ld84wn005uhdq6o1eiet2k5` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Zrzut danych tabeli `error`
--

INSERT INTO `error` (`id`, `user_id`, `app_id`, `state`, `title`, `description`) VALUES
(1, 1, 1, 'NEW', 'Nie działa', 'Gra mi nie działa, zróbcie coś'),
(2, 2, 1, 'ACTIVE', 'Problem podczas uruchamiania', 'Gra się crashuje podczas uruchamiania'),
(3, 1, 1, 'NEW', 'Nadal nie działa', 'Gra mi nadal nie działa'),
(4, NULL, 1, 'RESOLVED', 'Boss jest nieśmiertelny', 'Trzeci boss napotykany w grze nie otrzymuje żadnych obrażeń, jego pasek HP się nie zmienia'),
(8, 2, 1, 'RESOLVED', 'aaaaa', 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa'),
(11, 1, 2, 'NEW', 'aaaaaaaaaaaaa', 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa'),
(12, 2, 2, 'ACTIVE', 'zzzzzzzzzzzzzzzzzz', 'zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz'),
(13, 1, 1, 'RESOLVED', 'asdasd', 'asdasdasdaaaaaaaaaaaaa'),
(14, 1, 3, 'ACTIVE', 'zzzzz', 'zzzzzzzzzzzzzzzzzzzz'),
(16, 5, 3, 'RESOLVED', 'Error\'s Title', 'ErrorsDescriptionaaaaaa'),
(17, 5, 3, 'NEW', 'Error\'s Title', '_________________________________________________________________________________'),
(18, 5, 3, 'RESOLVED', '  \'   ', 'Error\'s Description aaa'),
(20, 5, 2, 'RESOLVED', 'Nie realistyczne', 'zdjęcia przerobione tym programem są nie realistyczne i nikt się na to nie nabiera'),
(22, 1, 4, 'RESOLVED', 'ążźćś test', 'ą ć ę ł ń ó ś ź ż    ');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `pass` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `role` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Zrzut danych tabeli `user`
--

INSERT INTO `user` (`id`, `login`, `pass`, `role`) VALUES
(1, 'admin', 'admin', 'ADMIN'),
(2, 'user1', 'user1', 'USER'),
(5, 'aaaaa', 'aaaaa', 'USER'),
(6, 'bbbbb', 'bbbbb', 'USER'),
(7, 'user2', 'user2', 'USER');

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `error`
--
ALTER TABLE `error`
  ADD CONSTRAINT `FKq9ld84wn005uhdq6o1eiet2k5` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKtb5tu5vhmwwucj6hnwwp7427k` FOREIGN KEY (`app_id`) REFERENCES `application` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
