package bova.codility;

class Solution1 {

    public int solution(int[] A) {

        // write your code in Java SE 8
        int intervalsCount = 0;
        int tempIntervalsCount = 0;
        int oldIntervalsCount = 0;
        int[] delta = new int[ A.length];
        delta[0] = -1;
        int tempSuccessivelyEqualIntervalsCount = 0;

        for(int i = 0, j = i + 1; i < A.length-2; i++, j++) {

            delta[j] =  A[i+1] - A[i];
            delta[j+1] =  A[i+2] - A[i+1];

            for(;i < A.length-2 && delta[j] == delta[j+1];) {
                tempSuccessivelyEqualIntervalsCount++;
                tempIntervalsCount = calculateSuccessivelyIntervalsCount(tempSuccessivelyEqualIntervalsCount);

                if( // changed intervals
                        j > 1 &&
                        delta[j-1] != delta[j]
                ) {
                    oldIntervalsCount = intervalsCount + tempIntervalsCount;
                    tempIntervalsCount = 0;
                    break;
                }

                if(j < A.length-2) {
                    i++;
                    j++;
                    delta[j] =  A[i+1] - A[i];
                    delta[j+1] =  A[i+2] - A[i+1];
                }
                else {
                    break;
                }
            }

            tempSuccessivelyEqualIntervalsCount = 0;
            if(i <= A.length-2) { // last check
                intervalsCount = oldIntervalsCount + tempIntervalsCount;
            }
        }

        if(intervalsCount == 0) { // only one successively interval
            intervalsCount = oldIntervalsCount;
        }

        if(intervalsCount > 1_000_000_000) {
            intervalsCount = -1;
        }

        return intervalsCount;
    }

    // Iteration 1
    private int calculateSuccessivelyIntervalsCount(int successivelyEqualIntervalsCount) {
        int count = 0;

        for(int i = successivelyEqualIntervalsCount; i  > 0; i--) {
            count += i;
        }

        return count;
    }
}
