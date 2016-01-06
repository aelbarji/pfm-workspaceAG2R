use parametre;
INSERT INTO `droits_liste` (`id`, `libelle`, `description`) VALUES (97, 'ICD_QLT_ADD', 'Droit d\'ajout d\'une fiche incident qualit�');
INSERT INTO `droits_liste` (`id`, `libelle`, `description`) VALUES (98, 'ICD_QLT_MOD', 'Droit de modifier une fiche incident qualit�');
INSERT INTO `droits_liste` (`id`, `libelle`, `description`) VALUES (99, 'ICD_QLT_DEL', 'Droit de supprimer une fiche incident qualit�');
INSERT INTO `droits_liste` (`id`, `libelle`, `description`) VALUES (100, 'ICD_QLT_ACT_ADD', 'Droit d\'ajout d\'une action');
INSERT INTO `droits_liste` (`id`, `libelle`, `description`) VALUES (101, 'ICD_QLT_ACT_MOD', 'Droit de modification d\'une action');
INSERT INTO `droits_liste` (`id`, `libelle`, `description`) VALUES (102, 'ICD_QLT_ACT_DEL', 'Droit de supprimer une action');
INSERT INTO `droits_liste` (`id`, `libelle`, `description`) VALUES (103, 'CSG_BBR_ADD', 'Droit d\'ajout d\'une consigne BBR');
INSERT INTO `droits_liste` (`id`, `libelle`, `description`) VALUES (104, 'CSG_BBR_MOD', 'Droit de modification d\'une consigne BBR');
INSERT INTO `droits_liste` (`id`, `libelle`, `description`) VALUES (105, 'CSG_BBR_DEL', 'Droit de suppression d\'une consigne BBR');
INSERT INTO `droits_liste` (`id`, `libelle`, `description`) VALUES (106, 'APP_ADD', 'Droit d\'ajout d\'une application');
INSERT INTO `droits_liste` (`id`, `libelle`, `description`) VALUES (107, 'APP_MOD', 'Droit de modification d\'une application');
INSERT INTO `droits_liste` (`id`, `libelle`, `description`) VALUES (108, 'APP_DEL', 'Droit de suppression d\'une application');

INSERT INTO `titre_page` (`ID`, `DESCRIPTION`, `LIBELLE`) VALUES ('ICD_QLT', 'Gestion des FIQ - liste', 'Liste des F.I.Q.');
INSERT INTO `titre_page` (`ID`, `DESCRIPTION`, `LIBELLE`) VALUES ('ICD_QLT_C', 'Gestion des FIQ - cr�ation', 'Cr�ation d\'une F.I.Q.');
INSERT INTO `titre_page` (`ID`, `DESCRIPTION`, `LIBELLE`) VALUES ('ICD_QLT_M', 'Gestion des FIQ - modification', 'Modification d\'une F.I.Q.');
INSERT INTO `titre_page` (`ID`, `DESCRIPTION`, `LIBELLE`) VALUES ('ICD_Q_A', 'Gestion des FIQ - Actions - liste', 'Liste des actions d\'une F.I.Q.');
INSERT INTO `titre_page` (`ID`, `DESCRIPTION`, `LIBELLE`) VALUES ('ICD_Q_A_C', 'Gestion des FIQ - Actions - cr�ation', 'Cr�ation d\'une action d\'une F.I.Q.');
INSERT INTO `titre_page` (`ID`, `DESCRIPTION`, `LIBELLE`) VALUES ('ICD_Q_A_M', 'Gestion des FIQ - Actions - modification', 'Modification d\'une action d\'une F.I.Q.');
INSERT INTO `titre_page` (`ID`, `DESCRIPTION`, `LIBELLE`) VALUES ('BBR_ADD', 'Gestion des consignes BBR - cr�ation', 'Cr�ation d\'une consigne BBR');
INSERT INTO `titre_page` (`ID`, `DESCRIPTION`, `LIBELLE`) VALUES ('BBR_LST', 'Gestion des consignes BBR - liste', 'Liste des consignes BBR');
INSERT INTO `titre_page` (`ID`, `DESCRIPTION`, `LIBELLE`) VALUES ('BBR_MOD', 'Gestion des consignes BBR - modification', 'Modification d\'une consigne BBR');
INSERT INTO `titre_page` (`ID`, `DESCRIPTION`, `LIBELLE`) VALUES ('APP_LST', 'Gestion applications - liste', 'Liste des applications');
INSERT INTO `titre_page` (`ID`, `DESCRIPTION`, `LIBELLE`) VALUES ('APP_MOD', 'Gestion applications - modification', 'Modification d\'une application');
INSERT INTO `titre_page` (`ID`, `DESCRIPTION`, `LIBELLE`) VALUES ('APP_ADD', 'Gestion applications - cr�ation', 'Cr�ation d\'une application');