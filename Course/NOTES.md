NOTAS

Day_01:
$ javac HelloWorld.java 
$ java HelloWorld 

$ native-image HelloWorld
$ ./helloworld 

$ graalpy
$ graalpy ParImpar.py 

$ export LLVM_TOOLCHAIN=$(lli --print-toolchain-path)
$ $LLVM_TOOLCHAIN/clang hello.c -lgraalvm-llvm -o hello
$ lli hello

Day_02:
$ java -version
$ sdk list java
$ sdk
$ sdk use java 22.3.r17-grl

$ javac PolyglotMain.java 
$ java PolyglotMain

$ native-image --language:js --language:python PolyglotMain
$ ./polyglotmain 

$ $LLVM_TOOLCHAIN/clang polyglotpython.c -lgraalvm-llvm -o polyglotpython
$ lli --polyglot polyglotpython

$ $LLVM_TOOLCHAIN/clang polyglotjs.c -lgraalvm-llvm -o polyglotjs
$ lli --polyglot polyglotjs

Day_03:

helloworld
$ mvn archetype:generate -DgroupId=com.example -DartifactId=helloworld -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
$ java -cp target/helloworld-1.0-SNAPSHOT.jar com.example.App
$ mvn -Pnative package

pom.xml
<profiles>
   <profile>
     <id>native</id>
     <build>
       <plugins>
         <plugin>
           <groupId>org.graalvm.buildtools</groupId>
           <artifactId>native-maven-plugin</artifactId>
           <extensions>true</extensions>
           <executions>
             <execution>
             <id>build-native</id>
               <goals>
                 <goal>compile-no-fork</goal>
               </goals>
               <phase>package</phase>
             </execution>
           </executions>
         </plugin>
       </plugins>
     </build>
   </profile>
 </profiles>

mvn archetype:generate -DarchetypeGroupId=org.apache.maven.archetypes -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.5 -DgroupId=example -DartifactId=javase -Dpackage=org.example -Dversion=1.0-SNAPSHOT -DinteractiveMode=false

Day_04:

$ sdk install micronaut
$ mn --version

spring

./mvnw test
./mvnw spring-boot:run

micronaut

./mvnw test
./mvnw mn:run

sudo snap install postman
postman &

http://localhost:8080/api/usuarios
http://localhost:8080/analyze?text=happy

{
  "id": "D09010313",
  "nombre": "Antonio Romero",
  "email": "antonio.romero@example.com"
}

