import java.util.*;

public class SortingAlgorithms {

	
//////////////////////// INSERTION SORT ////////////////////////
	
	public static void insertionSort (int[] l, List<Long> stats) {
		
		int key, i;
		long moves = 0, compares = 0;
		System.out.println("Input array: " + Arrays.toString(l));
		
		for (int j = 1; j < l.length; j++) {
			System.out.println("Array: " + Arrays.toString(l));
			System.out.println("Step number: " + j);
			key = l[j];   //bierzemy element ze zbioru nie posortowanego
			System.out.println("The key is now: " + key);
			i = j - 1;
			
			compares++;
			while (i >= 0 && l[i] > key) {  //por�wnujemy go z elementami zbioru posortowanego
				System.out.println(l[i] + " > " + key);
				l[i+1] = l[i];
				moves++;
				System.out.println("Moving " + l[i]);
				i--;
				compares++;
			}
			l[i+1] = key;    //wyci�gni�ty element wstawiamy na miejsce gdzie sko�czyli�my por�wnywa� 
			moves++;
		}
		stats.set(0, stats.get(0) + compares);
		stats.set(1, stats.get(1) + moves);
		System.out.println("List is sorted with " + compares + " compares and " + moves + " moves");
	}
	
public static void insertionSort2 (int[] list, int left, int right, List<Long> stats) {
		
		int key, i;
		long moves = 0, compares = 0;
		
		for (int j = left + 1; j <= right; j++) {
			System.out.println("Step number: " + j);
			key = list[j];
			System.out.println("The key is now: " + key);
			i = j - 1;
			
			compares++;
			while (i >= left && list[i] > key) {
				System.out.println(list[i] + " > " + key);
				list[i+1] = list[i];
				moves++;
				System.out.println("Moving " + list[i]);
				i--;
				compares++;
			}
			list[i+1] = key;
			moves++;
		}
		stats.set(0, stats.get(0) + compares);
		stats.set(1, stats.get(1) + moves);
		System.out.println("List is sorted with " + compares + " compares and " + moves + " moves");
	}
	
	
	
//////////////////////// END OF INSERTION SORT ////////////////////////
	
//////////////////////// MERGE SORT ////////////////////////
	
	public static int[] mergeSort (int[] list, List<Long> stats) {
		
		if (list.length == 1) {
			System.out.println("List with 1 element");
			return list;
		}
		//dzielimy tablice na mniejsze
		System.out.println("List  with " + list.length + " elements, splitted to list with " + (list.length / 2) + " elements and list with " + (list.length - (list.length / 2)) + " elements");
		
		int[] first = new int[list.length / 2];
		int[] second = new int[list.length - first.length];
		
		System.arraycopy(list, 0, first, 0, first.length);
        System.arraycopy(list, first.length, second, 0, second.length);
		
		mergeSort(first, stats);
		mergeSort(second, stats);
		//scalamy mniejsze posortowane tablice w jedn� posortowan� 
		merge(first, second, list, stats);
		return list;
	}
	
	public static void merge(int[] first, int[] second, int[] result, List<Long> stats) {
		
		int i = 0, j = 0, k = 0;
		int compares = 0, moves = 0;

		System.out.println("First array " + Arrays.toString(first));
		System.out.println("Second array " + Arrays.toString(second));
		
		compares++;
		while (i < first.length && j < second.length) {
			
			System.out.println("Comparing " + first[i] + " with " + second[j]);
			compares++;
			if (first[i] < second[j]) {
				System.out.println(first[i] + " < " + second[j]);
				result[k] = first[i];
				i++;
				moves++;
			}
			else  {
				System.out.println(first[i] + " >= " + second[j]);
				result[k] = second[j];
				j++;
				moves++;
			}
			k++;
		}
		stats.set(0, stats.get(0) + compares);
		stats.set(1, stats.get(1) + moves);
		System.out.println("Number of comparisions " + compares + " number of moves " + moves);
		// Because only 1 array is copied fully in while loop, so I have to copy 2nd array at the end of result
		
		System.arraycopy(first, i, result, k, first.length - i);
        System.arraycopy(second, j, result, k, second.length - j);
        System.out.println("After merge " + Arrays.toString(result));
        System.out.println("");
	}
	
//////////////////////// END OF MERGE SORT ////////////////////////	
	
	
//////////////////////// QUICK SORT ////////////////////////
	
	
	public static void swap (int[] list, int i, int j) {
		int tmp = (int) list[i];
		list[i] =  list[j];
		list[j] = tmp;
	}
	
