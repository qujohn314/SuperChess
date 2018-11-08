package pieces;
import gui.Board;
import java.awt.Color;
public class Knight extends Piece
{  
    public Knight(int r, int c,int t){
        super(r,c,t);
        if(team == 1)
            loadImage(dir + "res/images/BlackKnight.png");
        else
            loadImage(dir + "res/images/WhiteKnight.png");
    }

    public void updateSpaces(){
        if(r-2 >= 0 && c+1 < 8){
            Board.getBoard()[r-2][c+1].setDanger(this);
        }
        if(r-2 >= 0 && c-1 >= 0){
            Board.getBoard()[r-2][c-1].setDanger(this);;
        }
        if(r-1 >= 0 && c-2 >= 0){
            Board.getBoard()[r-1][c-2].setDanger(this);
        }
        if(r-1 >= 0 && c+2 < 8){
            Board.getBoard()[r-1][c+2].setDanger(this);
        }
        if(r+1 < 8 && c+2 < 8){
            Board.getBoard()[r+1][c+2].setDanger(this);
        }
        if(r+1 < 8 && c-2 >= 0){          
            Board.getBoard()[r+1][c-2].setDanger(this);;
        }
        if(r+2 < 8 && c-1 >= 0){
            Board.getBoard()[r+2][c-1].setDanger(this);
        }
        if(r+2 < 8 && c+1 < 8){
            Board.getBoard()[r+2][c+1].setDanger(this);
        }
    }

    public void updateFakeSpaces(){
        if(r-2 >= 0 && c+1 < 8){
            Board.getDangerBoard()[r-2][c+1].setDanger(this);
        }
        if(r-2 >= 0 && c-1 >= 0){
            Board.getDangerBoard()[r-2][c-1].setDanger(this);;
        }
        if(r-1 >= 0 && c-2 >= 0){
            Board.getDangerBoard()[r-1][c-2].setDanger(this);
        }
        if(r-1 >= 0 && c+2 < 8){
            Board.getDangerBoard()[r-1][c+2].setDanger(this);
        }
        if(r+1 < 8 && c+2 < 8){
            Board.getDangerBoard()[r+1][c+2].setDanger(this);
        }
        if(r+1 < 8 && c-2 >= 0){          
            Board.getDangerBoard()[r+1][c-2].setDanger(this);;
        }
        if(r+2 < 8 && c-1 >= 0){
            Board.getDangerBoard()[r+2][c-1].setDanger(this);
        }
        if(r+2 < 8 && c+1 < 8){
            Board.getDangerBoard()[r+2][c+1].setDanger(this);
        }
    }

    public void getMoves(){
        if(r-2 >= 0 && c+1 < 8){
            if(isValidMove(r-2,c+1))
                if(Board.getBoard()[r-2][c+1].getFill() == true && Board.getBoard()[r-2][c+1].getPiece().getTeam() != team){
                    Board.getBoard()[r-2][c+1].setBackground(Color.RED);
                }else if(Board.getBoard()[r-2][c+1].getFill() == false){
                    Board.getBoard()[r-2][c+1].setBackground(Color.RED);
                }  
        }
        if(r-2 >= 0 && c-1 >= 0){
            if(isValidMove(r-2,c-1))
                if(Board.getBoard()[r-2][c-1].getFill() == true && Board.getBoard()[r-2][c-1].getPiece().getTeam() != team){
                    Board.getBoard()[r-2][c-1].setBackground(Color.RED);
                }else if(Board.getBoard()[r-2][c-1].getFill() == false){
                    Board.getBoard()[r-2][c-1].setBackground(Color.RED);
                }  
        }
        if(r-1 >= 0 && c-2 >= 0){
            if(isValidMove(r-1,c-2))
                if(Board.getBoard()[r-1][c-2].getFill() == true && Board.getBoard()[r-1][c-2].getPiece().getTeam() != team){
                    Board.getBoard()[r-1][c-2].setBackground(Color.RED);
                }else if(Board.getBoard()[r-1][c-2].getFill() == false){
                    Board.getBoard()[r-1][c-2].setBackground(Color.RED);
                }  
        }
        if(r-1 >= 0 && c+2 < 8){
            if(isValidMove(r-1,c+2))
                if(Board.getBoard()[r-1][c+2].getFill() == true && Board.getBoard()[r-1][c+2].getPiece().getTeam() != team){
                    Board.getBoard()[r-1][c+2].setBackground(Color.RED);
                }else if(Board.getBoard()[r-1][c+2].getFill() == false){
                    Board.getBoard()[r-1][c+2].setBackground(Color.RED);
                }  
        }
        if(r+1 < 8 && c+2 < 8){
            if(isValidMove(r+1,c+2))
                if(Board.getBoard()[r+1][c+2].getFill() == true && Board.getBoard()[r+1][c+2].getPiece().getTeam() != team){
                    Board.getBoard()[r+1][c+2].setBackground(Color.RED);
                }else if(Board.getBoard()[r+1][c+2].getFill() == false){
                    Board.getBoard()[r+1][c+2].setBackground(Color.RED);
                }  
        }
        if(r+1 < 8 && c-2 >= 0){
            if(isValidMove(r+1,c-2))
                if(Board.getBoard()[r+1][c-2].getFill() == true && Board.getBoard()[r+1][c-2].getPiece().getTeam() != team){
                    Board.getBoard()[r+1][c-2].setBackground(Color.RED);
                }else if(Board.getBoard()[r+1][c-2].getFill() == false){
                    Board.getBoard()[r+1][c-2].setBackground(Color.RED);
                }  
        }
        if(r+2 < 8 && c-1 >= 0){
            if(isValidMove(r+2,c-1))
                if(Board.getBoard()[r+2][c-1].getFill() == true && Board.getBoard()[r+2][c-1].getPiece().getTeam() != team){
                    Board.getBoard()[r+2][c-1].setBackground(Color.RED);
                }else if(Board.getBoard()[r+2][c-1].getFill() == false){
                    Board.getBoard()[r+2][c-1].setBackground(Color.RED);
                }  
        }
        if(r+2 < 8 && c+1 < 8){
            if(isValidMove(r+2,c+1))
                if(Board.getBoard()[r+2][c+1].getFill() == true && Board.getBoard()[r+2][c+1].getPiece().getTeam() != team){
                    Board.getBoard()[r+2][c+1].setBackground(Color.RED);
                }else if(Board.getBoard()[r+2][c+1].getFill() == false){
                    Board.getBoard()[r+2][c+1].setBackground(Color.RED);
                }  
        }
    }

