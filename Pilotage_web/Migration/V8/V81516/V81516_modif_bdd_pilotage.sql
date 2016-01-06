
CREATE TABLE IF NOT EXISTS `meteo_indicateur_base` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `meteo` int(11) NOT NULL,
  `service` int(11) NOT NULL,
  `environnement` int(11) NOT NULL,
  `typeIndic` int(11) NOT NULL,
  `formatIndic` varchar(25) NOT NULL,
  `etat_desire` int(11) NOT NULL,
  `plage` int(11) NOT NULL,
  `heure_debut` varchar(5) NOT NULL,
  `heure_fin` varchar(5) NOT NULL,
  `indic_heure` tinyint(4) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `meteo_environnement_place` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_meteo` int(11) NOT NULL,
  `id_envir` int(11) NOT NULL,
  `place` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

INSERT INTO `meteo_environnement_place` (`id_meteo`, `id_envir`, `place`) VALUES
(55, 21, 0),
(55, 22, 1),
(56, 21, 0),
(56, 22, 1),
(57, 21, 0),
(57, 22, 1);

INSERT INTO `meteo_environnement_place` (`id_meteo`, `id_envir`, `place`) VALUES
(29, 10, 0),
(29, 11, 1),
(30, 10, 0),
(30, 11, 1),
(31, 10, 0),
(31, 11, 1),
(32, 10, 0),
(32, 11, 1);

INSERT INTO `meteo_environnement_place` (`id_meteo`, `id_envir`, `place`) VALUES
(33, 1, 0),
(33, 3, 1),
(33, 6, 2),
(34, 2, 0),
(34, 4,1),
(34, 7, 2),
(35, 1, 0),
(35, 3, 1),
(36, 9, 0);

INSERT INTO `meteo_environnement_place` (`id_meteo`, `id_envir`, `place`) VALUES
(61, 12, 0),
(62, 12, 0),
(38, 12, 0),
(37, 12, 0),
(42, 12, 0),
(58, 12, 0),
(63, 12, 0),
(60, 12, 0);

INSERT INTO `meteo_environnement_place` (`id_meteo`, `id_envir`, `place`) VALUES
(44, 1, 0),
(44, 3, 1);

INSERT INTO `meteo_environnement_place` (`id_meteo`, `id_envir`, `place`) VALUES
(45, 15, 0),
(46, 13, 0),
(47, 14, 0),
(48, 15, 0),
(49, 18, 0),
(50, 17, 0),
(51, 15, 0),
(52, 19, 0),
(53, 20, 0);

INSERT INTO `meteo_environnement_place` (`id_meteo`, `id_envir`, `place`) VALUES
(54, 10, 0),
(54, 11, 1);

INSERT INTO `meteo_environnement_place` (`id_meteo`, `id_envir`, `place`) VALUES
(55, 21, 0),
(55, 22, 0),
(55, 23, 0),
(55, 24, 0),
(56, 21, 0),
(56, 22, 0),
(56, 23, 0),
(56, 24, 0),
(57, 21, 0),
(57, 22, 0),
(57, 23, 0),
(57, 24, 1);

INSERT INTO `meteo_indicateur_format` (`id`, `format`) VALUES (4, 'varchar');
INSERT INTO `meteo_typeindicateur` (`type`, `format`) VALUES ('VARCHAR', 4);