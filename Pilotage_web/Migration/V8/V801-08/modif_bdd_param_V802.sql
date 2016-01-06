INSERT INTO `titre_page` (`ID`, `DESCRIPTION`, `LIBELLE`) VALUES
('MTG_ETA_A', 'Gestion des m�t�os - �tat - cr�ation', 'Cr�ation d''un �tat M�t�o'),
('MTG_ETA_L', 'Gestion des m�t�os - �tat - liste', 'Liste des �tats M�t�o'),
('MTG_TYP_L', 'Gestion des m�t�os - Type indicateur - liste', 'Liste des types d''indicateur M�t�o'),
('MTG_TYP_M', 'Gestion des m�t�os - Type indicateur - modification', 'Modification d''un type indicateur M�t�o'),
('MTG_TYP_A', 'Gestion des m�t�os - Type indicateur - cr�ation', 'Cr�ation d''un type indicateur M�t�o');

INSERT INTO `droits_liste` (`id`, `libelle`, `description`) VALUES
(141, 'MTG_TYP_ADD', 'Droit d''ajout d''un type d''indicateur M�t�o'),
(142, 'MTG_TYP_MOD', 'Droit de modifier un type d''indicateur M�t�o'),
(143, 'MTG_TYP_DEL', 'Droit de supprimer un type d''indicateur M�t�o'),
(144, 'MTG_TYP_DET', 'Droit de voir le d�tail d''un type d''indicateur M�t�o');

INSERT INTO `profil_droits` (`id_profil`, `id_droits`) VALUES
(7, 141),
(6, 141),
(5, 141),
(7, 142),
(6, 142),
(5, 142),
(7, 143),
(6, 143),
(5, 143),
(7, 144),
(6, 144),
(5, 144);