CREATE TABLE IF NOT EXISTS ROLES (
  ID   INTEGER      NOT NULL,
  ROLE VARCHAR(255) NOT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE IF NOT EXISTS SEX (
  ID  INTEGER      NOT NULL,
  SEX VARCHAR(255) NOT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE IF NOT EXISTS INSTITUTION_TYPES (
  ID   INTEGER NOT NULL,
  TYPE VARCHAR(255),
  PRIMARY KEY (ID)
);

CREATE TABLE IF NOT EXISTS CARE_STATUS (
  ID          INTEGER NOT NULL,
  CARE_STATUS VARCHAR(255),
  PRIMARY KEY (ID)
);

CREATE TABLE IF NOT EXISTS RELATIONSHIPS (
  ID           INTEGER NOT NULL,
  RELATIONSHIP VARCHAR(255),
  PRIMARY KEY (ID)
);

CREATE TABLE IF NOT EXISTS USERS (
  ID       INTEGER      NOT NULL,
  USERNAME VARCHAR(255) NOT NULL UNIQUE,
  PASSWORD VARCHAR(255),
  ROLE_ID  INTEGER      NOT NULL REFERENCES ROLES (ID),
  PRIMARY KEY (ID)
);

CREATE TABLE IF NOT EXISTS INSTITUTIONS (
  ID      INTEGER      NOT NULL,
  NAME    VARCHAR(255) NOT NULL UNIQUE,
  ADDRESS VARCHAR(255) NOT NULL,
  TYPE    VARCHAR(255) NOT NULL,
  PRIMARY KEY (ID)
);


DROP TABLE INSTITUTION_CARE_TYPES;
CREATE TABLE IF NOT EXISTS INSTITUTION_CARE_TYPES (
  ID             INTEGER NOT NULL UNIQUE,
  INSTITUTION_ID INTEGER NOT NULL REFERENCES INSTITUTIONS (ID),
  CARE_TYPE      VARCHAR(255),
  PRIMARY KEY (ID)
);

CREATE TABLE IF NOT EXISTS INSTITUTIONS_TO_USERS (
  INSTITUTION_ID INTEGER NOT NULL REFERENCES INSTITUTIONS (ID),
  USER_ID        INTEGER NOT NULL REFERENCES USERS (ID),
  PRIMARY KEY (INSTITUTION_ID, USER_ID)
);

CREATE TABLE IF NOT EXISTS PERSONAL_DETAILS (
  ID           INTEGER      NOT NULL,
  TITLE        VARCHAR(16),
  FIRST_NAME   VARCHAR(255) NOT NULL,
  MIDDLE_NAME  VARCHAR(255),
  LAST_NAME    VARCHAR(255) NOT NULL,
  MOTHERS_NAME VARCHAR(255) NOT NULL,
  BIRTH_DATE   DATE         NOT NULL,
  BIRTH_NAME   VARCHAR(255) NOT NULL,
  BIRTH_PLACE  VARCHAR(255) NOT NULL,
  SEX          INTEGER      NOT NULL REFERENCES SEX (ID),
  ADDRESS      VARCHAR(255) NOT NULL,
  PHONE_NUMBER VARCHAR(16),
  EMAIL        VARCHAR(255),
  PRIMARY KEY (ID),
  UNIQUE (FIRST_NAME, LAST_NAME, MOTHERS_NAME, BIRTH_DATE)
);

CREATE TABLE IF NOT EXISTS FOSTER_CHILDREN_TO_PARENTS (
  CHILD_ID  INTEGER NOT NULL REFERENCES PERSONAL_DETAILS (ID),
  PARENT_ID INTEGER NOT NULL REFERENCES PERSONAL_DETAILS (ID),
  PRIMARY KEY (CHILD_ID, PARENT_ID)
);

CREATE TABLE IF NOT EXISTS CARE_RECEIVER (
  PERSONAL_DETAILS_ID INTEGER REFERENCES PERSONAL_DETAILS (ID),
  CARE_STATUS         INTEGER REFERENCES CARE_STATUS (ID),
  TAJ                 INTEGER,
  START_OF_CARE       DATE,
  END_OF_CARE         DATE,
  PRIMARY KEY (PERSONAL_DETAILS_ID)
);

CREATE TABLE IF NOT EXISTS INSTITUTIONS_TO_CARE_RECEIVERS (
  INSTITUTION_ID   INTEGER NOT NULL REFERENCES INSTITUTIONS (ID),
  CARE_RECEIVER_ID INTEGER NOT NULL REFERENCES CARE_RECEIVER (PERSONAL_DETAILS_ID),
  PRIMARY KEY (INSTITUTION_ID, CARE_RECEIVER_ID)
);

CREATE TABLE IF NOT EXISTS INSTITUTIONS_TO_FOSTER_PARENTS (
  INSTITUTION_ID   INTEGER NOT NULL REFERENCES INSTITUTIONS (ID),
  FOSTER_PARENT_ID INTEGER NOT NULL REFERENCES PERSONAL_DETAILS (ID),
  PRIMARY KEY (INSTITUTION_ID, FOSTER_PARENT_ID)
);


CREATE TABLE IF NOT EXISTS CARE_RECEIVERS_TO_RELATIVES (
  CARE_RECEIVER_ID INTEGER NOT NULL REFERENCES CARE_RECEIVER (PERSONAL_DETAILS_ID),
  RELATIVE_ID      INTEGER NOT NULL REFERENCES PERSONAL_DETAILS (ID),
  RELATIONSHIP     INTEGER REFERENCES RELATIONSHIPS (ID),
  PRIMARY KEY (CARE_RECEIVER_ID, RELATIVE_ID)
);


