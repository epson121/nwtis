Naziv: enterprise aplikacija za rad s bazom podataka putem ORM uz korištenje JPA (bez SQL, koristi se Criteria API), uz korisničku stranu s JSF na bazi predloška, filtriranje gradova, izbor zip kodova uz Ajax i sl. Svi prikazi trebaju imati abecedni popis elemenata. Određeni gradovi (npr. Washington, Lexington i sl.) javljaju se u više država tako da je potrebno uz naziv grada dodati naziv/kraticu države iz koje je grad.

Klase i metode trebaju biti komentirane u javadoc formatu. Prije predavanja projekta potrebno je napraviti Clean na projektu. Zatim cijeli projekt sažeti u .zip (NE .rar) format s nazivom {LDAP_korisničko_ime}_zadaca_4.zip i predati u Moodle. Uključiti izvorni kod, primjere datoteka konfiguracijskih podataka i popunjeni obrazac za zadaću pod nazivom {LDAP_korisničko_ime}_zadaca_4.doc (u korijenskom direktoriju projekta).

Naziv projekta: {LDAP_korisničko_ime}_zadaca_4

1.	Kreiranje direktorija {LDAP_korisničko_ime}_zadaca_4. U nastavku se direktorij za vježbu simbolički označava kao {vježba}.
2.	preuzeti datoteku NWTiS_2012.zip (nalazi se nakon opisa vježbe/zadaće)
3.	smjestiti se na direktorij JavaDB baza podataka (u Windows Exploreru odnosno komandnom promptu). Pogledati u Services/Java DB/Properties/Database Location: (Slika 1)
na direktoriju raspakirati datoteku NWTiS_2012.zip
4.	startati Java DB
5.	spojiti se na NWTiS_2012 (Korisničko ime: NWTiS lozinka: foi2009)
6.	postaviti se na shemu NWTiS i postaviti da je osnovna (Set as Default Schema)
7.	provjeriti sadržaj sheme NWTiS (Treba biti kao na Slici 2.)
 

{LDAP_korisničko_ime}_zadaca_4_1: Kreiranje modula za podatkovni sloj s EJB: EntityBean - JPA

1.	Kreiranje projekta {LDAP_korisničko_ime}_zadaca_4_1 kao Java EE/EJB ModuleWeb aplikaciju, server Glassfish, Java EE verzija: Java EE 6, kontekst / Enable Context and Depencency Injection: Da
2.	kreirati pakete org.foi.nwtis.{LDAP_korisnik}.ejb.eb, org.foi.nwtis.{LDAP_korisnik}.ejb.sb
3.	kreirati perzistantnu jedinicu (New/Other/Persistence/Persistence Unit) . Slike 3, 4 i 5 prikazuju aktivnosti koje se provode: određivanje naziva: {LDAP_korisničko_ime}_zadaca_4_1PU, kreiranje izvora podataka (Data source) pod nazivom jdbc/NWTiS_2012 za ranije kreranu vezu prema bazi podataka NWTiS_2012. Slika 6 prikazuje podatke kreirane perzistente jedinice.
4.	kreirati shemu baze podataka (New/Other/Persistence/Database Schema). Naziv: NWTiS_2012 (Slika 7) na src/conf i odabrati ranije kreranu vezu prema bazi podataka NWTiS_2012 (Slika 8). Slijedi odabir svih tablica (Slika 9). Struktura sheme baze podataka prikazana je na slici 10. Služi nam kao pomoć i nije nužno to obavljati. 
5.	kreirati klase na entitete (New/Other/Persistence/Entity Classes from Database) na org.foi.nwtis.{LDAP_korisnik}.ejb.eb. Slijedi odabir slih tablica s time da se može odabrati kao osnova izvor podataka jdbc/NWTiS_2012 (Slika 11) ili shema baze podataka NWTiS_2012 ako smo ju kreirali u projektu (Slika 12). Preslikavanja tablica baze podataka u klase prikazana je na slici 13. I na kraju se određuju elementi preslikavanja (Slika 14)
6.	kreirati session bean-nove (Facade) za klase entiteta (New/Other/Persistence/Session Beans for Entity Classes) na org.foi.nwtis.{LDAP_korisnik}.ejb.sb. Slijedi odabir klasa (Slika 15) za koje će se generirati fasade (Slika 16)
 

{LDAP_korisničko_ime}_zadaca_4_2: Kreiranje modula za poslovnu logiku i korištenje EJB: SessionBean (Stateful, Stateless i Singleton)

