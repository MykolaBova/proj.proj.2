package bova.codility;

class Solution {
    public int solution(String S) {
        // write your code in Java SE 8
        int counter = 0;

        int decimal=Integer.parseInt(S,2);

        while (decimal!=0) {
            if(decimal%2 == 0) {
                decimal = decimal / 2;
            } else {
                decimal--;
            }
            counter++;
        }

        return counter;
    }
}