create table document (
    id bigint constraint pk_document primary key,
    name varchar(50) not null,
    dat_updated timestamp not null,
    version integer not null,
    text text not null
);

create sequence seq_document
    increment 1
        minvalue 1
        maxvalue 9223372036854775807
        start 1
        cache 1;