1.	kreirati paket org.foi.nwtis.{LDAP_korisnik}.ejb.sb
2.	kreirati novog klijenta web servisa (New/Other/Web Services/Web Service Client) za Web servis koji ima adresu WSDL-a http://api.wxbug.net/weatherservice.asmx?WSDL, a klase se generiraju u standardni paket
3.	kreirati Stateless SessionBean WeatherBugKlijent (New/Other/Enterprise JavaBeans/Timer Session Bean/Session Type: Stateless/Create Interface (označiti ni jedan)) u org.foi.nwtis.{LDAP_korisnik}.ejb.sb
4.	dodati privatnu varijablu sa svojim WeatherBug kodom
5.	napraviti poziv operacije getLiveWeatherByUSZipCode na web servisu WeatherBug (Insert Code/Call Web Service Operation...)
6.	kreirati funkciju public LiveWeatherData dajMeteoPodatke(String zip) koja poziva generiranu metodu a koristi prenešeni zip kode, UnitType.METRIC i korisnički kod za WeatherBug web servis (iz konfiguracijske datoteke)
 

{LDAP_korisničko_ime}_zadaca_4: Kreiranje Enterprise aplikacije

1.	Kreiranje projekta {LDAP_korisničko_ime}_zadaca_4 kao Enterprise Application, server Glassfish, Java EE verzija: Java EE 6, Enable Context and Depencency Injection: Da, Create EJB Module: NE, Create Web Application Module: NE
2.	otvoriti projekt
na Java EE Modules, Add Java EE Module... Označiti: {LDAP_korisničko_ime}_zadaca_4_1, {LDAP_korisničko_ime}_zadaca_4_2 i OK
 

{LDAP_korisničko_ime}_zadaca_4_3: Kreiranje web modula za korisničko sučelje

