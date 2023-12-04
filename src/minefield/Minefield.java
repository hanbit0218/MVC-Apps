//100% of this Class was written by Danny Matlob
package minefield;

import mvc.Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class Minefield extends Model {
    int playerX = 0;
    int playerY = 0;
    int mineFieldWidth;
    boolean gameOver = false;
    MineSquare[][] mineField;
    public int getWidth() {
        return mineFieldWidth;
    }
    public Minefield(int mWidth, int percentMined) {
        mineFieldWidth = mWidth;
        int totalMines = ((mineFieldWidth*mineFieldWidth*percentMined)/100);
        System.out.println("Total Mines: " + totalMines);
        mineField = new MineSquare[mWidth][mWidth];
        int mineSquareWidth = 500/mineFieldWidth;
        ArrayList<MineSquare> availableSquares = new ArrayList<>();
        //Create mine squares
        for (int i = 0; i<mineFieldWidth; i++) {
            for (int j = 0; j<mineFieldWidth; j++) {
                mineField[i][j] = new MineSquare(mineSquareWidth*i, mineSquareWidth*j,mineSquareWidth, false, this);
                //If the square is not the starting or ending square, add it to list of squares that can contain mines.
                availableSquares.add(mineField[i][j]);
                //If the square is the beginning or the end then do not allow mines on it
                if ((i==0 && j==0) || (i==mineFieldWidth-1 && j==mineFieldWidth-1)) {
                    availableSquares.remove(mineField[i][j]);
                }
            }
        }
        //Last Mine has to be highlighted green
        mineField[mineFieldWidth-1][mineFieldWidth-1].setGoal();

        //Randomly disperse mines
        while (totalMines>0) {
            Random random = new Random();
            int randomIndex = random.nextInt(availableSquares.size());
            MineSquare mineSquare = availableSquares.get(randomIndex);
            if (!mineSquare.hasMine) {
                mineSquare.setMine(true);
                totalMines--;
                availableSquares.remove(mineSquare);
            }
        }

        //Loop through minefield to set mineCounts for neighbors
        for (int i = 0; i<mineFieldWidth; i++) {
            for (int j = 0; j<mineFieldWidth; j++) {
                countNeighboringMines(i,j,mineField[i][j]);
            }
        }
        try {
            movePlayer(0,0);
        } catch (Exception e) {e.printStackTrace();}
    }
    public void countNeighboringMines (int i, int j, MineSquare ms) {
        //Check all 8 neighboring squares and increment the neighbor count of this current square if the neighbors contain mines
        if (i-1>=0 && j-1>=0 && mineField[i-1][j-1].hasMine)            ms.incrementNeighbors();
        if (i-1>=0 && mineField[i-1][j].hasMine)                        ms.incrementNeighbors();
        if (i-1>=0 && j+1<mineFieldWidth && mineField[i-1][j+1].hasMine)ms.incrementNeighbors();

        if (j-1>=0&& mineField[i][j-1].hasMine)                         ms.incrementNeighbors();
        if (j+1<mineFieldWidth&& mineField[i][j+1].hasMine)             ms.incrementNeighbors();

        if (i+1<mineFieldWidth && j-1>=0&& mineField[i+1][j-1].hasMine) ms.incrementNeighbors();
        if (i+1<mineFieldWidth&& mineField[i+1][j].hasMine)             ms.incrementNeighbors();
        if (i+1<mineFieldWidth && j+1<mineFieldWidth && mineField[i+1][j+1].hasMine)ms.incrementNeighbors();
    }
    public Iterator<MineSquare> iterator () {
        Iterator<MineSquare> mineIterator = new Iterator<MineSquare>() {
            private int rowIndex = 0;
            private int colIndex = 0;
            @Override
            public boolean hasNext() {
                return rowIndex < mineField.length && colIndex < mineField[rowIndex].length;
            }
            @Override
            public MineSquare next() {
                MineSquare result = mineField[rowIndex][colIndex];
                colIndex++;
                if (colIndex == mineField[rowIndex].length) {
                    rowIndex++;
                    colIndex = 0;
                }
                return result;
            }
        };
        return mineIterator;
    }
    public boolean isGameOver() {
        return gameOver;
    }
    public void revealZeros (int x, int y, HashSet<MineSquare> searched) {
        System.out.println("Searching: " + x + ", " + y);
        if (searched.contains(mineField[x][y]) || mineField[x][y].neighborMines != 0) {
            return;
        } else {
            searched.add(mineField[x][y]);
            mineField[x][y].setRevealed(true);
        }

        if (y>1) revealZeros(x,y-1, searched);
        if (y<mineFieldWidth-1) revealZeros(x,y+1, searched);
        if (x>1) revealZeros(x-1,y, searched);
        if (x<mineFieldWidth-1) revealZeros(x+1,y, searched);
    }
    public void movePlayer(int dx, int dy) throws Exception {
        //System.out.println("Model: " + this + "Received Move Command: " + dx +"," + dy);
        if (gameOver) throw new Exception("Cannot move after game is over");
        int tmpX = playerX + dx;
        int tmpY = playerY + dy;
        if (tmpX <0 || tmpX>=mineFieldWidth || tmpY <0 || tmpY>=mineFieldWidth) {
            //System.out.println("Coords: " + tmpX +"," + tmpY);
            throw new Exception("Invalid Movement");
        } else {
            //System.out.println("Moving Player: " + dx + ", " + dy);
            mineField[playerX][playerY].setPlayerHere(false);
            playerX=tmpX;
            playerY=tmpY;
            revealZeros(playerX,playerY, new HashSet<>());
            mineField[playerX][playerY].setRevealed(true);
            mineField[playerX][playerY].setPlayerHere(true);
            changed();
            if (mineField[playerX][playerY].hasMine) {
                gameOver = true;
                changed();
                throw new Exception("Oops, hit a mine");
            }
            if (playerX==mineFieldWidth-1 && playerY==mineFieldWidth-1) {
                gameOver = true;
                throw new Exception("You Win!");
            }
        }
    }
}
