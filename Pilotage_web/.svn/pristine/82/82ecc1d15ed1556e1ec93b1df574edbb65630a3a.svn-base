use epi
-- modif de checklist_current
ALTER TABLE `checklist_current` CHANGE `heure_gmt` `heure_gmt` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00';
ALTER TABLE `checklist_current` CHANGE `jour` `jour` DATE NOT NULL DEFAULT '1970-01-01';

-- procedures stockées traitement des jours ordinaires
DROP PROCEDURE IF EXISTS epi.CheckAllJour;
CREATE PROCEDURE epi.`CheckAllJour`(IN DateofDay Char(10))
BEGIN
    DECLARE done        int DEFAULT 0;
    DECLARE idTache     INT;
    DECLARE idSousTache INT;
    DECLARE idHoraire   INT;
    DECLARE SaufFerie   int;
    DECLARE JourFerie   int;

    DECLARE
      AllJours CURSOR FOR SELECT   DISTINCT cb.id AS id_tache, cbs.id AS id_sous_tache, cho.id AS id_horaire, cjo.ferie AS ferie
                          FROM     epi.checklist_base cb
                                   INNER JOIN epi.checklist_jour cjo
                                     ON cjo.id_checklist = cb.id
                                   INNER JOIN epi.checklist_horaire cho
                                     ON cho.id_checklist = cb.id
                                   INNER JOIN epi.checklist_base_soustache cbs
                                     ON cb.id = cbs.id_base
                          WHERE    cb.etat IN (1, 3) AND cb.date_debut <= Date(DateofDay) AND cho.actif = 1 AND cb.actif = 1 AND
                                   cbs.actif = 1 AND cjo.jour = (DATE_FORMAT(DATE_SUB(DATE(DateofDay), INTERVAL FLOOR(HOUR(ADDTIME(cho.horaire, cbs.decalage)) / 24) DAY), '%w')
                                   ) AND cb.id NOT IN (SELECT cme.id_checklist
                                                       FROM   epi.checklist_mensuel cme
                                   UNION
                                                       SELECT cpa.id_checklist
                                                       FROM   epi.checklist_parite cpa)
                          ORDER BY cho.horaire ASC;

    SET JourFerie = epi.IdFerie(DateOfDay, 0);

    OPEN AllJours;

    REPEAT
      BEGIN
        DECLARE CONTINUE   HANDLER FOR SQLSTATE '02000' SET done =1;
        FETCH AllJours INTO idTache, idSousTache, idHoraire,SaufFerie;
        IF (done = 0) THEN
          IF (isnull(JourFerie)) THEN
            INSERT INTO epi.checklist_current (tache,sous_tache,jour,jour_stamp,id_horaire) VALUES (idTache,idSousTache,Date(DateOfDay),UNIX_TIMESTAMP(DateOfDay)*1000,idHoraire);
          ELSEIF (SaufFerie = 0) THEN
            INSERT INTO epi.checklist_current (tache,sous_tache,jour,jour_stamp,id_horaire) VALUES (idTache,idSousTache,Date(DateOfDay),UNIX_TIMESTAMP(DateOfDay)*1000,idHoraire);
          END IF;
        END IF;
      END;
    UNTIL done
    END REPEAT;

    CLOSE AllJours;
  END;

-- procedures stockées traitement des jours exceptionnels
  
DROP PROCEDURE IF EXISTS epi.CheckExceptionnel;
CREATE PROCEDURE epi.`CheckExceptionnel`(DateOfDay Date)
BEGIN
      DECLARE done                 int DEFAULT 0;
      DECLARE idTache              INT(11);
      DECLARE idSousTache          INT(11);
      DECLARE idHoraire            INT(11);

      DECLARE
         AllFerie CURSOR FOR SELECT DISTINCT
                                    cb.id AS id_tache,
                                    cbs.id AS id_sous_tache,
                                    cho.id AS id_horaire
                               FROM epi.checklist_base cb
                                    INNER JOIN epi.checklist_exceptionnel cex
                                       ON cex.id_checklist = cb.id
                                    INNER JOIN epi.checklist_horaire cho
                                       ON cho.id_checklist = cb.id
                                    INNER JOIN epi.checklist_base_soustache cbs
                                       ON cb.id = cbs.id_base
                              WHERE cb.etat IN (1, 3) AND cho.actif = 1 AND cb.actif = 1 AND cbs.actif = 1
                                    AND cex.jour =(date_sub( DateOfDay, INTERVAL floor( hour( addtime( cho.horaire,cbs.decalage))/24) DAY ));

     DECLARE CONTINUE   HANDLER FOR SQLSTATE '02000' SET done =1;
    OPEN AllFerie;

    REPEAT
        FETCH AllFerie INTO idTache, idSousTache, idHoraire;
        IF (done= 0) THEN
          IF (NOT isnull(idTache)) THEN
            INSERT INTO epi.checklist_current (tache,sous_tache,jour,jour_stamp,id_horaire) VALUES (idTache,idSousTache,DATE(DateOfDay),UNIX_TIMESTAMP(DateOfDay)*1000,idHoraire);
          END IF;
        END IF;
      UNTIL done END REPEAT;
    CLOSE AllFerie;
  END;