1.	Kreiranje {LDAP_korisničko_ime}_zadaca_4_3 kao Java Web aplikaciju, za Enterprise aplikaciju: {LDAP_korisničko_ime}_zadaca_4, server Glassfish, Java EE verzija: Java EE 6, kontekst /{LDAP_korisničko_ime}_zadaca_4_3, Enable Context and Depencency Injection: Da, (Frameworks: JSF), Libraries/Server Library: JSF 2.0, Configuration/JSF Servlet URL pattern: /faces/* , Prefered Page Language: Facelets
2.	kreirati paket org.foi.nwtis.{LDAP_korisnik}.web.zrna
3.	kreirati predložak pod nazivom predlozak.xhtml (New/Others/JavaServer Faces/Facelets Template) Layout Style: CSS, izbor izgleda sa zaglavljem, podnožjem i lijevim izbornikom.  U <title>...</title> umetnuti <ui:insert name="naslov">Naslov</ui:insert> tako da se naslov može postaviti u pojedinom pogledu.
4.	kreirati JSF stranicu za korištenje predloška pod nazivom pregledPrognoza.xhtml (New/Others/JavaServer Faces/Facelets Template Client), odabrati predložak predlozak.xhtml, Generated Root Tag: <html>. Kopirati jedan od <ui:define...>...</ui:define> i za staviti name="naslov". Sam naslov staviti prema želji. Sadržaj za top staviti NWTiS Zadaća 4, za left poveznicu na index.xhtml, za bottom ime prezime &copy; 2013
5.	selektirati sadržaj pregledPrognoza.xhtml i kopirati u index.xhtml . Promijeniti potrebne elemente (naslov, poveznicu,...) 
6.	izgraditi i izvršiti aplikaciju {LDAP_korisničko_ime}_zadaca_4. Provjeriti izgled oba pogleda.
7.	kreirati JSF ManageBean OdabiZipKodovaZaGradove (scope po vlastitom izboru ;=)
8.dodati varijable za popis država, popis gradova, popis zip kodova, listu odabranih država, listu odabranih gradova, listu odabranih zip kodova
9.	dodati settere i gettere za varijable
10.	dodati poziv EJB StatesFacade (Insert Code .../Call Enterprise Bean i odabrati StatesFacade)
11.	dodati poziv EJB CitiesFacade (Insert Code .../Call Enterprise Bean i odabrati CitiesFacade)
12.	dodati poziv EJB ZipCodesFacade (Insert Code .../Call Enterprise Bean i odabrati ZipCodesFacade)
13.	u tablici CITIES primarni ključ se sastoji od šifre države, naziva okruga i naziva grada tako da se kod prikaza grada treba staviti sva tri elementa (država - okruga - grada). Poznato je da u SAD postoje gradovi koji se javljaju više puta unutar jedna zemlje, a onda i više puta unutar SAD. Pretraživanje je potrebno po nazivu grada. Može se dodati i pretraživanje po nazivu okruga.
14.	dodati funkciju public String dodajDrzave() koja će dodati odabrane države u listu odabranih država.
15.	dodati funkciju public String obrisiDrzave() koja će obrisati odabrane države iz liste odabranih država.
16.	dodati funkciju public String dodajGradove() koja će dodati odabrane gradove u listu odabranih gradova.
17.	dodati funkciju public String obrisiGradove() koja će obrisati odabrane gradove iz liste odabranih gradova.
18.	dodati funkciju public String dodajZipKodove() koja će dodati odabrane zip kodove u listu odabranih zip kodova.
19.	dodati funkciju public String obrisiZipKodove() koja će obrisati odabrane zip kodove iz listu odabranih zip kodova.
20.	dodati funkciju public String preuzmiGradove() koja će prikazati gradove na bazi liste odabranih država.
21.	dodati funkciju public String preuzmiZipKodove() koja će prikazati zip kodove na bazi liste odabranih gradova
22.	dodati funkciju public String preuzmiMeteoPodatke() koja će prikazati meteo podatke na bazi liste odabranih zip kodova.
23.	u StatesFacade, CitiesFacade, ZipCodesFacade treba dodati metode za preuzimanje podataka na bazi filtiranja i liste odabranih elemenata prethodne razine (npr. kod gradova treba uzeti u obzir listu odabranih država)
24.	u pregledPrognoza.xhtml dodati obrazac koji sadrži tablicu s više stupaca (slika 17).
25.	Prvi stupac započinje jednolinijskim tekstom za filtriranje država na bazi Ajax-a. Slijedi padajući izbornik  (id="drzave") sa 7 prikazanih redaka. U izborniku se može odabrati jedna ili više država. Upisom znaka za filtar države (događaj keyup) pokreće se filtriranje država te se ažurira padajući izbornik liste odabranih država. Jedan gumb ima akciju koja Ajax-om puni listu odabranih država u obliku padajućeg izbornika  (id="odabraneDrzave") sa 7 prikazanih redaka na bazi naziva odabranih država iz filtriranog/lijevog dijela. U izborniku se može odabrati jedna ili više država. Drugi gumb briše odabrane države iz liste odabranih država. Treći gumb preuzima gradove za odabrane države i puni padajući izbornik (id="gradovi") sa 7 prikazanih redaka i izvršava padajući izbornik za države (id="odabraneDrzave"). 
26.	Drugi stupac započinje jednolinijskim tekstom za filtriranje pribavljenih gradova na bazi Ajax-a. Slijedi padajući izbornik  (id="gradovi") sa 7 prikazanih redaka. U izborniku se može odabrati jedan ili više gradova. Upisom znaka za filtar grada (događaj keyup) pokreće se filtriranje gradova te se ažurira padajući izbornik liste odabranih gradova. Jedan gumb ima akciju koja Ajax-om puni listu odabranih gradova u obliku padajućeg izbornika  (id="odabraniGradovi") sa 7 prikazanih redaka na bazi naziva odabranih gradova iz filtriranog/lijevog dijela. U izborniku se može odabrati jedna ili više gradova. Drugi gumb briše odabrane gradove iz liste odabranih gradova. Treći gumb preuzima zip kodove za odabrane gradove i puni padajući izbornik (id="zipKodovi") sa 7 prikazanih redaka i izvršava padajući izbornik za gradove (id="odabraniGradovi"). 
27.	Treći stupac započinje jednolinijskim tekstom za filtriranje pribavljenih zip kodova na bazi Ajax-a. Slijedi padajući izbornik  (id="zipKodovi") sa 7 prikazanih redaka. U izborniku se može odabrati jedan ili više zip kodova. Upisom znaka za filtar zip kodova (događaj keyup) pokreće se filtriranje zip kodova te se ažurira padajući izbornik liste odabranih zip kodova. Jedan gumb ima akciju koja Ajax-om puni listu odabranih zip kodov u obliku padajućeg izbornika  (id="odabraniZipKodovi") sa 7 prikazanih redaka na bazi naziva odabranih zip kodova iz filtriranog/lijevog dijela. U izborniku se može odabrati jedna ili više zip kodova. Drugi gumb briše odabrane zip kodove iz liste odabranih zip kodova. Treći gumb preuzimameteo podatke za odabrane zip kodove i puni tablicu (id="meteoPodaci") i izvršava padajući izbornik za zip kodove (id="odabraniZipKodovi"). 
28.	Aktivnosti se obavljaju prema slici 18.
29.	Preporučuje se http://www.coreservlets.com/JSF-Tutorial/jsf2/#Ajax