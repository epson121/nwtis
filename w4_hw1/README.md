*   Naziv: višedretveni sustav vremenskog upravljanja putem socket-a

*   Naziv projekta: {LDAP\_korisničko\_ime}\_zadaca_1

Sve nove klase trebaju biti u paketu org.foi.nwtis.{LDAP\_korisničko\_ime}.zadaca\_1. Za rad s postavkama treba koristiti Java biblioteku iz vjezba\_03\_2. Klase i metode trebaju biti komentirane u javadoc formatu. Prije predavanja projekta potrebno je napraviti Clean na projektu. Zatim cijeli projekt (korijenski direktorij treba biti {LDAP\_korisničko\_ime}\_zadaca\_1) sažeti u .zip (NE .rar) format s nazivom {LDAP\_korisničko\_ime}\_zadaca\_1.zip i predati u Moodle. Uključiti izvorni kod, primjere datoteka konfiguracijskih podataka (.txt i .xml) i popunjeni obrazac za zadaću pod nazivom {LDAP\_korisničko\_ime}\_zadaca_1.doc (u korijenskom direktoriju projekta).

Program započinje utvrđivanjem vrste rada (server, admin, user, show) na bazi parametara:

    -server -port port -konf datoteka[.txt | .xml] [-load] -s datoteka

Primitivni server vremena -  ima ulogu socket servera na određenom portu (postavka port). Ako je upisan parametar -load, server kod pokretanja učitava serializiranu evidenciju iz datoteke (postavka evidencija) i zatim čeka. Kad primi zahtjev od klijenta zapisuje podatke u svoju evidenciju. Za podatke treba koristiti vlastitu klasu koja sadrži varijable za vrijeme, komandu, odgovor. Zahtjev se temelji na komandama putem socketa (isključivo u jednom retku).

-admin -ts ipadresa -port port -u korisnik -p lozinka -konf datoteka[.txt | .xml] [-t dd.mm.yyyy hh:mm:ss | [PAUSE | START | STOP | CLEAN]]

Administrator servera vremena - šalje mu komandu putem socketa koja od njega traži izvršavanja određene akcije:

    USER korisnik; PASSWD lozinka; SETTIME dd.mm.yyyy hh:mm:ss; -provjerava se u konfiguraciji da li postavka admin odgovara korisniku kao i lozinka. Ako je u redu postavlja se novo interno vrijeme na bazi zahtjevanog, a evidentira se kao razlika stvarnog vremena i zahtjevanog. Klijentu se vraća odgovor OK. Kada nije u redu vraća se odgovor ERROR tekst (tekst objašnjava razlog pogreške).

    USER korisnik; PASSWD lozinka; PAUSE; -provjerava se u konfiguraciji da li postavka admin odgovara korisniku kao i lozinka. Ako je u redu privremeno prekida prijem korisničkih komandi. Klijentu se vraća odgovor OK. Kada nije u redu vraća se odgovor ERROR tekst (tekst objašnjava razlog pogreške).

    USER korisnik; PASSWD lozinka; START; -provjerava se u konfiguraciji da li postavka admin odgovara korisniku kao i lozinka. Ako je u redu i server je u stanju pauze, prekida pauzu i nastavlja prijem korisničkih komandi. Klijentu se vraća odgovor OK. Kada nije u redu vraća se odgovor ERROR tekst (tekst objašnjava razlog pogreške).

    USER korisnik; PASSWD lozinka; STOP; -provjerava se u konfiguraciji da li postavka admin odgovara korisniku kao i lozinka. Ako je u redu i prekida rad i serializira svoju evidenciju.

    USER korisnik; PASSWD lozinka; CLEAN; -provjerava se u konfiguraciji da li postavka admin odgovara korisniku kao i lozinka. Ako je u redu prazni svoju evidenciju.

-user -ts ipadresa -port port -u korisnik -konf datoteka[.txt | .xml] 

Klijent/korisnik servera vremena - n dretvi od kojih mu svaka šalju komandu putem socketa koja od njega traži izvršavanja određene akcije:

     USER korisnik; GETTIME; -klijentu vraća odgovor u obliku OK dd.mm.yyyy hh:mm:ss. Ako nije u redu vraća se odgovor ERROR tekst (tekst objašnjava razlog pogreške).

