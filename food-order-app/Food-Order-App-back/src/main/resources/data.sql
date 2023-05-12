INSERT INTO user (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (1,'miroslav@maildrop.cc','miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','Miroslav','Simic','ADMIN');
INSERT INTO user (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (2,'tamara@maildrop.cc','tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','Tamara','Milosavljevic','KORISNIK');
INSERT INTO user (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (3,'petar@maildrop.cc','petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','Petar','Jovic','KORISNIK');

 

              
INSERT INTO restaurant(id, name, contact, founding_year, about_us, location) VALUES (1, 'KFC', '065775776', 1963, 'KFC is the second largest meal restaurant in the world with delicious fried chicken and beautiful enterier','Masarikova 22, Novi Sad');
INSERT INTO restaurant(id, name, contact, founding_year, about_us,location) VALUES (2, 'McDonalds', '701-701', 1955, 'McDonalds is the worlds largest fast food restaurant chain. McDonalds is best known for its hamburgers, cheeseburgers and french fries, although their menu also includes other items like chicken, fish, fruit, and salads.','Venizelesova 23, Novi Sad');

INSERT INTO meal(id, name, description, price, amount, restaurant_id) VALUES (1, 'Biftek', 'Meso prestiznog kvaliteta', 990.00, 100, 1);
INSERT INTO meal(id, name, description, price, amount, restaurant_id) VALUES (2, 'Krmenadla', 'Meso domace proizvodnje', 670.00, 100, 2);
INSERT INTO meal(id, name, description, price, amount, restaurant_id) VALUES (3, 'Chicken Nuggets', 'Pohovana piletina sa dodatkom BBQ sosa', 460.00, 50, 1);

