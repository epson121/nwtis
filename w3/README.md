
1.  Upoznavanje s NetBeans web mjestom: http://netbeans.org/
2.  Preuzimanje NetBeans (Java): http://netbeans.org/downloads/index.html (prethodno je preuzeto i nalazi se na lokalnom računalu unutar {NWTiS}).
3.  Instaliranje NetBeans na računalo (standardne postavke i Tomcat)
4.  Pokretanje NetBeans

* Vježba\_03\_1: upoznavanje s IDE NetBeans, kreiranje, kompiliranje i izvršavanje projekta

1.  Kreiranje 3. vježbe (direktorij {korisnik}\vjezba_03). U nastavku se direktorij za vježbu simbolički označava kao {vježba}).
2.  U NetBeans kreiranje projekta vjezba\_03\_1 kao Java aplikacije, na direktorij {vježba}, bez kreiranja glavne klase i kao glavni projekt
3.  Upoznavanje sa strukturom projekta u NetBeans (kartice Projects, Files)
4.  U Explorer-u postaviti se na direktorij src prethodne vježbe vjezba\_02\_1
5.  Kopiranje direktorija org u direktorij src u važećem projektu. Povratak u NetBeans
6.  Promijeniti naziv klase u Vjezba\_03\_1 putem Refactor - Rename
7.  Pripremiti projekt za izvršavanje (Build) i izvršiti (Run)
8.  Za projekt podesiti argumente za izvršavanje (Properties/Run)
9.  Napraviti nekoliko primjera konfiguracija (New, imenovati svaku od njih) i izvršiti