    public boolean hasValidMove(){
        if(r-2 >= 0 && c+1 < 8){
            if(isValidMove(r-2,c+1))
                if(Board.getBoard()[r-2][c+1].getFill() == true && Board.getBoard()[r-2][c+1].getPiece().getTeam() != team){
                    return true;
                }else if(Board.getBoard()[r-2][c+1].getFill() == false){
                    return true;
                }  
        }
        if(r-2 >= 0 && c-1 >= 0){
            if(isValidMove(r-2,c-1))
                if(Board.getBoard()[r-2][c-1].getFill() == true && Board.getBoard()[r-2][c-1].getPiece().getTeam() != team){
                    return true;
                }else if(Board.getBoard()[r-2][c-1].getFill() == false){
                    return true;
                }  
        }
        if(r-1 >= 0 && c-2 >= 0){
            if(isValidMove(r-1,c-2))
                if(Board.getBoard()[r-1][c-2].getFill() == true && Board.getBoard()[r-1][c-2].getPiece().getTeam() != team){
                    return true;
                }else if(Board.getBoard()[r-1][c-2].getFill() == false){
                    return true;
                }  
        }
        if(r-1 >= 0 && c+2 < 8){
            if(isValidMove(r-1,c+2))
                if(Board.getBoard()[r-1][c+2].getFill() == true && Board.getBoard()[r-1][c+2].getPiece().getTeam() != team){
                    return true;
                }else if(Board.getBoard()[r-1][c+2].getFill() == false){
                    return true;
                }  
        }
        if(r+1 < 8 && c+2 < 8){
            if(isValidMove(r+1,c+2))
                if(Board.getBoard()[r+1][c+2].getFill() == true && Board.getBoard()[r+1][c+2].getPiece().getTeam() != team){
                    return true;
                }else if(Board.getBoard()[r+1][c+2].getFill() == false){
                    return true;
                }  
        }
        if(r+1 < 8 && c-2 >= 0){
            if(isValidMove(r+1,c-2))
                if(Board.getBoard()[r+1][c-2].getFill() == true && Board.getBoard()[r+1][c-2].getPiece().getTeam() != team){
                    return true;
                }else if(Board.getBoard()[r+1][c-2].getFill() == false){
                    return true;
                }  
        }
        if(r+2 < 8 && c-1 >= 0){
            if(isValidMove(r+2,c-1))
                if(Board.getBoard()[r+2][c-1].getFill() == true && Board.getBoard()[r+2][c-1].getPiece().getTeam() != team){
                    return true;
                }else if(Board.getBoard()[r+2][c-1].getFill() == false){
                    return true;
                }  
        }
        if(r+2 < 8 && c+1 < 8){
            if(isValidMove(r+2,c+1))
                if(Board.getBoard()[r+2][c+1].getFill() == true && Board.getBoard()[r+2][c+1].getPiece().getTeam() != team){
                    return true;
                }else if(Board.getBoard()[r+2][c+1].getFill() == false){
                    return true;
                }  
        }
        return false;
    }

}
