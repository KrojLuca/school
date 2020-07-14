USE ospedale

CREATE TABLE CalendarioPrenotazioni(
    CodiceVisita int(10) NOT NULL AUTO_INCREMENT,
    TipologiaVisita varchar(6),
    Orario DATETIME,
    PRIMARY KEY (CodiceVisita)
);

INSERT INTO CalendarioPrenotazioni(CodiceVisita, TipologiaVisita, Orario)
    VALUES(1, "chigom", "2020-06-12 10:00:00"),
          (2, "chigin", "2020-06-15 15:00:00"),
          (3, "addo", "2020-07-13 16:30:00")

CREATE TABLE Pazienti(
    CodiceVisita int(10) PRIMARY KEY, 
    CFPaziente varchar(16) NOT NULL, 
    nome varchar(20), 
    cognome varchar(20), 
    luogoNascita varchar(40), 
    provincia varchar(2), 
    dataNascita DATE, 
    sesso varchar(1),
    FOREIGN KEY (CodiceVisita) REFERENCES CalendarioPrenotazioni(CodiceVisita)
);

INSERT INTO Pazienti(CodiceVisita, CFPaziente, nome, cognome, luogoNascita, provincia, dataNascita, sesso)
    VALUES(1, "KRJLCU00R01D205C", "luca", "kroj", "cuneo", "CN", "2000-10-01", "M"),
          (2, "LLNSRA02P42D205T", "sara", "ellena", "cuneo", "CN", "2002-09-02", "F"),
          (3, "GRDMTT01P01D205G", "mattia", "giordano", "cuneo", "CN", "2001-09-01", "M")
