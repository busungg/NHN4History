drop table post;

create table post(
	userid varchar(10) not null,
	title varchar(50) not null,
	shopname varchar(40),
	category varchar(40),
	tel varchar(15),
	image varchar(100),
	imageNumber INT not null,
	text varchar(200),
	uptime Timestamp not null,
	latitude double,
	longitude double
	);