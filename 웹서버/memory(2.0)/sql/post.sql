drop table post;

create table post(
	postid INT not null AUTO_INCREMENT,
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
	
insert into post values('1', 'aaaa', 'test1', '피자헛 a', 'shopnamea', 'b', '1.jpg', 1,'t', '20130506024016', '37.5669527', '126.9786178'  );
insert into post values('2', 'aaaa', 'test1', '피자헛 b', 'shopnameb', 'b', '2.jpg', 1,'t', '20130506024016', '37.5639594', '126.9743812'  );
insert into post values('3', 'aaaa', 'test1', '피자헛 c', 'shopnamec', 'b', '3.jpg', 1,'t', '20130508025316', '11', '22'  );
insert into post values('4', 'aaaa', 'test1', '멕스넛 코엑스', 'a', 'b', '2.jpg', 1,'t', '20130507025316', '11', '22'  );
insert into post values('5', 'bbbb', 'test1', '씨발 코엑스', 'a', 'b', '4.jpg', 1,'t', '20130508012254', '11', '22'  );
insert into post values('6', 'bbbb', 'test1', '씨발 코엑스', 'a', 'b', 'Tulips.jpg', 1,'t', '20130510025345', '11', '22'  );