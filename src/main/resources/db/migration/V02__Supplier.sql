create table supplier (
    id bigint constraint pk_supplier primary key,
    name varchar(500) not null,
    cnpj varchar(14) unique not null,
    phone varchar(11),
    email varchar(256) not null
);

create sequence seq_supplier
    increment 1
        minvalue 1
        maxvalue 9223372036854775807
        start 1
        cache 1;

alter table contract add column supplier_id bigint constraint supplier_fk REFERENCES supplier(id);
