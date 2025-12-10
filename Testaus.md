# Testaus

Jaoimme testauksen kolmeen eri pääluokkaan. **Yksikkötestaukseen**, **integraatiotestaukseen** ja **End-to-End (E2E)**. 

## Testauskäytännöt 
- Testit ajetaan JUnit 5 - kehitysympäristössä.
- Integraatiotestit käyttävät in-memory tietokantaa (H2).
- E2E-testit hyödyntävät myös TestRestTemplatea ja autentikointia.

## Yksikkötestaus (JUnit)

Yksikkötestien tarkoitus on testata yksittäisiä luokkia ja metodeita. 

#### Esimerkkejä tehdyistä testeistä 

```java
@Test
    public void testCreateEvent() {
        Event event = new Event();
        event.setName("Tikkurila Festival");
        event.setDateTime(LocalDateTime.now().plusDays(200));
        event.setLocation("Tikkurila");
        event.setCapacity(7000);

        assertEquals("Tikkurila Festival", event.getName());
        assertEquals("Tikkurila", event.getLocation());
        assertEquals(7000, event.getCapacity());
        assertNotNull(event.getDateTime());
    }

@Test
    public void testMarkAsSold() {

        Ticket ticket = new Ticket();
        ticket.setSold(false);
        ticket.setSold(true);
        assertTrue(ticket.getSold());
    }
```

   
### Mitä testattiin? : 
-  Event - ja Ticket-olioiden kenttien oikeat arvot ja toiminta
-  Lipun `sold` status päivittyy oikein
-  ID-generointi toimii
  
## Integraatiotestaus

Näissä testeissä varmistimme, että ohjelman eri komponentit toimivat yhdessä oikein. Kuten REST API ja tietokanta. 

#### Esimerkkejä tehdyistä testeistä 

```java

@Test
    public void testGetEventsEndpoint() throws Exception {
        mockMvc.perform(get("/api/events")
                .with(httpBasic("user", "password")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", instanceOf(java.util.List.class)));
    }
```
### Mitä testattiin? : 
- REST endpointtien palauttamat statuskoodit.
- Autentikoinnin ja tietokantaintegraation toimivuus
- JSON-datan rakenne ja arvot
  
## End-to-End(E2E)-testaus 

Nämä testit antavat selkeän kuvan siitä, miltä lippujen myyntiprosessi näyttäisi käyttäjän näkökulmasta. 

#### Esimerkkejä tehdyistä testeistä 

```java

@Test
    public void testCreateTicketAndFindByCode() {
        Ticket newTicket = new Ticket(null, testTicketTypeId, "TEST123", false, null);
        
        HttpEntity<Ticket> request = new HttpEntity<>(newTicket);
        ResponseEntity<Ticket> createResponse = restTemplate
            .withBasicAuth("user", "password")
            .postForEntity(getBaseUrl(), request, Ticket.class);
        
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(createResponse.getBody()).isNotNull();
        assertThat(createResponse.getBody().getCode()).isEqualTo("TEST123");
        assertThat(createResponse.getBody().getUsed()).isNull();

        ResponseEntity<Ticket> getResponse = restTemplate
            .withBasicAuth("user", "password")
            .getForEntity(getBaseUrl() + "?code=TEST123", Ticket.class);
        
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getResponse.getBody()).isNotNull();
        assertThat(getResponse.getBody().getCode()).isEqualTo("TEST123");
    }
```

### Mitä testattiin?: 
- Lipun luonti ja tallennus tietokantaan
- Lipun haku koodilla
- Lipun merkitseminen käytetyksi
- Virhetilanteiden käsittely

