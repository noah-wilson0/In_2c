/*
 * ���� : ������ ��� Ǫ�� GUI Ŭ����
 * 
 * �����̳� ���·� �ۼ���.
 * �̺�Ʈ�� GUI Ŭ���� ������ ó����.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class ChoiceQuiz extends Container implements ActionListener {
	// ��Ʈ�� �ִ� ��� �ܾ�� ���� ������ 2���� �迭 ����
	String[][] english;
	
	// ��Ʈ�� �ִ� ���ܾ��� ������ ������ ���� ����
	int count = 0;
	
	// ���� ������ �� ��° �������� �˷��ֱ� ���� ���� ����
	int now_count = 1;
	
	// ������ ��µ� �ܾ �����ϴ� ���� ����
	String quiz = null;
	
	// ������ ������ �˻��ϱ� ���� HashMap ��ü ����
	HashMap<String, String> answerHm = new HashMap<String, String>();
	
	// ������ �ϳ��� ����ϱ� ���� LinkedList ��ü ����(ť ����)
	LinkedList<String> quizQueue = new LinkedList<String>();
	
	// �ߺ����� �ʴ� 5���� ���� �����ϱ� ���� HashSet ��ü ����
	HashSet<String> optionHs = new HashSet<String>();
	
	// HashSet�� ���������� ó���ϱ� ���� Iterator ��ü ����
	Iterator<String> optionIt;
	
	// 5�� ���� ���⸦ �����ϱ� ���� ArrayList ��ü ����
	ArrayList<String> optionAl = new ArrayList<String>();
	
	// ������ ���⸦ ������ ���� ��ư �迭 ��ü ����
	JRadioButton[] option = new JRadioButton[5];
	
	// ��ư �׷� ��ü ����
	ButtonGroup bg = new ButtonGroup();
	
	// �� ��ü ����
	JLabel countJl;			// ���� ���� �����ִ� ��
	JLabel wordJl;			// �ܾ �����ִ� ��
	JLabel guideJl;			// ������ ���� ���θ� �ȳ��ϴ� ��
	JLabel rightAnswerJl;	// ������ �˷��ִ� ��
	
	// ������ Ȯ���ϴ� ��ư ��ü ����
	JButton check;
	
	// �����̳� ȭ���� �����ϴ� ������
	public ChoiceQuiz(String note_name) {
		// ���̾ƿ� ��ü ����
		FlowLayout fLoC1 = new FlowLayout(FlowLayout.CENTER, 0, 0);
		FlowLayout fLoC2 = new FlowLayout(FlowLayout.CENTER, 0, 50);
		FlowLayout fLoL1 = new FlowLayout(FlowLayout.LEFT, 0, 30);
		FlowLayout fLoL2 = new FlowLayout(FlowLayout.LEFT, 0, 5);
		BorderLayout bLo1 = new BorderLayout();
		
		// ��Ʈ ��ü ����
		Font titleFt = new Font("����", Font.BOLD, 24);
		Font mainFt1 = new Font("����", Font.PLAIN, 14);
		Font mainFt2 = new Font("����", Font.BOLD, 14);
		Font wordFt = new Font("����", Font.BOLD, 32);
		
		// ���� ����(1/4) - �ش� ��Ʈ�� ��� ���ܾ���� ��ȸ
		ConnectDB cd = new ConnectDB();								// DB�� �����ϴ� �޼ҵ带 ȣ���ϱ� ���� ��ü ����
		english = cd.selectWordAndMeaning(GetID.id, note_name);		// 2���� �迭 english�� ��� �ܾ�� ���� ����
		count = english.length;										// ���ܾ��� ������ count�� ����
		
		// ���� ����(2/4) - ��� �ܾ �˸��� ��� ��ġȭ
		for (int i=0; i<count; i++) {
			answerHm.put(english[i][0], english[i][1]);		// �ܾ�(Ű)�� ��(��)���� �̷���� ��Ҹ� answerHm�� ����
		}
		
		// ���� ����(3/4) - ������ ������ ������ ��ġ�� �� ť�� �߰�
		ArrayList<String> wordAl = new ArrayList<String>();		// ArrayList ��ü ����
		for (int i=0; i<count; i++) {
			wordAl.add(english[i][0]);							// ��� �ܾ wordAl�� �߰�
		}
		Collections.shuffle(wordAl);							// ��� �ܾ��� ������ �������� ���ġ
		
		for (int i=0; i<count; i++) {
			quizQueue.offer(wordAl.get(i));						// �������� ���� �ܾ���� quizQueue�� �߰�
		}
		quiz = quizQueue.poll();								// quizQueue�� ù ��° ��Ҹ� quiz�� ������ �� ����
		
		// �����̳� ���̾ƿ� ����
		setLayout(fLoC1);
		
		// ���� ȭ���� �����ϴ� �г�
		JPanel titleJp = new JPanel(); 								// ��ü ����
		titleJp.setLayout(fLoL1);									// ���̾ƿ� ����
		titleJp.setPreferredSize(new Dimension(300, 100));			// ũ�� ���� - new Dimension(�ʺ�, ����)
		
		JLabel titleJl = new JLabel("������ ����");						// ���� �� ��ü ����
		titleJl.setFont(titleFt);									// ���� �� ��Ʈ ����
		titleJl.setPreferredSize(new Dimension(300, 20));			// ���� �� ũ�� ����
		titleJp.add(titleJl);										// �гο� ���� �� �߰�
		
		JLabel noteJl = new JLabel("��Ʈ : \"" + note_name + "\"");	// ��Ʈ �� ��ü ����
		noteJl.setFont(mainFt1);									// ��Ʈ �� ��Ʈ ����
		noteJl.setPreferredSize(new Dimension(240, 20));			// ��Ʈ �� ũ�� ����
		titleJp.add(noteJl);										// �гο� ��Ʈ �� �߰�
		
		countJl = new JLabel(now_count + "/" + count);				// ���� ��ȣ �� ��ü ����
		countJl.setHorizontalAlignment(JLabel.RIGHT);				// ���� ��ȣ �� �ؽ�Ʈ ���� ����
		countJl.setFont(mainFt1);									// ���� ��ȣ �� ��Ʈ ����
		countJl.setPreferredSize(new Dimension(60, 20));			// ���� ��ȣ �� ũ�� ����
		titleJp.add(countJl);										// �гο� ���� ��ȣ �� �߰�
		
		// �ܾ �����ִ� �г�
		JPanel wordJp = new JPanel(); 						// ��ü ����
		wordJp.setLayout(fLoC2);							// ���̾ƿ� ����
		wordJp.setPreferredSize(new Dimension(300, 150));	// ũ�� ���� - new Dimension(�ʺ�, ����)
		wordJp.setBackground(Color.white);					// ���� ����
		
		wordJl = new JLabel(quiz);							// �ܾ� �� ��ü ���� - quizQueue�� ù ��° �ܾ� ���
		wordJl.setFont(wordFt);								// �ܾ� �� ��Ʈ ����
		wordJp.add(wordJl);									// �гο� �ܾ� �� �߰�
		
		// ������ ���⸦ �����ִ� �г�
		JPanel questionJp = new JPanel(); 							// ��ü ����
		questionJp.setLayout(fLoL2);								// ���̾ƿ� ����
		questionJp.setPreferredSize(new Dimension(300, 220));		// ũ�� ���� - new Dimension(�ʺ�, ����)
		
		guideJl = new JLabel("Q. �ܾ��� ���� �����ϼ���.");				// ���̵� �� ��ü ����
		guideJl.setFont(mainFt2);									// ���̵� �� ��Ʈ ����
		guideJl.setPreferredSize(new Dimension(300, 20));			// ���̵� �� ũ�� ����
		questionJp.add(guideJl);									// �гο� ���̵� �� �߰�
		
		JLabel numberJl[] = new JLabel[5];							// ���� ��ȣ �� ��ü �迭 ����
		for (int i=0; i<5; i++) {
			numberJl[i] = new JLabel((i+1) + ". ");					// ���� ��ȣ �� ��ü ����
			numberJl[i].setFont(mainFt2);							// ���� ��ȣ �� ��Ʈ ����
			numberJl[i].setPreferredSize(new Dimension(16, 24));	// ���� ��ȣ �� ũ�� ����
			questionJp.add(numberJl[i]);							// �гο� ���� ��ȣ �� �߰�
			
			option[i] = new JRadioButton();							// ���� ���� ��ư ����
			option[i].setFont(mainFt1);								// ���� ���� ��ư ��Ʈ ����
			option[i].setPreferredSize(new Dimension(284, 24));		// ���� ���� ��ư ũ�� ����
			bg.add(option[i]);										// ��ư �׷쿡 ���� ���� ��ư �߰�
			questionJp.add(option[i]);								// �гο� ���� ���� ��ư �߰�
		}
		setOption();												// ������ ���� ���� ����
		
		rightAnswerJl = new JLabel();								// ���� �� ��ü ����
		rightAnswerJl.setFont(mainFt2);								// ���� �� ��Ʈ ����
		rightAnswerJl.setPreferredSize(new Dimension(300, 20));		// ���� �� ũ�� ����
		questionJp.add(rightAnswerJl);								// �гο� ���� �� �߰�
		
		// ��ư�� ������ �г�
		JPanel buttonJp = new JPanel(); 					// ��ü ����
		buttonJp.setLayout(bLo1);							// ���̾ƿ� ����
		buttonJp.setPreferredSize(new Dimension(300, 40));	// ũ�� ���� - new Dimension(�ʺ�, ����)
		
		JButton exit = new JButton("������");					// ������ ��ư ��ü ����
		buttonJp.add(exit, BorderLayout.WEST);				// �гο� ������ ��ư �߰�
		
		check = new JButton("���� Ȯ��");						// ���� Ȯ�� ��ư ��ü ����
		buttonJp.add(check, BorderLayout.EAST);				// �гο� ���� Ȯ�� ��ư �߰�
		
		// ��ư�� �̺�Ʈ ������ �߰�
		exit.addActionListener(this);
		check.addActionListener(this);
		
		// �����̳ʿ� �г� �߰�
		add(titleJp);
		add(wordJp);
		add(questionJp);
		add(buttonJp);
	}
	
	// ������ ���� ������ �����ϴ� �޼ҵ�
	public void setOption() {
		// ���� ����(4/4) - 1���� �˸��� ��� 4���� �ٸ� ���� �������� ��ġ�Ͽ� ���⸦ ����
		optionHs.clear();									// optionHs �ʱ�ȭ
		optionAl.clear();									// optionAl �ʱ�ȭ
		
		optionHs.add(answerHm.get(quiz));					// quiz�� ����� �ܾ ����Ű�� ���� optionHs�� �߰�
		int randomIndex = 0;								// ����(����)�� ������ ���� ����
		while (optionHs.size() < 5) {
			randomIndex = (int)(Math.random() * count);		// 0 �̻� count �̸��� ������ randomIndex�� ����
			optionHs.add(english[randomIndex][1]);			// �������� ���õ� ���� �ߺ� ���� optionHs�� �߰�
		}
		
		optionIt = optionHs.iterator();						// optionHs�� ���������� ó���ϱ� ���� Iterator ���
		while (optionIt.hasNext()) {
			optionAl.add(optionIt.next());					// optionHs�� ��Ҹ� optionAl�� ���������� �߰�
		}
		Collections.shuffle(optionAl);						// 5���� ���� ������ ������ ������ �������� ���ġ
		
		for (int i=0; i<5; i++) {
			option[i].setText(optionAl.get(i));				// ���� ��ư�� Text�� optionAl�� ��ҷ� ����
			option[i].setActionCommand(optionAl.get(i));	// ���� ��ư�� ActionCommand�� optionAl�� ��ҷ� ����
		}
	}
	
	// �̺�Ʈ�� ó���ϴ� �޼ҵ�
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "������":
				MainFrame mf = MainFrame.getInstance();		// ȭ���� ��ȯ�ϴ� �޼ҵ带 ȣ���ϱ� ���� ��ü ����
				mf.ChangeGUI("����");							// ���� ȭ���� ���� ���� ȭ������ ��ȯ
				break;
			
			case "���� Ȯ��":
				ButtonModel answerBm = bg.getSelection();	// ���õ� ���� ��ư�� ��ȯ�ޱ� ���� ��ü ����
				
				if (answerBm == null) {									// ���⸦ �������� �ʾ�����..
					JOptionPane.showMessageDialog(null, "���� �����ϼ���.");		// �ش� �޼��� �ڽ� ���
					break;													// case ��� ����
				}
				
				for (int i=0; i<5; i++) {
					option[i].setEnabled(false);	// ��� ���� ��ư ��Ȱ��ȭ
				}
				
				if (answerBm.getActionCommand().equals(answerHm.get(quiz))) {	// ������ ���Ⱑ �����̶��..
					guideJl.setForeground(Color.blue);								// ���̵� �� ��Ʈ ���� ����
					guideJl.setText("O �����Դϴ�!");									// ���̵� �� �ؽ�Ʈ ����
				}
				else {															// ������ ���Ⱑ �����̶��..
					guideJl.setForeground(Color.red);								// ���̵� �� ��Ʈ ���� ����
					guideJl.setText("X �����Դϴ�!");									// ���̵� �� �ؽ�Ʈ ����
				}
				
				rightAnswerJl.setText("���� : "+(optionAl.indexOf(answerHm.get(quiz))+1)+"��");	// ���� ����
				
				check.setText("���� ���� >");			// check ��ư Text ����
				check.setActionCommand("���� ����");	// check ��ư ActionCommand ����
				break;
			
			case "���� ����":
				if (quizQueue.isEmpty()) {					// ������ �������..
					JOptionPane.showMessageDialog(null, "������ �����Դϴ�.");
				}
				else {										// ������ ������ �ƴ϶��..
					quiz = quizQueue.poll();					// ���� quizQueue�� ù ��° ��Ҹ� quiz�� ������ �� ����
					wordJl.setText(quiz);						// quiz�� ����� �ܾ ȭ�鿡 ���
					
					now_count++;								// ���� ���� ��ȣ�� 1��ŭ ����
					countJl.setText(now_count + "/" + count);	// ���� ��ȣ �� �ؽ�Ʈ ����
					
					guideJl.setForeground(Color.black);			// ���̵� �� ��Ʈ ���� ����
					guideJl.setText("Q. �ܾ��� ���� �����ϼ���.");		// ���̵� �� �ؽ�Ʈ ����
					
					rightAnswerJl.setText("");					// ���� �� �ؽ�Ʈ ����
					
					bg.clearSelection();						// ��� ���� ��ư�� ������ ����
					setOption();								// ������ ���� ���� �缳��
					for (int i=0; i<5; i++) {
						option[i].setEnabled(true);				// ��� ���� ��ư Ȱ��ȭ
					}
					
					check.setText("���� Ȯ��");			// check ��ư Text ����
					check.setActionCommand("���� Ȯ��");	// check ��ư ActionCommand ����
				}
				break;
		}
	}
}