/*
 * ���� : ȸ�������� ��ȸ�ϰ� �پ��� ��ɵ��� ������ �� �ִ� GUI Ŭ����
 * 
 * �����̳� ���·� �ۼ���.
 * Ȩ ȭ������ �̵� / ȸ������ ���� ȭ������ �̵� / ��й�ȣ ���� ȭ������ �̵� / ȸ��Ż�� ȭ������ �̵� / �α׾ƿ� ����� ������.
 * �̺�Ʈ��  GUI Ŭ���� ������ ó����.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;
import javax.swing.*;

// ȸ������(������)�� ��ȸ�ϴ� �����̳�
public class Profile extends Container implements ActionListener {
    // DB�� �����ϴ� �޼ҵ带 ȣ���ϱ� ���� ��ü ����
    ConnectDB cd = new ConnectDB();

    // �����̳� ȭ���� �����ϴ� ������ ������
    public Profile() {
        // ���̾ƿ� ��ü ����
        FlowLayout fLoC1 = new FlowLayout(FlowLayout.CENTER, 0, 0);
        FlowLayout fLoC2 = new FlowLayout(FlowLayout.CENTER, 0, 20);
        FlowLayout fLoL = new FlowLayout(FlowLayout.LEFT, 0, 20);

        // ��Ʈ ��ü ����
        Font titleFt = new Font("����", Font.BOLD, 24);
        Font mainFt = new Font("����", Font.BOLD, 14);

        // DB�� ����� ȸ�������� �ϳ��� ���ڿ��� �޴� ���� ����
        String profile = cd.lookProfile(GetID.id);

        // profile�� ��ū���� �и��ϴ� ��ü ����
        StringTokenizer st = new StringTokenizer(profile, "/");		// '/'�� �������� �и�

        // ���� ��ū�� �������� ���ڿ��� �� ������ ����
        String id = st.nextToken();
        String name = st.nextToken();
        String gender = st.nextToken();
        String email = st.nextToken();

        // �����̳� ���̾ƿ� ����
        setLayout(fLoC1);

        // ���� ȭ���� �����ϴ� �г�
        JPanel titleJp = new JPanel(); 						// �г� ��ü ����
        titleJp.setLayout(fLoC2);							// �г� ���̾ƿ� ����
        titleJp.setPreferredSize(new Dimension(300, 100));	// �г� ũ�� ���� - new Dimension(�ʺ�, ����)

        JLabel titleJl = new JLabel("�� ȸ������ ��ȸ");			// ���� �� ��ü ����
        titleJl.setFont(titleFt);							// ���� �� ��Ʈ ����
        titleJp.add(titleJl);								// �гο� ���� �� �߰�

        // ȸ�������� ������ �г�
        JPanel profileJp = new JPanel();						// �г� ��ü ����
        profileJp.setLayout(fLoL);								// �г� ���̾ƿ� ����
        profileJp.setPreferredSize(new Dimension(300, 220));	// �г� ũ�� ���� - new Dimension(�ʺ�, ����)

        JLabel idJl = new JLabel("���̵�");						// ���̵� �� ��ü ����
        idJl.setFont(mainFt);									// ���̵� �� ��Ʈ ����
        idJl.setPreferredSize(new Dimension(100, 30));			// ���̵� �� ũ�� ����
        profileJp.add(idJl);									// �гο� ���̵� �� �߰�

        JTextField idJtf = new JTextField(id);					// ���̵� �ؽ�Ʈ �ʵ� ��ü ����, id ���
        idJtf.setEditable(false);								// ���̵� �ؽ�Ʈ �ʵ� ���� �Ұ� ����
        idJtf.setPreferredSize(new Dimension(200, 30));			// ���̵� �ؽ�Ʈ �ʵ� ũ�� ����
        profileJp.add(idJtf);									// �гο� ���̵� �ؽ�Ʈ �ʵ� �߰�

        JLabel nameJl = new JLabel("�̸�");						// �̸� �� ��ü ����
        nameJl.setFont(mainFt);									// �̸� �� ��Ʈ ����
        nameJl.setPreferredSize(new Dimension(100, 30));		// �̸� �� ũ�� ����
        profileJp.add(nameJl);									// �гο� �̸� �� �߰�

        JTextField nameJtf = new JTextField(name);				// �̸� �ؽ�Ʈ �ʵ� ��ü ����, name ���
        nameJtf.setEditable(false);								// �̸� �ؽ�Ʈ �ʵ� ���� �Ұ� ����
        nameJtf.setPreferredSize(new Dimension(200, 30));		// �̸� �ؽ�Ʈ �ʵ� ũ�� ����
        profileJp.add(nameJtf);									// �гο� �̸� �ؽ�Ʈ �ʵ� �߰�

        JLabel genderJl = new JLabel("����");						// ���� �� ��ü ����
        genderJl.setFont(mainFt);								// ���� �� ��Ʈ ����
        genderJl.setPreferredSize(new Dimension(100, 30));		// ���� �� ũ�� ����
        profileJp.add(genderJl);								// �гο� ���� �� �߰�

        JTextField genderJtf = new JTextField(gender);			// ���� �ؽ�Ʈ �ʵ� ��ü ����, gender ���
        genderJtf.setEditable(false);							// ���� �ؽ�Ʈ �ʵ� ���� �Ұ� ����
        genderJtf.setPreferredSize(new Dimension(200, 30));		// ���� �ؽ�Ʈ �ʵ� ũ�� ����
        profileJp.add(genderJtf);								// �гο� ���� �ؽ�Ʈ �ʵ� �߰�

        JLabel emailJl = new JLabel("�̸���");					// �̸��� �� ��ü ����
        emailJl.setFont(mainFt);								// �̸��� �� ��Ʈ ����
        emailJl.setPreferredSize(new Dimension(100, 30));		// �̸��� �� ũ�� ����
        profileJp.add(emailJl);									// �гο� �̸��� �� �߰�

        JTextField emailJtf = new JTextField(email);			// �̸��� �ؽ�Ʈ �ʵ� ��ü ����, email ���
        emailJtf.setEditable(false);							// �̸��� �ؽ�Ʈ �ʵ� ���� �Ұ� ����
        emailJtf.setPreferredSize(new Dimension(200, 30));		// �̸��� �ؽ�Ʈ �ʵ� ũ�� ����
        profileJp.add(emailJtf);								// �гο� �̸��� �ؽ�Ʈ �ʵ� �߰�

        // Ȯ�� ��ư�� ������ �г�
        JPanel buttonJp1 = new JPanel();						// �г� ��ü ����
        buttonJp1.setLayout(fLoC1);								// �г� ���̾ƿ� ����
        buttonJp1.setPreferredSize(new Dimension(300, 70));		// �г� ũ�� ���� - Dimension(�ʺ�, ����)

        JButton ok = new JButton("Ȯ��");							// Ȯ�� ��ư ��ü ����
        ok.setPreferredSize(new Dimension(70, 40));				// Ȯ�� ��ư ũ�� ����
        buttonJp1.add(ok);										// �гο� Ȯ�� ��ư �߰�

        // ȸ������ �����ϱ� ��ư�� ������ �г�
        JPanel buttonJp2 = new JPanel();						// �г� ��ü ����
        buttonJp2.setLayout(fLoC1);								// �г� ���̾ƿ� ����
        buttonJp2.setPreferredSize(new Dimension(300, 50));		// �г� ũ�� ���� - Dimension(�ʺ�, ����)

        JButton edit_profile = new JButton("ȸ������ �����ϱ�");		// ȸ���������� ��ư ��ü ����
        edit_profile.setPreferredSize(new Dimension(250, 40));	// ȸ���������� ��ư ũ�� ����
        buttonJp2.add(edit_profile);							// �гο� ȸ���������� ��ư �߰�

        // ��й�ȣ �����ϱ� ��ư�� ������ �г�
        JPanel buttonJp3 = new JPanel();							// �г� ��ü ����
        buttonJp3.setLayout(fLoC1);									// �г� ���̾ƿ� ����
        buttonJp3.setPreferredSize(new Dimension(300, 50));			// �г� ũ�� ���� - Dimension(�ʺ�, ����)

        JButton edit_password = new JButton("��й�ȣ �����ϱ�");		// ��й�ȣ���� ��ư ��ü ����
        edit_password.setPreferredSize(new Dimension(250, 40));		// ��й�ȣ���� ��ư ũ�� ����
        buttonJp3.add(edit_password);								// �гο� ��й�ȣ���� ��ư �߰�

        // ȸ��Ż�� ��ư�� ������ �г�
        JPanel buttonJp4 = new JPanel();						// �г� ��ü ����
        buttonJp4.setLayout(fLoC1);								// �г� ���̾ƿ� ����
        buttonJp4.setPreferredSize(new Dimension(300, 50));		// �г� ũ�� ���� - Dimension(�ʺ�, ����)

        JButton delete = new JButton("ȸ��Ż��");					// ȸ��Ż�� ��ư ��ü ����
        delete.setPreferredSize(new Dimension(250, 40));		// ȸ��Ż�� ��ư ũ�� ����
        buttonJp4.add(delete);									// �гο� ȸ��Ż�� ��ư �߰�

        // �α׾ƿ� ��ư�� ������ �г�
        JPanel buttonJp5 = new JPanel();						// �г� ��ü ����
        buttonJp5.setLayout(fLoC2);								// �г� ���̾ƿ� ����
        buttonJp5.setPreferredSize(new Dimension(300, 60));		// �г� ũ�� ���� - Dimension(�ʺ�, ����)

        JButton logout = new JButton("�α׾ƿ�");					// �α׾ƿ� ��ư ��ü ����
        logout.setPreferredSize(new Dimension(250, 40));		// �α׾ƿ� ��ư ũ�� ����
        buttonJp5.add(logout);									// �гο� �α׾ƿ� ��ư �߰�

        // ��ư�� �̺�Ʈ ������ �߰�
        ok.addActionListener(this);
        edit_profile.addActionListener(this);
        edit_password.addActionListener(this);
        delete.addActionListener(this);
        logout.addActionListener(this);

        // �����̳ʿ� �г� �߰�
        add(titleJp);
        add(profileJp);
        add(buttonJp1);
        add(buttonJp2);
        add(buttonJp3);
        add(buttonJp4);
        add(buttonJp5);
    }

    // �̺�Ʈ�� ó���ϴ� �޼ҵ�
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame mf = MainFrame.getInstance();		// ȭ���� ��ȯ�ϴ� �޼ҵ带 ȣ���ϱ� ���� ��ü ����

        switch (e.getActionCommand()) {
            case "Ȯ��":
                mf.ChangeGUI("����ȭ��");
                break;
            case "ȸ������ �����ϱ�":
                mf.ChangeGUI("ȸ����������");
                break;
            case "��й�ȣ �����ϱ�":
                mf.ChangeGUI("��й�ȣ����");
                break;
            case "ȸ��Ż��":
                mf.ChangeGUI("ȸ��Ż��");
                break;
            case "�α׾ƿ�":
                GetID.keepID(null);		// ��� ���� ID�� null�� ����
                JOptionPane.showMessageDialog(null, "�α׾ƿ��Ǿ����ϴ�.");
                mf.ChangeGUI("�α���");
                break;
        }
    }
}