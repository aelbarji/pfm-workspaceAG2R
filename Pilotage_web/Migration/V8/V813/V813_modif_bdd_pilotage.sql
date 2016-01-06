use epi;

	
CREATE TABLE IF NOT EXISTS `debordement_noc_destinataire` (
  
	`id` int(11) NOT NULL AUTO_INCREMENT,
  
	`id_destinataire` int(11) NOT NULL,
  
	`cc` int(11) NOT NULL,
  PRIMARY KEY (`id`)
);
	

CREATE TABLE IF NOT EXISTS `destinataires` (
  
	`id` int(11) NOT NULL AUTO_INCREMENT,
  
	`nom` varchar(127) NOT NULL,
  
	`prenom` varchar(127) NOT NULL,
  
	`mail` text NOT NULL,
  PRIMARY KEY (`id`)
);



CREATE TABLE IF NOT EXISTS `debordement_noc` (
 
	`id` int(11) NOT NULL AUTO_INCREMENT,
  
	`datetime` datetime NOT NULL,
  
	`operateur` varchar(40) NOT NULL,
  
	`incident_noc` varchar(20) DEFAULT NULL,
  
	`incident_op` varchar(40) DEFAULT NULL,
  
	`info` longtext NOT NULL,
  PRIMARY KEY (`id`)
);



ALTER TABLE `meteo_etatpossible` ADD COLUMN `impact` int NOT NULL;
UPDATE meteo_etatpossible SET impact=1 WHERE libelle_etat LIKE "PERTU" OR libelle_etat LIKE "KO" OR libelle_etat LIKE "OK avec Incident batch" OR libelle_etat LIKE "OK J-1";
