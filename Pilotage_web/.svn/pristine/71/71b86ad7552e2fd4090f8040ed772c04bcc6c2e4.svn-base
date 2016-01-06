use epi;

--
-- Structure de la table `bilan_colonnes`
--

CREATE TABLE IF NOT EXISTS `bilan_colonnes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idIncidentColonne` int(11) NOT NULL,
  `idBilan` int(11) NOT NULL,
  `nomDansBilan` varchar(50) NOT NULL,
  `position` int(11) NOT NULL,
  `taille` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=45 ;

--
-- Contenu de la table `bilan_colonnes`
--

INSERT INTO `bilan_colonnes` (`id`, `idIncidentColonne`, `idBilan`, `nomDansBilan`, `position`, `taille`) VALUES
(37, 21, 8, 'Application OPC', 2, 4),
(36, 4, 8, 'Début Heure Paris', 1, 4),
(35, 8, 8, 'Applicatif', 0, 4),
(34, 19, 1, 'Astreintes', 10, 3),
(33, 7, 1, 'Fin d''incident autre fuseau', 9, 3),
(32, 6, 1, 'Fin d''incident', 8, 3),
(31, 12, 1, 'ARS', 8, 3),
(30, 10, 1, 'Actions effectuées', 7, 34),
(29, 9, 1, 'Observations', 6, 34),
(28, 5, 1, 'Début Heure autre fuseau', 5, 4),
(27, 4, 1, 'Début Heure Paris', 4, 4),
(26, 15, 1, 'Détection', 3, 4),
(25, 20, 1, 'Hardware', 2, 4),
(24, 8, 1, 'Applicatif', 1, 4),
(23, 13, 1, 'Serveur', 0, 4),
(38, 22, 8, 'Job', 3, 4),
(39, 9, 8, 'Observations', 4, 34),
(40, 10, 8, 'Actions effectuées', 5, 34),
(41, 23, 8, 'Reprise OK/KO', 6, 4),
(42, 12, 8, 'ARS', 7, 4),
(43, 6, 8, 'Fin d''incident', 8, 4),
(44, 19, 8, 'Astreintes', 9, 4);

ALTER TABLE `incidents` ADD `appli_ordonnanceur` VARCHAR(50) default "";
ALTER TABLE `incidents` ADD `job` VARCHAR(50) default "";

CREATE TABLE IF NOT EXISTS `incidents_colonnes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nomColonne` varchar(30) NOT NULL,
  `description` varchar(80) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=24 ;

--
-- Contenu de la table `incidents_colonnes`
--

INSERT INTO `incidents_colonnes` (`id`, `nomColonne`, `description`) VALUES
(1, 'id', 'Numéro identifiant de l''incident'),
(2, 'user', 'Pilote ayant crée l''incident'),
(3, 'oceor', 'Oceor'),
(4, 'heure_debut', 'Heure de début de l''incident selon le fuseau de Paris'),
(5, 'heure_debut_gmt', 'Heure de début de l''incident selon le fuseau de Greenwich (GMT)'),
(6, 'heure_fin', 'Heure de fin de l''incident selon le fuseau de Paris'),
(7, 'heure_fin_gmt', 'Heure de début de l''incident selon le fuseau de Greenwich (GMT)'),
(8, 'applicatif', 'Applicatif impacté'),
(9, 'observation', 'Observation sur l''incident'),
(10, 'action', 'Action(s) effectuée(s)'),
(11, 'domaine', 'Domaine impacté'),
(12, 'ars', 'Numéro d''ARS'),
(13, 'machine', 'Machine impacté'),
(14, 'Type', 'Type d''incident'),
(15, 'idOutil', 'Outil de détection'),
(16, 'coupure', 'Coupure de service'),
(17, 'service', 'Service impacté'),
(18, 'resoluPil', 'Résolu par le pilotage O/N'),
(19, 'aAstreinte', 'Si l''incident a eu des appels astreintes O/N'),
(20, 'hard_software', 'Hardware Impacté'),
(21, 'appli_ordonnanceur', 'Nom de l''application ordonnanceur'),
(22, 'job', 'job'),
(23, 'reprise', 'Résultat de la reprise');


