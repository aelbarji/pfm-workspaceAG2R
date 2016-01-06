use epi;

--
-- Structure de la table `consignes_type`
--

CREATE TABLE IF NOT EXISTS `consignes_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(25) NOT NULL,
  `dossier` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `consignes_type`
--

INSERT INTO `consignes_type` (`id`, `type`, `dossier`) VALUES
(1, 'Permanente', '/Consignes_Permanente'),
(2, 'Quotidienne', '/Consignes_Quotidienne'),
(3, 'Inter-Equipe', '/Consignes_InterEquipe');

ALTER TABLE `consignes` ADD COLUMN `type` INT NOT NULL;
UPDATE `consignes` SET type=1 WHERE type=0;

--
-- Structure de la table `tdb_commentaire`
--

CREATE TABLE IF NOT EXISTS `tdb_commentaire` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `environnement` int(11) NOT NULL,
  `commentaire` text NOT NULL,
  `pilote` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Fermeture des incidents avant le 23/02/14
--

UPDATE incidents SET heure_fin='2014-02-26 07:30:00', heure_fin_gmt='2014-02-26 06:30:00', resoluPil=0 WHERE heure_fin is null AND heure_debut<'2014-02-26 07:30:00';
