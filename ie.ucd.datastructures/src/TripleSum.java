import java.util.Scanner;

public class TripleSum {
    public static void main(String[] args) {
        int[] arr = new int[100];
        int i = 0;
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()){ // reading from stdin
            if (scan.hasNextInt()){
                arr[i] = scan.nextInt();
                i++;
            }
        }
                            // Time complexity of the Triple Sum function is O(n^3)
                            // This is because it has three nested loops inside the function
        System.out.println(checkNums(arr));
    }


    public static int checkNums(int[] arr) {
        int counter = 0;

        for (int i = 0; i < (arr.length - 2); i++) { // O(n)
            for (int j = i + 1; j < (arr.length - 1); j++) { // O(n^2)
                for (int k = j + 1; k < arr.length; k++) { // O(n^3)
                    if (arr[i] + arr[j] + arr[k] == 0) {
                        counter++;
                    }
                }
            }
        }
        return counter;
    }

}
