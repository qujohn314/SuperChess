package gui;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import pieces.*;
import javax.swing.border.*;
public class Space extends JPanel implements Clickable, Runnable
{
    private ImageIcon pic;
    private JLabel picLabel;
    private ArrayList<Piece> dangerPieces;
    private JPanel panel;
    private Border border;
    private Piece[] piece;
    public Color origColor;
    
    public static volatile boolean free = false;
    public static final int S_HEIGHT = (Board.B_WIDTH / 8) - 5;
    public static final int S_WIDTH = (Board.B_WIDTH / 8) - 5;
    public static final Object monitor = new Object();
    
    public static boolean move, promoRan, monitorState;
    private boolean filled,danger,clickable;
    public static int finalR,promoR,finalC,promoC;
    private int r, c;
    public Space(Color color,int x, int y){
        r = x;
        c = y;
        origColor = color;
        piece = new Piece[1]; 
        filled = false;
        clickable = false;
        setFocusable(true);
        setBackground(color);
        dangerPieces = new ArrayList<Piece>();
        setPreferredSize(new Dimension(S_WIDTH, S_HEIGHT));
        addMouseListener(new MouseAdapter() { 
                public void mousePressed(MouseEvent me) {
                    if(!Plane.gameOver && !Plane.inPromo){
                        if(getBackground() == Color.WHITE || getBackground() == Color.LIGHT_GRAY){
                            Board.updateBoard();
                        }
                        updateFill();
                        updateClickable();
                        isClicked(); 
                    }
                }
            });
    }

    public void run(){
        while(!free || !promoRan){
            if (free){
                if(Board.getBoard()[promoR][promoC].getPiece() instanceof Pawn && Board.getBoard()[promoR][promoC].getPiece().getTeam() == 0){
                    Board.getBoard()[promoR][promoC].removePiece();
                    if(Plane.getButtonSelection().equals("Queen"))
                        Board.getBoard()[promoR][promoC].setPiece(new Queen(promoR,promoC,0));
                    if(Plane.getButtonSelection().equals("Knight"))
                        Board.getBoard()[promoR][promoC].setPiece(new Knight(promoR,promoC,0));
                    if(Plane.getButtonSelection().equals("Bishop"))
                        Board.getBoard()[promoR][promoC].setPiece(new Bishop(promoR,promoC,0));
                    if(Plane.getButtonSelection().equals("Rook"))
                        Board.getBoard()[promoR][promoC].setPiece(new Rook(promoR,promoC,0)); 
                }
                if(Board.getBoard()[promoR][promoC].getPiece() instanceof Pawn && Board.getBoard()[promoR][promoC].getPiece().getTeam() == 1){
                    Board.getBoard()[promoR][promoC].removePiece();
                    if(Plane.getButtonSelection().equals("Queen"))
                        Board.getBoard()[promoR][promoC].setPiece(new Queen(promoR,promoC,1));
                    if(Plane.getButtonSelection().equals("Knight"))
                        Board.getBoard()[promoR][promoC].setPiece(new Knight(promoR,promoC,1));
                    if(Plane.getButtonSelection().equals("Bishop"))
                        Board.getBoard()[promoR][promoC].setPiece(new Bishop(promoR,promoC,1));
                    if(Plane.getButtonSelection().equals("Rook"))
                        Board.getBoard()[promoR][promoC].setPiece(new Rook(promoR,promoC,1));
                }
                Board.updateBoard();
                promoRan = true;
            }
        }
        Plane.switchTurns(Board.getBoard()[promoR][promoC].getPiece().getTeam());
        promoRan = false;
        free = false;
    }

    public void updateClickable(){
        if(filled && piece[0].getTeam() == Plane.getTurn())
            clickable = true;
        else
            clickable = false;
    }

    public void setDanger(Piece p){
        dangerPieces.add(p);
    }

    public boolean dangerWhite(){
        for(int i = 0;i<dangerPieces.size();i++){
            if(dangerPieces.get(i).getTeam() == 0){
                return true;
            }  
        }
        return false;
    }

