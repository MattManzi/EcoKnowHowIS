DROP DATABASE IF EXISTS ecoknowhow;

CREATE DATABASE ecoknowhow;
USE ecoknowhow;

DROP user IF EXISTS 'ekh'@'localhost';
CREATE USER 'ekh'@'localhost' IDENTIFIED BY 'adminadmin';
GRANT ALL ON ecoknowhow.* TO 'ekh'@'localhost';

CREATE TABLE cliente(
	username char(20) not null,
	nome char(30) not null,
	cognome char(30) not null, 
	funzioneAziendale char(50) not null,
	telefono char(10) not null,
	ragioneSociale char(50) not null, 
	indirizzo char(100) not null,
	pIvaCF char(16) not null,
	pec char(50) default null,
	sdi char(6) not null,
	email char(50) not null,
	password char(35) not null,
	codSicurezza char(7) not null,
	attivo int check(attivo in(0,1)),
	PRIMARY KEY(username)
);

CREATE TABLE amministratore(
	username char(20) not null,
	email char(50) not null,
	password char(35) not null,
	codSicurezza char(7) not null,
	PRIMARY KEY(username)
);

CREATE TABLE matrice(
	id int not null AUTO_INCREMENT, 
	nome char(20) not null,
	sottotitolo char(50) not null,
	descrizione char(250) not null,
	PRIMARY KEY(id)
);

CREATE TABLE parametro(
	id int not null AUTO_INCREMENT, 
	idMatrice int not null,
	nome char(20) not null,
	sku char(20) not null,
	campione char(50) not null,
	campionamento char(50) not null,
	limiteEmissione char(10) not null,
	uMisura char(10) not null,
	prezzo double default 0,
	FOREIGN KEY(idMatrice) REFERENCES matrice(id),
	PRIMARY KEY(id)
);

CREATE TABLE pacchetto(
	id char(6) not null,
	idMatrice int not null,
	nome char(20) not null,
	descrizione char(250) not null,
	tipo char(8) check(tipo in('standard', 'analitico')),
	username char(20) not null,
	contenuto mediumblob default null,
	prezzo double default 0,
	FOREIGN KEY(idMatrice) REFERENCES matrice(id),
	PRIMARY KEY(id)
);

CREATE TABLE piano(
	id int not null AUTO_INCREMENT, 
	idPacchetto char(6) not null,
	username char(20) not null,
	modulo mediumblob default null,
	referto mediumblob default null,
	schedaDatiSicurezza mediumblob default null,
	prezzo double default 0,
	stato char(9) check(stato in('respinto', 'inAttesa', 'approvato')),	
	FOREIGN KEY(idPacchetto) REFERENCES pacchetto(id),
	FOREIGN KEY(username) REFERENCES cliente(username),
	PRIMARY KEY(id)
);

insert into amministratore(username, email, password, codSicurezza) values('admin1', 'ptksfn@hotmail.com', 'a722c63db8ec8625af6cf71cb8c2d939', '336548');


insert into matrice(nome, sottotitolo, descrizione) values('Acque', 'Analisi Acque', 'descrizone1 descrizone1 descrizone1 descrizone1 descrizone1 descrizone1 descrizone1 descrizone1 descrizone1 descrizone1 descrizone1 descrizone1');
insert into matrice(nome, sottotitolo, descrizione) values('Terra', 'Analisi Terra', 'descrizone2 descrizone2 descrizone2 descrizone2 descrizone2 descrizone2 descrizone2 descrizone2 descrizone2 descrizone2 descrizone2 descrizone2 descrizone2 descrizone2 ');
insert into matrice(nome, sottotitolo, descrizione) values('Rifiuti', 'Analisi Rifiuti', 'descrizione3 descrizione3 descrizione3 descrizione3 descrizione3 descrizione3');


