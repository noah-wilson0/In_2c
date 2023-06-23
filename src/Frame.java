import javax.swing.*;
import java.awt.*;
import java.security.KeyStore;

public class Frame {
    public static void main(String[] args) {


        JFrame frame =new JFrame();
        JPanel panel=new JPanel();
        JLabel label=new JLabel("some test");
        JButton btn1=new JButton("click me:!");
        JTextArea textArea=new JTextArea();
        JTextField textField=new JTextField(100);

        panel.setLayout(new BorderLayout());

//        panel.add(new JLabel("first JLabel"));

        panel.setLayout(new BorderLayout());
        panel.add(label,BorderLayout.NORTH);
        panel.add(btn1,BorderLayout.WEST);
        panel.add(textArea,BorderLayout.CENTER);


        frame.add(panel);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(840,840/12*9));
        frame.setSize(840,840/12*9);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }
}
