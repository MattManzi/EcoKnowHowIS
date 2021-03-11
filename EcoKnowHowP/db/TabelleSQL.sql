DROP DATABASE IF EXISTS ecoknowhow;

CREATE DATABASE ecoknowhow;
USE ecoknowhow;

DROP user IF EXISTS 'ekh'@'localhost';
CREATE USER 'ekh'@'localhost' IDENTIFIED BY 'adminadmin';
GRANT ALL ON ecoknowhow.* TO 'ekh'@'localhost';

CREATE TABLE cliente(
	username char(15) not null,
	nome char(30) not null,
	cognome char(30) not null, 
	settore char(20) not null,
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
	username char(15) not null,
	email char(50) not null,
	password char(35) not null,
	codSicurezza char(7) not null,
	PRIMARY KEY(username)
);

CREATE TABLE matrice(
	id int not null AUTO_INCREMENT, 
	nome char(20) not null,
	sottotitolo char(50) not null,
	nota char(250) not null,
	modulo char(1) check(tipo in('A', 'B','C')),
	PRIMARY KEY(id)
);

CREATE TABLE parametro(
	id int not null AUTO_INCREMENT, 
	idMatrice int not null,
	nome char(100) not null,
	sku char(20) not null,
	limiteEmissione char(20) not null,
	uMisura char(20) not null,
	prezzo double default 0,
	FOREIGN KEY(idMatrice) REFERENCES matrice(id),
	PRIMARY KEY(id)
);

CREATE TABLE pacchetto(
	id char(6) not null,
	idMatrice int not null,
	nome char(20) not null,
	descrizione char(250) not null,
	
	username char(20) not null,
	contenuto mediumblob default null,
	prezzo double default 0,
	FOREIGN KEY(idMatrice) REFERENCES matrice(id),	
	PRIMARY KEY(id)
);

CREATE TABLE piano(
	id char(8) not null,
	username char(20) not null,
	pacchetto mediumblob default null,
	modulo mediumblob default null,
	referto mediumblob default null,
	schedaDatiSicurezza mediumblob default null,
	prezzo double default 0,
	stato char(9) check(stato in('respinto', 'inAttesa', 'approvato')),	
	PRIMARY KEY(id)
);

CREATE TABLE info(
	id int not null,
	anno char(4) not null,
	idPiano char(4) not null
);

insert into info(id, anno, idPiano) value(1,'2021','0001');
insert into amministratore(username, email, password, codSicurezza) values('admin1', 'ptksfn@hotmail.com', 'a722c63db8ec8625af6cf71cb8c2d939', '336548');







