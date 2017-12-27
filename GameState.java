package com.minich.android_ttt_customview;

public class GameState
{
    // instance variables   

    private boolean gameRunning;
    private String playerTurn;

    // default constructor

    public GameState()
    {
        gameRunning = true;
        playerTurn = "x";
    }

    // accessor methods

    public boolean gameRunning()
    {
        return gameRunning;
    }

    public String getPlayerTurn()
    {
        return playerTurn;
    }

    // interesting methods

    public void togglePlayerTurn()
    {
        if (playerTurn.equals("x"))
        {
            playerTurn = "o";
        }
        else
        {
            playerTurn = "x";
        }
    }

    public boolean isDraw(Board board)
    {

        for (int row = 0; row < Board.ROWS; row++)
        {

            for (int col = 0; col < Board.COLS; col++)
            {

                if (board.getBoard()[row][col].isEmpty())
                {
                    return false;
                }

            }

        }

        return true;
    }

    public String isWinner(Board board)
    {
        String result = "";

        if (board.getBoard()[0][0].equals("x") && board.getBoard()[0][1].equals("x") && board.getBoard()[0][2].equals("x"))
        {
            result = "x";
        }

        if (!result.isEmpty())
        {
            gameRunning = false;
        }

        return result;
    }

}


