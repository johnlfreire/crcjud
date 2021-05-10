CREATE TABLE  IF NOT EXISTS usuario (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
	cpf VARCHAR(14) NOT NULL,
    senha VARCHAR(120) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    ativo BOOLEAN DEFAULT true,
	id_cidade BIGINT NOT NULL,
	id_entidade BIGINT NOT NULL,
	id_vara BIGINT NOT NULL,
	FOREIGN KEY (id_cidade) REFERENCES cidade(id),
	FOREIGN KEY (id_entidade) REFERENCES entidade(id),
	FOREIGN KEY (id_vara) REFERENCES vara(id)
);

CREATE TABLE  IF NOT EXISTS perfil (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL
);

CREATE TABLE  IF NOT EXISTS permissao (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
	descricao VARCHAR(100) NOT NULL
);

CREATE TABLE  IF NOT EXISTS usuario_perfil (
    id_usuario BIGINT NOT NULL,
    id_perfil BIGINT NOT NULL,
    PRIMARY KEY (id_usuario, id_perfil),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id),
    FOREIGN KEY (id_perfil) REFERENCES perfil(id)
);

CREATE TABLE  IF NOT EXISTS perfil_permissao (
    id_perfil BIGINT NOT NULL,
    id_permissao BIGINT NOT NULL,
    PRIMARY KEY (id_perfil, id_permissao),
    FOREIGN KEY (id_perfil) REFERENCES perfil(id),
    FOREIGN KEY (id_permissao) REFERENCES permissao(id)
);