*   Vježba\_03\_2: kreiranje Java biblioteke klasa, rad s konfiguracijama na bazi klase Properties (http://docs.oracle.com/javase/7/docs/api/java/util/Properties.html)

1.  Kreiranje projekta vjezba\_03\_2 kao Java biblioteka klasa
2.  Kreiranje paket org.foi.nwtis.{LDAP_korisnik}.konfiguracije
3.  Kreiranje iznimke NemaKonfiguracije
4.  Kreiranje iznimke NeispravnaKonfiguracija
5.  Kreiranje sučelja Konfiguracija, javne metode:
        `public void ucitajKonfiguraciju() throws NemaKonfiguracije;`
        `public void ucitajKonfiguraciju(String datoteka) throws NemaKonfiguracije;`
        `public void spremiKonfiguraciju() throws NeispravnaKonfiguracija;`
        `public void spremiKonfiguraciju(String datoteka) throws NeispravnaKonfiguracija;`
        `public void dodajKonfiguraciju(Properties postavke);`
        `public void dodajKonfiguraciju(Konfiguracija konfig);`
        `public void kopirajKonfiguraciju(Properties postavke);`
        `public void kopirajKonfiguraciju(Konfiguracija konfig);`
        `public Properties dajSvePostavke();`
        `public Enumeration dajPostavke();`
        `public boolean obrisiSvePostavke();`
        `public String dajPostavku(String postavka);`
        `public boolean spremiPostavku(String postavka, String vrijednost);`
        `public boolean azurirajPostavku(String postavka, String vrijednost);`
        `public boolean postojiPostavka(String postavka);`
        `public boolean obrisiPostavku(String postavka);`
    
    Napomena: za brži rad (uključivanje potrebnih klasa) koristiti desnu tipku na mišu / Fix Imports, za formatiranje programskog koda koristiti desnu tipku na mišu / Format
6.  Kreiranje klase KonfiguracijaApstraktna, ima interne varijable String datoteka, Properties postavke, impementira sučelje Konfiguracija i metode:
        `public KonfiguracijaApstraktna(String datoteka);` - konstruktor
        `public void dodajKonfiguraciju(Properties postavke);`
        `public void dodajKonfiguraciju(Konfiguracija konfig);`
        `public void kopirajKonfiguraciju(Properties postavke);`
        `public void kopirajKonfiguraciju(Konfiguracija konfig);`
        `public Properties dajSvePostavke();`
        `public Enumeration dajPostavke();`
        `public boolean obrisiSvePostavke();`
        `public String dajPostavku(String postavka);`
        `public boolean spremiPostavku(String postavka, String vrijednost);`
        `public boolean azurirajPostavku(String postavka, String vrijednost);`
        `public boolean postojiPostavka(String postavka);`
        `public boolean obrisiPostavku(String postavka);`
        `public static Konfiguracija kreirajKonfiguraciju(String datoteka) throws NeispravnaKonfiguracija;` 
        kreira praznu konfiguraciju na bazi ekstenzije datoteke. Ako datoteka ima ekstenziju .xml onda se koristi klasa KonfiguracijaXML, inače KonfiguracijaTxt. Metoda se kasnije implementira do kraja (nakon što se završe podklase).
        `public static Konfiguracija preuzmiKonfiguraciju(String datoteka) throws NemaKonfiguracije;` 
        vraća konfiguraciju na bazi ekstenzije datoteke. Ako datoteka ima ekstenziju .xml onda se koristi klasa KonfiguracijaXML, inače KonfiguracijaTxt. Metoda se kasnije implementira do kraja (nakon što se završe podklase).
    Napomena: za brži rad koristiti desnu tipku na mišu / Insert Code... / Implement Method ... i odabrati metode koje treba implementirati.
7.  Kreiranje klase KonfiguracijaTxt, podklasa KonfiguracijaApstraktna i implementira metode (koristi se uobičajen format (metode store(...) i load(...))):
        `public KonfiguracijaTxt(String datoteka); - konstruktor`
        `public void ucitajKonfiguraciju() throws NemaKonfiguracije;`
        `public void ucitajKonfiguraciju(String datoteka) throws NemaKonfiguracije;`
        `public void spremiKonfiguraciju() throws NeispravnaKonfiguracija;`
        `public void spremiKonfiguraciju(String datoteka) throws NeispravnaKonfiguracija;`
8.  Kreiranje klase KonfiguracijaXML, podklasa KonfiguracijaApstraktna i implementira metode (koristi xml format (metode `storeToXML(...)` i `loadFromXML(...)`)):
        `public KonfiguracijaXML(String datoteka); - konstruktor`
        `public void ucitajKonfiguraciju() throws NemaKonfiguracije;`
        `public void ucitajKonfiguraciju(String datoteka) throws NemaKonfiguracije;`
        `public void spremiKonfiguraciju() throws NeispravnaKonfiguracija;`
        `public void spremiKonfiguraciju(String datoteka) throws NeispravnaKonfiguracija;`
9.  Pripremiti projekt-biblioteku za korištenje (Build)

* Vježba\_03_3: korištenje Java biblioteke, izvršavanje projekta, kreiranje datoteke konfiguracije

1.  Kreiranje projekta vjezba\_03_3 kao Java aplikacija
2.  Kreiranje paket org.foi.nwtis.{LDAP_korisnik}
3.  Dodavanje Java biblioteke iz prethodnog zadatka: desna tipka na mišu na Libraries / Add Project / Odabrati vjezba\_03\_2
4.  Kreiranje Java main klase Vjezba\_03\_3. Koristi 1 argument: datoteka. Kreira datoteku konfiguracije prema nazivu datoteke. Npr:
        NWTiS\_{LDAP\_korisnik}.txt
        NWTiS\_{LDAP\_korisnik}.xml

*   Vježba\_03\_4: korištenje Java biblioteke, izvršavanje projekta

1.  Kreiranje projekta vjezba\_03\_4 kao Java aplikacija
2.  Kreiranje paket org.foi.nwtis.{LDAP\_korisnik}
3.  Dodavanje Java biblioteke iz prethodnog zadatka: desna tipka na mišu na Libraries / Add Project / Odabrati vjezba\_03\_2
4.  Kreiranje Java mail klase Vjezba\_03\_4. Koristi maksimalno 3 argumenta: datoteka [postavka [vrijednost]]. Namjena prema broju argumenata:
        **   1 argument: ispisuje se sadržaj konfiguracijske datoteke tj. nazivi i vrijednosti svih postavki
        **   2 argumenta: ispisuje se vrijednost upisane postavke iz konfiguracijske datoteke
        **   3 argumenata: kreira se (ako ne postoji) ili ažurira upisana postavka s pridruženom vrijednosti u konfiguracijskoj datoteci
5.  Potrebno je kopirati datoteku koja je kreirana u prethodnom zadatku (kartica Files pa otvoriti prethodni zadatak i odabrati datoteku / Copy, zatim otvoriti važeći projekt i prenijeti datoteku / Paste)
    Npr.
        NWTiS\_{LDAP_korisnik}.txt
        NWTiS\_{LDAP_korisnik}.txt korisnik pero
        NWTiS\_{LDAP_korisnik}.txt lozinka 123456
        NWTiS\_{LDAP_korisnik}.txt korisnik
        NWTiS\_{LDAP_korisnik}.txt lozinka
        NWTiS\_{LDAP_korisnik}.xml
        NWTiS\_{LDAP_korisnik}.xml korisnik mato
        NWTiS\_{LDAP_korisnik}.xml lozinka 654321
        NWTiS\_{LDAP_korisnik}.xml korisnik
        NWTiS\_{LDAP_korisnik}.xml lozinka