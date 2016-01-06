Exécuter le batch Migration_filtre.

use epi;


CREATE TABLE IF NOT EXISTS `com_bilan_destinataire` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  
`id_destinataire` int(11) NOT NULL,
  
`cc` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) 
ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;





CREATE TABLE IF NOT EXISTS `hardware_software` (
  
`id` int(11) NOT NULL AUTO_INCREMENT,
  
`libelle` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) 
ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=44 ;



INSERT INTO `hardware_software` (`id`, `libelle`) VALUES
(1, 'Système'),

(2, 'Robotique'),
(3, 'Cft'),
(4, 'Ops'),
(5, 'Vsm'),
(6, 'Newtest'),
(8, 'Db2'),

(9, 'Routeur'),
(10, 'Firewall'),(11, 'Switch'),
(12, 'Sms'),
(13, 'Sunspool'),

(14, 'Opc'),
(15, 'Vpn'),
(16, 'Acs'),
(17, 'Robot 8500'),
(18, 'Control m'),

(19, 'Oracle'),(20, 'Tina'),
(21, 'Mysql'),
(22, 'Bnt'),
(23, 'Dns'),

(24, 'Proxy'),
(25, 'Informix'),
(26, 'Mvs'),
(27, 'Bbr'),
(28, 'Smf'),

(29, 'Bfn'),
(30, 'Ip-label'),
(31, 'Exchange'),
(32, 'Sismo/cd'),

(33, 'Serveur argaus'),
(34, 'Jobs techniques'),
(35, 'Vmware'),

(36, 'Irisa'),
(37, 'Gmvs'),
(38, 'Scom'),
(39, 'Imprimante'),

(40, 'Citrix'),
(41, 'AMVS'),
(42, 'Surf-ACQ'),
(43, 'Logistique');



ALTER TABLE `incidents` ADD COLUMN `hard_software` VARCHAR(25) DEFAULT "";
ALTER TABLE `services_liste` ADD COLUMN `actif` INT(25) DEFAULT 1;

ALTER TABLE debordement_noc RENAME TO com_debordement_noc;
ALTER TABLE debordement_noc_destinataire RENAME TO com_debordement_noc_destinataire;

UPDATE bilan_envoie SET libelle = 'Document de liaison CFF' where id = 8;

CREATE TABLE IF NOT EXISTS `com_domaine` (
  
`id` int(11) NOT NULL AUTO_INCREMENT,
  
`nom_domaine` varchar(40) NOT NULL,
  
PRIMARY KEY (`id`)
) 
ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11;


INSERT INTO `com_domaine` (`id`, `nom_domaine`) VALUES

(1, 'MySys Caisses'),
(2, 'MySys Assurances et Divers'),

(3, 'MySys Transverse'),
(4, 'MySys Canaux Directs'),

(5, 'Filiale & Corporate (hors MySys)'),
(6, 'T2SOM OCEOR'),

(7, 'Banques Filiales'),
(8, 'Producteurs Externes Distributeur IT-CE'),

(9, 'Infrastructure IT-CE'),
(10, 'Outils Internes & Divers');






CREATE TABLE IF NOT EXISTS `com_incidents` (
  
`id` int(11) NOT NULL AUTO_INCREMENT,
  
`user` int(11) NOT NULL,
  `date_deb` datetime NOT NULL,
  
`date_fin` datetime DEFAULT NULL,
  
`description_prob` longtext NOT NULL,
   
`impactON` int(11) NOT NULL,
  
`etat_service` int(11) DEFAULT NULL,
  
`ars` varchar(11) DEFAULT NULL,
  
PRIMARY KEY (`id`)
) 
ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;






CREATE TABLE IF NOT EXISTS `com_incident_appli` (
  
`id` int(11) NOT NULL AUTO_INCREMENT,
  
`id_incident` int(11) NOT NULL,
  
`id_appli` int(11) NOT NULL,
  
PRIMARY KEY (`id`)
) 
ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;




CREATE TABLE IF NOT EXISTS `com_incident_detection` (
  
`id` int(11) NOT NULL AUTO_INCREMENT,
  
`id_incident` int(11) NOT NULL,
  
`id_detection` int(11) NOT NULL,
  
PRIMARY KEY (`id`)
) 
ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;




CREATE TABLE IF NOT EXISTS `com_incident_domaine` (
  
`id` int(11) NOT NULL AUTO_INCREMENT,
  
`id_incident` int(11) NOT NULL,
  
`id_domaine` int(11) NOT NULL,
  
PRIMARY KEY (`id`)
) 
ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;





CREATE TABLE IF NOT EXISTS `com_incident_interlocuteur` (
  
`id` int(11) NOT NULL AUTO_INCREMENT,
  
`id_incident` int(11) NOT NULL,
  
`id_interlocuteur` int(11) NOT NULL,
  
PRIMARY KEY (`id`)
) 
ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;





CREATE TABLE IF NOT EXISTS `com_incident_service` (
  
`id` int(11) NOT NULL AUTO_INCREMENT,
  
`id_incident` int(11) NOT NULL,
  
`id_service` int(11) NOT NULL,
  
PRIMARY KEY (`id`)
) 
ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;




CREATE TABLE IF NOT EXISTS `com_service` (
  
`id` int(11) NOT NULL AUTO_INCREMENT,
  
`nom_service` varchar(40) NOT NULL,
  
PRIMARY KEY (`id`)
) 
ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=40 ;



INSERT INTO `com_service` (`id`, `nom_service`) 
VALUES
(1, 'Sessions Bancaires'),
(2, 'Sessions Credit'),

(3, 'Applications Caisses'), (4, 'Applications techniques'),
(5, 'Assurances IARD'),

(6, 'MURACEF UEA Parabancaire'),
(7, 'MAMBO'),
(8, 'PFE -N et -R'),
(9, 'Banque à Distance'),

(10, 'Centre Relations Clientèles'),
(11, 'Monétique'),
(12, 'Application Xenos'),
(13, 'Archivage Consultation'),

(14, 'Services d''accès'),
(15, 'Portails'),
(16, 'OCEOR LEASE Tahiti Réunion Nouméa'),

