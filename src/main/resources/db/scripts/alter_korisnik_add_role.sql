ALTER TABLE korisnik ADD COLUMN IF NOT EXISTS rola ENUM('ADMIN', 'RADNIK') NOT NULL DEFAULT 'RADNIK';

UPDATE korisnik SET rola = 'ADMIN' WHERE korisnicko_ime = 'korisnik1@heliant.rs';
UPDATE korisnik SET rola = 'ADMIN' WHERE korisnicko_ime = 'korisnik5@heliant.rs';