ALTER TABLE `epi`.`users` ADD COLUMN `timezone` INT(11) NOT NULL  AFTER `actif`;
ALTER TABLE `epi`.`users` ADD COLUMN `nrPerpage` INT(11) NOT NULL  AFTER `timezone` ;

UPDATE `epi`.`users` SET nrPerpage=20 WHERE nrPerpage=0;

ALTER TABLE `epi`.`meteo_groupemeteo` ADD COLUMN `timezone` VARCHAR(25) NOT NULL DEFAULT 'Europe/Paris';