	public static void quickSort (int[] list, List<Long> stats) {
		quickSort(list, 0, list.length - 1, stats);
	}
	
	public static void quickSort (int[] list, int left, int right, List<Long> stats) {
		if (right > left) {
			
			System.out.println("Array before partition");
			System.out.println(Arrays.toString(list));
			int p = partition(list, left, right, stats);
			System.out.println("Pivot's index: " + p);
			
			System.out.println("Array after partition");
			System.out.println(Arrays.toString(list));
			System.out.println("");
			
			
			quickSort (list, left, p - 1 , stats);
			quickSort (list, p + 1, right, stats);
		}
	}
	
	public static int partition (int[] list, int left, int right, List<Long> stats) {
		
		int middle = left + (right - left) / 2;
		int pivot = list[middle];//ustawiamy pivota na �rodkow� liczb� 
		int compares = 0, swaps = 0;
		
		System.out.println("Pivot: " + pivot);
		
		swap(list, left, middle);
		swaps++;
		int i = left;
		for (int j = left + 1; j <= right; j++) {
			
			System.out.println("Comparing " + list[j] + " with pivot " + pivot);
			compares++;
			if (list[j] <= pivot) { //je�li elementy s� wi�ksze lub r�wne od pivota
				System.out.println(list[j] + " < " + pivot);
				i++;
				System.out.println("Swapping " + list[j] + " with " + list[i]);
				swap(list, i, j);//zamieniam 
				swaps++;
			}
		}
		
		System.out.println("End of partition, moving pivot to " + i + " index");
		swap(list, i, left);//wstaw element podzia�u we w�a�ciwe miejsce
		swaps++;
		stats.set(0, stats.get(0) + compares);
		stats.set(1, stats.get(1) + swaps);
		
		return i;
	}
	
//////////////////////// END OF QUICK SORT ////////////////////////
	
/////////////////////// MY SORT ALGORITHMS ///////////////////////
	
	public static void myQuickSort (int[] list, List<Long> stats) {
		myQuickSort(list, 0, list.length - 1, stats);
	}
	
