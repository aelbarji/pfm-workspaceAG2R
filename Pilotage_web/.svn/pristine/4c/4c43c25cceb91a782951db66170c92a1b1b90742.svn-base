
ALTER TABLE `alertes_disques` MODIFY `fs` VARCHAR( 100 ) NOT NULL;
ALTER TABLE `meteo_etatpossible` MODIFY `libelle_etat` VARCHAR( 50 ) NOT NULL;
ALTER TABLE `meteo_groupemeteo` MODIFY `nom_groupeMeteo` VARCHAR( 50 ) NOT NULL;

ALTER TABLE `meteo_groupemeteo_meteo` ADD COLUMN `date_creation` datetime NOT NULL;
ALTER TABLE `meteo_groupemeteo_meteo` ADD COLUMN `date_suppression` datetime;
ALTER TABLE `meteo_indicateur` ADD COLUMN `date_creation` datetime NOT NULL;
ALTER TABLE `meteo_indicateur` ADD COLUMN `date_suppression` datetime;
ALTER TABLE `meteo_service_meteo` ADD COLUMN `date_creation` datetime NOT NULL;
ALTER TABLE `meteo_service_meteo` ADD COLUMN `date_suppression` datetime;
ALTER TABLE `meteo_groupemeteo_horaire` ADD COLUMN `date_exception` date DEFAULT NULL;
ALTER TABLE `meteo_typeindic_etatpossible` ADD CONSTRAINT `FK_meteotypeindicetatpossible_idetatpossible` FOREIGN KEY (`id_etatPossible`) REFERENCES meteo_etatpossible(`id`);
ALTER TABLE `meteo_typeindic_etatpossible` ADD CONSTRAINT `FK_meteotypeindicetatpossible_idtypeindic` FOREIGN KEY (`id_typeIndicateur`) REFERENCES meteo_typeindicateur(`id`);

UPDATE meteo_groupemeteo_meteo SET date_creation=REPLACE(date_creation, '0000-00-00 00:00:00', '2012-09-01 09:00:00');
UPDATE meteo_indicateur SET date_creation=REPLACE(date_creation, '0000-00-00 00:00:00', '2012-09-01 09:00:00');
UPDATE meteo_service_meteo SET date_creation=REPLACE(date_creation, '0000-00-00 00:00:00', '2012-09-01 09:00:00');

CREATE TABLE IF NOT EXISTS `meteo_commentaire` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_meteo` int(11) NOT NULL,
  `commentaire` TEXT,
  `date` date NOT NULL,
  `horaire` TIME NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (id_meteo)
  REFERENCES meteo_meteo(id)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

