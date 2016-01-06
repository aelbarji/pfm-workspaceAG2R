-- phpMyAdmin SQL Dump
-- version 3.4.5

--
-- Base de données: `epi`
--

-- --------------------------------------------------------

--
-- Structure de la table `meteo_caisse`
--

CREATE TABLE IF NOT EXISTS `meteo_caisse` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom_caisse` varchar(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=30 ;

--
-- Contenu de la table `meteo_caisse`
--

INSERT INTO `meteo_caisse` (`id`, `nom_caisse`) VALUES
(1, 'BPL'),
(2, 'MP'),
(3, 'LC'),
(6, 'NFE'),
(7, 'PIC'),
(8, 'CEN'),
(9, 'PAL'),
(10, 'CAZ'),
(11, 'LR'),
(12, 'PAC'),
(13, 'APC'),
(14, 'LCA'),
(15, 'IDF'),
(16, 'BCP'),
(17, 'ALS'),
(18, 'BFC'),
(19, 'LDA'),
(20, 'RA'),
(21, 'BT'),
(22, 'BR'),
(23, 'BDI'),
(24, 'NC'),
(25, 'BDAF'),
(26, 'PDL'),
(27, 'CA'),
(28, 'CFB'),
(29, 'BRET');

-- --------------------------------------------------------

--
-- Structure de la table `meteo_destinataire`
--

CREATE TABLE IF NOT EXISTS `meteo_destinataire` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_groupeMeteo` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

-- --------------------------------------------------------

--
-- Structure de la table `meteo_environnement`
--

CREATE TABLE IF NOT EXISTS `meteo_environnement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(20) NOT NULL,
  `technique` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Contenu de la table `meteo_environnement`
--

INSERT INTO `meteo_environnement` (`id`, `libelle`, `technique`) VALUES
(1, 'IP1', 0),
(2, 'Serveur13', 1),
(3, 'IP2', 0),
(4, 'Serveur23', 1),
(6, 'OCEOR', 0),
(7, 'Serveur06', 1),
(8, 'Yvoire', 0),
(9, 'CRC', 0);

-- --------------------------------------------------------

--
-- Structure de la table `meteo_environnement_caisse`
--

CREATE TABLE IF NOT EXISTS `meteo_environnement_caisse` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_envir` int(11) NOT NULL,
  `id_caisse` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=50 ;

--
-- Contenu de la table `meteo_environnement_caisse`
--

INSERT INTO `meteo_environnement_caisse` (`id`, `id_envir`, `id_caisse`) VALUES
(1, 1, 1),
(6, 1, 3),
(7, 1, 2),
(10, 1, 6),
(11, 1, 7),
(12, 1, 8),
(13, 1, 9),
(14, 1, 10),
(15, 1, 11),
(16, 1, 12),
(17, 3, 13),
(18, 3, 14),
(19, 3, 15),
(20, 3, 16),
(21, 3, 17),
(22, 3, 18),
(23, 3, 19),
(24, 3, 20),
(25, 6, 21),
(26, 6, 22),
(27, 6, 23),
(28, 6, 24),
(29, 6, 25),
(30, 2, 2),
(31, 2, 26),
(32, 2, 3),
(33, 2, 27),
(34, 2, 6),
(35, 2, 7),
(36, 2, 9),
(37, 2, 28),
(38, 4, 13),
(39, 4, 29),
(40, 4, 15),
(41, 4, 8),
(42, 4, 16),
(43, 7, 19),
(44, 7, 18),
(45, 7, 20),
(46, 7, 17),
(47, 7, 12),
(48, 7, 11),
(49, 7, 10);

-- --------------------------------------------------------

--
-- Structure de la table `meteo_envoi`
--

