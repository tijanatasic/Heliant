CREATE TABLE IF NOT EXISTS formular (
    id INT AUTO_INCREMENT,
    naziv VARCHAR(256) NOT NULL,
    vreme_kreiranja TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    vreme_poslednje_izmene TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS formular_popunjen (
    id INT AUTO_INCREMENT,
    id_formular INT NOT NULL,
    vreme_kreiranja TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    vreme_poslednje_izmene TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (id_formular) REFERENCES formular(id)
);

CREATE TABLE IF NOT EXISTS korisnik (
    id INT AUTO_INCREMENT,
    korisnicko_ime VARCHAR(256) NOT NULL,
    lozinka VARCHAR(256) NOT NULL,
    vreme_kreiranja TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    vreme_poslednje_izmene TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE KEY unique_korisnicko_ime (korisnicko_ime)
);

CREATE TABLE IF NOT EXISTS polje (
    id INT AUTO_INCREMENT,
    id_formular INT NOT NULL,
    naziv VARCHAR(256) NOT NULL,
    prikazni_redosled INT NOT NULL,
    tip ENUM('TIP_1', 'TIP_2', 'TIP_3') NOT NULL,
    vreme_kreiranja TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    vreme_poslednje_izmene TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (id_formular) REFERENCES formular(id)
);

CREATE TABLE IF NOT EXISTS polje_popunjeno (
    id INT AUTO_INCREMENT,
    id_formular_popunjen INT NOT NULL,
    id_polje INT NOT NULL,
    vrednost_tekst VARCHAR(256),
    vrednost_broj DOUBLE,
    vreme_kreiranja TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    vreme_poslednje_izmene TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (id_formular_popunjen) REFERENCES formular_popunjen(id),
    FOREIGN KEY (id_polje) REFERENCES polje(id)
);

CREATE TABLE IF NOT EXISTS statistika (
    id INT AUTO_INCREMENT,
    datum DATE NOT NULL,
    broj_popunjenih_formulara INT NOT NULL,
    PRIMARY KEY (id)
);