
    alter table DETALLE_COMBO 
        drop 
        foreign key FK_3vj95c2a811es35ar0pbmgpoq;

    alter table DETALLE_COMBO 
        drop 
        foreign key FK_i0t0w9qcb00t4bkgpbej8odw1;

    alter table DETALLE_PEDIDO 
        drop 
        foreign key FK_5pinfmscua9krx9undl3wn37;

    alter table DETALLE_PEDIDO 
        drop 
        foreign key FK_8vm63ydyjtibs9t54xhbkq6gy;

    alter table PEDIDO 
        drop 
        foreign key FK_824j0frl41cj3nrf9pfxkh017;

    drop table if exists CLIENTE;

    drop table if exists DETALLE_COMBO;

    drop table if exists DETALLE_PEDIDO;

    drop table if exists PEDIDO;

    drop table if exists PRODUCTO;

    create table CLIENTE (
        ID bigint not null auto_increment,
        VERSION bigint not null DEFAULT 0,
        DIRECCION varchar(255) not null,
        EMAIL varchar(255) not null,
        NOMBRE varchar(255) not null,
        TELEFONO varchar(255) not null,
        primary key (ID)
    ) ENGINE=InnoDB;

    create table DETALLE_COMBO (
        ID bigint not null auto_increment,
        VERSION bigint not null DEFAULT 0,
        COMBO_ID bigint not null,
        COMPONENTE_ID bigint not null,
        primary key (ID)
    ) ENGINE=InnoDB;

    create table DETALLE_PEDIDO (
        ID bigint not null auto_increment,
        VERSION bigint not null DEFAULT 0,
        PRECIO_UNITARIO double precision not null,
        PEDIDO_ID bigint not null,
        PRODUCTO_ID bigint not null,
        primary key (ID)
    ) ENGINE=InnoDB;

    create table PEDIDO (
        ID bigint not null auto_increment,
        VERSION bigint not null DEFAULT 0,
        DELIVERY bit not null,
        ESTADO_TYPE integer not null,
        FECHA datetime not null,
        CLIENTE_ID bigint not null,
        primary key (ID)
    ) ENGINE=InnoDB;

    create table PRODUCTO (
        ID bigint not null auto_increment,
        VERSION bigint not null DEFAULT 0,
        DESCRIPCION varchar(255) not null,
        PRECIO double precision not null,
        primary key (ID)
    ) ENGINE=InnoDB;

    alter table CLIENTE 
        add constraint UK_rlghcn1syh86ikddcqcahegu1 unique (EMAIL);

    alter table CLIENTE 
        add constraint UK_fkkpcclimsg8ek6gnb2x9c001 unique (TELEFONO);

    alter table PRODUCTO 
        add constraint UK_dp9w03d5yowyew1w41yxfrfii unique (DESCRIPCION);

    alter table DETALLE_COMBO 
        add constraint FK_3vj95c2a811es35ar0pbmgpoq 
        foreign key (COMBO_ID) 
        references PRODUCTO (ID);

    alter table DETALLE_COMBO 
        add constraint FK_i0t0w9qcb00t4bkgpbej8odw1 
        foreign key (COMPONENTE_ID) 
        references PRODUCTO (ID);

    alter table DETALLE_PEDIDO 
        add constraint FK_5pinfmscua9krx9undl3wn37 
        foreign key (PEDIDO_ID) 
        references PEDIDO (ID);

    alter table DETALLE_PEDIDO 
        add constraint FK_8vm63ydyjtibs9t54xhbkq6gy 
        foreign key (PRODUCTO_ID) 
        references PRODUCTO (ID);

    alter table PEDIDO 
        add constraint FK_824j0frl41cj3nrf9pfxkh017 
        foreign key (CLIENTE_ID) 
        references CLIENTE (ID);
