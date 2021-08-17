psql -c "DROP DATABASE IF EXISTS shipmenttracker"
psql -c "CREATE DATABASE shipmenttracker"
psql shipmenttracker < ./src/server/main/resources/schema.sql
psql shipmenttracker < ./src/server/main/resources/data.sql
