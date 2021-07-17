psql -c "DROP DATABASE IF EXISTS shipmenttracker"
psql -c "CREATE DATABASE shipmenttracker"
psql shipmenttracker < ./db/schema.sql
psql shipmenttracker < ./db/seed.sql
