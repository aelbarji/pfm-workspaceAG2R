INSERT INTO `droits_liste`(`id`,`libelle`,`description`) VALUES 
 (165,'MTG_SRV_DET','Droit de voir le d�tail d''un service m�t�o')
, (166,'MTG_SRV_DEL','Droit de supprimer un service m�t�o')
, (167,'MTG_SRV_MOD','Droit de modifier un service m�t�o')
, (168,'MTG_SRV_ADD','Droit d''ajouter un service m�t�o');

INSERT INTO profil_droits(id_profil,id_droits) VALUES
(7,165),
(7,166),
(7,167),
(7,168),
(6,165),
(6,166),
(6,167),
(6,168);

INSERT INTO `menu`(`id_parent`,`place`,`libelle`,`lien`) VALUES 
 (80,2,'Service','showServiceMeteoAction.action');