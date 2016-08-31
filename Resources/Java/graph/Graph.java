package graph;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Andi Gu
 */
public class Graph {
    public static void main(String[] args){
        /*
        int max = 10; // number of items.
        for (int i = 0; i < 1<<max; i++) {
            System.out.printf("%5s\n", Integer.toBinaryString(i));
        }*/
        int a = 10;
        int i = (1<<a)-1;
        System.out.println(i);
        for (int j = 0; j < a; j++) {
            System.out.println(Integer.toBinaryString(i>>j%2) + " " + ((i>>j)%2 == 1));
        }
    }
}