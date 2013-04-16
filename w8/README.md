===
Kreiranje 8. vježbe (direktorij {korisnik}\vjezba_08). U nastavku se direktorij za vježbu simbolički označava kao {vježba}).
===

Vježba\_08_1: Upoznavanje s web poslužiteljem Tomcat, razvoj Web aplikacije na bazi servleta, JSP, isporuka web aplikacije na web poslužitelj

Web aplikacija započinje učitavanjem datoteke konfguracije kod inicijalizacije aplikacije, konteksta (koristi se slušač konteksta org.foi.nwtis.{LPAD_korisnik}.web.slusaci.SlusacAplikacije). Naziv datoteke konfiguracije nalazi se u inicijalnom parametru konteksta (konfiguracija), a datoteka je smještena na direktoriju WEB-INF. 

Nakon uspješne prijave korisnika potrebno je upamtiti podatke korisnika (klasa org.foi.nwtis.{LPAD\_korisnik}.web.kontrola.Korisnik, u prilogu) u evidenciju aktivnih korisnika (neka klasa kolekcija) koja se sprema u atribut konteksta popisKorisnika (koristi se slušač atributa sesije i slušač sesije org.foi.nwtis.{LPAD_korisnik}.slusaci.SlusacSesije). Nakon odjave korisnika ili invalidacije sesije potrebno je korisnika obrisati iz evidencije aktivnih korisnika (akcije obavlja prethodni slušač). 

Direktorij privatno treba štititi putem filtera (org.foi.nwtis.{LPAD_korisnik}.web.filteri.KontrolaPristupa) tako da mu mogu pristupiti samo prijavljeni korisnici (u sesiji imaju zapis "korisnik"). Ostali korisnici preusmjeravaju se na /PrijavaKorisnika. 

Direktorij admin treba štititi putem kontejnera uz BASIC metodu, uz zaštićeni vezu i s tomcat-users.xml Pristup ima uloga nwtis.  Ostali korisnici preusmjeravaju se na /PrijavaKorisnika. 

Servlet s ulogom kontrolera (klasa org.foi.nwtis.{LPAD_korisnik}.web.Kontroler) glavno je sučenje web aplikacije i korisnici jedino njega pozivaju. On ima više vanjskih naziva (/Kontroler, /PrijavaKorisnika, /IspisAktivnihKorisnika, /IspisPodataka,...) za koje obavlja preusmjeravanje na određene jsp datoteke:

    /Kontroler - /jsp/index.jsp
    /PrijavaKorisnika - /jsp/login.jsp
    /OdjavaKorisnika - odjavljuje aktivnog korisnika i ide na /Kontroler
    /ProvjeraKorisnika - provjerava u tablici polaznici i ako je uspješno onda ide na /IspisKorisnika, inače generira iznimku org.foi.nwtis.{LPAD_korisnik}.web.NeuspjesnaPrijava
    /IspisAktivnihKorisnika - /admin/ispisAktvnihKorisnika.jsp 
    /IspisKorisnika - /admin/ispisKorisnika.jsp 
    /IspisPodataka - /privatno/ispisPodataka.jsp.

Na direktoriju jsp nalazi se datoteka:

    index.jsp - ispisuje pozdravnu poruku, daje veze za moguće akcije
    login.jsp - sadrži obrazac za prijavljivanje korisnika i poziva /ProvjeraKorisnika

Na direktoriju privatno nalaze se datoteke:

    ispisPodataka.jsp - ispisuje podatke aktivnog korisnika. Podaci se ispisuju pomoću JSP jezika izraza.

Na direktoriju admin nalaze se datoteke:

    ispisAktivnihKorisnika.jsp - ispisuje popis prijavljenih korisnika koji imaju aktivnu sesiju. Podaci se ispisuju pomoću iteratora iz biblioteke oznaka c (core) i jezika izraza. Ne koriste se skriptleti. 
    ispisKorisnika.jsp - ispisuje popis korisnika. Podaci se ispisuju pomoću biblioteke oznaka Display tag, sql i jezika izraza. 

 

Preporučeni koraci:

