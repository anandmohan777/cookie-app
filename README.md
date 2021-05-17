## Overview
Command line program to process the log file and return the most active cookie for a specific day.

## Prerequisite dependencies
* Maven
* Java 8

## Building locally
First, clone the project to your local machine in any directory of your choice.
``` 
git clone https://github.com/anandmohan777/cookie-app.git
```

Then go inside the cloned directory and build the project using the commands below. It will produce the executable `.jar` file in the `/target` directory.
```
cd cookie-app/
mvn clean package
```

## Running the project
Run the command 
``` bash
java -jar target/cookie-app-1.0.0-SNAPSHOT.jar -f <path of csv>/cookie_log.csv -d <date>
```


Example
``` bash
java -jar target/cookie-app-1.0.0-SNAPSHOT.jar -f /Users/anand/quantcast/cookie_log.csv -d 2018-12-09
```
