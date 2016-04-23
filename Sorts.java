//package sortingsolution;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Sorts {
    public int[] QuickSort(int[] elements){
        if(elements.length < 2)
            return elements;
        int[] retVec = elements;
        if(retVec.length == 2){
            if (retVec[0] > retVec[1]) {
			int temp = retVec[0];
			retVec[0] = retVec[1];
			retVec[1] = temp;
		}
		return retVec;
        }
        int a = retVec[0], b = retVec[(retVec.length-1) / 2], c = retVec[retVec.length-1];
	int pivot = ((a - b)*(b - c) > -1 ? (retVec.length-1)/2 : ((a - b)*(a - c) < 1 ? 0 : retVec.length-1));
        int temp = retVec[pivot];
	retVec[pivot] = retVec[retVec.length-1];
	retVec[retVec.length-1] = temp;
	pivot = retVec.length-1;
        int i = -1;
	int j = retVec.length - 1;
	do{
		while (retVec[++i] < retVec[pivot]);
		while (i < j && retVec[--j] > retVec[pivot]);
		temp = retVec[i];
		retVec[i] = retVec[j];
		retVec[j] = temp;
	} while (i < j);
        temp = retVec[pivot];
	retVec[pivot] = retVec[i];
	retVec[i] = temp;
        
        int[] left = new int[i];
        int[] right = new int[retVec.length - i - 1];
        for(int k = 0; k < i; ++k){
            left[k] = retVec[k];
        }
        for(int k = i+1; k < retVec.length; ++k){
            right[k-i-1] = retVec[k];
        }
        
        left = QuickSort(left);
        right = QuickSort(right);
        
        for (int z = 0; z < left.length; z++) {
            retVec[z] = left[z];
	}
	for (int z = 0; z < right.length; z++) {
            retVec[retVec.length-1-z] = right[right.length-1-z];
	}
        
        return retVec;
    }
    
    public int[] InsertionSort(int[] elements){
        for (int i = 1; i < elements.length; i++){
		int temp = elements[i];
		int placeToInsert = i;
		while (placeToInsert > 0 && elements[placeToInsert-1] > temp){
			elements[placeToInsert] = elements[placeToInsert - 1];
			--placeToInsert;
		}
		elements[placeToInsert] = temp;
	}
        return elements;
    }
    
    public int[] MergeSort(int[] elements){
        if (elements.length < 2)
		return elements;
        int[] retVec = elements;
        if(retVec.length == 2)
            if(retVec[0] > retVec[1]) {
                    int temp = retVec[0];
                    retVec[0] = retVec[1];
                    retVec[1] = temp;
                    return retVec;
            }
        int halfPoint = retVec.length / 2;
        int[] firstHalf = new int[halfPoint];
        int[] secondHalf = new int[retVec.length - halfPoint];
        for(int i = 0; i < firstHalf.length; ++i){
            firstHalf[i] = retVec[i];
        }
        for(int i = 0; i < secondHalf.length; ++i){
            secondHalf[i] = retVec[i+halfPoint];
        }
        firstHalf = MergeSort(firstHalf);
	secondHalf = MergeSort(secondHalf);
	int i = 0, j = 0, count = 0;
        while(i < firstHalf.length && j < secondHalf.length){
		if(firstHalf[i] <= secondHalf[j]){
			retVec[count] = firstHalf[i];
			++i;
		}
		else{
			retVec[count] = secondHalf[j];
			++j;
		}
		count++;
	}
        if (i < j)
		for (int k = i; k < firstHalf.length; k++){
			retVec[count] = firstHalf[k];
			count++;
		}
	else for (int k = j; k < secondHalf.length; k++) {
		retVec[count] = secondHalf[k];
		count++;
	}
        return retVec;
    }
    
    public int[] BubbleSort(int[] elements){
        int n = elements.length;
        for(int i = 0; i < elements.length; ++i){
            for(int j = 0; j < n-1; ++j){
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
    
    public int[] RadixSort(int[] elements){
        Queue<Integer> main_queue = new LinkedList();
        if(elements.length > 0){
            int largest = elements[0];
            for (int i = 0; i < elements.length; ++i) {
                main_queue.add(elements[i]);
                largest = largest < elements[i] ? elements[i] : largest;
            }
            int digits = 0;
            do{
                largest /= 10;
                ++digits;
            }while(largest != 0);
            Queue<Integer>[] buckets = new Queue[10];
            for(int i = 0; i < 10; ++i){
                buckets[i] = new LinkedList();
            }
            int den = 1;
            for (int i = 0; i < digits; ++i){
                while (main_queue.size() > 0){
                    int temp = main_queue.remove();
                    buckets[(temp / den) % 10].add(temp);
                }
                for (int j = 0; j < 10; ++j){
                    while (buckets[j].size() > 0){
                        main_queue.add(buckets[j].remove());
                    }
                }
                den *= 10;
            }
        }
        int[] ret = elements;
        int i = 0;
        while (main_queue.size() > 0) {
            ret[i] = main_queue.remove();
            i++;
	}
	return ret;
    }
}