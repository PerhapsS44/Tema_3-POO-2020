# Proiect Energy System Etapa 2

## Despre

Proiectare Orientata pe Obiecte, Seriile CA, CD
2020-2021

<https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/proiect/etapa2>

Student: Saraev Stefan, 322CA

## Rulare teste

Clasa Test#main
  * ruleaza solutia pe testele din checker/, comparand rezultatele cu cele de referinta
  * ruleaza checkstyle

Detalii despre teste: checker/README

Biblioteci necesare pentru implementare:
* Jackson Core 
* Jackson Databind 
* Jackson Annotations

Tutorial Jackson JSON: 
<https://ocw.cs.pub.ro/courses/poo-ca-cd/laboratoare/tutorial-json-jackson>

## Implementare

Pentru a realiza tema am folosit 2 entitati, numite Database si GameMaster. In prima salvez 
datele pe care le prelucrez, iar in a doua am realizat logica simularii.

### Entitati

SystemIO: InputData, IOFactory, IOSystem, OutputData, Reader, Writer
- le folosesc ca sa citesc din si sa scriu in fisiere

InputClasses: InitialData, DistributorChange, MonthlyUpdate, ProducerChange 
- le folosesc ca sa citesc structura de date din fisierele de input

OutputClasses: ConsumerOutput, DistributorOutput, MonthlyStats, ProducerOutput
- le folosesc ca sa scriu structura de date in fisierele de output

BaseClasses: Consumer, Contract, Distributor, Player, Producer
- le folosesc ca sa implementez logica simularii

SortingStrategies: FactoryStrategy, GreenSortingStrategy, PriceSortingStrategy, QuantitySortingStrategy,
SortingStrategy
- le folosesc ca sa implementez sttrategiile de sortare

Database

GameMaster

### Flow

Conform descrierii simularii de pe ocw, am runda initiala si apoi un loop prin toate rundele
In loop, fac update la inceput de luna, apoi rulez logica de implementare, apoi fac update la
finalul lunii

### Elemente de design OOP

Interfete: le-am folosit ca sa fac Strategy Pattern si ca sa implementez IOFactory

Incapsularea: am folosit o peste tot, pt ca checkstyle zice asa

Mosteniri: le-am folosit ca sa creez clasele Consumer, Distributor si Producer, in baza datelor 
comune din Player; am facut asta la inceput pt ca ma gandeam ca voi face un factory dupa Player

### Design patterns

Singleton: Database, Gamemaster
- am nevoie doar de cate o instanta pentru intreg programul

Factory: FactoryStrategy, IOFactory
- cu astea imi aleg strategiile de sortare, respectiv daca sa scriu sau sa citesc date

Strategy: SortingStrategy
- cu astea aleg metoda de sortare in functie de modul de sortare dorit de un distribuitor

Observer: Distributor, Producer
* am implementat logica acestui design pattern in cuplul Distributor, Producer
* cand un Producer isi face update la date (setEnergyPerDistributor), anunta toti distribuitorii
si acestia isi dau update la state-ul curent (needProdUpdate)

### Dificultati intalnite, limitari, probleme

In afara de debug si de intelegerea logicii testelor, nu am intampinat dificultati

12 ore si am terminat de codat toata tema

## Feedback, comments

Sa mi mosteniti frustrarile cu debugging ul la tema asta si sa agregati farmece anti-injuraturi
la proiectele viitoare

KUTGW!

