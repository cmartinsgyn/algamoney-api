
CREATE TABLE categorias(
  codigo SERIAL,
  nome VARCHAR(20),
  CONSTRAINT categoria PRIMARY KEY(codigo)
);

INSERT INTO categoria (nome) VALUES ('Lazer');
INSERT INTO categoria (nome) VALUES ('Alimentação');
INSERT INTO categoria (nome) VALUES ('Supermercado');
INSERT INTO categoria (nome) VALUES ('Farmácia');
INSERT INTO categoria (nome) VALUES ('Outros');

