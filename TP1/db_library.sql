--
-- Base de données : `db_library`
Create Database db_library;
--


use db_library;


--
-- Structure de la table `User`
--

CREATE TABLE `User` (
    
    `login` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    `password` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    `role` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL    
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `User`
  ADD PRIMARY KEY (`login`);

INSERT INTO `User` Values 
('admin','admin1234','admin'),
('guest','guest1234','guest');

--
-- Structure de la table `Auteur`
--

CREATE TABLE  `Auteur`(
    `matricule` int(11) NOT NULL,
    `nom`  varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL, 
    `prenom`  varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
    `genre` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `Auteur`
  ADD PRIMARY KEY (`matricule`);


--
-- Structure de la table `livre`
--

CREATE TABLE `livre` (
  `isbn` int(11) NOT NULL,
  `Titre` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `editeur` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `date_edition` Date NOT NULL,
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `matricule` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `livre`
  ADD PRIMARY KEY (`isbn`);
  
ALTER TABLE `livre`
  ADD FOREIGN KEY (`matricule`) REFERENCES AUTEUR(`matricule`);
  