CREATE DATABASE Sustentabilidade;

USE Sustentabilidade;

CREATE TABLE Pessoa(
	id int primary key auto_increment,
    nome varchar(255) not null,
    idade smallint not null,
    email varchar(255) unique not null,
    senha varchar(100) not null
);

CREATE TABLE Usuario(
	id int primary key auto_increment,
    longitude decimal(9,6) not null,
    latitude decimal(9,6) not null,
    pessoa_id int not null,
    constraint fk_pessoa_id
    foreign key (pessoa_id) references Pessoa(id)
);

CREATE TABLE Empresa(
	cnpj varchar(14) primary key,
    nome varchar(255) not null,
    email varchar(255) unique not null
);

CREATE TABLE Funcionario(
	id int primary key auto_increment,
    cargo varchar(100) not null,
    pessoa_id int not null,
    empresa_cnpj varchar(14) not null,
    constraint fk_pessoa_funcionario_id
    foreign key (pessoa_id) references Pessoa(id),
    constraint fk_empresa_funcionario_cnpj
    foreign key (empresa_cnpj) references Empresa(cnpj)
);

CREATE TABLE Ponto_de_coleta(
	id int primary key auto_increment,
    nome varchar(255) not null,
	longitude decimal(9,6) not null,
    latitude decimal(9,6) not null,
	empresa_cnpj varchar(14) not null,
	constraint fk_empresa_ponto_cnpj
    foreign key (empresa_cnpj) references Empresa(cnpj)
);

CREATE TABLE Material(
	id int primary key auto_increment,
    nome varchar(255) not null,
    ponto_coleta_id int,
	constraint fk_ponto_coleta
    foreign key (ponto_coleta_id) references Ponto_de_coleta(id)
);

CREATE TABLE Entrega(
	id int primary key auto_increment,
    status ENUM('Concluída', 'Cancelada') NOT NULL,
    quantidade_itens int not null,
    data_entrega datetime not null,
    usuario_id int not null,
    ponto_coleta_id int not null,
    funcionario_id int not null,
    constraint fk_usuario_entrega
    foreign key (usuario_id) references Usuario(id),
	constraint fk_ponto_coleta_entrega
    foreign key (ponto_coleta_id) references Ponto_de_coleta(id),
	constraint fk_funcionario_entrega
    foreign key (funcionario_id) references Funcionario(id)
);

CREATE TABLE Produto_reciclavel(
	id int primary key auto_increment,
    descricao varchar(255) not null,
    material_id int not null,
    constraint fk_material_id
    foreign key (material_id) references Material(id)
);

CREATE TABLE Item_entregue(
	id int primary key auto_increment,
    quantidade int not null,
    produto_id int not null,
    entrega_id int not null,
    constraint fk_produto_id
    foreign key (produto_id) references Produto_reciclavel(id),
    constraint fk_entrega_id
    foreign key (entrega_id) references Entrega(id)
);

CREATE TABLE Beneficio(
	id int primary key auto_increment,
    data_entregue date not null,
    valor decimal(10,2) not null,
    entrega_id int not null,
    constraint fk_entrega_beneficio_id
    foreign key (entrega_id) references Entrega(id)
);

INSERT INTO Pessoa (nome, idade, email, senha) VALUES
('Carlos Silva', 30, 'carlos@email.com', '123456'),
('Mariana Souza', 25, 'mariana@email.com', '123456'),
('Pedro Lima', 40, 'pedro@email.com', '123456'),
('Ana Costa', 35, 'ana@email.com', '123456'),
('Lucas Rocha', 28, 'lucas@email.com', '123456');

INSERT INTO Usuario (longitude, latitude, pessoa_id) VALUES
(-46.633309, -23.550520, 1),
(-46.634000, -23.551000, 2),
(-46.635000, -23.552000, 3),
(-46.636000, -23.553000, 4),
(-46.637000, -23.554000, 5);

INSERT INTO Empresa (cnpj, nome, email) VALUES
('11111111000101', 'ReciclaTech', 'contato@reciclatech.com'),
('22222222000102', 'EcoPonto', 'contato@ecoponto.com'),
('33333333000103', 'VerdeColeta', 'contato@verdecoleta.com'),
('44444444000104', 'Reciclar+', 'contato@reciclar.com'),
('55555555000105', 'EcoVida', 'contato@ecovida.com');

INSERT INTO Funcionario (cargo, pessoa_id, empresa_cnpj) VALUES
('Gerente', 1, '11111111000101'),
('Coletor', 2, '22222222000102'),
('Supervisor', 3, '33333333000103'),
('Analista', 4, '44444444000104'),
('Auxiliar', 5, '55555555000105');

