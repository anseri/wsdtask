INSERT INTO `wsd-task`.items (id, name, price, quantity_sold, sale_date) VALUES (1, 'Item A', 10.0, 5, '2024-04-15');
INSERT INTO `wsd-task`.items (id, name, price, quantity_sold, sale_date) VALUES (2, 'Item B', 15.0, 3, '2024-04-16');
INSERT INTO `wsd-task`.items (id, name, price, quantity_sold, sale_date) VALUES (3, 'Item C', 20.0, 7, '2024-04-17');
INSERT INTO `wsd-task`.items (id, name, price, quantity_sold, sale_date) VALUES (4, 'Item D', 25.0, 2, '2024-04-18');
INSERT INTO `wsd-task`.items (id, name, price, quantity_sold, sale_date) VALUES (5, 'Item E', 30.0, 10, '2024-04-19');
INSERT INTO `wsd-task`.items (id, name, price, quantity_sold, sale_date) VALUES (6, 'Item F', 5.0, 15, '2024-04-20');
INSERT INTO `wsd-task`.items (id, name, price, quantity_sold, sale_date) VALUES (7, 'Item G', 50.0, 1, '2024-04-21');
INSERT INTO `wsd-task`.items (id, name, price, quantity_sold, sale_date) VALUES (8, 'Item H', 60.0, 8, '2024-04-22');
INSERT INTO `wsd-task`.items (id, name, price, quantity_sold, sale_date) VALUES (9, 'Item I', 70.0, 4, '2024-04-23');
INSERT INTO `wsd-task`.items (id, name, price, quantity_sold, sale_date) VALUES (10, 'Item J', 80.0, 9, '2024-04-24');

-- Additional data for last month
INSERT INTO `wsd-task`.items (id, name, price, quantity_sold, sale_date) VALUES (11, 'Item K', 100.0, 12, '2024-03-10');
INSERT INTO `wsd-task`.items (id, name, price, quantity_sold, sale_date) VALUES (12, 'Item L', 110.0, 11, '2024-03-15');
INSERT INTO `wsd-task`.items (id, name, price, quantity_sold, sale_date) VALUES (13, 'Item M', 120.0, 14, '2024-03-20');
INSERT INTO `wsd-task`.items (id, name, price, quantity_sold, sale_date) VALUES (14, 'Item N', 130.0, 6, '2024-03-25');
INSERT INTO `wsd-task`.items (id, name, price, quantity_sold, sale_date) VALUES (15, 'Item O', 140.0, 8, '2024-03-30');


INSERT INTO `wsd-task`.customers (id, name, email) VALUES (1, 'Anseri Khan First', 'anseri.khan.first@example.com');
INSERT INTO `wsd-task`.customers (id, name, email) VALUES (2, 'Anseri Khan Second', 'anseri.khan.two@example.com');
INSERT INTO `wsd-task`.customers (id, name, email) VALUES (3, 'Anseri Khan Third', 'anseri.khan.three@example.com');
INSERT INTO `wsd-task`.customers (id, name, email) VALUES (4, 'Anseri Khan Fourth', 'anseri.khan.four@example.com');
INSERT INTO `wsd-task`.customers (id, name, email) VALUES (5, 'Charlie Anseri Khan Fifth', 'anseri.khan.five@example.com');


INSERT INTO `wsd-task`.wishlist_items (customer_id, item_id) VALUES (1, 1);
INSERT INTO `wsd-task`.wishlist_items (customer_id, item_id) VALUES (1, 2);
INSERT INTO `wsd-task`.wishlist_items (customer_id, item_id) VALUES (2, 3);
INSERT INTO `wsd-task`.wishlist_items (customer_id, item_id) VALUES (2, 4);
INSERT INTO `wsd-task`.wishlist_items (customer_id, item_id) VALUES (3, 5);
INSERT INTO `wsd-task`.wishlist_items (customer_id, item_id) VALUES (3, 6);
INSERT INTO `wsd-task`.wishlist_items (customer_id, item_id) VALUES (4, 7);
INSERT INTO `wsd-task`.wishlist_items (customer_id, item_id) VALUES (4, 8);
INSERT INTO `wsd-task`.wishlist_items (customer_id, item_id) VALUES (5, 9);
INSERT INTO `wsd-task`.wishlist_items (customer_id, item_id) VALUES (5, 10);

