/*
 * ���� : ȸ���� Ż���ϴ� GUI Ŭ����
 * 
 * �����̳� ���·� �ۼ���.
 * �̺�Ʈ�� GUI Ŭ���� ������ ó����.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class DeleteMember extends Container implements ActionListener {
    // DB�� �����ϴ� �޼ҵ带 ȣ���ϱ� ���� ��ü ����
    ConnectDB cb = new ConnectDB();

    // ��й�ȣ �ؽ�Ʈ �ʵ� ��ü ����
    JTextField passwordJtf;

    // �����̳� ȭ���� �����ϴ� ������
    public DeleteMember() {
        // ���̾ƿ� ��ü ����
        FlowLayout fLoC1 = new FlowLayout(FlowLayout.CENTER, 0, 0);
        FlowLayout fLoC2 = new FlowLayout(FlowLayout.CENTER, 0, 20);
        FlowLayout fLoC3 = new FlowLayout(FlowLayout.CENTER, 30, 20);
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

        JLabel titleJl = new JLabel("ȸ��Ż��");				// �� ��ü ����
        titleJl.setFont(titleFt);							// �� ��Ʈ ����
        titleJp.add(titleJl);								// �гο� �� �߰�

        // ��й�ȣ�� �Է¹޴� �г�
        JPanel passwordJp = new JPanel(); 						// ��ü ����
        passwordJp.setLayout(fLoL);								// ���̾ƿ� ����
        passwordJp.setPreferredSize(new Dimension(300, 100));	// ũ�� ���� - new Dimension(�ʺ�, ����)

        JLabel passwordJl = new JLabel("��й�ȣ �Է�");			// �� ��ü ����
        passwordJl.setFont(mainFt);								// �� ��Ʈ ����
        passwordJl.setPreferredSize(new Dimension(120, 30));	// �� ũ�� ����
        passwordJp.add(passwordJl);								// �гο� �� �߰�

        passwordJtf = new JTextField();							// �ؽ�Ʈ �ʵ� ��ü ����
        passwordJtf.setPreferredSize(new Dimension(180, 30));	// �ؽ�Ʈ �ʵ� ũ�� ����
        passwordJp.add(passwordJtf);							// �гο� �ؽ�Ʈ �ʵ� �߰�

        // Ż�� ��ư�� ��� ��ư�� ������ �г�
        JPanel buttonJp = new JPanel();							// ��ü ����
        buttonJp.setLayout(fLoC3);								// ���̾ƿ� ����
        buttonJp.setPreferredSize(new Dimension(300, 120));		// ũ�� ���� - Dimension(�ʺ�, ����)

        JButton delete = new JButton("Ż��");						// Ż�� ��ư ��ü ����
        delete.setPreferredSize(new Dimension(140, 40));		// ũ�� ����
        buttonJp.add(delete);									// �гο� ���� ��ư �߰�

        JButton cancel = new JButton("���");						// ��� ��ư ��ü ����
        cancel.setPreferredSize(new Dimension(140, 40));		// ũ�� ����
        buttonJp.add(cancel);									// �гο� ��� ��ư �߰�

        // ��ư�� �̺�Ʈ ������ �߰�
        delete.addActionListener(this);
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
            case "Ż��":
                if (passwordJtf.getText().equals("")) {				// ��й�ȣ�� �Է����� �ʾ�����..
                    JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է����ּ���.");
                }
                else {												// ��й�ȣ�� �Է�������..
                    if (cb.checkPW(GetID.id, passwordJtf.getText())) {	// ��й�ȣ�� �¾�����..
                        int answer = JOptionPane.showConfirmDialog(null, "������ Ż���Ͻðڽ��ϱ�?", "Message",
                                JOptionPane.YES_NO_OPTION);
                        if (answer == 0) {									// �޽��� â���� Yes�� ����������..
                            cb.deleteMember(GetID.id);
                            JOptionPane.showMessageDialog(null, "���������� Ż��Ǿ����ϴ�.");
                            mf.ChangeGUI("�α���");
                        }
                    }
                    else {												// ��й�ȣ�� Ʋ������..
                        JOptionPane.showMessageDialog(null, "��й�ȣ�� Ʋ�Ƚ��ϴ�.");
                        passwordJtf.setText("");
                    }
                }
                break;

            case "���":
                mf.ChangeGUI("ȸ������");		// ���� ȭ���� ȸ������ ��ȸ ȭ������ ��ȯ
                break;
        }
    }
}