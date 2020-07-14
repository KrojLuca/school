USE 5ainf1920

CREATE TABLE trasfusioni(
    idTrasfusione INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    idPersona INT NOT NULL 
);

INSERT INTO trasfusioni(idPersona)
   VALUES(1),
         (2),
         (3),
         (4),
         (5),
         (6)


CREATE TABLE donazioni(
    idTrasfusioni INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    idPersona INT NOT NULL ,
    nome VARCHAR(50),
    cognome VARCHAR(50),
    eta INT NOT NULL,
    dataDonazione VARCHAR(50)
);

INSERT INTO donazioni(idPersona,nome,cognome,eta,dataDonazione)
    VALUES(1,'Ferdinando','Lepre',20,'20/02/2020'),
          (2,'Matteo','Tealdi',19,'10/12/2019'),
          (3,'Giorgio','Gallo',18,'17/10/2019'),
          (4,'Delia','Amato',64,'05/01/2020'),
          (5,'Romano','Lepre',55,'26/02/2019')
      