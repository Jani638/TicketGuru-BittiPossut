# Rajapinnan testausohjeet

Tässä dokumentissa kuvataan, miten testataan API-rajapinnan toimintaa, jotta se täyttää seuraavat vaatimukset:

- Kaikissa tilanteissa annetaan oikea vastauskoodi:
  - Onnistuneet pyynnöt palauttavat HTTP-koodin **200** (tai muut asianmukaiset 2xx-koodit kuten 201 Created, 204 No Content).
  - Puuttuvista resursseista palautetaan **404 Not Found**.
  - Virheellisistä pyynnöistä palautetaan **400 Bad Request** (esim. puuttuvat pakolliset kentät, virheellinen data).
- Missään tilanteessa ei tule **500 Internal Server Error** -vastauskoodia.
- **POST**- ja **PUT**-pyynnöissä vaaditaan vähintään kaikki määritellyt pakolliset kentät datasta, jotta pyyntö voidaan suorittaa.

## Testattavat asiat resurssikohtaisesti

### 1. Events

- **GET /api/events**  
  Testaa onnistunut haku ilman parametreja → odotettu koodi **200**.  
  Testaa haku parametreilla (date, location, page, limit) → odotettu koodi **200**.

- **GET /api/events/{id}**  
  Testaa olemassa oleva id → **200**.  
  Testaa olematon id → **404**.

- **POST /api/events**  
  Testaa puuttuvilla pakollisilla kentillä → **400**.  
  Testaa oikealla datalla (esim. name, date, location, capacity) → **201**.

- **PUT /api/events/{id}**  
  Testaa olemassa oleva id & oikea data → **200**.  
  Testaa puuttuvilla kentillä → **400**.  
  Testaa olematon id → **404**.

- **DELETE /api/events/{id}**  
  Olemassa oleva id → **204**.  
  Olematon id → **404**.

### 2. Sales

- **GET /api/sales**  
  Hae kaikki myynnit → **200**.

- **GET /api/sales/{id}**  
  Olemassa oleva id → **200**.  
  Olematon id → **404**.

- **POST /api/sales**  
  Testaa puuttuvilla kentillä (ticketId, customerId, sellerId) → **400**.  
  Oikea data → **201**.

- **PUT /api/sales/{id}**  
  Olemassa oleva id & oikea data → **200**.  
  Puuttuvat kentät → **400**.  
  Olematon id → **404**.

- **DELETE /api/sales/{id}**  
  Olemassa oleva id → **204**.  
  Olematon id → **404**.

### 3. Users

- **GET /api/users**  
  Kaikki käyttäjät → **200**.

- **GET /api/users/{id}**  
  Olemassa oleva id → **200**.  
  Olematon id → **404**.

- **POST /api/users**  
  Puuttuvat kentät (username, password, role) → **400**.  
  Oikea data → **201**.

- **PUT /api/users/{id}**  
  Olemassa oleva id & oikea data → **200**.  
  Puuttuvat kentät → **400**.  
  Olematon id → **404**.

- **DELETE /api/users/{id}**  
  Olemassa oleva id → **204**.  
  Olematon id → **404**.

### 4. TicketTypes

- **GET /api/tickets/types**  
  Kaikki lippukategoriat → **200**.

- **GET /api/tickets/types/{id}**  
  Olemassa oleva id → **200**.  
  Olematon id → **404**.

- **POST /api/tickets/types**  
  Puuttuvat kentät (eventId, name, price, quantity) → **400**.  
  Oikea data → **201**.

- **PUT /api/tickets/types/{id}**  
  Olemassa oleva id & oikea data → **200**.  
  Puuttuvat kentät → **400**.  
  Olematon id → **404**.

- **DELETE /api/tickets/types/{id}**  
  Olemassa oleva id → **204**.  
  Olematon id → **404**.

### 5. Tickets

- **GET /api/tickets**  
  Kaikki liput → **200**.

- **GET /api/tickets/{id}**  
  Olemassa oleva id → **200**.  
  Olematon id → **404**.

- **POST /api/tickets**  
  Puuttuvat kentät (ticketTypeId, code, sold, used) → **400**.  
  Oikea data → **201**.

- **PUT /api/tickets/{id}**  
  Olemassa oleva id & oikea data → **200**.  
  Puuttuvat kentät → **400**.  
  Olematon id → **404**.

- **DELETE /api/tickets/{id}**  
  Olemassa oleva id → **204**.  
  Olematon id → **404**.
