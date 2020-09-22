package bova.hackerrank.array.arraymanipulation;

import java.io.*;
import java.util.Scanner;

public class Solution1 {

    // Complete the arrayManipulation function below.
    static long arrayManipulation(int n, int[][] queries) {
        long[] array = new long[n];
        long result = 0;

        for(int i = 0; i < queries.length; i++) {
            for(int j = queries[i][0] - 1; j < queries[i][1]; j++) {
                array[j] += queries[i][2];
            }
        }

        for(int i = 0; i < n; i++) {
            if(result < array[i]) {
                result = array[i];
            }
        }

        return result;
    }

    public static final String PATH = "/home/myk/proj.proj/core/JavaInterviewQuestionsAndAnswers/src/main/java/com/in28minutes/java/bova/hackerrank/array/arraymanipulation/";

    ///private static final Scanner scanner = new Scanner(System.in);
    private static Scanner scanner = null;

    static {
        try {
            scanner = new Scanner(new File(PATH + "input4.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ///BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(PATH +  "result4.txt"));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        int[][] queries = new int[m][3];

        for (int i = 0; i < m; i++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int queriesItem = Integer.parseInt(queriesRowItems[j]);
                queries[i][j] = queriesItem;
            }
        }

        long result = arrayManipulation(n, queries);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

