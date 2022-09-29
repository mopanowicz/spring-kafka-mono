CREATE ROLE test
    WITH LOGIN NOSUPERUSER CREATEDB NOCREATEROLE INHERIT NOREPLICATION CONNECTION LIMIT -1
    PASSWORD 'test123';

CREATE DATABASE test
    WITH OWNER=test ENCODING = 'UTF8' CONNECTION LIMIT = -1;
