package com.another1dd.testkingtestsudokuapp.view;

import android.content.Context;
import android.widget.Toast;

import com.another1dd.testkingtestsudokuapp.GameActivity;
import com.another1dd.testkingtestsudokuapp.R;
import com.another1dd.testkingtestsudokuapp.gameEngine.SudokuGenerator;

import java.util.ArrayList;
import java.util.Arrays;

public class GameGrid {
    private SudokuCell[][] sudoku = new SudokuCell[9][9];
    private int[][] sudokuFromDb = SudokuGenerator.getInstance().generateGameOverGrid();

    private Context context;

    public GameGrid(Context context) {
        this.context = context;
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                sudoku[x][y] = new SudokuCell(context);
            }
        }
    }

    public void setGrid(int[][] grid) {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                sudoku[x][y].setInitValue(grid[x][y]);
                sudoku[x][y].setBackgroundResource(R.drawable.cell_empty_backpsd);
                if (grid[x][y] != 0) {
                    sudoku[x][y].setBackgroundResource(R.drawable.cell_start_back);
                    sudoku[x][y].setNotModifiable();
                    sudoku[x][y].setEnabled(false);

                }
            }
        }
    }

    public void setGridFromDB(ArrayList<Integer> sudokuFromDb) {
        int[][] sudokuRdyFromDB = new int[9][9];
        int t = 0;
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                sudokuRdyFromDB[x][y] = sudokuFromDb.get(t);
                t++;
            }
        }
        int[][] sudoku = SudokuGenerator.getInstance().generateGrid();
        setGrid(sudoku);
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (sudokuRdyFromDB[x][y] != sudoku[x][y]) {
                    setItem(x, y, sudokuRdyFromDB[x][y]);
                }

            }
        }

    }

    public SudokuCell[][] getGrid() {
        return sudoku;
    }

    public ArrayList<Integer> getSudokuSaveState() {
        ArrayList<Integer> sudokuForDB = new ArrayList<>();

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                sudokuForDB.add(sudoku[x][y].getValue());

            }

        }
        return sudokuForDB;
    }

    private SudokuCell getItem(int x, int y) {
        return sudoku[x][y];
    }

    SudokuCell getItem(int position) {
        int x = position % 9;
        int y = position / 9;

        return sudoku[x][y];
    }

    public void setItem(int x, int y, int number) {
        sudoku[x][y].setValue(number);
        if (sudokuFromDb[x][y] == number) {
            sudoku[x][y].setBackgroundResource(R.drawable.cell_right_back);
        } else if (number == 0) {
            sudoku[x][y].setBackgroundResource(R.drawable.cell_empty_backpsd);
        } else {
            sudoku[x][y].setBackgroundResource(R.drawable.cell_wrong_back);
        }

    }

    public void checkGame() {
        int[][] sudGrid = new int[9][9];
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                sudGrid[x][y] = getItem(x, y).getValue();
            }
        }

        if (Arrays.deepEquals(sudGrid, sudokuFromDb)) {
            String t = GameActivity.timeForGameGrid;
            Toast.makeText(context, "Congratulations! Your time: " + t, Toast.LENGTH_LONG).show();
            GameActivity.timerStop();
        }
    }

}
