CREATE DATABASE marketDB;
\c marketdb
create table Customer(
    ID integer ,
    Fullname varchar(40) not null,
    Email varchar(100) not null unique,
    Image text,
    Customer_points numeric default 0,
    CCCD varchar(20) not null,
    PhoneNumber varchar(15) not null,
    constraint Customers_pk PRIMARY KEY(ID)
);

create table Order_Product ( 
    ID bigint PRIMARY KEY,
    OrderStatus varchar(40) default 'chờ xác nhận',
    Created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    Delivery_date timestamp,
    Payment_status boolean default False,
    Total_price numeric,
    Original_total_price numeric,
	Address varchar(1000),
    Customer_ID integer null references Customer(ID)

);

create table Product(
    ID integer PRIMARY KEY,
    Name varchar(40) not null,
    Origin varchar(40) not null,
    Description text not null,
    Image text,
    Num_Of_products integer,
    DVT varchar(30),
    Sale integer default 0,
    Prices numeric,
    Category_ID integer null
);

create table Order_Product_relationship(
    ID integer PRIMARY KEY,
    Order_ID integer references Order_Product(ID) on delete cascade,
    Product_ID integer references Product(ID) ,
    Num_Of_Products integer
);

create table Category(
    ID integer PRIMARY KEY,
    Name varchar(30),
    Location varchar(100)
);
ALTER TABLE Product ADD CONSTRAINT Product_Category FOREIGN KEY (Category_ID) REFERENCES Category(ID);

create table ImportCoupon(
    ID integer PRIMARY KEY,
    Import_at timestamp,
    Total_prices numeric,
    Import_Status boolean default False,
    Employee_ID integer null
);

create table ImportCoupon_Product(
    ID integer PRIMARY KEY,
    ImportCoupon_ID integer references ImportCoupon(ID) ,
    Product_ID integer  references Product(ID) ,
    Num_Of_Products integer
);

create table Employee(
    ID integer PRIMARY KEY,
    Fullname varchar(40) not null,
    Email varchar(100) not null unique,
    Image text,
    CCCD varchar(20) not null,
    PhoneNumber varchar(15) not null,
    Position varchar(30)
);
alter table ImportCoupon add constraint ImportCoupon_Employee FOREIGN KEY (Employee_ID) references Employee(ID);

create table Account(
    ID integer PRIMARY KEY,
    Username varchar(30) unique,
    Password varchar(30),
    Role boolean default false
);
create table Account_Employee(
    ID integer PRIMARY KEY,
    Account_id integer unique references Account(ID) on delete cascade,
    Employee_id integer unique references Employee(ID) on delete cascade
);
create table Account_Customer(
    ID integer PRIMARY KEY,
    Account_id integer unique references Account(ID) on delete cascade,
    Customer_id integer unique references Customer(ID) on delete cascade
);




