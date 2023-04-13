INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (1,'miroslav@maildrop.cc','miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','Miroslav','Simic','ADMIN');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (2,'tamara@maildrop.cc','tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','Tamara','Milosavljevic','KORISNIK');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (3,'petar@maildrop.cc','petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','Petar','Jovic','KORISNIK');

INSERT INTO reprezentacija(id, naziv, skraceni_naziv) VALUES (1, 'Srbija', 'srb');
INSERT INTO reprezentacija(id, naziv, skraceni_naziv) VALUES (2, 'Brazil', 'bra');
INSERT INTO reprezentacija(id, naziv, skraceni_naziv) VALUES (3, 'Spanija', 'spa');
INSERT INTO reprezentacija(id, naziv, skraceni_naziv) VALUES (4, 'Kamerun', 'kam');

INSERT INTO utakmica(id, reprezentacijaA_id, reprezentacijaB_id, broj_golova_a,broj_golova_b) VALUES (1, 1, 2, 1, 3);
INSERT INTO utakmica(id, reprezentacijaA_id, reprezentacijaB_id, broj_golova_a,broj_golova_b) VALUES (2, 2, 3, 2, 3);
INSERT INTO utakmica(id, reprezentacijaA_id, reprezentacijaB_id, broj_golova_a,broj_golova_b) VALUES (3, 4,1, 1, 0);

INSERT INTO igrac(id, ime, prezime, postignuti_golovi, reprezentacija_id) VALUES (1, 'Aleksandar', 'Mitrovic', 2, 1);
INSERT INTO igrac(id, ime, prezime, postignuti_golovi, reprezentacija_id) VALUES (2, 'Vinicius', 'Neymar', 1, 2);
INSERT INTO igrac(id, ime, prezime, postignuti_golovi, reprezentacija_id) VALUES (3, 'Marko', 'Villa', 1, 3);
INSERT INTO igrac(id, ime, prezime, postignuti_golovi, reprezentacija_id) VALUES (4, 'Dikembe', 'Ntombo', 0, 4);