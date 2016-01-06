use epi;

ALTER TABLE `environnement_type` ADD COLUMN `principal` INT(11) NOT NULL DEFAULT '0' AFTER `type`;

UPDATE `environnement_type` SET `principal`='1' WHERE `id`='1';

