use pilotage;

-- Applicatifs
ALTER TABLE `applicatifs_liste` ADD COLUMN `actif` TINYINT NOT NULL DEFAULT 1  AFTER `Documentation` ;
ALTER TABLE `applicatifs_liste` DROP COLUMN `id_environnement` ;

-- FIQ
DROP TABLE IF EXISTS `incidents_qualite_statut`;
CREATE TABLE `incidents_qualite_statut` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `statut` varchar(127) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

INSERT INTO `incidents_qualite_statut` (`id`, `statut`) VALUES (1, 'En cours');
INSERT INTO `incidents_qualite_statut` (`id`, `statut`) VALUES (2, 'Prise en compte');
INSERT INTO `incidents_qualite_statut` (`id`, `statut`) VALUES (3, 'Clos');

DROP TABLE IF EXISTS `incidents_qualite`;
CREATE TABLE `incidents_qualite` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `date_evenement` datetime NOT NULL,
  `description` varchar(127) NOT NULL,
  `cause_raison` varchar(127) DEFAULT NULL,
  `incidence` varchar(127) DEFAULT NULL,
  `echeance` varchar(127) DEFAULT NULL,
  `statut` int(10) NOT NULL,
  `date_resolution` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `incidents_qualite_action`;
CREATE TABLE `incidents_qualite_action` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `id_incidents_qualite` int(10) NOT NULL,
  `date_action` datetime NOT NULL,
  `action` varchar(1023) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `f_id_fiq` (`id_incidents_qualite`),
  CONSTRAINT `f_id_fiq` FOREIGN KEY (`id_incidents_qualite`) REFERENCES `incidents_qualite` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Planning
DROP TABLE IF EXISTS `planning_cycle`;
CREATE TABLE  `planning_cycle` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nomCycle` varchar(127) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `planning_cycle_equipe`;
CREATE TABLE  `planning_cycle_equipe` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idNomCycle` int(10) unsigned NOT NULL,
  `idNomEquipe` int(10) unsigned NOT NULL,
  `dateDebut` date NOT NULL,
  `dateFin` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1 ROW_FORMAT=FIXED;

DROP TABLE IF EXISTS `planning_equipe_pilote`;
CREATE TABLE  `planning_equipe_pilote` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idNomEquipe` int(10) unsigned NOT NULL,
  `idUser` int(10) unsigned NOT NULL,
  `dateDebut` date NOT NULL,
  `dateFin` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `planning_modif_ponctuelle`;
CREATE TABLE  `planning_modif_ponctuelle` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idUser` int(10) unsigned DEFAULT NULL,
  `dateDebut` date NOT NULL,
  `dateFin` date NOT NULL,
  `idVacation` int(10) unsigned NOT NULL,
  `dateModif` date NOT NULL,
  `idUserModif` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `planning_nom_equipe`;
CREATE TABLE  `planning_nom_equipe` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nomEquipe` varchar(127) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `planning_semaine`;
CREATE TABLE  `planning_semaine` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idNomCycle` int(10) unsigned NOT NULL,
  `nomSemaine` varchar(127) NOT NULL,
  `numeroSemaine` int(10) unsigned NOT NULL,
  `lundi` int(10) unsigned NOT NULL,
  `mardi` int(10) unsigned NOT NULL,
  `mercredi` int(10) unsigned NOT NULL,
  `jeudi` int(10) unsigned NOT NULL,
  `vendredi` int(10) unsigned NOT NULL,
  `samedi` int(10) unsigned NOT NULL,
  `dimanche` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `planning_vacation`;
CREATE TABLE  `planning_vacation` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `heureDebut` time NOT NULL,
  `heureFin` time NOT NULL,
  `codeCouleur` varchar(127) NOT NULL,
  `libelle` varchar(127) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1 ROW_FORMAT=FIXED;

-- Domaines de login
DROP TABLE IF EXISTS `domaine_wind_login`;
CREATE TABLE  `pilotage`.`domaine_wind_login` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `domaine` int(10) unsigned NOT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;

-- Sauvegarde des filtres
DROP TABLE IF EXISTS `filtre`;
CREATE TABLE `filtre` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `titrePageId` varchar(127) NOT NULL,
  `filtreString` text NULL,
  PRIMARY KEY (`id`)
);