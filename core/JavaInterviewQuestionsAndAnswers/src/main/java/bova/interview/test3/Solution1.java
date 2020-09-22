package bova.interview.test3;



public class Solution1 {
    public static void main(String[] args) {
        int[][] dots = {{1,2,4}, {2, 5, 6}, {21, 15, 66}, {3, 4, 6}, {3, 0, 0}, {0, 0, 0}};
        double min= 0;
        int mini = 0;
        int minj = 0;

        for(int i = 0; i < dots.length-1; i++) {
            for(int j = i+1; j < dots.length; j++) {
                int x = dots[i][0] - dots[j][0];
                int y = dots[i][1] - dots[j][1];
                int z = dots[i][2] - dots[j][2];
                double dist = Math.sqrt(x*x + y*y + z*z);
                if(i == 0) {
                    min = dist;
                    mini = i;
                    minj = j;
                } else {
                    if(min > dist) {
                        min = dist;
                        mini = i;
                        minj = j;
                    }
                }
            }
        }

        System.out.println("Min pair i = "+ mini + " j = "+ minj + " min dist = " + min);
    }
}
