
1.  Kreiranje 7. vježbe (direktorij {korisnik}\vjezba\_07). U nastavku se direktorij za vježbu simbolički označava kao {vježba}).

Vježba\_07\_1: Upoznavanje s web poslužiteljem Tomcat, razvoj Web aplikacije na bazi servleta, isporuka web aplikacije na web poslužitelj

1.  Kreiranje projekta vjezba\_07\_1 kao Java Web aplikaciju, server Tomcat 7.0.22, Java EE verzija: Java EE 5, kontekst /vjezba\_07\_1, bez korištenja okvira (Frameworksa)
2.  Otvoriti projekt  Vjezba\_07\_1  i pregled strukture unutar projekta Web aplikacije 
3.  Na projektu Vjezba\_07\_1 desna tipka na mišu/Properties - upoznavanje s postavkama Web aplikacije i poreporuke za određene postavke (Slika 1.)
4.  Kreiranje paketa org.foi.nwtis.{LDAP\_korisnik}.web
5.  Kreiranje servleta Vjezba\_07\_1. Logički naziv servleta: Vjezba\_07\_1, URL predložak:Vjezba\_07\_1, inicijalni parametri: korisnickoIme: LDAP\_korisnik, lozinka: 123456. 
6.  Priprema web aplikacije za izvršavanje (Na projektu Vjezba\_07\_1 desna tipka na mišu/ Build ili putem ikone u alatnoj traci)
7.  U Files otvoriti projekt Vjezba\_07\_1 i pogledati strukturu, posebno za dist i .war datoteku.
8.  Provjeti da li radi Web poslužitelj (Services/Servers/Apache Tomcat). Ako ne radi, desna tipka na mišu/ Startup.
9.  Provjeti na Web poslužitelju da li postoji web aplikacija Vjezba\_07\_1 (Services/Servers/Apache Tomcat/Web applications)
10. Isporuka web aplikacije na Web poslužitelj (Na projektu Vjezba\_07\_1 desna tipka na mišu/ Deploy)
11. Ponovno provjeti na Web poslužitelju da li postoji web aplikacija Vjezba\_07\_1 (Services/Servers/Apache Tomcat/Web applications)
12. Izvršavanje web aplikacije na Web poslužitelju (Na projektu Vjezba\_07\_1 desna tipka na mišu/ Run)
13. Izvršavanje servleta Vjezba\_07\_1 na Web poslužitelju (Na servletu Vjezba\_07\_1 desna tipka na mišu/ Run File...). Odabrati URL predložak za servlet.
14. Dodati za ispis vrijednosti inicijalnih parametara servleta (korisnickoIme, lozinka).
15. Otvaranje deskriptora web aplikacije projekt Vjezba\_07\_1 / Configuration Files / web.xml
16. Pogledati sadržaja kao Source (xml format)
17. Pogledati sadržaja kao Servlets. Dodati za servlet Vjezba\_07\_1 da ima URL predložak /Vjezba\_07\_1, /vjezba\_07\_1, /pero (Slika 2.)
18. Izvršavanje servleta Vjezba\_07\_1 na Web poslužitelju (Na servletu Vjezba\_07\_1 desna tipka na mišu/ Run File...). Odabrati URL predložak za servlet. Provjeriti za ostale URL predloške.
19. Izvršavanje Web aplikacije za upravljanje Web poslužiteljem (Services/Servers/Apache Tomcat/Web applications/manager) ili direktno u pregledniku http://localhost:8080/manager. Potrebno je korisničko ime i lozinka za rad. Nisu poznati za sada.
20. U NetBeans otvoriti Services/Servers/Apache Tomcat/ zatim desnom tipkom na mišu izvršiti Properties, aktivirati Show kod Passowrd. Upisati podatke u obrazac za prijavljivanje web aplikacije manager.
21. Otvoriti u pregledniku http://localhost:8080/docs/ Posebno se upoznati s Manager
22. Pogledati smještaj Apache Tomcat u NetBeans, postaviti se na taj direktorij, prijeći u conf i otvoriti tomcat-users.xml 
23. Dodati nekoliko korisnika s ulogama manager-gui,manager-status,manager-script,manager-jmx,admin-gui
24. U NetBeans za Tomcat izvršiti Restart
25. Ponoviti prijavljivanje za aplikaciju manager.
26. Upoznati se s mogućnostima aplikacije manager
27. Izvršiti Undeploy za aplikaciju Vjezba\_07\_1
28. U WAR file to deploy pokrenuti  Browse i postaviti se na dist direktorij u vježbi. Odabrati .war datoteku (ako je nema, izvršiti Build na projektu Vjezba\_07\_1)
29. Izvršiti Desploy i provjeriti postoji li sada aplikacija u popisu
30. Izvršiti servlet Vjezba\_07\_1


