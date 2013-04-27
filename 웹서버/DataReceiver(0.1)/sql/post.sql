drop table post;

create table post(
	userid varchar(10) not null,
	title varchar(50) not null,
	category varchar(40),
	tel varchar(15),
	image varchar(100),
	text varchar(200),
	uptime Timestamp not null,
	latitude double,
	longitude double
	);