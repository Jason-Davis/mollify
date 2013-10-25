UPDATE parameter SET value = '2_2' WHERE name = 'version';
INSERT INTO parameter (name, value) VALUES ('old_user_pw', '1');

ALTER TABLE user ADD lang char(2) NULL;

CREATE TABLE user_auth (
  id INTEGER PRIMARY KEY,
  type varchar(8) NULL,
  salt char(128) NOT NULL,
  hash char(128) NOT NULL,
  a1hash char(128) NULL
);

INSERT INTO user_auth (id, type, salt, hash) SELECT id, auth, '-' as salt, '-' as hash FROM user;