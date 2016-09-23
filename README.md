# avro-experiment

This example project exists to demonstrate one known edge case where incremental avro compatibility is not fully transitive. This breaking of compatibility is achieved by toggling "default" on and off in a field definition.

It introduces 3 schemas, s0, s1, s2 such that:
- s1 can be used to read s0
- s2 can be used to read s1
- s2 *cannot* be used to read s0

build: 
  
    mvn compile

run: 

    mvn exec::java
