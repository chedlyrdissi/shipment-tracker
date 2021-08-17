-- CREATE PROVIDER
WITH newsalt AS (SELECT gen_salt('bf', 8) AS salt)
INSERT INTO provider(provider_name, password, salt)
  VALUES  (<provider name>, crypt(<pw>, (SELECT salt from newsalt)), (SELECT salt from newsalt))

-- LOGIN
SELECT provider_name, token
	FROM provider
	WHERE provider_name = <provider name>
		AND password == crypt(<pw>, salt);

-- CHECK AUTHENTICATED
SELECT COUNT(*) = 1
	FROM provider
	WHERE token = <token>;


-- CREATE PACKAGE
INSERT INTO package(provider_name, source, destination)
  VALUES (<provider_name>, <source>, <destination>);

-- UPDATE PACKAGE STATUS
UPDATE package
	SET status = <status>::package_status;

-- CREATE PACKAGE UPDATE
INSERT INTO package_update(package_id, notes)
	VALUES (<package_id>, <notes>);

-- GET PACKAGE LIST
SELECT package_id, provider_name, source, destination, status
	FROM package
	WHERE provider_name == <provider name>;

-- GET PACKAGE DETAILS
SELECT package_id, provider_name, source, destination, status
	FROM package
	WHERE package_id == <package id>;

-- GET PACKAGE UPDATES
SELECT package_id, update_date, notes
	FROM package_update
	WHERE package_id = <package_id>