(17, 'Applications Corporate'),
(18, 'Système'),
(19, 'Réseau'),
(20, 'IAS/PORTAIL'),
(21, 'Applications Métiers'),

(22, 'Traitements Services Bancaires'),
(23, 'EDI/PMF'),
(24, 'Valeurs Mobilière'),
(25, 'Assurance Vie'),

(26, 'Autres'),
(27, 'PFB'),
(28, 'SOCRAM'),
(29, 'CNP'),
(30, 'NATIXIS FINANCEMENT credit conso'),

(31, 'NATIXIS EUROTITRES'),
(32, 'NATIXIS GARANTIES CEGC'),
(33, 'BDF'),
(34, 'MACIF'),
(35, 'IAS'),
(36, 'SAVE'),

(37, 'Editique'),
(38, 'Poste de travail'),
(39, 'Outils IT-CE');





TRUNCATE TABLE `applicatifs_liste`;


INSERT INTO `applicatifs_liste` (`id`, `applicatif`, `Description`, `Documentation`, `actif`) VALUES

(0, 'SAI', '...', '', 0),

(1, 'Images cheques', NULL, NULL, 0),

(2, 'Crescendo', 'service CRESCENDO', '', 1),

(3, 'Monetique', NULL, NULL, 0),

(4, 'Tam', NULL, NULL, 1),

(5, 'IARD', NULL, NULL, 0),

(6, 'Sin', 'Images Chèques', '', 1),

(7, 'Accès Internet', NULL, NULL, 1),

(8, 'Systeme', NULL, NULL, 0),

(9, 'Robotique', NULL, NULL, 0),

(10, 'Mambo', NULL, NULL, 0),

(11, 'PFE', 'PlateForme Echanges', NULL, 0),

(12, 'SIT', NULL, NULL, 0),

(14, 'Cie1818', NULL, NULL, 0),

(15, 'Coda BPCE', NULL, NULL, 1),

(16, 'Fermat', NULL, NULL, 0),

(17, 'Vbank', NULL, NULL, 1),

(19, 'Offreurs', NULL, NULL, 0),

(20, 'Carte premier', NULL, NULL, 0),

(21, 'Composant Isim', NULL, NULL, 1),

(22, 'Centrale risque', NULL, NULL, 0),

(23, 'Cnp', NULL, NULL, 0),

(26, 'Cetelem', NULL, NULL, 1),
(27, 'Prems', NULL, NULL, 1),

(28, 'Ctb', NULL, NULL, 1),

(29, 'Sgc', NULL, NULL, 1),

(30, 'Sipg', NULL, NULL, 1),
(31, 'Cesam', NULL, NULL, 1),

(32, 'Pfb', NULL, NULL, 1),

(33, 'Muracef', NULL, NULL, 0),

(34, 'PFI', NULL, NULL, 1),

(35, 'Spm', NULL, NULL, 1),

(36, 'Sepa', NULL, NULL, 1),

(37, 'Titres', NULL, NULL, 1),

(38, 'Eca', NULL, NULL, 0),

(39, 'Oceor', NULL, NULL, 0),

(40, 'Smcnet', NULL, NULL, 0),

(41, 'Delegation Bale2', NULL, NULL, 1),

(42, 'Don Image monetique ', NULL, NULL, 1),

(43, 'Cartage', NULL, NULL, 1),

(44, 'Cft', NULL, NULL, 0),

(45, 'Pfg37', NULL, NULL, 0),

(46, 'Ews', NULL, NULL, 0),

(47, 'Enet', NULL, NULL, 0),

(48, 'Ops', NULL, NULL, 0),

(49, 'Vsm', NULL, NULL, 0),

(50, 'Newtest', NULL, NULL, 0),

(52, 'Db2', NULL, NULL, 0),

(53, 'Totem', NULL, NULL, 1),

(54, 'Dtmfidel', NULL, NULL, 0),

(55, 'Routeur', NULL, NULL, 0),

(56, 'Firewall', NULL, NULL, 0),

(57, 'Switch', NULL, NULL, 0),

(58, 'Cefi', NULL, NULL, 0),

(59, 'Messagerie', NULL, NULL, 1),

(60, 'Surf', NULL, NULL, 1),

(61, 'Trade', NULL, NULL, 1),

(62, 'Cassiope', NULL, NULL, 1),

(63, 'Sms', NULL, NULL, 0),

(64, 'Sauvegardes TINA', NULL, NULL, 1),

(65, 'Rca_watt', NULL, NULL, 0),

(66, 'Sun stk', NULL, NULL, 0),

(67, 'Oscar/patio', NULL, NULL, 0),

(68, 'Xguard', NULL, NULL, 1),

(69, 'Crc', NULL, NULL, 0),

(70, 'Psi', NULL, NULL, 0),

(72, 'Sunspool', NULL, NULL, 0),

(73, 'Opc', NULL, NULL, 0),

(74, 'Bepa', NULL, NULL, 0),

(75, 'Vpn', NULL, NULL, 0),

(76, 'Pfo', NULL, NULL, 1),

(77, 'Sima', NULL, NULL, 1),

(79, 'Autorisation isim', NULL, NULL, 0),

(80, 'Cff', NULL, NULL, 0),
(81, 'Ias', NULL, NULL, 1),

(82, 'Statcomv2', NULL, NULL, 0),

(83, 'OIC-DESB', NULL, NULL, 1),

(84, 'Parabancaire', NULL, NULL, 0),

(85, 'Acs', NULL, NULL, 0),

(86, 'Dtm_cbond', NULL, NULL, 0),

(87, 'Ingenium', NULL, NULL, 0),

(88, 'Swift-citi', NULL, NULL, 0),

(89, 'W9 PRODUEA', NULL, NULL, 1),

(90, 'Edd', NULL, NULL, 0),

(91, 'Robot 8500', NULL, NULL, 0),

(92, 'Smiles', NULL, NULL, 0),

(94, 'Dtm_qtd', NULL, NULL, 0),

(96, 'Svi', NULL, NULL, 1),

