use parametre;

INSERT INTO `droits_liste` (`id`, `libelle`, `description`) VALUES
(181, 'BIL_COL_ADD', 'Ajout d''une colonne dans bilan'),
(182, 'BIL_COL_MOD', 'Modification d''une colonne dans bilan'),
(183, 'BIL_COL_DEL', 'Suppression d''une colonne dans bilan');

INSERT INTO `profil_droits` (`id_droits`, `id_profil`) VALUES
(181, 5),
(182, 5),
(183, 5),
(181, 6),
(182, 6),
(183, 6),
(181, 7),
(182, 7),
(183, 7);