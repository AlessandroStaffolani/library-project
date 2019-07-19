# Library Project
Library Project is a JEE example application that uses: EJB 3.x, JPA with Hibernate for the persistence and provides some RESTFul API for the management of the entities of a library.

## Configuration
### Data source wildfly
It's necessary to add a datasource inside Wildfly server, the file where to add the datasource is `<wildfly_root>/standalone/configuration/standalone.xml` and It's necessary to add the following content:

```xml
...
<subsystem xmlns="urn:jboss:domain:datasources:5.0">
    <datasources>
        <datasource jta="true" jndi-name="java:/jdbc/mySqlMampDS" pool-name="mySqlMampDS" enabled="true" use-java-context="true" use-ccm="true">
			<connection-url>jdbc:mysql://localhost:8889/library_project?serverTimezone=UTC</connection-url>
			<driver>mysql</driver>
			<security>
				<user-name>root</user-name>
				<password>root</password>
			</security>
		</datasource>
        <drivers>
            <driver name="mysql" module="com.mysql">
                <driver-class>com.mysql.cj.jdbc.Driver</driver-class>
				<xa-datasource-class>com.mysql.cj.jdbc.MysqlXADataSource</xa-datasource-class>
            </driver>
        </drivers>
    </datasources>
</subsystem>
...
```
Moreover, It's necessary to add mysql driver, to do that, create a folder in: `<wildfly_roo>/modules/system/layers/base/com`. The folder name should be `mysql/main` inside the folder you shold put the jar of the driver mysql and the file `module.xml` with the following content:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<module name="com.mysql" xmlns="urn:jboss:module:1.5">

    <resources>
        <resource-root path="mysql-connector-java-8.0.16.jar"/>
    </resources>
    <dependencies>
        <module name="javax.api"/>
        <module name="javax.transaction.api"/>
        <module name="javax.servlet.api" optional="true"/>
    </dependencies>
</module>
```
### Persistence file 
The file `persistence.xml` should be located in `META-INF` of resources (eg. `<root>/src/main/resources/META-INF/persistence.xml`) with the following content:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns="http://java.sun.com/xml/ns/persistence"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd"
	version="2.0">
	<persistence-unit name="LibraryProjectPU">
		<jta-data-source>java:/jdbc/mySqlMampDS</jta-data-source>
		<properties>
			<property name="hibernate.dialect"
				value="org.hibernate.dialect.MySQL5InnoDBDialect" />
			<property name="hibernate.show_sql" value="true" />
			<property name="hibernate.hbm2ddl.auto" value="update" />
			<property name="hibernate.generate_statistics" value="true"/>
		</properties>
	</persistence-unit>
</persistence>

```

Note: `<jta-data-source>java:/jdbc/mySqlMampDS</jta-data-source>` has the same value setted for the datasource

### API RESTFul
The RESTFul API should be configurated adding a file `web.xml` inside `WEB-INF` (eg. `<root>/src/main/webapp/WEB-INF/persistence.xml`) with the following content:

```xml
<!-- JBoss, Home of Professional Open Source Copyright 2015, Red Hat, Inc. 
	and/or its affiliates, and individual contributors by the @authors tag. See 
	the copyright.txt in the distribution for a full listing of individual contributors. 
	Licensed under the Apache License, Version 2.0 (the "License"); you may not 
	use this file except in compliance with the License. You may obtain a copy 
	of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required 
	by applicable law or agreed to in writing, software distributed under the 
	License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS 
	OF ANY KIND, either express or implied. See the License for the specific 
	language governing permissions and limitations under the License. -->
<web-app version="3.1"
	xmlns="http://xmlns.jcp.org/xml/ns/javaee"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd">
	<display-name>Restful Library Project</display-name>

	<!-- Auto scan REST service -->
	<context-param>
		<param-name>resteasy.scan</param-name>
		<param-value>true</param-value>
	</context-param>

	<!-- this need same with resteasy servlet url-pattern -->
	<context-param>
		<param-name>resteasy.servlet.mapping.prefix</param-name>
		<param-value>/api</param-value>
	</context-param>

	<listener>
		<listener-class>
			org.jboss.resteasy.plugins.server.servlet.ResteasyBootstrap</listener-class>
	</listener>

	<servlet>
		<servlet-name>resteasy-servlet</servlet-name>
		<servlet-class>
			org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher</servlet-class>
	</servlet>

	<servlet-mapping>
		<servlet-name>resteasy-servlet</servlet-name>
		<url-pattern>/api/*</url-pattern>
	</servlet-mapping>
</web-app>
```