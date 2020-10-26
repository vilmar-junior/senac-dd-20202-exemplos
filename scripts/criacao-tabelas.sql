CREATE SCHEMA IF NOT EXISTS EXEMPLOS;

CREATE TABLE EXEMPLOS."ENDERECO"
(
    "ID" numeric NOT NULL,
    "RUA" character varying(400) NOT NULL,
    "NUMERO" character varying(100) NOT NULL,
    "ESTADO" character(2) NOT NULL,
    "CIDADE" character varying(200) NOT NULL,
    PRIMARY KEY ("ID")
);

CREATE TABLE EXEMPLOS."CLIENTE"
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

CREATE TABLE EXEMPLOS."TELEFONE"
(
    "ID" numeric NOT NULL,
    "DDD" character varying(3) NOT NULL,
    "NUMERO" character varying(9) NOT NULL,
    "MOVEL" numeric(1) NOT NULL,
    "IMEI" character varying(20) NOT NULL,
    "ID_CLIENTE" numeric,
    PRIMARY KEY ("ID")
);

------- AULA 11

CREATE TABLE EXEMPLOS.PRODUTO (
  'id' INT NOT NULL AUTO_INCREMENT,
  'nome' VARCHAR(255) NOT NULL,
  'cor'VARCHAR(255) NOT NULL,
  'fabricante' VARCHAR(255) NOT NULL,
  'valor' DOUBLE NULL,
  'peso' DOUBLE NULL,
  'dataCadastro' DATE,
  PRIMARY KEY ('id'),
  UNIQUE INDEX 'id_UNIQUE' ('id' ASC));
  
  INSERT INTO PRODUTO (ID, NOME, COR, FABRICANTE, VALOR, PESO, DATACADASTRO)
  VALUES (1, 'Mouse', 'Preto', 'Logitech', '150', '0.12', '2012-05-10');
  
  INSERT INTO PRODUTO (ID, NOME, COR, FABRICANTE, VALOR, PESO, DATACADASTRO)
  VALUES (2, 'Teclado', 'Verde', 'Logitech', '190', '0.35', '2018-07-02');
  
  INSERT INTO PRODUTO (ID, NOME, COR, FABRICANTE, VALOR, PESO, DATACADASTRO)
  VALUES (3, 'Monitor Wide', 'Preto', 'Samsung', '750', '4.6', '2018-03-10');
  
  INSERT INTO PRODUTO (ID, NOME, COR, FABRICANTE, VALOR, PESO, DATACADASTRO)
  VALUES (4, 'Monitor CRT', 'Vermelho', 'LG', '200', '8.5', '2006-05-10');
  
  INSERT INTO PRODUTO (ID, NOME, COR, FABRICANTE, VALOR, PESO, DATACADASTRO)
  VALUES (5, 'Notebook', 'Preto', 'Dell', '4500', '2.8', '2018-05-10');
  
  INSERT INTO PRODUTO (ID, NOME, COR, FABRICANTE, VALOR, PESO, DATACADASTRO)
  VALUES (6, 'Notebook', 'Azul', 'Acer', '3250', '3.2', '2019-01-10');