(97, 'Eic-IDEFICS', NULL, NULL, 1),
(98, 'Tango', NULL, NULL, 0),

(99, 'Fds', NULL, NULL, 0),

(101, 'Pffd', NULL, NULL, 0),

(103, 'Gdnat', NULL, NULL, 0),

(104, 'Bdf-tam', NULL, NULL, 0),

(105, 'Gicvd', NULL, NULL, 0),

(106, 'Scenarisk', NULL, NULL, 1),

(107, 'Flux Nafi', NULL, NULL, 1),
(108, 'Pecc', NULL, NULL, 1),

(109, 'Watt', NULL, NULL, 0),

(110, 'D2000', NULL, NULL, 0),

(111, 'Intranet group', NULL, NULL, 0),

(112, 'Donneur d ordre', NULL, NULL, 0),

(113, 'Abis', NULL, '', 1),

(114, 'Sirene', NULL, NULL, 0),

(115, 'Sab', NULL, NULL, 1),

(116, 'Socram', NULL, NULL, 0),

(117, 'Bopa', NULL, NULL, 1),

(118, 'Fiben', NULL, NULL, 1),

(119, 'Ieg', NULL, NULL, 0),

(120, 'Control m', NULL, NULL, 0),

(121, 'Stockage', NULL, NULL, 0),

(123, 'Man montrouge', NULL, NULL, 0),

(124, 'Publication', NULL, NULL, 0),

(125, 'Edel', NULL, NULL, 0),

(126, 'E-Rsb', NULL, NULL, 1),

(127, 'Oracle', NULL, NULL, 0),

(128, 'Ecureuil gestion', NULL, NULL, 0),

(129, 'Tina', NULL, NULL, 0),

(130, 'Anadefi', NULL, NULL, 1),

(131, 'Olympic', NULL, NULL, 0),

(132, 'Livret a', NULL, NULL, 0),

(133, 'Saccef-prod', NULL, NULL, 0),

(134, 'Datamart', NULL, NULL, 0),

(135, 'Base de données', NULL, NULL, 0),

(136, 'Mysql', NULL, NULL, 0),

(138, 'Dtm_saccef', NULL, NULL, 0),

(139, 'Swift', NULL, NULL, 1),

(140, 'Edd_mif', NULL, NULL, 0),

(141, 'CI-Risques', NULL, NULL, 1),

(142, 'Serenité (maif)', NULL, NULL, 0),

(143, 'Tas', NULL, NULL, 0),

(144, 'Banque a distance', NULL, NULL, 0),

(145, 'Bnt', NULL, NULL, 0),

(146, 'E_guard', NULL, NULL, 0),

(147, 'Ventilation', NULL, NULL, 0),

(148, 'Telephonie ip', NULL, NULL, 1),

(150, 'Dtm_plta', NULL, NULL, 0),

(151, 'Iege', NULL, NULL, 0),

(152, 'Direct ecureuil inte', NULL, NULL, 0),

(153, 'Reseau', NULL, NULL, 0),

(154, 'Electrique', NULL, NULL, 0),

(155, 'Pki', NULL, NULL, 1),

(156, 'Defi 3270', NULL, NULL, 1),

(158, 'Covered bonds', NULL, NULL, 0),

(159, 'D2rc', NULL, NULL, 1),

(160, 'Gmacb', NULL, NULL, 0),

(161, 'Eurotitre', NULL, NULL, 0),

(162, 'Preprod', NULL, NULL, 0),

(163, 'Recette', NULL, NULL, 0),

(164, 'Editique', NULL, NULL, 1),

(165, 'Aris', NULL, NULL, 1),

(166, 'Dns', NULL, NULL, 0),

(167, 'Sacha', NULL, NULL, 1),

(168, 'Cics', NULL, NULL, 1),

(170, 'Portails', NULL, NULL, 1),

(171, 'Bdf', NULL, NULL, 1),

(172, 'Fakir', NULL, NULL, 1),

(173, 'Cofinoga', NULL, NULL, 1),

(174, 'Eol', NULL, NULL, 1),

(175, 'Osp', NULL, NULL, 0),

(176, 'Stam', NULL, NULL, 0),

(177, 'Alis', NULL, NULL, 1),

(178, 'Meteo', NULL, NULL, 0),

(179, 'Terradata', NULL, NULL, 0),

(180, 'Proxy', NULL, NULL, 0),

(181, 'Dei Part', NULL, NULL, 1),

(182, 'Authorisation', NULL, NULL, 0),

(183, 'Tipe', NULL, NULL, 1),

(184, 'Oceor-lease', NULL, NULL, 0),

(185, 'Dtm', NULL, NULL, 0),

(186, 'Arcelor', NULL, NULL, 0),

(187, 'Oscp', NULL, NULL, 1),

(188, 'Intranet', NULL, NULL, 0),

(189, 'Divers-compta', NULL, NULL, 0),

(190, 'Dtm_mobiliz', NULL, NULL, 0),

(191, 'Pmm-pmv', NULL, NULL, 1),

(192, 'Flux proxy', NULL, NULL, 1),

(193, 'Garantie santé NOSA', NULL, NULL, 1),

(195, 'Core', NULL, NULL, 1),

(196, 'Sdn', NULL, NULL, 0),

(197, 'Informix', NULL, NULL, 0),

(198, 'Boreal', NULL, NULL, 1),

(199, 'Ocrc', NULL, NULL, 0),

(200, 'Mvs', NULL, NULL, 0),

(201, 'Bccm', NULL, NULL, 1),

(203, 'Dtm_lgd', NULL, NULL, 0),

(204, 'Maintenance', NULL, NULL, 0),

(205, 'Smtp', NULL, NULL, 0),

(207, 'Cat-fakir', 'serveur CEMDLA  ou iastrf', '', 0),

(208, 'Boacv34', NULL, NULL, 0),

(209, 'Calypso', NULL, NULL, 1),

(210, 'Natexis', NULL, NULL, 0),

(211, 'Tradenet', NULL, NULL, 1),

(212, 'Alim', NULL, NULL, 0),

