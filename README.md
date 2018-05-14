# PSK komandinis projektas - elektroninės prekybos sistema

Technologies:
* Spring MVC 5
* Maven build automation tool
* TomEE Application server
* Bootstrap 4
* Lombok (@Getter, @Setter)
* JSTL
* Hibernate 5
* h2 database
* Spring Security Authentication
* Angular JS
* Spring Web Flow
* DataTables

Prerequisites:
1. Download and install Java JDK
(also set JAVA_HOME environment path variable if not already set)
2. Download and install IntelliJ (preferred: Ultimate edition, the version at least 2017.3.5)
3. Download and extract TomEE Application server
4. Download and install h2 database

Start:
1. Clone this repository:
git clone https://github.com/pauliax/PSK-Savana.git
2. Open the project in IntelliJ and allow the IDE to automatically do the configuration
3. "Edit configurations" Add -> TomEE server -> Local (press "Configure" and set paths if the server is not found)
Your preferences should look pretty similar: https://imgur.com/iDI0Wpd and 
https://imgur.com/6i0QWac
4. File -> Project Structure -> Artifacts -> Select "Available artifacts", right click "Put into Output Root", Apply, OK
Your preferences should look pretty similar:
https://imgur.com/2fVPNpw
5. File -> Project Structure -> Artifacts -> Output directory, change to: ...(project root)...\src\main\webapp
6. Open h2 console, select "Generic H2 (server)" and connect. Select specific table (for example, PRODUCTS) to view/add/edit/delete data. 
Should look pretty similar: https://imgur.com/JzUIW6N
7. If haven't done before, create authorization tables. Open H2 Console and execute these statements:
  ```sql
  create table users (
      username varchar_ignorecase(30) not null primary key,
      password varchar_ignorecase(60) not null,
      enabled boolean not null);

  create table authorities (
      username varchar_ignorecase(50) not null,
      authority varchar_ignorecase(50) not null,
      constraint fk_authorities_users foreign key(username) references users(username));
      create unique index ix_auth_username on authorities (username,authority); 
  ```
8. Add Admin to the USERS table:
```sql
INSERT INTO USERS VALUES (1, 0, TRUE, '$2a$10$VSBg03R00VpU98Fdo3YJru/8iIAVlZ8p1XlMzI.z.8mEuMAkKwGU2', 'admin');
```
(the decrypted password is: admin)  
9. Add Admin authority to the AUTHORITIES table:  
```sql
INSERT INTO AUTHORITIES VALUES (1, 'ROLE_ADMIN', 'admin');
```
10. Start the server  

Happy developing!

TSP rolės: 
* Komandos lyderis - Mantas Savaniakas
* Kūrimo vadovas - Lukas Cedronas
* Planavimo vadovas - Paulius Švagždys
* Kokybės/proceso vadovas - Tadas Vaitiekūnas
* Palaikymo vadovas - Jonas Švagždys

![reikalavimai](https://github.com/pauliax/PSK-Savana/blob/master/psk-needs.jpg?raw=true "Reikalavimai")
