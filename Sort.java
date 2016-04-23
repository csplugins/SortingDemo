//package sortingsolution;

import java.io.*;

public class Sort {
    public static void main(String[] args) throws FileNotFoundException, IOException{
        FileReader file = new FileReader("./sortFiles/everyOther.sort");
        BufferedReader br = new BufferedReader(file);
        int numElements = Integer.parseInt(br.readLine());
        int[] elements = new int[numElements];
        for(int i = 0; i < numElements; ++i){
            elements[i] = Integer.parseInt(br.readLine());
        }
        Sorts s = new Sorts();
        
        s.QuickSort(elements);
        s.BubbleSort(elements);
        s.InsertionSort(elements);
        s.MergetSort(elements);
    }
}