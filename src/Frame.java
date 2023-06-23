import javax.swing.*;
import java.awt.*;
import java.security.KeyStore;

public class Frame {
    public static void main(String[] args) {


        JFrame frame =new JFrame();
        JPanel panel=new JPanel();

        frame.add(panel);

        panel.add(new JLabel("first JLabel"));

        frame.setResizable(false);
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(840,840/12*9));
        frame.setSize(840,840/12*9);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }
}