-- procedures stockées traitement des jours fériés
  
DROP PROCEDURE IF EXISTS epi.CheckFerie;
CREATE PROCEDURE epi.`CheckFerie`(IN DateOfDay Date)
BEGIN
    DECLARE done        int DEFAULT 0;
    DECLARE idTache     INT(11);
    DECLARE idSousTache INT(11);
    DECLARE idHoraire   INT(11);

    DECLARE
      AllFerie CURSOR FOR SELECT DISTINCT cb.id AS id_tache, cbs.id AS id_sous_tache, cho.id AS id_horaire
                          FROM   epi.checklist_base cb
                                 INNER JOIN epi.checklist_ferie cfe
                                   ON cfe.id_checklist = cb.id
                                 INNER JOIN epi.checklist_horaire cho
                                   ON cho.id_checklist = cb.id
                                 INNER JOIN epi.checklist_base_soustache cbs
                                   ON cb.id = cbs.id_base
                          WHERE  cb.etat IN (1, 3) AND cho.actif = 1 AND cb.actif = 1 AND cbs.actif = 1 AND ((cfe.id_jour_ferie =
                                   epi.IdFerie                                                                ((date_sub(DateOfDay, INTERVAL floor(hour(addtime(cho.horaire, cbs.decalage)) / 24) DAY)), 0)
                                 AND cfe.lendeveille = 0) OR (cfe.id_jour_ferie =
                                   epi.IdFerie                                                               ((date_sub(DateOfDay, INTERVAL floor(hour(addtime(cho.horaire, cbs.decalage)) / 24) DAY)), 1)
                                 AND cfe.lendeveille = 1) OR (cfe.id_jour_ferie =
                                   epi.IdFerie                                                               ((date_sub(DateOfDay, INTERVAL floor(hour(addtime(cho.horaire, cbs.decalage)) / 24) DAY)), 2)
                                 AND cfe.lendeveille = 2));

    OPEN AllFerie;

    REPEAT
      BEGIN
        DECLARE CONTINUE   HANDLER FOR SQLSTATE '02000' SET done =1;
        FETCH AllFerie INTO idTache, idSousTache, idHoraire;
        IF (done= 0) THEN
          IF (NOT isnull(idTache)) THEN
            INSERT INTO epi.checklist_current (tache,sous_tache,jour,jour_stamp,id_horaire) VALUES (idTache,idSousTache,DATE(DateOfDay),UNIX_TIMESTAMP(DateOfDay)*1000,idHoraire);
          END IF;
        END IF;
      END;
    UNTIL done
    END REPEAT;

    CLOSE AllFerie;
  END;

-- procedures stockées traitement des mensuels
  
