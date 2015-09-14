/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package room_map;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class roomMap extends JPanel {
static int j,k;
  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    // TODO code application logic here
    JFrame f = new JFrame();
    f.setSize(300, 660);
    f.add(new Room_Map());
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);
    Random rn = new Random();
    while(true) {
      j = (rn.nextInt(8))*30;
      System.out.println("j = " +j);
      k = (rn.nextInt(20))*30;
      System.out.println("k = " +k);
      f.repaint();
    }
  }
  
  public void paint (Graphics g) {
      g.setColor(Color.LIGHT_GRAY);
      g.fillRect (10, 10, 240, 600);
      g.setColor(Color.red);
      int i=0;
      g.fillRect(10 + j, 10 + k, 30, 30);
  }
}