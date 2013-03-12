Dogovor o radnoj okolini za vježbe: direktorij D:\NWTiS U nastavku se direktorij za korisnika simbolički označava kao {NWTiS}.
Dogovor o radnoj okolini za vježbe pojedine grupe: direktorij {NWTiS} \grupa_{i} što u konkretnom slučaju za osobu koja je u grupi 1 izgleda D:\NWTiS\grupa_1. U nastavku se direktorij za korisnika simbolički označava kao {grupa_i}.
Dogovor o radnoj okolini za vježbe pojedinog studenta: direktorij {NWTiS} \{grupa_i}\{LDAP korisničko ime} što u konkretnom slučaju za osobu koja je u grupi 1 izgleda D:\NWTiS\grupa_1\dkermek. U nastavku se direktorij za korisnika simbolički označava kao {korisnik}.
Napomena: sve što se radi na nastavi iz NWTiS-a treba smjestiti unutar direktorija {NWTiS} osim ako nije drugačije navedeno.
Upoznavanje s Java web mjestom: http://www.oracle.com/technetwork/java/index.html
Upoznavanje s Java SE: http://www.oracle.com/technetwork/java/javase/overview/index.html
Preuzimanje JDK SE: http://www.oracle.com/technetwork/java/javase/downloads/index.html (prethodno je preuzeto i nalazi se na lokalnom računalu unutar {NWTiS}.
Instaliranje JDK SE na računalo (standardne postavke).
Preuzimanje JDK SE dokumentacije: http://www.oracle.com/technetwork/java/javase/downloads/index.html
Instaliranje JDK SE dokumentacije na računalo (direktorij {korisnik}\JDK_SE\docs).
Upoznavanje s JDK SE dokumentacijom (JDK SE alati, JDK SE API,...)
Preuzimanje programa notepadd++: http://notepad-plus-plus.org/download
Instaliranje programa notepadd++ (standardne postavke)

* Kreiranje 1. vježbe (direktorij {korisnik}\vjezba_01). U nastavku se direktorij za vježbu simbolički označava kao {vježba})
Vježba_01_1: Razvojni ciklus Java programa
Kreiranje 1. zadatka (direktorij {vježba}\vjezba_01_1)
Kreiranje Java datoteke Vjezba_01_1.java Opis problema: ispis argumenata.
Kompiliranje zadatka: javac Vjezba_01_1.java
Izvršavanje zadatka: java Vjezba_01_1 (uz razne argumente: pero ivo 505)

* Vježba_01_2: Strukturiranje direktorija za projekt
Kreiranje 2. zadatka (direktorij {vježba}\vjezba_01_2).
Kreiranje skripte {korisnik}priprema.bat za kreiranje poddirektorija src, classes, libs i dist unutar aktivnog direktorija
Izvršavanje skripte: {korisnik}\priprema.bat
Postavljanje na src direktorij
Kreiranje Java datoteke Faktorijela.java Opis problema: kreiranje klase za izračunavanje faktorijela. Konstruktor ima argument tipa int. Metoda public long dajFaktorijelu() vraća u tipu long.

* Kreiranje Java datoteke Vjezba_01_2.java Opis problema: ispis faktorijela za upisani argument .
Kompiliranje klasa uz smještaj .class datoteka na direktorij classes: javac -d ..\classes Vjezba_01_2.java
Izvršavanje zadatka s direktorija {vježba}: java -classpath ..\classes Vjezba_01_2 (uz razne argumente: 0, 1, 2, 10,...)
 
* Vježba_01_3: Kreiraje jar biblioteke
Kreiranje 3. zadatka (direktorij {vježba}\vjezba_01_3).
Izvršavanje skripte: {korisnik}\priprema.bat
Postavljanje na src direktorij
Kreiranje direktorija podrska i postavljanje na isti
Kopiranje datoteke Faktorijela.java iz prethodne vježbe i promjena da se nalazi u paketu podrska
Kompiliranje klasa uz smještaj .class datoteka na direktorij classes: javac -d ..\classes podrska\*.java
Kreiranje jar biblioteke Podrska.jar na direktioriju dist: jar -cvf ..\dist\Podrska.jar -C ..\classes .
 
* Vježba_01_4: Korištenje jar bioblioteke i kreiranje izvršne verzije jar biblioteke
Kreiranje 4. zadatka (direktorij {vježba}\vjezba_01_4).
Izvršavanje skripte: {korisnik}\priprema.bat
Kopiranje jar biblioteke Podrska.jar iz prethodne vježbe na direktorij libs
Postavljanje na src direktorij
Kreiranje direktorija aplikacija unutar src i postavljanje na isti
Kopiranje datoteke Vjezba_01_2.java iz prethodne vježbe, promjena naziva klase u Vjezba_01_4, promjena da se nalazi u paketu aplikacija, promjena paketa za klasu Faktorijela u podrska
Kompiliranje klasa uz smještaj .class datoteka na direktorij classes: javac -classpath ..\libs\Podrska.jar -d ..\classes aplikacija\*.java
Izvršavanje zadatka s direktorija {vježba}: java -classpath ..\classes;..\libs\Podrska.jar aplikacija.Vjezba_01_4 (uz razne argumente: 0, 1, 2, 10,...)
Kreiranje datoteke manifest na direktoriju src tako da je klasa Vjezba_01_4 početna klasa
Manifest-Version: 1.0
Created-By: NWTiS dd.MM.yyyy hh:mm (upisati stvarno vrijeme)
Class-Path: ..\libs\Podrska.jar
Main-Class: aplikacija.Vjezba_01_4
Kreiranje jar biblioteke Aplikacija.jar na direktioriju dist: jar -cvfm ..\dist\Aplikacija.jar manifest -C ..\classes *.*
Izvršavanje zadatka s direktorija {vježba}: java -jar ..\dist\Aplikacija.jar