
1.  Kreiranje 6. vježbe (direktorij {korisnik}\vjezba_06). U nastavku se direktorij za vježbu simbolički označava kao {vježba}). 

 

Priprema MySQL baze podataka za rada na vježbama:

2.  U ovoj dijelu vježbi treba pripremiti uvjete da svi studenti u dvorani koji rade na FOI računalima mogu se spojiti na MySQL poslužitelj koji je na nastavničkom računalu (192.168.15.1) i koristiti određenu bazu podataka  nwtis_g{grupa}, grupa=1,2,3,4. Zbog sigurnosne politike studenti koji rade na vlastitom prijenosnom računalu ne mogu se spojiti na nastavničko računalo.
3.  Na nastavničkom računalu (192.168.15.1) treba pokrenuti WAMP/XAMP tako da se MySQL poslužitelj može koristiti. Studenti koji rade na vlastitom prijenosnom računalu pokreću vlastiti MySQL poslužitelj (prethodno ga instaliraju ga ukoliko ga više nemaju). Ti studenti kao i svi ostali za rad kod kuće, trebaju na MySQL poslužitelju izvršiti kreiranje baze podataka nwtis\_g{grupa}, grupa=1,2,3,4 punjenje podataka i sl. Za tu namjenu služe sql datoteke koje se nalaze nakon opisa vježbe. Zatim treba kreirati korisničko ime nwtis\_admin uz pripadajuću lozinku nwtis\_foi te mu pridružiti administratorske ovlasti za rad s bazom podataka nwtis\_g{grupa}. Slijedi kreirati korisničkog imena nwtis\_g{grupa} uz pripadajuću lozinku  g{grupa}\_nwtis te mu pridružiti korisničke ovlasti (SELECT, INSERT, UPDATE, DELETE )  za rad s bazom podataka nwtis_g{grupa}.  
4.  Slijedi otvaranje veza prema određenim bazama podataka uz određeno korisničko ime i pripadajuću lozinku. Time će biti moguće raditi potrebne akcije s pojedinom bazom podataka.
5.  U NetBeans Services/Databases desnom tipkom na mišu otvoriti New Connection odabrati MySQL:

    `računalo upisati 192.168.15.1 `
    `port 3306`
    `baza podataka: mysql`
    `korisničko ime nwtis_admin (to je korisničko ime koje ima administracijske ovlasti za MySQL). `
    `lozinka nwtis_foi`

