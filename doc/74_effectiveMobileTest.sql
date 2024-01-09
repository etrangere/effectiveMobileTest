-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : mar. 09 jan. 2024 à 14:58
-- Version du serveur : 8.0.35-0ubuntu0.22.04.1
-- Version de PHP : 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `74.effectiveMobileTest`
--

-- --------------------------------------------------------

--
-- Structure de la table `comment_table`
--

CREATE TABLE `comment_table` (
  `id` bigint NOT NULL,
  `comments` longtext,
  `task_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `task_table`
--

CREATE TABLE `task_table` (
  `id` bigint NOT NULL,
  `author` varchar(100) DEFAULT NULL,
  `description` longtext,
  `header` text,
  `priority` enum('HIGH','LOW','MEDIUM') DEFAULT NULL,
  `status` enum('COMPLETED','IN_PROGRESS','PENDING') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `task_table_users`
--

CREATE TABLE `task_table_users` (
  `tasks_id` bigint NOT NULL,
  `users_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `user_table`
--

CREATE TABLE `user_table` (
  `id` bigint NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `executor` bit(1) DEFAULT NULL,
  `first_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `comment_table`
--
ALTER TABLE `comment_table`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKnww3g0k3ts4f4q7sf0ulb6mug` (`task_id`);

--
-- Index pour la table `task_table`
--
ALTER TABLE `task_table`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `task_table_users`
--
ALTER TABLE `task_table_users`
  ADD KEY `FKdhtvvjyjq66b2xxlpbu6nwyx0` (`users_id`),
  ADD KEY `FKkq8u4ilqraecyk2bvtaw03hb6` (`tasks_id`);

--
-- Index pour la table `user_table`
--
ALTER TABLE `user_table`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `comment_table`
--
ALTER TABLE `comment_table`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `task_table`
--
ALTER TABLE `task_table`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `user_table`
--
ALTER TABLE `user_table`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `comment_table`
--
ALTER TABLE `comment_table`
  ADD CONSTRAINT `FKnww3g0k3ts4f4q7sf0ulb6mug` FOREIGN KEY (`task_id`) REFERENCES `task_table` (`id`);

--
-- Contraintes pour la table `task_table_users`
--
ALTER TABLE `task_table_users`
  ADD CONSTRAINT `FKdhtvvjyjq66b2xxlpbu6nwyx0` FOREIGN KEY (`users_id`) REFERENCES `user_table` (`id`),
  ADD CONSTRAINT `FKkq8u4ilqraecyk2bvtaw03hb6` FOREIGN KEY (`tasks_id`) REFERENCES `task_table` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
