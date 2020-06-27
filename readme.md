

<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->


<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://github.com/bszczepa/RuntimeTerror">
    <img src="images/logo.png" alt="Logo" width="700" height="125">
  </a>

  <h3 align="center">RunTime Terror Reporting Tool ver 1.0</h3>

  <p align="center">
    Konsolowe narzędzie do raportowania w obszarze Project Managementu, stworzone przez zespół RunTime Terror w składzie:
    <br /> >> <a href="https://github.com/AnnaMadej"> Anna Madej </a>
    <br /> >> <a href="https://github.com/sloppysheep"> Aleksandra Wandzilak </a>
    <br /> >> <a href="https://github.com/martatomalska"> Marta Tomalska </a>
    <br /> >> <a href="https://github.com/zawibe"> Beata Zawidlak-Cyganek </a>
    <br /> >> <a href="https://github.com/mateuszsutor"> Mateusz Sutor </a>
    <br /> >> <a href="https://github.com/bszczepa"> Bartosz Szczepański </a>
    <br/> 
    <br />
    <a href="https://github.com/bszczepa/RuntimeTerror"><strong>Przejrzyj dokumentację »</strong></a>
    <br />
    <br />
    <br/>
    <a href="https://github.com/bszczepa/RuntimeTerror/issues">Znalazłem buga </a>
    |
    <a href="https://github.com/bszczepa/RuntimeTerror/issues">Zaproponuj funkcjonalność </a>
  </p>
</p>



<!-- TABLE OF CONTENTS -->
## Spis treści

* [O projekcie](#o-projekcie)
* [Dokumentacja](#dokumentacja)
  * [Wymagania](#wymagania)
  * [Instalacja](#instalacja)
* [Uruchomienie](#uruchomienie)
  * [Korzystanie](#korzystanie")
  * [Obsługa błędów](#obsluga-bledow)
* [Roadmapa](#roadmapa)
* [Licencja](#licencja)


<!-- ABOUT THE PROJECT -->
## O projekcie

   <br />
  <a href="https://github.com/bszczepa/RuntimeTerror">
    <img src="images/interface.png" alt="Interface" width="720" height="469">
  </a>
   <br />
   <br />
   
Narzędzie do raportowania uwzględniające następujące funkcjonalności:
 - Wczytywanie danych z plików w podanym folderze
 - Interfejs konsolowy CLI
 - Generowanie raportu godzin pracowników w danym roku
 - Generowanie raportu godzin projektów w podanym roku
 - Generowanie raportu godzin przepracowanych miesięcznie przez pracownika w podanym roku
 - Generowanie raportu procentowego udziału projektów w pracy osób dla podanego roku
 - Generowanie raportu ilości godzin pracowników w podanym projekcie
 - Generowanie wykresu słupkowego ilości godzin projektów w podanym roku
 - Generowanie wykresu kołowego procentowego udziału projektów dla pracowników w podanym roku
 - Eksportowanie raportów do plików MS Excel
 - Jeden plik wykonywalny JAR
 - Obsługa błędów oraz ich specyfikacja

<!-- GETTING STARTED -->
## Dokumentacja

Aby uruchomić lokalną kopię pliku należy:

### Wymagania

- co najmniej Java 8 SE Runtime Environment
- wiersz poleceń

### Instalacja

Sklonuj Runtime Terror
```sh
git clone https://github.com/bszczepa/RuntimeTerror.git
```
 Zainstaluj zależności Mavenowe i stwórz plik JAR
```sh
mvn install
```
W podkatalogu Target pojawi się plik RuntimeTerror-1.0-shaded.jar.


<!-- USAGE EXAMPLES -->
## Uruchomienie

Aby uruchomić narzędzie należy w wierszu poleceń wejść w katalog zawierajćy plik RuntimeTerror-1.0-shaded.jar 
i użyć komendy:

```sh
java -jar RuntimeTerror-1.0-shaded.jar "ścieżkaDoFolderuZPlikamiDanych"
```
np. 
```sh
java -jar RuntimeTerror-1.0-shaded.jar "C:\Users\Bartek\Desktop\reporter-dane2"
```
### Korzystanie

Aby wywołać relewantną funkcję należy podać nr odpowiedniej opcji z interfejsu, następnie postępować wedle wskazówek programu. 

### Obsluga bledow

Program przeprowadza wstępną walidację poniższych błędów - wiersze błędne są nieuwzględniane w dalszych obliczeniach.

- pusty wiersz
- pusta komórka
- komórka nie zawiera daty
- pusta komórka 
- komórka nie zawiera opisu
- komórka nie zawiera czasu
- komórki nie zawierają wartości numerycznej
- źle wpisana data
- nieprawidłowy rok w dacie
- nieprawidłowy miesiąc w dacie

Listę błędów i zignorowanych wierszy można zobaczyć wybierając funkcję nr 8 w głównym menu.

<!-- ROADMAP -->
## Roadmapa

Obejrzyj [open issues](https://github.com/bszczepa/RuntimeTerror/issues)  aby zapoznać się z propozycjami dalszych funkcjonalności oraz wykrytymi błędami.


<!-- LICENSE -->
## Licencja

Produkt komercyjny typu Single User. Zakaz kopiowania i rozpowszechniania. 



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/othneildrew/Best-README-Template.svg?style=flat-square
[contributors-url]: https://github.com/othneildrew/Best-README-Template/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/othneildrew/Best-README-Template.svg?style=flat-square
[forks-url]: https://github.com/othneildrew/Best-README-Template/network/members
[stars-shield]: https://img.shields.io/github/stars/othneildrew/Best-README-Template.svg?style=flat-square
[stars-url]: https://github.com/othneildrew/Best-README-Template/stargazers
[issues-shield]: https://img.shields.io/github/issues/othneildrew/Best-README-Template.svg?style=flat-square
[issues-url]: https://github.com/othneildrew/Best-README-Template/issues
[license-shield]: https://img.shields.io/github/license/othneildrew/Best-README-Template.svg?style=flat-square
[license-url]: https://github.com/othneildrew/Best-README-Template/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=flat-square&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/othneildrew
[product-screenshot]: images/screenshot.png