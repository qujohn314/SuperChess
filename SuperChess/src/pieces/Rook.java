package pieces;
import gui.Board;
import java.awt.Color;
public class Rook extends Piece
{  
	
	private Moves[] moves;
	
    public Rook(int r, int c,int t){
        super(r,c,t);
        moves = new Moves[] {Moves.Move.Orthogonal(this,0,0,0,0)};
        if(team == 1)
            loadImage(dir + "res/images/BlackRook.png");
        else
            loadImage(dir + "res/images/WhiteRook.png");
    }

    public void getMoves(){
        for(int rr = r-1;rr>=0;rr--){
            if(isValidMove(rr,c))
                if(Board.getBoard()[rr][c].getFill() == true){
                    if(Board.getBoard()[rr][c].getPiece().getTeam() != team){
                        Board.getBoard()[rr][c].setBackground(Color.RED);
                    }
                    break;
                }else{
                    Board.getBoard()[rr][c].setBackground(Color.RED);
                }
            else if(Board.getBoard()[rr][c].getFill() == true)
                break;
        }
        for(int rr = r+1;rr<8;rr++){
            if(isValidMove(rr,c)){
                if(Board.getBoard()[rr][c].getFill() == true){
                    if(Board.getBoard()[rr][c].getPiece().getTeam() != team){
                        Board.getBoard()[rr][c].setBackground(Color.RED);
                    }
                    break;
                }
                else{
                    Board.getBoard()[rr][c].setBackground(Color.RED);
                }
            }else if(Board.getBoard()[rr][c].getFill() == true)
                break;
        }
        for(int cc = c-1;cc>=0;cc--){
            if(isValidMove(r,cc)){
                if(Board.getBoard()[r][cc].getFill() == true){
                    if(Board.getBoard()[r][cc].getPiece().getTeam() != team){
                        Board.getBoard()[r][cc].setBackground(Color.RED);
                    }
                    break;
                }else{
                    Board.getBoard()[r][cc].setBackground(Color.RED);
                }
            }else if(Board.getBoard()[r][cc].getFill() == true)
                break;
        }
        for(int cc = c+1;cc<8;cc++){
            if(isValidMove(r,cc)){
                if(Board.getBoard()[r][cc].getFill() == true){
                    if(Board.getBoard()[r][cc].getPiece().getTeam() != team){
                        Board.getBoard()[r][cc].setBackground(Color.RED);
                    }
                    break;
                }else{
                    Board.getBoard()[r][cc].setBackground(Color.RED);
                }
            }else if(Board.getBoard()[r][cc].getFill() == true)
                break;
        }
    }

    public boolean hasValidMove(){
        for(int rr = r-1;rr>=0;rr--){
            if(isValidMove(rr,c))
                if(Board.getBoard()[rr][c].getFill() == true){
                    if(Board.getBoard()[rr][c].getPiece().getTeam() != team){
                        return true;
                    }
                    break;
                }else{
                    return true;
                }
            else if(Board.getBoard()[rr][c].getFill() == true)
                break;
        }
        for(int rr = r+1;rr<8;rr++){
            if(isValidMove(rr,c))
                if(Board.getBoard()[rr][c].getFill() == true){
                    if(Board.getBoard()[rr][c].getPiece().getTeam() != team){
                        return true;
                    }
                    break;
                }else{
                    return true;
                }
            else if(Board.getBoard()[rr][c].getFill() == true)
                break;
        }
        for(int cc = c-1;cc>=0;cc--){
            if(isValidMove(r,cc))
                if(Board.getBoard()[r][cc].getFill() == true){
                    if(Board.getBoard()[r][cc].getPiece().getTeam() != team){
                        return true;
                    }
                    break;
                }else{
                    return true;
                }
            else if(Board.getBoard()[r][cc].getFill() == true)
                break;
        }
        for(int cc = c+1;cc<8;cc++){
            if(isValidMove(r,cc))
                if(Board.getBoard()[r][cc].getFill() == true){
                    if(Board.getBoard()[r][cc].getPiece().getTeam() != team){
                        return true;
                    }
                    break;
                }else{
                    return true;
                }
            else if(Board.getBoard()[r][cc].getFill() == true)
                break;
        }
        return false;
    }

    public void updateSpaces(){
        moves[0].updateMoves(Board.staticBoard);
    }

    public void updateFakeSpaces(){
      moves[0].updateFakeMoves(Board.staticBoard);
    }
}
