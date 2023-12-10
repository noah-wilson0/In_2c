/*
 * ���� : ��й�ȣ�� �����ϴ� GUI Ŭ����
 * 
 * �����̳� ���·� �ۼ���.
 * �̺�Ʈ�� GUI Ŭ���� ������ ó����.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class EditPassword extends Container implements ActionListener {
	// DB�� �����ϴ� �޼ҵ带 ȣ���ϱ� ���� ��ü ����
    ConnectDB cb = new ConnectDB();

    // ������Ʈ ��ü ����
    JTextField nowPwJtf;	// ���� ��й�ȣ�� �Է¹޴� �ؽ�Ʈ �ʵ�
    JTextField newPwJtf;	// �� ��й�ȣ�� �Է¹޴� �ؽ�Ʈ �ʵ�

    // �����̳� ȭ���� �����ϴ� ������
    public EditPassword() {
        // ���̾ƿ� ��ü ����
        FlowLayout fLoC1 = new FlowLayout(FlowLayout.CENTER, 0, 0);
        FlowLayout fLoC2 = new FlowLayout(FlowLayout.CENTER, 0, 20);
        FlowLayout fLoC3 = new FlowLayout(FlowLayout.CENTER, 30, 0);
        FlowLayout fLoL = new FlowLayout(FlowLayout.LEFT, 0, 20);

        // ��Ʈ ��ü ����
        Font titleFt = new Font("����", Font.BOLD, 24);
        Font mainFt = new Font("����", Font.BOLD, 14);

        // �����̳� ���̾ƿ� ����
        setLayout(fLoC1);

        // ���� ȭ���� �����ϴ� �г�
        JPanel titleJp = new JPanel(); 						// ��ü ����
        titleJp.setLayout(fLoC2);							// ���̾ƿ� ����
        titleJp.setPreferredSize(new Dimension(300, 100));	// ũ�� ���� - new Dimension(�ʺ�, ����)

        JLabel titleJl = new JLabel("��й�ȣ ����");			// �� ��ü ����
        titleJl.setFont(titleFt);							// �� ��Ʈ ����
        titleJp.add(titleJl);								// �гο� �� �߰�

        // ��й�ȣ�� �Է¹޴� �г�
        JPanel passwordJp = new JPanel(); 						// ��ü ����
        passwordJp.setLayout(fLoL);								// ���̾ƿ� ����
        passwordJp.setPreferredSize(new Dimension(300, 160));	// ũ�� ���� - new Dimension(�ʺ�, ����)

        JLabel nowPwJl = new JLabel("���� ��й�ȣ");				// �� ��ü ����
        nowPwJl.setFont(mainFt);								// �� ��Ʈ ����
        nowPwJl.setPreferredSize(new Dimension(120, 30));		// �� ũ�� ����
        passwordJp.add(nowPwJl);								// �гο� �� �߰�

        nowPwJtf = new JTextField();							// �ؽ�Ʈ �ʵ� ��ü ����
        nowPwJtf.setPreferredSize(new Dimension(180, 30));		// �ؽ�Ʈ �ʵ� ũ�� ����
        passwordJp.add(nowPwJtf);								// �гο� �ؽ�Ʈ �ʵ� �߰�

        JLabel newPwJl = new JLabel("�� ��й�ȣ");					// �� ��ü ����
        newPwJl.setFont(mainFt);								// �� ��Ʈ ����
        newPwJl.setPreferredSize(new Dimension(120, 30));		// �� ũ�� ����
        passwordJp.add(newPwJl);								// �гο� �� �߰�

        newPwJtf = new JTextField();							// �ؽ�Ʈ �ʵ� ��ü ����
        newPwJtf.setPreferredSize(new Dimension(180, 30));		// �ؽ�Ʈ �ʵ� ũ�� ����
        passwordJp.add(newPwJtf);								// �гο� �ؽ�Ʈ �ʵ� �߰�

        // ���� ��ư�� ��� ��ư�� ������ �г�
        JPanel buttonJp = new JPanel();						// ��ü ����
        buttonJp.setLayout(fLoC3);							// ���̾ƿ� ����
        buttonJp.setPreferredSize(new Dimension(300, 50));	// ũ�� ���� - Dimension(�ʺ�, ����)

        JButton save = new JButton("����");					// ���� ��ư ��ü ����
        save.setPreferredSize(new Dimension(120, 40));		// ũ�� ����
        buttonJp.add(save);									// �гο� ���� ��ư �߰�

        JButton cancel = new JButton("���");					// ��� ��ư ��ü ����
        cancel.setPreferredSize(new Dimension(120, 40));	// ũ�� ����
        buttonJp.add(cancel);								// �гο� ��� ��ư �߰�

        // ��ư�� �̺�Ʈ ������ �߰�
        save.addActionListener(this);
        cancel.addActionListener(this);

        // �����̳ʿ� �г� �߰�
        add(titleJp);
        add(passwordJp);
        add(buttonJp);
    }

    // �̺�Ʈ�� ó���ϴ� �޼ҵ�
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame mf = MainFrame.getInstance();		// ȭ���� ��ȯ�ϴ� �޼ҵ带 ȣ���ϱ� ���� ��ü ����

        switch (e.getActionCommand()) {
            case "����":
                if (nowPwJtf.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "���� ��й�ȣ�� �Է����ּ���.");
                }
                else if (newPwJtf.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "�� ��й�ȣ�� �Է����ּ���.");
                }
                else {
                    if (cb.checkPW(GetID.id, nowPwJtf.getText())) {
                        cb.editPassword(GetID.id, newPwJtf.getText());
                        JOptionPane.showMessageDialog(null, "���������� ����Ǿ����ϴ�.");
                        mf.ChangeGUI("ȸ������");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "���� ��й�ȣ�� Ʋ�Ƚ��ϴ�.");
                        nowPwJtf.setText("");
                    }
                }
                break;

            case "���":
                mf.ChangeGUI("ȸ������");
                break;
        }
    }
}