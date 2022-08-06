CREATE TABLE IF NOT EXISTS product (
    id int auto_increment primary key,
    name varchar(255) not null,
    is_deleted boolean not null default 0,
    created_date timestamp default current_timestamp,
    modified_date timestamp default current_timestamp on update current_timestamp
);

CREATE TABLE IF NOT EXISTS client (
    id int auto_increment primary key,
    name varchar(255) not null,
    created_date timestamp default current_timestamp,
    modified_date timestamp default current_timestamp on update current_timestamp
);