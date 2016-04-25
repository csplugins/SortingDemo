import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Sort {
    public static void main(String[] args) throws IOException {
        FileReader file = new FileReader("./sortFiles/reverseOrder.sort");
        BufferedReader br = new BufferedReader(file);
        int numElements = Integer.parseInt(br.readLine());
        int[] elements = new int[numElements];
        for (int i = 0; i < numElements; ++i) {
            elements[i] = Integer.parseInt(br.readLine());
        }
        Sorts s = new Sorts();
        //s.QuickSort(elements);
        //s.BubbleSort(elements);
        //s.InsertionSort(elements);
        //s.MergeSort(elements);
        s.RadixSort(elements);
        for (final int element : elements) System.out.print(element + " ");
        System.out.println("");
    }
}