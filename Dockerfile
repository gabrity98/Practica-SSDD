#################################################
# Imagen base para el contenedor de la aplicación
#################################################
FROM openjdk:21
# Define el directorio de trabajo donde se encuentra el JAR
WORKDIR /usr/app/
# Copia el JAR del contenedor de compilación
COPY --from=builder /project/target/*.jar /usr/app/
# Indica el puerto que expone el contenedor
EXPOSE 8080
# Comando que se ejecuta al hacer docker run
CMD [ "java", "-jar", "app.jar" ]