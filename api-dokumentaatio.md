# API - Dokumentaatio

## Base URL: 

- http://localhost:8080/api 

## Endpointit

### Event: 

- /api/events
- /api/events/{id}

## Metodit, polku, polkuparametrit, query-parametrit events:

| Metodi | Polku | Polkuparametrit | Query-parametrit |
|--------|-------|----------------|-----------------|
| GET    | /api/events | – | ?date=2025-09-23&location=helsinki&page=2&limit=10 |
| GET    | /api/events/{id} | {id}=123 | – |
| GET    | /api/events/{id} | {id}=999 | – |
| POST   | /api/events | – | – |
| PUT    | /api/events/{id} | {id}=123 | ?notifyUsers=true |
| DELETE | /api/events/{id} | {id}=123 | – |

### Lyhyet kuvaukset endpointien toiminnasta

#### GET /api/events
Hakee kaikki tapahtumat. Palauttaa listan kaikista järjestelmän tapahtumista. Hakua voi rajata query-parametreilla, kuten päivämäärä, sijainti, sivutus jne.

#### GET /api/events/{id}
Hakee yksittäisen tapahtuman tunnisteen perusteella. Palauttaa tapahtuman tiedot, jos id löytyy, muuten 404 Not Found.

#### POST /api/events
Luo uuden tapahtuman. Lähetä tapahtuman tiedot pyynnön bodynä (JSON). Palauttaa luodun tapahtuman ja statuskoodin 201 Created.

#### PUT /api/events/{id}
Päivittää olemassa olevan tapahtuman annetulla id:llä. Lähetä uudet tiedot pyynnön bodynä. Palauttaa päivitetyn tapahtuman tai 404 Not Found, jos id:tä ei löydy.

#### DELETE /api/events/{id}
Poistaa tapahtuman annetulla id:llä. Palauttaa statuskoodin 204 No Content, jos poisto onnistuu, tai 404 Not Found, jos tapahtumaa ei löydy.

### Esimerkkituloste:

```java
{
  "name": "OpenAI Hackathon",
  "date": "2025-11-01",
  "location": "Tampere",
  "capacity": 200
}
```
- **POST-pyynnöissä** ID:tä ei anneta — se luodaan automaattisesti palvelimella.

## Endpointit

## Sale: 

- api/sales
- api/sales/{id}

### SaleController

| Metodi | URL | Kuvaus |
|--------|-----|--------|
| GET    | `/api/sales` | Hae kaikki myynnit |
| GET    | `/api/sales/{id}` | Hae yksittäinen myynti ID:llä |
| POST   | `/api/sales` | Luo uusi myynti (SaleCreateDto) |
| PUT    | `/api/sales/{id}` | Päivitä olemassa oleva myynti |
| DELETE | `/api/sales/{id}` | Poista myynti |

<img src="https://github.com/Jani638/TicketGuru-BittiPossut/blob/feature/Joel/POST-api-sales.png?raw=true" alt="POST api/sales" width=400 />

 ## POST- esimerkki:

 ```java
    {
  "ticketId": 101,
  "customerId": 202,
  "sellerId": 303
}
```
- **POST-pyynnöissä** ID:tä ei anneta — se luodaan automaattisesti palvelimella.

## GET- esimerkki:

```java
{
    "id": 1,
    "ticketId": 101,
    "customerId": 202,
    "sellerId": 303,
    "saleDate": "2025-09-30T11:11:02.042723"
}
```

## PUT- esimerkki:
```java
{
    "id": 1,
    "ticketId": 321,
    "customerId": 202,
    "sellerId": 303,
    "saleDate": "2025-09-30T10:34:08.457799"
}
```

## DELETE- esimerkki:

| DELETE | `/api/sales/{id}` | Poistaa myyntitapahtuman | 

## Miksi loimme SaleService-luokan?

SaleService on apuluokka, joka hoitaa myyntitapahtuman taustatyöt. Kun käyttäjä tekee myynnin, ohjelman pitää:
- Tarkistaa kuka ostaa ja kuka myy
- Hakea oikeat liput
- Varmistaa että liput eivät ole jo myyty
- Tallentaa myynti tietokantaan

Jos kaikki tämä tehtäisiin suoraan controllerissa, koodi olisi sekavaa ja vaikea ylläpitää. Siksi loimme SaleService-luokan, joka hoitaa nämä asiat taustalla.

### Hyödyt

- Selkeämpi koodi
- Helpompi testata
- Laajennettavuus tulevaisuudessa


## User

- api/users
- api/users/{id}

### UserController