DROP PROCEDURE IF EXISTS epi.CheckMensuel;
CREATE PROCEDURE epi.`CheckMensuel`(DateOfDay Date)
BEGIN
    DECLARE done        int DEFAULT 0;
    DECLARE idTache     INT(11);
    DECLARE idSousTache INT(11);
    DECLARE idHoraire   INT(11);
    DECLARE SaufFerie   int;
    DECLARE JourFerie   int;

    DECLARE
      AllMensuel CURSOR FOR SELECT DISTINCT cb.id AS id_tache, cbs.id AS id_sous_tache, cho.id AS id_horaire, cjo.ferie AS ferie
                            FROM   epi.checklist_base cb
                                   INNER JOIN epi.checklist_jour cjo
                                     ON cjo.id_checklist = cb.id
                                   INNER JOIN epi.checklist_mensuel cme
                                     ON cme.id_checklist = cb.id
                                   INNER JOIN epi.checklist_parite cpa
                                     ON cpa.id_checklist = cb.id
                                   INNER JOIN epi.checklist_horaire cho
                                     ON cho.id_checklist = cb.id
                                   INNER JOIN epi.checklist_base_soustache cbs
                                     ON cb.id = cbs.id_base
                            WHERE  cb.etat IN (1, 3) AND cho.actif = 1 AND cb.actif = 1 AND cbs.actif = 1 AND cjo.jour =
                                     (DATE_FORMAT                                                             (date_sub(DateOfDay, INTERVAL floor(hour(addtime(cho.horaire, cbs.decalage)) / 24) DAY), '%w')
                                   ) AND (cme.mensuel =
                                     (floor((dayofmonth(date_sub(date(DateOfDay), INTERVAL floor(hour(addtime(cho.horaire, cbs.decalage)) / 24) DAY)) + 6) / 7
                                   )) OR cme.mensuel =
                                     (datediff(last_day(date_sub(date(DateOfDay), INTERVAL floor(hour(addtime(cho.horaire, cbs.decalage)) / 24) DAY)), date_sub(date(DateOfDay), INTERVAL floor(hour(addtime(cho.horaire, cbs.decalage)) / 24) DAY))
                                   DIV
                                     7));

    SET JourFerie = epi.IdFerie(DateOfDay, 0);

    OPEN AllMensuel;

    REPEAT
      BEGIN
        DECLARE CONTINUE   HANDLER FOR SQLSTATE '02000' SET done =1;
        FETCH AllMensuel INTO idTache, idSousTache, idHoraire,SaufFerie;
        IF (done= 0) THEN
          IF (NOT isnull(idTache)) THEN
            IF (isnull(JourFerie)) THEN
              INSERT INTO epi.checklist_current (tache,sous_tache,jour,jour_stamp,id_horaire) VALUES (idTache,idSousTache,Date(DateOfDay),UNIX_TIMESTAMP(DateOfDay)*1000,idHoraire);
            ELSEIF (SaufFerie = 0) THEN
              INSERT INTO epi.checklist_current (tache,sous_tache,jour,jour_stamp,id_horaire) VALUES (idTache,idSousTache,Date(DateOfDay),UNIX_TIMESTAMP(DateOfDay)*1000,idHoraire);
            END IF;
          END IF;
        END IF;
      END;
    UNTIL done
    END REPEAT;

    CLOSE AllMensuel;
  END;

-- procedures stockées traitement des semaines paires
  
DROP PROCEDURE IF EXISTS epi.CheckParite;
CREATE PROCEDURE epi.`CheckParite`(DateOfDay Date)
BEGIN
    DECLARE done        int DEFAULT 0;
    DECLARE idTache     INT(11);
    DECLARE idSousTache INT(11);
    DECLARE idHoraire   INT(11);
    DECLARE SaufFerie   int;
    DECLARE JourFerie   int;

    DECLARE
      AllParite CURSOR FOR SELECT DISTINCT cb.id AS id_tache, cbs.id AS id_sous_tache, cho.id AS id_horaire, cjo.ferie AS ferie
                           FROM   epi.checklist_base cb
                                  INNER JOIN epi.checklist_jour cjo
                                    ON cjo.id_checklist = cb.id
                                  INNER JOIN epi.checklist_parite cpa
                                    ON cpa.id_checklist = cb.id
                                  INNER JOIN epi.checklist_horaire cho
                                    ON cho.id_checklist = cb.id
                                  INNER JOIN epi.checklist_base_soustache cbs
                                    ON cb.id = cbs.id_base
                           WHERE  cb.etat IN (1, 3) AND cho.actif = 1 AND cb.actif = 1 AND cbs.actif = 1 AND cpa.parite = (week(date_sub(date(DateOfDay), INTERVAL floor(hour(addtime(cho.horaire, cbs.decalage)) / 24) DAY), 1)
                                  % 2) AND cjo.jour =
                                    (DATE_FORMAT(date_sub(DateOfDay, INTERVAL floor(hour(addtime(cho.horaire, cbs.decalage)) / 24) DAY)
                                  , '%w'));

    SET JourFerie = epi.IdFerie(DateOfDay, 0);

    OPEN AllParite;

    REPEAT
      BEGIN
        DECLARE CONTINUE   HANDLER FOR SQLSTATE '02000' SET done =1;
        FETCH AllParite INTO idTache, idSousTache, idHoraire,SaufFerie;
        IF (done= 0) THEN
          IF (NOT isnull(idTache)) THEN
            IF (isnull(JourFerie)) THEN
              INSERT INTO epi.checklist_current (tache,sous_tache,jour,jour_stamp,id_horaire) VALUES (idTache,idSousTache,Date(DateOfDay),UNIX_TIMESTAMP(DateOfDay)*1000,idHoraire);
            ELSEIF (SaufFerie = 0) THEN
              INSERT INTO epi.checklist_current (tache,sous_tache,jour,jour_stamp,id_horaire) VALUES (idTache,idSousTache,Date(DateOfDay),UNIX_TIMESTAMP(DateOfDay)*1000,idHoraire);
            END IF;
          END IF;
        END IF;
      END;
    UNTIL done
    END REPEAT;

    CLOSE AllParite;
  END;

