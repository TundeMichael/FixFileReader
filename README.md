# JAXRS-FileReader
CSV File Reader, XML File Writer, JMS Queue, REST endpoint to retrieve subscriber's data via GET taking phone number as PathParam

 This is a pure Maven application, so it should open well in any IDE that supports Maven e.g NetBeans, Eclipse, InteliJ thou it was written in NetBeans 8.0.1 IDE.

 The application should work pretty fine in any Java EE 7 complaint application server Glassfish 4.x, WildFly 8.x etc but it was only tested with Glassfish 4.1 In any server of your choice, create the following JMS resources:

 JMS Queue: readerQueue JMS Queue Connection Factory: readerConFact

 Also change the file location for both the CSV file and the XML files in the classes CSVReader and XmlCreator to a location on your system

The REST endpoint can be accessed at http://localhost:8080/Reader/api/subscribers/{phoneNo} 
where {phoneNo} is a placeholder for the subscriber's phone number.

Please see the subscribers.csv for the subscribers used.
