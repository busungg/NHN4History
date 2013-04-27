drop table post;

create table post(
	image varchar(100),
	text varchar(200) not null,
	uptime Timestamp not null,
	latitude double,
	longitude double
	);