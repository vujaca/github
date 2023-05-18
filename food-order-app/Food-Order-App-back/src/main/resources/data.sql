INSERT INTO user (id, e_mail, username, password, name, surname, uloga)
              VALUES (1,'miroslav@maildrop.cc','miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','Miroslav','Simic','ADMIN');
INSERT INTO user (id, e_mail, username, password, name, surname, uloga)
              VALUES (2,'tamara@maildrop.cc','tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','Tamara','Milosavljevic','KORISNIK');
INSERT INTO user (id, e_mail, username, password, name, surname, uloga)
              VALUES (3,'petar@maildrop.cc','petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','Petar','Jovic','KORISNIK');

 

              
INSERT INTO restaurant(id, name, contact, founding_year, about_us, location, image) VALUES (1, 'KFC', '065775776', 1963, 'KFC is the second largest meal restaurant in the world with delicious fried chicken and beautiful enterier','Masarikova 22, Novi Sad', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSsZ8SYaujJOzsWKOEh3xeIdDiFZBIi1M3TvA&usqp=CAU');
INSERT INTO restaurant(id, name, contact, founding_year, about_us,location, image) VALUES (2, 'McDonalds', '701-701', 1955, 'McDonalds is the worlds largest fast food restaurant chain. McDonalds is best known for its hamburgers, cheeseburgers and french fries, although their menu also includes other items like chicken, fish, fruit, and salads.','Venizelesova 23, Novi Sad', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTUJU0OS8K0J_vgcRXdP_9kRglSQQqkxld_Gw&usqp=CAU');
INSERT INTO restaurant(id, name, contact, founding_year, about_us,location, image) VALUES (3, 'Gyros Master', '708-706', 2020 ,'Gyros Master ima najbolji giros u gradu. Uvek smo maksimalno brzi i spremamo preukusne girose.','Bul. Oslobodjenja 23, Novi Sad', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQjg1_sCm-qF8eUKSYee-HgiOn2_RjDqTBBdg&usqp=CAU');

INSERT INTO meal(id, name, description, price, amount, restaurant_id, image) VALUES (1, 'Biftek', 'Meso prestiznog kvaliteta', 990.00, 100, 1, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQBV0aYIdUYb1DB6Zr0oZi7rnUwc7rZIfQ2LGfZwGWdpQ&s');
INSERT INTO meal(id, name, description, price, amount, restaurant_id, image) VALUES (2, 'Krmenadla', 'Meso domace proizvodnje', 670.00, 100, 2, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT_H8o_GbPjO_Oa6PGDJE5dIPQRR3_5BJOSdg&usqp=CAU');
INSERT INTO meal(id, name, description, price, amount, restaurant_id, image) VALUES (3, 'Chicken Nuggets', 'Hrskavi pileci komadi izvanrednog ukusa', 460.00, 50, 1, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQxIZejDujKHEpdFm-x2rXCAjGAO_z4TWl47g&usqp=CAU');