(214, 'Snb2', NULL, NULL, 0),

(215, 'Ficp', NULL, NULL, 1),

(216, 'Aristion', NULL, NULL, 0),

(217, 'Vinci_citi', NULL, NULL, 0),

(219, 'Ptg-Oceor', NULL, NULL, 1),

(220, 'Webias3(IHM)', NULL, NULL, 1),

(221, 'Datamart', NULL, NULL, 1),

(222, 'Wpc', NULL, NULL, 1),

(223, 'Stf', NULL, NULL, 0),

(224, 'Pim', NULL, NULL, 0),
(225, 'Ctrl. de domaine', NULL, NULL, 0),


(226, 'Bbr', NULL, NULL, 0),

(227, 'Sp+', NULL, NULL, 0),

(228, 'Virtual server', NULL, NULL, 0),

(229, 'Carte à carte', NULL, NULL, 0),

(230, 'Aquarel', NULL, NULL, 1),

(232, 'Marge', NULL, NULL, 0),

(233, 'Pfd', NULL, NULL, 0),

(234, 'Smf', NULL, NULL, 0),

(235, 'Bfn', NULL, NULL, 0),

(236, 'Ip-label', NULL, NULL, 0),

(237, 'Ars V7', NULL, NULL, 1),

(238, 'Maif', NULL, NULL, 0),

(239, 'Ecolocale', NULL, NULL, 1),

(240, 'Bls', NULL, NULL, 1),

(241, 'Exchange', NULL, NULL, 0),

(243, 'Prose', NULL, NULL, 1),

(244, 'Autres', NULL, NULL, 0),

(246, 'Bo', NULL, NULL, 1),

(247, 'Asv', NULL, NULL, 0),

(248, 'Pleiades', NULL, NULL, 1),

(249, 'Sismo/cd', NULL, NULL, 0),

(250, 'Fcc', NULL, NULL, 1),

(251, 'Psdn', NULL, NULL, 0),

(252, 'Ceca', NULL, NULL, 0),

(253, 'Ecuvie', NULL, NULL, 1),

(254, 'Serveur argaus', NULL, NULL, 0),

(255, 'Psmla1', NULL, NULL, 1),

(256, 'Ass', NULL, NULL, 0),
(258, 'Jobs techniques', NULL, NULL, 0),

(259, 'Serveurs de conversion', NULL, NULL, 1),

(261, 'Upcc', NULL, NULL, 0),

(262, 'Vmware', NULL, NULL, 0),

(264, 'Argaus', NULL, NULL, 1),

(265, 'WSA', 'Gestion des droits au niveau nationnal', NULL, 1),

(266, 'Coclico', NULL, NULL, 1),

(267, 'Kana', NULL, NULL, 1),

(268, 'Espace disque.', NULL, NULL, 1),

(269, 'Izicefi', NULL, NULL, 1),

(270, 'Izicarte', NULL, NULL, 0),

(271, 'Irisa', NULL, NULL, 0),

(272, 'Spa', NULL, NULL, 1),

(273, 'Gmvs', NULL, NULL, 0),
(275, 'Eteback et pesit', NULL, NULL, 0),

(276, 'Edi  upcc', NULL, NULL, 0),

(278, 'Scom', NULL, NULL, 0),

(279, 'Session applicative', NULL, NULL, 0),

(280, 'Opcvm', NULL, NULL, 0),

(281, 'BO-XI', NULL, NULL, 1),

(282, 'Ecm', NULL, NULL, 1),

(283, 'Imprimante', NULL, NULL, 0),

(288, 'Citrix', 'Citrix Supragrp', '', 0),

(287, 'Geide', '\n', '\n', 0),

(289, 'SIRH', '', '', 0),

(295, 'Megaflash', '\n', '\n', 1),

(294, 'Vignette', '', '', 0),

(307, 'Cours des devises', '', '', 0),

(306, 'Produmetrie', '', '', 0),

(303, 'Prisme', '', '', 1),

(305, 'supragrp', '', '', 0),

(312, 'Platine', '', '', 1),

(310, 'AMVS', '', '', 0),

(311, 'WebServices GCE CLIE', 'Interface entre intranet client et MYSYS', '/Consignes/A-GCE CLIENT/Fonctionnement_Web Services GCE Client et EDS_National_0.3.doc;/Consignes/GCE CLIENT/architecture GCE Client.vsd', 0),

(313, 'Defi 3270', 'Application Titres chez NATIXIS', '', 1),

(314, 'Xenos', '', '', 1),

(316, 'Dms', 'traitement mise à jour base de données', '', 1),

(319, 'Palatine', 'Workflow', '', 1),

(320, 'Surf-ACQ', '', '', 0),

(322, 'SCVD', ' ...', '', 0),

(323, 'CLEVA', 'Assurances ', '', 1),

(324, 'Cedricom', '...', '', 1),

(325, 'CASH MANAGEMENT', 'OSE IP1/IP2 marché diversifié', '', 1),

(327, 'Enet', 'Cautionnement des crédits immobilliers (Natixis Garantie)', '', 1),

(328, 'Credit Conso', 'Cautionnement Crédit', '', 0),

(329, 'OIC', 'Crédit', '', 1),

(331, 'IDEFICS', 'IDEFICS ', '', 1),

(332, 'Intranet Titres (PIL', 'Pilotage des contingents', '', 1),

(333, 'Neo', 'neo fait parti des services crédit', '', 1),

(334, 'Inconnu', 'Inconnu', NULL, 1),

(335, 'Scancheque', 'Numérisation des chèques', '', 1),

(336, 'Outilspdt', 'Outil poste de travail', '', 1),

(337, 'SAVEPDT', 'Sauvegarde environnement Poste de travail', '', 1),

(338, 'PMM', '...', '', 1),

(340, 'IMGCHQ', 'image Cheque', '', 1),

(341, 'EDI', '...', '', 1),

(342, 'Logistique', 'Climatisation, Electricité', '', 0),


(343, 'Ligis', 'ligis', '', 1),

(344, 'Bureautique Oceor', NULL, NULL, 0),

