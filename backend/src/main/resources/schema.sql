CREATE TABLE task (
  id identity not null primary key,
  title varchar(100) not null,
  status int default 0,
  description varchar(100),
  dueDate date not null,
  completionDate date
);

