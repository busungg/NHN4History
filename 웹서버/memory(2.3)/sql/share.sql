drop table share;

create table share(
	shareid INT not null AUTO_INCREMENT,
	userid varchar(10) not null,
	simage varchar(10) not null,
	syear varchar(10) not null,
	smonth varchar(10) not null,
	sday varchar(10) not null
	);