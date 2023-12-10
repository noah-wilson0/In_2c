/*
 * ���� : ����ȭ�����μ�, ��Ʈ ��ϸ� ��ȸ�ϰ� �پ��� ��ɵ��� ������ �� �ִ� GUI Ŭ����
 * 
 * �����̳� ���·� �ۼ���.
 * ��Ʈ ���� / ��Ʈ �߰� / ��Ʈ ���� / �ܾ� �˻� / ȸ������ ��ȸ ȭ������ �̵� / Ȩ ȭ������ �̵� / ���� ȭ������ �̵� ����� ������.
 * ��Ʈ ���ð� ��Ʈ�߰�����Ʈ�������ܾ�˻� ��ư�� �̺�Ʈ�� ���� Ŭ���� ������ ó����.
 * ȸ��������Ȩ������ ��ư�� �̺�Ʈ��  GUI Ŭ���� ������ ó����.
 */

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import static java.awt.BorderLayout.*;
import static java.awt.BorderLayout.SOUTH;

public class MainScreen extends Container implements ActionListener {
	//��Ʈ ��ü ����
    Font main_btn_Font = new Font("����", Font.PLAIN, 16);
    
    JPanel Home_jp;
    JPanel Menu_jp;
    
    //��Ʈ�� �����Ҷ����� ��Ʈ �̸��� ���� �迭
    ArrayList<String> note_NameLists = new ArrayList<String>();
    
    //NoteŬ�������� �Ѱ� ���� ��Ʈ ����
    String clicked_Note="";  //Note_jp���� Ŭ���� ��Ʈ �̸��� ������ ����
    
    //�� �����ӿ� 13���� �ִ� ��·�
    //JList�� �̿��� ��Ʈ ��� ����
    DefaultListModel<String> myList = new DefaultListModel<>();
    //DefaultListModel�� ����ϸ� �������� �߰� �Ǵ� ������ �߻��� ������ �ڵ����� ���� ������Ʈ ����
    //���� ��� ArrayList ���� �������� �߰� �Ǵ� ������ �߻��Ҷ� ���� ���������� ���� ������Ʈ �ؾ���
    JList<String> myLists = new JList<>(myList);
    //JList�� Swing ������Ʈ �� �ϳ���, ���� �׸��� ��Ÿ���� ����Ʈ�� ������ �� �����

    ArrayList<String> noteList = new ArrayList<>();

    //�˾�â���� ���޹��� ��Ʈ �̸� ����
    String add_note_Name ="";
    
    //�˾�â���� ���޹��� ������ ��Ʈ �̸� ����
    String del_note_Name ="";
    
    //db��ɾ� ����� ���� db��ü ����
    ConnectDB cb=new ConnectDB();
    
