/*
 * ���� :	�������� ����ϴ� GUI Ŭ����
 * 
 * '�̱��� ����'���� �ۼ���.
 * �� Ŭ�����κ��� ��ü�� ���� �� �����Ǿ �������� ����ϴ� �����ڴ� ���� �� ���� ����.
 * ȭ�� ��ȯ �޼ҵ带 ����Ͽ� ����� �Է¿� ���� ȭ���� �����.
 */

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame {
    // ������ ��ü ����
    JFrame frame = new JFrame();
    
    // ù ȭ���� ���� �������� ����ϴ� ������ (�̱��� ���Ͽ��� �����ڴ� private�� ����)
    private MainFrame() {
        // �����ӿ� �α��� �����̳� �߰�
        frame.add(new Login());
        
        // ������� ȭ�� ũ�⸦ ��������
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        
        // �������� �߾ӿ� ��ġ�ϱ� ���� ��ǥ x, y
        int x = (screen.width - 400) / 2;
        int y = (screen.height - 700) / 2;
        
        // ȭ�鿡 ���̱�
        frame.setTitle("������ �ܾ���");
        frame.setSize(400, 700);
        frame.setResizable(false); 	// ȭ�� ũ�� ���� �Ұ���
        frame.setLocation(x, y);	// ȭ�� �߾ӿ� ���
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // �̱��� ���� ���� ���(1/2)
    public static MainFrame getInstance() {
        return LazyHolder.INSTANCE;
    }

    // �̱��� ���� ���� ���(2/2)
    private static class LazyHolder {
        public static final MainFrame INSTANCE = new MainFrame();
    }

    // Ŭ���� ��ư�� �ش��ϴ� �����̳ʷ� ��ȯ�ϴ� �޼ҵ�(�����ε�)
    public void ChangeGUI(String container) {
    	// ���� �������� ��� ������Ʈ�� ����
        frame.getContentPane().removeAll();
        
        // �ش��ϴ� �����̳ʸ� �����ӿ� �߰�
        switch (container) {
            case "����ȭ��":
            	frame.add(new MainScreen()); // �����ӿ� ����ȭ�� �����̳� �߰�
                break;
            
            case "�α���":
                frame.add(new Login()); // �����ӿ� �α��� �����̳� �߰�
                break;
            
            case "ȸ������":
                frame.add(new SignUp()); // �����ӿ� ȸ������ �����̳� �߰�
                break;
            
            case "ȸ������":
                frame.add(new Profile()); // �����ӿ� ȸ������ �����̳� �߰�
                break;
            
            case "ȸ����������":
            	frame.add(new EditProfile()); // �����ӿ� ȸ������ ���� �����̳� �߰�
                break;
            
            case "��й�ȣ����":
                frame.add(new EditPassword()); // �����ӿ� ��й�ȣ ���� �����̳� �߰�
                break;
            
            case "ȸ��Ż��":
                frame.add(new DeleteMember()); // �����ӿ� ȸ��Ż�� �����̳� �߰�
                break;
            
            case "����":
                frame.add(new SelectQuiz()); // �����ӿ� ���� ���� �����̳� �߰�
                break;
        }
        
        // ������Ʈ ���ġ
        frame.revalidate();
        
        // ȭ�� �ٽ� ���
        frame.repaint();
    }
    
    // Ŭ���� ��ư�� �ش��ϴ� �����̳ʷ� ��ȯ�ϴ� �޼ҵ�(�����ε�)
    public void ChangeGUI(String container, String note_name) {
    	// ���� �������� ��� ������Ʈ�� ����
        frame.getContentPane().removeAll();
        
        // �ش��ϴ� �����̳ʸ� �����ӿ� �߰�
        switch (container) {
	        case "��Ʈ":
	            frame.add(new Note(note_name)); // �����ӿ� ��Ʈ �����̳� �߰�
	            break;
	        
	        case "�ܾ��߰�":
                frame.add(new AddEnglish(note_name)); // �����ӿ� �ܾ� �߰� �����̳� �߰�
                break;
	        
	        case "����������":
                frame.add(new ChoiceQuiz(note_name)); // �����ӿ� ������ ���� �����̳� �߰�
                break;
	       
	        case "�ְ��Ĵܾ�����":
                frame.add(new WordQuiz(note_name)); // �����ӿ� �ְ��� �ܾ� ���� �����̳� �߰�
                break;
	       
	        case "�ְ��Ķ�����":
                frame.add(new MeaningQuiz(note_name)); // �����ӿ� �ְ��� �� ���� �����̳� �߰�
                break;
        }
        
        // ������Ʈ ���ġ
        frame.revalidate();
        
        // ȭ�� �ٽ� ���
        frame.repaint();
    }

    // Ŭ���� ��ư�� �ش��ϴ� �����̳ʷ� ��ȯ�ϴ� �޼ҵ�(�����ε�)
    public void ChangeGUI(String container, String note_name, ArrayList<String> words) {
    	// ���� �������� ��� ������Ʈ�� ����
        frame.getContentPane().removeAll();
        
        // �ش��ϴ� �����̳ʸ� �����ӿ� �߰�
        switch (container) {
            case "�ܾ����":
                frame.add(new DeleteEnglish(note_name, words)); // �����ӿ� �ܾ� ���� �����̳� �߰�
                break;
        }
        
        // ������Ʈ ���ġ
        frame.revalidate();
        
        // ȭ�� �ٽ� ���
        frame.repaint();
    }
}