(345, 'Bureautique CE', NULL, NULL, 1),


(346, 'Novabank', NULL, NULL, 1),
(348, 'MEVO', 'Messagerie vocale sur ToIP', '', 1),
(349, 'Taxation', 'Serveur de Taxation', '', 1),

(350, 'Serveur de ', 'Serveur de License', '', 1),
(351, 'DOCAPOST', 'Gestion des envoies des documents via la poste', '', 1),
(352, 'Nim', 'NIm', '\r\n', 0),

(353, 'Hnas', 'Hnas', '\r\n', 0),

(354, 'OMNI', 'PETRUS PRODUCTION', '\r\n', 0),
(355, 'Saga', 'Assurance vie', '', 1),

(356, 'P_SCVD_PROD', ' P_SCVD_PROD', '', 1),

(357, 'Ged', 'Ged', '', 0),

(358, 'SGM', 'Le projet SGM s’inscrit directement dans la stratégie « Performance du Groupe ». Il répond au besoin unique d’un outil GCE pour la gestion des moyens généraux', '', 1),

(359, 'Boac', 'Boac', '', 1),

(360, 'Smac', 'Smac', '', 1),

(361, 'CHECK_LIST', 'Outil de pilotage', '', 1),

(362, 'IZIVENTE', 'Natixis', '', 1),

(363, 'PowerAMC', 'PowerAMC', '', 1),

(364, 'DEM', 'Direct Ecureuil Mobile.', '', 1),

(365, 'MSI', 'BAD', '', 1),

(366, 'Andromede', 'Application Citrix Oceorlease', '', 1),

(367, 'Iam', 'UPCC', '', 1),
(368, 'Evolan', 'upcc', '', 1),

(369, 'VTOM', 'Ordonnanceur', '', 1),

(370, 'MVS PROD IP1', 'Partition PROD Seclin IP1', '', 0),

(371, 'MVS CREDIT IP1', 'Partition Crédit Seclin IP1', '', 0),

(372, 'Sysplex', 'logiciel IBM liant plusieurs partions MVS entre elles', '', 1),
(373, 'Hardware', 'traitement des incidents hardware', '', 0),

(374, 'ICG BP', 'Infrastructure de Confiance Groupe\r\nBanque Populaire.', 'undefined', 1),

(375, 'Toip', 'Toip', '', 1),

(376, 'Iselection', 'Immobilier', '', 1),

(377, 'nosa', 'nosa', 'undefined', 1),

(378, 'SQL', 'SQL', '', 1),

(379, 'Archivage Groupe', 'Archivage Groupe', '', 1),

(380, 'Adam', '.', '', 1),

(381, 'ICG CE', 'Dictao', '', 1),

(382, 'KMVS', 'Partition IP 0', '', 1),



(383, 'CEIDF', 'CEIDF', '', 1),

(384, 'ODS', 'ODS CFF', '', 1),

(385, 'Tradix', 'Marchés Financiers CFF', '', 1),

(386, 'Assurances', 'Assurances CFF', '', 1),

(387, 'OGREC', 'OGREC CFF', '', 1),

(388, 'CREDEC', 'CREDEC CFF', '', 1),

(389, 'RDJ', 'RDJ CFF', '', 1),

(390, 'CLI', 'CLI CFF', '', 1),

(391, 'DEC PIL', 'DEC PIL CFF', '', 1),

(392, 'STRA', 'STRA CFF', '', 1),

(393, 'L66 - APPORTEURS', 'L66 - APPORTEURS CFF', '', 1),

(394, '48-NEMO', 'NEMO', '', 1),

(395, 'Not. Corpor. NIE', '34 - Notation Corporate NIE (CFF)', '', 1),

(396, 'IARD Vente', 'CFF IARD Vente', '', 1),

(397, 'BDCF', '40-BDCF CFF', '', 1),


(399, 'Localisation credit', '43 - Localisation dossier crédit CFF', '', 0),

(400, 'APL', 'APL CFF', '', 0),

(401, 'Evolan ENB', '01 - Evolan ENB CFF', '', 1),

(402, 'Evolan Loans', '01 - Evolan Loans CFF', '', 1),

(403, 'Site Internet', '01 - Site Internet "je fais des travaux" CFF', '', 1),

(404, 'FGAS', '02 - FGAS CFF', '', 1),

(405, 'ESOPE', '03 - ESOPE CFF', '', 1),

(406, 'ATHENA', '04 - ATHENA CFF', '', 1),

(407, 'Comptes courants', '07 - Comptes courants CFF', '', 1),

(408, 'Refinancement', '09 - Refinancement CFF', '', 1),

(409, 'Déclarat. fiscales', '10 - Déclarations fiscales CFF', '', 1),

(410, 'OPTIM''IS', '10 - OPTIM''IS CFF', '', 1),

(411, 'Référentiels BPCE', '11 - Référentiels BPCE CFF', '', 1),

(412, 'Référent. Comptable', '11 - Référentiels Comptable CFF', '', 1),

(413, 'Référent. DOF', '11 - Référentiels DOF CFF', '', 1),

(414, 'Référent. Externes', '11 - Référentiels Externes CFF', '', 1),

(415, 'Référent. Financiers', '11 - Référentiels Financiers CFF', '', 1),

(416, 'Référent. Internes', '11 - Référentiels Internes CFF', '', 1),

(417, 'REUNI', '11 - REUNI CFF', '', 1),

(418, 'ACR', '12 - ACR CFF', '', 1),

(419, 'CFI', '13 - CFI CFF', '', 1),

(420, 'Expertise', '13 - Expertise CFF', '', 1),

(421, 'IMHOTEP', '13 - IMHOTEP CFF', '', 1),
(422, 'ST-Gest. Créd. Bel.', '14 - ST - Gestion Crédits Belgique CFF', '', 1),

(423, 'Trait. Cli. Corp.', '15 - Traitements Clients Corporate CFF', '', 1),

(424, 'Base des Expo.', '16 - Base des Expositions CFF', '', 1),

(425, 'Datamart BFI', '17 - Datamart BFI CFF', '', 1),

