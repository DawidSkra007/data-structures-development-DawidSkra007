import java.util.Random;

public class Sorter {
    public static void main(String[] args) {
        Random random = new Random(20010);

        for (int n = 1000;n < 10000000;) {
            int[] arr = new int[n];
            for (int i = 0;i < n;++i) {
                arr[i] = random.nextInt();
            }
            final long startTime = System.currentTimeMillis();
            Sorter.selectionSort(arr);
            final long elapsedTime = System.currentTimeMillis() - startTime;
            System.out.println("" + n + "," + elapsedTime);
            n = (int) (n * 1.5);
        }

    }
    public static void selectionSort(int[] arr) {

        for (int i = 0; i < arr.length - 1;i++) {//traversing the array

            int min = i;
            for (int j = i + 1; j < arr.length;j++) {//finding smallest integer
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            //two nested for loops so -> O(n^2)
            int tmp = arr[min];//arr[min] = smallest int in array
            arr[min] = arr[i];
            arr[i] = tmp;//now smallest element is first
        }
    }

    public static void insertionSort(int[] arr) {

        for (int i = 1; i < arr.length; ++i) {//traversing the array
            int key = arr[i];//stores current element in 'key'
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {//while the element before key is bigger then change the position of that element
                arr[j + 1] = arr[j];
                j--;//this this will check every position before key
            }//One for loop and nested while loop so -> O(n^2)
            arr[j + 1] = key;//move the element up to make space
        }
    }

    public static void bubbleSort(int[] arr) {
        for (int i = 0;i < arr.length;i++) { // O(n)    traversing array
            for (int j = 0; j < arr.length - 1;j++) { // O(n^2) ->two nested arrays
                if (arr[j] > arr[j+1]) { //checks each element to current number, if its bigger then the elements are swapped
                    int temp = arr[j]; // swaps arr[j] arr[j + 1]
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    public static void bubbleSortRecursive(int[] arr, int size) {
        if (size == 1) { // base case
            return;
        }

        for (int i = 0; i < size - 1; i++) { // traversing array
            if (arr[i] > arr[i + 1]) {
                int temp = arr[i];  // swaps arr[i] arr[i + 1]
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }

        bubbleSortRecursive(arr,size - 1); // recursive call
    }

}