Klijentske dretve (njihov broj određen je postavkom brojDretvi - maksimalno 9) pokreću se jedna iza druge uz pauzu (postavka pauza množi se generatorom slučajnih brojeva u intervalu 0.0 do 1.0 na bazi funkcije Math.random() (adresa http://docs.oracle.com/javase/7/docs/api/java/lang/Math.html#random())  i u dnevnik (postavka dnevnik) upisuju svoju oznaku i vrijeme pokretanja. Svaka dretva u zadanom intervalu (postavka interval, broj sekundi) zahtjeva trenutno vrijeme od servera vremena i zapisuje u dnevnik svoju oznaku, vlastito vrijeme, vrijeme od servera, tekst poruke. Kada server ne odgovari na zahtjev, dretva pamti broj pokušaja. Kada broj pokušaja bude veći od postavke brojPokusaja, dretva zapisuje u dnevnik i prekida s radom. Rad s dnevnikom mora biti isključiv u odnosu na ostale dretve. Pod intervalom se smatra vrijeme između pokretanja dretvi, a ne između završetka i ponovnog pokretanja! Ne mogu se koristiti klase Timer i TimerTask! 

    -show -s datoteka

Za pretvaranje serializiraniih podataka iz evidencije u čitljiv oblik.

Za formatiranje datumskih podataka preporučuje se klasa java.text.SimpleDateFormat (http://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html)

Komande koje šalje administrator sustava ili klijent/korisnik predstavljaju niz znakova i NE završavaju s CR/LF.

Elementi konfiguracije (primjer se nalazi nakon opisa vježbe/zadaće):

    port
    evidencija
    admin
    lozinka
    brojDretvi
    pauza (broj sekundi)
    interval (broj sekundi)
    brojPokusaja
    dnevnik.

*   Zadaća_1: višedretveni sustav vremenskog upravljanja putem socket-a

1.  U NetBeans kreiranje projekta {LDAP_korisničko_ime}_zadaca_1 kao Java aplikacije, na direktorij {LDAP_korisničko_ime}, bez kreiranja glavne klase i kao glavni projekt
2.  Kreiranje paketa org.foi.nwtis.{LDAP_korisnik}.zadaca_1
3.  Dodavanje Java biblioteke iz prethodnog zadatka: desna tipka na mišu na Libraries / Add Project / Odabrati vjezba_03_2
4.  Kreiranje klase Zadaca_1 u paketu org.foi.nwtis.{LDAP_korisnik}.zadaca_1. Klasa provjerava korektnost upisanih opcija. Preporučuje se koristiti dopuštene izraze (RegEx). Primjer TestOpcija.java nalazi se nakon opisa vježbe/zadaće.
    Java RegEx tutorial http://docs.oracle.com/javase/tutorial/essential/regex/
    Korisni primjeri za RegEx http://www.mkyong.com/regular-expressions/10-java-regular-expression-examples-you-should-know/
    Applet za provjeru RegEx http://www.cis.upenn.edu/~matuszek/General/RegexTester/regex-tester.html.
5.  Učitavaju se postavke iz datoteke. Na temelju opcija kreiraju se objekti pojedinih klasa (u nastavku) i prepušta im se njihovo izvršavanje.
6.  Kreiranje klase Dnevnik. Objekt otvori datoteku, upisuje pojedinačne zapise kako stižu i kada dobije naredbu za kraj, zatvara datoteku.
7.  Kreiranje klase Evidencija. Služi za serializaciju podataka.Treba odrediti varijable u koje će se pridružiti vrijednosti.
8.  Kreiranje klase ServerVremena. Objekt klase se izvršava kao dretva koja koristi SockerServer (slično primjerima ClientTester.java i TinyHttpd.java s 4. predavanja). Kada primi zahtjev od klijenta provjerava  korektnost komandi iz zahtjeva. Preporučuje se koristiti dopuštene izraze. Za prvo testiranje servera može se koristiti primjer s 4. predavanja Primjer33_3.java.
9.  Kreiranje klase KlijentVremena. Objekti klase se izvršavaju kao dretve koje se spajaju na server i šalje komandu u zahtjevu. 
10. Kreiranje klase AdministratorVremena. Objekt klase spaja se na server i šalje komandu(e) u zahtjevu.
11. Kreiranje klase IspisEvidencije. Otvara i čita datoteku sa serializiranim podacima evidencije te ih upisuje u formatiranu datoteku iz opcije -s.
12. Kod prvog pokretanja programa s osobinom servera javlja se Sigurnosna stijena (Firewall) s pitanjem o blokiranju ili dozvoli rada programa. Potrebno je dozvoliti rad programu (Java SE...). Drugi način je da to uradimo unaprijed putem postavki veze koju koristimo (LAN. wireless) za Advanced, Settings, u kojima dodamo port (Add Port) koji će biti otvoren