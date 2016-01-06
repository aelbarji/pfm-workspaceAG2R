--<ScriptOptions statementTerminator=";"/>

ALTER TABLE `epi`.`bbr_consigne` DROP PRIMARY KEY;

DROP TABLE `epi`.`bbr_consigne`;

CREATE TABLE `epi`.`bbr_consigne` (
	`ID` INT NOT NULL,
	`BBRORIGINE` VARCHAR(50) NOT NULL,
	`BBRCOMPOSANT` VARCHAR(100) NOT NULL,
	`BBRTYPE` VARCHAR(60),
	`BBRPRIORITY` decimal(10 , 1) NOT NULL,
	`LOCALISATION` VARCHAR(400),
	PRIMARY KEY (`ID`)
) ENGINE=InnoDB;

