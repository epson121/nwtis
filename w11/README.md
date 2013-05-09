
    Kreiranje 12. vježbe (direktorij {korisnik}\vjezba_12). U nastavku se direktorij za vježbu simbolički označava kao {vježba}). U vježbi se koriste više oblika EJB za razvoj poslovne logike i JSF za korisničko sučelje.

vjezba_12_1: Kreiranje modula za poslovnu logiku i korištenje EJB: SessionBean (Stateful, Stateless i Singleton) 

1.  Kreiranje projekta vjezba_12_1 kao Java EE/EJB Module, server Glassfish, Java EE verzija: Java EE 6, kontekst / Enable Context and Depencency Injection: Da
2.  kreirati paket org.foi.nwtis.{LDAP_korisnik}.ejb.sb
3.  kreirati novog klijenta web servisa (New/Other/Web Services/Web Service Client) za Web servis koji ima adresu WSDL-a http://api.wxbug.net/weatherservice.asmx?WSDL, a klase se generiraju u standardni paket  
4.  kreirati Stateful SessionBean MeteoPrognosticar  (New/Other/Enterprise JavaBeans/Session Bean/Session Type: Stateful/Create Interface (označiti ni jedan)
5.  dodati varijable za zip kodove i meteo podatke (List<String> zipKodovi i  List<LiveWeatherData> meteoPodaci)
6.  kreirati settere i gettere za varijable
7.  dodati poslovnu metodu dajZipKodove() (Insert Code.../Add Business Method...) koja vraća List<String>. Postaviti da poziva get metodu za zip kodove. (Ovo gotovo da nije potrebno jer ne koristimo sučelja (lokalno ili udaljeno). Više da se vidi što bi bilo potrebnu uraditi jer se tako dodaju metode u sučelje/a.
8.  kreirati Singleton SessionBean MeteoOsvjezivac  (New/Other/Enterprise JavaBeans/Timer Session Bean/Session Type: Session/Create Interface (označiti ni jedan). (Kada bi bio Stateless/Statefull SessionBean nastao bi problem zbog broja dretvi (Timer) koje bi se javile kod većeg broja korisnika. U video materijalima prvo je korištena varijanta sa Stateless SessionBean-om!)
9.  u anotaciji metode myTimer postaviti da se poziva svake 2 minute, ostalo obrisati
10. dodati privatnu varijablu sa svojim WeatherBug kodom
11. napraviti poziv operacije getLiveWeatherByUSZipCode  na web servisu WeatherBug (Insert Code/Call Web Service Operation...)
12. dodati poziv EJB MeteoPrognosticar  (Insert Code .../Call Enterprise Bean i odabrati MeteoPrognosticar  )
13. kopirati dio koda iz funkcije dajMeteoPodatkeZaViseZip klase MeteoServis iz projekta vjezba_11_1
14. prilagoditi za korištenje u ovom situaciji (za zip kodove koristi se pripadajući getter na varijabli za EJB  MeteoPrognosticar. Slično je i za spremanje meteo podataka).   
15. pokrenuti na Glassfish-u Admin Console
16. izabrati server (Admin Server) / JMS Physical Destinations / New
17. Name: NWTiS_vjezba_12, Type: javax.jms.Queue, Threshold Limit Behavior: Throw out oldest (slika 1)
18. izabrati Resources/JMS Resources/Conenction Factories/New
19. Pool Name: jms/NWTiS_QF_vjezba_12, Resource Type: QueueConnectionFactory (slika 2)
20. izabrati Resources/JMS Resources/Destionation Resources/New
21. JNDI: jms/NWTiS_vjezba_12 Physical Destionation Name: NWTiS_vjezba_12, Resource Type: javax.jms.Queue (slika 3)
22. poslati JMS poruku /Insert Code .../Send JMS Message ... Odrabrati Server Destinations... jms/NWTiS_vjezba_12 (slika 4). Nakon toga se automatski kreira konfiguracijska datoteka glasfish-ejb-jar.xml (slika 5). Ovo je način koji se preporučuje. Moguće je kreirati projektno odredište (slika 6) ili koristiti postojeći MDB, kada bi prvo kreirali projekt vjezba_12_2 i MeteoPostar (slika 7).
23. promijeniti private u public za metodu sendJMSMessageToNWTiS_vjezba_12(...)

vjezba_12_2: Kreiranje modula za poslovnu logiku i korištenje EJB: MessageDrivenBean 

1.  Kreiranje projekta vjezba_12_2 kao Java EE/EJB ModuleWeb aplikaciju, server Glassfish, Java EE verzija: Java EE 6, kontekst / Enable Context and Depencency Injection: Da
2.  kreirati paket org.foi.nwtis.{LDAP_korisnik}.ejb.mdb
3.  kreirati MessageDrivenBean MeteoPostar (New/Other/Enterprise JavaBeans/MessageDrivenBean/ Server Destinations:  jms/NWTiS_vjezba_12 (slika 8). Ako se odabere serverska varijanta odredišta, tada neće biti potrebna dodatna konfiguracijska datoteka. Kod odabira projektnog odredišta (slika 9) automatski se kreira konfiguracijska datoteka glassfish-resources.xml u kojoj su upute Glassfish-u za kreiranje resursa (slika 10).
4.  po prijemu poruke ispisati sadržaj na konzolu

vjezba_12_3: Kreiranje web modula za korisničko sučelja 

1.  Kreiranje projekta vjezba_12_3 kao Java Web aplikaciju,  Java EE verzija: Java EE 6, kontekst /vjezba_12_3, Enable Context and Depencency Injection: Da, (Frameworks: JSF), Libraries/Server Library: JSF 2.0, Configuration/JSF Servlet URL pattern: /faces/* , Prefered Page Language: Facelets
2.  kreirati pakete org.foi.nwtis.{LDAP_korisnik}.web.zrna, net.wxbug.api
3.  kopirati klasu LiveWeatherData iz paketa net.wxbug.api u projektu vjezba_12_1 u paket net.wxbug.api
4.  kreirati JSF MeteoPrognoza New/JSF(JSF Manage Bean) (bez uključivanja u web.xml), scope: session
5.  dodati privatne varijable za odabrani zip kod, zip kodove i meteo podatke (String odabraniZipKod, List<String> zipKodovi i  List<LiveWeatherData> meteoPodaci)
6.  dodati settere i gettere za varijable
7.  dodati funkciju public String dodajZipKod() koja će biti veza za akciju putem koje će se dodati uneseni zip kod u varijablu 
8.  dodati poziv EJB MeteoPrognosticar  (Insert Code .../Call Enterprise Bean i odabrati MeteoPrognosticar) NIJE MOGUĆE JER NISU U ISTOM MODULU ILI ENTERPRISE APLIKACIJI.
9.  obaviti kreiranje projekta Vježba_12 (dolje je opis)
10. dodati poziv EJB MeteoPrognosticar  (Insert Code .../Call Enterprise Bean i odabrati MeteoPrognosticar)
11. pridružiti varijabli trenutni sadržaj zip kodova iz MeteoPrognosticara, dodati uneseni zip kod i ažurirati zip kodove kod MeteoPrognosticara
12. u getter za varijablu meteo podataka (npr. lwds)  dodati da se preuzimaju podaci iz MeteoPrognosticara (lwds = meteoPrognosticar.getMeteoPodaci())
13. dodati poziv EJB MeteoOsjezivac  (Insert Code .../Call Enterprise Bean i odabrati MeteoOsjezivac)
14. pozvati metodu sendJMSMessageToNWTiS_vjezba_12 i prenijeti String "Dodan zip kod: " x
15. kreirati JSF unosZipKodova.xhtml
16. dodati obrazac u kojem će biti obrazac za unos zip koda, prikaz odabranih zip kodova i gumb za slanje uz akciju na prethodnu funkciju
17. dodati vezu na index.xhtml i pregledMeteoPodataka.xhtml
18. kreirati JSF pregledMeteoPodataka.xhtml
19. dodati obrazac u kojem će prikazati tablica za pregled meteo podataka i gumb za osvježavanje
20. dodati vezu na unosZipKodova.xhtml i pregledMeteoPodataka.xhtml
21. u index.xhtml dodati vezu na unosZipKodova.xhtml i pregledMeteoPodataka.xhtml
22. izgraditi, isporučiti, izvršiti i testirati Enterprise aplikaciju vjezba_12
23. može se pogledati na Admin Console-i sastav aplikacije vjezba_12

vjezba_12: Kreiranje Enterprise aplikacije

1.  Kreiranje projekta vjezba_12_B kao Enterprise Application, server Glassfish, Java EE verzija: Java EE 6, Enable Context and Depencency Injection: Da, Create EJB Module: NE, Create Web Application Module: NE
2.  otvoriti projekt
3.  na Java EE Modules, Add Java EE Module... Označiti: vjezba_12_1, jezba_12_2, vjezba_12_3 i OK 
4.  odabrati deploy 
5.  izvršiti vjezba_12

vjezba_12_A: Kreiranje Enterprise aplikacije

1.  Kreiranje projekta vjezba_12_A kao Enterprise Application, server Glassfish, Java EE verzija: Java EE 6, Enable Context and Depencency Injection: Da, Create EJB Module: NE, Create Web Application Module: NE
2.  otvoriti projekt
3.  na Java EE Modules, Add Java EE Module... Označiti: vjezba_12_2
4.  odabrati deploy 

vjezba_12_B: Kreiranje Enterprise aplikacije

1.  Kreiranje projekta vjezba_12_B kao Enterprise Application, server Glassfish, Java EE verzija: Java EE 6, Enable Context and Depencency Injection: Da, Create EJB Module: NE, Create Web Application Module: NE
2.  otvoriti projekt
3.  na Java EE Modules, Add Java EE Module... Označiti: vjezba_12_1, vjezba_12_3 i OK 
4.  odabrati deploy 
5.  izvršiti vjezba_12_3

