package bova.hackerrank.array.nychaos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution1 {

    // Complete the minimumBribes function below.
    static void minimumBribes(int[] q) {
        int moves = 0;
        for(int i = 0;i< q.length;i++) {
            int delta = q[i] - (i + 1);
            if(delta > 2) {
                System.out.println("Too chaotic");
                return;
            }
            if(delta > 0) {
                moves += delta;
//                if(i != q.length - 1 && q[i] > q[i+1]) {
//                    int tmp = q[i];
//                    q[i] = q[i+1];
//                    q[i+1] = tmp;
//                }
            }
        }

        System.out.println(moves);
        return;
    }

    public static final String PATH = "/home/myk/proj.proj/core/JavaInterviewQuestionsAndAnswers/src/main/java/com/in28minutes/java/bova/hackerrank/array/nychaos/";

    ///private static final Scanner scanner = new Scanner(System.in);
    private static Scanner scanner = null;

    static {
        try {
            scanner = new Scanner(new File(PATH + "input1.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] q = new int[n];

            String[] qItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int qItem = Integer.parseInt(qItems[i]);
                q[i] = qItem;
            }

            minimumBribes(q);
        }

        scanner.close();
    }
}