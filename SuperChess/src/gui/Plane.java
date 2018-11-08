package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicArrowButton;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import pieces.*;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSpinner;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;

import java.awt.event.*;
import java.util.List;

public class Plane extends JPanel implements ActionListener, Runnable{
    public static final String dir = "/";
    public static int boardDim = 0;
    protected boolean vis;
    private static String buttonSelection = "Queen";
    private static int inCheck = -1;
    private static int turn;
    private ImageIcon img;
    private static PieceIcon[] whitePieces;
    private static PieceIcon[] blackPieces;
    private static int whiteCount = 0;
    private static int blackCount = 0;
    private static JRadioButtonMenuItem[] button;
    private static JButton confirm;
    private static JPanel announce, whiteWing, blackWing, boardSize;
    public static JPanel promoBoxBlack,promoBoxWhite;
    public static JTextArea output;
    private Timer timerOne, timerTwo;
    private boolean ingame;
    private final int P_WIDTH = 1000;
    private final int P_HEIGHT = 700;
    public static boolean inPromo;
    public static boolean gameOver = false;
    private JFormattedTextField[] ftf = new JFormattedTextField[2];
    private JSpinner[] spinner = new JSpinner[2];
    public Plane(){
        initPlane();
    }

    private void initPlane(){
        button = new JRadioButtonMenuItem[8];
        confirm = new JButton("CONFIRM");
       
        confirm.addActionListener(this);
        confirm.setActionCommand("confirm");
        button[0] = new JRadioButtonMenuItem("Queen");
        button[1] = new JRadioButtonMenuItem("Rook");
        button[2] = new JRadioButtonMenuItem("Knight");
        button[3] = new JRadioButtonMenuItem("Bishop");
        button[4] = new JRadioButtonMenuItem("Queen");
        button[5] = new JRadioButtonMenuItem("Rook");
        button[6] = new JRadioButtonMenuItem("Knight");
        button[7] = new JRadioButtonMenuItem("Bishop");
        announce = new JPanel();
        whiteWing = new JPanel();
        blackWing = new JPanel();
        output = new JTextArea();
        promoBoxWhite = new JPanel();
        promoBoxWhite.setLayout(new BoxLayout(promoBoxWhite,BoxLayout.Y_AXIS));
        promoBoxWhite.setFocusable(false);
        promoBoxBlack = new JPanel();
        promoBoxBlack.setLayout(new BoxLayout(promoBoxBlack,BoxLayout.Y_AXIS));
        promoBoxBlack.setFocusable(false);
        for(int i = 0;i < 8;i++)
        {
            button[i].setFont(new Font("Monospaced", Font.BOLD, 20));
            button[i].setPreferredSize(new Dimension(15,15));
            button[i].addActionListener(this);
            button[i].setActionCommand(""+button[i].getText()+i);
        }
        for(int i = 0;i < 4;i++){
            promoBoxBlack.add(button[i]);
            if(i==0)
                setButtonPic(button[i],dir + "res/images/BlackQueen.png");
            else if(i==1)
                setButtonPic(button[i],dir + "res/images/BlackRook.png");
            else if(i==2)
                setButtonPic(button[i],dir + "res/images/BlackKnight.png");
            else
                setButtonPic(button[i],dir + "res/images/BlackBishop.png");
        }
        for(int i = 4;i < 8;i++){
            promoBoxWhite.add(button[i]);
            if(i==4)
                setButtonPic(button[i],dir + "res/images/WhiteQueen.png");
            else if(i==5)
                setButtonPic(button[i],dir + "res/images/WhiteRook.png");
            else if(i==6)
                setButtonPic(button[i],dir + "res/images/WhiteKnight.png");
            else
                setButtonPic(button[i],dir + "res/images/WhiteBishop.png");
        }
        promoBoxBlack.add(confirm);
        output.setBackground(Color.LIGHT_GRAY);
        output.setFocusable(false);
        output.setEditable(false);

        announce.add(output,BorderLayout.LINE_START);
        whitePieces = new PieceIcon[20];
        blackPieces = new PieceIcon[20];
        setFocusable(true);
        setBackground(Color.LIGHT_GRAY);
        ingame = true;
        setPreferredSize(new Dimension(P_WIDTH, P_HEIGHT));
        add(promoBoxBlack);
        add(blackWing,BorderLayout.LINE_START);
        add(new Board(), BorderLayout.CENTER);
        add(promoBoxWhite);
        add(whiteWing,BorderLayout.LINE_END);
        add(announce,BorderLayout.PAGE_END);

        announce.setPreferredSize(new Dimension(Board.B_WIDTH, 50));
        output.setPreferredSize(new Dimension(Board.B_WIDTH, 50));
        promoBoxWhite.setPreferredSize(new Dimension(175, Board.B_HEIGHT));
        promoBoxBlack.setPreferredSize(new Dimension(175, Board.B_HEIGHT));
        whiteWing.setPreferredSize(new Dimension(150, Board.B_HEIGHT));
        blackWing.setPreferredSize(new Dimension(150, Board.B_HEIGHT));
        confirm.setPreferredSize(new Dimension(550,50));
        confirm.setFont(new Font("Monospaced", Font.BOLD, 34));
        output.setBackground(Color.LIGHT_GRAY);
        output.setForeground(Color.BLACK);
        output.setFont(new Font("Monospaced", Font.BOLD, 20));
        announce.setBackground(Color.LIGHT_GRAY);
        whiteWing.setBackground(Color.LIGHT_GRAY);
        blackWing.setBackground(Color.LIGHT_GRAY);
        promoBoxWhite.setBackground(Color.LIGHT_GRAY);
        promoBoxBlack.setBackground(Color.LIGHT_GRAY);
        promoBoxWhite.setVisible(false);
        promoBoxBlack.setVisible(false);
        output.setText("White's Turn");
    }

