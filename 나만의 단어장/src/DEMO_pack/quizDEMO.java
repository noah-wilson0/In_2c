///*
//    객관식, 주관식(단어), 주관식(뜻) 클릭시 changeGUI해주면 끝남
// */
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class quizDEMO extends Container implements ActionListener {
//    //폰트 - 객관식, 주관식(단어), 주관식(뜻), 메인화면
//    Font fnt=new Font("돋음", Font.BOLD,14);
//    public quizDEMO(){
//        setLayout(null);
//        Font fnt_title=new Font("돋음", Font.BOLD,20);
//        //레이블 -단어퀴즈
//        JLabel jl=new JLabel("단어 퀴즈");
//        jl.setFont(new Font("돋음",Font.BOLD,20));
//        //버튼 - 객관식
//        JButton multipleChoiceButton=new JButton("객관식");
//
//        //버튼 - 주관식(단어)
//        JButton wordAnswerButton=new JButton("주관식(단어)");
//
//        //버튼 - 주관식(뜻)
//        JButton meanAnswerButton=new JButton("주관식(뜻)");
//
//        //메인화면으로...
//        JButton btn_home = new JButton("메인화면");
//        // 버튼 폰트 변경
//        multipleChoiceButton.setFont(fnt);
//        wordAnswerButton.setFont(fnt);
//        meanAnswerButton.setFont(fnt);
//
//        //제목 배치 - 절대 위치
//        jl.setBounds(150,100,100,35);
//
//        //버튼 배치 - 절대 위치
//        btn_home.setBounds(210,150,100,35);
//        multipleChoiceButton.setBounds(100,200,200,30);
//        wordAnswerButton.setBounds(100,250,200,30);
//        meanAnswerButton.setBounds(100,300,200,30);
//
//        // 컨테이너에 추가
//        add(jl);
//        add(multipleChoiceButton);
//        add(wordAnswerButton);
//        add(meanAnswerButton);
//        add(btn_home);
//        //버튼 - 이벤트
//        btn_home.addActionListener(this); //버튼 클릭시... 홈화면
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        MainFrame mf = MainFrame.getInstance();		// 메인 프레임 클래스로부터 객체 생성 (생성자 호출 X - 싱글톤 패턴)
//        mf.ChangeGUI(e.getActionCommand());		// 컨테이너를 전환하는 메소드에 클릭한 버튼의 이름을 매개변수로 전달
//    }
//}
