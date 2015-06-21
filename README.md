# FST - JIT
#### Just-in-time compiling of Finite State Transducers
###### Experimental - not functional yet

Engineering school final year project.

The idea is to build a FST not as a data structure, but as java bytecode behaving as a "bad student". Each input will be
associated to an output using a huge lookupswitch.

#### Build instructions
To build the project, go into the java/ directory and run :
```
mvn package
```

#### Execution instructions
A fst-jit-${version}-main.jar file has been created.
To build a FST with the default dictionary, go into the java/target/ directory (where the jar is) and run :
```
head ../../dictionary | java -jar fst-jit-${version}-main.jar
```

The following options are available : 
```
 -g,--graph <arg>        Prints the resulting FST in graphviz format (.dot). Specify
                         destination file name in argument
 -j,--java-class <arg>   Compiles the generated FST to a Java file.
                         Specify the class name in argument
 -b,--bytecode <arg>     Compiles the generated FST to bytecode and loads
                         it. Specify the class name in argument
```

