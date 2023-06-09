package com.premiumminds.internship.snail;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by aamado on 05-05-2023.
 */
class SnailShellPattern implements ISnailShellPattern {
  private final ExecutorService executor = Executors.newSingleThreadExecutor();

  /**
   * Method to get snailshell pattern
   *
   * @param matrix matrix of numbers to go through
   * @return order array of values thar represent a snail shell pattern
   */
  public Future<int[]> getSnailShell(int[][] matrix) {
    // Matrix of size N = n x n
    int n = matrix.length;
    int n_i = n;
    int k = (int) Math.ceil((double) n / 2); // Number of matrices
    int[] result = new int[n * n];

    int matrix_origin = 0;
    for (int i = 0; i <= k; i++) {
      // Top Row
      int start = matrix_origin;
      for (int offset = 0; offset < n_i; offset++) {
        System.out.println("Placing Top: " + matrix[i][offset + i] + " at " + (start + offset));
        result[start + offset] = matrix[i][offset + i];
      }

      // Bottom Row
      start = matrix_origin + 2 * n_i - 2;
      for (int offset = 0; offset < n_i; offset++) {
        System.out.println("Placing Bottom: " + matrix[n - 1 - i][offset + i] + " at " + (start + (n_i - 1 - offset)));
        result[start + (n_i - 1 - offset)] = matrix[n - 1 - i][offset + i];
      }

      for (int offset = 1; offset < n_i - 1; offset++) {
        // Left Column
        start = matrix_origin + 3 * (n_i - 1);
        System.out.println("Placing Left: " + matrix[offset + i][i] + " at " + (start + (n_i - 1 - offset)));
        result[start + (n_i - 1 - offset)] = matrix[offset + i][i];

        // Right Column
        start = matrix_origin + n_i - 1;
        System.out.println("Placing Right: " + matrix[offset + i][n - 1 - i] + " at " + (start + offset));
        result[start + offset] = matrix[offset + i][n - 1 - i];
      }

      // After going through the top part of every step, do the same for the bottom, left and right sides
      // No need to do ifs, just add if done correctly => Greatly reduces time

      // go to inner matrix
      matrix_origin = matrix_origin + (4 * n_i - 4);
      n_i -= 2;

      System.out.println("i = " + i + " ; matrix_origin = " + matrix_origin + " ; n_i = " + n_i);

      System.out.println("Result after " + (i + 1) + " iteration: " + Arrays.toString(result));
    }

    System.out.println("Result: " + Arrays.toString(result));

    return executor.submit(() -> result);
  }
}