insert into parametro(idMatrice ,nome ,sku , campione ,	campionamento ,	limiteEmissione ,uMisura ,	prezzo ) values (1,'Ferro','wwee','qwe','qwe','qw','qwe',15.0);
INSERT INTO parametro(idMatrice, nome, sku, campione, campionamento, limiteEmissione, uMisura, prezzo ) VALUES (1,'Zinco','wwee','qwe','qwe','qw','qwe',15.0);
INSERT INTO parametro(idMatrice, nome, sku, campione, campionamento, limiteEmissione, uMisura, prezzo ) VALUES (1,'Rame','wwee','qwe','qwe','qw','qwe',15.0);
INSERT INTO parametro(idMatrice, nome, sku, campione, campionamento, limiteEmissione, uMisura, prezzo ) VALUES (1,'Quarzo','wwee','qwe','qwe','qw','qwe',15.0);
INSERT INTO parametro(idMatrice, nome, sku, campione, campionamento, limiteEmissione, uMisura, prezzo ) VALUES (1,'Mercurio','wwee','qwe','qwe','qw','qwe',15.0);
INSERT INTO parametro(idMatrice, nome, sku, campione, campionamento, limiteEmissione, uMisura, prezzo ) VALUES (1,'Bronzo','wwee','qwe','qwe','qw','qwe',15.0);
insert into parametro(idMatrice ,nome ,sku , campione ,	campionamento ,	limiteEmissione ,uMisura ,	prezzo ) values (1,'Ferro','wwee','qwe','qwe','qw','qwe',15.0);
INSERT INTO parametro(idMatrice, nome, sku, campione, campionamento, limiteEmissione, uMisura, prezzo ) VALUES (1,'Zinco2','wwee','qwe','qwe','qw','qwe',15.0);
INSERT INTO parametro(idMatrice, nome, sku, campione, campionamento, limiteEmissione, uMisura, prezzo ) VALUES (1,'Rame2','wwee','qwe','qwe','qw','qwe',15.0);
INSERT INTO parametro(idMatrice, nome, sku, campione, campionamento, limiteEmissione, uMisura, prezzo ) VALUES (1,'Quarzo2','wwee','qwe','qwe','qw','qwe',15.0);
INSERT INTO parametro(idMatrice, nome, sku, campione, campionamento, limiteEmissione, uMisura, prezzo ) VALUES (1,'Mercurio2','wwee','qwe','qwe','qw','qwe',15.0);
INSERT INTO parametro(idMatrice, nome, sku, campione, campionamento, limiteEmissione, uMisura, prezzo ) VALUES (1,'Bronzo2','wwee','qwe','qwe','qw','qwe',15.0);

insert into parametro(idMatrice ,nome ,sku , campione ,	campionamento ,	limiteEmissione ,uMisura ,	prezzo ) values (2,'Ferro','wwee','qwe','qwe','qw','qwe',15.0);
INSERT INTO parametro(idMatrice, nome, sku, campione, campionamento, limiteEmissione, uMisura, prezzo ) VALUES (2,'Zinco','wwee','qwe','qwe','qw','qwe',15.0);
INSERT INTO parametro(idMatrice, nome, sku, campione, campionamento, limiteEmissione, uMisura, prezzo ) VALUES (2,'Rame','wwee','qwe','qwe','qw','qwe',15.0);
INSERT INTO parametro(idMatrice, nome, sku, campione, campionamento, limiteEmissione, uMisura, prezzo ) VALUES (2,'Quarzo','wwee','qwe','qwe','qw','qwe',15.0);
INSERT INTO parametro(idMatrice, nome, sku, campione, campionamento, limiteEmissione, uMisura, prezzo ) VALUES (2,'Mercurio','wwee','qwe','qwe','qw','qwe',15.0);
INSERT INTO parametro(idMatrice, nome, sku, campione, campionamento, limiteEmissione, uMisura, prezzo ) VALUES (2,'Bronzo','wwee','qwe','qwe','qw','qwe',15.0);

insert into parametro(idMatrice, nome, sku, campione, campionamento ,limiteEmissione, uMisura,prezzo ) values (3,'Ferro','wwee','qwe','qwe','qw','qwe',15.0);
INSERT INTO parametro(idMatrice, nome, sku, campione, campionamento, limiteEmissione, uMisura, prezzo ) VALUES (3,'Zinco','wwee','qwe','qwe','qw','qwe',15.0);
INSERT INTO parametro(idMatrice, nome, sku, campione, campionamento, limiteEmissione, uMisura, prezzo ) VALUES (3,'Rame','wwee','qwe','qwe','qw','qwe',15.0);
INSERT INTO parametro(idMatrice, nome, sku, campione, campionamento, limiteEmissione, uMisura, prezzo ) VALUES (3,'Quarzo','wwee','qwe','qwe','qw','qwe',15.0);
INSERT INTO parametro(idMatrice, nome, sku, campione, campionamento, limiteEmissione, uMisura, prezzo ) VALUES (3,'Mercurio','wwee','qwe','qwe','qw','qwe',15.0);
INSERT INTO parametro(idMatrice, nome, sku, campione, campionamento, limiteEmissione, uMisura, prezzo ) VALUES (3,'Bronzo','wwee','qwe','qwe','qw','qwe',15.0);