    // �����̳� ȭ���� �����ϴ� ������
    public MainScreen() {
        setLayout(new BorderLayout());        // ���̾ƿ� ����

        // 1.� �ܾ�������...(�ֻ��)
        Font fnt = new Font("����", Font.BOLD, 20); // �� �ܾ��� ��Ʈ
        // Width: 384, Height: 43
        Home_jp = new JPanel(); // ���� ������ �Ͽ� Note_Name�� ���ʿ� ǥ��
        Home_jp.setBackground(Color.white); //������ �Ͼ������

        String user_name=cb.selectName(GetID.id);
        JLabel Note_Name = new JLabel(user_name+"�� �ܾ���");
        Note_Name.setFont(fnt);

        // �۷ι� �ܾ� �˻� ��ư(24x24)
        ImageIcon img_search = new ImageIcon("./btn_img/btn_search.png");
        JButton btn_serch = new JButton(img_search);
        btn_serch.setPreferredSize(new Dimension(30, 30));
        btn_serch.addActionListener(new serchWord());

        // ��Ʈ �߰� ��ư
        ImageIcon img_notePlus = new ImageIcon("./btn_img/btn_notePlus.png");
        JButton btn_notePlus = new JButton(img_notePlus);
        btn_notePlus.setFont(main_btn_Font);
        btn_notePlus.setPreferredSize(new Dimension(30, 30));
        
        //��Ʈ �߰� �̺�Ʈ
        btn_notePlus.addActionListener(new add_NoteTable());

        //��Ʈ ���� ��ư
        ImageIcon img_delNote = new ImageIcon("./btn_img/btn_noteDel.png"); //��Ʈ ���� ������
        JButton btn_delNote=new JButton(img_delNote);//��Ʈ ���� ������
        btn_delNote.setPreferredSize(new Dimension(30, 30));

        //��Ʈ ���� �̺�Ʈ
        btn_delNote.addActionListener(new del_NoteTable());

        //ȸ������ ��ư
        ImageIcon img_Info = new ImageIcon("./btn_img/btn_info.png");
        JButton btn_Info = new JButton(img_Info);
        btn_Info.setPreferredSize(new Dimension(30, 30));
        btn_Info.setActionCommand("ȸ������");
        btn_Info.addActionListener(this);

        // Box ���̾ƿ����� ��ü�� ����(vertical)���� ��ġ
        Box verticalBox = Box.createVerticalBox();

        // ���ʿ� ��ġ�� Box
        Box topBox = Box.createHorizontalBox();
        topBox.add(Note_Name);

        // �Ʒ��ʿ� ��ġ�� Box
        Box bottomBox = Box.createHorizontalBox();
        bottomBox.add(Box.createHorizontalGlue());  // ���� ���� �� ����
        bottomBox.add(Box.createHorizontalStrut(230));
        bottomBox.add(btn_serch);
        bottomBox.add(Box.createHorizontalStrut(10));  // �߰��� ���� �߰� (10 �ȼ� ����)
        bottomBox.add(btn_notePlus);
        bottomBox.add(Box.createHorizontalStrut(10));  // �߰��� ���� �߰� (10 �ȼ� ����)
        bottomBox.add(btn_delNote);
        bottomBox.add(Box.createHorizontalStrut(10)); // �߰��� ���� �߰� (10 �ȼ� ����)
        bottomBox.add(btn_Info);
        
        // ��ü�� �������� ��ġ
        verticalBox.add(topBox);
        verticalBox.add(bottomBox);
        
        // JFrame�� �߰�
        Home_jp.add(verticalBox);

        //2.��Ʈó�� ���̴� ����(����)�� �Ϸ�(�ߴ�)
        JScrollPane Note_jp=new JScrollPane(myLists);  //�� �����ӿ� 13���� �ִ� ��·�
        Note_jp.setBackground(Color.white); //������ �Ͼ������

        // JList�� ���ڻ� ����
        myLists.setForeground(Color.black); // �ؽ�Ʈ�� ���ڻ��� ���������� ����

        // Border ���� -Note_jp�� �����ϱ� ����
        Border border = BorderFactory.createLineBorder(Color.black, 2); // �׵θ� ������ ����������, �β��� 2�� ����

        // JList�� Border ����
        Note_jp.setBorder(border);

        //�������� ����Ͽ� JList�� �� ���̾ƿ��� ����
        myLists.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, 
            											  boolean isSelected, boolean cellHasFocus) {
            	// DefaultListCellRenderer�� getListCellRendererComponent �޼��带 ȣ��
            	Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, 
            															cellHasFocus);
                /*
                list: ���� �������� ���� JList�Դϴ�. �̸� ���� ���� JList�� �Ӽ��̳� �����Ϳ� ������ �� �ִ�.
                value: ���� ���� ǥ�õǴ� �������Դϴ�. ���� JList���� �� �׸��� �ؽ�Ʈ �Ǵ� ����� ���� ��ü�� �� �ִ�.
                index: ���� ���� �ε����Դϴ�. �� ��° �׸������� ��Ÿ����.
                isSelected: ���� ���� ���õǾ����� ���θ� ��Ÿ���� �Ҹ��� ���̴�. �̸� ���� ���õ� �׸� ���� �ð��� ȿ���� ������ �� �ִ�.
                cellHasFocus: ���� ���� ��Ŀ���� �������� ���θ� ��Ÿ���� boolean ��. ��Ŀ���� �ִ� ���� ���� Ư���� ��Ÿ���� ������ �� �ִ�.
                */
            	
            	// ���ϴ� ��Ʈ�� ��Ÿ�Ϸ� ����
                setFont(main_btn_Font);
                