(426, 'Datamart GCR', '17 - Datamart GCR CFF', '', 1),

(427, 'Datam. gest. par.', '17 - Datamart gestion particulier CFF', '', 1),

(428, 'Datam. pil. CDG', '17 - Datamart Pilotage CDG CFF', '', 1),

(429, 'Datam.pil.prod. PART', '17 - Datamart pilotage production PART CFF', '', 1),

(430, 'Datamart pil. SCF', '17 - Datamart pilotage SCF CFF', '', 1),

(431, 'Datamart PVH', '17 - Datamart PVH CFF', '', 1),

(432, 'Datamart RH', '17 - Datamart RH CFF', '', 1),

(433, 'Datamart risques', '17 - Datamart risques CFF', '', 1),

(434, 'Datam.suiv.frai,gén.', '17 - Datamart suivi frais généraux CFF', '', 1),

(435, 'Espace Historisation', '17 - Espace Historisation CFF', '', 1),

(436, 'FINREP', '17 - FINREP CFF', '', 1),

(437, 'Livraisons BPCE', '17 - livraisons BPCE CFF', '', 1),

(438, 'Panorama - DTM IARD', '17 - Panorama DTM IARD CFF', '', 1),

(439, 'Prisme CFF', '17 - Prisme CFF', '', 1),

(440, 'ODS CFF', '17 - ODS CFF', '', 1),

(441, 'PIQUADO', '18 - PIQUADO CFF', '', 1),

(442, 'PIQUADO données', '18 - PIQUADO données CFF', '', 1),

(443, 'PIQUADO indicateurs', '18 - PIQUADO indicateurs CFF', '', 1),

(444, 'PIQUADO Workflow', '18 - PIQUADO Workflow CFF', '', 1),

(445, 'Hypervision', '19 - Hypervision CFF', '', 1),

(446, 'Support RH', '19 - Suppport RH CFF', '', 1),

(447, 'Impayés', '20 - Impayés CFF', '', 1),

(448, 'Imputation', '20 - Imputation CFF', '', 1),

(449, 'BJC', '22 - BJC', '', 1),

(450, 'Compl. comptable', '22 - Complément comptables CFF', '', 1),

(451, 'Justine', '22 - Justine CFF', '', 1),




(452, 'Trésor', '26 - Trésor CFF', '', 1),

(453, 'CEGID', '28 - CEGID CFF', '', 1),

(454, 'Interf. Bankware', '28 - Interface Bankware CFF', '', 1),

(455, 'Interf. Financière', '28 - Interface Financière CFF', '', 1),

(456, 'Ulysse', '28 - Ulysse CFF', '', 1),

(457, 'SIT CFF', '29 - SIT CFF', '', 1),

(458, 'Assurances CFF', '30 - Assurances CFF', '', 1),

(459, 'Versements clients', '31 - Versements clients CFF', '', 1),

(460, 'Pertes et provisions', '33 - Pertes et provisions CFF', '', 1),

(461, 'AEF', '34 - AEF CFF', '', 1),

(462, 'ANADEFI CFF', '34 - ANADEFI CFF', '', 1),

(463, 'Base centr. notes', '34 - Base centralisée des notes CFF', '', 1),

(464, 'Base contrat douteux', '34 - Base des contrats douteux CFF', '', 1),

(465, 'Bas. donn. orig. BDO', '34 - Base des données d''origine (BDO)', '', 1),

(466, 'Structure d''accueil', '49 - Structure d''accueil CFF', '', 1),

(467, 'Base incidents', '34 - Base incidents CFF', '', 1),

(468, 'DRE(dir.risq.engag.)', '34 - DRE (Direction risques engagements) CFF', '', 1),

(469, 'E-local', '34 - E-local CFF', '', 1),
(470, 'FE18', '34 - FE18 CFF', '', 1),

(471, 'GNB2', '34 - GNB2 CFF', '', 1),

(472, 'Notation Banq. Souv.', '34 - Notations Banques Souverains CFF', '', 1),

(473, 'Notation CNCE', '34 - Notation CNCE CFF', '', 1),

(474, 'Notation Corp NIE', '34 - Notation Corporate NIE CFF', '', 1),

(475, 'Notation Mens. NIH', '34 - Notation Mensuelle NIH CFF', '', 1),

(476, 'Notation Part. NIF', '34 - Notation Particuliers NIF CFF', '', 1),

(477, 'Notation PIM', '34 - Notation PIM CFF', '', 1),

(478, 'Score Travaux', '34 - Score Travaux CFF', '', 1),

(479, 'SNB2 CFF', '34 - SNB2 CFF', '', 1),

(480, 'Statistiq. de prod.', '34 - Statistiques de production CFF', '', 1),

(481, 'Tiers FM', '34 - Tiers FM CFF', '', 1),

(482, 'ACCT', '35 - ACCT CFF', '', 1),

(483, 'Compl. recouvrement', '36 - Complément recouvrement CFF', '', 1),

(484, 'ASC', '37 - ASC CFF', '', 1),

(485, 'Prime Eparg.Log(PEL)', '39 - Prime Epargne Logement (PEL) CFF', '', 1),

(486, 'BDCF CFF', '40 - BDCF CFF', '', 1),

(487, 'VBANK CFF', '41 - VBANK CFF', '', 1),

(488, 'APL CFF', '42 - APL CFF', '', 1),

(489, 'ADN', '43 - ADN CFF', '', 1),

(490, 'Location doss. créd.', '43 - Localisation dossiers crédits - Archives CFF', '', 1),

(491, 'Base Bilan', '47 - Base Bilan CFF', '', 1),

(492, 'Client-Tiers', '47 - Client-Tiers CFF', '', 1),

(493, 'CRM', '47 - CRM CFF', '', 1),

(494, 'CRM POFI', '47 - CRM POFI', '', 1),

(495, 'Garanties', '47 - Garanties CFF', '', 1),

(496, 'SADS', '47 - SADS CFF', '', 1),

(497, 'Vision Client', '47 - Vision Client CFF', '', 1),

