

## Welcome to CryptocurrencyAlerting
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


## Usage
It is requrested a minimal configuration before to start the application:
 - configure the provider for the api of the market criptocurrency prices (mandatory step)
 - configure the provide to send mail (not mandatory in case you want to use just the popup alert)

## Configuration of the market price adapter  - mandatory step
Obviusly the value of the cryptocurrencies must be obtained by a specific provider.
The application is based on interfaces, then it is possible to implement a specific adpter, for any provide that is able to expose the interface that give bach the price of the cryptocurrency,
and configure the application to use it.

In this Actual version the application is shipped wiht the follow adapter already implemented:
 - testAdapter --> used for test purpose in case you want to test or extend the application
 - coinmarketcap

### coinmarketcap api cofiguration
This provider has different pricing to use its api, but the basic usage is for free.
Aniway it is requested to login in the applicatiom https://pro.coinmarketcap.com/login/  and generate an API key to use its services.
After the API key is gerenate, it must be introduced in the configuration file 
```
configuration\marketprovider\coinmarketcap\CoinMarketCap.properties
```

## Configuration of the actions
as described before, actually there are the action already implemented
 - send a mail 
 - create a popup on the desktop and emit a sound
 
 this actions can be configured in the way to tailor the way you are notified.

### configure the mail provide action - mandatory step if you intend send mail
This application rely on the Smtp protocol to send the mails, the implementation is done on the way to support the majority of the Use cases: Encrypt by TLS or SSL, authentication, etc... 
On internet there are thousand of provided that give you for free the opportunity to send mail, i will not advice any one of them, they are more or less all valid.
You have just to chose one, verify what is its integartion stategy and configure accrodly the configuration file 
```
configuration\action\mail.properties
```

### configure the popup action - not mandatory step
I will give a litle by of space, to discuss about the popup alert. 
Pratically it will generate a Dialog with the alert message and will emit a sound.
It can be configure to move it in foreground in case you are working on another application, this is very usefull functionality becouse you can continue to do your work and then the popup will apper magically on top of your desktop! you cannot avoid to note it
Also the sound can be configured, you can remove it or you can disable the loop.
Any specific configuration can be find on the file
```
configuration\action\popup.properties
```
 

## Start the application
the prerequirement is that the javaw command is accesible wherever on your pc.
then in Windows OS the bin folder of your JVM is part of the Path in the environment variables

the generate folder contain in the root folder the script, it will take to start the application

```
startApplication.bat 
``` 
 

 
 

