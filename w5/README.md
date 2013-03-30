
1.  Kreiranje 5. vježbe (direktorij {korisnik}\vjezba_05). U nastavku se direktorij za vježbu simbolički označava kao {vježba}). 

2.  Vježba\_05_1: izrada certifikata, digitalni potpis jar aplikacije, postavljanje sigurnosne politike i izvršavanje java aplikacije u sigurnosnom okruženju

3.  Kreiranje projekta vjezba\_05_1 kao Java aplikacija
4.  Kreiranje paket org.foi.nwtis.{LDAP_korisnik}.sigurnost
5.  Dodavanje biblioteke iz projekta vjezba\_03_2
6.  Kreiranje klase Vjezba\_05_1 ima 1 argument: datoteka. Namjena: učitava se datoteka postavki i slijedi ispis svih postavki. Prvo treba provjeriti koliko je upisano argumenata. Ako nije jedan treba javiti pogrešku i prekinuti izvršavanje. Upisani argument treba biti naziv datoteke postavki. Drugi korak je provjera postoji li datoteka tog naziva. Ako ne postoji treba javiti pogrešku i prekinuti izvršavanje. Treći korak je kreiranje objekta Konfiguracija na temelju upisanog naziva datoteke. Zadnji korak je ispis svih postavki.
7.  Kopirati datoteku postavki iz projekta {LDAP\_korisnik}\_zadaca_1
8.  Pripremiti projekt za izvršavanje (Clean/Build)
9.  Izvršiti projekt `java -jar dist\vjezba\_05\_1.jar datoteka i/ili jar -cp dist\vjezba\_05\_1.jar org.foi.nwtis.{LDAP\_korisnik}.sigurnost.Vjezba\_05_1 datoteka`
10. Kreirati ključ nazivom {LDAP\_korisnik} putem Java alata `keytool`. Spremište ključeva se naziva {LDAP_korisnik}.jks. Lozinka za spremište je nwtis2012, a za korisnika 123456. Više od keytool pogledati na http://docs.oracle.com/javase/7/docs/technotes/tools/windows/keytool.html
11. Provjeriti sadržaj spremišta ključeva koje se naziva {LDAP_korisnik}.jks putem Java alata keytool
12. Digitalno potpisati jar datoteku projekta vjezba\_05\_1 putem Java alata jarsigner. Potpisana jar datoteka ima naziv vjezba\_05\_1_signed.jar. Više o jarsigner pogledati na http://docs.oracle.com/javase/7/docs/technotes/tools/windows/jarsigner.html
13. Provjeriti digitalni potpis datoteke putem Java alata jarsigner
14. Eksportirati certifikat za korisnika {LDAP\_korisnik}u datoteku {LDAP_korisnik}.cer  putem Java alata keytool
15. Upload certifikata na http://161.53.120.205/NWTiS/upload.html
16. Izvršiti projekt `java -jar dist\vjezba\_05\_1\_signed.jar datoteka i/ili jar -cp dist\vjezba\_05\_1\_signed.jar org.foi.nwtis.{LDAP\_korisnik}.sigurnost.Vjezba\_05\_1 datoteka`
17. Izvršiti projekt uz opciju `-Djava.security.manager`
18. Kreirati datoteku sigurnosne politike {LDAP_korisnik}.policy u kojoj se dozvoljava čitanje svih datoteka. Koristi se Java alat policytool. Više o policytools na http://docs.oracle.com/javase/7/docs/technotes/guides/security/PolicyGuide.html
19. Izvršiti projekt uz opciju `-Djava.security.manager -Djava.security.policy={LDAP_korisnik}.policy` Opcije koje započinju s -D spadaju u opcije za virtualni stroj pa se u NetBeans-u prilikom podešavanja konfiguracije za izvršavanje moraju upisati u liniju VM Options.
20. Kreirati datoteku sigurnosne politike {LDAP\_korisnik}_signed.policy u kojoj se dozvoljava čitanje datoteke na temelju digitalnog potpisa. Spremište ključeva nalazi se na http://161.53.120.205/NWTiS/NWTiS.jks. Koristi se Java alat policytool. Slike 1. 2. i 3. prikazuju elemente koji su bitni.
21. Izvršiti projekt uz opciju `-Djava.security.manager -Djava.security.policy={LDAP\_korisnik}_signed.policy`
22. Analizirati obavljene aktivnosti, utvrditi razloge postojećem stanju i prioremiti novu verziju.
23. Postupci kreiranja certifikata, digitanog potpisa i eksporta certifikata MOGU se ubrzati pomoću ant-a. Više o ant-u na http://ant.apache.org
24. Ugraditi automatsko potpisivanje jar datoteke izabranim certifikatom. Dodati u build.xml novi cilj "-post-jar" koji se poziva nakon što se uspješno završi cilj "jar" tj. kreira se jar datoteka projekta. Kao zadatak koristi se signjar. 
25. Ugraditi automatsko generiranje ključa na bazi upisanih podataka. Dodati u build.xml novi cilj "keygen" koji se ručno poziva kada se želi generirati ključ u definirano spremište ključeva. Kao zadatak koristi se genkey. 
26. Ugraditi automatsko brisanje ključ. Dodati u build.xml novi cilj "keydel" koji se ručno poziva kada se želi generirati obrisati ključ iz definiranog spremište ključeva. Kao zadatak koristi se exec.
27. Ugraditi izvršavanje projekta uz definirane argumente. Dodati u build.xml novi cilj "run.0" koji se ručno poziva kada se želi izvršiti projekt uz udefinirani argument(e). Kao zadatak koristi se java.
