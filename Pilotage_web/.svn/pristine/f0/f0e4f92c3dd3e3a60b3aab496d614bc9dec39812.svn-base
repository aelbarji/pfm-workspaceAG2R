DROP PROCEDURE IF EXISTS epi.SelectIndicateurMeteo;
CREATE PROCEDURE epi.`SelectIndicateurMeteo`(IN groupeMeteo INT, IN dateMeteo Char(10), IN datetime Char(19), IN heure Char(8))
BEGIN
	SELECT mi.id AS id, mm.id AS meteo, ms.id AS service, me.id AS environnement, mt.id AS typeIndic, mif.format AS formatIndic, mp.etat_desire AS etat_desire, mp.id AS plage, mi.heure_debut AS heure_debut, mi.heure_fin AS heure_fin, mi.heure_debut AS indic_heure
			FROM meteo_groupemeteo_meteo mgm
			INNER JOIN meteo_meteo mm ON mgm.id_meteo = mm.id 
			INNER JOIN meteo_service_meteo msm ON mm.id = msm.id_meteo
			INNER JOIN meteo_service ms ON msm.id_service = ms.id
			INNER JOIN meteo_indicateur mi ON ms.id = mi.id_service
			INNER JOIN meteo_environnement me ON mi.id_environnement = me.id
			INNER JOIN meteo_typeindicateur mt ON mi.id_typeIndicateur = mt.id
			INNER JOIN meteo_indicateur_format mif ON mt.format = mif.id
			INNER JOIN meteo_plagefonctionnement mp ON mi.id = mp.id_indicateur
      INNER JOIN meteo_plagefonct_jour mpj ON mp.id = mpj.id_plageFonct
			WHERE mgm.id_groupeMeteo = groupeMeteo
			AND datetime>=mgm.date_creation AND (mgm.date_suppression is null OR datetime<=mgm.date_suppression)
			AND datetime>=msm.date_creation AND (msm.date_suppression is null OR datetime<=msm.date_suppression)
			AND datetime>=mi.date_creation AND (mi.date_suppression is null OR datetime<=mi.date_suppression)
      AND datetime>=mp.date_creation AND (mp.date_suppression is null OR datetime<mp.date_suppression)
      AND mpj.jour = DATE_FORMAT(datetime, '%w')
			AND ((mpj.ferie=1 AND epi.IdFerie(dateMeteo,0) is not null) OR (ferie=0 AND epi.IdFerie(dateMeteo,0) is null) OR (ferie=1 AND epi.IdFerie(dateMeteo,0) is null))
			ORDER BY mm.nom_meteo ASC, ms.service ASC, me.id ASC;
END;
