use epi;

ALTER TABLE planning_vacation ADD COLUMN partiJour INT(11);
ALTER TABLE planning_vacation ADD COLUMN active TINYINT(4) DEFAULT 1;
ALTER TABLE planning_semaine ADD COLUMN dateDeb datetime NOT NULL DEFAULT '2013-01-01 00:00:00';
ALTER TABLE planning_semaine ADD COLUMN dateFin datetime DEFAULT NULL;
ALTER TABLE planning_modif_ponctuelle ADD COLUMN textCell VARCHAR(15);

CREATE TABLE IF NOT EXISTS `planning_cra_partie_jour` 
( 
`id` int(11) NOT NULL AUTO_INCREMENT,
`nom` varchar(30) NOT NULL,
PRIMARY KEY (`id`)
) 
ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;


INSERT INTO `planning_cra_partie_jour` 
(`id`, `nom`) VALUES
(1, 'Matin'),
(2, 'Apres-Midi'),
(3, 'Nuit'),
(4, 'Maladie'),
(5, 'Autres');

UPDATE planning_vacation set partiJour = 4 WHERE id = 1;
UPDATE planning_vacation set partiJour = 1 WHERE id = 2;
UPDATE planning_vacation set partiJour = 2 WHERE id = 3;
UPDATE planning_vacation set partiJour = 3 WHERE id = 4;
UPDATE planning_vacation set partiJour = 2 WHERE id = 5;
UPDATE planning_vacation set partiJour = 3 WHERE id = 6;
UPDATE planning_vacation set partiJour = 5 WHERE id = 7;
UPDATE planning_vacation set partiJour = 5 WHERE id = 8;
UPDATE planning_vacation set partiJour = 5 WHERE id = 9;
UPDATE planning_vacation set partiJour = 5 WHERE id = 10;
UPDATE planning_vacation set partiJour = 1 WHERE id = 11;
UPDATE planning_vacation set partiJour = 2 WHERE id = 12;
UPDATE planning_vacation set partiJour = 2 WHERE id = 13;
UPDATE planning_vacation set partiJour = 1 WHERE id = 14;
UPDATE planning_vacation set partiJour = 1 WHERE id = 15;
UPDATE planning_vacation set partiJour = 2 WHERE id = 16;





