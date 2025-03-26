drop table if exists customer;
create table customer (
    cid int AUTO_INCREMENT primary key,
    username varchar(255),
    fName varchar(255) default null,
    lName varchar(255) default null
);

drop table if exists user_cred;
create table user_cred (
    username varchar(25),
    password varchar(25),
    email varchar(255),
    city VARCHAR(100)
);

drop table if exists incident;
create table incident (
    iid varchar(255) primary key,
    from_street varchar(255),
    to_street varchar(255),
    incident_type varchar(255)
);