--<ScriptOptions statementTerminator=";"/>

ALTER TABLE "BBRA"."BBR_CONSIGNES" DROP CONSTRAINT "PK_ID";

DROP INDEX "BBRA"."PK_ID";

DROP INDEX "BBRA"."SYS_C002817_2";

DROP TABLE "BBRA"."BBR_CONSIGNES";

CREATE TABLE "BBRA"."BBR_CONSIGNES" (
		"BBRORIGINE" VARCHAR2(50) NOT NULL,
		"BBRCOMPOSANT" VARCHAR2(100) NOT NULL,
		"BBRTYPE" VARCHAR2(60),
		"BBRPRIORITY" NUMBER NOT NULL,
		"LOCALISATION" VARCHAR2(400),
		"ID" NUMBER(22 , 0) NOT NULL
	);

CREATE UNIQUE INDEX "BBRA"."PK_ID" ON "BBRA"."BBR_CONSIGNES" ("ID" ASC);

CREATE INDEX "BBRA"."SYS_C002817_2" ON "BBRA"."BBR_CONSIGNES" ("BBRCOMPOSANT" ASC, "BBRORIGINE" ASC);

ALTER TABLE "BBRA"."BBR_CONSIGNES" ADD CONSTRAINT "PK_ID" PRIMARY KEY ("ID");

CREATE SEQUENCE  "BBRA"."S_BBRCONSIGNE_ID"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 201 CACHE 20 NOORDER  NOCYCLE ;