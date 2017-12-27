package com.minich.android_ttt_customview;

import android.view.animation.Animation; // unnecessary but used to test git


public class Board
{
    // instance variables

    public static final int ROWS = 3;   // TODO: add comments for each constant & variable
    public static final int COLS = 3;
    private String myBoard[][];

    // default constructor

    public Board()
    {
        myBoard = new String[ROWS][COLS];

        for (int row = 0; row < ROWS; row++)
        {
            for (int col = 0; col < COLS; col++)
            {
                myBoard[row][col] = "";
            }
        }
    }

    // accessor methods

    public String[][] getBoard()
    {
        return myBoard;
    }

    public String getPosition(int row, int col)
    {
        return myBoard[row][col];
    }

    // modifier methods

    public void setPosition(int row, int col, String player)
    {
        myBoard[row][col] = player;
    }

}
