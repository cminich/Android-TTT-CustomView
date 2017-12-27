/**
 NAME_OF_PROJECT
 @author John Doe Period 5/6
 */

// TODO: add comments for all variable & constant declarations & method postconditions as
// well as any other worthwhile, best practices descriptive comments

package com.minich.android_ttt_customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.MotionEvent;
import android.widget.Toast;
import android.text.TextPaint;
import android.text.StaticLayout;
import android.text.Layout;

public class MainActivity extends AppCompatActivity
{
    TicTacToeBoardView ticTacToeBoardView;
    Board board;
    GameState game;
    int mySquareWidth;

    // initialize the Activity with the View of the tic tac toe board
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ticTacToeBoardView = new TicTacToeBoardView(this);
        setContentView(ticTacToeBoardView);
    }

    // tic tac toe board view
    private class TicTacToeBoardView extends View
    {
        private String playerTurn = "x";

        public TicTacToeBoardView(Context context)
        {
            super(context);
            board = new Board();
            game = new GameState();
        }

        // draw tic tac toe board
        @Override
        protected void onDraw(Canvas canvas)
        {
            super.onDraw(canvas);
            int screenWidth = getWidth();

            Paint paint = new Paint();
            paint.setColor(Color.GREEN);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(10);
            paint.setTextSize(72);

            TextPaint textPaint = new TextPaint();
            textPaint.setAntiAlias(true);
            textPaint.setTextSize(16 * getResources().getDisplayMetrics().density);
            textPaint.setColor(Color.BLACK);
            String message = "";

            int myLeftEdge = 0;
            int myTopEdge = 0;
            mySquareWidth = screenWidth / 3;

            canvas.drawLine(myLeftEdge, myTopEdge + mySquareWidth, myLeftEdge + mySquareWidth * 3, myTopEdge + mySquareWidth, paint);           // top horizontal line
            canvas.drawLine(myLeftEdge, myTopEdge + mySquareWidth * 2, myLeftEdge + mySquareWidth * 3, myTopEdge + mySquareWidth * 2, paint);   // bottom horizontal line
            canvas.drawLine(myLeftEdge + mySquareWidth,       myTopEdge, myLeftEdge + mySquareWidth,     myTopEdge + mySquareWidth * 3, paint); // left vertical line
            canvas.drawLine(myLeftEdge + mySquareWidth * 2,   myTopEdge, myLeftEdge + mySquareWidth * 2, myTopEdge + mySquareWidth * 3, paint); // right vertical line

            if (board.getPosition(0, 0).equals("x"))
            {
                canvas.drawLine(0, 0, mySquareWidth, mySquareWidth, paint);
                canvas.drawLine(mySquareWidth,  0, 0, mySquareWidth, paint);
            }
            else if (board.getPosition(0, 0).equals("o"))
            {
                canvas.drawCircle(Math.round(myLeftEdge + mySquareWidth * .5), Math.round(myTopEdge + mySquareWidth / 2), Math.round(mySquareWidth / 2), paint);
            }

            if (board.getPosition(0, 1).equals("x"))
            {
                canvas.drawLine(mySquareWidth, 0, 2 * mySquareWidth, mySquareWidth, paint);
                canvas.drawLine(2 * mySquareWidth, 0, mySquareWidth, mySquareWidth, paint);
            }
            else if (board.getPosition(0, 1).equals("o"))
            {
                canvas.drawCircle(Math.round(myLeftEdge + mySquareWidth * 1.5), Math.round(myTopEdge + mySquareWidth / 2), Math.round(mySquareWidth / 2), paint);
            }

            if (board.getPosition(0, 2).equals("x"))
            {
                canvas.drawLine(2 * mySquareWidth, 0, 3 * mySquareWidth, mySquareWidth, paint);
                canvas.drawLine(3 * mySquareWidth, 0, 2 * mySquareWidth, mySquareWidth, paint);
            }
            else if (board.getPosition(0, 2).equals("o"))
            {
                canvas.drawCircle(Math.round(myLeftEdge + mySquareWidth * 2.5), Math.round(myTopEdge + mySquareWidth / 2), Math.round(mySquareWidth / 2), paint);
            }

            // TODO: add if's here for the rest of the game board positions


            // win detection
            if (game.isDraw(board))
            {
                message = "Tie Game";
            }
            else if (!game.isWinner(board).isEmpty())
            {
                message = game.isWinner(board) + " Wins";
            }
            else
            {
                message = "It is player " + game.getPlayerTurn() + "'s turn";
            }

            int width = (int) textPaint.measureText(message);
            StaticLayout staticLayout = new StaticLayout(message, textPaint, (int) width, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0, false);
            canvas.save();
            canvas.translate(screenWidth / 2 - width / 2, 3 * mySquareWidth + 50);
            staticLayout.draw(canvas);
            canvas.restore();
        }

        // detect user's touch
        public boolean onTouchEvent(MotionEvent event)
        {

            if (event.getAction() == MotionEvent.ACTION_UP && game.gameRunning())
            {
                double x = event.getX();
                double y = event.getY();

                // using Toast for debugging purposes -  remove from final version
                Toast.makeText(getApplicationContext(),"playerTurn: " + game.getPlayerTurn() + "\nx: "+ Math.round(x) + "\ny: " + Math.round(y),Toast.LENGTH_SHORT).show();

                if (x >= 0 && x <= mySquareWidth && y >= 0 && y <= mySquareWidth && board.getPosition(0, 0).isEmpty())
                {

                    if (game.getPlayerTurn().equals("x"))
                    {
                        board.setPosition(0, 0, "x");
                    }
                    else
                    {
                        board.setPosition(0, 0, "o");
                    }

                }
                else if (x >= mySquareWidth && x <= 2 * mySquareWidth && y >= 0 && y <= mySquareWidth)
                {

                    if (game.getPlayerTurn().equals("x"))
                    {
                        board.setPosition(0, 1, "x");
                    }
                    else
                    {
                        board.setPosition(0, 1, "o");
                    }

                }
                else if (x >= 2 * mySquareWidth && x <= 3 * mySquareWidth && y >= 0 && y <= mySquareWidth)
                {

                    if (game.getPlayerTurn().equals("x"))
                    {
                        board.setPosition(0, 2, "x");
                    }
                    else
                    {
                        board.setPosition(0, 2, "o");
                    }

                }

                game.togglePlayerTurn();

                invalidate();        // redraw the View by calling onDraw
            }

            return true;

        }// end of onTouchEvent

    }// end of TicTacToeBoardView class

}// end of MainActivity class





