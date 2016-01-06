use epi;

CREATE TABLE `checklist_consigne_documents` AS 
  (SELECT checklist_consignes.document_consigne document, 
          checklist_consignes.id id_consigne
   FROM   checklist_consignes
          WHERE checklist_consignes.document_consigne != "" 
			AND checklist_consignes.document_consigne !=  "undefined");

ALTER TABLE `checklist_consigne_documents` 
ADD COLUMN `id` INT(11) NOT NULL AUTO_INCREMENT FIRST,
ADD PRIMARY KEY (`id`);

ALTER TABLE `checklist_consignes` DROP COLUMN `document_consigne`;






