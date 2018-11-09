package pieces;
import gui.Board;
import java.awt.Color;
public class Rook extends Piece
{  
	
	private Moves[] moves;
	
    public Rook(int r, int c,int t){
        super(r,c,t);
        moves = new Moves[] {Moves.Move.Orthogonal(this,2,2,2,2)};
        if(team == 1)
            loadImage(dir + "res/images/BlackRook.png");
        else
            loadImage(dir + "res/images/WhiteRook.png");
    }

    public void getMoves(){
        moves[0].getMoves(Board.staticBoard);
    }

    public boolean hasValidMove(){
        return moves[0].validSpaces();
    }

    public void updateSpaces(){
        moves[0].updateMoves(Board.staticBoard);
    }

    public void updateFakeSpaces(){
      moves[0].updateFakeMoves(Board.staticBoard);
    }
}
