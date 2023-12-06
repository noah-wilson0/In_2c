/*
 * 설명 : 로그인을 하는 GUI 클래스
 *
 * 컨테이너 형태로 작성됨.
 * 이벤트는 GUI 클래스 내에서 처리됨.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends Container implements ActionListener {
    // db 사용을 위한 변수 ID,PW 선언
    private JTextField ID;
    private JTextField Passwd;

    // 컨테이너 화면을 구성하는 생성자
    public Login(){
        // 버튼, ID, PW 폰트
        Font fnt=new Font("돋음", Font.BOLD,14);
        setLayout(null);

        // 타이틀(로그인, 회원가입) 폰트
        Font fnt_title=new Font("돋음", Font.BOLD,20);

        // 로그인 제목
        JLabel Jl_title=new JLabel("로그인");
        Jl_title.setFont(fnt_title);
        Jl_title.setBounds(170,120,100,40);
        add(Jl_title);

        // ID추가
        JLabel l1=new JLabel("ID");
        l1.setFont(fnt); //폰트 변경
        l1.setBounds(50, 200, 50, 30);	// 절대 위치-배치관리자 사용 x
        ID=new JTextField(8);
        ID.setBounds(100,200,200,30);	// 절대 위치-배치관리자 사용 x

        JLabel ID_Rock=new JLabel("* 최대8자 가능");
        ID_Rock.setBounds(100, 225, 100, 30);	// 절대 위치-배치관리자 사용 x

        // PW추가
        JLabel l2=new JLabel("PW");
        l2.setBounds(50, 250, 50, 30);	// 절대 위치-배치관리자 사용 x
        l2.setFont(fnt);	// 폰트 변경
        Passwd=new JTextField(12);
        Passwd.setBounds(100,250,200,30);	// 절대 위치-배치관리자 사용 x
        JLabel PW_Rock=new JLabel("*최대 12자 가능");
        PW_Rock.setBounds(100, 275, 100, 30);	// 절대 위치-배치관리자 사용 x

        // login버튼
        JButton LogIn_BT=new JButton("Sign in");	// 로그인
        JButton Join_BT=new JButton("Sign up");		// 회원가입
        LogIn_BT.setFont(fnt);	// 폰트 변경
        Join_BT.setFont(fnt);	// 폰트 변경

        // 절대 위치-배치관리자 사용 x
        LogIn_BT.setBounds(100,300,100,40);		// 절대 위치-배치관리자 사용 x
        Join_BT.setBounds(210,300,100,40);		// 절대 위치-배치관리자 사용 x

        // 컨테이너에 추가
        add(l1);
        add(ID);
        add(ID_Rock);
        add(l2);
        add(Passwd);
        add(PW_Rock);
        add(LogIn_BT);
        add(Join_BT);

        // 이벤트 리스너 추가
        LogIn_BT.addActionListener(this);
        Join_BT.addActionListener(this);
    }

    // 이벤트를 처리하는 메소드
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame mf = MainFrame.getInstance();		// 화면을 전환하는 메소드를 호출하기 위한 객체 생성
        ConnectDB cb = new ConnectDB();				// DB에 접근하는 메소드를 호출하기 위한 객체 생성

        switch (e.getActionCommand()) {
            case "Sign in":
                if (ID.getText().equals("")) {						// 아이디를 입력하지 않았으면..
                    JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.");
                }
                else if (Passwd.getText().equals("")) {				// 비밀번호를 입력하지 않았으면..
                    JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.");
                }
                else {												// 모든 정보를 입력했으면..
                    if (cb.checkID(ID.getText())) {						// DB에 등록된 아이디를 입력했으면..
                        if (cb.checkPW(ID.getText(), Passwd.getText())) {	// 비밀번호가 해당 아이디와 일치하면..
                            GetID.keepID(ID.getText());							// 로그인한 아이디를 GetID 클래스에 저장
                            mf.ChangeGUI("메인화면");								// 메인화면으로 이동(로그인 성공)
                        }
                        else {												// 비밀번호가 해당 아이디와 일치하지 않으면..
                            JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다.");
                            Passwd.setText("");
                        }
                    }
                    else {												// DB에 등록되지 않은 아이디를 입력했으면..
                        JOptionPane.showMessageDialog(null, "등록되지 않은 아이디입니다.");
                        ID.setText("");
                        Passwd.setText("");
                    }
                }
                break;
            case "Sign up":
                mf.ChangeGUI("회원가입");		// 회원가입 화면 불러오기
                break;
        }
    }
}