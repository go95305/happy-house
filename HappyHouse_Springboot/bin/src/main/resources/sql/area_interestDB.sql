use happyhouse;

drop table area_interest;

Create table area_interest (
Member_id varchar(20), 
House_code INT);

alter table area_interest
add primary key (Member_id,House_code);

insert into area_interest
values('admin','1');

insert into area_interest 
	values ('ssafy','4');