| Metodi | URL               | Kuvaus                            |
| ------ | ----------------- | --------------------------------- |
| GET    | `/api/users`      | Hae kaikki käyttäjät              |
| GET    | `/api/users/{id}` | Hae yksittäinen käyttäjä ID:llä   |
| POST   | `/api/users`      | Luo uusi käyttäjä (UserCreateDto) |
| PUT    | `/api/users/{id}` | Päivitä olemassa oleva käyttäjä   |
| DELETE | `/api/users/{id}` | Poista käyttäjä                   |

## POST-esimerkki:
```java
{
  "username": "johnpork",
  "password": "secret123",
  "role": "admin"
}
```

## GET-esimerkki:
```java
{
  "id": 1,
  "username": "johnpork",
  "role": "admin"
}
```

## PUT-esimerkki:
```java
{
  "id": 1,
  "username": "johnpork",
  "password": "newpassword456",
  "role": "admin"
}
```

## DELETE- esimerkki:

| DELETE | `/api/users/{id}` | Poistaa käyttäjän |


## TicketType

- api/tickets/types
- api/tickets/types/{id}

### TicketTypeController

| Metodi | URL                     | Kuvaus                                        |
| ------ | ----------------------- | --------------------------------------------- |
| GET    | `/api/tickets/types`      | Hae kaikki lippukategoriat                    |
| GET    | `/api/tickets/types/{id}` | Hae yksittäinen lippukategoria ID:llä         |
| POST   | `/api/tickets/types`      | Luo uusi lippukategoria (TicketTypeCreateDto) |
| PUT    | `/api/tickets/types/{id}` | Päivitä olemassa oleva lippukategoria         |
| DELETE | `/api/tickets/types/{id}` | Poista lippukategoria                         |


## POST-esimerkki:
```java
{
  "eventId": 123,
  "name": "VIP",
  "price": 150.00,
  "quantity": 50
}
```

## GET-esimerkki:
```java
{
  "id": 1,
  "eventId": 123,
  "name": "VIP",
  "price": 150.00,
  "quantity": 50
}
```

## PUT-esimerkki:
```java
{
  "id": 1,
  "eventId": 123,
  "name": "Standard",
  "price": 75.00,
  "quantity": 200
}
```

## DELETE-esimerkki: 

| DELETE | `/api/tickettypes/{id}` | Poistaa lippukategorian |


## Ticket

- api/tickets
- api/tickets/{id}

### TicketController

| Metodi | URL                 | Kuvaus                           |
| ------ | ------------------- | -------------------------------- |
| GET    | `/api/tickets`      | Hae kaikki liput                 |
| GET    | `/api/tickets/{id}` | Hae yksittäinen lippu ID:llä     |
| POST   | `/api/tickets`      | Luo uusi lippu (TicketCreateDto) |
| PUT    | `/api/tickets/{id}` | Päivitä olemassa oleva lippu     |
| DELETE | `/api/tickets/{id}` | Poista lippu                     |


## POST-esimerkki:
```java
{
  "ticketTypeId": 1,
  "code": "VIP001",
  "sold": false,
  "used": null
}
```

## GET-esimerkki:
```java
{
  "id": 1,
  "ticketTypeId": 1,
  "code": "VIP001",
  "sold": true,
  "used": null
}
```

## PUT-esimerkki:
```java
{
  "id": 1,
  "ticketTypeId": 1,
  "code": "VIP001",
  "sold": true,
  "used": null
}
```

## DELETE-esimerkki:

| DELETE | `/api/tickets/{id}` | Poistaa lipun |


## Lippukoodin perusteella hakeminen:

| Metodi | URL                 | Kuvaus                           |
| ------ | ------------------- | -------------------------------- |
| GET    | `/api/tickets?=VIP001`      | Hakee lipun koodin perusteella                 |
|
## Esimerkki:
```java
{
  "id": 1,
  "ticketTypeId": 1,
  "code": "VIP001",
  "sold": false,
  "used": null
}
```

## Patch-esimerkki:

| Metodi | URL                 | Kuvaus                           |
| ------ | ------------------- | -------------------------------- |
| PATCH    | `/api/tickets/10`      | Merkkaa lipun käytetyksi ja asettaa ajankohdan                 |
|
#### Body valitse raw ja JSON, ja kirjoita
```json
{
    "used": "2023-11-07T07:03:46"
}
```

## Esimerkki:
```java
{
    "id": 10,
    "ticketTypeId": 1,
    "code": "VIP001",
    "sold": false,
    "used": "2023-11-07T07:03:46"
}
```


