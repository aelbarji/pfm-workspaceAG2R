USE epi;

CREATE TABLE IF NOT EXISTS `meteo_format` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `format` VARCHAR(25) NOT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

INSERT INTO `meteo_format` (`id`, `format`) VALUES
(1, 'Format général'),
(2, 'Format détaillé');

ALTER TABLE `meteo_groupemeteo` ADD COLUMN `format` int NOT NULL;
UPDATE meteo_groupemeteo SET format=REPLACE(format, 0, 1);
ALTER TABLE `meteo_groupemeteo` ADD CONSTRAINT `FK_meteoformat` FOREIGN KEY (`format`) REFERENCES meteo_format(`id`);

CREATE TABLE IF NOT EXISTS `meteo_groupemeteo_commentaire` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_groupe` int(11) NOT NULL,
  `commentaire` TEXT NOT NULL,
  `date` date NOT NULL,
  `horaire` time NOT NULL,
   PRIMARY KEY (`id`),
   FOREIGN KEY (id_groupe)
   REFERENCES meteo_groupemeteo(id)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;

ALTER TABLE `meteo_etatcourant` MODIFY `commentaire` TEXT;

