1. INSTALACIJA JAMES MAIL POSLUŽITELJA


1.	preuzeti James verziju 3.0 s adrese
    http://james.apache.org/
    http://james.apache.org/download.cgi#Apache_James_Server
    (direktna adresa najsvježije verzije je https://repository.apache.org/content/repositories/snapshots/org/apache/james/james-server-app/3.0.0-beta5-SNAPSHOT/james-server-app-3.0.0-beta5-20130422.114628-70-app.zip)
instalirati ga na radni direktorij korisnika (npr: D:\NWTiS\grupa_1\)
2.	pokrenuti james (na bin direktoriju skriptu run.bat na Windows ili run.sh na Linux)
3.	postaviti se na bin direktorij u instalacijskom direktoriju james-a (cd /d  D:\NWTiS\grupa_1\apache-james-3.0-beta3\bin)
4.	izvršiti james-cli.bat kako bi se vidjele koje su komande na raspolaganju u james konzoli (slika 1)
5.	izvršiti james-cli.bat -h localhost listusers za ispis postojećih korisnika
6.	izvršiti james-cli.bat -h localhost adddomain nwtis.nastava.foi.hr za kreiranje nove domene nwtis.foi.hr
7.	izvršiti james-cli.bat -h localhost adduser servis@nwtis.nastava.foi.hr 123456 za kreiranje korisnika servis@nwtis.nastava.foi.hr s lozinkom 123456
8.	izvršiti james-cli.bat -h localhost adduser admin@nwtis.nastava.foi.hr 654321 za kreiranje korisnika admin@nwtis.nastava.foi.hr s lozinkom 654321
9.	kreirati nekoliko korisnika ({LDAP_korisnik} i 3 po izboru)
10.	izvršiti james-cli.bat -h localhost listusers za ispis postojećih korisnika
11.	u C:\WINDOWS\system32\drivers\etc\hosts dodati na kraj liniju
        127.0.0.1       nwtis.nastava.foi.hr
12.	pokrenuti Tomcat i/ili Glassfish i provjeriti http://nwtis.nastava.foi.hr:80 ili http://nwtis.nastava.foi.hr:8080 ili http://nwtis.nastava.foi.hr:8084 ovisno o postu za Tomcat i Glassfish



2. ZADACA 2.

Naziv: web aplikacija za rad e email porukama s lokaliziranim korisničkim sučeljem i provjerom email poruka u pozadinskoj dretvi

Naziv projekta: {LDAP_korisničko_ime}_zadaca_2

Klase i metode trebaju biti komentirane u javadoc formatu. Prije predavanja projekta potrebno je napraviti Clean na projektu. Zatim cijeli projekt sažeti u .zip (NE .rar) format s nazivom {LDAP_korisničko_ime}_zadaca_2.zip i predati u Moodle. Uključiti izvorni kod, primjere datoteka konfiguracijskih podataka i popunjeni obrazac za zadaću pod nazivom {LDAP_korisničko_ime}_zadaca_2.doc (u korijenskom direktoriju projekta).

Slušač aplikacije starta pozadinsku dretvu koja provjerava na poslužitelj (adresa i IMAP port određena konfiguracijom) u pravilnom intervalu (određen konfiguracijom u sek) ima li poruka u poštanskom sandučiću korisnika (adresa i lozinka određeni konfiguracijom, npr. servis@nwtis.nastava.foi.hr, 123456). Koristi se IMAP protokol. Poruke koje u predmetu(subject) započinju ključnom riječi (određena konfiguracijom, npr. MWTiS) obrađuju se tako da se ispituje sadržaj poruke. Poruka treba biti u MIME format i zatim treba pronaći njen dio koji je u text/plain formatu kako bi se provjerio njegov sadržaj koji može biti sa sljedećem sintaksom:

    USER korisnik
    PASSWORD lozinka
    GALERY galerija

Za poruku koje ima ispravnu sintaksu, treba autenticirati korisnika prema bazi podataka. Zatim se provjeravaju ostali dijelovi poruke koji imaju tip "image/*" ili  "application/octet-stream" (slikovne datoteke). Slikovnu datoteku treba zapisati na direktorij koji je primljen u poruci (npr. galerija) unutar direktorija web aplikacije (određen konfiguracijom, npr. NWTiS_galerije). Direktorije koji ne postoji treba kreirati. Poruka s neuspješnom autentikacijom ili neispravna poruka (format ili sintaksa ne odgovara) prebacuju se u svoju mapu (određena konfiguracijom, npr. NWTiS_NespravnePoruke). Ostale poruke prebacuju se svoju mapu (određena konfiguracijom, npr. NWTiS_OstalePoruke). Ako neka mapa ne postoji, dretva ju treba sama kreirati. Ispravne poruke prebacuju se u posebnu mapu (određena konfiguracijom, npr. NWTiS_IspravnePoruke).

Dretva na kraju svakog ciklusa šalje email poruku na adresu (određena konfiguracijom, npr. admin@nwtis.nastava.foi.hr), uz predmet (određen konfiguracijom, npr. Statistika poruka), u text/html formatu, i koja u sadržaju ima sljedeće elemente:

Obrada započela u: vrijeme_1 (dd.MM.yyyy hh.mm.ss.zzz)
Obrada završila u: vrijeme_2 (dd.MM.yyyy hh.mm.ss.zzz)
Trajanje obrade u ms: n
Sveukupan broj poruka: n - stalno se brojač povećava (to je ukupan broj svih poruka koju su pročitane u razdoblju od početka pokretanja aplikacije do kraja pojedinog ciklusa dretve)
Ukupan broj poruka: n - odnosi se na jedan prolaz
Broj ispravnih poruka: n - ispravne poruke
Broj neispravnih poruka: n - neautentične i neispravne poruke
Broj ostalih poruka: n - ostale poruke
Broj preuzetih datoteke: n - broj datoteka


Nakon slanja poruke, potrebno ju je spremiti u određeni direktorij (određen konfiguracijom, npr. Sent). Primjer na http://publib.boulder.ibm.com/infocenter/iseries/v5r3/index.jsp?topic=%2Frzatz%2F51%2Fprogram%2Fjmlexcode.htm

Potrebno je napraviti korisnički dio web aplikacije za izbor jezika za lokalizaciju, podršku listanja, čitanja i slanje email poruka. Prvo se provodi izbor jezika između raspoloživih jezika. Svi statički elementi u pogledima trebaju biti pripremljeni da se prikažu u skladu s izabranih jezikom. Slijedi obrazac za unos podataka za spajanje na email poslužitelj (adresa poslužitelja, korisničko ime, lozinka), s time da se prvi puta podaci za poslužitelj i korisničko ime preuzimanu iz konfiguracijske datoteke. U podnožju tablice nalazi se gumb za prikaz poruka i gumb za slanje email poruka. Potrebno je provesti validaciju podataka (min broj znakova i sl) uz ispis poruke o vrsti pogreške uz element kod kojeg je došlo do pogreške.

Kod odabira prikaza poruka nakon uspješne autentikacije na email poslužitelj, slijedi prikaz padajućeg izbornika s nazivim mapa, do njega je gumb za prijelaz na izabranu mapu, s time da se prvi puta postavlja na mapu Inbox. Slijedi preuzimanje n  (određen konfiguracijom, npr. 10) najsvježijih poruka i njihov prikaz (osnovne informacije: tko šalje, kada, predmet, vrsta formata, broj privitaka) uz straničenje. Uz ispis svake poruke dodaje se poveznica za prikaz sadržaja poruke. U zaglavlju tablice nalaze se prostori za unos podataka za filtriranje poruka (pošiljatelj, npr. pero@nwtis), predmet (npr. FOI) i sl. te gumb za filtriranje poruka. U podnožju tablice nalazi se gumb za povratak na unos podataka za povezivanje. Kod prikaza sadržaja poruke ispisuju se osnovne informacije: tko šalje, kada, predmet, sadržaj privitaka (naziv, vrsta i veličina) i sam sadržaj poruke (text/plain ili text/html). U zaglavlju tablice nalazi se gumb za povratak na popis poruka.

Kod odabira slanja poruka slijedi obrazac za unos podataka poruke (primatelj, predmet, vrsta (text/plain, text/html), sadržaj poruke. U podnožju tablice nalazi se gumb za slanje email poruke.



Preporučeni koraci:

    instalirati James (upute su nakon opisa zadaće)
    Kreiranje projekta {LDAP_korisnicko_ime}_zadaca_2  kao Java Web aplikaciju, server Glassfish, Java EE verzija: Java EE 6, kontekst {LDAP_korisnicko_ime}_zadaca_2 , (Frameworks: JSF), Libraries/Server Library: JSF 2.*, Configuration/JSF Servlet URL pattern: /faces/* , Prefered Page Language: Facelets
    kreirati pakete org.foi.nwtis.{LDAP_korisnik}.web.slusaci, org.foi.nwtis.{LDAP_korisnik}.web.filteri, org.foi.nwtis.{LDAP_korisnik}.web.kontrole, org.foi.nwtis.{LDAP_korisnik}.web.zrna
    kopirati datoteke konfiguracije iz projekta vjezba_08_1 s direktorija WEB-INF
    postaviti u web.xml parametar za konfiguracijsku datoteku
    dodati biblioteke iz projekta vjezba_03_2
    dodati biblioteke iz projekta vjezba_06_1
    dodati biblioteke Java DB JDBC Driver
    preuzeti klasu PrivitakPoruke za spremanje podataka o pojedinom privitku neke poruke
    preuzeti klasu Poruka za spremanje podataka o pojedinoj poruci
    kreirati slušaca konteksta SlusacAplikacije (anotirani bez uključivanja u web.xml) i kopirati kod funkcije iz vjezba_08_1
    kreirati klasu za dretvu (ObradaPoruka) koju će pokrenti metoda inicijalizacije kod slušača aplikacije, a prekinuti metoda brisanja kod slušača aplikacije.
    u run metodu kopirati dio programskog koda iz primjera s 14. predavanja (iz klase WebMail)  i izmijeniti ga tako da se podaci odnose na konfiguracijske podatke i sl. Može se koristiti ideja iz  http://www.hiteshagrawal.com/java/reading-imap-server-emails-using-java. Javadoc za klasu javax.mail.Folder http://docs.oracle.com/javaee/6/api/javax/mail/Folder.html
    izgraditi, isporučiti, izvršiti i testirati aplikaciju
    preporučuje se korištenje klijentskog programa za email poruke (Thunderbird, Outlook i sl. ili izraditi vlastiti) u kojem se može slati email poruka s privitcima na lokalni James poslužitelj.
    kreirati properties datoteku i18n (New/Other/Properties) u org.foi.nwtis.{LDAP_korisnik}. Za elemente koji se dodaju vrijedi pravilo da prvo dolazi naziv pogleda npr. index, zatim _ pa naziv elementa. Npr. index_naslov=Početna stranica, index_izborJezika=Izaberi jezik
    dodati lokalizacije za en i de.
    dodati u JSF konfiguraciju za i18n (bundle, varijabla m). Primjer na predavanjima prikazuje potrebe elemente ili pogledati http://jdevelopment.nl/internationalization-jsf-utf8-encoded-properties-files/, http://www.tutorialspoint.com/jsf/jsf_internationalization.htm
    kreirati JSF ManageBean Lokalizacija (New/JSF(JSF Manage Bean) (bez uključivanja u web.xml), scope: session
    kreirati varijable za podržane jezike (Map<String, String>), za odabrani jezik (String) i važeću lokalizaciju (Locale)
    kreirati setter i getter za odabrani jezik i getter za jezike i važeću lokalizaciju
    kreirati funkciju koja će biti akcija za preuzimanje odabranog jezika public Object odaberiJezik(). Može se koristiti ideja iz http://jdevelopment.nl/internationalization-jsf-utf8-encoded-properties-files/ ili  iz  http://courses.coreservlets.com/Course-Materials/pdf/jsf/jsf2/JSF2-Event-Handling.pdf
    u index.xhtml dodati obrazac u kojem će biti radio za izbor jezika i gumb za slanje uz akciju na prethodnu funkciju. Postaviti za lokalizaciju <f:view locale=...>
    izgraditi, isporučiti, izvršiti i testirati aplikaciju
    kreirati JSF ManageBean EmailPovezivanje (New/JSF(JSF Manage Bean) (bez uključivanja u web.xml), scope: session
    kreirati privatne varijable za adresu poslužitelja, korisničko ime i lozinku
    kreirati settere i gettere za varijable
    kreirati funkciju public String saljiPoruku() koja će biti akcija za prijelaz na slanjePoruke.xhtml. Vraća OK.
    kreirati funkciju public String citajPoruke() koja će biti akcija za prijelaz na pregledSvihPoruka.xhtml. Vraća OK.
    kreirati JSF emailPostavke.xhtml
    kreirati obrazac za unos potrebnih podataka za prijavljivanje i gumbovi za slanje uz akcije na prethodne funkcije
    kreirati JSF slanjePoruke.xhtml
    kreirati obrazac za unos potrebnih podataka za slanje email poruke (prima, šalje, predmet, sadržaj)
    kreirati JSF MenagesBean SlanjePoruke, scope: session
    postaviti preuzimanje parametara iz obrasca emailPostavke.xhtml kod poziva akcije citajPoruke(). Postoje drije mogućnost:
    a) za svaki parametar koristi se <f:param name="naziv" value="vrijednost"/>, a preuzima se putem FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("naziv");
    b) direktno povezivanje na Manage Bean putem  EmailPovezivanje ep = (EmailPovezivanje)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("emailPovezivanje");
    kreirati funkciju public String saljiPoruku() za slanje upisane poruke. Ako je u redu, ispisuje se poruka da je poruka uspješno poslana, inače tekst progreške. Dodati varijablu za poruku u MB i mjesto u obrascu.
    kreirati JSF ManegedBean PregledSvihPoruka, scope: request
    kreirati varijable za povezivanje na sandučić korisnika, popis poruka, za filtriranje poruka
    postaviti preuzimanje parametara iz obrasca emailPostavke.xhtml kod poziva akcije citajPoruke()
    kreirati funkciju public String filtrirajPoruke() koja će biti akcija za filtiranje poruka. Vraća OK, NOT_OK ili ERROR. Primjer za filtriranje poruka http://www.codejava.net/java-ee/javamail/using-javamail-for-searching-e-mail-messages , http://stackoverflow.com/questions/870045/java-imap-fetch-messages-since-a-date
    kreirati funkciju public String pregledPoruke() koja će biti akcija za pregled izabrane poruke. Vraća OK, NOT_OK ili ERROR.
    kreirati funkciju public String obrisiPoruku() koja će biti akcija za brisane izabrane poruke. Vraća OK, NOT_OK ili ERROR.
    kreirati JSF pregledSvihPoruka.xhtml
    kreirati obrazac u kojem se koristi <h:dataTable ...> za prikaz poruka. Dodati polja za filtriranje i gumb za slanje uz akcije na prethodne funkcije  Postaviti vizualno organiziranje sadržaja uz korištenje <h:panelGrid...> Može se koristiti ideja iz  http://www.jsftoolbox.com/documentation/help/12-TagReference/html/h_panelGrid.html .  Za pojedinu poruku koristi se objekt klase Poruka (izvorni kod nalazi se nakon opisa zadaće).
    dodati navigacijska pravila za prethodne poglede
    izgraditi, isporučiti, izvršiti i testirati aplikaciju
    dodati converter <f:convertDateTime pattern="..."/> za vrijeme prijema poruka
    dodati poveznicu za pregled pojedine poruke koja poziva akciju pregledPoruke() uz prijenos parametara za poruku
    kreirati JSF ManegedBean PregledPoruke, scope: session
    kreirati varijablu za poruku. Koristiti klasu PrivitakPoruke (izvorni kod nalazi se nakon opisa zadaće).
    kreirati funkcije public String povratakPregledSvihPoruka() koja će biti akcija za vraćane na popis svih poruka. Vraća OK.
    kreirati JSF pregledPoruke.xhtml
    kreirati obrazac u kojem se prikazuju podaci izabrane poruke i koristi se <h:dataTable ...> za prikaz privitaka poruke. Dodati gumb za povratak na pregled svih poruka i gumb za brisanje poruke (tj. prijenos u mapu Trash). Nakon uspješnog brisanje prelazi se na  pregled svih poruka.
    izgraditi, isporučiti, izvršiti i testirati aplikaciju

http://java.sun.com/developer/onlineTraining/JavaMail/contents.html

http://www.coreservlets.com/JSF-Tutorial/jsf2/

Ako se želi koristiti SSL za povezivanje na IMAP poslužitelj, ideja na http://www.jguru.com/faq/view.jsp?EID=329193