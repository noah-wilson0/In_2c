/*
 * ���� : ��Ʈ�� �߰��� ��� ���ܾ ��ȸ�ϰ� �پ��� ��ɵ��� ������ �� �ִ� GUI Ŭ����
 * 
 * �����̳� ���·� �ۼ���.
 * �ܾ� �߰� ȭ������ �̵� / ������ �ܾ� ���� / �ܾ� ���� ȭ������ �̵� / Ȩ ȭ������ �̵� / ���� ȭ������ �̵� ����� ������.
 * �ܾ��߰����ܾ������Ȩ������ ��ư�� �̺�Ʈ�� GUI Ŭ���� ������ ó����.
 * ������ �ܾ� ������ �̺�Ʈ�� ���� Ŭ���� ������ ó����.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import static java.awt.BorderLayout.*;

public class Note extends Container implements ActionListener {
    String noteName; //�̺�Ʈ���� ���� ����
    
    //�ܾ��� ������ �Է¹ޱ� ���� ����
    int count=0;
    
    //������ �ܾ �����ϱ� ���� üũ�ڽ� �迭 ��ü ����
    JCheckBox[] wordCb;
    
    //üũ�ڽ��� üũ�� �ܾ ����ڰ� �߰��� �ܾ ���� �迭�� ����
    ArrayList<String> words=new ArrayList<String>();
    
    // �����̳� ȭ���� �����ϴ� ������
    public Note(String noteName){
        this.noteName=noteName;
        
        setLayout(new BorderLayout());
        
        //��� �г� - top_jp
        JPanel top_jp=new JPanel();

        //���(���� ����)- ��Ʈ�̸�
        JLabel note_title = new JLabel("���� ��Ʈ: " + noteName);
        note_title.setFont(new Font("����", Font.BOLD, 20));
        
        //���(�Ʒ�) - ��Ʈ ����, [�ܾ� �߰�(EnglishAdd  ���������� ��ȯ)],�ܾ� ����
        //�̹���
        ImageIcon img_addWord = new ImageIcon("./btn_img/btn_addWord.png"); //�ܾ� �߰� ������
        ImageIcon img_delWord = new ImageIcon("./btn_img/btn_delWord.png"); //�ܾ� ���� ������
        
        //��ư
        JButton btn_addWord=new JButton(img_addWord);//�ܾ� �߰� ������
        JButton btn_delWord=new JButton(img_delWord);//�ܾ� ���� ������

        //��ư ������ 30x30 ����
        btn_addWord.setPreferredSize(new Dimension(30, 30));
        btn_delWord.setPreferredSize(new Dimension(30, 30));

        // top_jp ���̾ƿ� : Box
        // Box ���̾ƿ����� ��ü�� ����(vertical)���� ��ġ
        Box verticalBox = Box.createVerticalBox();
        
        //�ڽ��� ���(����)�� ��ġ�� Box
        Box topBox = Box.createHorizontalBox();
        topBox.add(note_title);

        // �ڽ��� �ϴ�(�Ʒ���)�� ��ġ�� Box
        Box bottomBox = Box.createHorizontalBox();
        bottomBox.add(Box.createHorizontalGlue());  // ���� ���� �� ����
        bottomBox.add(Box.createHorizontalStrut(260));
        bottomBox.add(Box.createHorizontalStrut(10));  // �߰��� ���� �߰� (10 �ȼ� ����)
        bottomBox.add(btn_addWord); //�ܾ� �߰� ��ư
        bottomBox.add(Box.createHorizontalStrut(10)); // �߰��� ���� �߰� (10 �ȼ� ����)
        bottomBox.add(btn_delWord); //�ܾ� ���� ��ư
        
        // ��ü�� �������� ��ġ
        verticalBox.add(topBox);
        verticalBox.add(bottomBox);
        
        // JFrame�� �߰�
        top_jp.add(verticalBox);
        
        //��� �г� �����̳ʿ� �߰�
        add(top_jp);
        
        //�ܾ� �߰�/���� ��ư ��ȯ�� ����
        btn_addWord.setActionCommand("�ܾ��߰�");
        btn_delWord.setActionCommand("�ܾ����");
        
        //�ܾ� �߰�/���� ��ư �̺�Ʈ
        btn_addWord.addActionListener(this);
        btn_delWord.addActionListener(this);

        //�ߴ� - �ܾ� ��ȸ(��ũ���� ���)
        //������ - �ܾ� ���
        ConnectDB cb=new ConnectDB();  //db����

        // ��Ʈ ��ü ����
        Font wordFt = new Font("����", Font.BOLD, 24);
        Font mainFt = new Font("����", Font.PLAIN, 13);

        // ���� ��ü ����
        Color mainCr = new Color(100, 100, 100);
        Color lineCr = new Color(240, 240, 240);

        // ���ܾ �����ִ� �г�
        JPanel englishJp = new JPanel(); 						// ��ü ����
        englishJp.setLayout(new FlowLayout(FlowLayout.CENTER));		// ���̾ƿ� ����

        englishJp.setBackground(Color.white);		// ���� ����

        JScrollPane middle_jp = new JScrollPane(englishJp, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
        										JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        middle_jp.setBorder(BorderFactory.createLineBorder(Color.black,2));

        String[][] english = cb.lookEnglish(GetID.id, noteName); // ��Ʈ ���̺� �ִ� ��� ���ܾ���� ���޹޴� 2���� �迭 ����
        if (english != null) {	// ��Ʈ�� ���ܾ �ִٸ�..
            count = english.length;		// ���ܾ��� ������ count�� ����

            englishJp.setPreferredSize(new Dimension(360, 131*count));	// ũ�� ���� - new Dimension(�ʺ�, ����)

            wordCb = new JCheckBox[count];				// üũ�ڽ� �迭 ��ü ���� (�ܾ�)
            JLabel[] partJl = new JLabel[count];		// �� �迭 ��ü ���� (ǰ��)
            JLabel[] meanJl = new JLabel[count];		// �� �迭 ��ü ���� (��)
            JLabel[] line = new JLabel[count];			// �� �迭 ��ü ���� (��輱)

            for (int i=0; i<count; i++) {	// ��Ʈ�� �ִ� ���ܾ��� ������ŭ �ݺ�
                wordCb[i] = new JCheckBox(english[i][0]);  // üũ�ڽ� ��ü ���� (�ܾ� ���)
                wordCb[i].setFont(wordFt);  // ��Ʈ ����
                wordCb[i].setBackground(Color.WHITE);	// ���� ����
                wordCb[i].setPreferredSize(new Dimension(386, 50));  // ũ�� ����
                wordCb[i].setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 0));  // �� ���� ���� (��, ��, ��, ��)
                englishJp.add(wordCb[i]);  // �гο� üũ�ڽ� �߰�

                partJl[i] = new JLabel(english[i][2]);  // �� ��ü ���� (ǰ�� ���)
                partJl[i].setFont(mainFt);  // ��Ʈ ����
                partJl[i].setForeground(mainCr);  // ��Ʈ ���� ����
                partJl[i].setPreferredSize(new Dimension(386, 30));  // ũ�� ����
                partJl[i].setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));  // �� ���� ���� (��, ��, ��, ��)
                englishJp.add(partJl[i]);  // �гο� �� �߰�

                meanJl[i] = new JLabel(english[i][1]);  // �� ��ü ���� (�� ���)
                meanJl[i].setFont(mainFt);  // ��Ʈ ����
                meanJl[i].setForeground(mainCr);  // ��Ʈ ���� ����
                meanJl[i].setPreferredSize(new Dimension(386, 30));  // ũ�� ����
                meanJl[i].setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 0));  // �� ���� ���� (��, ��, ��, ��)
                englishJp.add(meanJl[i]);  // �гο� �� �߰�

                line[i] = new JLabel();  // �� ��ü ���� (��輱 ���)
                line[i].setPreferredSize(new Dimension(386, 1));  // ũ�� ����
                line[i].setOpaque(true);  // ������ ���� ���� (�������� �ʾƾ� ���� ǥ�� ����)
                line[i].setBackground(lineCr);  // ���� ����
                englishJp.add(line[i]);  // �гο� �� �߰�
            }
        }

        //�ϴ�
        JPanel button_jp=new JPanel();
        button_jp.setBackground(Color.white); //������ �Ͼ������

        Box Menu_Box = Box.createHorizontalBox();

        //�޴� ������ ��������(24x24)
        ImageIcon img_home = new ImageIcon("./btn_img/btn_home.png");
        ImageIcon img_game = new ImageIcon("./btn_img/btn_game.png");
        
        //�޴� ����-Ȩ(������ Ȩȭ��), ����(�ܾ�����)
        JButton btn_home = new JButton(img_home);
        JButton btn_quiz = new JButton(img_game);
        
        //��ư ������ ����
        btn_home.setPreferredSize(new Dimension(30, 30));
        btn_quiz.setPreferredSize(new Dimension(30, 30));
        
        // �ڽ��� �߰�
        Menu_Box.add(btn_home);
        Menu_Box.add(btn_quiz);
        
        //��ư�� �޴� �гο� �߰�
        button_jp.add(Menu_Box);
        button_jp.setBorder(BorderFactory.createEmptyBorder(20 , 10 , 10 , 10));
        
        //Ȩ ȭ�� ����
        add(top_jp, NORTH);
        add(middle_jp,CENTER);
        add(button_jp, SOUTH);
        
        //��ư Ŭ����...
        btn_home.setActionCommand("����ȭ��");
        btn_quiz.setActionCommand("����");
        
        //��ư Ŭ���� �̺�Ʈ �߻�...
        btn_home.addActionListener(this);
        btn_quiz.addActionListener(this);
    }
    
    // �̺�Ʈ ó�� ��ƾ �ۼ� -this
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame mf = MainFrame.getInstance();		// ���� ������ Ŭ�����κ��� ��ü ���� (������ ȣ�� X - �̱��� ����)
        switch (e.getActionCommand()) {
            case "����ȭ��":
                mf.ChangeGUI(e.getActionCommand());		// �����̳ʸ� ��ȯ�ϴ� �޼ҵ忡 Ŭ���� ��ư�� �̸��� �Ű������� ����
                break;
            case "����":
                mf.ChangeGUI(e.getActionCommand());		// �����̳ʸ� ��ȯ�ϴ� �޼ҵ忡 Ŭ���� ��ư�� �̸��� �Ű������� ����
                break;
            case "�ܾ��߰�":
                mf.ChangeGUI("�ܾ��߰�", noteName);
                break;
            case "�ܾ����":
            	for (int i=0; i<count; i++) {
                    if (wordCb[i].isSelected()) {		// üũ�ڽ��� ���õǾ� ������..
                    	words.add(wordCb[i].getText());		// �ش� üũ�ڽ��� Text�� words�� �߰�
                    }
                }
                mf.ChangeGUI("�ܾ����", noteName, words);
                break;
        }
    }
}