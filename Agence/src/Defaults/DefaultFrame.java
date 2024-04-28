package Defaults;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Color;

public class DefaultFrame extends JFrame {
    public DefaultFrame(int w,int h){
        final ImageIcon image=new ImageIcon("src\\image\\car_48px.png");
       this.setSize(w,h);
       this.setTitle("Rent");
       this.setResizable(false);
       this.getContentPane().setBackground(Color.decode("#d6d7d4"));
       this.setLayout(null);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.setIconImage(image.getImage().getScaledInstance(200,200, Image.SCALE_SMOOTH));
       this.setLocationRelativeTo(null);
    }



}
