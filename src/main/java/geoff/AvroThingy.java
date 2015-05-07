package geoff;

import org.apache.avro.Schema;
import org.apache.avro.SchemaValidationException;
import org.apache.avro.SchemaValidator;
import org.apache.avro.SchemaValidatorBuilder;

import java.util.ArrayList;
import java.util.List;

public class AvroThingy {
  public static Schema s0;
  public static Schema s1;
  public static Schema s2;
  public static SchemaValidator validator;

  static {
    s0 = Schema.parse("{"
                      + "\"type\": \"record\","
                      + "\"name\": \"r\","
                      + "\"fields\": [ {\"name\": \"f0\", \"type\": \"int\", \"default\": 1} ]"
                      + "}");
    
    s1 = Schema.parse("{"
                      + "\"type\": \"record\","
                      + "\"name\": \"r\","
                      + "\"fields\": [ {\"name\": \"f1\", \"type\": \"int\", \"default\": 1} ]"
                      + "}");
    
    s2 = Schema.parse("{"
                      + "\"type\": \"record\","
                      + "\"name\": \"r\","
                      + "\"fields\": [ {\"name\": \"f1\", \"type\": \"int\"} ]"
                      + "}");

    validator = new SchemaValidatorBuilder().canReadStrategy().validateLatest();
  }

  
  static void validate(Schema reader, Schema writer) {
    System.out.println("Validating that " + reader.toString() + "can read " + writer.toString());
    
    List<Schema> list = new ArrayList<Schema>();
    list.add(writer);
    try {
      validator.validate(reader, list);
    }
    catch (SchemaValidationException e) {
      System.out.println("Problem!");
      System.out.println(e.getMessage());
    }
  }
  
  public static void main(String[] args) {
    validate(s1, s0);
    validate(s2, s1);
    validate(s2, s0);
  }
}


