# SOAP - Beginner - Producer

## A SOAP Tutorial for Beginners
This tutorial answers a simple question. I have a class written with Eclipse and I want to deploy it on Tomcat, how can I show it as a web service.

## Prerequisites
How much time do we spend writing a service?
With Eclipse, writing a SOAP service for Tomcat takes 5 minutes, without adding a jar to our project (in fact Eclipse will take care of it). In this tutorial we used:
- [Eclipse Java EE IDE for Web Developers](https://www.eclipse.org/downloads/packages/release/2018-09/r/eclipse-ide-java-ee-developers);
- [Tomcat v8.5](https://tomcat.apache.org/download-80.cgi);

Generally speaking, both the SOAP and REST services expose an interface, a kind of contract that describes the functionality of the server (called the service's producer) and to which the client (service consumer) relies to "consume" the service. This interface, known in SOAP as the Web Service Definition Language (WSDL) and Web Application Description Language (WADL) in REST, is in XML and describes the methods to access the services and types of data exchanged. 
Being in XML it is clearly cross-platform: just analyze the WSDL / WADL in order to write a producer or a service consumer.

## KISS: Keep It Simple, Stupid
Eclipse has an excellent code generator for SOAP services.
We immediately exclude the eventuality of writing the WSDL by hand and follow the so-called "bottom-up" approach: that is, we implement our class that does the things we want to transform into a service, like the following ones...


Note: 
File->New->Dynamic Web Project
It is reccomended to create a package named: it.things.ws - Your classes will stay under this package. Import the needed standard libraries.

``` java
public class MyService {
 
   public String echo(String message) {
      return "Echo " + message;
   }  
   public Output sort(Input input) {
      Arrays.sort(input.getVector());
      return new Output(input.getVector());
   }
}

public class Input {
   private int[] vector;
   public Input() {
   }
   public Input(int... numbers) {
      this.vector = numbers;
   } 
}
// Input contains only getters and setters

public class Output {
   private int[] sortedVector;
   public Output() {
   }
   public Output(int... numbers) {
      this.sortedVector = numbers;
   }
}

// Output contains only getters and setters. 
// This type of classes are called POJO

```

* Note: according to the Java Bean specification, in addition to getters & setters there must be at least one constructor without arguments.

## Instructions

- Right click on the class of our service MyService.java, then New-> Other ... and under Web Service folder select "Web Service"
- A configuration wizard will start: in the first screen: the service creation strategy ("Bottom-up").
- Put the horizontal lever on the left to the "Test Service" level.
- In this way the service will be generated, will be automatically deplored on Tomcat and will open a test page of the service that we will see at the end. Before continuing, also select "Monitor the Web Service" at the bottom to monitor SOAP messages from within Eclipse. Click Next.
- Select the name of the WSDL file that will be created and the methods of the class that you want to expose as a service.
For now leave them as they are. Click Next.
- The last two steps are very simple: you will be asked to start the Tomcat if it is not already (click Start Server and Next)
- Then click Next to launch the test application provided by Eclipse and Finish.
- At the end the browser will open showing a page containing the services.

## WebContent folder

- The new wsdl folder with the descriptor of our service appeared. 
- Inside WEB-INF instead we find another new folder and a service configuration file.
- We will check immediately from the browser page that has opened previously if everything works. 
- For example, by selecting the echo method and entering a string, the result returned by the server will be displayed immediately.
- Looking at the web.xml you can see Eclipse has added two new servlets: AxisServlet and AdminServlet with different types of mapping: they will receive the calls and translate them for our service in Java. Axis is nothing more than an open source implementation of the SOAP protocol for Java: every time you deal with Axis, you can discover all the services deplored on the server by simply recalling a URI like this:

http://localhost:8080/WebServiceProducerTest/services which will show the URI of the WSDL, like the one just created.
http://localhost:12652/Producer/services/MyService which will show the WSDL of the service.

## Testing your Service

1. Download SOAPUI[https://www.soapui.org/]
2. Open SoapUI -> Create Empty Project 
3. Right click on project -> Add new WSDL
4. Paste WSDL link -> http://localhost:12652/Producer/services/MyService?wsdl
5. Click Next. From the Navigator select MyServiceSoapBinding/Echo/Request1 double-click
6.  Add message then click Play (Green Button).
7. You will see the response.
