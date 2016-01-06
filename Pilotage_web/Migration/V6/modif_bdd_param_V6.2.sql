use parametre;
UPDATE titre_page SET DESCRIPTION = 'Gestion des plannings - Mois Equipes' WHERE ID = 'PLN_MOIS';
UPDATE titre_page SET DESCRIPTION = 'Gestion des plannings - Semaine Pilotes' WHERE ID = 'PLN_SEM';

INSERT INTO `titre_page` (`ID`, `DESCRIPTION`, `LIBELLE`) VALUES ('PLN_M_PIL', 'Gestion des plannings - Mois Pilotes', 'Administration de planning');