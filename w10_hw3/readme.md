Naziv: sustav aplikacija s korištenjem web servisa WeatherBug, kreiranje vlastitog web servisa na bazi WeatherBug i njegovo korištenje, kreiranje RESTful servisa na bazi WeatherBug i njegovo korištenje. Izbor gradova iz tablice mycities za čije se zip kodove ispisuju važeći meteo podaci.
Klase i metode trebaju biti komentirane u javadoc formatu. Prije predavanja projekta potrebno je napraviti Clean na projektu. Zatim cijeli projekt sažeti u .zip (NE .rar) format s nazivom {LDAP_korisničko_ime}_zadaca_3.zip i predati u Moodle. Uključiti izvorni kod, primjere datoteka konfiguracijskih podataka i popunjeni obrazac za zadaću pod nazivom {LDAP_korisničko_ime}_zadaca_3.doc (u korijenskom direktoriju projekta).


Naziv projekta: {LDAP_korisničko_ime}_zadaca_3


Kreiranje 11. vježbe/zadaće 3 (direktorij {LDAP_korisničko_ime}_zadaca_3). U nastavku se direktorij za vježbu simbolički označava kao
{vježba}).

1.	Kreiranje projekta {LDAP_korisničko_ime}_zadaca_3_1 kao Java Web aplikaciju, server Tomcat 7.*, Java EE verzija: Java EE 6, kontekst {LDAP_korisničko_ime}_zadaca_3_1, (Frameworks: Ne)
2.	kreirati pakete org.foi.nwtis.{LDAP_korisnik}.ws.klijenti, org.foi.nwtis.{LDAP_korisnik}.ws.serveri, org.foi.nwtis.{LDAP_korisnik}.rest.serveri
3.	kreirati novog klijenta web servisa (New/Other/Web Services/Web Service Client) za Web servis koji ima adresu WSDL-a http://api.wxbug.net/weatherservice.asmx?WSDL, a klase se 	generiraju u standardni paket
4.	kreirati klasu WeatherBugKlijent u paketu org.foi.nwtis.{LDAP_korisnik}.ws.klijenti
5.	napraviti poziv operacije getLiveWeatherByUSZipCode na web servisu WeatherBug (Insert Code/Call Web Service Operation...)
6.	kreirati funkciju public LiveWeatherData dajMeteoPodatke(String zip) koja poziva generiranu metodu a koristi prenešeni zip kode, UnitType.METRIC i korisnički kod za WeatherBug web servis (iz web.xml, parametar konteksta "wb_code")
7.	dodati privatne varijable String zip i LiveWeatherData meteoPodatak te prvom pridružiti gettera i settera, a drugom samo gettera i postaviti da poziva gornju metodu.
8.	kreirati novi web servis MeteoWS (New/Other/Web Services/Web Service)  u paketu org.foi.nwtis.{LDAP_korisnik}.ws.serveri
9.	slijedi prikaz dijaloškog okvira (Slika 1.) u vezi uključivanja METRO web servisa u projekt. Odabrati Yes.
10.	otvoriti klasu MeteoWS i odabrati Design
11.	obrisati operaciju hello
12.	dodati operaciju dajMeteoWSPodatkeZaZip(String zip) a vraća LiveWeatherData (Potražiti klasu putem Browse)
13.	implementirati metodu na bazi korištenja metoda klase WeatherBugKlijent
14.	dodati operaciju dajMeteoWSPodatkeZaViseZip(List<String> zipovi), a vraća List<LiveWeatherData> (Potražiti klasu putem Browse)
15.	implementirati metodu na bazi korištenja metoda klase WeatherBugKlijent
16.	provjeriti web servis i WSDL http://localhost:xxxx/{LDAP_korisničko_ime}_zadaca_3_1/MeteoWS?WSDL
17.	testirati operaciju web servisa. Kartica Services/Web Services pa desna tipka na mišu Create Group/NWTiS, desna tipka na mišu pa Add Web Service... i kopirati adresu WSDL-a. 18.	18.	Otvoriti web servis i odabrati operaciju, desna tipka na mišu i Test Method... upisati 40300 i druge zip kodove
19.	kreirati RESTful web servis u paketu org.foi.nwtis.{LDAP_korisnik}.rest.serveri (Other/Web Services/RESTful Web Services from Patterns/Container-Item/ (Slika 2) s nazivom resursa MeteoREST,  klasa MeteoREST, klasa za kontejner MeteoREST_Container, putanja {zip} i putanja kontejnera /meteoREST, tip text/html.(Slika 3.) Odabrati Create default Jersey REST servlet adaptor in web.xml (Slika 4.)
20.	u klasi MeteoREST_Container u metodi getHtml staviti da vraća "OK". Ta metoda nam nema koristi.
21.	u klasi MeteoREST  metodi getHtml dodati poziv metode MeteoServisKlijent.dajMeteoWSPodatkeZaZip(zip) te vraćanje formatiranih podataka u html-u
22.	izgraditi i isporučiti aplikaciju
23.	testirati RESTful web servis (RESTFul Test Services/Test RESTFul Test Services)
24.	izvršiti RESTful http://localhost:xxxx/{LDAP_korisnicko_ime}_zadaca_3_1/resources/meteoREST/40300 i druge zip kodove
25.	Kreiranje projekta {LDAP_korisničko_ime}_zadaca_3_2 kao JavaAplikaciju
26.	kreirati pakete org.foi.nwtis.{LDAP_korisnik}.ws.klijenti,  org.foi.nwtis.{LDAP_korisnik}.rest.klijenti
27.	kreirati glavnu klasu Zadaca_3_2, koja će u main metodi imati više argumenata od kojih prvi služi za određivanje vrste servisa (1 - MeteoWS, 2 - MeteoREST) 
28.	kreirati novog klijenta web servisa (New/Other/Web Services/Web Service Client) za Web servis MeteoWS koji ima adresu WSDL na bazi projekta {LDAP_korisničko_ime}_zadaca_3_1
29.	napraviti poziv operacije dajMeteoWSPodatkeZaZip na web servisu MeteoWS (Insert Code/Call Web Service Operation...)
30.	dodati uvjet za poziv generirane metode i proslijediti kao parametar upisani argument iz komandne linije
31.	izgraditi, isporučiti, izvršiti i testirati aplikaciju uz nekoliko konfiguracija s parametrima (npr. 1 10000, 1 40200, 1  90420)
32.	napraviti poziv operacije dajMeteoWSPodatkeZaViseZip na web servisu MeteoWS (Insert Code/Call Web Service Operation...)
33.	dodati uvjet za poziv generirane metode i proslijediti kao parametre upisane argumente iz komandne linije
34.	izgraditi, isporučiti, izvršiti i testirati aplikaciju uz nekoliko konfiguracija s parametrima (npr. 1 10000 2000 40200 90420, 40100 40300 40500 40700)
35.	kreirati novog klijenta REST servisa (New/Other/Web Services/RESTful Java Client) za MeteoREST na bazi projekta {LDAP_korisničko_ime}_zadaca_3_1 pod nazivom MeteoRESTKlijent
36.	napraviti poziv operacije getHtml na REST servisu MeteoRESTKlijent
37.	izgraditi, isporučiti, izvršiti i testirati aplikaciju uz nekoliko konfiguracija s parametrima (npr. 2 10000, 2 40200, 2  90420)
38.	Kreiranje projekta {LDAP_korisnicko_ime}_zadaca_3_3 kao Java Web aplikaciju, server Glassfish, Java EE verzija: Java EE 6, kontekst {LDAP_korisnicko_ime}_zadaca_3_3 , (Frameworks: JSF), Libraries/Server Library: JSF 2.*, Configuration/JSF Servlet URL pattern: /faces/* , Prefered Page Language: Facelets
kreirati pakete org.foi.nwtis.{LDAP_korisnik}.ws.klijenti,  org.foi.nwtis.{LDAP_korisnik}.rest.klijenti, org.foi.nwtis.{LDAP_korisnik}.web.slusaci, org.foi.nwtis.{LDAP_korisnik}.web.zrna
39.	kreirati novog klijenta web servisa (New/Other/Web Services/Web Service Client) za Web servis MeteoWS koji ima adresu WSDL na bazi projekta {LDAP_korisnicko_ime}_zadaca_3_1
40.	kreirati klasu MeteoWSKlijent u paketu org.foi.nwtis.{LDAP_korisnik}.ws.klijenti
41.	napraviti poziv operacije dajMeteoWSPodatkeZaZip na web servisu MeteoWS (Insert Code/Call Web Service Operation...)
42.	promijeniti za metodu iz private u public
43.	dodati potrebne slušače, datoteke konfiguracijskih podataka, biblioteke iz ranijih vježbi i sl.
44.	kreirati JSF MenagesBean OdabirZipKodova (razmisliti primjeni session ili request)
45.	dodati potrebne varijable za realizaciju pogleda (List<String> zipKodovi, String zipKodDodaj, List<String> odabraniZipKodovi, String zipKodBrisi, List<LiveWeatherData> meteoWSPodaci, String meteoRESTPodaci) i njihove gettere i settere
46.	zipKodovi se pune iz tablice mycities iz Java DB, optimirati preuzimanje podataka iz baze podataka
47.	otvoriti Services/Databases, odabrati vezu na NWTiS u Java DB, desnom tipkom na mišu odabrati Connect, otvoriti NWTiS. desnom tpkom na mišu odabrati Execute Command, preuzeti datoteku mycities.sql (nalazi se nakon opisan vježbe), kopirati njen sadržaj u NetBeans i izvršiti
48.	kreirati metodu String dodajZipKod() koja dodaje izabrani zip kod u odabrane zip kodove
49.	kreirati metodu String brisiZipKod() koja briše izabrani zip kod iz odabranih zip kodova
50.	kreirati metodu String dajMeteoWSPodatke() koja preuzima meteo podatke za odabrane zip kodove putem metode klase MeteoWSKlijent. Validacija treba osigurati izbor minimalno 5, a maksimalno 10 zip kodova.
51.	kreirati metodu String dajMeteoRESTPodatke() koja preuzima meteo podatke za odabrani zip kod putem metode klase MeteoRESTKlijent.
52.	kreirati JSF odabirZipKodova.xhtml
53.	dodati obrazac u kojem se može odabrati zip kod iz padajućeg izbornika sa 7 vidljivih redaka i jednim izborom (iz tablice mycities), gumb za poziv akcije dodajZipKod(), padajući izbornik sa 7 vidljivih redaka za odabrane zip kodove s izborom jednog, gumb za akciju brisiZipKod(), gumb za akciju dajMeteoWSPodatke().
54.	dodati tablicu za prikaz meteo podataka (grad, širina, visina, temp, vlaga).
55.	dodati element u kojem će se prikazati sadržaj REST poziva
56.	dodati direktnu poveznicu (bez faces-config.xml) u index.xhtml na odabirZipKodova.xhtml
57.	izgraditi i isporučiti aplikaciju