INSERT INTO Ponto_de_coleta (nome, longitude, latitude, empresa_cnpj) VALUES
('Ponto Centro', -46.633000, -23.550000, '11111111000101'),
('Ponto Norte', -46.634000, -23.551500, '22222222000102'),
('Ponto Sul', -46.635500, -23.552500, '33333333000103'),
('Ponto Leste', -46.636500, -23.553500, '44444444000104'),
('Ponto Oeste', -46.637500, -23.554500, '55555555000105');

INSERT INTO Material (nome, ponto_coleta_id) VALUES
('Plástico', 1),
('Papel', 2),
('Metal', 3),
('Vidro', 4),
('Eletrônico', 5);

INSERT INTO Produto_reciclavel (descricao, material_id) VALUES
('Garrafas PET', 1),
('Jornais Velhos', 2),
('Latas de Alumínio', 3),
('Vidros de Garrafa', 4),
('Celulares Antigos', 5);

INSERT INTO Entrega (status, quantidade_itens, data_entrega, usuario_id, ponto_coleta_id, funcionario_id) VALUES
('Concluída', 10, '2025-09-25 10:00:00', 1, 1, 1),
('Concluída', 8, '2025-09-25 11:00:00', 2, 2, 2),
('Cancelada', 5, '2025-09-25 12:00:00', 3, 3, 3),
('Concluída', 12, '2025-09-25 13:00:00', 4, 4, 4),
('Concluída', 7, '2025-09-25 14:00:00', 5, 5, 5);

INSERT INTO Item_entregue (quantidade, produto_id, entrega_id) VALUES
(5, 1, 1),
(3, 2, 2),
(2, 3, 3),
(6, 4, 4),
(4, 5, 5);

INSERT INTO Beneficio (data_entregue, valor, entrega_id) VALUES
('2025-09-25', 50.00, 1),
('2025-09-25', 30.00, 2),
('2025-09-25', 20.00, 3),
('2025-09-25', 60.00, 4),
('2025-09-25', 35.00, 5);

SELECT * FROM Pessoa;

UPDATE Pessoa
SET idade = 31
WHERE id = 1;

SELECT u.id, u.longitude, u.latitude, p.nome, p.email
FROM Usuario u
JOIN Pessoa p ON u.pessoa_id = p.id;

UPDATE Usuario
SET longitude = -46.638000, latitude = -23.555000
WHERE id = 1;

SELECT * FROM Empresa;

UPDATE Empresa
SET email = 'novoemail@reciclatech.com'
WHERE cnpj = '11111111000101';

SELECT f.id, f.cargo, p.nome AS nome_pessoa, e.nome AS nome_empresa
FROM Funcionario f
JOIN Pessoa p ON f.pessoa_id = p.id
JOIN Empresa e ON f.empresa_cnpj = e.cnpj;

UPDATE Funcionario
SET cargo = 'Supervisor Sênior'
WHERE id = 2;

SELECT pc.id, pc.nome AS ponto, e.nome AS empresa
FROM Ponto_de_coleta pc
JOIN Empresa e ON pc.empresa_cnpj = e.cnpj;

UPDATE Ponto_de_coleta
SET nome = 'Ponto Central'
WHERE id = 1;

SELECT m.id, m.nome AS material, pc.nome AS ponto_coleta
FROM Material m
JOIN Ponto_de_coleta pc ON m.ponto_coleta_id = pc.id;

UPDATE Material
SET nome = 'Plástico PET'
WHERE id = 1;


SELECT pr.id, pr.descricao, m.nome AS material
FROM Produto_reciclavel pr
JOIN Material m ON pr.material_id = m.id;

UPDATE Produto_reciclavel
SET descricao = 'Garrafas PET Grandes'
WHERE id = 1;

SELECT ie.id, ie.quantidade, pr.descricao AS produto, e.id AS entrega
FROM Item_entregue ie
JOIN Produto_reciclavel pr ON ie.produto_id = pr.id
JOIN Entrega e ON ie.entrega_id = e.id;

UPDATE Item_entregue
SET quantidade = 10
WHERE id = 3;


SELECT e.id, e.status, e.quantidade_itens, u.id AS usuario, pc.nome AS ponto, f.cargo AS funcionario
FROM Entrega e
JOIN Usuario u ON e.usuario_id = u.id
JOIN Ponto_de_coleta pc ON e.ponto_coleta_id = pc.id
JOIN Funcionario f ON e.funcionario_id = f.id;

UPDATE Entrega
SET status = 'Concluída'
WHERE id = 3;

SELECT b.id, b.data_entregue, b.valor, e.id AS entrega
FROM Beneficio b
JOIN Entrega e ON b.entrega_id = e.id;

UPDATE Beneficio
SET valor = 55.00
WHERE id = 1;



