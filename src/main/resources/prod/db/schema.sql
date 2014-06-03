CREATE DATABASE monitoring
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Russian_Russia.1251'
       LC_CTYPE = 'Russian_Russia.1251'
       CONNECTION LIMIT = -1;

CREATE TABLE location
(
  id serial NOT NULL,
  longitude character varying NOT NULL,
  latitude character varying NOT NULL,
  apple_id character varying NOT NULL,
  user_id character varying NOT NULL,
  date timestamp without time zone NOT NULL,
  CONSTRAINT location_id PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE role
(
  id serial NOT NULL,
  role_name character varying(64) NOT NULL,
  description character varying(128) NOT NULL,
  CONSTRAINT role_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE "user"
(
  id serial NOT NULL,
  mail character varying(256) NOT NULL,
  first_name character varying(256) NOT NULL,
  last_name character varying(256) NOT NULL,
  password character varying(256) NOT NULL,
  date_registration time without time zone,
  CONSTRAINT id_master PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

CREATE TABLE user_roles
(
  id serial NOT NULL,
  user_id integer NOT NULL,
  role_id integer NOT NULL,
  CONSTRAINT user_roles_pkey PRIMARY KEY (id),
  CONSTRAINT user_roles_role_id_fkey FOREIGN KEY (role_id)
      REFERENCES role (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT user_roles_user_id_fkey FOREIGN KEY (user_id)
      REFERENCES "user" (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);