CREATE TABLE pessoa (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    ativo BIT(1) NOT NULL,
    logradouro VARCHAR(50) NOT NULL,
    numero VARCHAR(6) NOT NULL,
    complemento VARCHAR(50),
    bairro VARCHAR(20) NOT NULL,
    cidade VARCHAR(20) NOT NULL,
    estado VARCHAR(20) NOT NULL,
    cep VARCHAR(9) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cidade, estado, cep) values
("Igor", true, "Avenida Odilon Araújo", "1760", "Bl B Apto 104", "Cidade Nova", "Teresina", "Piauí", "64017-565"),
("Emanuelle", true, "Avenida Odilon Araújo", "1760", "Bl B Apto 104", "Cidade Nova", "Teresina", "Piauí", "64017-565"),
("Artur", true, "Avenida Odilon Araújo", "1760", "Bl B Apto 104", "Cidade Nova", "Teresina", "Piauí", "64017-565"),
("Odésia", true, "Quadra 3 Setor A", "39", "Mocambinho II", "Mocambinho", "Teresina", "Piauí", "64010-010"),
("João", true, "Rua Arlindo Nogueira", "200", null, "Centro", "Teresina", "Piauí", "64000-000"),
("Maria", true, "Avenida Barão de Castelo Branco", "402", null, "Cristo Rei", "Teresina", "Piauí", "64017-565"),
("Fenelon", true, "Avenida Miguel Rosa", "4031", null, "Mafuá", "Teresina", "Piauí", "64000-000");