(498, 'Créance irrécouvrab.', '48 - Créances irrécouvrables CFF', '', 1),

(499, 'Editique diffusion', '48 - Editique diffusion CFF', '', 1),

(500, 'Editique PDT', '48 - Editique PDT CFF', '', 1),

(501, 'FICP CFF', '48 - FICP CFF', '', 1),

(502, 'FILEMO', '48 - FILEMO CFF', '', 1),

(503, 'NEMO', '48 - NEMO', '', 1),

(504, 'Support crédit', '48 - Support crédit CFF', '', 1),

(505, 'SVI-CTI', '48 - SVI-CTI', '', 1),

(506, 'Tarificat.Acte Gest.', '48 - Tarification actes de gestion CFF', '', 1),

(507, 'AIS', '49 - AIS CFF', '', 1),

(508, 'Simulateur Copro', '49 - Simulateur Copro CFF', '', 1),

(509, 'ARSI', '53 - ARSI CFF', '', 1),

(510, 'Prêts Fonds For.Nat.', '55 - Prêts Fonds Forestier National (FFN) CFF', '', 1),

(511, 'Alibaba', '60 - Alibaba CFF', '', 1),

(512, 'Cassiopée', '60 - Cassiopée CFF', '', 1),

(513, 'Logos Promo', '60 - Logos Promo CFF', '', 1),

(514, 'ALIS CFF', '61 - ALIS CFF', '', 1),

(515, 'ALM Bancware', '62 - ALM Bancware CFF', '', 1),

(516, 'Bâle 3', '62 - Bâle 3 CFF', '', 1),

(517, 'Pégase', '62 - Pégase CFF', '', 1),

(518, 'Artémis', '63 - Artémis CFF', '', 1),

(519, 'Apporteurs-convent.', '66 - Apporteurs-conventions CFF', '', 1),

(520, 'GCR', '66 - GCR CFF', '', 1),

(521, 'Interopérabilité par', '66 - Interopérabilité partenaires CFF', '', 1),

(522, 'OCTAV', '66 - OCTAV CFF', '', 1),

(523, 'Référent. Partenair.', '66 - Référentiel Partenaires CFF', '', 1),

(524, 'Reporting Gds Part.', '66 - Reporting Grands Partenaires CFF', '', 1),

(525, 'Reporting part. fin.', '66 - Reporting partenaire financier CFF', '', 1),

(526, 'RDJ CFF', '68 - RDJ CFF', '', 1),

(527, 'CAPI', '72 - CAPI CFF', '', 1),

(528, 'Marketic One', '72 - Marketic One CFF', '', 1),

(529, 'INTRANET CFF', '74 - INTRANET CFF', '', 1),

(530, 'OSD', '74 - OSD CFF', '', 1),

(531, 'DEFIMMO', '75 - DEFIMMO CFF', '', 1),

(532, 'Espace Cli. Internet', '76 - Espace Client Internet CFF', '', 1),

(533, 'Extranet Belgique', '76 - Extranet Belgique CFF', '', 1),

(534, 'Extranet CFCAL', '76 - Extranet CFCAL CFF', '', 1),

(535, 'Foncier Box', '76 - Foncier Box CFF', '', 1),

(536, 'Foncier Home', '76 - Foncier Home CFF', '', 1),
(537, 'Foncier Partenaire', '76 - Foncier Partenaire CFF', '', 1),

(538, 'Interro Base notair.', '76 - Interrogation Base notaires (pour Foncier Partenaire) CFF', '', 1),

(539, 'JDE', '76 - JDE CFF', '', 1),

(540, 'Quatrinvest', '76 - Quatrinvest CFF', '', 1),

(541, 'SCOOP', '76 - SCOOP CFF', '', 1),

(542, 'Site Foncier Home', '76 - Site Foncier Home CFF', '', 1),

(543, 'Mégastore', '76 - Mégastore CFF', '', 1),

(544, 'Site grand public', '76 - Site grand public CFF', '', 1),

(545, 'Site LTI', '76 - Site LTI CFF', '', 1),

(546, 'BEST CFF', '77 - BEST CFF', '', 1),

(547, 'Néo CFF', '77 - Néo CFF', '', 1),

(548, 'RHO', '77 - RHO CFF', '', 1),

(549, 'Synchro', '77 - Synchro CFF', '', 1),

(550, 'CALCULIMMO', '79 - CALCULIMMO CFF', '', 1),

(551, 'Reversimmo', '79 - Reversimmo CFF', '', 1),

(552, 'SEVAN', '79 - SEVAN CFF', '', 1),

(553, 'CA RCM', '84 - CA RCM CFF', '', 1),

(554, 'Requêtes Programmées', '89 - Requêtes Programmées CFF', '', 1),

(555, 'EVOLAN SURFI', '90 - EVOLAN SURFI CFF', '', 1),

(556, 'Editique Batch', '92 - Editique Batch CFF', '', 1),

(557, 'Service Editiq. BDOC', '92 - Service Editique BDOC CFF', '', 1),

(558, 'SAP', '94 - SAP CFF', '', 1),
(559, 'QUALITE DRI', '18-QUALITE DRI CFF', '', 1),
(560, 'Iprox', 'Iprox', '', 1),


(561, 'Prélèvements extérieurs', NULL, NULL, 1),

(562, 'Prime PEP', NULL, NULL, 1),

(563, 'Prime PEL', NULL, NULL, 1),

(564, 'D.A.T.', NULL, NULL, 1),

(565, 'Trésorerie', NULL, NULL, 1),

(566, 'Bons', '', NULL, 1),

(567, 'R.P.M.', NULL, NULL, 1),

(568, 'Gestion commerciale', NULL, NULL, 1),

(569, 'MAD', NULL, NULL, 1),

(570, 'Clients', NULL, NULL, 1),

(571, 'SDPI', NULL, NULL, 1),

(572, 'T.I.M', NULL, NULL, 1),

(573, 'Escompte', NULL, NULL, 1),

(574, 'Historisation soldes CCE', NULL, NULL, 1),

(575, 'VAO', NULL, NULL, 1),

