/*
 * 설명 : 회원가입을 하는 GUI 클래스
 *
 * 컨테이너 형태로 작성됨.
 * 이벤트는 GUI 클래스 내에서 처리됨.
 */

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SignUp extends Container implements ActionListener {
    private JTextField ID; // ID
    private JTextField pw; // PW
    private JTextField name; // NAME
    private JRadioButton[] gender = new JRadioButton[2]; // GENDER
    private JTextField email; // EMAIL

    // 컨테이너 화면을 구성하는 생성자
    public SignUp(){
        // 버튼, ID, PW, NAME 폰트
        Font fnt=new Font("돋음", Font.BOLD,14);

        setLayout(null);

        // 타이틀(로그인, 회원가입) 폰트
        Font fnt_title=new Font("돋음", Font.BOLD,20);

        // 로그인 제목
        JLabel Jl_title=new JLabel("회원가입");
        Jl_title.setFont(fnt_title);
        Jl_title.setBounds(150,120,100,40);
        add(Jl_title);

        // ID추가
        JLabel l1=new JLabel("ID");
        l1.setFont(fnt); //폰트 변경
        l1.setBounds(50, 200, 80, 30);	// 절대 위치-배치관리자 사용 x
        ID=new JTextField(8);
        ID.setBounds(130,200,200,30);	// 절대 위치-배치관리자 사용 x
        JLabel ID_Rock=new JLabel("* 최대8자 가능");
        ID_Rock.setBounds(130, 225, 100, 30);	// 절대 위치-배치관리자 사용 x

        // PW추가
        JLabel l2=new JLabel("PW");
        l2.setBounds(50, 250, 80, 30);	// 절대 위치-배치관리자 사용 x
        l2.setFont(fnt);	// 폰트 변경
        pw=new JTextField(12);
        pw.setBounds(130,250,200,30);	// 절대 위치-배치관리자 사용 x
        JLabel PW_Rock=new JLabel("*최대 12자 가능");
        PW_Rock.setBounds(130, 275, 100, 30);	// 절대 위치-배치관리자 사용 x

        // NAME추가
        JLabel l3=new JLabel("NAME");
        l3.setBounds(50, 300, 80, 30);	// 절대 위치-배치관리자 사용 x
        l3.setFont(fnt);	// 폰트 변경
        name=new JTextField(10);
        name.setBounds(130,300,200,30);	// 절대 위치-배치관리자 사용 x

        // GENDER추가
        JLabel l4=new JLabel("GENDER");
        l4.setBounds(50, 350, 80, 30);	// 절대 위치-배치관리자 사용 x
        l4.setFont(fnt);	// 폰트 변경
        gender[0] = new JRadioButton("남");
        gender[1] = new JRadioButton("여");
        ButtonGroup bg = new ButtonGroup();
        bg.add(gender[0]);
        bg.add(gender[1]);
        gender[0].setBounds(130,350,40,30);	// 절대 위치-배치관리자 사용 x
        gender[1].setBounds(180,350,40,30);	// 절대 위치-배치관리자 사용 x

        // EMAIL추가
        JLabel l5=new JLabel("EMAIL");
        l5.setBounds(50, 400, 80, 30);	// 절대 위치-배치관리자 사용 x
        l5.setFont(fnt);	// 폰트 변경
        email=new JTextField(30);
        email.setBounds(130,400,200,30);	// 절대 위치-배치관리자 사용 x

        // 로그인 버튼
        JButton signIN_BT=new JButton("Sign in");	// 로그인

        // 절대 위치-배치관리자 사용 x
        signIN_BT.setBounds(100,450,100,40);	// 절대 위치-배치관리자 사용 x

        // 회원 가입 버튼
        JButton signUP_BT=new JButton("Join");	// 회원가입

        // 절대 위치-배치관리자 사용 x
        signUP_BT.setBounds(210,450,100,40);	// 절대 위치-배치관리자 사용 x

        // 컨테이너에 추가
        add(l1);
        add(ID);
        add(ID_Rock);
        add(l2);
        add(pw);
        add(PW_Rock);
        add(l3);
        add(name);
        add(l4);
        add(gender[0]);
        add(gender[1]);
        add(l5);
        add(email);
        add(signIN_BT);
        add(signUP_BT);

        // 이벤트 리스너 추가
        signIN_BT.addActionListener(this);
        signUP_BT.addActionListener(this);
    }

    // 이벤트를 처리하는 메소드
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame mf = MainFrame.getInstance();		// 화면을 전환하는 메소드를 호출하기 위한 객체 생성
        ConnectDB cd = new ConnectDB();				// DB에 접근하는 메소드를 호출하기 위한 객체 생성

        switch (e.getActionCommand()) {
            case "Sign in":
                mf.ChangeGUI("로그인");	// 로그인 화면으로 이동
                break;

            case "Join":
                String gender_val = "";		// gender 라디오 버튼에서 선택된 값을 받는 변수 생성

                for (int i=0; i<2; i++) {
                    if(gender[i].isSelected()) {		// 라디오 버튼이 선택되어 있으면 ..
                        gender_val = gender[i].getText();	// 라디오 버튼의 Text를 gender_val에 저장
                    }
                }

                if (ID.getText().equals("")) {			// 아이디를 입력하지 않았으면..
                    JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.");
                }
                else if (pw.getText().equals("")) {		// 비밀번호를 입력하지 않았으면..
                    JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.");
                }
                else if (name.getText().equals("")) {	// 이름을 입력하지 않았으면..
                    JOptionPane.showMessageDialog(null, "이름을 입력해주세요.");
                }
                else if (gender_val.equals("")) {		// 성별을 선택하지 않았으면..
                    JOptionPane.showMessageDialog(null, "성별을 선택해주세요.");
                }
                else if (email.getText().equals("")) {	// 이메일을 입력하지 않았으면..
                    JOptionPane.showMessageDialog(null, "이메일을 입력해주세요.");
                }
                else if(ID.getText().length()>8){  //아이디를 8자리 이상 입력했으면...
                    JOptionPane.showMessageDialog(null, "아이디를 8자리 이하로 입력해주세요.");
                    ID.setText("");
                }
                else if(pw.getText().length()>12){  //아이디를 8자리 이상 입력했으면...
                    JOptionPane.showMessageDialog(null, "비밀번호를 12자리 이하로 입력해주세요.");
                    pw.setText("");
                }
                else {									// 모든 정보를 입력했으면..
                    if (cd.checkID(ID.getText())) {			// 이미 등록된 아이디를 입력했으면..
                        JOptionPane.showMessageDialog(null, "이미 등록된 아이디입니다.");
                        ID.setText("");
                    }
                    else {									// 등록되지 않은 아이디를 입력했으면..
                        cd.addMember(ID.getText(), pw.getText(), name.getText(), gender_val,
                                email.getText());			// 입력된 정보를 DB에 추가
                        JOptionPane.showMessageDialog(null, "정상적으로 가입되었습니다.");
                        mf.ChangeGUI("로그인");					// 로그인 화면으로 이동
                    }
                }
                break;
        }
    }
}