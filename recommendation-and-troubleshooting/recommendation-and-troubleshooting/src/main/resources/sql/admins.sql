insert into admins (id, first_name, last_name, email, password, verified)  values
    (nextval('person_seq'), 'Neil', 'Gaiman', 'admin@admin.com', '$2y$12$NFN7DJUX1lFfaDX1tc9/6uBtgls9SZOU9iwjhrlXJc0xr471vgKAK', true);
insert into users (id, email, first_name, last_name, password, verified) values 
	(nextval('person_seq'), 'user@gmail.com', 'user', 'user', '$2y$12$NFN7DJUX1lFfaDX1tc9/6uBtgls9SZOU9iwjhrlXJc0xr471vgKAK', true);