	public static void myQuickSort (int[] list, int left, int right, List<Long> stats) {
		
		if (right - left < 8) {
			insertionSort2(list, left, right, stats);
		}
		else {
					
			int p = partition(list, left, right, stats);
			
			myQuickSort (list, left, p - 1 , stats);
			myQuickSort (list, p + 1, right, stats);
		}
	}
	
	
	public static int[] myMergeSort (int[] l, List<Long> stats) {
		if (l.length < 7) {
			insertionSort(l, stats);
		}
		else {

			int[] first = new int[l.length / 2];
			int[] second = new int[l.length - first.length];
			
			System.arraycopy(l, 0, first, 0, first.length);
	        System.arraycopy(l, first.length, second, 0, second.length);
			
	        myMergeSort(first, stats);
	        myMergeSort(second, stats);
			
			merge(first, second, l, stats);
		}
		return l;
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int option = Integer.parseInt(args[0]);
		//int size = Integer.parseInt(args[1]);
		Random rn = new Random();
		int random;
		
		
		List<Long> stats = new ArrayList<Long>();
		List<Long> stats2 = new ArrayList<Long>();
		List<Long> stats3 = new ArrayList<Long>();
		List<Long> stats4 = new ArrayList<Long>();
		List<Long> stats5 = new ArrayList<Long>();
		List<Long> mergeStats = new ArrayList<Long>();
		List<Long> quickStats = new ArrayList<Long>();
		List<Long> inStats = new ArrayList<Long>();
		List<Long> myQuickStats = new ArrayList<Long>();
		List<Long> myMergeStats = new ArrayList<Long>();
		stats.add((long)0);
		stats.add((long)0);	
		stats2.add((long)0);
		stats2.add((long) 0);
		stats3.add((long)0);
		stats3.add((long) 0);
		stats4.add((long) 0);
		stats4.add((long) 0);
		stats5.add((long) 0);
		stats5.add((long) 0);
		
		
//////// STANDARD RUN	/////////////	
		for(int size=100; size<=100000; size=size+100) {
			int[] mSort = new int[size];
			int[] qSort = new int[size];
			int[] inSort = new int[size];
			int[] myQuick = new int[size];
			int[] myMerge = new int[size];
			int repeat = 1;

			for (int l = 0; l < repeat; l++) {
				if (option == 0) {
					for (int i = 0; i < size; i++) {
						mSort[i] = i;
						qSort[i] = i;
						inSort[i] = i;
						myQuick[i] = i;
						myMerge[i] = i;
					}
				} else if (option == 1) {
					for (int i = 0; i < size; i++) {
						random = rn.nextInt(100000);
						mSort[i] = random;
						qSort[i] = random;
						inSort[i] = random;
						myQuick[i] = random;
						myMerge[i] = random;
					}
				} else {
					for (int i = 0; i < size; i++) {
						mSort[i] = size - i;
						qSort[i] = size - i;
						inSort[i] = size - i;
						myQuick[i] = size - i;
						myMerge[i] = size - i;

					}
				}
//			System.out.println("MERGE SORT:");
//			System.out.println(Arrays.toString(mSort));
//			mergeSort(mSort, stats);
//			System.out.println(Arrays.toString(mSort));
//			System.out.println(" ");

//			System.out.println("QUICK SORT:");
//			System.out.println(Arrays.toString(qSort));
				quickSort(qSort, stats2);
//			System.out.println(Arrays.toString(qSort));
//			System.out.println(" ");

//			System.out.println("INSERTION SORT:");
//			System.out.println(Arrays.toString(inSort));
//			insertionSort(inSort, stats3);
//			System.out.println("compares: " + stats3.get(0) + " moves: " + stats3.get(1));
//			System.out.println(Arrays.toString(inSort));
//			System.out.println(" ");

//			System.out.println("MY MERGE SORT:");
//			System.out.println(Arrays.toString(myMerge));
//			myMergeSort(myMerge, stats4);
//			System.out.println(Arrays.toString(myMerge));
//			System.out.println(" ");

//			System.out.println("MY QUICK SORT:");
//			System.out.println(Arrays.toString(myQuick));
//			myQuickSort(myQuick, stats5);
//			System.out.println(Arrays.toString(myQuick));
//			System.out.println(" ");

			}
		}
//		System.out.println("compares: " + stats.get(0)/repeat + " moves: " + stats.get(1)/repeat);
//		
		System.out.println(stats2.get(0)/repeat);
		//System.out.println("compares: " + stats2.get(0)/repeat + " moves: " + stats2.get(1)/repeat);
//		
//		
//		System.out.println("compares: " + stats4.get(0)/repeat + " moves: " + stats4.get(1)/repeat);
//		
//		
//		System.out.println("compares: " + stats5.get(0)/repeat + " moves: " + stats5.get(1)/repeat);

		
/////////// STATISTICS //////////////////
//		for (int l = 0; l < 10; l++) {
//			for (int n = 100000; n <= 100000; n = n + 10000) {
//				int[] mSort = new int[n];
//				int[] qSort = new int[n];
//				int[] inSort = new int[n];
//				int[] myQuick = new int[n];
//				int[] myMerge = new int[n];
//				stats.set(0, (long)0);
//				stats.set(1, (long)0);
//				stats2.set(0, (long)0);
//				stats2.set(1, (long)0);
//				stats3.set(0, (long)0);
//				stats3.set(1, (long)0);
//				stats4.set(0, (long)0);
//				stats4.set(1, (long)0);
//				stats5.set(0, (long)0);
//				stats5.set(1, (long)0);
//
//				for (int i = 0; i < n; i++) {
//					if (option == 1) {
//						random = rn.nextInt(n);
//						mSort[i] = random;
//						qSort[i] = random;
//						//inSort[i] = random;
//						myQuick[i] = random;
//						myMerge[i] = random;
//					}
//					else {
//						mSort[i] = n - i;
//						qSort[i] = n - i;
//						//inSort[i] = n - i;
//						myQuick[i] = n - i;
//						myMerge[i] = n - i;
//					}
//				}
//				mSort = mergeSort(mSort, stats);
//				quickSort(qSort, stats2);
//			//	insertionSort(inSort, stats3);
//				myQuickSort(myQuick, stats4);
//				myMerge = myMergeSort(myMerge, stats5);
//				
//				if (l == 0) {
//					mergeStats.add(stats.get(0));
//					mergeStats.add(stats.get(1));
//					
//					quickStats.add(stats2.get(0));
//					quickStats.add(stats2.get(1));
//					
////					inStats.add(stats3.get(0));
////					inStats.add(stats3.get(1));
//					
//					myQuickStats.add(stats4.get(0));
//					myQuickStats.add(stats4.get(1));
//					
//					myMergeStats.add(stats5.get(0));
//					myMergeStats.add(stats5.get(1));
//				}
//				else {
//					mergeStats.set(0, (mergeStats.get(0) + stats.get(0)) / 2);
//					mergeStats.set(1, (mergeStats.get(1) + stats.get(1)) / 2);
//					
//					quickStats.set(0, (quickStats.get(0) + stats2.get(0)) / 2);
//					quickStats.set(1, (quickStats.get(1) + stats2.get(1)) / 2);
//					
////					inStats.set(0, (inStats.get(0) + stats3.get(0)) / 2);
////					inStats.set(1, (inStats.get(1) + stats3.get(1)) / 2) ;
////					
//					myQuickStats.set(0, (myQuickStats.get(0) + stats4.get(0)) / 2);
//					myQuickStats.set(1, (myQuickStats.get(1) + stats4.get(1)) / 2);
//					
//					myMergeStats.set(0, (myMergeStats.get(0) + stats5.get(0)) / 2);
//					myMergeStats.set(1, (myMergeStats.get(1) + stats5.get(1)) / 2);
//				}
//			}
//				
//			
//			
//		}
//		System.out.println("");
//		System.out.println("MERGE SORT STATISTICS:");
//		for (int k = 0; k < 2; k = k + 2) {
//			System.out.println("Compares: " + mergeStats.get(k) + " moves: " + mergeStats.get(k+1));
//		}
//		
//		System.out.println("");
//		System.out.println("QUICK SORT STATISTICS:");
//		for (int k = 0; k < 2; k = k + 2) {
//			System.out.println("Compares: " + quickStats.get(k) + " moves: " + quickStats.get(k+1));
//		}
//		
////		System.out.println("");
////		System.out.println("INSERTION SORT STATISTICS:");
////		for (int k = 0; k < 2; k = k + 2) {
////			System.out.println("Compares: " + inStats.get(k) + " moves: " + inStats.get(k+1));
////		}
//		
//		System.out.println("");
//		System.out.println("Faster Merge SORT STATISTICS:");
//		for (int k = 0; k < 2; k = k + 2) {
//			System.out.println("Compares: " + myMergeStats.get(k) + " moves: " + myMergeStats.get(k+1));
//		}
//		
//		System.out.println("");
//		System.out.println("Faster QUICK SORT STATISTICS:");
//		for (int k = 0; k < 2; k = k + 2) {
//			System.out.println("Compares: " + myQuickStats.get(k) + " moves: " + myQuickStats.get(k+1));
//		}
		
		
	}
		
		
		
		

}
//}

