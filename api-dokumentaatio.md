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

- api/events
- api/events/{id}

### SaleController

| Metodi | URL | Kuvaus |
|--------|-----|--------|
| GET    | `/api/sales` | Hae kaikki myynnit |
| GET    | `/api/sales/{id}` | Hae yksittäinen myynti ID:llä |
| POST   | `/api/sales` | Luo uusi myynti (SaleCreateDto) |
| PUT    | `/api/sales/{id}` | Päivitä olemassa oleva myynti |
| DELETE | `/api/sales/{id}` | Poista myynti |


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


