package com.another1dd.testkingtestsudokuapp.gameEngine;


class SudokuDB {

    private static final int[] gameOver = new int[]{
            2, 6, 4, 1, 9, 7, 5, 8, 3,
            3, 9, 8, 2, 5, 4, 6, 1, 7,
            1, 5, 7, 3, 6, 8, 9, 2, 4,
            7, 4, 6, 8, 2, 9, 3, 5, 1,
            9, 1, 2, 5, 3, 6, 7, 4, 8,
            8, 3, 5, 7, 4, 1, 2, 6, 9,
            6, 8, 1, 9, 7, 2, 4, 3, 5,
            4, 7, 3, 6, 8, 5, 1, 9, 2,
            5, 2, 9, 4, 1, 3, 8, 7, 6,
    };

    private static final int[] gameStart = new int[]{
            0, 0, 4, 1, 0, 0, 0, 0, 0,
            0, 9, 0, 0, 0, 4, 6, 0, 7,
            1, 0, 0, 0, 6, 0, 0, 2, 4,
            7, 0, 6, 0, 0, 9, 0, 5, 0,
            0, 1, 0, 0, 0, 6, 0, 0, 0,
            0, 0, 5, 0, 0, 1, 2, 6, 0,
            0, 8, 0, 0, 0, 0, 4, 0, 0,
            0, 0, 3, 0, 0, 5, 0, 9, 2,
            5, 0, 9, 0, 1, 0, 8, 0, 6,
    };

    static int[] getGameStart() {
        return gameStart;
    }


    static int[] getGameOver() {
        return gameOver;
    }
}
