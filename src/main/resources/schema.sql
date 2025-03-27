drop table if exists user_cred;
create table user_cred (
    username varchar(25) primary key,
    password varchar(25),
    email varchar(255)
);

drop table if exists location;
create table location (
    lid varchar(100) primary key,
    state varchar(100),
    city varchar(100)
);

drop table if exists customer;
create table customer (
    cid int AUTO_INCREMENT primary key,
    username varchar(255),
    lid varchar(100) default null,
    fName varchar(255) default null,
    lName varchar(255) default null
);

drop table if exists incident;
create table incident (
    iid varchar(100) primary key,
    lid varchar(100) default null,
    from_street varchar(100),
    to_street varchar(100),
    incident_type varchar(100)
);