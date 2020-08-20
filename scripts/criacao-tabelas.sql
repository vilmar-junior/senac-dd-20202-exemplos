CREATE TABLE public."ENDERECO"
(
    "ID" numeric NOT NULL,
    "RUA" character varying(400) NOT NULL,
    "NUMERO" character varying(100) NOT NULL,
    "ESTADO" character(2) NOT NULL,
    "CIDADE" character varying(200) NOT NULL,
    PRIMARY KEY ("ID")
);

CREATE TABLE public."CLIENTE"
(
    "ID" numeric NOT NULL,
    "NOME" character varying(200) NOT NULL,
    "CPF" character varying(11) NOT NULL,
    "ID_ENDERECO" numeric NOT NULL,
    PRIMARY KEY ("ID"),
    CONSTRAINT "CONSTRAINT_CPF" UNIQUE ("CPF"),
    CONSTRAINT "FK_CLIENTE_ENDERECO" FOREIGN KEY ("ID_ENDERECO")
        REFERENCES public."ENDERECO" ("ID") MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE public."TELEFONE"
(
    "ID" numeric NOT NULL,
    "DDD" character varying(3) NOT NULL,
    "NUMERO" character varying(9) NOT NULL,
    "MOVEL" numeric(1) NOT NULL,
    "IMEI" character varying(20) NOT NULL,
    "ID_CLIENTE" numeric,
    PRIMARY KEY ("ID")
);