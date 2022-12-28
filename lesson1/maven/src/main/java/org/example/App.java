package org.example;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public class App
{
    @SneakyThrows
    public static void main( String[] args ) {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(new Person("Jhon", 1)));
    }
}