CREATE TABLE IF NOT EXISTS `meteo_envoi` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_groupeMeteo` int(11) NOT NULL,
  `date` date NOT NULL,
  `horaire` time NOT NULL,
  `date_envoi` date NOT NULL,
  `heure_envoi` time NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

-- --------------------------------------------------------

--
-- Structure de la table `meteo_etatcourant`
--

CREATE TABLE IF NOT EXISTS `meteo_etatcourant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_indicateur` int(11) NOT NULL,
  `etat_courant` float NOT NULL,
  `commentaire` varchar(200) DEFAULT NULL,
  `date` date NOT NULL,
  `horaire` time NOT NULL,
  `heure_saisie` time NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `meteo_etatpossible`
--

CREATE TABLE IF NOT EXISTS `meteo_etatpossible` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `libelle_etat` varchar(20) NOT NULL,
  `couleur` varchar(20) NOT NULL,
  `definition` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Contenu de la table `meteo_etatpossible`
--

INSERT INTO `meteo_etatpossible` (`id`, `libelle_etat`, `couleur`, `definition`) VALUES
(1, 'OK', '#99cc00', 'Service rendu normalement'),
(2, 'PERTU', '#f68e1c', 'Service perturbé'),
(3, 'KO', '#DA4646', 'Service interrompu'),
(4, 'Disponible', '#99cc00', 'Disponible'),
(5, 'Disponible J-1', '#808000', 'Disponible J-1'),
(6, 'Incident batch', '#f68e1c', 'Disponible avec incident batch'),
(7, 'Indisponible', '#DA4646', 'Indisponible'),
(8, 'En cours', '#1E90FF', 'En cours'),
(9, 'A venir', '#FFFF00', 'A venir');

-- --------------------------------------------------------

--
-- Structure de la table `meteo_groupemeteo`
--

CREATE TABLE IF NOT EXISTS `meteo_groupemeteo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom_groupeMeteo` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=26 ;

-- --------------------------------------------------------

--
-- Structure de la table `meteo_groupemeteo_horaire`
--

CREATE TABLE IF NOT EXISTS `meteo_groupemeteo_horaire` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_groupeMeteo` int(11) NOT NULL,
  `horaire` time NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

-- --------------------------------------------------------

--
-- Structure de la table `meteo_groupemeteo_meteo`
--

CREATE TABLE IF NOT EXISTS `meteo_groupemeteo_meteo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_groupeMeteo` int(11) NOT NULL,
  `id_meteo` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

-- --------------------------------------------------------

--
-- Structure de la table `meteo_indicateur`
--

CREATE TABLE IF NOT EXISTS `meteo_indicateur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `automatique` tinyint(4) NOT NULL,
  `id_service` int(11) NOT NULL,
  `id_environnement` int(11) NOT NULL,
  `id_typeIndicateur` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `meteo_meteo`
--

CREATE TABLE IF NOT EXISTS `meteo_meteo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom_meteo` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=29 ;

-- --------------------------------------------------------

--
-- Structure de la table `meteo_plagefonctionnement`
--

CREATE TABLE IF NOT EXISTS `meteo_plagefonctionnement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `heure_debut` time NOT NULL,
  `heure_fin` time NOT NULL,
  `zone` int(11) NOT NULL,
  `id_indicateur` int(11) NOT NULL,
  `etat_desire` varchar(11) NOT NULL,
  `id_seuil` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `meteo_plagefonct_jour`
--

CREATE TABLE IF NOT EXISTS `meteo_plagefonct_jour` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_plageFonct` int(11) NOT NULL,
  `jour` int(11) NOT NULL,
  `ferie` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `meteo_service_meteo`
--

CREATE TABLE IF NOT EXISTS `meteo_service_meteo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_service` int(11) NOT NULL,
  `id_meteo` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `meteo_seuil_plage`
--

CREATE TABLE IF NOT EXISTS `meteo_seuil_plage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ko_min` varchar(11) DEFAULT NULL,
  `ko_max` varchar(11) DEFAULT NULL,
  `pertu_min` varchar(11) DEFAULT NULL,
  `pertu_max` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

-- --------------------------------------------------------

--
-- Structure de la table `meteo_typeindicateur`
--

CREATE TABLE IF NOT EXISTS `meteo_typeindicateur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(20) NOT NULL,
  `format` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `meteo_typeindicateur`
--

INSERT INTO `meteo_typeindicateur` (`id`, `type`, `format`) VALUES
(0, 'NbGab', 'reel'),
(1, 'OK/PERTU', 'liste'),
(2, 'IP1_IP2', 'liste_IP');

-- --------------------------------------------------------

--
-- Structure de la table `meteo_typeindic_etatpossible`
--

CREATE TABLE IF NOT EXISTS `meteo_typeindic_etatpossible` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_typeIndicateur` int(11) NOT NULL,
  `id_etatPossible` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Contenu de la table `meteo_typeindic_etatpossible`
--

INSERT INTO `meteo_typeindic_etatpossible` (`id`, `id_typeIndicateur`, `id_etatPossible`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 1, 3),
(4, 2, 4),
(5, 2, 5),
(6, 2, 6),
(7, 2, 7),
(8, 2, 8),
(9, 2, 9);

ALTER TABLE services_liste ADD consigne_meteo TEXT default NULL;
