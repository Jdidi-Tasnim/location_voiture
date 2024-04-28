
create database if not exists agence;
set autocommit=1;
create table client( 
Num_permis varchar(6) , 
Nom varchar(15) Not Null,
Prenom varchar(15) Not Null,
Cin varchar(8) primary key  CHECK (Cin LIKE '0%' OR Cin LIKE '1%'),
Num_tel varchar(8) not null , 
Num_Passeport varchar(15) ,
Nationalite varchar(20) , 
Adress varchar(25) Not Null
 );

create table Employee( 
ID varchar(5) not null , 
pass varchar(20)not null CHECK (pass REGEXP '^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]+$'),
 Nom varchar(15) Not Null, 
 Prenom varchar(15) Not Null, 
 Cin varchar(8) primary key CHECK (Cin LIKE '0%' OR Cin LIKE '1%'),
 Num_tel varchar(8) not null , 
 Num_Passeport varchar(15) ,
 Nationalite varchar(20) ,
 Adress varchar(25) Not Null 
 );

create table Voiture( 
Matricule varchar(15) primary key ,   
Prix_par_jour decimal(6,2) not null,
Etat varchar(10) check (etat in ("Bonne etat","En panne","Louée")) default("Bonne etat"),
ID int references modele(ID) on update cascade on delete cascade
 );
 create table modele( 
 ID int primary key ,
 Marque varchar(15) Not Null,
 Carburant varchar(10) check (Carburant in ("Essence","Mazout","Courant")) default("Essence"),
 Transmission varchar(15) check (Transmission in ("automatique","manuelle")) ,
 Couleur varchar(15) , 
 Année year Not Null
 );

create table Location(
 Cin varchar(8) references client(Cin) on update cascade on delete cascade, 
 Matricule varchar(15) references voiture(Matricule) on update cascade on delete cascade,
 primary key(cin,Matricule,date_debut), 
 date_debut Date Not null, 
 date_fin Date Not null, 
 Montant_totale decimal(8,2) Not null,
 Penalite decimal(5,2) Not null  );
 
 INSERT INTO client (Num_permis, Nom, Prenom, Cin, Num_tel, Num_Passeport, Nationalite, Adress)
VALUES 
('123456', 'Smith', 'John', '01234567', '01234567', 'ABCDE12345', 'American', '123 Main St'),
('234567', 'Doe', 'Jane', '12345678', '12345678', 'FGHIJ67890', 'Canadian', '456 Elm St'),
('345678', 'Garcia', 'Maria', '03456789', '23456789', 'KLMNO12345', 'Mexican', '789 Maple St');

INSERT INTO Employee (ID, pass, Nom, Prenom, Cin, Num_tel, Num_Passeport, Nationalite, Adress)
VALUES 
('E001', 'P@ssw0rd!', 'Johnson', 'Robert', '01234767', '01234567', 'ABCDE12345', 'American', '123 Main St'),
('E002', 'P@ssw0rd123', 'Gomez', 'Ana', '12345678', '12345678', 'FGHIJ67890', 'Mexican', '456 Elm St'),
('E003', 'Password1!', 'Lee', 'Jin', '03456789', '23456789', 'KLMNO12345', 'Korean', '789 Maple St');

INSERT INTO modele (ID, Marque, Carburant, Transmission, Couleur, Année)
VALUES 
(1, 'Toyota', 'Essence', 'manuelle', 'Blue', '2022'),
(2, 'Honda', 'Essence', 'automatique', 'Red', '2021'),
(3, 'Ford', 'Mazout', 'manuelle', 'Black', '2020');

INSERT INTO Voiture (Matricule, Prix_par_jour, Etat, ID)
VALUES 
('ABC728', 25.00, 'en panne', 1),
('DEF856', 30.00, 'Bonne etat', 2),
('GHI799', 35.00, 'Louee', 3);
INSERT INTO Location (Cin, Matricule, date_debut, date_fin, Montant_totale, Penalite)
VALUES 
('01234567', 'GHI799', '2017-04-20', '2023-04-25', 125.00, 0.00),
('12345678', 'GHI799', '2002-04-22', '2023-04-28', 210.00, 15.00),
('03456789', 'GHI789', '2003-04-23', '2023-04-26', 105.00, 0.00);


SELECT v.Matricule, v.Etat
FROM voiture v; 
SELECT  v.Matricule, v.Prix_par_jour,v.Etat,m.*
FROM voiture v,modele m
WHERE( v.Etat = 'Bonne etat' OR v.Etat = 'Louée') and(v.ID=m.ID);

SELECT v.Matricule, v.Prix_par_jour,v.Etat,m.*, COUNT(*) as Nombre_Locations
FROM voiture v
JOIN location l ON v.Matricule = l.Matricule
Join modele m ON v.ID=m.ID
GROUP BY v.Matricule
ORDER BY COUNT(*) DESC
LIMIT 3;

SELECT c.CIN, c.Nom, c.Prenom, COUNT(*) as Nombre_Locations
FROM client c
JOIN location l ON c.Cin = l.Cin
GROUP BY c.Cin
ORDER BY COUNT(*) DESC
LIMIT 1;




