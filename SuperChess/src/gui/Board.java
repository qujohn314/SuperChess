package gui;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import pieces.*;
public class Board extends JPanel
{
    private static Space[][] spaces, dangerBoard;
    public static final int B_HEIGHT = 604;
    public static final int B_WIDTH = 602;
    public Board board;
    public static Board staticBoard;
    
    public Board()
    {
        initBoard();
        board = this;
        staticBoard = board;
    }

    public void initBoard(){
    	
        dangerBoard = new Space[8][8];
        spaces = new Space[8][8];
        int spot = 0;
        setFocusable(true);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        for(int r = 0;r<spaces.length;r++)
        	for(int c = 0;c<spaces[r].length;c++) {
        	  if(r%2 == 0) {
                if(spot % 2 == 0 ){
                    spaces[r][c] = new Space(Color.WHITE,r,c);
                    add(spaces[r][c]);
                }
                else{
                    spaces[r][c] = new Space(Color.LIGHT_GRAY,r,c);
                    add(spaces[r][c]);
                }
        	  }else {
        		  if(spot % 2 == 0 ){
                      spaces[r][c] = new Space(Color.LIGHT_GRAY,r,c);
                      add(spaces[r][c]);
                  }
                  else{
                      spaces[r][c] = new Space(Color.WHITE,r,c);
                      add(spaces[r][c]);
                  }
        	  }
              spot++;
        }
        spaces[7][0].setPiece(new Rook(7,0,0));
        spaces[7][7].setPiece(new Rook(7,7,0));
        spaces[0][0].setPiece(new Rook(0,0,1));
        spaces[0][7].setPiece(new Rook(0,7,1));
        spaces[7][2].setPiece(new Bishop(7,2,0));
        spaces[7][5].setPiece(new Bishop(7,5,0));
        spaces[0][2].setPiece(new Bishop(0,2,1));
        spaces[0][5].setPiece(new Bishop(0,5,1));
        spaces[7][1].setPiece(new Knight(7,1,0));
        spaces[7][6].setPiece(new Knight(7,6,0));
        spaces[0][1].setPiece(new Knight(0,1,1));
        spaces[0][6].setPiece(new Knight(0,6,1));
        spaces[0][3].setPiece(new Queen(0,3,1));
        spaces[7][3].setPiece(new Queen(7,3,0));
        spaces[0][4].setPiece(new King(0,4,1));
        spaces[7][4].setPiece(new King(7,4,0));
        for(int i = 0;i<8;i++)
            spaces[6][i].setPiece(new Pawn(6,i,0));
        for(int i = 0;i<8;i++)
            spaces[1][i].setPiece(new Pawn(1,i,1));
        updateBoard();
    }

    public static void updateBoard(){
        for(int r = 0;r<8;r++){
            for(int c = 0;c<8;c++){
                if(spaces[r][c].getBackground() != Color.LIGHT_GRAY || spaces[r][c].getBackground() != Color.WHITE){
                    spaces[r][c].setBackground(spaces[r][c].origColor);
                }
                spaces[r][c].updateFill();
                spaces[r][c].updateClickable();
            }
        }
        for(int r = 0;r<8;r++){
            for(int c = 0;c<8;c++){
                spaces[r][c].removeDanger();
            }
        }
        for(int r = 0;r<8;r++){
            for(int c = 0;c<8;c++){
                if(spaces[r][c].getFill()== true){
                    spaces[r][c].getPiece().updateSpaces();
                }
            }
        }
        updateDangerBoard();
    }

    public static void updateDangerBoard(){
        dangerBoard = new Space[8][8];
        for(int r = 0;r<8;r++){
            for(int c = 0;c<8;c++){
                dangerBoard[r][c] = new Space(Color.WHITE,r,c);
            }
        }
        for(int r = 0;r<8;r++){
            for(int c = 0;c<8;c++){
                if(spaces[r][c].getFill() == true){
                    dangerBoard[r][c].setFakePiece(spaces[r][c].getPiece().clonePiece());
                }
            }
        }
        printDangerBoard();
    }

    public static void printDangerBoard(){
        /*
        System.out.println('\u000C');
        for(int r = 0;r<8;r++){
            for(int c = 0;c<8;c++){
                if(dangerBoard[r][c].getFill() == true){
                    if(dangerBoard[r][c].getPiece() instanceof Pawn)
                        System.out.print("P");
                    if(dangerBoard[r][c].getPiece() instanceof Knight)
                        System.out.print("H");
                    if(dangerBoard[r][c].getPiece() instanceof Rook)
                        System.out.print("R");
                    if(dangerBoard[r][c].getPiece() instanceof King)
                        System.out.print("K");
                    if(dangerBoard[r][c].getPiece() instanceof Queen)
                        System.out.print("Q");
                    if(dangerBoard[r][c].getPiece() instanceof Bishop)
                        System.out.print("B");
                }
                else
                    System.out.print("-");
               System.out.print(" ");
            }
            System.out.print("\n");
        }
        */
    }

    public static Space[][] getBoard(){
        return spaces; 
    }

    public static Space[][] getDangerBoard(){
        return dangerBoard; 
    }
}