6.  Pokrenuti Test Connection. Ukoliko je u redu, dobije se kao na slici 1, s razlikom u adresi računala jer se radi o mom računalu kojem pristupam s adresom localhost. (U pravilu NE preporučuje se da se korisničko ime s administracijskim ovlastima može koristiti s drugih računala osim s localhost-a)
7.  U NetBeans Services/Databases desnom tipkom na mišu otvoriti New Connection odabrati MySQL:

    `računalo upisati 192.168.15.1 `
    `port 3306 `
    `baza podataka: nwtis_g{grupa}, grupa=1,2,3,4`
    `korisničko ime nwtis_g{grupa}` (to je korisničko ime koje ima korisničke ovlasti (SELECT, INSERT, UPDATE, DELETE) za MySQL nad bazom podataka nwtis_g{grupa}).` 
    `lozinka g{grupa}_nwtis`

8.  Pokrenuti Test Connection. Ukoliko je u redu dobije se kao na slici 2, s razlikom u adresi računala  (prikazano je  za grupu 1). 
9.  Umjesto pojedinačnog i eksplicitnog upisa podataka za otvaranje veze prema bazi podataka, može se koristiti registracija MySQL poslužitelja.
10. U NetBeans Services/Databases desnom tipkom na mišu otvoriti Register MySQL Server...

    `računalo upisati `
    `192.168.15.1 `
   ` port 3306 `
    `baza podataka: nwtis_g{grupa}, grupa=1,2,3,4`
   ` korisničko ime nwtis_g{grupa}` (to je korisničko ime koje ima  korisničke ovlasti za MySQL nad bazom podataka nwtis_g{grupa}). 
    `lozinka g{grupa}_nwtis`

11. Pokrenuti OK. Ukoliko je u redu ne pojavljuje se poruka za pogrešku. Izgled kod unosa je kao na slici 3, s razlikom u adresi računala (prikazano je za grupu 1). 
12. Sada se može u Services/Databases otvoriti MySQL Server at 192.168.15.1 pa se dobije popis baza podataka za koje korisničko ime ima dozvolu pristupa.
13. Dovoljno je desnom tipkom na mišu otvoriti izabranu bazu podataka i aktivirati Connect ... To je brži postupak nego prethodni za pojedinu bazu podataka.
14. Neovisno o izabranom postupku, ako je uspješan dobije se ispod Databases linija s nazivom jdbc:mysql://192.168.15.1:3306/nwtis_g{grupa}
15. Otvaranjam linije dobije se baza podataka pa se u njoj može odabrati izmađu Tables, View, Procedures. Otvara se Tables te se dobije popis tablica u bazi podataka.
16. Desnom tipkom na mišu otvoriti tablicu polaznici  i odabrati View Data... Pogledati upisane podatke.
    Odabrati ikonu za dodavanje podataka, unijeti vlastite podatke te potvrditi s OK. Provjeriti jesu li upisani podaci.

 
    Priprema Java DB baze podataka za rada na vježbama:
 

1.  U ovoj dijelu vježbi treba pripremiti uvjete da svi studenti u dvorani koji rade na FOI računalima kao i oni koji rade na vlastitom prijenosnom računalu mogu se spojiti na Java DB poslužitelj koji je na svakom pojedinačnom računalu (localhost) i koristiti određenu bazu podataka  nwtis_g{grupa}, grupa=1,2,3,4.
3.  U NetBeans Services/Databases desnom tipkom na mišu otvoriti Java DB i odabrati Start Server, kako bi se pokrenuo poslužitelj za JaveDB bazu podataka.
3.  Desnom tipkom na mišu otvoriti Java DB i odabrati Create Database, kako bi se pokrenuo poslužitelj za JaveDB bazu podataka.

    `baza podataka: nwtis_g{grupa}, grupa=1,2,3,4`
    `korisničko ime nwtis_g{grupa}` (to je korisničko ime koje ima  korisničke ovlasti za MySQL nad bazom podataka nwtis_g{grupa}). 
    `lozinka g{grupa}_nwtis`

4.  Pokrenuti OK. Ukoliko je u redu ne pojavljuje se poruka za pogrešku. Izgled kod unosa je kao na slici 4. 
5.  Dobije se ispod Databases linija s nazivom jdbc:derby://localhost:1527/nwtis_g{grupa}
6.  Desnom tipkom na mišu otvoriti gornju liniju i odabrati Connect..., kako bi se otvorila veza na poslužitelj za JaveDB bazu podataka  nwtis_g{grupa} 
7.  Otvara se nwtis_g{grupa} , Tables.
8.  Desnom tipkom na mišu otvoriti Execute Command...
9.  Za kreiranje tablice polaznici preuzeti datoteku  polaznici_1.sql (nalazi se nakon opisa vježbe), otvoriti u nekom od editora (Notepad++), kopirati sadržaj i prenijeti ga u NetBeans.
10. Pokrenuti izvršavanje SQL komande. Ako je uspješno, pokazuje se tablica polaznici ispod Tables.
11. Obrisati sadržaj komande.
12. Za punjenje tablice polaznici preuzeti datoteku polaznici_2.sql (nalazi se nakon opisa vježbe), otvoriti u nekom od editora (Notepad++), kopirati sadržaj i prenijeti ga u NetBeans.
13. Pokrenuti izvršavanje SQL komande. Ako je uspješno, nema poruke za pogreške.
14. Desnom tipkom na mišu otvoriti tablicu polaznici  i odabrati View Data... Pogledati upisane podatke.
15. Odabrati ikonu za dodavanje podataka, unijeti vlastite podatke te potvrditi s OK. Provjeriti jesu li upisani podaci.

 
 
Vježba\_06\_1: proširenje Java biblioteke za rad s različitim bazama podataka (MySQL, Java DB tj. Apache Derby)
 

1.  Kreiranje projekta vjezba\_06_1 kao Java biblioteka
2.  Kreiranje paket org.foi.nwtis.{LDAP_korisnik}.konfiguracije.bp
3.  Dodavanje biblioteke iz projekta vjezba\_03_2
4.  Kreiranje klase BP\_Konfiguracija koja implementira metode sučelja BP\_sucelje (priloženo nakon opisa vježbe) s konstruktorom BP_Konfiguracija(String datoteka). U konstruktoru se preuzima konfiguracija na temelju naziva datoteke putem KonfiguracijaApstraktna.preuzmiKonfiguraciju(naziv) te se pripremaju podaci koji će se vratiti putem metoda za :

    `adresu poslužitelja BP`
    `korisničko ime administratora BP`
    `lozinku administratora BP`
    `naziv BP za administratora`
    `korisničko ime korisnika BP`
    `lozinku korisnika BP`
    `naziv BP za korisnika`
    `upravljački program za bapu podataka` (na temelju podprotokola iz adrese odnosno poslužitelja BP ili iz adrese u parametru)
    `listu upravljačkih programa` (započinju ključem driver.database. )

5.  Klasi BP_Konfiguracija treba dodati varijable za spremanje podataka konfiguracije koje će odgovarati Java Bean modelu get/set naziva metoda.

6.  Sadržaj konfiguracijske datoteke može biti sljedeći:


`<?xml version="1.0" encoding="UTF-8" standalone="no"?>`
`<!DOCTYPE properties SYSTEM "http://java.sun.com/dtd/properties.dtd">`
`<properties>`
`<comment>NWTIS konfiguracija</comment>`
`<entry key="server.database">jdbc:mysql://192.168.15.1/</entry>`
`<entry key="admin.username">nwtis_admin</entry>`
`<entry key="admin.password">nwtis_foi</entry>`
`<entry key="admin.database">mysql</entry>`
`<entry key="user.password">g1_nwtis</entry>`
`<entry key="user.database">nwtis_g1</entry>`
`<entry key="driver.database.odbc">sun.jdbc.odbc.JdbcOdbcDriver</entry>`
`<entry key="driver.database.mysql">com.mysql.jdbc.Driver</entry>`
`<entry key="driver.database.derby">org.apache.derby.jdbc.ClientDriver</entry>`
`<entry key="driver.database.postgresql">org.postgresql.Driver</entry>`
`</properties>`


