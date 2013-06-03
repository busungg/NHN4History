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
	text varchar(1000),
	uptime datetime not null,
	latitude double,
	longitude double
	);
	
insert into post values('1', 'aaaa', 'test1', '피자헛 a', 'shopnamea', 'b', '1.jpg', 1,'<img src=upload/1.jpg width=100 height=100/><img src=upload/2.jpg width=100 height=100/><img src=upload/3.jpg width=100 height=100/><br><br>텍스트1<br><br>', '20130506024016', '37.5669527', '126.9786178'  );
insert into post values('2', 'aaaa', 'test2', '패밀리 레스토랑', '푸드코트', 'b', '4.jpg', 1,'<img src=upload/4.jpg width=100 height=100/><img src=upload/5.jpg width=100 height=100/><img src=upload/6.jpg width=100 height=100/><br><br>텍스트2 abcd !@#$%~<br><br>', '20130506024020', '37.5669527', '126.9786178'  );
insert into post values('3', 'aaaa', 'test3', '과메기', '포항과메기', 'b', '7.jpg', 1,'<img src=upload/7.jpg width=100 height=100/><img src=upload/8.jpg width=100 height=100/><br><br>텍스트3 과메기!! abcd !@#$%~<br><br>', '20130508024020', '37.5669527', '126.9786178'  );