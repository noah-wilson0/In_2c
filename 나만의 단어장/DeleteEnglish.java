/*
 * ���� : ���ܾ �����ϴ� GUI Ŭ����
 * 
 * �����̳� ���·� �ۼ���.
 * �̺�Ʈ�� GUI Ŭ���� ������ ó����.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class DeleteEnglish extends Container implements ActionListener {
    String noteName="";
    ConnectDB cb=new ConnectDB();
    JTextArea jta;

    // �����̳� ȭ���� �����ϴ� ������
    public DeleteEnglish(String noteName, ArrayList<String> del_words){
        this.noteName=noteName;
        setLayout(new FlowLayout(FlowLayout.CENTER));

        //���� �г�
        JPanel titleJp=new JPanel();
        titleJp.setPreferredSize(new Dimension(320, 100));

        //����
        JLabel title=new JLabel(noteName+": �ܾ� �����ϱ�");
        title.setFont(new Font("����", Font.BOLD, 20));  //���� ��Ʈ ����
        titleJp.add(title);
        add(titleJp);

        // üũ�� �ܾ����  JTextArea �� �߰��ϱ�
        jta=new JTextArea(10,20);

        jta.setEditable(false); // �б� �������� ����

        for (String word : del_words) {		// word�� del_words�� ��Ҹ� ���ʴ�� ����
            jta.append(word+"\n");				// word�� �ؽ�Ʈ ���� ���
        }
        
        //üũ�� �ܾ���� ��ũ���ҿ� ���
        JScrollPane delEnglishScrollPane = new JScrollPane(jta);
        add(delEnglishScrollPane);

        //��ư �г�
        JPanel btnJp=new JPanel();
        btnJp.setPreferredSize(new Dimension(320,100));

        //�����ϱ�
        JButton btn_del=new JButton("����");
        btn_del.addActionListener(this);
        btnJp.add(btn_del);

        //��Ʈȭ������ ���ư���
        JButton btn_cancel=new JButton("���");
        btn_cancel.addActionListener(this);
        btnJp.add(btn_cancel);

        add(btnJp);
    }

    // �̺�Ʈ�� ó���ϴ� �޼ҵ�
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame mf = MainFrame.getInstance();     // ���� ������ Ŭ�����κ��� ��ü ���� (������ ȣ�� X - �̱��� ����)
        if(e.getActionCommand().equals("����")){
            //������ �ܾ ��������
            String selected_Word=jta.getText();
            String[] del_wordList=selected_Word.split("\n");
            cb.deleteWords(GetID.id, noteName, del_wordList);

            mf.ChangeGUI("��Ʈ", noteName); 	// �����̳ʸ� ��ȯ�ϴ� �޼ҵ忡 Ŭ���� ��ư�� �̸��� �Ű������� ����
        }
        else if (e.getActionCommand().equals("���")) {
            mf.ChangeGUI("��Ʈ", noteName); 	// �����̳ʸ� ��ȯ�ϴ� �޼ҵ忡 Ŭ���� ��ư�� �̸��� �Ű������� ����
        }
    }
}