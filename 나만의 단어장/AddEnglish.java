/*
 * ���� : ���ܾ �߰��ϴ� GUI Ŭ����
 * 
 * �����̳� ���·� �ۼ���.
 * �̺�Ʈ�� GUI Ŭ���� ������ ó����.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import static java.lang.String.join;

public class AddEnglish extends Container implements ActionListener {
    //�̺�Ʈ���� ���� ����
    JTextField wordJtf;
    String noteName;
    JTextField meanJtf[];
    JCheckBox partCheck[];
    
    // �����̳� ȭ���� �����ϴ� ������
    public AddEnglish(String noteName) {
        this.noteName=noteName;
        // ���̾ƿ� ��ü ����
        FlowLayout fLoC1 = new FlowLayout(FlowLayout.CENTER, 0, 0);
        FlowLayout fLoC2 = new FlowLayout(FlowLayout.CENTER, 0, 10);
        FlowLayout fLoC3 = new FlowLayout(FlowLayout.CENTER, 30, 20);
        FlowLayout fLoL = new FlowLayout(FlowLayout.LEFT, 0, 0);

        // ��Ʈ ��ü ���� - new Font("�۲�", Font.ȿ��, ũ��) (ȿ�� : PLAIN-����, BOLD-���ϰ�, ITALIC-����̱�)
        Font titleFt = new Font("���� ���", Font.BOLD, 24);
        Font informFt = new Font("���� ���", Font.PLAIN, 14);
        Font mainFt = new Font("���� ���", Font.BOLD, 14);

        setLayout(fLoC1);				// ���̾ƿ� ����
        // ���� ȭ���� �����ϴ� �г�
        JPanel titleJp = new JPanel(); 							// ��ü ����
        titleJp.setLayout(fLoC2);								// ���̾ƿ� ����
        titleJp.setPreferredSize(new Dimension(320, 80));		// ũ�� ���� - new Dimension(�ʺ�, ����)

        JLabel titleJl = new JLabel("���ܾ� �߰��ϱ�");				// �� ��ü ����
        titleJl.setFont(titleFt);								// �� ��Ʈ ����
        titleJp.add(titleJl);									// �гο� �� �߰�

        // �ܾ �߰��ϴ� ����� �˷��ִ� �г�
        JPanel informJp = new JPanel();											// ��ü ����
        informJp.setLayout(fLoC1);												// ���̾ƿ� ����
        informJp.setPreferredSize(new Dimension(320, 80));						// ũ�� ���� - Dimension(�ʺ�, ����)

        JLabel informJl1 = new JLabel("* �ܾ�� ���� �Է��� �� ǰ�縦 �������ּ���.");		// ��1 ��ü ����
        informJl1.setFont(informFt);											// ��1 ��Ʈ ����
        informJl1.setPreferredSize(new Dimension(320, 20));						// ��1 ũ�� ����
        informJp.add(informJl1);												// �гο� ��1 �߰�

        JLabel informJl2 = new JLabel("* ���� �ִ� 5������ �Է��Ͻ� �� �ֽ��ϴ�.");		// ��2 ��ü ����
        informJl2.setFont(informFt);											// ��2 ��Ʈ ����
        informJl2.setPreferredSize(new Dimension(320, 20));						// ��2 ũ�� ����
        informJp.add(informJl2);												// �гο� ��2 �߰�

        JLabel informJl3 = new JLabel("* ǰ��� �ʿ��� ��ŭ ��� �����Ͻ� �� �ֽ��ϴ�.");	// ��3 ��ü ����
        informJl3.setFont(informFt);											// ��3 ��Ʈ ����
        informJl3.setPreferredSize(new Dimension(320, 20));						// ��3 ũ�� ����
        informJp.add(informJl3);												// �гο� ��3 �߰�

        // �ܾ �Է¹޴� �г�
        JPanel wordJp = new JPanel();						// ��ü ����
        wordJp.setLayout(fLoL);								// ���̾ƿ� ����
        wordJp.setPreferredSize(new Dimension(320, 40));	// ũ�� ���� - new Dimension(�ʺ�, ����)

        JLabel wordJl = new JLabel("���ο� �ܾ� �Է� ");			// �� ��ü ����
        wordJl.setFont(mainFt);								// �� ��Ʈ ����
        wordJl.setPreferredSize(new Dimension(130, 40));	// �� ũ�� ����
        wordJp.add(wordJl);									// �гο� �� �߰�
        //wordJl.setBorder(new TitledBorder(""));

        wordJtf = new JTextField();				// �ؽ�Ʈ �ʵ� ��ü ����
        wordJtf.setPreferredSize(new Dimension(150, 30));	// �ؽ�Ʈ �ʵ� ũ�� ����
        wordJp.add(wordJtf);								// �гο� �ؽ�Ʈ �ʵ� �߰�

        // ���� �Է¹޴� �г�
        JPanel meanJp = new JPanel();								// ��ü ����
        meanJp.setLayout(fLoL);										// ���̾ƿ� ����
        meanJp.setPreferredSize(new Dimension(320, 200));			// ũ�� ���� - Dimension(�ʺ�, ����)

        JLabel meanJl[] = new JLabel[5];							// �� ��ü �迭 ����
        meanJtf = new JTextField[5];					// �ؽ�Ʈ �ʵ� ��ü �迭 ����
        for (int i=0; i<5; i++) {
            meanJl[i] = new JLabel("��" + (int)(i+1) + " �Է� ");		// �� ��ü ����
            meanJl[i].setFont(mainFt);								// �� ��Ʈ ����
            meanJl[i].setPreferredSize(new Dimension(130, 40));		// �� ũ�� ����
            meanJp.add(meanJl[i]);									// �гο� �� �߰�

            meanJtf[i] = new JTextField();							// �ؽ�Ʈ �ʵ� ��ü ����
            meanJtf[i].setPreferredSize(new Dimension(150, 30));	// �ؽ�Ʈ �ʵ� ũ�� ����
            meanJp.add(meanJtf[i]);									// �гο� �ؽ�Ʈ �ʵ� �߰�
        }

        // ǰ�縦 �Է¹޴� �г�
        JPanel partJp = new JPanel();								// ��ü ����
        partJp.setLayout(fLoC1);									// ���̾ƿ� ����
        partJp.setPreferredSize(new Dimension(320, 98));			// ũ�� ���� - Dimension(�ʺ�, ����)

        JLabel partJl = new JLabel("[ǰ�� ����] ");					// �� ��ü ����
        partJl.setFont(mainFt);										// �� ��Ʈ ����
        partJl.setPreferredSize(new Dimension(320, 40));			// �� ũ�� ����
        partJp.add(partJl);											// �гο� �� �߰�

        JPanel partChoiceJp1 = new JPanel();						// üũ�ڽ� �պκ��� ������ �г�1 ��ü ����
        partChoiceJp1.setLayout(fLoL);								// �г�1 ���̾ƿ� ����
        partChoiceJp1.setPreferredSize(new Dimension(320, 28));		// �г�1 ũ�� ����

        JPanel partChoiceJp2 = new JPanel();						// üũ�ڽ� �޺κ��� ������ �г�2 ��ü ����
        partChoiceJp2.setLayout(fLoL);								// �г�2 ���̾ƿ� ����
        partChoiceJp2.setPreferredSize(new Dimension(320, 28));		// �г�2 ũ�� ����

        String partList[] = {"����", "���", "�λ�", "�����", "����", "��ġ��", "���ӻ�", "��ź��", "����"};
        partCheck = new JCheckBox[9];								// üũ�ڽ� ��ü �迭 ����
		for (int i=0; i<9; i++) {
			partCheck[i] = new JCheckBox(partList[i]);					// üũ�ڽ� ��ü ����
			partCheck[i].setPreferredSize(new Dimension(64, 20));		// ũ�� ����
			if (i < 4) {
				partChoiceJp1.add(partCheck[i]);							// �г�1�� üũ�ڽ� �պκ� 4�� �߰�
			}
			else {
				partChoiceJp2.add(partCheck[i]);							// �г�2�� üũ�ڽ� �޺κ� 4�� �߰�
			}
		}
		partJp.add(partChoiceJp1);									// �гο� �г�1 �߰�
		partJp.add(partChoiceJp2);									// �гο� �г�2 �߰�

        // ���� ��ư�� ��� ��ư�� ������ �г�
        JPanel buttonJp = new JPanel();						// ��ü ����
        buttonJp.setLayout(fLoC3);							// ���̾ƿ� ����
        buttonJp.setPreferredSize(new Dimension(320, 62));	// ũ�� ���� - Dimension(�ʺ�, ����)

        JButton save = new JButton("����");					// ���� ��ư ��ü ����
        save.setPreferredSize(new Dimension(120, 40));		// ũ�� ����
        buttonJp.add(save);									// �гο� ���� ��ư �߰�

        JButton cancel = new JButton("���");					// ��� ��ư ��ü ����
        cancel.setPreferredSize(new Dimension(120, 40));	// ũ�� ����
        buttonJp.add(cancel);								// �гο� ��� ��ư �߰�

        //�̺�Ʈ �ֱ� - ����, ���
        save.addActionListener(this);
        cancel.addActionListener(this);
        
        // �����̳ʿ� �г� �߰�
        add(titleJp);	// ���� ȭ���� �����ϴ� �г�
        add(informJp);	// �ܾ �߰��ϴ� ����� �˷��ִ� �г�
        add(wordJp);		// �ܾ �Է¹޴� �г�
        add(meanJp);		// ���� �Է¹޴� �г�
        add(partJp);		// ǰ�縦 �Է¹޴� �г�
        add(buttonJp);	// ���� ��ư�� ��� ��ư�� ������ �г�
    }
    
    // �̺�Ʈ�� ó���ϴ� �޼ҵ�
    @Override
    public void actionPerformed(ActionEvent e) {
    	//���� ������ Ŭ�����κ��� ��ü ����
        MainFrame mf = MainFrame.getInstance();
        
        //�Է��� ���� �Է��� ��ŭ ���� �迭�� �޴´�.
        List<String> meanList = new ArrayList<>();

        //üũ�� ǰ�縸ŭ ���� �迭�� �޴´�.
        List<String> pos = new ArrayList<>();
        
        switch (e.getActionCommand()) {
        	case "����":
        		//�Էµ� �� ��ŭ ���� �迭�� �߰�
                for (int i=0; i<5; i++) {
                    String mean=meanJtf[i].getText();
                    if(!mean.isEmpty()){  //�Է��� �游  meanList�� ������, insert�� ����;
                        meanList.add(mean);
                    }
                }
                
                //meanList�� ", "�� ������ ���ڿ��� �����
                String mean=join(", ",meanList);
                
                //üũ�� ǰ�縸ŭ �迭 ����
                for (int i = 0; i < partCheck.length; i++) {
                    if (partCheck[i].isSelected()) {
                        pos.add(partCheck[i].getText());
                    }
                }
                
                //�Է� ���� ���� ���� Ȯ��
                if (wordJtf.getText().equals("")) {		// �ܾ �Է����� �ʾ�����..
                	JOptionPane.showMessageDialog(null, "�ܾ �Է����ּ���.");
                }
                else if (mean.isEmpty()) {				// ���� �Է����� �ʾ�����..
                	JOptionPane.showMessageDialog(null, "���� �Է����ּ���.");
                }
                else if (pos.isEmpty()) {				// ǰ�縦 �������� �ʾ�����..
                	JOptionPane.showMessageDialog(null, "ǰ�縦 �������ּ���.");
                }
                else {									// ��� �ۼ�������..
                	ConnectDB cb=new ConnectDB();	// �ش� ��Ʈ table�� �ܾ insert�ϱ� ���� ��ü ����
                    cb.insertWord(GetID.id, noteName, wordJtf.getText(), mean, pos);  // ID, ��Ʈ, �ܾ�, ��, ǰ��
                    mf.ChangeGUI("��Ʈ", noteName); 	// ��Ʈ ȭ������ ���ư���
                }
                break;
        	
        	case "���":
        		mf.ChangeGUI("��Ʈ", noteName);	// ��Ʈ ȭ������ ���ư���
        		break;
        }
    }
}