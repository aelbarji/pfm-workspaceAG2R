
ALTER TABLE epi.incidents ADD COLUMN resoluPil int(11) DEFAULT NULL;
ALTER TABLE epi.incidents ADD COLUMN aAstreinte int(11) NOT NULL DEFAULT 0;

UPDATE epi.incidents SET aAstreinte = 1 WHERE astreinte <> "";

DROP PROCEDURE IF EXISTS epi.migration_incident;
CREATE PROCEDURE epi.`migration_incident`()
BEGIN
    DECLARE done        int  DEFAULT 0;
    DECLARE fin         int   DEFAULT 0;
    DECLARE idIncident    INT;
    DECLARE idsAstreinte VARCHAR(20);
    DECLARE heure_debut DATETIME;
    DECLARE i   INT;
    DECLARE idas   VARCHAR(20);
    DECLARE ida   VARCHAR(3);
    DECLARE id  INT;
   
    DECLARE
      incidents CURSOR FOR SELECT  epi.incidents.id, epi.incidents.astreinte, epi.incidents.heure_debut
                          FROM     epi.incidents       
                          WHERE    epi.incidents.astreinte <> ""
                          ORDER BY epi.incidents.id ASC;
     OPEN incidents;
    REPEAT
      BEGIN
        DECLARE CONTINUE   HANDLER FOR SQLSTATE '02000' SET done =1;
        FETCH incidents INTO idIncident, idsAstreinte, heure_debut;
        IF (done = 0) THEN
          set fin = 0;
          SET idas = idsAstreinte;
          WHILE (fin = 0) DO
            set i = locate(';',idas);
            IF (i = 0) THEN 
              set ida = idas;
              select idIncident,ida,idas;
              select ida, idIncident, heure_debut, 2;
              INSERT INTO astreinte_appel (id_astreinte, id_incident, datetime, id_statut)
              VALUES (ida, idIncident, heure_debut, 2);
              SET fin = 1;
            ELSE
              set ida = substring(idas,1,i-1);
              select idIncident,ida,idas,i;
              INSERT INTO astreinte_appel (id_astreinte, id_incident, datetime, id_statut)
              VALUES (ida, idIncident, heure_debut, 2);
              SET idas = substring(idas, i+1, length(idas) - i);
            END IF;
          END WHILE;
        END IF;
      END;
    UNTIL done
  END REPEAT;
  CLOSE incidents;
END;

call epi.`migration_incident`();


CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `epi`.`astreinte_appel_view` AS 
select `aa`.`id_astreinte` AS `id_astreinte`,`aa`.`id_incident` AS `id_incident`,min(`aa`.`datetime`) AS `minDate`,count(`aa`.`datetime`) AS `nbAppel`,max(`aa`.`datetime`) AS `maxDate` 
from `epi`.`astreinte_appel` `aa` group by `aa`.`id_astreinte`,`aa`.`id_incident`;


CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `epi`.`astreinte_nombre_appel_view` AS 
select distinct `ab`.`id` AS `id`,`aa`.`id_astreinte` AS `id_astreinte`,`aa`.`id_incident` AS `id_incident`,`aa`.`minDate` AS `minDate`,`aa`.`nbAppel` AS `nbAppel`,`ab`.`id_statut` AS `id_statut` 
from (`epi`.`astreinte_appel_view` `aa` join `epi`.`astreinte_appel` `ab` 
on(((`aa`.`id_astreinte` = `ab`.`id_astreinte`) and (`aa`.`id_incident` = `ab`.`id_incident`) and (`aa`.`maxDate` = `ab`.`datetime`))));

