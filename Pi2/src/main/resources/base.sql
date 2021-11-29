DROP DATABASE IF EXISTS PI2;

create database pi2;

create table pi2.clientes(
    idCliente integer PRIMARY KEY auto_increment,
    nomeCompleto VARCHAR(250),
    CPF varchar(11),
    nascimento date,
    endereco varchar(250),
    cidade varchar(250),
    bairro varchar(250),
    cep varchar(8),
    contato varchar(13),
    email varchar(250),
    observacao varchar(250),
    sexo char(1)
);


create table pi2.produtos(
    idProduto int primary key auto_increment,
    cdFiscal varchar(50),
    marca varchar(100),
    nome varchar(150),
    tipo varchar(250),
    qtdEstoque float,
    validade DATE,
    valor float,
    descricao varchar(250)
);

create table pi2.usuario(
    idUsuario int PRIMARY KEY AUTO_INCREMENT,
    loginUsuario varchar(250),
    senha varchar(250),
    desabilitado boolean
);


create table pi2.vendas(
    idVenda int PRIMARY KEY AUTO_INCREMENT,
    idCliente int ,
    data_venda date,
    total float,
    quantidadeItens int,
    idUsuario int,
	FOREIGN KEY (idCliente) REFERENCES pi2.clientes(idCliente),
	FOREIGN KEY (idUsuario) REFERENCES pi2.usuario(idUsuario)
);

create table pi2.prod_vendas(
    idProdVenda int primary key auto_increment,
    idVenda int,
    idProduto int,
    quantidade float,
    preco float,
    total float,
    FOREIGN KEY (idProduto) REFERENCES pi2.produtos(idProduto),
    FOREIGN KEY (idVenda) REFERENCES pi2.vendas(idVenda)
);



INSERT INTO `pi2`.`clientes` (`nomeCompleto`, `CPF`, `nascimento`, `endereco`, `cidade`, `cep`, `contato`, `email`, `observacao`, `sexo`,`bairro`) VALUES ('Paulo Belfi', '44700488875', '1996-12-12', 'Casa', 'SP', '04774030', 'Contato', 'email@paulo.com', 'Sim', 'M','bairro');
INSERT INTO `pi2`.`produtos` (`cdFiscal`, `marca`, `nome`, `tipo`, `qtdEstoque`, `validade`, `valor`,`descricao`) VALUES ('1000', 'Omo', 'Sabão em po', 'Sabão', '1000', '1996-12-12', '1999','description');
INSERT INTO `pi2`.`usuario` (`loginUsuario`, `senha`, `desabilitado`) VALUES ('paulo', '121296', '0');