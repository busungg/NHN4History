drop table login;

create table login(
	id varchar(15) primary key,
	password varchar(15) not null,
	name varchar(15) not null,
	email varchar(50) not null
);

insert into login values('aaaa', '1111', '테스터abc', 'test@naver.com');
insert into login values('bbbb', '1111', '테스트11ab', 'bbbb@test.com');