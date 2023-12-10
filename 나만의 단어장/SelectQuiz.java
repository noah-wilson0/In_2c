/*
 * ���� : ��� Ǯ ��Ʈ�� ���� ������ �����ϴ� GUI Ŭ����
 * 
 * �����̳� ���·� �ۼ���.
 * �̺�Ʈ�� GUI Ŭ���� ������ ó����.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SelectQuiz extends Container implements ActionListener {
	// DB�� �����ϴ� �޼ҵ带 ȣ���ϱ� ���� ��ü ����
	ConnectDB cd = new ConnectDB();
	
	// �޺��ڽ� ��ü ����
	JComboBox<String> note_combo = new JComboBox<String>();		// ��Ʈ ����� �߰��Ǵ� �޺��ڽ�
	
	// �����̳� ȭ���� �����ϴ� ������
	public SelectQuiz() {
		// ���̾ƿ� ��ü ����
		FlowLayout fLoC1 = new FlowLayout(FlowLayout.CENTER, 0, 0);
		FlowLayout fLoC2 = new FlowLayout(FlowLayout.CENTER, 0, 40);
		FlowLayout fLoL = new FlowLayout(FlowLayout.LEFT, 0, 20);
		
		// ��Ʈ ��ü ����
		Font titleFt = new Font("����", Font.BOLD, 32);
		Font mainFt = new Font("����", Font.PLAIN, 14);
		Font buttonFt = new Font("����", Font.BOLD, 18);
		
		// �����̳� ���̾ƿ� ����
		setLayout(fLoC1);
		
		// ���� ȭ���� �����ϴ� �г�
		JPanel titleJp = new JPanel(); 						// �г� ��ü ����
		titleJp.setLayout(fLoL);							// �г� ���̾ƿ� ����
		titleJp.setPreferredSize(new Dimension(320, 100));	// �г� ũ�� ���� - new Dimension(�ʺ�, ����)
		
		JLabel titleJl = new JLabel("���� ����");				// ���� �� ��ü ����
		titleJl.setFont(titleFt);							// ���� �� ��Ʈ ����
		titleJp.add(titleJl);								// �гο� ���� �� �߰�
		
		// ��Ʈ�� �����ϴ� �г�
		JPanel noteJp = new JPanel(); 									// �г� ��ü ����
		noteJp.setLayout(fLoL);											// �г� ���̾ƿ� ����
		noteJp.setPreferredSize(new Dimension(320, 50));				// �г� ũ�� ���� - new Dimension(�ʺ�, ����)
		
		JLabel guideJl = new JLabel("��� Ǯ ��Ʈ�� �������ּ���.  ");			// ���̵� �� ��ü ����
		guideJl.setFont(mainFt);										// ���̵� �� ��Ʈ ����
		noteJp.add(guideJl);											// �гο� ���̵� �� �߰�
		
		String noteList[] = cd.selectNoteList(GetID.id).split("\\|");	//'|'�� ���е�  note�� �ٽ� '|'�� �����Ͽ� �迭 ����
        for (int i = 0; i < noteList.length; i++) {
            note_combo.addItem(noteList[i]);								// ��Ʈ �޺��ڽ��� ������ �߰�
        }
        noteJp.add(note_combo);											// �гο� ��Ʈ �޺��ڽ� �߰�
		
		// ���� ������ �����ϴ� �г�
		JPanel quizTypeJp = new JPanel(); 						// �г� ��ü ����
		quizTypeJp.setLayout(fLoC2);							// �г� ���̾ƿ� ����
		quizTypeJp.setPreferredSize(new Dimension(320, 300));	// �г� ũ�� ���� - new Dimension(�ʺ�, ����)
		
		JButton choice_quiz = new JButton("������ ����");			// ������ ���� ��ư ��ü ����
		choice_quiz.setFont(buttonFt);							// ������ ���� ��ư ��Ʈ ����
		choice_quiz.setPreferredSize(new Dimension(240, 60));	// ������ ���� ��ư ũ�� ����
		quizTypeJp.add(choice_quiz);							// �гο� ������ ���� ��ư �߰�
		
		JButton word_quiz = new JButton("�ְ��� ���� (�ܾ�)");		// �ְ��� �ܾ� ���� ��ư ��ü ����
		word_quiz.setFont(buttonFt);							// �ְ��� �ܾ� ���� ��ư ��Ʈ ����
		word_quiz.setPreferredSize(new Dimension(240, 60));		// �ְ��� �ܾ� ���� ��ư ũ�� ����
		quizTypeJp.add(word_quiz);								// �гο� �ְ��� �ܾ� ���� ��ư �߰�
		
		JButton meaning_quiz = new JButton("�ְ��� ���� (��)");		// �ְ��� �� ���� ��ư ��ü ����
		meaning_quiz.setFont(buttonFt);							// �ְ��� �� ���� ��ư ��Ʈ ����
		meaning_quiz.setPreferredSize(new Dimension(240, 60));	// �ְ��� �� ���� ��ư ũ�� ����
		quizTypeJp.add(meaning_quiz);							// �гο� �ְ��� �� ���� ��ư �߰�
		
		// �������� �̵��ϴ� ��ư�� ������ �г�
		JPanel mainButtonJp = new JPanel(); 					// �г� ��ü ����
		mainButtonJp.setLayout(fLoC2);							// �г� ���̾ƿ� ����
		mainButtonJp.setPreferredSize(new Dimension(320, 80));	// �г� ũ�� ���� - new Dimension(�ʺ�, ����)
		
		JButton to_main = new JButton("��������");					// ����ȭ�� ��ư ��ü ����
		to_main.setPreferredSize(new Dimension(90, 40));		// ����ȭ�� ��ư ũ�� ����
		mainButtonJp.add(to_main);								// �гο� ����ȭ�� ��ư �߰�
		
		// ��ư�� �̺�Ʈ ������ �߰�
		choice_quiz.addActionListener(this);
		word_quiz.addActionListener(this);
		meaning_quiz.addActionListener(this);
		to_main.addActionListener(this);
		
		// �����̳ʿ� �г� �߰�
		add(titleJp);
		add(noteJp);
		add(quizTypeJp);
		add(mainButtonJp);
	}

	// �̺�Ʈ�� ó���ϴ� �޼ҵ�
	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrame mf = MainFrame.getInstance();		// ȭ���� ��ȯ�ϴ� �޼ҵ带 ȣ���ϱ� ���� ��ü ����
		String note_name = (String)note_combo.getSelectedItem();	// �޺��ڽ����� ���õ� ��Ʈ�� �̸��� �޴� ���� ����
		int count = cd.getNoteSize(GetID.id, note_name);		// �ش� ��Ʈ�� �ִ� �ܾ��� ������ ��ȸ
		
		switch (e.getActionCommand()) {
			case "������ ����":
				if (count < 5) {										// �ܾ ������ 5�� �̸��̸�..
					JOptionPane.showMessageDialog(null, "��� Ǯ�� ���� �ܾ �����մϴ�!\n�ʿ��� �ܾ�� �ּ� 5�� �̻��Դϴ�.");
				}
				else {													// �ܾ ������ 5�� �̻��̸�..
					mf.ChangeGUI("����������", note_name);						// ������ ���� ȭ������ �̵�
				}
				break;
			case "�ְ��� ���� (�ܾ�)":
				if (count < 1) {										// �ܾ ������ 1�� �̸��̸�..
					JOptionPane.showMessageDialog(null, "��� Ǯ�� ���� �ܾ �����մϴ�!\n�ʿ��� �ܾ�� �ּ� 1�� �̻��Դϴ�.");
				}
				else {													// �ܾ ������ 1�� �̻��̸�..
					mf.ChangeGUI("�ְ��Ĵܾ�����", note_name);					// �ְ��� �ܾ� ���� ȭ������ �̵�
				}
				break;
			case "�ְ��� ���� (��)":
				if (count < 1) {										// �ܾ ������ 1�� �̸��̸�..
					JOptionPane.showMessageDialog(null, "��� Ǯ�� ���� �ܾ �����մϴ�!\n�ʿ��� �ܾ�� �ּ� 1�� �̻��Դϴ�.");
				}
				else {													// �ܾ ������ 1�� �̻��̸�..
					mf.ChangeGUI("�ְ��Ķ�����", note_name);					// �ְ��� �� ���� ȭ������ �̵�
				}
				break;
			case "��������":
				mf.ChangeGUI("����ȭ��");		// ����ȭ������ �̵�
				break;
		}
	}
}