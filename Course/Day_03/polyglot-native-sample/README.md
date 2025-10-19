# polyglot-native-sample

Proyecto **Maven** con **GraalVM** para integración **políglota** (Java host + **Python** + **JavaScript**) y compilación a **imagen nativa**.

## Requisitos
- Instala un **JDK de GraalVM** (21/23/25). Exporta `JAVA_HOME` y añade `bin` al `PATH`.
- Maven 3.9+

## Estructura
```
pom.xml
src/main/java/com/example/PolyglotMain.java
```

## Comandos
```bash
# Compilar y ejecutar como JAR
mvn -DskipTests package
java -jar target/polyglot-native-sample-1.0.0.jar

# Construir imagen nativa
mvn -Pnative -DskipTests package
./target/polyglot-native-sample
```

> Si usas reflexión/bibliotecas dinámicas, activa el agente para generar configuraciones antes del build nativo:
```bash
mvn -Pnative -Dagent=true test
mvn -Pnative -DskipTests package
```
Los artefactos del agente quedan en `target/native/agent-output` y el plugin los reutiliza automáticamente.
