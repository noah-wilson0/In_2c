/*
 * ���� : �α����� �ϴ� GUI Ŭ����
 * 
 * �����̳� ���·� �ۼ���.
 * �̺�Ʈ�� GUI Ŭ���� ������ ó����.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends Container implements ActionListener {
    // db ����� ���� ���� ID,PW ����
    private JTextField ID;
    private JTextField Passwd;

    // �����̳� ȭ���� �����ϴ� ������
    public Login(){
        // ��ư, ID, PW ��Ʈ
        Font fnt=new Font("����", Font.BOLD,14);
        setLayout(null);

        // Ÿ��Ʋ(�α���, ȸ������) ��Ʈ
        Font fnt_title=new Font("����", Font.BOLD,20);

        // �α��� ����
        JLabel Jl_title=new JLabel("�α���");
        Jl_title.setFont(fnt_title);
        Jl_title.setBounds(170,120,100,40);
        add(Jl_title);

        // ID�߰�
        JLabel l1=new JLabel("ID");
        l1.setFont(fnt); //��Ʈ ����
        l1.setBounds(50, 200, 50, 30);	// ���� ��ġ-��ġ������ ��� x
        ID=new JTextField(8);
        ID.setBounds(100,200,200,30);	// ���� ��ġ-��ġ������ ��� x

        JLabel ID_Rock=new JLabel("* �ִ�8�� ����");
        ID_Rock.setBounds(100, 225, 100, 30);	// ���� ��ġ-��ġ������ ��� x

        // PW�߰�
        JLabel l2=new JLabel("PW");
        l2.setBounds(50, 250, 50, 30);	// ���� ��ġ-��ġ������ ��� x
        l2.setFont(fnt);	// ��Ʈ ����
        Passwd=new JTextField(12);
        Passwd.setBounds(100,250,200,30);	// ���� ��ġ-��ġ������ ��� x
        JLabel PW_Rock=new JLabel("*�ִ� 12�� ����");
        PW_Rock.setBounds(100, 275, 100, 30);	// ���� ��ġ-��ġ������ ��� x

        // login��ư
        JButton LogIn_BT=new JButton("Sign in");	// �α���
        JButton Join_BT=new JButton("Sign up");		// ȸ������
        LogIn_BT.setFont(fnt);	// ��Ʈ ����
        Join_BT.setFont(fnt);	// ��Ʈ ����

        // ���� ��ġ-��ġ������ ��� x
        LogIn_BT.setBounds(100,300,100,40);		// ���� ��ġ-��ġ������ ��� x
        Join_BT.setBounds(210,300,100,40);		// ���� ��ġ-��ġ������ ��� x

        // �����̳ʿ� �߰�
        add(l1);
        add(ID);
        add(ID_Rock);
        add(l2);
        add(Passwd);
        add(PW_Rock);
        add(LogIn_BT);
        add(Join_BT);

        // �̺�Ʈ ������ �߰�
        LogIn_BT.addActionListener(this);
        Join_BT.addActionListener(this);
    }

    // �̺�Ʈ�� ó���ϴ� �޼ҵ�
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame mf = MainFrame.getInstance();		// ȭ���� ��ȯ�ϴ� �޼ҵ带 ȣ���ϱ� ���� ��ü ����
        ConnectDB cb = new ConnectDB();				// DB�� �����ϴ� �޼ҵ带 ȣ���ϱ� ���� ��ü ����
        
        switch (e.getActionCommand()) {
            case "Sign in":
                if (ID.getText().equals("")) {						// ���̵� �Է����� �ʾ�����..
                    JOptionPane.showMessageDialog(null, "���̵� �Է����ּ���.");
                }
                else if (Passwd.getText().equals("")) {				// ��й�ȣ�� �Է����� �ʾ�����..
                    JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է����ּ���.");
                }
                else {												// ��� ������ �Է�������..
                    if (cb.checkID(ID.getText())) {						// DB�� ��ϵ� ���̵� �Է�������..
                        if (cb.checkPW(ID.getText(), Passwd.getText())) {	// ��й�ȣ�� �ش� ���̵�� ��ġ�ϸ�..
                            GetID.keepID(ID.getText());							// �α����� ���̵� GetID Ŭ������ ����
                            mf.ChangeGUI("����ȭ��");								// ����ȭ������ �̵�(�α��� ����)
                        }
                        else {												// ��й�ȣ�� �ش� ���̵�� ��ġ���� ������..
                            JOptionPane.showMessageDialog(null, "��й�ȣ�� Ʋ�Ƚ��ϴ�.");
                            Passwd.setText("");
                        }
                    }
                    else {												// DB�� ��ϵ��� ���� ���̵� �Է�������..
                        JOptionPane.showMessageDialog(null, "��ϵ��� ���� ���̵��Դϴ�.");
                        ID.setText("");
                        Passwd.setText("");
                    }
                }
                break;
            case "Sign up":
                mf.ChangeGUI("ȸ������");		// ȸ������ ȭ�� �ҷ�����
                break;
        }
    }
}