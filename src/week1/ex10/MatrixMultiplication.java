package week1.ex10;

import java.util.*;

public class MatrixMultiplication {

    public int[][] multiplyMatrices(int[][] matrixA, int[][] matrixB) {
        int rowsA = matrixA.length;
        int colsA = matrixA[0].length;

        int colsB = matrixB[0].length;

        int[][] result = new int[rowsA][colsB];

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                final int row = i;
                final int col = j;

                /* Create a new thread to compute the element at position (row, col) */
                Thread thread = new Thread(() -> {
                    result[row][col] = 0;
                    for (int k = 0; k < colsA; k++) {
                        result[row][col] += matrixA[row][k] * matrixB[k][col];
                    }
                });

                threads.add(thread);
                thread.start();  // Start the thread
            }
        }

        /* Wait for all threads to finish */
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted: " + e.getMessage());
            }
        }

        return result;
    }

    // Method to print a matrix
    public void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static void main(String[] args) {
        MatrixMultiplication matrixMultiplication = new MatrixMultiplication();

        int[][] matrixA = {{1, 2}, {3, 4}};
        int[][] matrixB = {{2, 0}, {1, 2}};

        int[][] result = matrixMultiplication.multiplyMatrices(matrixA, matrixB);

        System.out.println("Result of the multiplication:");
        matrixMultiplication.printMatrix(result);

        // Test case 2: Larger matrices
        int[][] largeMatrixA = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] largeMatrixB = {
                {9, 8, 7},
                {6, 5, 4},
                {3, 2, 1}
        };

        int[][] result2 = matrixMultiplication.multiplyMatrices(largeMatrixA, largeMatrixB);

        System.out.println("Result of the multiplication:");
        matrixMultiplication.printMatrix(result2);

    }
}
