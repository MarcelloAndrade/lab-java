package com.java.hashing;

import java.util.HashMap;
import java.util.Hashtable;

class TestHashtable {
    public static void main(String args[]) {
        Hashtable<Integer, String> hm = new Hashtable<Integer, String>();
        hm.put(1, "B");
        hm.put(12, "C");
        hm.put(15, "A");
        hm.put(3, "D");

        System.out.println(hm);
        System.out.println(hm.contains(1));
        System.out.println(hm.contains("B"));
        System.out.println(hm.containsKey(12));
    }

}