create table contract (
    id bigint constraint pk_contract primary key,
    title varchar(100) not null,
    dat_start date not null,
    dat_end date,
    contract_value decimal not null,
    description varchar(1000) not null
);

create sequence seq_contract
    increment 1
        minvalue 1
        maxvalue 9223372036854775807
        start 1
        cache 1;






