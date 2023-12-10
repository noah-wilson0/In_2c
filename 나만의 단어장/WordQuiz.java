/*
 * ���� : �ְ��� �ܾ� ��� Ǫ�� GUI Ŭ����
 * 
 * �����̳� ���·� �ۼ���.
 * �̺�Ʈ�� GUI Ŭ���� ������ ó����.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class WordQuiz extends Container implements ActionListener {
    String noteName="";
    JTextField answerJtf; //�Է¹���  JTextField ����
    ConnectDB cb=new ConnectDB();   //db��ɾ� ����ϱ�
    String wordList[][]; //db���� ���� �迭 ����
    JLabel wordJl; //�ܾ� ���̺� ����
    String mean; //�� ���ڿ� ����;
    JLabel rightAnswerJl;  //���� ���̺� ����
    
    //���� �ܾ�,�� �迭�� �ε���
    int currentIndex=0;
    
    // �����̳� ȭ���� �����ϴ� ������
    public WordQuiz(String noteName) {
        this.noteName=noteName;
        
        // ���̾ƿ� ��ü ����
        FlowLayout fLoC1 = new FlowLayout(FlowLayout.CENTER, 0, 0);
        FlowLayout fLoL1 = new FlowLayout(FlowLayout.LEFT, 0, 30);
        FlowLayout fLoL2 = new FlowLayout(FlowLayout.LEFT, 0, 0);
        BorderLayout bLo1 = new BorderLayout(10,10);

        // ��Ʈ ��ü ����
        Font titleFt = new Font("����", Font.BOLD, 24);
        Font mainFt = new Font("����", Font.PLAIN, 14);
        Font wordFt = new Font("����", Font.BOLD, 32);
        Font guideFt = new Font("����", Font.BOLD, 14);

        // �����̳� ���̾ƿ� ����
        setLayout(fLoC1);

        // ���� ȭ���� �����ϴ� �г�
        JPanel titleJp = new JPanel(); 								// ��ü ����
        titleJp.setLayout(fLoL1);									// ���̾ƿ� ����
        titleJp.setPreferredSize(new Dimension(300, 180));			// ũ�� ���� - new Dimension(�ʺ�, ����)

        JLabel titleJl = new JLabel("�ְ��� �ܾ� ����");					// �� ��ü ����
        titleJl.setFont(titleFt);									// �� ��Ʈ ����
        titleJl.setPreferredSize(new Dimension(300, 30));			// �� ũ�� ����
        titleJp.add(titleJl);										// �гο� �� �߰�

        JLabel noteJl = new JLabel("��Ʈ : \"" + noteName + "\"");	// �� ��ü ����
        noteJl.setFont(mainFt);										// �� ��Ʈ ����
        noteJl.setPreferredSize(new Dimension(300, 20));			// �� ũ�� ����
        titleJp.add(noteJl);										// �гο� �� �߰�

        // �ܾ �����ִ� �г�
        JPanel wordJp = new JPanel(); 							// ��ü ����
        wordJp.setLayout(fLoC1);								// ���̾ƿ� ����
        wordJp.setPreferredSize(new Dimension(300, 100));		// ũ�� ���� - new Dimension(�ʺ�, ����)

        wordList =cb.selectWordAndMeaning(GetID.id,noteName);	//���� ������ �ִ� �ܾ �迭�� ����
        
        wordJl = new JLabel(wordList[currentIndex][0]);			// �� ��ü ���� - ���ܾ�
        mean=wordList[currentIndex][1];  //���ܾ �ش��ϴ� �� ����
        wordJl.setFont(wordFt);								// �� ��Ʈ ����
        wordJp.add(wordJl);									// �гο� �� �߰�

        // ����� �Է¹޴� �г�
        JPanel answerJp = new JPanel(); 								// ��ü ����
        answerJp.setLayout(fLoL2);										// ���̾ƿ� ����
        answerJp.setPreferredSize(new Dimension(300, 100));				// ũ�� ���� - new Dimension(�ʺ�, ����)

        JLabel guideJl1 = new JLabel("�� ����");							// �� ��ü ����
        guideJl1.setFont(guideFt);										// �� ��Ʈ ����
        guideJl1.setPreferredSize(new Dimension(300, 20));				// �� ũ�� ����
        answerJp.add(guideJl1);											// �гο� �� �߰�
        
        JLabel guideJl2 = new JLabel("(���� ���� ���� ��� �ϳ��� �ۼ��ϼ���.)");	// �� ��ü ����
        guideJl2.setFont(guideFt);										// �� ��Ʈ ����
        guideJl2.setPreferredSize(new Dimension(300, 30));				// �� ũ�� ����
        answerJp.add(guideJl2);											// �гο� �� �߰�

        answerJtf = new JTextField();									// �ؽ�Ʈ �ʵ� ��ü ����
        answerJtf.setPreferredSize(new Dimension(300, 30));				// �ؽ�Ʈ �ʵ� ũ�� ����
        answerJp.add(answerJtf);										// �гο� �ؽ�Ʈ �ʵ� �߰�

        // ������ �����ִ� �г�
        JPanel rightAnswerJp = new JPanel(); 						// ��ü ����
        rightAnswerJp.setLayout(fLoL2);								// ���̾ƿ� ����
        rightAnswerJp.setPreferredSize(new Dimension(300, 100));	// ũ�� ���� - new Dimension(�ʺ�, ����)

        rightAnswerJl = new JLabel("���� : ");						// �� ��ü ����
        rightAnswerJl.setFont(guideFt);								// �� ��Ʈ ����
        rightAnswerJl.setPreferredSize(new Dimension(300, 30));		// �� ũ�� ����
        rightAnswerJp.add(rightAnswerJl);							// �гο� �� �߰�

        // ��ư�� ������ �г�
        JPanel buttonJp = new JPanel(); 					// ��ü ����
        buttonJp.setLayout(bLo1);							// ���̾ƿ� ����
        buttonJp.setPreferredSize(new Dimension(300, 40));	// ũ�� ���� - new Dimension(�ʺ�, ����)

        JButton exit = new JButton("������");					// ��ư ��ü ����
        buttonJp.add(exit, BorderLayout.WEST);				// �гο� ��ư �߰�

        JButton next = new JButton("���� ����");				// ��ư ��ü ����
        buttonJp.add(next, BorderLayout.CENTER);			// �гο� ��ư �߰�

        JButton check = new JButton("���� Ȯ��");				// ��ư ��ü ����
        buttonJp.add(check, BorderLayout.EAST);				// �гο� ��ư �߰�

        // ��ư�� �̺�Ʈ ������ �߰�
        exit.addActionListener(this);
        next.addActionListener(this);
        check.addActionListener(this);

        // �����̳ʿ� �г� �߰�
        add(titleJp);
        add(wordJp);
        add(answerJp);
        add(rightAnswerJp);
        add(buttonJp);

    }

    // �̺�Ʈ�� ó���ϴ� �޼ҵ�
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame mf = MainFrame.getInstance();		// ȭ���� ��ȯ�ϴ� �޼ҵ带 ȣ���ϱ� ���� ��ü ����
        String meanList[]=mean.split(", "); // ["��1","��2",...] ���� �迭�� �����
        String answer=answerJtf.getText(); //�亯 ���ڿ��� ��ȯ
        boolean correctAnswer = false; //������ ������ true
        
        if(e.getActionCommand().equals("���� Ȯ��")){
            for (int i = 0; i < meanList.length; i++) { //�� ���̸�ŭ �ݺ�
                if(answer.equals(meanList[i])){  //�Է��� ���� ������
                    correctAnswer=true;
                    JOptionPane.showMessageDialog(this, "�����Դϴ�!", "����", JOptionPane.INFORMATION_MESSAGE);
                    rightAnswerJl.setText("����: "+mean);
                    break; //�Է��� ���� ������ �ݺ��� �߷�
                }
            }
            if(!correctAnswer) {  // �Է��� ���� Ʋ���� ��
                if(meanList.length==1) {
                    JOptionPane.showMessageDialog(this, "Ʋ�Ƚ��ϴ�. ������ " + mean + "�Դϴ�.", "����", 
                    							  JOptionPane.INFORMATION_MESSAGE);
                    rightAnswerJl.setText("����: " + mean);
                }
                else if(meanList.length>1) {
                    JOptionPane.showMessageDialog(this, "Ʋ�Ƚ��ϴ�. ������ " + mean + "�Դϴ�.", "����", 
                    							  JOptionPane.INFORMATION_MESSAGE);
                    rightAnswerJl.setText("����: " + mean);
                }
            }
        }
        else if (e.getActionCommand().equals("���� ����")) {
            if (wordList != null && wordList.length > 0 &&currentIndex<wordList.length-1) {
                currentIndex = currentIndex + 1; // ���� �ܾ� �ε��� ���
                answerJtf.setText(""); // answerJtf �ʱ�ȭ
                rightAnswerJl.setText("����: "); //rightAnswerJl �ʱ�ȭ
                wordJl.setText(wordList[currentIndex][0]);  // ���� �ܾ� ǥ��
                mean = wordList[currentIndex][1];
            }
            else if(currentIndex>=wordList.length-1){
                JOptionPane.showMessageDialog(this, "���� �ܾ �����ϴ�.(�ܾ �� �߰��ϼ���)", "�ܾ� ���� �Ϸ�", 
                							  JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else if (e.getActionCommand().equals("������")) {
            mf.ChangeGUI("����");
        }
    }
}