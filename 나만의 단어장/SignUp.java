/*
 * ���� : ȸ�������� �ϴ� GUI Ŭ����
 *
 * �����̳� ���·� �ۼ���.
 * �̺�Ʈ�� GUI Ŭ���� ������ ó����.
 */

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SignUp extends Container implements ActionListener {
    private JTextField ID; // ID
    private JTextField pw; // PW
    private JTextField name; // NAME
    private JRadioButton[] gender = new JRadioButton[2]; // GENDER
    private JTextField email; // EMAIL

    // �����̳� ȭ���� �����ϴ� ������
    public SignUp() {
        // ��ư, ID, PW, NAME ��Ʈ
        Font fnt=new Font("����", Font.BOLD,14);

        setLayout(null);

        // Ÿ��Ʋ(�α���, ȸ������) ��Ʈ
        Font fnt_title=new Font("����", Font.BOLD,20);

        // �α��� ����
        JLabel Jl_title=new JLabel("ȸ������");
        Jl_title.setFont(fnt_title);
        Jl_title.setBounds(150,120,100,40);
        add(Jl_title);

        // ID�߰�
        JLabel l1=new JLabel("ID");
        l1.setFont(fnt); //��Ʈ ����
        l1.setBounds(50, 200, 80, 30);	// ���� ��ġ-��ġ������ ��� x
        ID=new JTextField(8);
        ID.setBounds(130,200,200,30);	// ���� ��ġ-��ġ������ ��� x
        JLabel ID_Rock=new JLabel("* �ִ�8�� ����");
        ID_Rock.setBounds(130, 225, 100, 30);	// ���� ��ġ-��ġ������ ��� x

        // PW�߰�
        JLabel l2=new JLabel("PW");
        l2.setBounds(50, 250, 80, 30);	// ���� ��ġ-��ġ������ ��� x
        l2.setFont(fnt);	// ��Ʈ ����
        pw=new JTextField(12);
        pw.setBounds(130,250,200,30);	// ���� ��ġ-��ġ������ ��� x
        JLabel PW_Rock=new JLabel("*�ִ� 12�� ����");
        PW_Rock.setBounds(130, 275, 100, 30);	// ���� ��ġ-��ġ������ ��� x

        // NAME�߰�
        JLabel l3=new JLabel("NAME");
        l3.setBounds(50, 300, 80, 30);	// ���� ��ġ-��ġ������ ��� x
        l3.setFont(fnt);	// ��Ʈ ����
        name=new JTextField(10);
        name.setBounds(130,300,200,30);	// ���� ��ġ-��ġ������ ��� x

        // GENDER�߰�
        JLabel l4=new JLabel("GENDER");
        l4.setBounds(50, 350, 80, 30);	// ���� ��ġ-��ġ������ ��� x
        l4.setFont(fnt);	// ��Ʈ ����
        gender[0] = new JRadioButton("��");
        gender[1] = new JRadioButton("��");
        ButtonGroup bg = new ButtonGroup();
        bg.add(gender[0]);
        bg.add(gender[1]);
        gender[0].setBounds(130,350,40,30);	// ���� ��ġ-��ġ������ ��� x
        gender[1].setBounds(180,350,40,30);	// ���� ��ġ-��ġ������ ��� x

        // EMAIL�߰�
        JLabel l5=new JLabel("EMAIL");
        l5.setBounds(50, 400, 80, 30);	// ���� ��ġ-��ġ������ ��� x
        l5.setFont(fnt);	// ��Ʈ ����
        email=new JTextField(30);
        email.setBounds(130,400,200,30);	// ���� ��ġ-��ġ������ ��� x

        // �α��� ��ư
        JButton signIN_BT=new JButton("Sign in");	// �α���

        // ���� ��ġ-��ġ������ ��� x
        signIN_BT.setBounds(100,450,100,40);	// ���� ��ġ-��ġ������ ��� x

        // ȸ�� ���� ��ư
        JButton signUP_BT=new JButton("Join");	// ȸ������

        // ���� ��ġ-��ġ������ ��� x
        signUP_BT.setBounds(210,450,100,40);	// ���� ��ġ-��ġ������ ��� x

        // �����̳ʿ� �߰�
        add(l1);
        add(ID);
        add(ID_Rock);
        add(l2);
        add(pw);
        add(PW_Rock);
        add(l3);
        add(name);
        add(l4);
        add(gender[0]);
        add(gender[1]);
        add(l5);
        add(email);
        add(signIN_BT);
        add(signUP_BT);

        // �̺�Ʈ ������ �߰�
        signIN_BT.addActionListener(this);
        signUP_BT.addActionListener(this);
    }

    // �̺�Ʈ�� ó���ϴ� �޼ҵ�
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame mf = MainFrame.getInstance();		// ȭ���� ��ȯ�ϴ� �޼ҵ带 ȣ���ϱ� ���� ��ü ����
        ConnectDB cd = new ConnectDB();				// DB�� �����ϴ� �޼ҵ带 ȣ���ϱ� ���� ��ü ����

        switch (e.getActionCommand()) {
            case "Sign in":
                mf.ChangeGUI("�α���");	// �α��� ȭ������ �̵�
                break;

            case "Join":
                String gender_val = "";		// gender ���� ��ư���� ���õ� ���� �޴� ���� ����

                for (int i=0; i<2; i++) {
                    if(gender[i].isSelected()) {		// ���� ��ư�� ���õǾ� ������ ..
                        gender_val = gender[i].getText();	// ���� ��ư�� Text�� gender_val�� ����
                    }
                }

                if (ID.getText().equals("")) {			// ���̵� �Է����� �ʾ�����..
                    JOptionPane.showMessageDialog(null, "���̵� �Է����ּ���.");
                }
                else if (pw.getText().equals("")) {		// ��й�ȣ�� �Է����� �ʾ�����..
                    JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է����ּ���.");
                }
                else if (name.getText().equals("")) {	// �̸��� �Է����� �ʾ�����..
                    JOptionPane.showMessageDialog(null, "�̸��� �Է����ּ���.");
                }
                else if (gender_val.equals("")) {		// ������ �������� �ʾ�����..
                    JOptionPane.showMessageDialog(null, "������ �������ּ���.");
                }
                else if (email.getText().equals("")) {	// �̸����� �Է����� �ʾ�����..
                    JOptionPane.showMessageDialog(null, "�̸����� �Է����ּ���.");
                }
                else if(ID.getText().length()>8){  //���̵� 8�ڸ� �̻� �Է�������...
                    JOptionPane.showMessageDialog(null, "���̵� 8�ڸ� ���Ϸ� �Է����ּ���.");
                    ID.setText("");
                }
                else if(pw.getText().length()>12){  //��й�ȣ�� 12�ڸ� �̻� �Է�������...
                    JOptionPane.showMessageDialog(null, "��й�ȣ�� 12�ڸ� ���Ϸ� �Է����ּ���.");
                    pw.setText("");
                }
                else {									// ��� ������ �Է�������..
                    if (cd.checkID(ID.getText())) {			// �̹� ��ϵ� ���̵� �Է�������..
                        JOptionPane.showMessageDialog(null, "�̹� ��ϵ� ���̵��Դϴ�.");
                        ID.setText("");
                    }
                    else {									// ��ϵ��� ���� ���̵� �Է�������..
                        cd.addMember(ID.getText(), pw.getText(), name.getText(), gender_val,
                                email.getText());			// �Էµ� ������ DB�� �߰�
                        JOptionPane.showMessageDialog(null, "���������� ���ԵǾ����ϴ�.");
                        mf.ChangeGUI("�α���");					// �α��� ȭ������ �̵�
                    }
                }
                break;
        }
    }
}