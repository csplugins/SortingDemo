//package sortingsolution;

import java.io.IOException;

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
            //System.out.println(retVec.length + " " + k + " " + (k - i - 1));
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
    
    public void InsertionSort(int[] elements){
        
    }
    
    public void MergetSort(int[] elements){
        
    }
    
    public void BubbleSort(int[] elements){
        
    }
}

/*for(int z = 0; z < retVec.length; ++z){
            System.out.print(retVec[z] + " ");
        }
        System.out.println("");*/