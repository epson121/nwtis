# Vježba\_02\_1: #
## Rad s klasama, sučeljima i nasljeđivanjem ##

1.	Kreiranje 1. zadatka (direktorij {vježba}\vjezba\_02\_1)_
2. 	Izvršavanje skripte: {korisnik}\priprema.bat
3.	Postavljanje na src direktorij
4.	Kreiranje paketa org.foi.nwtis.{LDAP_korisnik}.kvadrati  
5.	Kreiranje klase Kvadrati  u paketu org.foi.nwtis.{LDAP_korisnik}.kvadrati s konstruktorom Kvadrati(int od, int do), metoda void ispis() ispisuje kvadrate brojeva u intervalu od-do.
Kreiranje Java datoteke Vjezba\_02\_1.java u paketu  org.foi.nwtis.{LDAP_korisnik}. 



Klasa je izvršna (ima metodu main(...)) te sadrži switch instrukciju kojom se na temelju 3. argumenta (args[2]) određuje klasa čiji će objekti biti korišteni u radu. U ovom slučaju to je klasa Kvadrati. Konstruktoru izabrane klase prenose se 1. i 2. argument. Poziva se metoda ispis().
11.	Kompiliranje klasa uz smještaj .class datoteka na direktorij classes: `javac -d ..\classes org/foi/nwtis/{LDAP_korisnik}/*.java`
12.	Izvršavanje zadatka:  `java -classpath ..\classes org.foi.nwtis.{LDAP_korisnik}.Vjezba_02_1` (uz razne argumente: 1 20 0, 1 5 0)



13.	Napomena: u privitku vježbe nalaze se datoteka vjezba\_02.zip koja sadrži početne datoteka klase Kvadrati i Vjezba\_02\_1. Potrebno je preuzeti datoteke, kopirati na potrebna mjesta i prilagoditi za svoje korisničko ime.
14.	Kreiranje klase NeparniKvadrati\_1  koja nasljeđuje klasu Kvadrati u paketu org.foi.nwtis.{LDAP_korisnik}.kvadrati , metoda void ispis() ispisuje kvadrate neparnih brojeva u intervalu od-do.
15.	Promjena Java datoteke Vjezba\02\_1.java tako da se doda za klasu NeparniKvadrati\_1 što znači da vrijednost 3. argumenta (args[2]) 0 određuje da se koristi klasa Kvadrati, ako je 1 onda je NeparniKvadrati\_1. Ako je 2 onda se instancira klasa NeparniKvadrati_1 i pridružuje se objektu klase Kvadrati.



16.	Kompiliranje klasa uz smještaj .class datoteka na direktorij classes: `javac -d ..\classes org/foi/nwtis/{LDAP_korisnik}/*.java`
17.	Izvršavanje zadatka:  `java -classpath ..\classes org.foi.nwtis.{LDAP_korisnik}.Vjezba_02_1` (uz razne argumente: 1 20 1, 1 20 2)
18.	Kreiranje klase NeparniKvadrati\_2  koja nasljeđuje klasu Kvadrati u paketu org.foi.nwtis.{LDAP_korisnik}.kvadrati, metoda void ispis() ispisuje kvadrate neparnih brojeva u intervalu od-do uz korak od 4.
19.	Promjena Java datoteke Vjezba\_02\_1.java tako da se za vrijednost 3 doda za klasu NeparniKvadrati_2 
---
20.	Kompiliranje klasa uz smještaj .class datoteka na direktorij classes: `javac -d ..\classes org/foi/nwtis/{LDAP_korisnik}/*.java`
21.	Izvršavanje zadatka:  `java -classpath ..\classes org.foi.nwtis.{LDAP_korisnik}.Vjezba_02_1` (uz razne argumente: 1 20 3.)



22.	Kreirati sučelje Ispisiva\_1  u paketu org.foi.nwtis.{LDAP_korisnik}.kvadrati, ima metode void ispisiPodatke() 
23.	Kreiranje klase NeparniKvadrati\_3  koja nasljeđuje klasu NeparniKvadrati\_2 u paketu org.foi.nwtis.{LDAP\_korisnik} i implementira sučelje Ispisivac_1, tj. metodu ispisiPodatke() koja poziva metodu ispis().
24.	Promjena Java datoteke Vjezba\_02\_1.java tako da se za vrijednost 4 doda za klasu NeparniKvadrati_3 a poziva se metoda ispisiPodatke()
25.	Kompiliranje klasa uz smještaj .class datoteka na direktorij classes: `javac -d ..\classes org/foi/nwtis/{LDAP_korisnik}/*.java`