                // ȭ�� �������� ���� ä�쵵�� ������ ����
                renderer.setPreferredSize(new Dimension(360, 40)); // ��Ʈ���� ȭ���� �������� ���� ä��� ����(380,40)
                setBorder(BorderFactory.createLineBorder(Color.black)); // �׵θ� �߰�
                return this;
            }
        });
        
        //Jlist�� �������� Ŭ���ϸ�...
        myLists.addMouseListener(new MyListsMouseListener());
        
        //DB�� �ִ� ��Ʈ �̸��� note_NameLists, myList�� �߰�
        String note_names = cb.selectNoteList(GetID.id);
        if (note_names != null) {
        	String noteList[] = note_names.split("\\|");  //"|"�� ���е�  note�� �ٽ� "|"�� �����Ͽ� �迭 ����
            for (int i = 0; i < noteList.length; i++) {
            	note_NameLists.add(noteList[i]);
            	myList.addElement(noteList[i]);
            }
        }

        //3.Ȩȭ��, ������ Ŭ���ϸ� �ٸ� ȭ������(�ϴ�)
        Menu_jp=new JPanel();
        Menu_jp.setBackground(Color.white); //������ �Ͼ������

        Box Menu_Box = Box.createHorizontalBox();

        //�޴� ������ ��������(24x24)
        ImageIcon img_home = new ImageIcon("./btn_img/btn_home.png");
        ImageIcon img_game = new ImageIcon("./btn_img/btn_game.png");
        
        //�޴� ����-Ȩ(������ Ȩȭ��), ����(���ܾ�����)
        JButton btn_home = new JButton(img_home);
        JButton btn_quiz = new JButton(img_game);
        
        //��ư ������ ����
        btn_home.setPreferredSize(new Dimension(30, 30));
        btn_quiz.setPreferredSize(new Dimension(30, 30));
        
        // �ڽ��� �߰�
        Menu_Box.add(btn_home);
        Menu_Box.add(btn_quiz);
        
        //��ư�� �޴� �гο� �߰�
        Menu_jp.add(Menu_Box);
        Menu_jp.setBorder(BorderFactory.createEmptyBorder(20 , 10 , 10 , 10));
        
        //Ȩ ȭ�� ����
        add(Home_jp, NORTH);
        add(Note_jp, CENTER);
        add(Menu_jp, SOUTH);
        
        //��ư Ŭ����...
        btn_home.setActionCommand("����ȭ��");
        btn_quiz.setActionCommand("����");
        
        //��ư Ŭ���� �̺�Ʈ �߻�...
        btn_home.addActionListener(this);
        btn_quiz.addActionListener(this);
    }
    
    // �̺�Ʈ ó�� ��ƾ �ۼ� - ȸ������, Ȩ, ���� ��ư
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame mf = MainFrame.getInstance();		// ���� ������ Ŭ�����κ��� ��ü ���� (������ ȣ�� X - �̱��� ����)
        
        if (e.getActionCommand().equals("����")) {	// ���� ��ư Ŭ�� ��
        	String note_names = cb.selectNoteList(GetID.id);	// DB�� �߰��� ��Ʈ���� ��ȸ
        	if (note_names == null) {							// ��Ʈ�� �������� ������..
        		JOptionPane.showMessageDialog(null, "��� Ǯ�� ���� ��Ʈ�� �������� �ʽ��ϴ�.\n��Ʈ�� �߰����ּ���.");
        	}
        	else {												// ��Ʈ�� �����ϸ�..
        		mf.ChangeGUI("����");		// ���� ���� ȭ������ �̵�
        	}
        }
        else {										// ���� �� �ٸ� ��ư Ŭ�� ��
        	mf.ChangeGUI(e.getActionCommand());			// �ش� ȭ������ �̵�
        }
    }
    
    // �̺�Ʈ �����ʸ� ���� Ŭ������ ���� - ��Ʈ ����Ʈ
    private class MyListsMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            int selectedIndex = myLists.getSelectedIndex();
            
            if (selectedIndex == -1) { // ���õ� ��Ұ� ������ �ƹ��͵� ���� ����
                myLists.clearSelection(); // ������ Jlist Ŀ���� ������
                return;
            }
            else if (e.getClickCount() == 1 || e.getClickCount() == 2) { // ���콺 Ŭ�� Ƚ���� 1 �Ǵ� 2���� ��쿡�� ó��
                int note_size = myLists.getModel().getSize();

                if (selectedIndex < note_size) {
                    // ������ Jlist�� �� ��µǴ���
                    // Ŭ���� ��Ʈ ��������
                    clicked_Note = myLists.getModel().getElementAt(selectedIndex);

                    // Ŭ���� ��Ʈ ������ �ҷ�����
                    MainFrame mf = MainFrame.getInstance();
                    mf.ChangeGUI("��Ʈ", clicked_Note);
                    myLists.clearSelection(); // ������ Jlist Ŀ���� ������
                }
            }
            else if (e.getClickCount() > 2) {
                System.out.println("��Ʈ Ŭ��: �����߻�");
                myLists.clearSelection(); // ������ Jlist Ŀ���� ������
            }
        }
    }

    //��Ʈ �߰� ��ư Ŭ����... ��Ʈ ���̺� ����(�̺�Ʈ ó���� ���� ���� Ŭ���� ����)
    private class add_NoteTable implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	ConnectDB cb = new ConnectDB();
            // ���⿡ ��Ʈ �߰� ����� ����
            // �� ��Ʈ�� �����ϰ� ��Ʈ ��Ͽ� �߰��ϴ� �ڵ带 �ۼ���
            //�� ��Ʈ�� �̸� �Է¹ޱ�
            String newNoteName="";
            add_note_Name = JOptionPane.showInputDialog(null,"��Ʈ �̸��� �Է��ϼ���:", "��Ʈ �̸� �Է�", 
            											JOptionPane.PLAIN_MESSAGE);
            if (add_note_Name != null && !add_note_Name.isEmpty()) {
                newNoteName = add_note_Name; // �� ��Ʈ�� �̸�
                //��Ʈ �߰�
                cb.addNote(GetID.id,newNoteName); //������� ��Ʈ�� table�߰�
                note_NameLists.add(newNoteName);   //note_NameLists�� ���� ������ ��Ʈ�̸� �߰�
                myList.addElement(newNoteName);
            }
            else{
                JOptionPane.showMessageDialog(null, "��Ʈ �̸��� �Է����� �ʾҽ��ϴ�.", "���", 
                							  JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    //��Ʈ ���� ��ư Ŭ����... ��Ʈ ���̺� ����(�̺�Ʈ ó���� ���� ���� Ŭ���� ����)
    private class del_NoteTable implements ActionListener {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        //��Ʈ ���� ��ư Ŭ����.. � ��Ʈ�� �������� ��Ʈ �̸��� �˾�â���� ����
	        del_note_Name = JOptionPane.showInputDialog(null,"��Ʈ �̸��� �Է��ϼ���:", "��Ʈ �̸� �Է�", 
	        											JOptionPane.PLAIN_MESSAGE);
	
	        if (del_note_Name != null && !del_note_Name.isEmpty() ) {
	            //������ �����ϴ� ��Ʈ���� �˻�
	            if(note_NameLists.contains(del_note_Name)) {
	                //��Ʈ ���� �̺�Ʈ
	                ConnectDB cb = new ConnectDB();
	                cb.deleteNote(GetID.id, del_note_Name);
	                // ���� �Ϸ�� ����Ʈ���� ��Ʈ �̸� ����
	                myList.removeElement(del_note_Name);
	            }
	            else{
	                JOptionPane.showMessageDialog(null, "������ ��Ʈ �̸��� �ٸ��� �Է��Ͻÿ�.", "���", 
	                							  JOptionPane.WARNING_MESSAGE);
	            }
	        }
	        else{
	            JOptionPane.showMessageDialog(null, "������ ��Ʈ �̸��� �Է����� �ʾҽ��ϴ�.", "���", 
	            							  JOptionPane.WARNING_MESSAGE);
	        }
	    }
    }
    
    //��ư search ��ư Ŭ����...(�̺�Ʈ ó���� ���� ���� Ŭ���� ����)
    private class serchWord extends Component implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //ã�� �ܾ� �Է� �ޱ�
            String searchingWord;
            searchingWord = JOptionPane.showInputDialog(this, "�˻��� �ܾ �Է��ϼ���.", "�ܾ� �˻�", 
            											JOptionPane.INFORMATION_MESSAGE);
            
            if (searchingWord != null) { //ã�� �ܾ �Է� ������...
                String[] result_word;
                result_word = cb.searchWord(GetID.id, searchingWord);
                if (result_word[0] != null) {
                    System.out.println("ã�� �ܾ�:"+result_word[0]);
                    JOptionPane.showMessageDialog(null,"�ܾ ã�ҽ��ϴ�.("+result_word[2]+"�� �ֽ��ϴ�.)");
                }
                else {
                    System.out.println("ã�� �ܾ� ����");
                    JOptionPane.showMessageDialog(null,"ã�� �ܾ �����ϴ�.(�Է��� �ܾ�:"+searchingWord+")");
                }
            }
            else {
                System.out.println("����ڰ� ����ϰų� �� ���ڿ��� �Է��߽��ϴ�.");
            }
        }
    }
}