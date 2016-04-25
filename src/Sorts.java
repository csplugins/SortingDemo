import java.util.LinkedList;
import java.util.Queue;

public class Sorts {
    public int[] QuickSort(int[] elements) {
        if (elements.length < 2)
            return elements;
        if (elements.length == 2) {
            if (elements[0] > elements[1]) {
                int temp = elements[0];
                elements[0] = elements[1];
                elements[1] = temp;
            }
            return elements;
        }
        int a = elements[0], b = elements[(elements.length - 1) / 2], c = elements[elements.length - 1];
        int pivot = ((a - b) * (b - c) > -1 ? (elements.length - 1) / 2 : ((a - b) * (a - c) < 1 ? 0 : elements.length - 1));
        int temp = elements[pivot];
        elements[pivot] = elements[elements.length - 1];
        elements[elements.length - 1] = temp;
        pivot = elements.length - 1;
        int i = -1;
        int j = elements.length - 1;
        do {
            while (elements[++i] < elements[pivot]) ;
            while (i < j && elements[--j] > elements[pivot]) ;
            temp = elements[i];
            elements[i] = elements[j];
            elements[j] = temp;
        } while (i < j);
        temp = elements[pivot];
        elements[pivot] = elements[i];
        elements[i] = temp;

        int[] left = new int[i];
        int[] right = new int[elements.length - i - 1];
        System.arraycopy(elements, 0, left, 0, i);
        for (int k = i + 1; k < elements.length; ++k) {
            right[k - i - 1] = elements[k];
        }

        left = QuickSort(left);
        right = QuickSort(right);

        System.arraycopy(left, 0, elements, 0, left.length);
        for (int z = 0; z < right.length; z++) {
            elements[elements.length - 1 - z] = right[right.length - 1 - z];
        }

        return elements;
    }

    public int[] InsertionSort(int[] elements) {
        for (int i = 1; i < elements.length; i++) {
            int temp = elements[i];
            int placeToInsert = i;
            while (placeToInsert > 0 && elements[placeToInsert - 1] > temp) {
                elements[placeToInsert] = elements[placeToInsert - 1];
                --placeToInsert;
            }
            elements[placeToInsert] = temp;
        }
        return elements;
    }

    public int[] MergeSort(int[] elements) {
        if (elements.length < 2)
            return elements;
        if (elements.length == 2)
            if (elements[0] > elements[1]) {
                int temp = elements[0];
                elements[0] = elements[1];
                elements[1] = temp;
                return elements;
            }
        int halfPoint = elements.length / 2;
        int[] firstHalf = new int[halfPoint];
        int[] secondHalf = new int[elements.length - halfPoint];
        System.arraycopy(elements, 0, firstHalf, 0, firstHalf.length);
        System.arraycopy(elements, halfPoint, secondHalf, 0, secondHalf.length);
        firstHalf = MergeSort(firstHalf);
        secondHalf = MergeSort(secondHalf);
        int i = 0, j = 0, count = 0;
        while (i < firstHalf.length && j < secondHalf.length) {
            if (firstHalf[i] <= secondHalf[j]) {
                elements[count] = firstHalf[i];
                ++i;
            } else {
                elements[count] = secondHalf[j];
                ++j;
            }
            count++;
        }
        if (i < j)
            for (int k = i; k < firstHalf.length; k++) {
                elements[count] = firstHalf[k];
                count++;
            }
        else for (int k = j; k < secondHalf.length; k++) {
            elements[count] = secondHalf[k];
            count++;
        }
        return elements;
    }

    public int[] BubbleSort(int[] elements) {
        int n = elements.length;
        for (int i = 0; i < elements.length; ++i) {
            for (int j = 0; j < n - 1; ++j) {
                if (elements[j] > elements[j + 1]) {
                    int temp = elements[j];
                    elements[j] = elements[j + 1];
                    elements[j + 1] = temp;
                }
            }
            n--;
        }
        return elements;
    }

    public int[] RadixSort(int[] elements) {
        Queue<Integer> main_queue = new LinkedList<>();
        if (elements.length > 0) {
            int largest = elements[0];
            for (int element : elements) {
                main_queue.add(element);
                largest = largest < element ? element : largest;
            }
            int digits = 0;
            do {
                largest /= 10;
                ++digits;
            } while (largest != 0);
            @SuppressWarnings("unchecked")
            Queue<Integer>[] buckets = new Queue[10];
            for (int i = 0; i < 10; ++i) {
                buckets[i] = new LinkedList<>();
            }
            int den = 1;
            for (int i = 0; i < digits; ++i) {
                while (main_queue.size() > 0) {
                    int temp = main_queue.remove();
                    buckets[(temp / den) % 10].add(temp);
                }
                for (int j = 0; j < 10; ++j) {
                    while (buckets[j].size() > 0) {
                        main_queue.add(buckets[j].remove());
                    }
                }
                den *= 10;
            }
        }
        int i = 0;
        while (main_queue.size() > 0) {
            elements[i] = main_queue.remove();
            i++;
        }
        return elements;
    }
}