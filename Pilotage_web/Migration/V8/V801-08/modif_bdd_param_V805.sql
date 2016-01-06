USE parametre;

DROP TABLE IF EXISTS parametre.droits_liste;

CREATE TABLE `droits_liste` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `libelle` varchar(45) NOT NULL,
  `description` varchar(127) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=148 DEFAULT CHARSET=latin1;


insert into `droits_liste`(`id`,`libelle`,`description`) values 
(1,'AST_DOM_ADD','Droit d''ajouter un domaine d''astreinte')
, (2,'AST_DOM_MOD','Droit de modifier un domaine d''astreinte')
, (3,'AST_DOM_DEL','Droit de supprimer un domaine d''astreinte')
, (4,'AST_OBL_ADD','Droit d''ajouter une astreinte obligatoire')
, (5,'AST_OBL_MOD','Droit de modifier une astreinte obligatoire')
, (7,'AST_OBL_DEL','Droit de supprimer une astreinte obligatoire')
, (8,'AST_PLN_ADD','Droit d''ajouter un planning d''astreinte')
, (9,'AST_PLN_MOD','Droit de modifier un planning d''astreinte')
, (10,'AST_PLN_DEL','Droit de supprimer un planning d''astreinte')
, (11,'AST_ADD','Droit d''ajouter une astreinte')
, (12,'AST_MOD','Droit de modifier une astreinte')
, (13,'AST_DEL','Droit de supprimer une astreinte')
, (14,'AST_TYP_ADD','Droit d''ajouter un type d''astreinte')
, (15,'AST_TYP_MOD','Droit de modifier un type d''astreinte')
, (16,'AST_TYP_DEL','Droit de supprimer un type d''astreinte')
, (17,'ICD_ADD','Droit d''ajouter un incident')
, (18,'ICD_MOD','Droit de modifier un incident')
, (19,'ICD_DEL','Droit de supprimer un incident')
, (20,'ICD_OTL_ADD','Droit d''ajouter un outil d''incident')
, (21,'ICD_OTL_MOD','Droit de modifier un outil d''incident')
, (22,'ICD_OTL_DEL','Droit de supprimer un outil d''incident')
, (23,'ICD_TYP_ADD','Droit d''ajouter un type d''incident')
, (24,'ICD_TYP_MOD','Droit de modifier un type d''incident')
, (25,'ICD_TYP_DEL','Droit de supprimer un type d''incident')
, (26,'AD_ADD','Droit d''ajouter une alerte disque')
, (27,'AD_MOD','Droit de modifier une alerte disque')
, (28,'AD_DEL','Droit de supprimer une alerte disque')
, (29,'AD_DES_ADD','Droit d''ajouter un destinataire d''alerte disque')
, (30,'AD_DES_MOD','Droit de modifier un destinataire d''alerte disque')
, (31,'AD_DES_DEL','Droit de supprimer un destinataire d''alerte disque')
, (32,'AD_TYP_ADD','Droit d''ajouter un type d''alerte disque')
, (33,'AD_TYP_MOD','Droit de modifier un type d''alerte disque')
, (34,'AD_TYP_DEL','Droit de supprimer un type d''alerte disque')
, (35,'BL_TYP_ADD','Droit d''ajouter un type de bilan')
, (36,'BL_TYP_MOD','Droit de modifier un type de bilan')
, (37,'BL_TYP_DEL','Droit de supprimer un type de bilan')
, (38,'BL_DES_ADD','Droit d''ajouter un destinataire de bilan')
, (39,'BL_DES_MOD','Droit de modifier un destinataire de bilan')
, (40,'BL_DES_DEL','Droit de supprimer un destinataire de bilan')
, (41,'BL_DSK_ADD','Droit d''ajouter un disque de bilan')
, (42,'BL_DSK_MOD','Droit de modifier un disque de bilan')
, (43,'BL_DSK_DEL','Droit de supprimer un disque de bilan')
, (44,'BL_FLX_ADD','Droit d''ajouter un flux cft de bilan')
, (45,'BL_FLX_MOD','Droit de modifier un flux cft de bilan')
, (46,'BL_FLX_DEL','Droit de supprimer un flux cft de bilan')
, (47,'CKL_ADD','Droit d''ajouter un checklist')
, (48,'CKL_MOD','Droit de modifier un checklist')
, (49,'CKL_DEL','Droit de supprimer un checklist')
, (50,'CSG_ADD','Droit d''ajouter une consigne')
, (51,'CSG_MOD','Droit de modifier une consigne')
, (52,'CSG_DEL','Droit de supprimer une consigne')
, (53,'DRG_ADD','Droit d''ajouter une dérogation')
, (54,'DRG_MOD','Droit de modifier une dérogation')
, (55,'DRG_DEL','Droit de supprimer une dérogation')
, (56,'DRG_TYP_ADD','Droit d''ajouter un type de dérogation')
, (57,'DRG_TYP_MOD','Droit de modifier un type de dérogation')
, (58,'DRG_TYP_DEL','Droit de supprimer un type de dérogation')
, (59,'DRG_TCH_ADD','Droit d''ajouter un type de changement de dérogation')
, (60,'DRG_TCH_MOD','Droit de modifier un type de changement de dérogation')
, (61,'DRG_TCH_DEL','Droit de supprimer un type de changement de dérogation')
, (62,'DRG_VAL_ADD','Droit d''ajouter un valideur de dérogation')
, (63,'DRG_VAL_DEL','Droit de supprimer un valideur de dérogation')
, (64,'ENV_ADD','Droit d''ajouter un environnement')
, (65,'ENV_MOD','Droit de modifier un environnement')
, (66,'ENV_DEL','Droit de supprimer un environnement')
, (67,'ENV_TYP_ADD','Droit d''ajouter un type d''environnement')
, (68,'ENV_TYP_MOD','Droit de modifier un type d''environnement')
, (69,'ENV_TYP_DEL','Droit de supprimer un type d''environnement')
, (70,'FED_ADD','Droit d''ajouter un feedback')
, (71,'FED_MOD','Droit de modifier un feedback')
, (72,'FED_DEL','Droit de supprimer un feedback')
, (73,'MAC_DOM_ADD','Droit d''ajouter un domaine de machine')
, (74,'MAC_DOM_MOD','Droit de modifier un domaine de machine')
, (75,'MAC_DOM_DEL','Droit de supprimer un domaine de machine')
, (76,'MAC_ADD','Droit d''ajouter une machine')
, (77,'MAC_MOD','Droit de modifier une machine')
, (78,'MAC_DEL','Droit de supprimer une machine')
, (79,'MAC_OS_ADD','Droit d''ajouter un os de machine')
, (80,'MAC_OS_MOD','Droit de modifier un os de machine')
, (81,'MAC_OS_DEL','Droit de supprimer un os de machine')
, (82,'MAC_SRV_ADD','Droit d''ajouter un interlocuteur de machine')
, (83,'MAC_SRV_MOD','Droit de modifier un interlocuteur de machine')
, (84,'MAC_SRV_DEL','Droit de supprimer un interlocuteur de machine')
, (85,'MAC_SIT_ADD','Droit d''ajouter un site de machine')
, (86,'MAC_SIT_MOD','Droit de modifier un site de machine')
, (87,'MAC_SIT_DEL','Droit de supprimer un site de machine')
, (88,'MAC_TYP_ADD','Droit d''ajouter un type de machine')
, (89,'MAC_TYP_MOD','Droit de modifier un type de machine')
, (90,'MAC_TYP_DEL','Droit de supprimer un type de machine')
, (91,'USR_ADD','Droit d''ajouter un utilisateur')
, (92,'USR_MOD','Droit de modifier un utilisateur')
, (93,'USR_DEL','Droit de supprimer un utilisateur')
, (94,'SRV_ADD','Droit d''ajouter un service')
, (95,'SRV_MOD','Droit de modifier un service')
, (96,'SRV_DEL','Droit de supprimer un service')
, (97,'ICD_QLT_ADD','Droit d''ajout d''une fiche incident qualité')
, (98,'ICD_QLT_MOD','Droit de modifier une fiche incident qualité')
, (99,'ICD_QLT_DEL','Droit de supprimer une fiche incident qualité')
, (100,'ICD_QLT_ACT_ADD','Droit d''ajout d''une action')
, (101,'ICD_QLT_ACT_MOD','Droit de modification d''une action')
, (102,'ICD_QLT_ACT_DEL','Droit de supprimer une action')
, (103,'CSG_BBR_ADD','Droit d''ajout d''une consigne BBR')
, (104,'CSG_BBR_MOD','Droit de modification d''une consigne BBR')
, (105,'CSG_BBR_DEL','Droit de suppression d''une consigne BBR')
, (106,'APP_ADD','Droit d''ajout d''une application')
, (107,'APP_MOD','Droit de modification d''une application')
, (108,'APP_DEL','Droit de suppression d''une application')
, (109,'PLN_CYL_ADD','Droit d''ajout d''un cycle de planning')
, (110,'PLN_CYL_MOD','Droit de modification d''un cycle de planning')
, (111,'PLN_CYL_DEL','Droit de suppression d''un cycle de planning')
, (112,'PLN_EQU_ADD','Droit d''ajout d''une équipe de planning')
, (113,'PLN_EQU_MOD','Droit de modification d''une équipe de planning')
, (114,'PLN_EQU_DEL','Droit de suppression d''une équipe de planning')
, (115,'PLN_VAC_ADD','Droit d''ajout d''une vacation de planning')
, (116,'PLN_VAC_MOD','Droit de modification d''une vacation de planning')
, (117,'PLN_VAC_DEL','Droit de suppression d''une vacation de planning')
, (118,'PLN_MOD_PONC','Droit de modification ponctuelle du planning d''un pilote')
, (120,'MTG_CAI_MOD','Droit de modifier une caisse météo')
, (121,'MTG_CAI_DEL','Droit de supprimer une caisse météo')
, (122,'MTG_CAI_ADD','Droit d''ajouter une caisse météo')
, (123,'MTG_ENV_MOD','Droit de modifier un environnement météo')
, (124,'MTG_ENV_ADD','Droit d''ajouter un environnement météo')
, (125,'MTG_ENV_DEL','Droit de supprimer un environnement météo')
, (126,'MTG_GM_ME','Droit de modifier et d''envoyer un groupe météo')
, (127,'MTG_GRM_ADD','Droit d''ajouter un groupe météo')
, (128,'MTG_GRM_MOD','Droit de modifier un groupe météo')
, (129,'MTG_GRM_DEL','Droit de supprimer un groupe météo')
, (130,'MTG_GRM_DET','Droit d''entrer dans le détail d''un groupe météo')
, (131,'MTG_IND_MOD','Droit de modifier un indicateur météo')
, (132,'MTG_MET_ADD','Droit d''ajouter une météo')
, (133,'MTG_MET_MOD','Droit de modifier une météo')
, (134,'MTG_MET_DEL','Droit de supprimer une météo')
, (135,'MTG_MET_DET','Droit d''entrer dans le détail d''une météo')
, (136,'SRV_DET','Droit d''entrer dans le détail du service')
, (137,'AST_APP_ADD','Droit d''ajout d''un appel astreinte')
, (138,'AST_APP_MOD','Droit de modifier un appel astreinte')
, (139,'AST_APP_DEL','Droit de supprimer un appel astreinte')
, (140,'AST_APP_DET','Droit de voir le détail d''un appel astreinte')
, (141,'MTG_TYP_ADD','Droit d''ajout d''un type d''indicateur Météo')
, (142,'MTG_TYP_MOD','Droit de modifier un type d''indicateur Météo')
, (143,'MTG_TYP_DEL','Droit de supprimer un type d''indicateur Météo')
, (144,'MTG_TYP_DET','Droit de voir le détail d''un type d''indicateur Météo')
, (145,'BIL_ADD','Droit d''ajout d''un type d''indicateur Météo')
, (146,'BIL_MOD','Droit de modifier un type d''indicateur Météo')
, (147,'BIL_DEL','Droit de supprimer un type d''indicateur Météo');

