NOTAS

Day_01:
$ javac HelloWorld.java 
$ java HelloWorld 

$ native-image HelloWorld
$ ./helloworld 

$ graalpy
$ graalpy ParImpar.py 

Day_02:
$ java -version
$ sdk list java
$ sdk
$ sdk use java 22.3.r17-grl

$ javac PolyglotMain.java 
$ java PolyglotMain

$ native-image --language:js --language:python PolyglotMain
$ ./polyglotmain 

Day_03:

helloworld
$ mvn archetype:generate -DgroupId=com.example -DartifactId=helloworld -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
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

 appcli:
 
 graalpy --jvm --polyglot GraalPYDemoPolyglot2.py 