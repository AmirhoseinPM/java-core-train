import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[][] matrix = {
                {12, 7},
                {5, 89, 7, 48, 999},
                {67, 14, 18, -3}
        };
        System.out.println("Matrix: " + Arrays.deepToString(matrix));

        int[] sortedArray = Sorter.quickSort(matrix);
        System.out.println("Sorted array: " + Arrays.toString(sortedArray));

    }
}