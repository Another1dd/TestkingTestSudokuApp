package com.another1dd.testkingtestsudokuapp.gameEngine;


public class SudokuGenerator {
    private static SudokuGenerator instance;


    private SudokuGenerator() {
    }

    public static SudokuGenerator getInstance() {
        if (instance == null) {
            instance = new SudokuGenerator();
        }
        return instance;
    }

    public int[][] generateGrid() {
        int[][] sudoku = new int[9][9];
        int[] sudokuFromDb = SudokuDB.getGameStart();
        int currentPos = 0;


        while (currentPos < 81) {
            if (currentPos == 0) {
                clearGrid(sudoku);
            }

            int number = sudokuFromDb[currentPos];
            int xPos = currentPos % 9;
            int yPos = currentPos / 9;

            sudoku[xPos][yPos] = number;
            currentPos++;
        }
        return sudoku;
    }

    public int[][] generateGameOverGrid() {
        int[][] sudoku = new int[9][9];
        int[] sudokuFromDb = SudokuDB.getGameOver();
        int currentPos = 0;


        while (currentPos < 81) {
            if (currentPos == 0) {
                clearGrid(sudoku);
            }

            int number = sudokuFromDb[currentPos];
            int xPos = currentPos % 9;
            int yPos = currentPos / 9;

            sudoku[xPos][yPos] = number;
            currentPos++;
        }
        return sudoku;
    }


    private void clearGrid(int[][] Sudoku) {

        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                Sudoku[x][y] = -1;
            }
        }

    }


}
