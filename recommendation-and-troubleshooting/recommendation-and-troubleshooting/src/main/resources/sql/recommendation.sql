insert into configuration_usages (usage) values ('Editing');
insert into configuration_usages (usage) values ('Bookkeeping');
insert into configuration_usages (usage) values ('3D Design');
insert into configuration_usages (usage) values ('Software Development');
insert into configuration_usages (usage) values ('Academic Purposes');
insert into configuration_usages (usage) values ('Gaming');
insert into configuration_usages (usage) values ('Personal Usage');

insert into configuration_usages_requirements (usage_id) values (1);
insert into configuration_usages_requirements (usage_id) values (2);
insert into configuration_usages_requirements (usage_id) values (3);
insert into configuration_usages_requirements (usage_id) values (4);
insert into configuration_usages_requirements (usage_id) values (5);
insert into configuration_usages_requirements (usage_id) values (6);
insert into configuration_usages_requirements (usage_id) values (7);

insert into configuration_usage_type_requirements_cpu (configuration_usage_type_requirements_id, cpu) values (4, 'Intel Core i3 Processor');
insert into configuration_usage_type_requirements_gpu (configuration_usage_type_requirements_id, gpu) values (6, 'ASUS GeForce GTX 1050 Ti Cerberus OC 4GB GDDR5 128bit - CERBERUS-GTX1050TI-O4G');

insert into configuration_characteristics (characteristic) values ('Presentations');
insert into configuration_characteristics (characteristic) values ('Touchscreen');
insert into configuration_characteristics (characteristic) values ('Low Brightness');
insert into configuration_characteristics (characteristic) values ('Online Meeting');
insert into configuration_characteristics (characteristic) values ('Damaged Eyesight');

insert into configuration_characteristics_requirements (characteristic_id) values (1);
insert into configuration_characteristics_requirements (characteristic_id) values (2);
insert into configuration_characteristics_requirements (characteristic_id) values (3);
insert into configuration_characteristics_requirements (characteristic_id) values (4);
insert into configuration_characteristics_requirements (characteristic_id) values (5);

insert into configuration_characteristic_type_requirements_microphone (configuration_characteristic_type_requirements_id, microphone) values (1, true);
insert into configuration_characteristic_type_requirements_camera (configuration_characteristic_type_requirements_id, camera) values (4, true);

insert into configurations(price, type, cpu, gpu, ram, os, psu, disc_type, disc_size, motherboard, screen_size, screen_resolution, music_card, touch_screen, microphone, camera, ergonomic, considered) values (52999, 'LAPTOP', 'Intel Core i3 Processor', 'GeForce GTX 1050 Ti', '8GB DDR4 2666 MHz', 'Windows 10 Pro 64bit', '500W', 'SSD', '240GB', 'MSI H3110M PRO-M2 PLUS', '13', '1024 x 768', 'musicCard1',  true, true, true, false, true);
insert into configurations(price, type, cpu, gpu, ram, os, psu, disc_type, disc_size, motherboard, screen_size, screen_resolution, music_card, touch_screen, microphone, camera, ergonomic, considered) values (89999, 'DESKTOP', 'AMD Ryzen 5', 'ASUS GeForce GTX 1050 Ti Cerberus OC 4GB GDDR5 128bit - CERBERUS-GTX1050TI-O4G', '16GB DDR4 2400 MHz', 'Windows 10 Pro 64bit', '600W', 'SSD', '240GB', 'MSI H3110M PRO-M2 PLUS', '15', '3840 x 1440', 'musicCard1', false, true, true, false, true);
insert into configurations(price, type, cpu, gpu, ram, os, psu, disc_type, disc_size, motherboard, screen_size, screen_resolution, music_card, touch_screen, microphone, camera, ergonomic, considered) values (99999, 'LAPTOP', 'Intel Core i3 Processor', 'GeForce GTX 1050 Ti', '16GB DDR4 2400 MHz', 'Windows 10 Pro 64bit', '600W', 'SSD', '240GB', 'MSI H3110M PRO-M2 PLUS', '15', '1024 x 768', 'musicCard1', false, true, true, true, true);

insert into favorites (active, date_of_favorite, put_to_favorite, configuration_id, favorite_id) values (true, '2021-06-01 00:00:00', 7, 1, 2);

insert into ratings (rate, rating_id) values (5.0, 1);
