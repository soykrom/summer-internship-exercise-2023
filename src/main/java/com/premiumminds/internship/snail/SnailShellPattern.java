package com.premiumminds.internship.snail;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by aamado on 05-05-2023.
 */
class SnailShellPattern implements ISnailShellPattern {
  private final ExecutorService executor = Executors.newSingleThreadExecutor();
  private int[][] matrix;

  private int[] result;

  private int position = 0;

  private int begin = 0;
  private int end;
  /**
   * Method to get snailshell pattern
   *
   * @param original_matrix matrix of numbers to go through
   * @return order array of values thar represent a snail shell pattern
   */
  public Future<int[]> getSnailShell(int[][] original_matrix) {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Future<int[]> future = executorService.submit(() -> {
      matrix = original_matrix;
      end = matrix.length;

      int n_matrix = (int) Math.ceil((double) end / 2); // Number of matrices
      result = new int[end * end];

      while (end > n_matrix) {
        topRow();

        rightColumn();

        bottomRow();

        leftColumn();

        begin++;
        end = end - 1;
      }

      if (begin < n_matrix) {
        result[position] = matrix[n_matrix - 1][n_matrix - 1];
      }

      return result;
    });

    executorService.shutdown();
    return future;
  }

  private void topRow() {
    for (int i = begin; i < end; i++) {
      result[position++] = matrix[begin][i];
    }
  }

  private void rightColumn() {
    for (int i = begin + 1; i < end - 1; i++) {
      result[position++] = matrix[i][end - 1];
    }
  }

  private void bottomRow() {
    for (int i = end - 1; i >= begin; i--) {
      result[position++] = matrix[end - 1][i];
    }
  }

  private void leftColumn() {
    for (int i = end - 2; i >= begin + 1; i--) {
      result[position++] = matrix[i][begin];
    }
  }
}