use parametre;



INSERT INTO `menu` (`ID`, `ID_PARENT`, `PLACE`, `LIBELLE`, `LIEN`, `ICONE`, `INTEROP`) 
VALUES
(99, 93, 2, 'Base incidents ', 'ShowIncidentsGupBase.action', NULL, 0),

(100, 93, 4, 'Bilan', 'ShowAccueilBilanGup.action', NULL, 0),

(102, 93, 3, 'Communiqué Incident', 'ShowIncidentsGup.action', NULL, 0),

(101, 95, 1, 'Destinataire Bilan', 'showAdminDestinataireBilan.action', NULL, 0);



INSERT INTO `titre_page` (`ID`, `DESCRIPTION`, `LIBELLE`) VALUES

('DST_BIL_A', 'Création d''un destinataire du bilan GUP', 'Création d''un destinataire du bilan GUP'),

('DST_BIL_L', 'Liste des destinataires du bilan GUP', 'Liste des destinataires du bilan GUP'),

('ICDG_DES', 'Choix des destinataires', 'Choix des destinataires'),

('ICDG_DET', 'GUP - Détail incident GUP', 'Détail d''un incident GUP'),

('DOM_COM_LST', 'Liste des domaines GUP', 'Liste des domaines'),

('DOM_COM_MOD', 'Modification d''un domaine GUP', 'Modification d''un domaine'),

('DOM_GUP_ADD', 'Création d''un domaine GUP', 'Création d''un domaine'),

('SER_COM_ADD', 'Création d''un service GUP', 'Création d''un service'),

('SER_COM_LST', 'Liste des services GUP', 'Liste des services'),

('SER_COM_MOD', 'Modification d''un service GUP', 'Modification d''un service');


INSERT INTO `droits_liste` (`id`, `libelle`, `description`) 
VALUES
(169, 'DEST_BIL_DEL', 'Suppression des destinataires du bilan du GUP'),

(170, 'DEST_BIL_ADD', 'Ajout des destinataires du bilan du GUP'),

(171, 'ICD_GUP_ADD', 'Ajout d''un incident Gup'),

(172, 'ICD_GUP_MOD', 'Modification d''un incident Gup'),

(173, 'ICD_GUP_DEL', 'Suppression d''un incident Gup'),

(174, 'ICD_BIL_ENV', 'Envoi d''un bilan des incidents GUP'),
(175, 'COM_SER_DEL', 'Suppression d''un service Gup'),
(176, 'COM_SER_ADD', 'Ajout d''un service Gup'),
(177, 'COM_SER_MOD', 'Modification d''un service Gup'),
(178, 'COM_DOM_DEL', 'Suppression d''un domaine Gup'),
(179, 'COM_DOM_ADD', 'Ajout d''un domaine Gup'),
(180, 'COM_DOM_MOD', 'Modification d''un domaine Gup');

ALTER TABLE `parametre` MODIFY `valeur` VARCHAR(200);


INSERT INTO `parametre` (`LIBELLE`, `VALEUR`) VALUES

('SIGNATURE_GUP', 'Direction EXPLOITATION ET DATACENTER <br/> Guichet Unique de Production <br/> 15 rue BRAUDEL <br/> 75013 paris <br/> 33 1 58 40 21 22');
