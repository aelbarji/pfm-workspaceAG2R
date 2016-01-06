use epi;

ALTER TABLE `meteo_plagefonctionnement` ADD COLUMN `date_creation` DATETIME NOT NULL;
ALTER TABLE `meteo_plagefonctionnement` ADD COLUMN `date_suppression` DATETIME DEFAULT NULL;

UPDATE `meteo_plagefonctionnement` SET date_creation='2012-09-09 00:00:00' WHERE date_creation='0000-00-00 00:00:00';