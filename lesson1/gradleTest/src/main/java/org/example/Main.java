package org.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {
    File file = new File("C:\\Services\\test.txt");
    FileOutputStream fileOutputStream = new FileOutputStream(file);
    fileOutputStream.write(87);
    fileOutputStream.flush();

  }
}