1.  kopirati projekt vjezba\_08_1 kao Java Web aplikacija (na Tomcat, EE 6)
2.  kreirati pakete org.foi.nwtis.{LDAP\_korisnik}.web.slusaci, org.foi.nwtis.{LDAP\_korisnik}.web.filteri, org.foi.nwtis.{LDAP_korisnik}.web.kontrole
3.  kreirati direktorije jsp, privatno, admin u Web Pages (New/Other/Folder)
4.  prebaciti index.jsp u jsp/index.jsp. U web.xml podesiti da je to pocetna stranica aplikacije.
5.  kopirati datoteke konfiguracije iz projekta vjezba\_07_1 s direktorija WEB-INF
6.  dodati biblioteke iz projekta vjezba\_06_1
7.  dodati biblioteke iz projekta vjezba\_03_2
8.  dodati biblioteke Java DB JDBC Driver i MySQL JDBC Driver
9.  dodati biblioteku JSTL
10. upisati u web.xml incicijalni parametar konteksta konfiguracija
11. kreirati slušaca konteksta SlusacAplikacije i podatke konfiguracije učitati kod kreiranja konteksta (kopirati dio programskog koda iz Vjezba\_07\_1 te prilagoditi) i spremiti u atribut konteksta "BP_Konfiguracija" (vrlo slicno postoji na primjeru s predavanja)
12. kreiraj servlet Kontroler. On nema komunikaciju s korisnikom. Sam prosljeđuje na druge. Pridružiti mu potrebne vanjske nazive. Dodati uvjete za preusmjeravanja.
13. kreirati iznimke NeuspjesnaPrijava
14. dodaj u jsp/index.jsp veze na aktivnosti
15. kreirati jsp/login.jsp
16. kreirati privatno/ispisPodataka.jsp
17. u web.xml postaviti obradu pogrešaka: za iznimku NeuspjesnaPrijava poziva jsp/login.jsp 
18. kreirati slušaca sesije i atributa sesije SlusacSesije i kod kreiranja atributa sesije „korisnik“ upisati  njegove podatke u klasu kolekcije u kontekstu , a kod brisanja sesije obrisati njegove podatke iz objekta u kontekstu. To služi za evidenciju aktivnih korisnika web aplikcije 
19. kreirati filter KontrolaPristupa koji je zadužen za kontrolu nad dijelom /privatno/* u koji mogu ulaziti samo korisnici koji su prijavljeni (imaju u sesiji zapis korisnik)
20. pripremiti za izvršavanje web aplikacije (Build), isporučiti web aplikaciju na web poslužitelj (Deploy), izvršiti web aplikaciju i testirati rad
21. u web.xml postaviti sigurnosnu postavku za /admin/* s BASIC metodom uz ulogu nwtis i uz zaštićenu vezu. 
22. dodati korisnika s ulogom nwtis u {Tomcat}/conf/tomcat-users.xml
23. kreirati certifikat za tomcat i postaviti SSL na portu 8443 na poslužitelju u datoteci server.xml Više na http://tomcat.apache.org/tomcat-7.0-doc/ssl-howto.html 
24. kreirati admin/ispisAktivnihKorisnika.jsp
25. uključiti potrebnu JSTL biblioteku za core 
26. <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
27. dodati za izlistavanje aktivnih korisnika na temelju atributa iz konteksta
28. kreirati admin/ispisKorisnika.jsp 
29. uključiti potrebne JSTL biblioteke za core i  sql
30. <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
31. <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
32. dodati biblioteku Display tag (u prilogu) Više o biblioteci na http://www.displaytag.org/1.2/ . Preporučuje se kreiranje posebnog direktorija izvan projekta npr. libs i u njega raspakirati .zip datoteku.
33. dodati u Tools/Libraries/New Library novu biblioteku "Display tag" 
34. dodati u Libraries/Add Library... prethodno kreiranu biblioteku "Display tag"
35. postaviti tld dotatoteku na potreban direktorij (prema primjeru strukture web aplikacije s predavanja, može i bez toga)
36. kreirati direktorij css unutar Web Pages i  u njega raspakirati .zip datoteku s display tag css datotekama
37. uključiti potrebnu JSTL biblioteku za display tag
38. <%@taglib prefix="display" uri="http://displaytag.sf.net" %>
39. kopirati u njega dio JSP koda iz primjena s predavanja te prilagoditi da se konfiguracijski podaci preuzimaju iz konteksta putem EL (jezika izraza)
40. otvoriti <display i pratiti atribute koje predlaže
41. postavit da je veličina stranice 5
42. uključiti jednu css datoteku uz preporuku da se definira apsolutna putanja (kao i kod poveznica u index.jsp)
43. pripremiti za izvršavanje web aplikacije (Build), isporučiti web aplikaciju na web poslužitelj (Deploy), izvršiti web aplikaciju i testirati rad

Koristan kratki tutorial za JSP na http://www.hscripts.com/tutorials/jsp/index.php
