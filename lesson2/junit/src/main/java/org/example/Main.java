package org.example;

public class Main {
  public static void main(String[] args) {
    MyHashMap<Integer, String> map = new MyHashMap<>();
    System.out.println(map.size());
    map.put(1, "Kyiv");
    map.put(2, "Warsaw");
    map.put(3, "London");
    map.put(4, "Madrid");
    map.put(5, "Paris");
    map.put(6, "Rome");
    map.put(7, "Berlin");
    map.put(8, "Prague");
    map.put(9, "Oslo");
    map.put(10, "Helsinki");
    map.put(11, "Lisbon");
    map.put(12, "Tokyo");
    map.put(13, "Dublin");
    map.put(14, "Tbilisi");
    map.put(15, "Sofia");
    map.put(16, "Amsterdam");
    System.out.println(map);
    map.remove(12);
    System.out.println(map);
    System.out.println(map.get(12));
  }
}