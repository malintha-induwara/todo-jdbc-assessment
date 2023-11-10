
create database todo;

use todo;

create table users(
     email varchar(35) primary key,
     name varchar(155) not null,
     password text not null);

create table tasks(
     task_id int primary key,
     email varchar(35) not null,
     description text not null,
     dueDate date not null,
     isCompleted tinyint not null,
     foreign key (email) references users(email));

