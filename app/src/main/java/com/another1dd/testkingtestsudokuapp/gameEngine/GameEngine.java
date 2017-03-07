package com.another1dd.testkingtestsudokuapp.gameEngine;

import android.content.Context;

import com.another1dd.testkingtestsudokuapp.view.GameGrid;

import java.util.ArrayList;

public class GameEngine {
    private static GameEngine instance;

    private GameGrid grid = null;

    private int selectedPosX = -1, selectedPosY = -1;

    private GameEngine() {
    }

    public static GameEngine getInstance() {
        if (instance == null) {
            instance = new GameEngine();
        }
        return instance;
    }

    public void createGrid(Context context) {
        int[][] sudoku = SudokuGenerator.getInstance().generateGrid();
        grid = new GameGrid(context);
        grid.setGrid(sudoku);
    }

    public void createGridFromDB(Context context, ArrayList<Integer> sudokuFromDB) {
        grid = new GameGrid(context);
        grid.setGridFromDB(sudokuFromDB);
    }

    public GameGrid getGrid() {
        return grid;
    }

    public void setSelectedPosition(int x, int y) {
        selectedPosX = x;
        selectedPosY = y;
    }

    public void setNumber(int number) {
        if (selectedPosX != -1 && selectedPosY != -1) {
            grid.setItem(selectedPosX, selectedPosY, number);
        }
        grid.checkGame();
    }

    public ArrayList<Integer> getGridSaveState() {
        return grid.getSudokuSaveState();
    }
}
