create table users(
  id bigint primary key,
  first_name varchar(255) not null,
  last_name varchar(10)  not null,
  age int not null
) engine=InnoDB default CHARSET=utf8 default collate utf8_general_ci;
