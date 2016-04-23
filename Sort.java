//package sortingsolution;

import java.io.*;

public class Sort {
    public static void main(String[] args) throws FileNotFoundException, IOException{
        FileReader file = new FileReader("./sortFiles/reverseOrder.sort");
        BufferedReader br = new BufferedReader(file);
        int numElements = Integer.parseInt(br.readLine());
        int[] elements = new int[numElements];
        for(int i = 0; i < numElements; ++i){
            elements[i] = Integer.parseInt(br.readLine());
        }
        Sorts s = new Sorts();
        
        s.QuickSort(elements);
        for(int i = 0; i < elements.length; ++i){
            System.out.print(elements[i] + " ");
        }
        System.out.println("");
        s.BubbleSort(elements);
        s.InsertionSort(elements);
        s.MergetSort(elements);
    }
}