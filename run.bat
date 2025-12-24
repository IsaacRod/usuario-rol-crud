@echo off 
echo Limpiando y compilando... 
.\mvnw.cmd clean compile -DskipTests 
echo. 
echo Ejecutando Spring Boot... 
.\mvnw.cmd spring-boot:run -DskipTests 
pause 
