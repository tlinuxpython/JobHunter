drop table if exists job;
drop table if exists jobType;
drop table if exists enterprise;
create table enterprise
(
	ID int auto_increment,
	name varchar(100) not null unique,
	scale varchar(50),
	nature varchar(100) not null,
	industry varchar(100) not null,
	introduction text,
	homePage varchar(100),
	telephone varchar(50),
	address varchar(100),
	primary key(ID)
)ENGINE=InnoDB default charset=utf8;
create table jobType
(
	ID int auto_increment,
	name varchar(100) not null unique,
	primary key(ID)
)ENGINE=InnoDB default charset=utf8;
create table job
(
	ID int auto_increment,
	name varchar(100) not null,
	experience varchar(50),
	nature varchar(50),
	educationRequest varchar(50),
	wage varchar(50),
	vacancies varchar(50) not null,
	description text,
	publishDate datetime not null,
	location varchar(100),
	jobTypeID int not null,
	enterpriseID int not null,
	primary key(ID),
	unique key(ID, enterpriseID),
	foreign key(jobTypeID) references jobType(ID),
	foreign key(enterpriseID) references enterprise(ID)
)ENGINE=InnoDB default charset=utf8;