    public static void addWhite(Piece p){
        whitePieces[whiteCount] = new PieceIcon();
        whitePieces[whiteCount].addPiece(p.getImage());
        whiteWing.add(whitePieces[whiteCount]);
        whiteCount++;
    }

    public static String getButtonSelection(){
        return buttonSelection;
    }

    public static void getPromo(int t){
        buttonSelection = "Queen";
        inPromo = true;
        Space.free = false;
        if(t == 0){
            whiteWing.setVisible(false);
            promoBoxWhite.setVisible(true);
            promoBoxWhite.add(confirm);
        }else{
            blackWing.setVisible(false);
            promoBoxBlack.setVisible(true);
            promoBoxBlack.add(confirm);
        }
    }

    public static void addBlack(Piece p){
        blackPieces[blackCount] = new PieceIcon();
        blackPieces[blackCount].addPiece(p.getImage());
        blackWing.add(blackPieces[blackCount]);
        blackCount++;
    }

    public static int getTurn(){
        return turn;
    }

    public static int getCheck(){
        boolean none = true;
        for(int r = 0;r<Board.getBoard().length;r++){
            for(int c = 0;c<Board.getBoard()[0].length;c++){
                if(Board.getBoard()[r][c].getFill() == true && Board.getBoard()[r][c].getPiece() instanceof King){
                    if(Board.getBoard()[r][c].getPiece().getTeam() == 0 && Board.getBoard()[r][c].dangerBlack() == true){
                        inCheck = 0;
                        none = false;
                        break;
                    }else if(Board.getBoard()[r][c].getPiece().getTeam() == 1 && Board.getBoard()[r][c].dangerWhite() == true){
                        inCheck = 1;
                        none = false;
                        break;
                    }
                }
            }
        }
        if(none)
            inCheck = -1;
        return inCheck;
    }

    private static void resetEnPass(){
        for(int r = 0;r<8;r++){
            for(int c = 0;c<8;c++){
                if(Board.getBoard()[r][c].getFill() && Board.getBoard()[r][c].getPiece() instanceof Pawn && Board.getBoard()[r][c].getPiece().getTeam() == turn)
                    ((Pawn)Board.getBoard()[r][c].getPiece()).setEnPass(false);
            }
        }
    }

