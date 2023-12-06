package DEMO_pack;/*
    추가 사항
    1.로그인 버튼 이벤트 처리
    2. 회원가입시 DataBase에 ID,PS추가되는 코드
    3.로그인시 DataBase의 ID,PS 검사 / 틀리면 다시 로그인 시도
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login_DEMO implements ActionListener {
    //로그인 프레임
    JFrame Login_JP=new JFrame();
    private JTextField ID;
    private  JTextField Passwd;

    public login_DEMO(){
        Container ct=Login_JP.getContentPane();
        //로그인 관련
        JPanel ID_jp=new JPanel();
        JPanel PS_jp=new JPanel();
        //로그인 버튼 관련
        JPanel B_jp=new JPanel();

        //ID추가
        JLabel l1=new JLabel("ID");
        ID=new JTextField(8);
        JLabel ID_Rock=new JLabel("*8자 까지 입력가능");
        ID_jp.add(l1);
        ID_jp.add(ID);
        ID_jp.add(ID_Rock);
        //PS추가
        JLabel l2=new JLabel("Passwd");
        Passwd=new JTextField(12);
        JLabel PS_Rock=new JLabel("*12자 까지 입력가능");
        PS_jp.add(l2);
        PS_jp.add(Passwd);
        PS_jp.add(PS_Rock);
        //login버튼
        JButton LogIn_BT=new JButton("Sign in"); //로그인
        JButton Join_BT=new JButton("Sign up");  //회원가입
        B_jp.add(LogIn_BT);
        B_jp.add(Join_BT);
        //패널 세팅
        ID_jp.setLayout(new FlowLayout());
        PS_jp.setLayout(new FlowLayout());
        B_jp.setLayout(new FlowLayout());

        //컨테이너에 추가
        ct.add(ID_jp);
        ct.add(PS_jp);
        ct.add(B_jp);
        //화면 설정
        ct.setLayout(new FlowLayout());
        Login_JP.setTitle("로그인");
        Login_JP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Login_JP.setSize(400, 200);
        Login_JP.setVisible(true);
        Login_JP.setResizable(false);
        //이벤트 리스너 추가
        LogIn_BT.addActionListener(this);
        Join_BT.addActionListener(this);

    }
    //회원가입 프레임 구현
    @Override
    public void actionPerformed(ActionEvent e) {
        //로그인 버튼 이벤트 처리
        if(e.getActionCommand()=="Sign in"){
            //DataBase의 ID,PS 검증
            if(true) {

                //검증 완료시
                //메인 화면 생성
                new Main_Screen_DEMO();
                //현재 창을 끄고 Main_Screen을 생성
                Login_JP.dispose();
            }
            //검증 실패시
            else{
                ID.setText("");
                Passwd.setText("");
            }

        }

        //회원 가입(sign_up) 이벤트 처리
        else if(e.getActionCommand()=="Sign up"){
            //회원 가입 화면 불러오기
            new SignU_DEMO(this);
            Login_JP.dispose();
        }

    }

}
//회원가입 프레임 구현
class SignU_DEMO implements ActionListener {
    //회원가입 프레임 생성
    JFrame SignUp_JP=new JFrame();
    private JTextField ID;
    private  JTextField Passwd;
    private login_DEMO loginInstance;  // login 클래스의 인스턴스를 저장할 필드

    public SignU_DEMO(login_DEMO loginInstance){
        this.loginInstance = loginInstance;  // login 인스턴스 저장
        Container ct=SignUp_JP.getContentPane();
        //로그인 관련
        JPanel ID_jp=new JPanel();
        JPanel PS_jp=new JPanel();
        //로그인 버튼 관련
        JPanel B_jp=new JPanel();

        //ID추가
        JLabel l1=new JLabel("ID");
        ID=new JTextField(8);
        JLabel ID_Rock=new JLabel("*8자 까지 입력가능");
        ID_jp.add(l1);
        ID_jp.add(ID);
        ID_jp.add(ID_Rock);
        //PS추가
        JLabel l2=new JLabel("Passwd");
        Passwd=new JTextField(12);
        JLabel PS_Rock=new JLabel("*12자 까지 입력가능");
        PS_jp.add(l2);
        PS_jp.add(Passwd);
        PS_jp.add(PS_Rock);
        //회원 가입 버튼
        JButton Join_BT=new JButton("Sign up");  //회원가입
        B_jp.add(Join_BT);
        //패널 배치 관리
        ID_jp.setLayout(new FlowLayout());
        PS_jp.setLayout(new FlowLayout());
        B_jp.setLayout(new FlowLayout());

        //패널을 컨테이너에 추가
        ct.add(ID_jp);
        ct.add(PS_jp);
        ct.add(B_jp);
        ct.setLayout(new FlowLayout());

        //회원가입 프레임 설정
        SignUp_JP.setTitle("회원가입");
        SignUp_JP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SignUp_JP.setSize(400, 200);
        SignUp_JP.setResizable(false);
        SignUp_JP.setVisible(true);
        //회원 가입완료 시 로그인 화면으로 전환
        Join_BT.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals("Sign up")){
            SignUp_JP.dispose();
            // Login_JP 프레임을 다시 보이게 합니다.
            loginInstance.Login_JP.setVisible(true);

        }

    }
}