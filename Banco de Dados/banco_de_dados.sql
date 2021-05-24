create database db_projeto_ensino_livre;

use db_projeto_ensino_livre;

create table tb_usuario
(
	id bigint auto_increment not null primary key,
    nome varchar (255) not null,
    senha varchar (255) not null,
    email varchar (60) not null
);

create table tb_tema
(
	id bigint auto_increment not null primary key,
	nome varchar(50)not null,
	descricao varchar(255),
	qtd_postagem bigint
);

create table tb_postagens 
(
	id bigint auto_increment not null primary key,
    texto varchar(1000),
    data_publicacao datetime not null,
    midia varchar(300),
    localizacao varchar(100),
	tema_id bigint not null,
    usuario_id bigint not null,
    foreign key (usuario_id) references tb_usuario (id),
	foreign key (tema_id) references tb_tema (id)
    
);
