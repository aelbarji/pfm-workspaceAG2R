use parametre;

INSERT INTO `droits_liste` (`id`, `libelle`, `description`) 
VALUES
(156, 'DEB_NOC_MOD', 'Modification d''un débordement NOC'),

(157, 'DEB_NOC_DEL', 'Suppression d''un débordement NOC'),

(158, 'DEST_NOC_ADD', 'Création des destinataires débordement NOC'),

(159, 'DEST_NOC_MOD', 'Modification des destinataires débordement NOC'),

(160, 'DEST_NOC_DEL', 'Suppression des destinataires débordement NOC'),

(161, 'DEST_ADD', 'Création d''un destinataire'),

(162, 'DEST_MOD', 'Modification d''un destinataire'),

(163, 'DEST_DEL', 'Suppression d''un destinataire');





INSERT INTO `menu` (`ID`, `ID_PARENT`, `PLACE`, `LIBELLE`, `LIEN`, `ICONE`, `INTEROP`) 
VALUES
(93, NULL, 11, 'Gestion GUP', NULL, 42, 0),

(94, 93, 0, 'Débordement NOC', 'showDebordementNocAction.action', NULL, 0),

(95, 93, 1, 'Admin', NULL, NULL, 0),

(96, 95, 0, 'Destinataire NOC', 'showAdminDestinataireNoc.action', NULL, 0),

(97, 1, 15, 'Destinataires', 'showDestinatairesAdmin.action', NULL, 0);



INSERT INTO `droits_liste` (`id`, `libelle`, `description`) 
VALUES
(164, 'DEB_NOC_ADD', 'Création d''un débordement NOC');




INSERT INTO `titre_page` (`ID`, `DESCRIPTION`, `LIBELLE`) 
VALUES
('DEST_ADD', 'Ajout d''un destinataire', 'Création d''un destinataire'),

('DEST_LST', 'Liste des destinataires', 'Liste des destinataires'),

('DEST_MOD', 'Modification d''un destinataire', 'Modification d''un destinataire'),

('DNOC_ADD', 'Création d''un destinataire du débordement NOC', 'Création d''un destinataire NOC'),

('DNOC_LST', 'Liste des destinataires du débordement NOC', 'Liste des destinataires NOC'),

('NOC_ADD', 'Ajout d''un débordement NOC', 'Débordement NOC'),

('NOC_LST', 'Liste des débordements NOC', 'Liste des débordements NOC');


INSERT INTO `module` (`id`, `nom`) VALUES
(15, 'GUP');
INSERT INTO `module` (`id`, `nom`) VALUES
(16, 'Destinataires');





INSERT INTO `sous_module` (`id`, `nom`, `id_parent`, `id_modif`, `id_suppr`, `id_ajout`, `id_detail`) 
VALUES (52, 'Débordement NOC', 15, 156, 157, 164, NULL),

(53, 'Administration des destinataires NOC', 15, 159, 160, 158, NULL),

(54, 'Destinataires Admin', 16, 162, 163, 161, NULL);



INSERT INTO `profil` (`ID`, `LIBELLE`, `ACCUEIL`, `CLIGN_CONSIGNE`, `ADMIN`, `PILOTE`) 
VALUES
(11, 'Communication Production', 4, '0', 0, 0);