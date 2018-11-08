// AUTHOR: Quincy T. Johnson
// Update Version 1.0.0
// Date of Release 04/06/18
// GIVE CREDIT! DON'T DELETE COMMENTS!

import java.util.concurrent.TimeUnit;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.io.File;
import java.awt.event.*;
import gui.Plane;
public class Runner extends JFrame
{
  public JFrame frame;
  public BoxLayout layout;
  public JPanel field;
  public Runner()
    {
        init();
    }
    
    public static void main(String[]args)
    {
     
         EventQueue.invokeLater(new Runnable() 
        {
          @Override
          public void run(){
        	  
              Runner r = new Runner();
              r.setVisible(true);
          }
        });
  }
  
  public void init(){
	  ImageIcon pic = new ImageIcon(getClass().getResource("/res/images/WhitePawn.png"));
	  setIconImage(pic.getImage());
      add(new Plane());
      setResizable(false);
      pack();
      setTitle("Chess v1.0.0");
      setLocationRelativeTo(null);
  }
}