    public static void switchTurns(){
        if(turn == 0 && inPromo == true){
            turn = 1;
            output.setText("White's Promotion");
        }
        else if(turn == 1 && inPromo == true){
            turn = 0;
            output.setText("Black's Promotion");
        }
        else if(turn == 0 && inPromo == false){
            turn = 1;
            if(!endGame())
                if(getCheck() == -1)
                    output.setText("Black's Turn");
                else
                    output.setText("Black in Check");
        }
        else{
            turn = 0;
            if(!endGame())
                if(getCheck() == -1)
                    output.setText("White's Turn");
                else
                    output.setText("White in Check");
        }
        resetEnPass();
    }

    public static void switchTurns(int turn){
        if(turn == 0){
            turn = 1;
            if(!endGame())
                if(getCheck() == -1)
                    output.setText("Black's Turn");
                else
                    output.setText("Black in Check");
        }
        else{
            turn = 0;
            if(!endGame())
                if(getCheck() == -1)
                    output.setText("White's Turn");
                else
                    output.setText("White in Check");
        }
        resetEnPass();
    }

    public static boolean endGame(){
        for(int r = 0;r<8;r++){
            for(int c = 0;c<8;c++){
                if(Board.getBoard()[r][c].getFill() && Board.getBoard()[r][c].getPiece().getTeam() == getTurn() && Board.getBoard()[r][c].getPiece().hasValidMove() == true){
                    return false;
                }
            }
        }
        gameOver = true;
        for(int r = 0;r<8;r++){
            for(int c = 0;c<8;c++){
                if(Board.getBoard()[r][c].getFill() && Board.getBoard()[r][c].getPiece() instanceof King && Board.getBoard()[r][c].getPiece().getTeam() == getCheck())
                    Board.getBoard()[r][c].setBackground(Color.RED);
            }
        }
        if(getCheck() == -1){
            output.setText("Stalemate!");
            return true;
        } else if(getCheck() == 0){
            output.setText("Checkmate: BLACK WINS!");
            return true;
        } else{
            output.setText("Checkmate: WHITE WINS!");
            return true;
        }

    }
    

    private void inGame() {
        if (!ingame) {
            timerOne.stop();
            timerTwo.stop();
        }
    }

    private void setButtonPic(AbstractButton b,String s){
        try{
        	img = new ImageIcon(Plane.class.getResource(s));
        }catch(Exception e){System.out.println("fail");}
        b.setIcon(img);
    }

    
    public void actionPerformed(ActionEvent e) {
        if(!(e.getActionCommand().equals("confirm")))
            for(int i = 0;i<button.length;i++){
                if(!(e.getActionCommand().equals(""+button[i].getText()+i)))
                    button[i].setSelected(false);
                else if(e.getActionCommand().equals(""+button[i].getText()+i) && button[i].isSelected() == false)
                	buttonSelection = button[0].getText();
                else{
                    buttonSelection = button[i].getText();
                }
            }
        if(e.getActionCommand().equals("settings"))
        	boardSize.setVisible(false);
        	
        else{
            Space.free = true;
            inPromo = false;
            confirm.setSelected(false);
            promoBoxBlack.setVisible(false);
            promoBoxWhite.setVisible(false);
            whiteWing.setVisible(true);
            blackWing.setVisible(true);
            for(int i = 0;i<button.length;i++){
                button[i].setSelected(false);
            }
        }
    }
    
    @SuppressWarnings("serial")
	class SpinnerCircularListModel extends SpinnerListModel {
    	  public SpinnerCircularListModel(Object[] items) {
    	    super(items);
    	  }

    	  public Object getNextValue() {
    	    List list = getList();
    	    int index = list.indexOf(getValue());

    	    index = (index >= list.size() - 1) ? 0 : index + 1;
    	    return list.get(index);
    	  }

    	  public Object getPreviousValue() {
    	    List list = getList();
    	    int index = list.indexOf(getValue());

    	    index = (index <= 0) ? list.size() - 1 : index - 1;
    	    return list.get(index);
    	  }
    	}

	@Override
	public void run() {
		
		
	}

	
}
