package Defaults;

import javax.swing.JButton;
import javax.swing.ImageIcon;

import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CustomButton extends JButton {
    public CustomButton(String url, String nom, int ... t){
        this.setText(nom);
        this.setBounds(1,t[0],t[1],t[2]);
        this.setFont(new Font("Mv Boli",Font.PLAIN,18));
        this.setFocusable(false);
        this.setBackground(Color.decode("#d6d7d4"));
        this.setForeground(Color.WHITE);
        this.setBorder(new CompoundBorder(new EmptyBorder(1, 1, 1,1 ), new LineBorder(Color.decode("#d6d7d4"), 1)));

        ImageIcon home=new ImageIcon(url);
        Image im=home.getImage().getScaledInstance(32,32, Image.SCALE_SMOOTH);
        home=new ImageIcon(im);
        this.setIcon(home);
        this.setHorizontalTextPosition(JButton.RIGHT);
    }
}
