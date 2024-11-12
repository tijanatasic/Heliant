ALTER TABLE formular ADD COLUMN IF NOT EXISTS id_korisnik_kreirao INT NOT NULL;
ALTER TABLE formular ADD CONSTRAINT fk_id_korisnik_kreirao_formular FOREIGN KEY IF NOT EXISTS (id_korisnik_kreirao) REFERENCES korisnik(id);
ALTER TABLE formular ADD COLUMN IF NOT EXISTS id_korisnik_poslednji_azurirao INT NOT NULL;
ALTER TABLE formular ADD CONSTRAINT fk_id_korisnik_poslednji_azurirao_formular FOREIGN KEY IF NOT EXISTS (id_korisnik_poslednji_azurirao) REFERENCES korisnik(id);

ALTER TABLE formular_popunjen ADD COLUMN IF NOT EXISTS id_korisnik_kreirao INT NOT NULL;
ALTER TABLE formular_popunjen ADD CONSTRAINT fk_id_korisnik_kreirao_formular_popunjen FOREIGN KEY IF NOT EXISTS (id_korisnik_kreirao) REFERENCES korisnik(id);
ALTER TABLE formular_popunjen ADD COLUMN IF NOT EXISTS id_korisnik_poslednji_azurirao INT NOT NULL;
ALTER TABLE formular_popunjen ADD CONSTRAINT fk_id_korisnik_poslednji_azurirao_formular_popunjen FOREIGN KEY IF NOT EXISTS (id_korisnik_poslednji_azurirao) REFERENCES korisnik(id);

ALTER TABLE polje ADD COLUMN IF NOT EXISTS id_korisnik_kreirao INT NOT NULL;
ALTER TABLE polje ADD CONSTRAINT fk_id_korisnik_kreirao_polje FOREIGN KEY IF NOT EXISTS (id_korisnik_kreirao) REFERENCES korisnik(id);
ALTER TABLE polje ADD COLUMN IF NOT EXISTS id_korisnik_poslednji_azurirao INT NOT NULL;
ALTER TABLE polje ADD CONSTRAINT fk_id_korisnik_poslednji_azurirao_polje FOREIGN KEY IF NOT EXISTS (id_korisnik_poslednji_azurirao) REFERENCES korisnik(id);

ALTER TABLE polje_popunjeno ADD COLUMN IF NOT EXISTS id_korisnik_kreirao INT NOT NULL;
ALTER TABLE polje_popunjeno ADD CONSTRAINT fk_id_korisnik_kreirao_polje_popunjeno FOREIGN KEY IF NOT EXISTS (id_korisnik_kreirao) REFERENCES korisnik(id);
ALTER TABLE polje_popunjeno ADD COLUMN IF NOT EXISTS id_korisnik_poslednji_azurirao INT NOT NULL;
ALTER TABLE polje_popunjeno ADD CONSTRAINT fk_id_korisnik_poslednji_azurirao_polje_popunjeno FOREIGN KEY IF NOT EXISTS (id_korisnik_poslednji_azurirao) REFERENCES korisnik(id);