/*
 * ���� : ȸ�������� �����ϴ� GUI Ŭ����
 * 
 * �����̳� ���·� �ۼ���.
 * �̺�Ʈ�� GUI Ŭ���� ������ ó����.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;
import javax.swing.*;

public class EditProfile extends Container implements ActionListener {
    // DB�� �����ϴ� �޼ҵ带 ȣ���ϱ� ���� ��ü ����
    ConnectDB cb = new ConnectDB();

    // ������Ʈ ��ü ����
    JTextField nameJtf;									// �̸� �ؽ�Ʈ �ʵ� ��ü ����
    JRadioButton[] genderjrb = new JRadioButton[2];		// ���� ���� ��ư �迭 ��ü ����
    JTextField emailJtf;								// �̸��� �ؽ�Ʈ �ʵ� ��ü ����

    // �����̳� ȭ���� �����ϴ� ������
    public EditProfile() {
        // ���̾ƿ� ��ü ����
        FlowLayout fLoC1 = new FlowLayout(FlowLayout.CENTER, 0, 0);
        FlowLayout fLoC2 = new FlowLayout(FlowLayout.CENTER, 0, 20);
        FlowLayout fLoC3 = new FlowLayout(FlowLayout.CENTER, 30, 0);
        FlowLayout fLoL = new FlowLayout(FlowLayout.LEFT, 0, 20);

        // ��Ʈ ��ü ����
        Font titleFt = new Font("����", Font.BOLD, 24);
        Font mainFt = new Font("����", Font.BOLD, 14);

        // DB�� ����� ȸ�������� �ϳ��� ���ڿ��� �޴� ���� ����
        String profile = cb.lookProfile(GetID.id);		// lookProfile(String) : ȸ�������� ��ȸ�ϴ� �޼ҵ�

        // profile�� ��ū���� �и��ϴ� ��ü ����
        StringTokenizer st = new StringTokenizer(profile, "/");		// "/"�� �������� �и�

        // ���� ��ū�� �������� ���ڿ��� �� ������ ����
        String id = st.nextToken();
        String name = st.nextToken();
        String gender = st.nextToken();
        String email = st.nextToken();

        // �����̳� ���̾ƿ� ����
        setLayout(fLoC1);

        // ���� ȭ���� �����ϴ� �г�
        JPanel titleJp = new JPanel(); 						// ��ü ����
        titleJp.setLayout(fLoC2);							// ���̾ƿ� ����
        titleJp.setPreferredSize(new Dimension(300, 100));	// ũ�� ���� - new Dimension(�ʺ�, ����)

        JLabel titleJl = new JLabel("�� ȸ������ ����");			// �� ��ü ����
        titleJl.setFont(titleFt);							// �� ��Ʈ ����
        titleJp.add(titleJl);								// �гο� �� �߰�

        // ȸ�������� ������ �г�
        JPanel profileJp = new JPanel();							// ��ü ����
        profileJp.setLayout(fLoL);									// ���̾ƿ� ����
        profileJp.setPreferredSize(new Dimension(300, 230));		// ũ�� ���� - new Dimension(�ʺ�, ����)

        JLabel idJl = new JLabel("���̵�");							// �� ��ü ����
        idJl.setFont(mainFt);										// �� ��Ʈ ����
        idJl.setPreferredSize(new Dimension(100, 30));				// �� ũ�� ����
        profileJp.add(idJl);										// �гο� �� �߰�

        JTextField idJtf = new JTextField(id);						// �ؽ�Ʈ �ʵ� ��ü ����, id ���
        idJtf.setEditable(false);									// �ؽ�Ʈ �ʵ� ���� �Ұ� ����
        idJtf.setPreferredSize(new Dimension(200, 30));				// �ؽ�Ʈ �ʵ� ũ�� ����
        profileJp.add(idJtf);										// �гο� �ؽ�Ʈ �ʵ� �߰�

        JLabel nameJl = new JLabel("�̸�");							// �� ��ü ����
        nameJl.setFont(mainFt);										// �� ��Ʈ ����
        nameJl.setPreferredSize(new Dimension(100, 30));			// �� ũ�� ����
        profileJp.add(nameJl);										// �гο� �� �߰�

        nameJtf = new JTextField(name);								// �ؽ�Ʈ �ʵ� ��ü ����, name ���
        nameJtf.setPreferredSize(new Dimension(200, 30));			// �ؽ�Ʈ �ʵ� ũ�� ����
        profileJp.add(nameJtf);										// �гο� �ؽ�Ʈ �ʵ� �߰�

        JLabel genderJl = new JLabel("����");							// �� ��ü ����
        genderJl.setFont(mainFt);									// �� ��Ʈ ����
        genderJl.setPreferredSize(new Dimension(100, 30));			// �� ũ�� ����
        profileJp.add(genderJl);									// �гο� �� �߰�

        genderjrb[0] = new JRadioButton("��");						// ���� ��ư ����
        genderjrb[1] = new JRadioButton("��");
        ButtonGroup bg = new ButtonGroup();							// ��ư �׷� ����
        for (int i=0; i<2; i++) {
            genderjrb[i].setPreferredSize(new Dimension(40, 30));	// ���� ��ư ũ�� ����
            bg.add(genderjrb[i]);									// ��ư �׷쿡 ���� ��ư �߰�
            profileJp.add(genderjrb[i]);							// �гο� ���� ��ư �߰�
            if (genderjrb[i].getText().equals(gender)) {			// ���� ��ư�� �̸��� gender�� ���ٸ�..
                genderjrb[i].setSelected(true);						// ���� ��ư�� ���¸� true�� ����
            }
        }

        JLabel blank = new JLabel();								// �� ��ü ����
        blank.setPreferredSize(new Dimension(120, 30));				// �� ũ�� ����
        profileJp.add(blank);										// �гο� �� �߰�

        JLabel emailJl = new JLabel("�̸���");						// �� ��ü ����
        emailJl.setFont(mainFt);									// �� ��Ʈ ����
        emailJl.setPreferredSize(new Dimension(100, 30));			// �� ũ�� ����
        profileJp.add(emailJl);										// �гο� �� �߰�

        emailJtf = new JTextField(email);							// �ؽ�Ʈ �ʵ� ��ü ����, email ���
        emailJtf.setPreferredSize(new Dimension(200, 30));			// �ؽ�Ʈ �ʵ� ũ�� ����
        profileJp.add(emailJtf);									// �гο� �ؽ�Ʈ �ʵ� �߰�

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
        add(profileJp);
        add(buttonJp);
    }

    // �̺�Ʈ�� ó���ϴ� �޼ҵ�
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame mf = MainFrame.getInstance();		// ȭ���� ��ȯ�ϴ� �޼ҵ带 ȣ���ϱ� ���� ��ü ����

        switch (e.getActionCommand()) {
            case "����":
                String gender_val = "";		// gender ���� ��ư���� ���õ� ���� �޴� ���� ����

                for (int i=0; i<2; i++) {
                    if(genderjrb[i].isSelected()) {				// ���� ��ư�� ���õǾ� ������ ..
                        gender_val = genderjrb[i].getText();	// ���� ��ư�� �̸��� gender_val�� ����
                    }
                }

                if (nameJtf.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "�̸��� �Է����ּ���.");
                }
                else if (emailJtf.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "�̸����� �Է����ּ���.");
                }
                else {
                    cb.editProfile(GetID.id, nameJtf.getText(), gender_val, emailJtf.getText());
                    JOptionPane.showMessageDialog(null, "���������� �����Ǿ����ϴ�.");
                    mf.ChangeGUI("ȸ������");
                }
                break;
            case "���":
                mf.ChangeGUI("ȸ������");
                break;
        }
    }
}