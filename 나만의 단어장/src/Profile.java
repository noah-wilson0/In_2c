/*
 * 설명 : 회원정보를 조회하고 다양한 기능들을 선택할 수 있는 GUI 클래스
 *
 * 컨테이너 형태로 작성됨.
 * 홈 화면으로 이동 / 회원정보 수정 화면으로 이동 / 비밀번호 변경 화면으로 이동 / 회원탈퇴 화면으로 이동 / 로그아웃 기능을 포함함.
 * 이벤트는  GUI 클래스 내에서 처리됨.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;
import javax.swing.*;

// 회원정보(프로필)를 조회하는 컨테이너
public class Profile extends Container implements ActionListener {
    // DB에 접근하는 메소드를 호출하기 위한 객체 생성
    ConnectDB cd = new ConnectDB();

    // 컨테이너 화면을 구성하는 생성자 생성자
    public Profile() {
        // 레이아웃 객체 생성
        FlowLayout fLoC1 = new FlowLayout(FlowLayout.CENTER, 0, 0);
        FlowLayout fLoC2 = new FlowLayout(FlowLayout.CENTER, 0, 20);
        FlowLayout fLoL = new FlowLayout(FlowLayout.LEFT, 0, 20);

        // 폰트 객체 생성
        Font titleFt = new Font("돋음", Font.BOLD, 24);
        Font mainFt = new Font("돋음", Font.BOLD, 14);

        // DB에 저장된 회원정보를 하나의 문자열로 받는 변수 생성
        String profile = cd.lookProfile(GetID.id);

        // profile을 토큰으로 분리하는 객체 생성
        StringTokenizer st = new StringTokenizer(profile, "/");		// '/'을 기준으로 분리

        // 다음 토큰을 기준으로 문자열을 각 변수에 저장
        String id = st.nextToken();
        String name = st.nextToken();
        String gender = st.nextToken();
        String email = st.nextToken();

        // 컨테이너 레이아웃 설정
        setLayout(fLoC1);

        // 현재 화면을 설명하는 패널
        JPanel titleJp = new JPanel(); 						// 패널 객체 생성
        titleJp.setLayout(fLoC2);							// 패널 레이아웃 설정
        titleJp.setPreferredSize(new Dimension(300, 100));	// 패널 크기 설정 - new Dimension(너비, 높이)

        JLabel titleJl = new JLabel("내 회원정보 조회");			// 제목 라벨 객체 생성
        titleJl.setFont(titleFt);							// 제목 라벨 폰트 설정
        titleJp.add(titleJl);								// 패널에 제목 라벨 추가

        // 회원정보를 가지는 패널
        JPanel profileJp = new JPanel();						// 패널 객체 생성
        profileJp.setLayout(fLoL);								// 패널 레이아웃 설정
        profileJp.setPreferredSize(new Dimension(300, 220));	// 패널 크기 설정 - new Dimension(너비, 높이)

        JLabel idJl = new JLabel("아이디");						// 아이디 라벨 객체 생성
        idJl.setFont(mainFt);									// 아이디 라벨 폰트 설정
        idJl.setPreferredSize(new Dimension(100, 30));			// 아이디 라벨 크기 설정
        profileJp.add(idJl);									// 패널에 아이디 라벨 추가

        JTextField idJtf = new JTextField(id);					// 아이디 텍스트 필드 객체 생성, id 출력
        idJtf.setEditable(false);								// 아이디 텍스트 필드 수정 불가 설정
        idJtf.setPreferredSize(new Dimension(200, 30));			// 아이디 텍스트 필드 크기 설정
        profileJp.add(idJtf);									// 패널에 아이디 텍스트 필드 추가

        JLabel nameJl = new JLabel("이름");						// 이름 라벨 객체 생성
        nameJl.setFont(mainFt);									// 이름 라벨 폰트 설정
        nameJl.setPreferredSize(new Dimension(100, 30));		// 이름 라벨 크기 설정
        profileJp.add(nameJl);									// 패널에 이름 라벨 추가

        JTextField nameJtf = new JTextField(name);				// 이름 텍스트 필드 객체 생성, name 출력
        nameJtf.setEditable(false);								// 이름 텍스트 필드 수정 불가 설정
        nameJtf.setPreferredSize(new Dimension(200, 30));		// 이름 텍스트 필드 크기 설정
        profileJp.add(nameJtf);									// 패널에 이름 텍스트 필드 추가

        JLabel genderJl = new JLabel("성별");						// 성별 라벨 객체 생성
        genderJl.setFont(mainFt);								// 성별 라벨 폰트 설정
        genderJl.setPreferredSize(new Dimension(100, 30));		// 성별 라벨 크기 설정
        profileJp.add(genderJl);								// 패널에 성별 라벨 추가

        JTextField genderJtf = new JTextField(gender);			// 성별 텍스트 필드 객체 생성, gender 출력
        genderJtf.setEditable(false);							// 성별 텍스트 필드 수정 불가 설정
        genderJtf.setPreferredSize(new Dimension(200, 30));		// 성별 텍스트 필드 크기 설정
        profileJp.add(genderJtf);								// 패널에 성별 텍스트 필드 추가

        JLabel emailJl = new JLabel("이메일");					// 이메일 라벨 객체 생성
        emailJl.setFont(mainFt);								// 이메일 라벨 폰트 설정
        emailJl.setPreferredSize(new Dimension(100, 30));		// 이메일 라벨 크기 설정
        profileJp.add(emailJl);									// 패널에 이메일 라벨 추가

        JTextField emailJtf = new JTextField(email);			// 이메일 텍스트 필드 객체 생성, email 출력
        emailJtf.setEditable(false);							// 이메일 텍스트 필드 수정 불가 설정
        emailJtf.setPreferredSize(new Dimension(200, 30));		// 이메일 텍스트 필드 크기 설정
        profileJp.add(emailJtf);								// 패널에 이메일 텍스트 필드 추가

        // 확인 버튼을 가지는 패널
        JPanel buttonJp1 = new JPanel();						// 패널 객체 생성
        buttonJp1.setLayout(fLoC1);								// 패널 레이아웃 설정
        buttonJp1.setPreferredSize(new Dimension(300, 70));		// 패널 크기 설정 - Dimension(너비, 높이)

        JButton ok = new JButton("확인");							// 확인 버튼 객체 생성
        ok.setPreferredSize(new Dimension(70, 40));				// 확인 버튼 크기 설정
        buttonJp1.add(ok);										// 패널에 확인 버튼 추가

        // 회원정보 수정하기 버튼을 가지는 패널
        JPanel buttonJp2 = new JPanel();						// 패널 객체 생성
        buttonJp2.setLayout(fLoC1);								// 패널 레이아웃 설정
        buttonJp2.setPreferredSize(new Dimension(300, 50));		// 패널 크기 설정 - Dimension(너비, 높이)

        JButton edit_profile = new JButton("회원정보 수정하기");		// 회원정보수정 버튼 객체 생성
        edit_profile.setPreferredSize(new Dimension(250, 40));	// 회원정보수정 버튼 크기 설정
        buttonJp2.add(edit_profile);							// 패널에 회원정보수정 버튼 추가

        // 비밀번호 변경하기 버튼을 가지는 패널
        JPanel buttonJp3 = new JPanel();							// 패널 객체 생성
        buttonJp3.setLayout(fLoC1);									// 패널 레이아웃 설정
        buttonJp3.setPreferredSize(new Dimension(300, 50));			// 패널 크기 설정 - Dimension(너비, 높이)

        JButton edit_password = new JButton("비밀번호 변경하기");		// 비밀번호변경 버튼 객체 생성
        edit_password.setPreferredSize(new Dimension(250, 40));		// 비밀번호변경 버튼 크기 설정
        buttonJp3.add(edit_password);								// 패널에 비밀번호변경 버튼 추가

        // 회원탈퇴 버튼을 가지는 패널
        JPanel buttonJp4 = new JPanel();						// 패널 객체 생성
        buttonJp4.setLayout(fLoC1);								// 패널 레이아웃 설정
        buttonJp4.setPreferredSize(new Dimension(300, 50));		// 패널 크기 설정 - Dimension(너비, 높이)

        JButton delete = new JButton("회원탈퇴");					// 회원탈퇴 버튼 객체 생성
        delete.setPreferredSize(new Dimension(250, 40));		// 회원탈퇴 버튼 크기 설정
        buttonJp4.add(delete);									// 패널에 회원탈퇴 버튼 추가

        // 로그아웃 버튼을 가지는 패널
        JPanel buttonJp5 = new JPanel();						// 패널 객체 생성
        buttonJp5.setLayout(fLoC2);								// 패널 레이아웃 설정
        buttonJp5.setPreferredSize(new Dimension(300, 60));		// 패널 크기 설정 - Dimension(너비, 높이)

        JButton logout = new JButton("로그아웃");					// 로그아웃 버튼 객체 생성
        logout.setPreferredSize(new Dimension(250, 40));		// 로그아웃 버튼 크기 설정
        buttonJp5.add(logout);									// 패널에 로그아웃 버튼 추가

        // 버튼에 이벤트 리스너 추가
        ok.addActionListener(this);
        edit_profile.addActionListener(this);
        edit_password.addActionListener(this);
        delete.addActionListener(this);
        logout.addActionListener(this);

        // 컨테이너에 패널 추가
        add(titleJp);
        add(profileJp);
        add(buttonJp1);
        add(buttonJp2);
        add(buttonJp3);
        add(buttonJp4);
        add(buttonJp5);
    }

    // 이벤트를 처리하는 메소드
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame mf = MainFrame.getInstance();		// 화면을 전환하는 메소드를 호출하기 위한 객체 생성

        switch (e.getActionCommand()) {
            case "확인":
                mf.ChangeGUI("메인화면");
                break;
            case "회원정보 수정하기":
                mf.ChangeGUI("회원정보수정");
                break;
            case "비밀번호 변경하기":
                mf.ChangeGUI("비밀번호변경");
                break;
            case "회원탈퇴":
                mf.ChangeGUI("회원탈퇴");
                break;
            case "로그아웃":
                GetID.keepID(null);		// 사용 중인 ID를 null로 지정
                JOptionPane.showMessageDialog(null, "로그아웃되었습니다.");
                mf.ChangeGUI("로그인");
                break;
        }
    }
}