DROP TABLE IF EXISTS package_update;
DROP TABLE IF EXISTS package;
DROP TABLE IF EXISTS provider;
DROP EXTENSION IF EXISTS pgcrypto;
CREATE EXTENSION pgcrypto;

CREATE TABLE provider(
  provider_name varchar(30) NOT NULL,
  password varchar(100) NOT NULL,
  token TEXT DEFAULT md5(random()::text) NOT NULL UNIQUE,
  PRIMARY KEY (provider_name)
);

CREATE TYPE package_status AS ENUM ('PENDING', 'DELIVERED', 'CANCELED');

CREATE TABLE package(
  package_id SERIAL NOT NULL,
  provider_name varchar(30) NOT NULL,
  source varchar(100) NOT NULL,
  destination varchar(100) NOT NULL,
  status package_status NOT NULL DEFAULT 'PENDING'::package_status,
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
  INSERT INTO package_update(package_id, update_date) values (NEW.package_id, NOW());
  RETURN NEW;
END;
$$;

CREATE TRIGGER package_created
  AFTER INSERT
  ON package
  FOR ROW
  EXECUTE PROCEDURE init_package_update();
