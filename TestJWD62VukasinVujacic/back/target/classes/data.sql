INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (1,'miroslav@maildrop.cc','miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','Miroslav','Simic','ADMIN');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (2,'tamara@maildrop.cc','tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','Tamara','Milosavljevic','KORISNIK');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (3,'petar@maildrop.cc','petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','Petar','Jovic','KORISNIK');

INSERT INTO kategorija(id, naziv) VALUES (1, 'kategorija 1');
INSERT INTO kategorija(id, naziv) VALUES (2, 'nesto 2');
INSERT INTO kategorija(id, naziv) VALUES (3, 'kategorija 3');


INSERT INTO ljubimac(id, ime, starost, vakcinisan, pol, tezina, opis, kategorija_id) VALUES (1, 'pas', 6, false, 'muski', 5.6, 'opis 1', 1);
INSERT INTO ljubimac(id, ime, starost, vakcinisan, pol, tezina, opis, kategorija_id) VALUES (2, 'papagaj', 26, true, 'zenski', 0.6, 'opis 2', 2);
INSERT INTO ljubimac(id, ime, starost, vakcinisan, pol, tezina, opis, kategorija_id) VALUES (3, 'macka', 16, true, 'muski', 2.4, 'nesto', 3);
INSERT INTO ljubimac(id, ime, starost, vakcinisan, pol, tezina, opis, kategorija_id) VALUES (4, 'kornjaca', 1, false, 'zenski', 0.9, 'nestp 1', 1);

INSERT INTO udomljavanje(id, datum_i_vreme, ljubimac_id) VALUES (1, '2020-06-21 20:00', 2);
INSERT INTO udomljavanje(id, datum_i_vreme, ljubimac_id) VALUES (2, '2022-06-23 10:00', 1);
INSERT INTO udomljavanje(id, datum_i_vreme, ljubimac_id) VALUES (3, '2022-06-22 16:00', 3);