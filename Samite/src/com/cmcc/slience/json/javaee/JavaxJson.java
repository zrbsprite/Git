package com.cmcc.slience.json.javaee;

import java.io.StringWriter;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;

import org.junit.Test;

public class JavaxJson {

	@Test
	public void testJson(){
		
		JsonObject obj = Json.createObjectBuilder().build();
		StringWriter w = new StringWriter();
        try (JsonWriter writer = Json.createWriter(w)) {
            writer.write(obj);
        }
        System.out.println(w.toString());
        
        obj = Json.createObjectBuilder()
                .add("apple", "red")
                .add("banana", "yellow")
                .build();
        w = new StringWriter();
        try (JsonWriter writer = Json.createWriter(w)) {
            writer.write(obj);
        }
        System.out.println(w.toString());
	}
}
