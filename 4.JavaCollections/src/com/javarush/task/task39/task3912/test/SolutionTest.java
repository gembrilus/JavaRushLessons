package com.javarush.task.task39.task3912.test;

import com.javarush.task.task39.task3912.Solution;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

public class SolutionTest {

    int[][] matrix0 = {                         // MaxSquare = 0
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 }
    };

    int[][] matrix1 = {                         // MaxSquare = 4
            { 1, 1, 0, 0, 1, 0, 1, 0, 0 },
            { 1, 1, 0, 0, 1, 0, 1, 0, 0 },
            { 1, 1, 0, 0, 1, 0, 1, 0, 0 },
            { 1, 1, 0, 0, 1, 0, 1, 0, 0 },
            { 1, 1, 0, 0, 1, 0, 1, 0, 0 },
            { 1, 1, 0, 0, 1, 0, 1, 0, 0 },
            { 1, 1, 0, 0, 1, 0, 1, 0, 0 },
            { 1, 1, 0, 0, 1, 0, 1, 0, 0 }
    };

    int[][] matrix2 = {                         // MaxSquare = 9
            { 1, 1, 1, 0, 1, 0, 1, 1, 1 },
            { 1, 0, 1, 0, 1, 0, 1, 1, 1 },
            { 1, 1, 1, 0, 1, 0, 1, 0, 1 },
            { 1, 1, 0, 0, 1, 0, 1, 0, 0 },
            { 1, 1, 0, 0, 1, 0, 1, 0, 0 },
            { 1, 1, 0, 0, 1, 1, 1, 0, 0 },
            { 1, 1, 0, 0, 1, 1, 1, 0, 0 },
            { 1, 1, 0, 0, 1, 1, 1, 0, 0 }
    };

    int[][] matrix3 = {                         // MaxSquare = 64
            { 0, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1 }
    };

    int[][] matrix4 = {                         // MaxSquare = 49
            { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1 }
    };

    int[][] matrix5 = {                         // MaxSquare = 64
            { 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1 }
    };

    int[][] matrix6 = {                         // MaxSquare = 1
            { 1, 1, 1, 1, 1, 1, 1, 1, 1 }
    };

    int[][] matrix7 = {                         // MaxSquare = 1
            { 1, 0, 1, 0, 1, 0, 1, 0, 1 },
            { 0, 1, 0, 1, 0, 1, 0, 1, 0 },
            { 1, 0, 1, 0, 1, 0, 1, 0, 1 },
            { 0, 1, 0, 1, 0, 1, 0, 1, 0 },
            { 1, 0, 1, 0, 1, 0, 1, 0, 1 },
            { 0, 1, 0, 1, 0, 1, 0, 1, 0 },
            { 1, 0, 1, 0, 1, 0, 1, 0, 1 },
            { 0, 1, 0, 1, 0, 1, 0, 1, 0 }
    };

    int[][] matrix8 = {};
    int[][] matrix9 = null;

    int[][] matrix10 = {                         // MaxSquare = 1
            { 1 },
            { 0 },
            { 1 },
            { 0 },
            { 1 },
            { 0 },
            { 1 },
            { 0 }
    };

    @Test
    public void maxSquare() {
        MatcherAssert.assertThat(Solution.maxSquare(matrix0), Matchers.equalTo(0));
        MatcherAssert.assertThat(Solution.maxSquare(matrix1), Matchers.equalTo(4));
        MatcherAssert.assertThat(Solution.maxSquare(matrix2), Matchers.equalTo(9));
        MatcherAssert.assertThat(Solution.maxSquare(matrix3), Matchers.equalTo(64));
        MatcherAssert.assertThat(Solution.maxSquare(matrix4), Matchers.equalTo(49));
        MatcherAssert.assertThat(Solution.maxSquare(matrix5), Matchers.equalTo(64));
        MatcherAssert.assertThat(Solution.maxSquare(matrix6), Matchers.equalTo(1));
        MatcherAssert.assertThat(Solution.maxSquare(matrix7), Matchers.equalTo(1));
        MatcherAssert.assertThat(Solution.maxSquare(matrix8), Matchers.equalTo(0));
        MatcherAssert.assertThat(Solution.maxSquare(matrix9), Matchers.equalTo(0));
        MatcherAssert.assertThat(Solution.maxSquare(matrix10), Matchers.equalTo(1));
    }
}