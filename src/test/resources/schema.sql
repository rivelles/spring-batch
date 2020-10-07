CREATE TABLE cartao_credito (
  numero_cartao_credito INT primary key,
  cliente INT NOT NULL
);

CREATE TABLE transacao (
  id INT primary key,
  numero_cartao_credito INT NOT NULL,
  descricao VARCHAR(255) NOT NULL,
  valor DOUBLE NOT NULL,
  data DATE NOT NULL
);