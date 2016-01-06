use epi;
DROP TABLE IF EXISTS `astreinte_destinataires`;
CREATE TABLE `astreinte_destinataires` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `MAIL` text NOT NULL,
  `NOM` varchar(127) NOT NULL,
  `PRENOM` varchar(127) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;