    public boolean dangerBlack(){
        for(int i = 0;i<dangerPieces.size();i++){
            if(dangerPieces.get(i).getTeam() != 0){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Piece> getDangerPieces(){
        return dangerPieces;
    }

    public Piece getDangerPiece(int teamColor){
        for(int i = 0;i<dangerPieces.size();i++){
            if(dangerPieces.get(i).getTeam() == teamColor){
                return dangerPieces.get(i);
            }
        }
        return null;
    }

    public int getDangerCount(int teamColor){
        int count = 0;
        for(int i = 0;i<dangerPieces.size();i++){
            if(dangerPieces.get(i).getTeam() == teamColor){
                count++;
            }
        }
        return count;
    }

    public boolean getDanger(){
        if(Board.getBoard()[finalR][finalC].getPiece().getTeam() == 1 && dangerWhite() == true){
            return true;
        }
        if(Board.getBoard()[finalR][finalC].getPiece().getTeam() == 0 && dangerBlack() == true){
            return true;
        }
        return false;
    }

    public void removeDanger(){
        dangerPieces.clear();
    }

    public void setFakePiece(Piece p){
        piece[0] = p;
        updateFill();
    }

    public void setPiece(Piece p){
        piece[0] = p;
        try{
            pic = new ImageIcon(Space.class.getResource(p.getImage()));
        }catch(Exception e){}
        JLabel picLabel = new JLabel(pic);
        add(picLabel);
        this.revalidate();
    }

    public Piece getPiece(){
        return piece[0];
    }

    public void moveFakePiece(int rr,int cc){
        Board.getDangerBoard()[rr][cc].setFakePiece(null);
        Board.getDangerBoard()[rr][cc].removeAll();
        Board.getDangerBoard()[rr][cc].setFakePiece(Board.getDangerBoard()[r][c].getPiece());
        Board.getDangerBoard()[rr][cc].getPiece().setCoords(rr,cc);
        piece[0] = null;
        updateFill();
        Board.printDangerBoard();
    }

    private void movePiece(){
        if(Board.getBoard()[r][c].getFill() == true){
            Board.getBoard()[r][c].getPiece().getCaptured();
        }
        Board.getBoard()[r][c].removePiece();
        Board.getBoard()[r][c].setPiece(Board.getBoard()[finalR][finalC].getPiece());
        Board.getBoard()[finalR][finalC].removePiece();
        Board.getBoard()[r][c].getPiece().setCoords(r,c);
        if(Board.getBoard()[r][c].getPiece() instanceof Pawn && (finalR == r - 2 || finalR == r + 2)){
            if((c-1 >= 0 && Board.getBoard()[r][c-1].getFill() && Board.getBoard()[r][c-1].getPiece() instanceof Pawn) || (c+1<8 && Board.getBoard()[r][c+1].getFill() && Board.getBoard()[r][c+1].getPiece() instanceof Pawn))
            {
                ((Pawn)Board.getBoard()[r][c].getPiece()).setEnPass(true);
            }
        }
        if(Board.getBoard()[r][c].getPiece().getTeam() == 0 && Board.getBoard()[r][c].getPiece() instanceof Pawn && Board.getBoard()[r+1][c].getPiece() instanceof Pawn && ((Pawn)Board.getBoard()[r+1][c].getPiece()).getEnPass())
            Board.getBoard()[r+1][c].removePiece();
        if(Board.getBoard()[r][c].getPiece().getTeam() == 1 && Board.getBoard()[r][c].getPiece() instanceof Pawn && Board.getBoard()[r-1][c].getPiece() instanceof Pawn && ((Pawn)Board.getBoard()[r-1][c].getPiece()).getEnPass())
            Board.getBoard()[r-1][c].removePiece();
        if(Board.getBoard()[r][c].getPiece() instanceof Pawn || Board.getBoard()[r][c].getPiece() instanceof Rook || Board.getBoard()[r][c].getPiece() instanceof King)
            (piece[0]).setMove(true);
        if(Board.getBoard()[r][c].getPiece() instanceof Pawn && Board.getBoard()[r][c].getPiece().getTeam() == 0 && r == 0){
            Plane.getPromo(Board.getBoard()[r][c].getPiece().getTeam());
            promoR = r;
            promoC = c;
            (new Thread(this)).start();
        }
        if(Board.getBoard()[r][c].getPiece() instanceof Pawn && Board.getBoard()[r][c].getPiece().getTeam() == 1 && r == 7){
            Plane.getPromo(Board.getBoard()[r][c].getPiece().getTeam());
            promoR = r;
            promoC = c;
            (new Thread(this)).start();
        }
        move = false;
        Board.updateBoard();
        Plane.switchTurns();
    }

    public static void waitForThread() {
        monitorState = true;
        while (monitorState) {
            synchronized (monitor) {
                try {
                    monitor.wait(); // wait until notified
                } catch (Exception e) {}
            }
        }
    }

    public static void unlockWaiter() {
        synchronized (monitor) {
            monitorState = false;
            monitor.notifyAll(); // unlock again
        }
    }

    public void removePiece(){
        Board.updateBoard();
        piece[0] = null;
        this.removeAll(); 
        this.setVisible(false);
        this.setVisible(true);
    }

    public boolean getFill(){
        return filled;
    }

    public void updateFill(){
        if(piece[0] != null){
            filled = true;
        }else
            filled = false;
    }

    public void isClicked(){
        Piece p = piece[0];
        if(clickable && move == false){
            setBackground(Color.ORANGE);
            move = true;
            finalR = r;
            finalC = c;
            p.getMoves();
        }else if(move == true && getBackground() != Color.ORANGE && getBackground() != Color.WHITE && getBackground() != Color.LIGHT_GRAY){
            if(getBackground() == Color.BLUE && c == 6){
                Board.getBoard()[r][7].removePiece();
                Board.getBoard()[r][5].setPiece(new Rook(r,5,Board.getBoard()[finalR][finalC].getPiece().getTeam()));
            }else if(getBackground() == Color.BLUE && c == 2){
                Board.getBoard()[r][0].removePiece();
                Board.getBoard()[r][3].setPiece(new Rook(r,3,Board.getBoard()[finalR][finalC].getPiece().getTeam()));
            }
            movePiece();
            move = false; 
        }
        else if(move == true && getBackground() == Color.ORANGE || getBackground() == Color.WHITE || getBackground() == Color.LIGHT_GRAY){
            Board.updateBoard();
            move = false;
        }
    }
}
