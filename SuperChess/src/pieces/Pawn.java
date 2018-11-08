package pieces;
import java.awt.Color;
import gui.Board;
public class Pawn extends Piece
{
    private boolean canEnPass;
    public Pawn(int r, int c,int t){
        super(r,c,t);
        if(team == 1)
            loadImage(dir + "res/images/BlackPawn.png");
        else
            loadImage(dir + "res/images/WhitePawn.png");
        canEnPass = false;
    }

    public void updateSpaces(){
        if(team == 0 && c < 7 && r > 0){
            Board.getBoard()[r-1][c+1].setDanger(this);
        }
        if(team == 0 && c > 0 && r > 0){
            Board.getBoard()[r-1][c-1].setDanger(this);
        }
        if(team == 1 && c < 7 && r < 7){
            Board.getBoard()[r+1][c+1].setDanger(this);
        }
        if(team == 1 && c > 0 && r < 7){
            Board.getBoard()[r+1][c-1].setDanger(this);
        }
    }

    public void updateFakeSpaces(){
        if(team == 0 && c < 7 && r > 0){
            Board.getDangerBoard()[r-1][c+1].setDanger(this);
        }
        if(team == 0 && c > 0 && r > 0){
            Board.getDangerBoard()[r-1][c-1].setDanger(this);
        }
        if(team == 1 && c < 7 && r < 7){
            Board.getDangerBoard()[r+1][c+1].setDanger(this);
        }
        if(team == 1 && c > 0 && r < 7){
            Board.getDangerBoard()[r+1][c-1].setDanger(this);
        }
    }

    public void getMoves(){
        if(!hasMoved){
            if(team == 0)
                for(int rr = r-1;rr>=r-2;rr--){
                    if(isValidMove(rr,c))
                        if(Board.getBoard()[rr][c].getFill() == true)
                            break;
                        else
                            Board.getBoard()[rr][c].setBackground(Color.RED);
                    else if(Board.getBoard()[rr][c].getFill() == true)
                        break;
                }
            else if(team == 1)
                for(int rr = r+1;rr<=r+2;rr++){
                    if(isValidMove(rr,c))
                        if(Board.getBoard()[rr][c].getFill() == true)
                            break;
                        else
                            Board.getBoard()[rr][c].setBackground(Color.RED);
                    else if(Board.getBoard()[rr][c].getFill() == true)
                        break;
                }
            diagKill();
        }else{
            if(team == 0)
                for(int rr = r-1;rr>=r-1;rr--){
                    if(isValidMove(rr,c))
                        if(Board.getBoard()[rr][c].getFill() == true)
                            break;
                        else
                            Board.getBoard()[rr][c].setBackground(Color.RED);
                    else
                        break;
                }
            else if(team == 1)
                for(int rr = r+1;rr<=r+1;rr++){
                    if(isValidMove(rr,c))
                        if(Board.getBoard()[rr][c].getFill() == true)
                            break;
                        else
                            Board.getBoard()[rr][c].setBackground(Color.RED);
                    else
                        break;
                }
            if(c-1 >= 0 && Board.getBoard()[r][c-1].getFill() && Board.getBoard()[r][c-1].getPiece() instanceof Pawn && ((Pawn)Board.getBoard()[r][c-1].getPiece()).getEnPass() == true){
                if(isValidMove(r-1,c-1) && team == 0)
                    Board.getBoard()[r-1][c-1].setBackground(Color.RED);
                if(isValidMove(r+1,c-1) && team == 1)
                    Board.getBoard()[r+1][c-1].setBackground(Color.RED);
            }
            if(c+1 < 8 && Board.getBoard()[r][c+1].getFill() && Board.getBoard()[r][c+1].getPiece() instanceof Pawn && ((Pawn)Board.getBoard()[r][c+1].getPiece()).getEnPass() == true){
                if(isValidMove(r-1,c+1) && team == 0)
                    Board.getBoard()[r-1][c+1].setBackground(Color.RED);
                if(isValidMove(r+1,c+1) && team == 1)
                    Board.getBoard()[r+1][c+1].setBackground(Color.RED);
            }
            diagKill();
        }
    }

    public void setEnPass(boolean b){
        canEnPass = b;
    }

    public boolean getEnPass(){
        return canEnPass;
    }

    private void diagKill(){
        if(team == 0 && c < 7 && Board.getBoard()[r-1][c+1].getFill() == true && Board.getBoard()[r-1][c+1].getPiece().getTeam() != team){
            if(isValidMove(r-1,c+1))
                Board.getBoard()[r-1][c+1].setBackground(Color.MAGENTA);
        }
        if(team == 0 && c > 0 && Board.getBoard()[r-1][c-1].getFill() == true &&Board.getBoard()[r-1][c-1].getPiece().getTeam() != team){
            if(isValidMove(r-1,c-1))
                Board.getBoard()[r-1][c-1].setBackground(Color.MAGENTA);
        }
        if(team == 1 && c < 7 && Board.getBoard()[r+1][c+1].getFill() == true &&Board.getBoard()[r+1][c+1].getPiece().getTeam() != team){
            if(isValidMove(r+1,c+1))
                Board.getBoard()[r+1][c+1].setBackground(Color.MAGENTA);
        }
        if(team == 1 && c > 0 && Board.getBoard()[r+1][c-1].getFill() == true &&Board.getBoard()[r+1][c-1].getPiece().getTeam() != team){
            if(isValidMove(r+1,c-1))
                Board.getBoard()[r+1][c-1].setBackground(Color.MAGENTA);   
        }
    } 

    public boolean hasValidMove(){
        if(team == 0 && c < 7 && Board.getBoard()[r-1][c+1].getFill() == true && Board.getBoard()[r-1][c+1].getPiece().getTeam() != team){
            if(isValidMove(r-1,c+1))
                return true;
        }
        if(team == 0 && c > 0 && Board.getBoard()[r-1][c-1].getFill() == true &&Board.getBoard()[r-1][c-1].getPiece().getTeam() != team){
            if(isValidMove(r-1,c-1))
                return true;
        }
        if(team == 1 && c < 7 && Board.getBoard()[r+1][c+1].getFill() == true &&Board.getBoard()[r+1][c+1].getPiece().getTeam() != team){
            if(isValidMove(r+1,c+1))
                return true;
        }
        if(team == 1 && c > 0 && Board.getBoard()[r+1][c-1].getFill() == true &&Board.getBoard()[r+1][c-1].getPiece().getTeam() != team){
            if(isValidMove(r+1,c-1))
                return true;  
        }
        if(!hasMoved){
            if(team == 0)
                for(int rr = r-1;rr>=r-2;rr--){
                    if(isValidMove(rr,c))
                        if(Board.getBoard()[rr][c].getFill() == true)
                            break;
                        else
                            return true;
                }
            else if(team == 1)
                for(int rr = r+1;rr<=r+2;rr++){
                    if(isValidMove(rr,c))
                        if(Board.getBoard()[rr][c].getFill() == true)
                            break;
                        else
                            return true;
                }
        }else{
            if(team == 0)
                for(int rr = r-1;rr>=r-1;rr--){
                    if(isValidMove(rr,c))
                        if(Board.getBoard()[rr][c].getFill() == true)
                            break;
                        else
                            return true;
                }
            else if(team == 1)
                for(int rr = r+1;rr<=r+1;rr++){
                    if(isValidMove(rr,c))
                        if(Board.getBoard()[rr][c].getFill() == true)
                            break;
                        else
                            return true;
                }
        }
        return false;
    }

}