USE parametre;

--
-- Structure de la table `module`
--

DROP TABLE IF EXISTS parametre.module;

CREATE TABLE `module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(80) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1;

--
-- Structure de la table `sous_module`
--

INSERT INTO `module`(`id`,`nom`)
VALUES (1,'Application'),
       (2,'Astreinte'),
       (3,'Bilan'),
       (4,'Checklist'),
       (5,'Consigne'),
       (6,'Dérogation'),
       (7,'Environnement'),
       (8,'Feedback'),
       (9,'Incidents'),
       (10,'Machine'),
       (11,'Météo'),
       (12,'Planning'),
       (13,'Service'),
       (14,'Utilisateur');

USE parametre;
--
-- Structure de la table `sous_module`
--

DROP TABLE IF EXISTS parametre.sous_module;

CREATE TABLE `sous_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(80) NOT NULL,
  `id_parent` int(11) NOT NULL,
  `id_modif` int(11) DEFAULT NULL,
  `id_suppr` int(11) DEFAULT NULL,
  `id_ajout` int(11) DEFAULT NULL,
  `id_detail` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1;

--
-- Contenu de la table `sous_module`
--

INSERT INTO `sous_module` (`nom`, `id_parent`, `id_ajout`, `id_modif`, `id_suppr`, `id_detail`) VALUES
('Applications',1,106,107,108,null),
('Appel Astreinte',2,137,138,139,140),
('Astreintes',2,11,12,13,null),
('Astreintes obligatoires',2,4,5,7,null),
('Domaine',2,1,2,3,null),
('Planning',2,8,9,10,null),
('Type',2,14,15,16,null),
('Alertes disques',3,26,27,28,null),
('Alertes disques - Destinataires',3,29,30,31,null),
('Alertes disques - Type',3,32,33,34,null),
('Destinataires',3,38,39,40,null),
('Disques',3,41,42,43,null),
('Flux',3,44,45,46,null),
('Liste',3,145,146,147,null),
('Type',3,35,36,37,null),
('Taches',4,47,48,49,null),
('BBR',5,103,104,15,null),
('Consignes',5,50,51,52,null),
('Dérogations',6,53,54,55,null),
('Types',6,56,57,58,null),
('Types de changement',6,59,60,61,null),
('Valideurs',6,62,null,63,null),
('Environnements',7,64,65,66,null),
('Types',7,67,68,69,null),
('Feedback',8,70,71,72,null),
('Action Fiche Qualité',9,100,101,102,null),
('Fiche Qualité',9,97,98,99,null),
('Incidents',9,17,18,19,null),
('Outils',9,20,21,22,null),
('Type',9,23,24,25,null),
('Domaine',10,73,74,75,null),
('Interlocuteur',10,82,83,84,null),
('Machine',10,76,77,78,null),
('OS',10,79,80,81,null),
('Site',10,85,86,87,null),
('Type',10,88,89,90,null),
('Groupe de Météo',11,127,128,129,130),
('Groupe de Météo - utilisateur',11,null,126,null,null),
('Indicateurs',11,null,131,null,null),
('Météo',11,132,133,134,135),
('Météo - Caisse',11,122,120,121,null),
('Météo - Environnement',11,124,123,125,null),
('Météo - type d''indicateur',11,141,142,143,144),
('Cycle',12,109,110,111,null),
('Equipe',12,112,113,114,null),
('Planning d''un pilote',12,null,118,null,null),
('Vacation',12,115,116,117,null),
('Services',13,94,95,96,136),
('Utilisateur',14,91,92,93,null);


