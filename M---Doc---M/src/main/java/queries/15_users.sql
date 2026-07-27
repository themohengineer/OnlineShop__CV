insert into customer (id, address, first_name, last_name,mobile, postal_code, tel)
values (1, 'tehran', 'Hossein', 'Badrnezhad', '1234567890', '02112345678','021123456');
insert into users (id, email, enable, mobile, password, register_date, username, customer_id)
values (1, 'hossein@gmail.com', true, '09121234567', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', now(), 'admin', 1);