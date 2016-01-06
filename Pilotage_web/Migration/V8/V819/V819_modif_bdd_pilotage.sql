use epi;

INSERT INTO incidents_type(id, type) VALUES(7, 'Alerte');

UPDATE incidents SET type=1 WHERE type=5;
UPDATE incidents SET type=7 WHERE type=6;

DELETE FROM incidents_type WHERE id=4 || id=5 || id=6;

ALTER TABLE `incidents_type` ADD COLUMN `description` VARCHAR(50) NOT NULL;
ALTER TABLE `incidents_type` ADD COLUMN `impact` INT NOT NULL;
ALTER TABLE `incidents_type` ADD COLUMN `titre_bilan` VARCHAR(50) NOT NULL;

UPDATE incidents_type SET description='service dégradé' WHERE type='Incident';
UPDATE incidents_type SET description='coupure de service' WHERE type='Critique';
UPDATE incidents_type SET description='Information' WHERE type='Information';
UPDATE incidents_type SET description='sans impact service' WHERE type='Alerte';

UPDATE incidents_type SET titre_bilan='Incidents' WHERE type='Incident';
UPDATE incidents_type SET titre_bilan='Informations' WHERE type='Information';
UPDATE incidents_type SET titre_bilan='Incidents avec coupure de service' WHERE type='Critique';
UPDATE incidents_type SET titre_bilan='Alertes' WHERE type='Alerte';

UPDATE incidents_type SET impact=2 WHERE type='Incident';
UPDATE incidents_type SET impact=4 WHERE type='Information';
UPDATE incidents_type SET impact=1 WHERE type='Critique';
UPDATE incidents_type SET impact=3 WHERE type='Alerte';