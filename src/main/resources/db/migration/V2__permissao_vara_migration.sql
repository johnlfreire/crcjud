CREATE TABLE  IF NOT EXISTS entidade (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
	email VARCHAR(50) NOT NULL,
	telefone VARCHAR(20) NOT NULL,
	endereco VARCHAR(100) NOT NULL,
    id_cidade BIGINT NOT NULL,
    FOREIGN KEY (id_cidade) REFERENCES cidade(id)
);

CREATE TABLE  IF NOT EXISTS vara (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
	id_cidade BIGINT NOT NULL,
    id_entidade BIGINT NOT NULL,
    FOREIGN KEY (id_cidade) REFERENCES cidade(id),
	FOREIGN KEY (id_entidade) REFERENCES vara(id)
);
