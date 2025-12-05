# Testaus

Jaoimme testauksen kolmeen eri tasoon. 

## Yksikkötestaus (JUnit)

### Esimerkkejä testeistä 

```java
@Test
public void testCreateEvent() {
    Event event = new Event();
    event.setName("Tikkurila Festival");
    event.setDateTime(LocalDateTime.now().plusDays(200));
    event.setLocation("Tikkurila");
    event.setCapacity(7000);
    assertEquals("Tikkurila Festival", event.getName());
} 

Näissä yksikkötesteissä testattiin

## Integraatiotestaus


## End-to-End(E2E)-testaus 
