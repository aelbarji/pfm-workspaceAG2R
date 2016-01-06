
ALTER TABLE `machines_liste` ADD COLUMN `commentaire` TEXT;
ALTER TABLE `meteo_caisse` ADD COLUMN `nom_caisse_complet` VARCHAR(80);

--
-- Structure de la table `astreinte_appel`
--

CREATE TABLE IF NOT EXISTS `astreinte_appel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_astreinte` int(11) NOT NULL,
  `id_incident` int(11) NOT NULL,
  `datetime` datetime NOT NULL,
  `id_statut` int(11) NOT NULL,
  `commentaire` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=28 ;

--
-- Structure de la table `astreinte_appel_statut`
--

CREATE TABLE IF NOT EXISTS `astreinte_appel_statut` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `statut` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `astreinte_appel_statut`
--

INSERT INTO `astreinte_appel_statut` (`id`, `statut`) VALUES
(1, 'Répondeur'),
(2, 'L''astreinte a répondu'),
(3, 'L''astreinte a rappelé');

