DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
     `id` int NOT NULL AUTO_INCREMENT,
     `nom` varchar(50) NOT NULL,
     `login` varchar(100) NOT NULL,
     `password` varchar(250) NOT NULL,
     `role` varchar(250) NOT NULL,
     PRIMARY KEY (`id`)
);

INSERT INTO `admin` (`id`, `nom`, `login`, `password`) VALUES
   (1,	'Nassim',	'nassim',	'dfa8b570f448cb6949aec9625f85ef110ca320cb',	'admin'),
   (2,	'Manager',	'manager',	'1a8565a9dc72048ba03b4156be3e569f22771f23',	'manager');
DROP TABLE IF EXISTS `etudiant`;
CREATE TABLE `etudiant` (
    `id` int NOT NULL AUTO_INCREMENT,
    `nom` varchar(100) NOT NULL,
    `classe` varchar(100) NOT NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO `etudiant` (`id`, `nom`, `classe`) VALUES
(1,	'Cywar',	'Génie info 4'),
(2,	'Nour',	'Génie info 5'),
(3,	'Mohamed test',	'Génie info 3'),
(4,	'nassim',	'4GI');