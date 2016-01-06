ALTER TABLE `meteo_etatcourant` MODIFY `etat_courant` VARCHAR(25);
ALTER TABLE `meteo_etatcourant` ADD COLUMN `heure_debut` time DEFAULT NULL;
ALTER TABLE `meteo_etatcourant` ADD COLUMN `heure_fin` time DEFAULT NULL;

UPDATE `meteo_seuil_plage` SET ko_min=REPLACE(ko_min, "3100", "9") WHERE id=10;
UPDATE `meteo_seuil_plage` SET ko_min=REPLACE(ko_min, "2100", "9") WHERE id=11;
UPDATE `meteo_seuil_plage` SET ko_min=REPLACE(ko_min, "3000", "9") WHERE id=12;

UPDATE `meteo_plagefonctionnement` SET etat_desire=REPLACE(etat_desire, "3100", "3400");
UPDATE `meteo_plagefonctionnement` SET etat_desire=REPLACE(etat_desire, "2100", "2300");
UPDATE `meteo_plagefonctionnement` SET etat_desire=REPLACE(etat_desire, "3000", "3300");

UPDATE `meteo_seuil_plage` SET pertu_min="5";

ALTER TABLE `meteo_indicateur` ADD COLUMN `heure_debut` tinyint(4) NOT NULL;
ALTER TABLE `meteo_indicateur` ADD COLUMN `heure_fin` tinyint(4) NOT NULL;

DROP TABLE IF EXISTS `meteo_indicateur_format`;
CREATE TABLE `meteo_indicateur_format` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `format` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

INSERT INTO `meteo_indicateur_format` (`id`, `format`) VALUES
(1, 'reel'),
(2, 'liste'),
(3, 'datetime');

INSERT INTO `meteo_typeindicateur` (`type`, `format`) VALUES
('DATE HEURE', 3);

ALTER TABLE `meteo_plagefonctionnement` MODIFY `etat_desire` VARCHAR(25) DEFAULT NULL;

UPDATE `meteo_typeindicateur` SET format=REPLACE(format, "liste", "2");
UPDATE `meteo_typeindicateur` SET format=REPLACE(format, "reel", "1");

ALTER TABLE `meteo_typeindicateur` MODIFY `format` int NOT NULL;
ALTER TABLE `meteo_typeindicateur` ADD CONSTRAINT `FK_indicformat` FOREIGN KEY (`format`) REFERENCES meteo_indicateur_format(`id`);