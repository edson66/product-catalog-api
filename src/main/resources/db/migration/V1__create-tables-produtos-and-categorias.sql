create table categorias(
    id bigint not null auto_increment,
    nome varchar(100) not null unique,

    primary key(id)
);

create table produtos(
    id bigint not null auto_increment,
    nome varchar(100) not null,
    descricao text,
    valor decimal(10,2) not null,
    estoque int not null,
    categoria_id bigint not null,

    primary key(id),

    constraint fk_produtos_categoria_id
    foreign key (categoria_id)
    references categorias(id)
);