(576, 'Forcage Contentieux', NULL, NULL, 1),

(577, 'Provision Clientèle', NULL, NULL, 1),

(578, 'CI-DAILLY', NULL, NULL, 1),

(579, 'Date Comptable', NULL, NULL, 1),

(580, 'GL-Expert', NULL, NULL, 1),

(581, 'Atome', NULL, NULL, 1),

(582, 'Parts Sociales', NULL, NULL, 1),

(583, 'RDJ Credec Vers GL', NULL, NULL, 1),

(584, 'Vario', NULL, NULL, 1),

(585, 'TP AIS-PIM', NULL, NULL, 1),

(586, 'Agenda', NULL, NULL, 1),

(587, 'Chene', NULL, NULL, 1),

(588, 'GEIDE Opera', NULL, NULL, 1),

(589, 'GEIDE V2', NULL, NULL, 1),

(590, 'GEIDE ECM', NULL, NULL, 1),

(591, 'GEIDE FACT PRO', NULL, NULL, 1),

(592, 'ECM Arcade', NULL, NULL, 1),

(593, 'GEIDE ADM', NULL, NULL, 1),

(594, 'Heremus', NULL, NULL, 1),

(595, 'SID', NULL, NULL, 1),

(596, 'SLR', NULL, NULL, 1),

(597, 'Tableaux de bord', NULL, NULL, 1),

(598, 'Reporting TOIP', NULL, NULL, 1),

(599, 'Immo-Iselection', NULL, NULL, 1),

(600, 'OLAP', NULL, NULL, 1),

(601, 'ESBO', NULL, NULL, 1),

(602, 'Techusine', NULL, NULL, 1),

(603, 'PARC', NULL, NULL, 1),

(604, 'DEA', NULL, NULL, 1),

(605, 'W9 SINGENUEA', NULL, NULL, 1),

(606, 'W9 SINGEN', NULL, NULL, 1),

(607, 'W9 PROD', NULL, NULL, 1),

(608, 'Vacations Aller', NULL, NULL, 1),

(609, 'Vacations Retour', NULL, NULL, 1),

(610, 'CFN', NULL, NULL, 1),

(611, 'MBL', NULL, NULL, 1),

(612, 'SVI BAD', NULL, NULL, 1),

(613, 'Services Centraux', NULL, NULL, 1),

(614, 'Services Genesys', NULL, NULL, 1),

(615, 'SVI CRC', NULL, NULL, 1),

(616, 'IHM Fraude', NULL, NULL, 1),

(617, 'Nb de GAB ok', NULL, NULL, 1),

(618, 'SAF', NULL, NULL, 1),

(619, 'SAF - Fraude', NULL, NULL, 1),

(620, 'TP', NULL, NULL, 1),

(621, 'DON', NULL, NULL, 1),

(622, 'SCN', NULL, NULL, 1),

(623, 'CultureNet BPCE', NULL, NULL, 1),

(624, 'CultureNet BP', NULL, NULL, 1),

(625, 'CultureNet Filiales', NULL, NULL, 1),

(626, 'Intranet IDF', NULL, NULL, 1),

(627, 'Arcole', NULL, NULL, 1),

(628, 'LEASOR2', NULL, NULL, 1),

(629, 'CFN', NULL, NULL, 1),

(630, 'Saves hebdo', NULL, NULL, 1),

(631, 'Couche Echanges', NULL, NULL, 1),

(632, 'SAS OCEOR', NULL, NULL, 1),

(633, 'Portail Intranet IOM', NULL, NULL, 1),

(634, 'Zadig', NULL, NULL, 1),

(635, 'SAGE', NULL, NULL, 1),

(636, 'ISIM GAB', NULL, NULL, 1),

(637, 'Flux Interbancaires', NULL, NULL, 1),

(638, 'Relevés Comptes', NULL, NULL, 1),

(639, 'EDI-Web', NULL, NULL, 1),

(640, 'PUMA', NULL, NULL, 1),

(641, 'ICP', NULL, NULL, 1),

(642, 'CR-EDI Valerian', NULL, NULL, 1),

(643, 'X3', NULL, NULL, 1),

(644, 'TP vers SAB(5250)', NULL, NULL, 1),

(645, 'Metiers SOCRAM', NULL, NULL, 1),

(646, 'Extranet', NULL, NULL, 1),

(647, 'Assurance Vie Net/CIA', NULL, NULL, 1),

(648, 'Assurances Emprunts/CNP-Net', NULL, NULL, 1),

(649, 'Iziweb - SAV', NULL, NULL, 1),

(650, 'Iziweb VAD', NULL, NULL, 1),

(651, 'Batchs Titres', NULL, NULL, 1),

(652, 'Platine-BEE', NULL, NULL, 1),

(653, 'ALADIN', NULL, NULL, 1),

(654, 'ENET-Internet', NULL, NULL, 1),

(655, 'ENET-Intranet', NULL, NULL, 1),

(656, 'SURVMP', NULL, NULL, 1),

(657, 'SERENA', NULL, NULL, 1),

(658, 'Bunker', NULL, NULL, 1),

(659, 'Sécurité', NULL, NULL, 1),

(660, 'Sites Agences NOC', NULL, NULL, 1),

(661, 'Sièges CE NOC (Méga GSU)', NULL, NULL, 1),

(662, 'WAN DC', NULL, NULL, 1),

(663, 'LAN DC', NULL, NULL, 1),

(664, 'Sites IT-CE', NULL, NULL, 1),

(665, 'SAS Prestataires & filiales', NULL, NULL, 1),

(666, 'Accès CultureNet CE', NULL, NULL, 1),

(667, 'Serveurs Agences', NULL, NULL, 1),

(668, 'Ulysse', NULL, NULL, 1),

(669, '2GPE', NULL, NULL, 1),

(670, 'Flux CFT', NULL, NULL, 1),
(671, 'ATD', NULL, NULL, 1)
,
(672, 'Dei Pro', NULL, NULL, 1),
(673, 'Intranet Titres (Pilcot)', NULL, NULL, 1);



