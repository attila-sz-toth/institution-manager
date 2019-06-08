CREATE TABLE institutionm.roles (
  id   INTEGER      NOT NULL,
  role VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE institutionm.users (
  id       INTEGER      NOT NULL,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255),
  role_id  INTEGER      NOT NULL REFERENCES institutionm.roles (id),
  PRIMARY KEY (id)
);