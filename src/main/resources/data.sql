insert into user(id_user,password,roles, username) values (1,'$2a$12$nOKj7bsgZnEZOSm8iFMMieDcZxLFV41jboQnJBgYd1EZUsk7UfDpq','ADMIN','admin');
insert into user(id_user,password,roles, username) values (2,'$2a$12$gUD7ppZJiRrgzEJiyVbqTOTxqIX5qTusf6Cbb90zN2IHKR.0ipO.e','CASHIER','cashier1');
insert into user(id_user,password,roles, username) values (3,'$2a$12$cCbqI09DGa1oZ6GP24V2K.4XNaHhP253p0sxFoYt9BwvkUF2JqnAS','CASHIER','cashier2');

insert into product(id_product,description,price, product_name,stock_available,stock_bought) values (1,'suc',3.0,'cola',15,0);
insert into product(id_product,description,price, product_name,stock_available,stock_bought) values (2,'pahar de vin',15.0,'glasses',30,0);

insert into client(id_client,first_name,last_name,loyalty) values (1,'Marin','Andreea',5);
insert into client(id_client,first_name,last_name,loyalty) values (2,'Marin','Razvan',3);

insert into cart(id_cart,price) values (1,15.0);
insert into cart(id_cart,price) values (2,100.0);

insert into orders(id_order,date,total_price,client_id,cashier_id,cart_id,status) values (1,'30/04/2023',15.0,1,2,1,'delivered');
insert into orders(id_order,date,total_price,client_id,cashier_id,cart_id,status) values (2,'1/05/2023',100.0,2,3,2,'delivered');