-- procedures stockées creation de la checklist du jour
  
DROP PROCEDURE IF EXISTS epi.CreateCheckJour;
CREATE PROCEDURE epi.`CreateCheckJour`(IN DateOfDay Date)
BEGIN
    CALL epi.CheckAllJour(DateOfDay);
    CALL epi.CheckParite(DateOfDay);
    CALL epi.CheckMensuel(DateOfDay);
    CALL epi.CheckExceptionnel(DateOfDay);
    CALL epi.CheckFerie(DateOfDay);
    UPDATE   epi.checklist_current ccu
       INNER JOIN
         epi.checklist_annule can
       ON (ccu.sous_tache = can.tache AND ccu.id_horaire = can.horaire)
SET    ccu.status = 5
WHERE  can.date = DateOfDay AND ccu.jour = DateOfDay;
  END;


-- procedures stockées traitement des checks pour une date future sans sauvegarde en base
  
DROP PROCEDURE IF EXISTS epi.CheckFutureAllJour;

CREATE PROCEDURE epi.`CheckFutureAllJour`(IN DateofDay Char(10))
  BEGIN
    DECLARE JourFerie int;
    SET @counter  = 0;
    SET JourFerie = epi.IdFerie(DateOfDay, 0);

    IF (isnull(jourFerie))
    THEN
      SELECT       DISTINCT @counter := @counter + 1 AS id, cb.id AS tache, cbs.id AS sous_tache,
      cho.id AS id_horaire, Date(DateOfDay) AS jour, UNIX_TIMESTAMP(DateOfDay)*1000 AS jour_stamp,
      0 AS heure_stamp, '1970-01-01 00:00:00' AS heure, '1970-01-01 00:00:00' AS heure_gmt,
      6 AS status, NULL AS commentaire, NULL AS time_used,
      NULL AS user
      FROM         epi.checklist_base cb
      INNER JOIN epi.checklist_jour cjo
        ON cjo.id_checklist = cb.id
      INNER JOIN epi.checklist_horaire cho
        ON cho.id_checklist = cb.id
      INNER JOIN epi.checklist_base_soustache cbs
        ON cb.id = cbs.id_base
      WHERE        cb.etat IN (1, 3) AND cb.date_debut <= Date(DateofDay) AND cho.actif = 1 AND cb.actif = 1 AND cbs.actif = 1 AND
      cjo.jour =
        (DATE_FORMAT
      (                                                                                                                            DATE_SUB(DATE(DateofDay), INTERVAL FLOOR(HOUR(ADDTIME(cho.horaire, cbs.decalage)) / 24) DAY)
      , '%w')) AND cb.id NOT IN (SELECT cme.id_checklist
                                 FROM   epi.checklist_mensuel cme
      UNION
                                 SELECT cpa.id_checklist
                                 FROM   epi.checklist_parite cpa)
      UNION
      SELECT       DISTINCT @counter := @counter + 1 AS id, cb.id AS tache, cbs.id AS sous_tache,
      cho.id AS id_horaire, Date(DateOfDay) AS jour, UNIX_TIMESTAMP(DateOfDay)*1000 AS jour_stamp,
      0 AS heure_stamp, '1970-01-01 00:00:00' AS heure, '1970-01-01 00:00:00' AS heure_gmt,
      6 AS status, NULL AS commentaire, NULL AS time_used,
      NULL AS user
      FROM         epi.checklist_base cb
      INNER JOIN epi.checklist_exceptionnel cex
        ON cex.id_checklist = cb.id
      INNER JOIN epi.checklist_horaire cho
        ON cho.id_checklist = cb.id
      INNER JOIN epi.checklist_base_soustache cbs
        ON cb.id = cbs.id_base
      WHERE        cb.etat IN (1, 3) AND cho.actif = 1 AND cb.actif = 1 AND cbs.actif = 1 AND cex.jour = (date_sub(DateOfDay, INTERVAL floor(hour(addtime(cho.horaire, cbs.decalage)) / 24) DAY)
      )
      UNION
      SELECT       DISTINCT DISTINCT @counter := @counter + 1 AS id, cb.id AS tache, cbs.id AS sous_tache,
      cho.id AS id_horaire, Date     (DateOfDay) AS jour, UNIX_TIMESTAMP(DateOfDay)*1000 AS jour_stamp,
      0 AS heure_stamp, '1970-01-01 00:00:00' AS heure, '1970-01-01 00:00:00' AS heure_gmt,
      6 AS status, NULL AS commentaire, NULL AS time_used,
      NULL AS user
      FROM         epi.checklist_base cb
      INNER JOIN epi.checklist_ferie cfe
        ON cfe.id_checklist = cb.id
      INNER JOIN epi.checklist_horaire cho
        ON cho.id_checklist = cb.id
      INNER JOIN epi.checklist_base_soustache cbs
        ON cb.id = cbs.id_base
      WHERE        cb.etat IN (1, 3) AND cho.actif = 1 AND cb.actif = 1 AND cbs.actif = 1 AND ((cfe.id_jour_ferie = epi.IdFerie((date_sub(DateOfDay, INTERVAL floor(hour(addtime(cho.horaire, cbs.decalage)) / 24) DAY)), 0)
      AND cfe.lendeveille = 0) OR (cfe.id_jour_ferie =
        epi.IdFerie                                                                            ((date_sub(DateOfDay, INTERVAL floor(hour(addtime(cho.horaire, cbs.decalage)) / 24) DAY))
      ,
      1) AND cfe.lendeveille = 1) OR (cfe.id_jour_ferie =
        epi.IdFerie                                                                            ((date_sub(DateOfDay, INTERVAL floor(hour(addtime(cho.horaire, cbs.decalage)) / 24) DAY))
      ,
      2) AND cfe.lendeveille = 2))
      UNION
      SELECT       DISTINCT @counter := @counter + 1 AS id, cb.id AS tache, cbs.id AS sous_tache,
      cho.id AS id_horaire, Date(DateOfDay) AS jour, UNIX_TIMESTAMP(DateOfDay)*1000 AS jour_stamp,
      0 AS heure_stamp, '1970-01-01 00:00:00' AS heure, '1970-01-01 00:00:00' AS heure_gmt,
      6 AS status, NULL AS commentaire, NULL AS time_used,
      NULL AS user
      FROM         epi.checklist_base cb
      INNER JOIN epi.checklist_jour cjo
        ON cjo.id_checklist = cb.id
      INNER JOIN epi.checklist_mensuel cme
        ON cme.id_checklist = cb.id
      INNER JOIN epi.checklist_parite cpa
        ON cpa.id_checklist = cb.id
      INNER JOIN epi.checklist_horaire cho
        ON cho.id_checklist = cb.id
      INNER JOIN epi.checklist_base_soustache cbs
        ON cb.id = cbs.id_base
      WHERE        cb.etat IN (1, 3) AND cho.actif = 1 AND cb.actif = 1 AND cbs.actif = 1 AND cjo.jour = (DATE_FORMAT(date_sub(DateOfDay, INTERVAL floor(hour(addtime(cho.horaire, cbs.decalage)) / 24) DAY), '%w')
      ) AND (cme.mensuel =
        (floor     ((dayofmonth(date_sub(date(DateOfDay), INTERVAL floor(hour(addtime(cho.horaire, cbs.decalage)) / 24) DAY)) + 6) / 7
      )) OR cme.mensuel =
        (datediff  (last_day(date_sub(date(DateOfDay), INTERVAL floor(hour(addtime(cho.horaire, cbs.decalage)) / 24) DAY)), date_sub(date(DateOfDay), INTERVAL floor(hour(addtime(cho.horaire, cbs.decalage)) / 24) DAY))
      DIV
        7))
      UNION
      SELECT       DISTINCT @counter := @counter + 1 AS id, cb.id AS tache, cbs.id AS sous_tache,
      cho.id AS id_horaire, Date(DateOfDay) AS jour, UNIX_TIMESTAMP(DateOfDay)*1000 AS jour_stamp,
      0 AS heure_stamp, '1970-01-01 00:00:00' AS heure, '1970-01-01 00:00:00' AS heure_gmt,
      6 AS status, NULL AS commentaire, NULL AS time_used,
      NULL AS user
      FROM         epi.checklist_base cb
      INNER JOIN epi.checklist_jour cjo
        ON cjo.id_checklist = cb.id
      INNER JOIN epi.checklist_parite cpa
        ON cpa.id_checklist = cb.id
      INNER JOIN epi.checklist_horaire cho
        ON cho.id_checklist = cb.id
      INNER JOIN epi.checklist_base_soustache cbs
        ON cb.id = cbs.id_base
      WHERE        cb.etat IN (1, 3) AND cho.actif = 1 AND cb.actif = 1 AND cbs.actif = 1 AND cpa.parite = (week(date_sub(date(DateOfDay), INTERVAL floor(hour(addtime(cho.horaire, cbs.decalage)) / 24) DAY), 1)
      % 2) AND cjo.jour =
        (DATE_FORMAT(date_sub(DateOfDay, INTERVAL floor(hour(addtime(cho.horaire, cbs.decalage)) / 24) DAY), '%w'));
    ELSE
      SELECT       DISTINCT @counter := @counter + 1 AS id, cb.id AS tache, cbs.id AS sous_tache,
      cho.id AS id_horaire, Date(DateOfDay) AS jour, UNIX_TIMESTAMP(DateOfDay)*1000 AS jour_stamp,
      0 AS heure_stamp, '1970-01-01 00:00:00' AS heure, '1970-01-01 00:00:00' AS heure_gmt,
      6 AS status, NULL AS commentaire, NULL AS time_used,
      NULL AS user
      FROM         epi.checklist_base cb
      INNER JOIN epi.checklist_jour cjo
        ON cjo.id_checklist = cb.id
      INNER JOIN epi.checklist_horaire cho
        ON cho.id_checklist = cb.id
      INNER JOIN epi.checklist_base_soustache cbs
        ON cb.id = cbs.id_base
      WHERE        cb.etat IN (1, 3) AND cjo.ferie = 0 AND cb.date_debut <= Date(DateofDay) AND cho.actif = 1 AND cb.actif = 1 AND
      cbs.actif = 1 AND cjo.jour = (DATE_FORMAT(DATE_SUB(DATE(DateofDay), INTERVAL FLOOR(HOUR(ADDTIME(cho.horaire, cbs.decalage)) / 24) DAY), '%w')
      ) AND cb.id NOT IN (SELECT cme.id_checklist
                          FROM   epi.checklist_mensuel cme
      UNION
                          SELECT cpa.id_checklist
                          FROM   epi.checklist_parite cpa)
      UNION
      SELECT       DISTINCT @counter := @counter + 1 AS id, cb.id AS tache, cbs.id AS sous_tache,
      cho.id AS id_horaire, Date(DateOfDay) AS jour, UNIX_TIMESTAMP(DateOfDay)*1000 AS jour_stamp,
      0 AS heure_stamp, '1970-01-01 00:00:00' AS heure, '1970-01-01 00:00:00' AS heure_gmt,
      6 AS status, NULL AS commentaire, NULL AS time_used,
      NULL AS user
      FROM         epi.checklist_base cb
      INNER JOIN epi.checklist_exceptionnel cex
        ON cex.id_checklist = cb.id
      INNER JOIN epi.checklist_horaire cho
        ON cho.id_checklist = cb.id
      INNER JOIN epi.checklist_base_soustache cbs
        ON cb.id = cbs.id_base
      WHERE        cb.etat IN (1, 3) AND cho.actif = 1 AND cb.actif = 1 AND cbs.actif = 1 AND cex.jour = (date_sub(DateOfDay, INTERVAL floor(hour(addtime(cho.horaire, cbs.decalage)) / 24) DAY)
      )
      UNION
      SELECT       DISTINCT DISTINCT @counter := @counter + 1 AS id, cb.id AS tache, cbs.id AS sous_tache,
      cho.id AS id_horaire, Date     (DateOfDay) AS jour, UNIX_TIMESTAMP(DateOfDay)*1000 AS jour_stamp,
      0 AS heure_stamp, '1970-01-01 00:00:00' AS heure, '1970-01-01 00:00:00' AS heure_gmt,
      6 AS status, NULL AS commentaire, NULL AS time_used,
      NULL AS user
      FROM         epi.checklist_base cb
      INNER JOIN epi.checklist_ferie cfe
        ON cfe.id_checklist = cb.id
      INNER JOIN epi.checklist_horaire cho
        ON cho.id_checklist = cb.id
      INNER JOIN epi.checklist_base_soustache cbs
        ON cb.id = cbs.id_base
      WHERE        cb.etat IN (1, 3) AND cho.actif = 1 AND cb.actif = 1 AND cbs.actif = 1 AND ((cfe.id_jour_ferie = epi.IdFerie((date_sub(DateOfDay, INTERVAL floor(hour(addtime(cho.horaire, cbs.decalage)) / 24) DAY)), 0)
      AND cfe.lendeveille = 0) OR (cfe.id_jour_ferie =
        epi.IdFerie                                                                            ((date_sub(DateOfDay, INTERVAL floor(hour(addtime(cho.horaire, cbs.decalage)) / 24) DAY))
      ,
      1) AND cfe.lendeveille = 1) OR (cfe.id_jour_ferie =
        epi.IdFerie                                                                            ((date_sub(DateOfDay, INTERVAL floor(hour(addtime(cho.horaire, cbs.decalage)) / 24) DAY))
      ,
      2) AND cfe.lendeveille = 2))
      UNION
      SELECT       DISTINCT @counter := @counter + 1 AS id, cb.id AS tache, cbs.id AS sous_tache,
      cho.id AS id_horaire, Date(DateOfDay) AS jour, UNIX_TIMESTAMP(DateOfDay)*1000 AS jour_stamp,
      0 AS heure_stamp, '1970-01-01 00:00:00' AS heure, '1970-01-01 00:00:00' AS heure_gmt,
      6 AS status, NULL AS commentaire, NULL AS time_used,
      NULL AS user
      FROM         epi.checklist_base cb
      INNER JOIN epi.checklist_jour cjo
        ON cjo.id_checklist = cb.id
      INNER JOIN epi.checklist_mensuel cme
        ON cme.id_checklist = cb.id
      INNER JOIN epi.checklist_parite cpa
        ON cpa.id_checklist = cb.id
      INNER JOIN epi.checklist_horaire cho
        ON cho.id_checklist = cb.id
      INNER JOIN epi.checklist_base_soustache cbs
        ON cb.id = cbs.id_base
      WHERE        cb.etat IN (1, 3) AND cjo.ferie = 0 AND cho.actif = 1 AND cb.actif = 1 AND cbs.actif = 1 AND cjo.jour =
        (DATE_FORMAT                                                                                            (date_sub(DateOfDay, INTERVAL floor(hour(addtime(cho.horaire, cbs.decalage)) / 24) DAY), '%w')
      ) AND (cme.mensuel =
        (floor     ((dayofmonth(date_sub(date(DateOfDay), INTERVAL floor(hour(addtime(cho.horaire, cbs.decalage)) / 24) DAY)) + 6) / 7
      )) OR cme.mensuel =
        (datediff  (last_day(date_sub(date(DateOfDay), INTERVAL floor(hour(addtime(cho.horaire, cbs.decalage)) / 24) DAY)), date_sub(date(DateOfDay), INTERVAL floor(hour(addtime(cho.horaire, cbs.decalage)) / 24) DAY))
      DIV
        7))
      UNION
      SELECT DISTINCT  @counter   := @counter + 1 AS id, cb.id AS tache, cbs.id AS sous_tache,
      cho.id AS id_horaire, Date(DateOfDay) AS jour, UNIX_TIMESTAMP(DateOfDay)*1000 AS jour_stamp,
      0 AS heure_stamp, '1970-01-01 00:00:00' AS heure, '1970-01-01 00:00:00' AS heure_gmt,
      6 AS status, NULL AS commentaire, NULL AS time_used,
      NULL AS user
                           FROM   epi.checklist_base cb
                                  INNER JOIN epi.checklist_jour cjo
                                    ON cjo.id_checklist = cb.id
                                  INNER JOIN epi.checklist_parite cpa
                                    ON cpa.id_checklist = cb.id
                                  INNER JOIN epi.checklist_horaire cho
                                    ON cho.id_checklist = cb.id
                                  INNER JOIN epi.checklist_base_soustache cbs
                                    ON cb.id = cbs.id_base
                           WHERE  cb.etat IN (1, 3) AND cjo.ferie = 0 AND cho.actif = 1 AND cb.actif = 1 AND cbs.actif = 1 AND cpa.parite = (week(date_sub(date(DateOfDay), INTERVAL floor(hour(addtime(cho.horaire, cbs.decalage)) / 24) DAY), 1)
                                  % 2) AND cjo.jour =
                                    (DATE_FORMAT(date_sub(DateOfDay, INTERVAL floor(hour(addtime(cho.horaire, cbs.decalage)) / 24) DAY)
                                  , '%w'));
    END IF;
  END; 
 
