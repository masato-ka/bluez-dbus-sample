bluez-sample
====

# Overview

## Description

 This application is sample of [bluez-dbus](https://github.com/hypfvieh/bluez-dbus).
 Very lazy code. Linux only.
 
 1. Linux
 2. Bluez 5.43
 3. libunixsocket-java

## Build

Build before please install libunixsocket-java

~~~
$ sudo apt-get install libunixsocket-java 
~~~

~~~~
$ git clone https://github.com/masato-ka/bluez-dbus-sample
$ cd bluez-dbus-sample
$ mkdir lib
$ cp /bin/lib/jni/libunix-java.so ./lib/ #Depending on library constraints.
$ mvn package -DskipTests=true
~~~~

And create jar file in target folder.

## Run the service.

~~~~
$ bluez-dbus-sample
$ java -jar target/bluz-sample-0.0.1-SNAPSHOT-jar-with-dependencies.jar
~~~~

When failed running, add sudo and try again.


## Licence

[MIT LICENCE](https://github.com/masato-ka/geo-hash-potate/blob/master/LICENSE.txt)


## Author

[masato-ka](https://twitter.com/masato_ka)

Last modify : Sep/17/2017