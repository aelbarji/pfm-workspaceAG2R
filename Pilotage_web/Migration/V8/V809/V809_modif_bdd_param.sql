USE parametre;

insert into `titre_page`(`ID`,`DESCRIPTION`,`LIBELLE`) values 
('AST_DES_C','Gestion des astreintes - Destinataire - ajout','Création de destinataire pour l''envoi des astreintes')
, ('AST_DES_D','Gestion des astreintes - Destinataire - suppression','Suppression de destinataire pour l''envoi des astreintes')
, ('AST_DES_M','Gestion des astreintes - Destinataire - modification','Modification de destinataire pour l''envoi des astreintes');


insert into `droits_liste`(`ID`,`libelle`,`description`) values 
 (151,'AST_DES_ADD','Ajout de destinatiare')
, (152,'AST_DES_MOD','Modification de destinatiare')
, (153,'AST_DES_DEL','Suppression d''un destinataire')
, (154,'AST_DES_LST','Liste des destinataires');


insert into `menu`(`ID`,`ID_PARENT`,`PLACE`,`LIBELLE`,`LIEN`,`ICONE`,`INTEROP`) values 
 (92,16,4,'Gestion des destinataires des astreintes','showAstreinteDestinataire.action',null,0);


insert into `sous_module`(`id`,`nom`,`id_parent`,`id_modif`,`id_suppr`,`id_ajout`,`id_detail`) values 
 (50,'Destinataire',2,152,153,151,154);

