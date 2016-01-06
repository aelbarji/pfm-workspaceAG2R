use epi
DROP EVENT IF EXISTS Checklist_Update_Retard;
CREATE EVENT Checklist_Update_Retard
    ON SCHEDULE  EVERY 1 MINUTE
    DO
    Update epi.checklist_current ccu 
inner join epi.checklist_horaire cho on ccu.id_horaire = cho.id
inner join epi.checklist_base_soustache cbs on ccu.sous_tache = cbs.id
set ccu.status = 4, ccu.heure = now(),ccu.heure_stamp = UNIX_TIMESTAMP(now())*1000,ccu.heure_gmt=UTC_TIMESTAMP()
where (ccu.status = 6 and now() >=  date_add(ccu.jour,interval mod(time_to_sec(ADDTIME(cho.horaire, cbs.decalage)),86400) second) ) 
          or (ccu.status = 4 
                and timediff(now() ,heure) >= (select valeur from parametre.parametre where LIBELLE="CHECKLIST_RETARD_1")
                and timediff(now() ,heure) <= (select addtime(valeur,"00:02:00") from parametre.parametre where LIBELLE="CHECKLIST_RETARD_1")
          )or (ccu.status = 4 
                and timediff(now() ,heure) >= (select valeur from parametre.parametre where LIBELLE="CHECKLIST_RETARD_2")
                and timediff(now() ,heure) <= (select addtime(valeur,"00:02:00") from parametre.parametre where LIBELLE="CHECKLIST_RETARD_2")
          )