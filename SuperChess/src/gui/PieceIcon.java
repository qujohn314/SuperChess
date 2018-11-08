package gui;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


import javax.swing.*;
import java.awt.image.*;
import java.awt.Color;
import java.awt.Dimension;
import pieces.*;
public class PieceIcon extends JPanel
{
    public static final int HEIGHT = 60;
    public static final int WIDTH = 60;
    private BufferedImage pic;
    private JLabel picLabel;
    public PieceIcon(){
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.LIGHT_GRAY);
    }

    public void addPiece(String s){

        try{
           pic = ImageIO.read(new File(s));
        }catch(Exception e){}
        JLabel picLabel = new JLabel(new ImageIcon(PieceIcon.class.getResource(s)));
        add(picLabel);
        this.revalidate();
    }
    
   
    
}


