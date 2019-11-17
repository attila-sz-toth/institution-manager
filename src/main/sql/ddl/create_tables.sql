create table institution_manager.roles
(
  id        integer      not null
    constraint roles_pkey
    primary key,
  role_name varchar(255) not null
);

alter table institution_manager.roles
  owner to institutionadmin;

create table institution_manager.institutions
(
  id      integer      not null
    constraint institutions_pkey
    primary key,
  name    varchar(255) not null,
  type    varchar(255) not null,
  address varchar(255) not null
);

alter table institution_manager.institutions
  owner to institutionadmin;

create table institution_manager.users
(
  id             integer      not null
    constraint users_pkey
    primary key,
  username       varchar(255) not null
    constraint users_username_key
    unique,
  password       varchar(255),
  role_id        integer      not null
    constraint users_role_id_fkey
    references institution_manager.roles,
  name           varchar(255),
  institution_id integer
    constraint users_institutions_id_fk
    references institution_manager.institutions
);

alter table institution_manager.users
  owner to institutionadmin;

create unique index institutions_name_uindex
  on institution_manager.institutions (name);

create table institution_manager.personal_details
(
  id           integer      not null
    constraint personal_details_pkey
    primary key,
  first_name   varchar(255) not null,
  last_name    varchar(255) not null,
  mothers_name varchar(255) not null,
  birth_date   date         not null,
  birth_name   varchar(255) not null,
  birth_place  varchar(255) not null,
  sex          varchar(255) not null,
  address      varchar(255) not null,
  phone_number varchar(16),
  email        varchar(255),
  constraint personal_details_first_name_last_name_mothers_name_birth_da_key
  unique (first_name, last_name, mothers_name, birth_date)
);

alter table institution_manager.personal_details
  owner to institutionadmin;

create index idx_personal_details
  on institution_manager.personal_details (first_name, last_name, birth_date, mothers_name);

create table institution_manager.care_receiver
(
  personal_details_id integer not null
    constraint care_receiver_pkey
    primary key
    constraint care_receiver_personal_details_id_fkey
    references institution_manager.personal_details,
  care_status         varchar(255),
  taj                 integer,
  start_of_care       date,
  end_of_care         date,
  institution_id      integer not null
    constraint care_receiver_institutions_id_fk
    references institution_manager.institutions
);

alter table institution_manager.care_receiver
  owner to institutionadmin;

create table institution_manager.institution_care_types
(
  id             integer not null
    constraint institution_care_types_pkey
    primary key,
  institution_id integer not null
    constraint institution_care_types_institution_id_fkey
    references institution_manager.institutions,
  care_type      varchar(255)
);

alter table institution_manager.institution_care_types
  owner to institutionadmin;

create table institution_manager.normative
(
  id             integer    not null
    constraint normative_pk
    primary key,
  institution_id integer    not null
    constraint normative_institutions_id_fk
    references institution_manager.institutions,
  year           varchar(4) not null,
  amount         integer    not null
);

alter table institution_manager.normative
  owner to institutionadmin;

create unique index normative_id_uindex
  on institution_manager.normative (id);