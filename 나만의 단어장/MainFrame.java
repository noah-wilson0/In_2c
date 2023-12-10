/*
 * 설명 :	프레임을 출력하는 GUI 클래스
 * 
 * '싱글톤 패턴'으로 작성됨.
 * 이 클래스로부터 객체가 여러 번 생성되어도 프레임을 출력하는 생성자는 최초 한 번만 사용됨.
 * 화면 전환 메소드를 사용하여 사용자 입력에 따른 화면을 출력함.
 */

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame {
    // 프레임 객체 생성
    JFrame frame = new JFrame();
    
    // 첫 화면을 담은 프레임을 출력하는 생성자 (싱글톤 패턴에서 생성자는 private로 선언)
    private MainFrame() {
        // 프레임에 로그인 컨테이너 추가
        frame.add(new Login());
        
        // 사용자의 화면 크기를 가져오기
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        
        // 프레임을 중앙에 배치하기 위한 좌표 x, y
        int x = (screen.width - 400) / 2;
        int y = (screen.height - 700) / 2;
        
        // 화면에 보이기
        frame.setTitle("나만의 단어장");
        frame.setSize(400, 700);
        frame.setResizable(false); 	// 화면 크기 조절 불가능
        frame.setLocation(x, y);	// 화면 중앙에 출력
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // 싱글톤 패턴 관련 블록(1/2)
    public static MainFrame getInstance() {
        return LazyHolder.INSTANCE;
    }

    // 싱글톤 패턴 관련 블록(2/2)
    private static class LazyHolder {
        public static final MainFrame INSTANCE = new MainFrame();
    }

    // 클릭한 버튼에 해당하는 컨테이너로 전환하는 메소드(오버로딩)
    public void ChangeGUI(String container) {
    	// 현재 프레임의 모든 컴포넌트를 삭제
        frame.getContentPane().removeAll();
        
        // 해당하는 컨테이너를 프레임에 추가
        switch (container) {
            case "메인화면":
            	frame.add(new MainScreen()); // 프레임에 메인화면 컨테이너 추가
                break;
            
            case "로그인":
                frame.add(new Login()); // 프레임에 로그인 컨테이너 추가
                break;
            
            case "회원가입":
                frame.add(new SignUp()); // 프레임에 회원가입 컨테이너 추가
                break;
            
            case "회원정보":
                frame.add(new Profile()); // 프레임에 회원정보 컨테이너 추가
                break;
            
            case "회원정보수정":
            	frame.add(new EditProfile()); // 프레임에 회원정보 수정 컨테이너 추가
                break;
            
            case "비밀번호변경":
                frame.add(new EditPassword()); // 프레임에 비밀번호 변경 컨테이너 추가
                break;
            
            case "회원탈퇴":
                frame.add(new DeleteMember()); // 프레임에 회원탈퇴 컨테이너 추가
                break;
            
            case "퀴즈":
                frame.add(new SelectQuiz()); // 프레임에 퀴즈 선택 컨테이너 추가
                break;
        }
        
        // 컴포넌트 재배치
        frame.revalidate();
        
        // 화면 다시 출력
        frame.repaint();
    }
    
    // 클릭한 버튼에 해당하는 컨테이너로 전환하는 메소드(오버로딩)
    public void ChangeGUI(String container, String note_name) {
    	// 현재 프레임의 모든 컴포넌트를 삭제
        frame.getContentPane().removeAll();
        
        // 해당하는 컨테이너를 프레임에 추가
        switch (container) {
	        case "노트":
	            frame.add(new Note(note_name)); // 프레임에 노트 컨테이너 추가
	            break;
	        
	        case "단어추가":
                frame.add(new AddEnglish(note_name)); // 프레임에 단어 추가 컨테이너 추가
                break;
	        
	        case "객관식퀴즈":
                frame.add(new ChoiceQuiz(note_name)); // 프레임에 객관식 퀴즈 컨테이너 추가
                break;
	       
	        case "주관식단어퀴즈":
                frame.add(new WordQuiz(note_name)); // 프레임에 주관식 단어 퀴즈 컨테이너 추가
                break;
	       
	        case "주관식뜻퀴즈":
                frame.add(new MeaningQuiz(note_name)); // 프레임에 주관식 뜻 퀴즈 컨테이너 추가
                break;
        }
        
        // 컴포넌트 재배치
        frame.revalidate();
        
        // 화면 다시 출력
        frame.repaint();
    }

    // 클릭한 버튼에 해당하는 컨테이너로 전환하는 메소드(오버로딩)
    public void ChangeGUI(String container, String note_name, ArrayList<String> words) {
    	// 현재 프레임의 모든 컴포넌트를 삭제
        frame.getContentPane().removeAll();
        
        // 해당하는 컨테이너를 프레임에 추가
        switch (container) {
            case "단어삭제":
                frame.add(new DeleteEnglish(note_name, words)); // 프레임에 단어 삭제 컨테이너 추가
                break;
        }
        
        // 컴포넌트 재배치
        frame.revalidate();
        
        // 화면 다시 출력
        frame.repaint();
    }
}