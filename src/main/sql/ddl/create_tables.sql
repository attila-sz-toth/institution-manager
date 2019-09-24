CREATE TABLE IF NOT EXISTS institutionm.roles (
  id   INTEGER      NOT NULL,
  role VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

create table IF NOT EXISTS institutionm.institution_types (
  id   INTEGER NOT NULL,
  type varchar(255),
  primary key (id)
);

create table IF NOT EXISTS institutionm.care_types (
  id        integer not null,
  care_type varchar(255),
  primary key (id)
);

CREATE TABLE IF NOT EXISTS institutionm.users (
  id       INTEGER      NOT NULL,
  username VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255),
  role_id  INTEGER      NOT NULL REFERENCES institutionm.roles (id),
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS institutionm.institutions (
  id      INTEGER NOT NULL,
  name    varchar(255),
  type_id integer not null references institutionm.institution_types (id),
  primary key (id)
);

create table IF NOT EXISTS institutionm.institutions_to_types (
  institution_id      integer not null references institutionm.institutions (id),
  institution_type_id integer not null references institutionm.institution_types (id),
  PRIMARY KEY (institution_id, institution_type_id)
);

create table IF NOT EXISTS institutionm.institutions_to_users (
  institution_id integer not null references institutionm.institutions (id),
  user_id        integer not null references institutionm.users (id),
  PRIMARY KEY (institution_id, user_id)
);
