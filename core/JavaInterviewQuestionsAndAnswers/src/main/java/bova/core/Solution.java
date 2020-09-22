package bova.core;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Solution {

    // Complete the minimumSwaps function below.
    static int minimumSwaps(int[] array) {
        int n = array.length - 1;
        int minSwaps = 0;

        printArray(array);

        for (int i = 0; i < n; i++) {

            System.out.println("==========");
            System.out.println("i="+i);
            System.out.println("array[i] - 1="+(array[i] - 1));

            if (i < array[i] - 1) {
                System.out.println("*** i < array[i] - 1");
                System.out.println("n="+n);

                swap(array, i, Math.min(n, array[i] - 1));
                printArray(array);

                minSwaps++;
                i--;

                System.out.println("minSwaps="+minSwaps);
                System.out.println("i="+i);
            }
        }
        return minSwaps;
    }

    private static void printArray(int[] array) {
        for(int i=0;i<array.length;i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("");
        System.out.println("----------");
        for(int i=0;i<array.length;i++) {
            System.out.print(i + " ");
        }
        System.out.println("");
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("1.txt"));

//        int n = scanner.nextInt();
//        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

//        int[] arr = new int[n];
//
//        String[] arrItems = scanner.nextLine().split(" ");
//        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
//
//        for (int i = 0; i < n; i++) {
//            int arrItem = Integer.parseInt(arrItems[i]);
//            arr[i] = arrItem;
//        }

        int[] arr = {4,3,1, 2};

        int res = minimumSwaps(arr);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
