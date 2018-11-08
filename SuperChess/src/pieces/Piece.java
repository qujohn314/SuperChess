package pieces;
import gui.Board;
import gui.Plane;
import gui.Space;
public abstract class Piece
{
	protected static final String dir = Plane.dir;
    protected int team;
    protected int r;
    protected int c;
    protected String image;
    protected boolean hasMoved;
    public Piece(int x, int y, int t){
        r = x;
        c = y;
        team = t;
    }

    public abstract void getMoves();

    public void getCaptured(){
        if(team == 0)
            Plane.addWhite(this);
        else
            Plane.addBlack(this);
    }

    public abstract boolean hasValidMove();

    public abstract void updateSpaces();

    public abstract void updateFakeSpaces();

    protected void loadImage(String imageName) {
        image = imageName;
    }

    public String getImage() {
        return image;
    }

    public Piece clonePiece(){
        if(this instanceof Pawn)
            return new Pawn(r,c,team);
        if(this instanceof Knight)
            return new Knight(r,c,team);
        if(this instanceof Rook)
            return new Rook(r,c,team);
        if(this instanceof King)
            return new King(r,c,team);
        if(this instanceof Queen)
            return new Queen(r,c,team);
        if(this instanceof Bishop)
            return new Bishop(r,c,team);
        return new Pawn(r,c,team);
    }

    public void setCoords(int x, int y){
        r = x;
        c = y;
    }

    protected boolean isValidMove(int rr, int cc){
        Board.updateDangerBoard();
        Space whiteKing = null;
        Space blackKing = null;
        Board.getDangerBoard()[r][c].moveFakePiece(rr,cc);
        for(int rrr = 0;rrr<8;rrr++){
            for(int ccc = 0;ccc<8;ccc++){
                Board.getDangerBoard()[rrr][ccc].removeDanger();
            }
        }
        for(int rrr = 0;rrr<8;rrr++){
            for(int ccc = 0;ccc<8;ccc++){
                if(Board.getDangerBoard()[rrr][ccc].getFill() == true)
                    Board.getDangerBoard()[rrr][ccc].getPiece().updateFakeSpaces();
            } 
        }
        for(int rrr = 0;rrr<8;rrr++){
            for(int ccc = 0;ccc<8;ccc++){
                Board.getDangerBoard()[rrr][ccc].updateFill();
            } 
        } 
        for(int rrr = 0;rrr<8;rrr++){
            for(int ccc = 0;ccc<8;ccc++){
                if(Board.getDangerBoard()[rrr][ccc].getFill() && Board.getDangerBoard()[rrr][ccc].getPiece() instanceof King && Board.getDangerBoard()[rrr][ccc].getPiece().getTeam() == 0)
                    whiteKing = Board.getDangerBoard()[rrr][ccc];
            }
        }
        for(int rrr = 0;rrr<8;rrr++){
            for(int ccc = 0;ccc<8;ccc++){
                if(Board.getDangerBoard()[rrr][ccc].getFill() && Board.getDangerBoard()[rrr][ccc].getPiece() instanceof King && Board.getDangerBoard()[rrr][ccc].getPiece().getTeam() == 1)
                    blackKing = Board.getDangerBoard()[rrr][ccc];
            }
        }
        if(Plane.getTurn() == 1 && blackKing != null && !blackKing.dangerWhite())
            return true;
        if(Plane.getTurn() == 0 && whiteKing != null && !whiteKing.dangerBlack())
            return true;
        return false;
    }

    public void setMove(boolean t){
        hasMoved = t;
    }

    public boolean getMove(){
        return hasMoved;
    }

    public int getTeam(){
        return team;
    }
}
