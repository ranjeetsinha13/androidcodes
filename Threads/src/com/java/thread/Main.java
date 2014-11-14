package com.java.thread;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
   public static void main(String[] argv) 
   throws Exception {
      String[] alpha = { "A", "E", "I", "O", "U" };
      List list = new ArrayList();
      Collections.shuffle(list);
      System.out.println("list");
   }   
}