-- procedures stockées traitement creation de la checklist pour une date future appel de la procedure
  
DROP PROCEDURE IF EXISTS epi.CreateFutureCheckJour;
CREATE PROCEDURE epi.`CreateFutureCheckJour`(IN DateOfDay Date)
BEGIN
    CALL epi.CheckFutureAllJour(DateOfDay);
  END;  
  

-- Fonction calcul des jours férié France avec notion de veille et de lendemain
  
DROP FUNCTION IF EXISTS epi.IdFerie;
CREATE FUNCTION epi.`IdFerie`(DateOfDay Char(10), Decalage int) RETURNS int(11) DETERMINISTIC
BEGIN
      DECLARE JourTest    DATE;
      DECLARE JourFerie   varchar(33) DEFAULT NULL;
      DECLARE idFerie     INT DEFAULT NULL;
      DECLARE DiffDAYS    INT;

      IF (Decalage = 1)
      THEN
         SET JourTest = ADDDATE(Date(DateOfDay), 1);
      ELSEIF (Decalage = 2)
      THEN
         SET JourTest = SUBDATE(Date(DateOfDay), 1);
      ELSE
         SET JourTest = Date(DateOfDay);
      END IF;

      IF (MONTH(JourTest) = 1 AND DAY(JourTest) = 1)
      THEN
         SET JourFerie = "Jour de l'An";
      END IF;

      IF (MONTH(JourTest) = 5 AND DAY(JourTest) = 1)
      THEN
         SET JourFerie = "Fete du travail";
      END IF;

      IF (MONTH(JourTest) = 5 AND DAY(JourTest) = 8)
      THEN
         SET JourFerie = "Fete de la victoire 1945";
      END IF;

      IF (MONTH(JourTest) = 7 AND DAY(JourTest) = 14)
      THEN
         SET JourFerie = "Fete Nationale";
      END IF;

      IF (MONTH(JourTest) = 8 AND DAY(JourTest) = 15)
      THEN
         SET JourFerie = "Assomption";
      END IF;

      IF (MONTH(JourTest) = 11 AND DAY(JourTest) = 1)
      THEN
         SET JourFerie = "Toussaint";
      END IF;

      IF (MONTH(JourTest) = 11 AND DAY(JourTest) = 11)
      THEN
         SET JourFerie = "Armistice";
      END IF;

      IF (MONTH(JourTest) = 12 AND DAY(JourTest) = 25)
      THEN
         SET JourFerie = "Noel";
      END IF;

      IF (MONTH(JourTest) = 12 AND DAY(JourTest) = 25)
      THEN
         SET JourFerie = "Lendemain de Noel";
      END IF;

      SET DiffDays = DATEDIFF(JourTest, epi.Paques(YEAR(JourTest)));

      IF (DiffDAYS = -2)
      THEN
         SET JourFerie = "Vendredi Saint";
      END IF;

      IF (DiffDays = 1)
      THEN
         SET JourFerie = "Lundi de Paques";
      END IF;

      IF (DiffDays = 39)
      THEN
         SET JourFerie = "Ascension";
      END IF;

      IF (DiffDays = 50)
      THEN
         SET JourFerie = "Lundi de Pentecote";
      END IF;

      SELECT cfe.id
        FROM epi.jour_ferie cfe
       WHERE cfe.nom = JourFerie
        INTO idFerie;

      RETURN idFerie;
   END;

-- calcul du dimanche de pâques
  
DROP FUNCTION IF EXISTS epi.Paques;
CREATE FUNCTION epi.`Paques`(Annee Int) RETURNS date DETERMINISTIC
BEGIN
    DECLARE t, s, a, i, b, c, d, e, result INT;
    DECLARE paques                         DATE;
    SET t      = Annee - 1900;
    SET s      = Floor(t / 4);
    SET a      = mod(t,19);
    SET i      = a * 11;
    SET b      = mod(i,30);
    SET c      = mod(t + s,7);
    SET d      = mod(b, 7);
    SET i      = 6 + c - d;
    SET e     = mod(i, 7);
    SET i      = 49 - b - e;

    IF ((i = 15 OR i = 16) OR (e = 6 AND (i = 17 OR i = 18)))
    THEN
      SET i = i + 7;
    ELSE
      SET i = i;
    END IF;

    IF (b <= 24)
    THEN
      SET result = 110 - b - e;
    ELSE
      SET result = i + 89;
    END IF;

    IF (mod(t, 4) = 0)
    THEN
      SET result = result + 1;
    END IF;

    SET paques = ADDDATE(MAKEDATE(Annee, 1), result);
    RETURN paques;
  END;
