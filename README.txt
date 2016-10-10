Maven2 (http://maven.apache.org) is used to download dependent JAR files, and to compile and run the examples.  Once downloaded and installed, the code from any chapter can be run by the command "mvn jetty:run" from the "app" directory.  This directory will usually be immediately underneath the main chapter directory (for Chapter 7, it will be one level lower, underneath the specific implementation directory).

For Chapter 9 and Chapter 10, additional artifacts need to be installed in your local Maven2 repository.  The instructions to download and install these are: 

Chapter 9:

1. Download the Rome JAR file http://georss.geonames.org/georss-rome-0.9.8.jar
2. Run the Maven2 command to install the artifact from the directory that the JAR file was downloaded to:

mvn install:install-file -DgroupId=rome -DartifactId=georss-rome -Dversion=0.9.8 -Dpackaging=jar -Dfile=georss-rome-0.9.8.jar


Chapter 10:

1. Download the JSON plug-in file from http://cwiki.apache.org/S2PLUGINS/json-plugin.html
2. Run the Maven2 command to install the artifact from the directory that the JAR file was downloaded to:

mvn install:install-file -DgroupId=jsonplugin -DartifactId=0.16 -Dversion=1.5 -Dpackaging=jar -Dfile=jsonplugin-0.16-1.5.jar

3. Download the GWT plug-in file from http://code.google.com/p/struts2gwtplugin/downloads/list
4. Run the Maven2 commands to install the artifact from the directory that the JAR file was downloaded (and expanded) to:

mvn install:install-file -DgroupId=com.googlcode.strut2gwtplugin -DartifactId=struts2gwtplugin -Dversion=0.2.1 -Dpackaging=jar -Dfile=struts2gwtplugin-0.2.1.jar

mvn install:install-file -DgroupId=gwt -DartifactId=servlet -Dversion=1.4.10 -Dpackaging=jar -Dfile=gwt-servlet.jar

mvn install:install-file -DgroupId=gwt -DartifactId=user -Dversion=1.4.10 -Dpackaging=jar -Dfile=gwt-user.jar