Vježba\_06_2: rad s različitim bazama podataka (MySQL, Java DB tj. Apache Derby)

1.  Kreiranje projekta vjezba\_06_2 kao Java aplikacija
2.  Kreiranje paket org.foi.nwtis.{LDAP_korisnik}.bp
3.  Dodavanje biblioteke iz projekta vjezba\_06_1
4.  Kreiranje klase Vjezba\_06\_2 kao Java main class, klasa ima metodu ispisiPodatke(String datoteka). Namjena: ispis stupaca ime i prezime iz tablice polaznici. Svi potrebni podaci trebaju se preuzeti iz datoteke postavki/konfiguracije na bazi metoda klase BP_Konfiguracija. Rad s resursima treba se temeljiti na obradi iznimke putem try-with-resource.
5.  Preuzeti primjere datoteka konfiguracija za MySQL (NWTiS.db.config\_1.xml) i JavaDB (NWTiS.db.config_1.xml)
6.  Podesiti dvije konfiguracije za izvršavanje programa tako da 1. ima argument NWTiS.db.config\_1.xml, a druga NWTiS.db.config_2.xml
7.  Izvršiti program s 1. konfiguracijom i s 2. konfiguracijiom
8.  Analiza pogreške u radu
9.  Dodavanje biblioteke za MySQL. Otvoriti projekt vjezba\_06_2, desnom tipkom na mišu otvoriti Libraries  i odabrati Add Library... Iz popisa globalnih biblioteka odabrati MySQL JDBC Driver (Slika 5.). 
10. Izvršiti program s 1. konfiguracijom (MySQL)
11. Dodavanje biblioteke za Java DB. Otvoriti projekt vjezba\_06_2, desnom tipkom na mišu otvoriti Libraries  i odabrati Add Library... U popisu globalnih biblioteka ne postoji Java DB odnodsno Derby JDBCDriver. U NetBeans Services/Databases otvoriti Drivers te desnom tipkom na mišu otvoriti Java DB (Network) i odabrati Customize (Slika 6. sadrži putanju na osobnom računalu nastavnika i ne odgovara putanji na računalima u dvorani ili osobnom računalu studenta). Kopirati naziv datoteke iz Driver File(s).
12. Otvoriti projekt vjezba\_06_2, desnom tipkom na mišu otvoriti Libraries  i odabrati Add Library... i odabrati Create... Za Libray Name upisati "Java DB Driver" (točno tako da odgovara na svim računalima, uključujući nastavničko na kojem će se ocjenjivati zadaće koje koriste taj driver!). Odabrati Add Jar/Folder... te prebaciti prethodno upamćeni naziv datoteke (Slika 7. sadrži putanju na osobnom računalu nastavnika i ne odgovara putanji na računalima u dvorani ili osobnom računalu studenta) ) i potvrditi s OK. I na kraju se s Add Library dodaje Java DB Driver biblioteka u projekt.
13. Izvršiti program s 2. konfiguracijom (Java DB)

