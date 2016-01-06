DROP PROCEDURE IF EXISTS epi.CreateCheckNextDay;
CREATE PROCEDURE epi.`CreateCheckNextDay`()
BEGIN
    DECLARE DateOfDay char(10);
    DECLARE nb int;

    SET DateOfDay = date_format( date_add(now(),INTERVAL 1 DAY ),"%Y-%m-%d");
    SELECT count(*) INTO nb FROM epi.checklist_creation WHERE jour=DateOfDay;
    IF (nb = 0 ) THEN
      INSERT INTO epi.checklist_creation  (epi.checklist_creation.jour,epi.checklist_creation.user) VALUE (DateOfDay, "MySQL");
      CALL epi.CreateCheckJour(DateOfDay);
    END IF;
END;

use epi
DROP EVENT IF EXISTS Checklist_Next_Day;
CREATE EVENT Checklist_Next_Day
    ON SCHEDULE  EVERY 1 Day
    STARTS '2012-09-17 23:50:00'
    DO call epi.CreateCheckNextDay()