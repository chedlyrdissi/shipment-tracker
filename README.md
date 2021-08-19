# ShipmentTracker

## DB
### password
Use crypt() and gen_salt() in queries
Compare :pass to existing hash with:
```sql
select * from accounts where password_hash = crypt(:pass, password_hash);
//(note how the existing hash is used as its own individualized salt)
Create a hash of :password with a great random salt:

insert into accounts (password) values crypt(:password, gen_salt('bf', 8));
//(the 8 is the work factor)
```

### Create Instance
```sh
psql -c "DROP DATABASE IF EXISTS shipmenttracker"
psql -c "CREATE DATABASE shipmenttracker"
psql shipmenttracker < ./src/server/main/resources/schema.sql
psql shipmenttracker < ./src/server/main/resources/data.sql
```

