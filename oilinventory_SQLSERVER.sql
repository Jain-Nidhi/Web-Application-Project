
CREATE DATABASE oilinventory;
 
CREATE  TABLE  oilinventory . newuser  (
   uname  varchar(40) not null UNIQUE,
   pword  varchar(8) not null ,
   fname  varchar(45) not null ,
   lname  varchar(45) null ,
   phone  bigint not null ,
   cellphone  bigint null ,
   email  varchar(60) not null UNIQUE,
   type  char(1) not null ,
   uid  varchar(40) not null,
  primary key ( uid )
  );
 
 
CREATE  TABLE  oilinventory . trader  (
   traderid  varchar(40) not null ,
   transactioncount  int not null,
   primary key ( traderid ),
     foreign key ( traderid ) REFERENCES   oilinventory . newuser ( uid )
      ON DELETE CASCADE ON UPDATE CASCADE
);
 
 
CREATE  TABLE  oilinventory . log  (
 traderid  varchar(40) not null ,
   tid  varchar(40) not null,
   pdate  date not null
  );
 
 
CREATE  TABLE  oilinventory . client  (
   clientid  varchar(40) not null ,
   level  varchar(6) not null default 'Silver' ,
   due  float null ,
  street  varchar(60) not null,
  city  varchar(20) not null,
 zipcode  int not null,
 state  char(2) not null,
 stock  int not null,
  primary key ( clientid ),
foreign key ( clientid ) REFERENCES   oilinventory . newuser ( uid )
      ON DELETE CASCADE ON UPDATE CASCADE
);
 
 
create  table  oilinventory . transaction1  (
   clientid  varchar(40) not null ,
 traderid  varchar(40) not null ,
   tid  varchar(40) not null ,
   quantity  float not null ,
   tdate  date not null ,
   rate  float not null ,
   commission  float not null ,
   cmode  tinyint not null ,
    active  tinyint not null ,          
   reserve  float not null ,
   buyorsell  tinyint not null,
  primary key ( tid ) ,
     foreign key ( clientid ) REFERENCES   oilinventory . client ( clientid ),
     foreign key ( traderid ) REFERENCES   oilinventory . trader ( traderid ));
 
 
 
create  table  oilinventory . payment  (
   paymentid  varchar(40) not null ,
   clientid  varchar(40) not null ,
   amount  float not null ,
   pdate  date not null ,
    tid  varchar(40) not null,
   primary key ( paymentid ),
   foreign key ( clientid ) REFERENCES   oilinventory . client ( clientid ),
foreign key ( tid ) REFERENCES   oilinventory . transaction1 ( tid )
);
 
   
insert into  oilinventory . newuser  ( uname , pword , fname , lname , phone , cellphone , email , type , uid ) values('admin','12345','John','Peterson','9878765454','8782762543','john.peterson@gmail.com','m','a70808a3-9c0a-48aa-9585-72852a72a3aa');

insert into  oilinventory . newuser  ( uname , pword , fname , lname , phone , cellphone , email , type , uid ) values('Jacob','jacob','Jacob','Black','9878765454','8782762543','john.peterson@gmail.com','c','a70808a3-9c0a-48aa-9585-72852a72b7gh');
insert into  oilinventory . newuser  ( uname , pword , fname , lname , phone , cellphone , email , type , uid ) values('Muriel','muriel','Muriel','Smith','9878765454','8782762543','m.smith@gmail.com','c','a70808a3-9c0a-48aa-9585-72852a72u9we');

insert into  oilinventory . client  ( clientid , level , due , street , city , zipcode , state , stock ) values('a70808a3-9c0a-48aa-9585-72852a72b7gh','Silver','0','17817 coit rd','Dallas','75252','TX','0');
insert into  oilinventory . client  ( clientid , level , due , street , city , zipcode , state , stock ) values('a70808a3-9c0a-48aa-9585-72852a72u9we','Silver','0','17817 coit rd','Dallas','75252','TX','0');

insert into  oilinventory . newuser  ( uname , pword , fname , lname , phone , cellphone , email , type , uid ) values('Arthur','arthur','Arthur','Smith','9878765454','8782762543','a.smith@gmail.com','t','a70808a3-9c0a-48aa-9585-72852a72j5tr');
insert into  oilinventory . newuser  ( uname , pword , fname , lname , phone , cellphone , email , type , uid ) values('Jack','jack','Jack','Donald','9878765454','8782762543','j.donald@gmail.com','t','a70808a3-9c0a-48aa-9585-72852a72g3dt');

