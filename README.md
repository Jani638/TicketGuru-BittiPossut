# TicketGuru-BittiPossut

## Johdanto 

Asiakkaamme on lipputoimisto, joka haluaa parantaa lippujen myyntiä heidän myyntipisteessään. Järjestelmän tarkoituksena on mahdollistaa sujuva lipunmyynti myyntipisteessä. Lipuissa on koodi, joka luetaan ovella, jonka avulla lippu voidaan merkata käytetyksi. 

Tarkoituksena on luoda järjestelmä, joka on selkeä ja nopea. Lipuissa on oltava helposti tarkastettava koodi, jonka avulla lippu voidaan varmistaa ovella.  Tulevaisuudessa järjestelmään olisi tarkoitus lisätä myös verkkokauppa. 

## Järjestelmän määrittely

## Käyttötapaukset
Käyttötapauskaavio:
<img width="696" height="572" alt="image" src="https://github.com/user-attachments/assets/b7ebd84c-8237-4d35-b3e8-7e5df6cba0a3" />

## Toiminnalliset vaatimukset

- Ylläpitäjä pystyy muokkaamaan, lisäämään ja poistamaan tapahtumia helposti. 
Tapahtumista on oltava saatavilla olennaiset tiedot kuten nimi, päivämäärä, kellonaika ja paikka. 

- Lipunmyyjä pystyy valitsemaan tietyn tapahtuman ja lipputyypin. 

- Liput pystyy tulostaa ovella. Lipuissa oleva koodi varmistaa, että lippu on aito ja se merkitään käytetyksi. 

## Ei-toiminnalliset vaatimukset

- Järjestelmän pitää olla helposti laajennettava tulevaisuuden verkkokauppa järjestelmää varten. 
- Selkeä ja helppokäyttöinen. 
- Järjestelmä tallentaa ja säilyttää käyttäjien tiedot turvallisesti. 

## Järjestelmän rajoitukset

- Aluksi järjestelmä tehdään vain  lippujen myynti myyntipisteessä, ei verkkokauppaa. 
- Lippujen on oltava tulostettavia.
- Lippujen tarkastus toimii tietyssä järjestelmässä. 

## Käyttäjäroolit
- Asiakas
- Myyjä
- Tarkastaja

## Käyttäjätarinat

### 1 tarina (asiakas)
> Asiakkaana haluan saada paperisen lipun, jossa on koodi, jotta voin todistaa ostaneeni lipun.

### 2 tarina (asiakas)
> Asiakkaana haluan ostaa lipun turvallisesti viralliselta myyntipisteeltä, jotta voin osallistua tapahtumaan.

### 3 tarina (myyjä)
> Myyjänä haluan kirjautua turvallisesti järjestelmään, jotta voin myydä ja palvella asiakkaita helposti.

### 4 tarina (myyjä)
> Myyjänä haluan nähdä paljonko lippuja on jäljellä, ja paljonko niitä on myyty. Helppo nähdä myös paljonko lippuja jää yli.

### 6 tarina (tarkastaja)
> Tarkastajana haluan skannata lipun koodin ovella, jotta voin merkitä lipun käytetyksi.


