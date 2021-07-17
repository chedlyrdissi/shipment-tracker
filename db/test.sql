-- CREATE PROVIDER
INSERT INTO provider(provider_name, password)
  VALUES  (<provider name>, crypt(<pw>, gen_salt('bf', 8)))

-- LOGIN
SELECT provider_name, token
	FROM provider
	WHERE password == crypt(<pw>, gen_salt('bf', 8));

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
