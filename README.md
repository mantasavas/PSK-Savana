# PSK komandinis projektas - elektroninės prekybos sistema

Technologies:
* Spring MVC
* Maven build automation tool
* TomEE Application server

Prerequisites:
1. Download and install Java JDK
(also set JAVA_HOME environment path variable if not already set)
2. Download and install IntelliJ (preferred: Ultimate edition, the version at least 2017.3.5)
3. Download and extract TomEE Application server

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
5. Start the server

Happy developing!

TSP rolės: 
* Komandos lyderis - Mantas Savaniakas
* Kūrimo vadovas - Lukas Cedronas
* Planavimo vadovas - Paulius Švagždys
* Kokybės/proceso vadovas - Tadas Vaitiekūnas
* Palaikymo vadovas - Jonas Švagždys

![reikalavimai](https://github.com/pauliax/PSK-Savana/blob/master/psk-reikalavimai.jpg?raw=true "Reikalavimai")
>>>>>>> d364af089ce52927072de1c9fb66c5705ddd88f3