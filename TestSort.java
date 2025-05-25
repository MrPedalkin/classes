import java.util.Arrays;
import java.util.Random;

public class SortingBenchmark {
    public static void main(String[] args) {
        // Размеры массивов для тестирования
        int[] smallArray = generateRandomArray(1_000);
        int[] mediumArray = generateRandomArray(10_000);
        int[] largeArray = generateRandomArray(100_000);
        int[] veryLargeArray = generateRandomArray(1_000_000);

        // Тестирование на маленьком массиве (1,000 элементов)
        System.out.println("Маленький массив (1,000 элементов):");
        benchmarkSorting(smallArray.clone(), "Быстрая сортировка", SortingAlgorithms::quickSort);
        benchmarkSorting(smallArray.clone(), "Сортировка слиянием", SortingAlgorithms::mergeSort);

        // Тестирование на среднем массиве (10,000 элементов)
        System.out.println("\nСредний массив (10,000 элементов):");
        benchmarkSorting(mediumArray.clone(), "Быстрая сортировка", SortingAlgorithms::quickSort);
        benchmarkSorting(mediumArray.clone(), "Сортировка слиянием", SortingAlgorithms::mergeSort);

        // Тестирование на большом массиве (100,000 элементов)
        System.out.println("\nБольшой массив (100,000 элементов):");
        benchmarkSorting(largeArray.clone(), "Быстрая сортировка", SortingAlgorithms::quickSort);
        benchmarkSorting(largeArray.clone(), "Сортировка слиянием", SortingAlgorithms::mergeSort);

        // Тестирование на очень большом массиве (1,000,000 элементов)
        System.out.println("\nОчень большой массив (1,000,000 элементов):");
        benchmarkSorting(veryLargeArray.clone(), "Быстрая сортировка", SortingAlgorithms::quickSort);
        benchmarkSorting(veryLargeArray.clone(), "Сортировка слиянием", SortingAlgorithms::mergeSort);
    }

    // Генерация случайного массива
    private static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt();
        }
        return arr;
    }

    // Метод для измерения времени выполнения сортировки
    private static void benchmarkSorting(int[] arr, String algorithmName, Runnable sortingAlgorithm) {
        long startTime = System.nanoTime();
        sortingAlgorithm.run();
        long endTime = System.nanoTime();
        
        double durationMs = (endTime - startTime) / 1_000_000.0;
        System.out.printf("%s: %.3f мс%n", algorithmName, durationMs);
    }
}