26.	Izvršavanje zadatka:  `java -classpath ..\classes org.foi.nwtis.{LDAP_korisnik}.Vjezba_02_1` (uz razne argumente: 1 20 4)
27.	Promjena Java datoteke Vjezba\_02\_1.java tako da se za vrijednost 5 doda za klasu NeparniKvadrati\_3 s time da se pridružuje objektu sučelja  Ispisivac_1   a poziva se metoda ispisiPodatke()
28.	Kompiliranje klasa uz smještaj .class datoteka na direktorij classes: `javac -d ..\classes org/foi/nwtis/{LDAP_korisnik}/*.java`
29.	Izvršavanje zadatka:  `java -classpath ..\classes org.foi.nwtis.{LDAP_korisnik}.Vjezba_02_1` (uz razne argumente: 1 20 5)
30.	Kreirati sučelje Ispisivac\_2  koje nasljeđuje sučelje Ispisivac\_1 u paketu org.foi.nwtis.{LDAP_korisnik}.kvadrati, ima metodu ispisiPodatkeLinijski() 



31.	Kreiranje klase NeparniKvadrati\_4  koja nasljeđuje klasu NeparniKvadrati\_2 u paketu org.foi.nwtis.{LDAP\_korisnik}.kvadrati i implementira sučelje Ispisivac_2, metoda void ispisiPodatkeLinijski() ispisuje sve podatke u jednoj liniji (koristi se System.out.print(...)).
32.	Promjena Java datoteke Vjezba\_02\_1.java tako da se za vrijednost 6 doda za klasu NeparniKvadrati_4 a poziva se metoda ispisiPodatkeLinijski()
33.	Kompiliranje klasa uz smještaj .class datoteka na direktorij classes: `javac -d ..\classes org/foi/nwtis/{LDAP_korisnik}/*.java`



34.	Izvršavanje zadatka:  `java -classpath ..\classes org.foi.nwtis.{LDAP_korisnik}.Vjezba_02_1` (uz razne argumente: 1 20 6.)
35.	Promjena Java datoteke Vjezba\_02\_1.java tako da se za vrijednost 7 doda za klasu NeparniKvadrati\_4 s time da se pridružuje objektu klase Kvadrati   a poziva se metoda ispis()
36.	Kompiliranje klasa uz smještaj .class datoteka na direktorij classes: `javac -d ..\classes org/foi/nwtis/{LDAP_korisnik}/*.java`



37.	Izvršavanje zadatka:  `java -classpath ..\classes org.foi.nwtis.{LDAP_korisnik}.Vjezba_02_1` (uz razne argumente: 1 20 7.)
38.	Promjena Java datoteke Vjezba\_02\_1.java tako da se za vrijednost 8 doda za klasu NeparniKvadrati\_4 s time da se pridružuje objektu sučelja  Ispisivac_1   a poziva se metoda ispisiPodatke()
39.	Kompiliranje klasa uz smještaj .class datoteka na direktorij classes: `javac -d ..\classes org/foi/nwtis/{LDAP_korisnik}/*.java`




40.	Izvršavanje zadatka:  `java -classpath ..\classes org.foi.nwtis.{LDAP_korisnik}.Vjezba_02_1` (uz razne argumente: 1 20 8.)
41.	Promjena Java datoteke Vjezba\_02\_1.java tako da se za vrijednost 9 doda za klasu NeparniKvadrati\_4 s time da se pridružuje objektu sučelja  Ispisivac_2   a poziva se metoda ispisiPodatkeLinijski()
42.	Kompiliranje klasa uz smještaj .class datoteka na direktorij classes: `javac -d ..\classes org/foi/nwtis/{LDAP_korisnik}/*.java`
43.	Izvršavanje zadatka:  `java -classpath ..\classes org.foi.nwtis.{LDAP_korisnik}.Vjezba_02_1` (uz razne argumente: 1 20 9.)
44.	Kreiranje klase KolikoJeSati u paketu org.foi.nwtis.{LDAP\_korisnik}.kvadrati, implementira sučelje Ispisivac_1, metoda void ispisiPodatke() ispisuje trenutno vrijeme putem klase java.util.Date.
45.	Kreiranje klase NeparniKvadrati\_5 u paketu org.foi.nwtis.{LDAP\_korisnik}.kvadrati, metoda static  Ispisivac\_1 kreirajIspisivac\_1(int odBroja, int doBroja) vraća na temelju modula dobivene vrijednosti generatora slučajnih brojeva: za 0 instanca objekta klase NeparniKvadrati\_3,  za 1 klase NeparniKvadrati_4 i za 2 klase KolikoJeSati.
46.	Promjena Java datoteke Vjezba\_02\_1.java  Ako je 10 onda se u petlji 10 puta poziva NeparniKvadrati\_5.kreirajIspisivac_1(int odBroja, int doBroja), poziva se metoda ispisiPodatke() i ispisuje se naziv klase kojoj pripada dobiveni  objekt
47.	Kompiliranje klasa uz smještaj .class datoteka na direktorij classes: `javac -d ..\classes org/foi/nwtis/{LDAP_korisnik}/*.java`
48.	Izvršavanje zadatka:  `java -classpath ..\classes org.foi.nwtis.{LDAP_korisnik}.Vjezba_02_1` (uz razne argumente: 1 20 10.)