Vježba\_06_3: rad s različitim bazama podataka (MySQL, Java DB tj. Apache Derby)

1.  Kreiranje projekta vjezba\_06_3 kao Java aplikacija
2.  Kreiranje paket org.foi.nwtis.{LDAP_korisnik}.bp
3.  Dodavanje biblioteke iz projekta vjezba\_06_1
4.  Kreiranje klase Vjezba\_06\_3 kao Java main class, klasa ima metodu kreirajTablicu(String datoteka). Može se brže uraditi tako da se klasa Vjezba\_06\_1 iz prethodnog projekta putem Refactor/Copy kopira s novim nazivom. Namjena: kreiranje tablice test\_{LDAP_korisnik} koja ima dva stupaca:  

        `kor_ime varchar(10) NOT NULL DEFAULT ''`
        `zapis varchar(250) NOT NULL DEFAULT ''`

5.  Za spajanje na poslužitelj baze podataka treba koristiti administratorsko korisničko ime i pripadajuću lozinku. Svi potrebni podaci trebaju se preuzeti iz datoteke postavki/konfiguracije na bazi metoda klase BP_Konfiguracija. Rad s resursima treba se temeljiti na obradi iznimke putem try-with-resource.
6.  Preuzeti primjere datoteka konfiguracija za MySQL (NWTiS.db.config\_1.xml) i JavaDB (NWTiS.db.config_1.xml)
7.  Podesiti dvije konfiguracije za izvršavanje programa tako da 1. ima argument NWTiS.db.config\_1.xml, a druga NWTiS.db.config_2.xml
8.  Izvršiti program s 1. konfiguracijom i s 2. konfiguracijiom
9.  Dodavanje biblioteke za MySQL.
10. Izvršiti program s 1. konfiguracijom (MySQL)
11. Dodavanje biblioteke za Java DB.
12. Izvršiti program s 2. konfiguracijom (Java DB)
