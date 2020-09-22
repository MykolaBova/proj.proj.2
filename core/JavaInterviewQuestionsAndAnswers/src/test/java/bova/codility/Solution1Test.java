package bova.codility;

import bova.codility.Solution1;
import junit.framework.TestCase;
import org.junit.Test;

public class Solution1Test extends TestCase {

    @Test
    public void testBase() {
        Solution1 solution = new Solution1();

        int[] array = {0,1,2,3};
        int result = solution.solution(array);
        assertEquals(result, 3);

        int[] array2 = {0,1,2,3,4};
        int result2 = solution.solution(array2);
        assertEquals(result2, 6);

        int[] array3 = {0,1,2,3,4,4,4};
        int result3 = solution.solution(array3);
        assertEquals(7,  result3);
    }
}