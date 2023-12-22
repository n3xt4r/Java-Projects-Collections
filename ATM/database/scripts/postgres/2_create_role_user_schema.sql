--psql -U postgres -d atm
create user learnjava_my_atm password 'atm123';
create schema learnjava authorization learnjava_my_atm;
alter user learnjava_my_atm set search_path to learnjava;