create table interest(ID Int NOT NULL,
				  Name varchar(50)NOT NULL,
				  Amount numeric(9)NOT NULL,
				  TookDate Date NOT NULL,
				  Intrst numeric(2,1) NOT NULL,
				  Phn numeric(13),
				  PRIMARY KEY (ID));
insert into interest values (1,'Rammohan','1000','2020-05-26',1.5,9640129572);
insert into interest values(2,'Hemanth','2000','2019-05-06',1.5,9705821582);
select * from interest
