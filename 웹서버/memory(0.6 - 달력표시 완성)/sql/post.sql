drop table post;

create table post(
	postid INT not null,
	userid varchar(10) not null,
	title varchar(50) not null,
	shopname varchar(40),
	category varchar(40),
	tel varchar(15),
	image varchar(100),
	imageNumber INT not null,
	text varchar(200),
	uptime datetime not null,
	latitude double,
	longitude double
	);
	
insert into post values('1', 'aaaa', 'test1', '피자헛 코엑스', 'a', 'b', 'c', 1,'t', '20130506025316', '11', '22'  );
insert into post values('1', 'aaaa', 'test1', '피자헛 코엑스', 'a', 'b', 'c', 1,'t', '20130506025316', '11', '22'  );
insert into post values('1', 'aaaa', 'test1', '멕스넛 코엑스', 'a', 'b', 'c', 1,'t', '20130507025316', '11', '22'  );
insert into post values('1', 'aaaa', 'test1', '씨발 코엑스', 'a', 'b', 'c', 1,'t', '20130508025316', '11', '22'  );
insert into post values('1', 'aaaa', 'test1', '씨발 코엑스', 'a', 'b', 'c', 1,'t', '20130608025316', '11', '22'  );