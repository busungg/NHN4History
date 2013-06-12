drop table share;

create table share(
	shareid INT not null AUTO_INCREMENT,
	userid varchar(100) not null,
	simage varchar(100) not null,
	syear varchar(100) not null,
	smonth varchar(100) not null,
	sday varchar(100) not null
	);