Vježba\_07\_2: Rad s bazom podataka MySQL

1.  Kreiranje servleta Vjezba\_07\_2. Logički naziv servleta: Vjezba\_07\_2, URL predložak:Vjezba\_07\_2, inicijalni parametri: konfiguracija: NWTiS.db.config\_1.xml  
2.  Namjena: ispis stupaca ime i prezime iz tablice polaznici. Svi potrebni podaci trebaju se preuzeti iz datoteke postavki/konfiguracije na bazi metoda klase BP\_Konfiguracija. Naziv datoteke konfiguracije nalazi se u inicijalnim parametrima aplikacije (konfiguracija). Datoteka je smještena na direktoriju WEB-INF. Pogledati na primjer s predavanja kako dohvatiti putanju za WEB-INF direktorij (Kod slušača aplikacije).
3.  Dodavanje biblioteke iz projekta vjezba\_06\_1
4.  Dodavanje biblioteke iz projekta vjezba\_03\_2
5.  Dodavanje biblioteke MySQL JDBC Driver
6.  Preuzeti veći dio programskog koda iz klase Vjezba\_06\_2 i prilagoditi novoj situaciji (naziv datoteke se dobije putem inicijalnih parametara servleta)
7.  Piripremiti web aplikaciju za isporuku
8.  Isporučiti web aplikaciju na web poslužitelj
9.  Izvršiti servlet Vjezba\_07\_2

Vježba\_07\_3: Rad s bazom podataka Java DB

1.  Kreiranje servleta Vjezba\_07\_3. Logički naziv servleta: Vjezba\_07\_3, URL predložak:Vjezba\_07\_3, inicijalni parametri: konfiguracija: NWTiS.db.config\_2.xml  
2.  Namjena: ispis stupaca ime i prezime iz tablice polaznici. Svi potrebni podaci trebaju se preuzeti iz datoteke postavki/konfiguracije na bazi metoda klase BP\_Konfiguracija. Naziv datoteke konfiguracije nalazi se u inicijalnim parametrima aplikacije (konfiguracija). Datoteka je smještena na direktoriju WEB-INF. 
3.  Dodavanje biblioteke Java DB JDBC Driver
4.  Kopirati veći dio programskog koda iz klase Vjezba\_07\_2 
5.  Piripremiti web aplikaciju za isporuku
6.  Isporučiti web aplikaciju na web poslužitelj
7.  Izvršiti servlet Vjezba\_07\_3


Vježba\_07\_4: Rad sa slušačima aplikacije itd

1.  Otvaranje deskriptora web aplikacije projekt Vjezba\_07\_1 / Configuration Files / web.xml
2.  Pogledati sadržaja kao General. Otvoriti Context Parameters i dodati inicijalni parametar: konfiguracija: NWTiS.db.config\_2.xml  
3.  Kreiranje paketa org.foi.nwtis.{LDAP\_korisnik}.web.slusaci
4.  Kreiranje slušača aplikacije Vjezba\_07\_4 (New/Other/Web/Web Applicatio Listener). Označiti samo Context Listener.
5.  U metodi contextInitialized(...) ispisati na konzolu naziv konteksta koji se inicijalizira i sadržaj inicijalnog parametra konteksta pod nazivom konfiguracija (putem System.out.println(...))
6.  U metodi contextDestroyed(...) ispisati na konzolu naziv konteksta koji se zatvara (putem System.out.println(...))
7.  Piripremiti za izvršavanje, isporučiti aplikaciju na web poslužitelj
8.  Piripremiti web aplikaciju za isporuku
9.  Isporučiti web aplikaciju na web poslužitelj. Provjeriti sadržaj na konzolu Web poslužitelja



Vježba\_07\_5: Rad s filterima

1.  Kreiranje paketa org.foi.nwtis.{LDAP\_korisnik}.web.filteri
2.  Kreiranje filtera Vjezba\_07\_5. Logički naziv servleta: Vjezba\_07\_5, primjenjuje se za:*
3.  U metodi doBeforeProcessing(...) ispisati na konzolu naziv konteksta i filtera koji se počinje izvršavati (putem System.out.println(...))
4.  U metodi doAfterProcessing(...) ispisati na konzolu naziv konteksta i filtera koji se završava (putem System.out.println(...))
5.  Piripremiti za izvršavanje, isporučiti aplikaciju na web poslužitelj
6.  Piripremiti web aplikaciju za isporuku
7.  Izvršiti nekoliko servleta
8.  Isporučiti web aplikaciju na web poslužitelj. Provjeriti sadržaj na konzolu Web poslužitelja