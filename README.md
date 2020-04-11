![Java CI with Maven](https://github.com/alesky78/CryptocurrencyAlerting/workflows/Java%20CI%20with%20Maven/badge.svg?branch=master)

# Welcome to CryptocurrencyAlerting
CryptocurrencyAlerting is a Desktop application allows you to monitoring the cryptocurrency price and generate an alert when specific conditions are meet.

this software is implemented using java swing technology
  
the CryptocurrencyAlerting allow the user to monitor:
 - if the price of the cryptocurrency goes above or below a certain price and then trigger an action
 
 The actual actions imlpemented are:
 - send a mail
 - create a popup on the desktop and emit a sound

## How to build
when downloaded this repository, in the main folder  **CryptocurrencyAlerting** run the bat file 

```
compile.bat
```
in alternative run the maven command on the root of the project

```
mvn clean compile install
```

the build will create in the target folder zip file  **cryptocurrencyalerting-version-x.x.zip**
that is the package ready to start

unziping this file will create the folder cryptocurrencyalerting-version-x.x that you can move wherever you prefere


## Configuration
It is requrested a minimal configuration before to start the application:
 - configure the provider for the api of the market criptocurrency prices (mandatory step)
 - configure the provide to send mail (not mandatory in case you want to use just the popup alert)

### Configuration of the market price adapter  - mandatory step
Obviusly the value of the cryptocurrencies must be obtained by a specific provider.
The application is based on interfaces, then it is possible to implement a specific adpter, for any provide that is able to expose the interface that give back the price of the cryptocurrency,
and configure the application to use it.

In this Actual version the application is shipped wiht the follow adapters already implemented:
 - testAdapter --> used for test purpose in case you want to test or extend the application
 - coinmarketcap

to define the market Adapter that the application must use, configure the main configuration file,
```
configuration\configuration.properties
```

#### Coinmarketcap api cofiguration
This provider has different pricing to use its api, but the basic usage is for free.
Aniway it is requested to login in the applicatiom https://pro.coinmarketcap.com/login/ and generate an API key to use its services.
After the API key is gerenate, it must be introduced in the configuration file 
```
configuration\marketprovider\coinmarketcap\CoinMarketCap.properties
```

### Configuration of the actions
as described before, actually these are the actions already implemented
 - send a mail 
 - create a popup on the desktop and emit a sound
 
 these actions can be configured in the way to tailor the way you are notified.

#### Configure the mail provide action - mandatory step if you intend send mail
This application rely on the Smtp protocol to send the mails, the implementation is done on the way to support the majority of the Use cases: Encrypt by TLS or SSL, authentication, etc... 
On internet there are thousand of provided that give you for free the opportunity to send mail, i will not advice any one of them, they are more or less all valid.
You have just to chose one, verify what is its integartion stategy and configure accrodly the configuration file 
```
configuration\action\mail.properties
```

#### Configure the popup action - not mandatory step
I will dedicate a litle by of time to discuss about the popup alert. 
Pratically it will generate a Dialog with the alert message and will emit a sound.
It can be configure to move the Dialog generated in foreground of your desktop in case you are working on another application, this is very usefull functionality becouse you can continue to do your work and then the popup will apper magically on top of your desktop, you cannot avoid to note it!
Also the sound can be configured, you can remove it or you can disable the loop.
Any specific configuration can be done alterating the file
```
configuration\action\popup.properties
```
 
 
## Screenshot

Create an alert
<img src="https://raw.githubusercontent.com/alesky78/CryptocurrencyAlerting/master/cryptocurrencyalerting/screenshot/create_alert.png">

Manage the alert created
<img src="https://raw.githubusercontent.com/alesky78/CryptocurrencyAlerting/master/cryptocurrencyalerting/screenshot/manage_alert.png">

Popup alert
<img src="https://raw.githubusercontent.com/alesky78/CryptocurrencyAlerting/master/cryptocurrencyalerting/screenshot/alert_popup.png">
	

## Start the application
the prerequirement is that the javaw command is accesible wherever on your pc.
then in Windows OS the bin folder of your JVM is part of the Path in the environment variables.
the generate folder contain in the root folder the script that must be execute to start the application.

```
startApplication.bat 
``` 

In case you want to start the application in adifferent way, analizing **startApplication.bat** will give you enought information to understand how is working the start of the application and then create a 
start more appropriate for your needs.
 

 
 

