public class Sorter {
    public static int[] quickSort(int[][] matrix) {
        int[] array = matrixToArray(matrix);
        quickSort(array);
        return array;
    }

    private static int[] matrixToArray(int[][] matrix) {
        int[] array = new int[getMatrixLength(matrix)];
        int count = 0;
        for(int i=0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++)
                array[count++] = matrix[i][j];
        }
        return array;
    }
    private static int getMatrixLength(int[][] matrix) {
        int len = 0;
        for (int i=0; i < matrix.length; i++)
            len += matrix[i].length;
        return len;
    }

    public static void quickSort(int[] numbers) {
        quickSort(numbers, 0, numbers.length - 1);
    }
    public static void quickSort(int[] numbers, int start, int end) {
        if (start >= end)
            return;
        int boundary = partition(numbers, start, end);
        quickSort(numbers, start, boundary - 1);
        quickSort(numbers, boundary + 1, end);
    }
    private static int partition(int[] numbers, int start, int end) {
        int pivot = numbers[end];
        int boundary = start - 1;
        for(int i = start; i <= end; i++)
            if (numbers[i] <= pivot)
                swap(numbers, i, ++boundary);
        return boundary;
    }

    public static void mergeSort(int[] numbers) {
        if (numbers.length < 2)
            return;

        // divide to left and right
        int middle = numbers.length / 2;

        int[] left = new int[middle];
        for(int i=0; i < middle; i++)
            left[i] = numbers[i];

        int[] right = new int[numbers.length - middle];
        for(int i = middle; i < numbers.length; i++)
            right[i - middle] = numbers[i];

        // sort left and right
        mergeSort(left);
        mergeSort(right);

        // merge left and right
        merge(left, right, numbers);
    }
    private static void merge(int[] left, int[] right, int[] result) {
        int i=0, j=0, k=0;
        while (i < left.length && j < right.length && k < result.length) {
            if (left[i] < right[j])
                result[k++] = left[i++];
            else
                result[k++] = right[j++];
        }
        // insert remaining items in left or right to the result
        while(i < left.length)
            result[k++] = left[i++];
        while(j < right.length)
            result[k++] = right[j++];
    }

    public static void insertionSort(int[] numbers) {
        for(int i=1; i < numbers.length; i++) {
            int current = numbers[i];
            // compare current with all previous elements
            int j = i - 1;
            while (j >= 0 && numbers[j] > current) {
                numbers[j + 1] = numbers[j];
                j--;
            }
            numbers[j + 1] = current;
        }
    }

    public static void bubbleSort(int[] numbers) {
        for(int i=0; i < numbers.length; i++) {
            boolean isSorted = true;
            for(int j=1; j < numbers.length - i; j++)
                if (numbers[j - 1] > numbers[j]) {
                    swap(numbers, j - 1, j);
                    isSorted = false;
                }
            if (isSorted)
                return;
        }
    }

    private static void swap(int[] numbers, int first, int second) {
        int smaller = numbers[first];
        numbers[first] = numbers[second];
        numbers[second] = smaller;
    }
}