/*
Script created by Quest Data Compare at 31/01/2013 14:38:34
This script must be executed against parametre on 126.198.6.17
This script applies changes to parametre to make it the same as parametre on localhost
*/

USE `parametre`;

START TRANSACTION;

INSERT INTO `parametre`.`menu` (`ID`, `ID_PARENT`, `PLACE`, `LIBELLE`, `LIEN`, `ICONE`, `INTEROP`)
VALUES (88, 36, 0, 'Mensuel par équipe', 'showPlanningMoisAction.action', NULL, 0);
INSERT INTO `parametre`.`menu` (`ID`, `ID_PARENT`, `PLACE`, `LIBELLE`, `LIEN`, `ICONE`, `INTEROP`)
VALUES (89, 36, 1, 'Mensuel par pilote', 'showPlanningMoisPiloteAction.action', NULL, 0);
INSERT INTO `parametre`.`menu` (`ID`, `ID_PARENT`, `PLACE`, `LIBELLE`, `LIEN`, `ICONE`, `INTEROP`)
VALUES (90, 36, 2, 'Hebdomadaire par pilote', 'showPlanningSemaineAction.action', NULL, 0);

UPDATE `parametre`.`menu` SET `LIEN` = 'showConsigneBbrAction.action' WHERE `ID` = 28;
UPDATE `parametre`.`menu` SET `LIEN` = NULL WHERE `ID` = 36;

COMMIT;
