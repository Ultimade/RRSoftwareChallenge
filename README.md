# RRSoftwareChallenge
Ty to resolving R&amp;R Software developer test

Indoklás:
Az adatbázis kezeléshez azért a spring-boot-starter-data-jpa, postgresql, flyway-core kombinációt használtam, 
mert ezeket már jól ismerem és eddig beváltak. Ezen kívül a postgre mert szerintem jól managelhető, a flyway pedig 
megbízhatóbb az adatbázis szerkezetének kezelésében mint a hibernate.

Megoldás bemutatása:
A megoldás szokványos mvc formában készült. Az osztályokat szerepkörük szerint fájl struktúrába rendeztem(controller,
dto, modell, enum, repository, service) 

A controllerek felelnek az api kommunikációért melyek a dto-at használják 
adatok fogadására és küldésére. Speciális eset az xml ez a modellen belüli al könyvtár itt vannak 
az xml komunikációhoz szükséges osztályok.

A repository-ba az sql komunikációhoz használt interfészek találhatóak melyek mindegyike a JpaRepository kibővítése.Itt 
speciális eset a statisztikai adatok lekérdezése , mert sima query-vel nehézkes megoldani a csoportosított és 
megszámolt adatokat megfelelő obijektumba rakni így A DailyStatistic entity-be bekerültek named query-ként azok 
a lekérdezések melyek összesített értéket adnak visza és egy külön Class készült ezek tárolására.

A dto és model mappa tárolja azokat az osztályokat melyek példányai az adatokat  tartalmazzák.

A service mappába azok az osztályok kerültek melyek a funkciókat valósítják meg, 
vagyis a rendszer logikai része itt van megírva ezekben az osztályokban. Mielött magát a logikát kidolgoztam volna 
az adott szervízeket interfészként hoztam létre bennük leírva a szükségesnek vélt metódusokat, 
majd ezeket implementáltam így biztosítva azt, hogy a funkciók mindegyike kidolgozásra kerüljön.

A resources mappában találhatóak a rendszer konfigurációs fájljai és a flyway migrációs sql kódjai.

Felhasznált lib-ek:
Alapvetően a build.gradle fájlba fel vannak kommentelve a libek funkciójuk szerint, de 1-2 libet kiemelnék:
'org.flywaydb:flyway-core:8.0.2': segítségével natív sql-el készíthető el a db ami biztonságosabb számomra 
mint az entitik-re bízni az adatbázis megformálását. Természetesen az alap felépítés azonos így biztosítva, hogy a 
query-k által kinyert adatok megfelelően kerülnek az obijektumokba. Ezen kívül a flyway verzióza is a db-t 
így annak struktúrális változása nyomonkövethető illetve az illetéktelen szerkezeti módosítások is észlelhetőek.
'org.projectlombok:lombok:1.18.22': leegyszerűsíti az osztályok készítését mivel pár annotációval elkészíthető 
az összes getter/setter, üres és minden elemet tartalmazó konstruktor.
org.hibernate:hibernate-core és org.hibernate:hibernate-envers: Az adatbázis auditálásához szükséges libek.
'org.springframework.boot:spring-boot-starter-thymeleaf:2.5.6' és 'org.springframework.boot:spring-boot-starter-web':
nem feltétlenül szükségesek, de én szeretem, ha a backendnek van egy kis saját felülete. jelen esetben csak 
teszt céljából, hogy elérhető-e ill, hogy megfelelő porton érhető-e el az oldal.

Egyébb infók:
A projekt 3 ágra van bontva:
-Master ez nagyából az első 3-om óra termése(úgy érzem, hogy a teljes feladat elvégzésére 3-om óra kevés.)
-after3hours: minden ami nem fért bele a 3-om órába, de úgy gondoltam érdemes befejezni ha mást nem gyakorlás céljából.
-afterpresent: minden más amit a jövőben a kódon fejlesztek.