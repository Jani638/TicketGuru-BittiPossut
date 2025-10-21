# Autentikointi 

## Yleistä 

Projektiin on lisätty HTTP Basic Authentication - suojaus kaikille /api/** endpointeille. Eli kaikki API-pyynnöt vaativat kirjautumisen.  

---

## Toteutus

### Riippuvuudet

`pom.xml`-tiedostoon lisätty: 

```xml
</dependency>
		<dependency>
    	<groupId>org.springframework.boot</groupId>
    	<artifactId>spring-boot-starter-security</artifactId>
	</dependency>
```
### Testikäyttäjä

**Käyttäjä:** `user`\
**Salasana:** `password`

Testikäyttäjä on kovakoodattu käyttäen `InMemoryUserDetailsManager`-ratkaisua.

```java
@Configuration
@EnableWebSecurity

public class SecurityConfig { 

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> authz
                .requestMatchers("/api/**").authenticated()
                .anyRequest().permitAll())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService users() {
        UserDetails user = User.builder()
        .username("user")
        .password("{noop}password")
        .roles("USER")
        .build();
        return new InMemoryUserDetailsManager(user);
    }
}
```

## Testaus / Postman

### Ilman autentikaatiota

**Pyyntö:** GET http://localhost:8080/api/users 

**Vastaus:** 

```json 
{
    "timestamp": "2025-10-21T09:50:58.341+00:00",
    "status": 401,
    "error": "Unauthorized",
    "message": "Unauthorized",
    "path": "/api/users"
}
```

Tästä tulee virhekoodi 401, Unauthorized. Eli pääsyyn ei ole oikeuksia koska käyttäjä ei vielä ole kirjautunut. 

### Autentikaatiolla

Authorization → Basic Auth: 

**Username:** `user`\
**Password:** `password` 

**Pyyntö:** GET http://localhost:8080/api/users 

**Vastaus:**

```json 
[{"id":1,"username":"seller1","password":"password","role":"SELLER"},{"id":2,"username":"seller2","password":"password","role":"SELLER"}]
```

Suojaus onnistui, koska nyt kirjautuneena pystyy näkemään käyttäjien tiedot. Sama toimii muillekkin endpointeille. 