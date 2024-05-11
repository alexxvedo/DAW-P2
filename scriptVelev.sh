#!/bin/bash

# Directorio donde se encuentran los archivos del proyecto
PROYECTO_DIR="/home/user/Desktop/tercero/daw/p2/tomcat/apache-tomcat-9.0.87/webapps/minitienda3"

# Directorio donde está instalado Tomcat
TOMCAT_DIR="/home/user/Desktop/tercero/daw/p2/tomcat/apache-tomcat-9.0.87"

# Compilar todos los servlets y helpers
javac -classpath "$TOMCAT_DIR/lib/servlet-api.jar" -d "$PROYECTO_DIR/WEB-INF/classes" "$PROYECTO_DIR/WEB-INF/classes/minitienda3/"*.java "$PROYECTO_DIR/WEB-INF/classes/minitienda3/helpers/"*.java "$PROYECTO_DIR/WEB-INF/classes/minitienda3/modelos/"*.java

# Detener Tomcat
cd "$TOMCAT_DIR/bin"
./shutdown.sh

# Reanudar Tomcat
./startup.sh