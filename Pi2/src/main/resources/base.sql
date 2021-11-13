DROP DATABASE IF EXISTS PI2;

create database pi2;

create table pi2.clientes(
    idCliente integer PRIMARY KEY auto_increment,
    nomeCompleto VARCHAR(250),
    CPF varchar(11),
    nascimento date,
    endereco varchar(250),
    cidade varchar(250),
    cep varchar(8),
    contato varchar(13),
    email varchar(250),
    observacao varchar(250),
    sexo char(1)
);


create table pi2.produtos(
    idProduto VARCHAR(250) primary key,
    cdFiscal varchar(50),
    marca varchar(100),
    nome varchar(150),
    qtdEstoque float,
    validade DATE,
    valor float
);



create table pi2.vendas(
    idVenda int PRIMARY KEY AUTO_INCREMENT,
    idCliente int ,
    data_venda date,
    total float,
    quantidadeItens int,
	FOREIGN KEY (idCliente) REFERENCES pi2.clientes(idCliente)
);

create table pi2.prod_vendas(
    idVenda int,
    idProduto VARCHAR(250),
    quantidade float,
    valor float,
    valor_total float,
    FOREIGN KEY (idProduto) REFERENCES pi2.produtos(idProduto)
);
