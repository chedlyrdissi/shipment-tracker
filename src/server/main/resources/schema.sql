DROP TABLE IF EXISTS package_update;
DROP TABLE IF EXISTS package;
DROP TABLE IF EXISTS provider;
DROP EXTENSION IF EXISTS pgcrypto;
DROP TYPE IF EXISTS package_status;

CREATE EXTENSION pgcrypto;

CREATE TABLE provider(
  provider_name varchar(30) NOT NULL,
  password varchar(100) NOT NULL,
  salt varchar(100) NOT NULL,
  token TEXT DEFAULT md5(random()::text) NOT NULL UNIQUE,
  PRIMARY KEY (provider_name)
);

CREATE TYPE package_status AS ENUM ('PENDING', 'DELIVERED', 'CANCELED');

CREATE TABLE package(
  package_id SERIAL NOT NULL,
  provider_name varchar(30) NOT NULL,
  source varchar(100) NOT NULL,
  destination varchar(100) NOT NULL,
  status VARCHAR(10) NOT NULL DEFAULT 'PENDING',
  PRIMARY KEY (package_id),
  FOREIGN KEY (provider_name) REFERENCES provider(provider_name) ON DELETE CASCADE
);

CREATE TABLE package_update(
  package_id INT NOT NULL,
  update_date TIMESTAMP DEFAULT NOW() NOT NULL,
  notes TEXT,
  PRIMARY KEY (package_id, update_date),
  FOREIGN KEY (package_id) REFERENCES package(package_id) ON DELETE CASCADE
);

CREATE OR REPLACE FUNCTION init_package_update()
  RETURNS TRIGGER
  LANGUAGE PLPGSQL
  AS
$$
BEGIN
  INSERT INTO package_update(package_id, update_date, notes)
    VALUES  (NEW.package_id, NOW(), 'Status -> CREATED, Status -> PENDING');
  RETURN NEW;
END;
$$;

CREATE OR REPLACE FUNCTION package_status_update()
  RETURNS TRIGGER
  LANGUAGE PLPGSQL
  AS
$$
BEGIN
	IF NEW.status != OLD.status
	THEN
		INSERT INTO package_update(package_id, update_date, notes)
			VALUES (NEW.package_id, NOW(), CONCAT('Status -> ', NEW.status));
	END IF;
  	RETURN NEW;
END
$$;

CREATE TRIGGER package_created
  AFTER INSERT
  ON package
  FOR ROW
  EXECUTE PROCEDURE init_package_update();

CREATE TRIGGER package_status_updated
  AFTER UPDATE
  ON package
  FOR ROW
  EXECUTE PROCEDURE package_status_update();

CREATE INDEX package_update_index
  ON package_update (package_id);
