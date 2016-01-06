use parametre;

INSERT INTO `droits_liste` (`id`, `libelle`, `description`) VALUES
(148, 'URL_ADD', 'Droit d''ajouter une URL'),
(149, 'URL_MOD', 'Droit de modifier une URL'),
(150, 'URL_DEL', 'Droit de supprimer une URL');

INSERT INTO `profil_droits` (`id_profil`, `id_droits`) VALUES
(7, 148),
(6, 148),
(5, 148),
(7, 149),
(6, 149),
(5, 149),
(7, 150),
(6, 150),
(5, 150);

INSERT INTO `titre_page` (`ID`, `DESCRIPTION`, `LIBELLE`) VALUES
('URL_LST', 'Gestion des URL - liste', 'Liste des URL'),
('URL_ADD', 'Gestion des URL - création', 'Création d''une URL'),
('URL_MOD', 'Gestion des URL - modification', 'Modfification d''une URL');