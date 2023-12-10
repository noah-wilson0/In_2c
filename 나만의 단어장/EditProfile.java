/*
 * 설명 : 회원정보를 수정하는 GUI 클래스
 * 
 * 컨테이너 형태로 작성됨.
 * 이벤트는 GUI 클래스 내에서 처리됨.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;
import javax.swing.*;

public class EditProfile extends Container implements ActionListener {
    // DB에 접근하는 메소드를 호출하기 위한 객체 생성
    ConnectDB cb = new ConnectDB();

    // 컴포넌트 객체 선언
    JTextField nameJtf;									// 이름 텍스트 필드 객체 선언
    JRadioButton[] genderjrb = new JRadioButton[2];		// 성별 라디오 버튼 배열 객체 생성
    JTextField emailJtf;								// 이메일 텍스트 필드 객체 선언

    // 컨테이너 화면을 구성하는 생성자
    public EditProfile() {
        // 레이아웃 객체 생성
        FlowLayout fLoC1 = new FlowLayout(FlowLayout.CENTER, 0, 0);
        FlowLayout fLoC2 = new FlowLayout(FlowLayout.CENTER, 0, 20);
        FlowLayout fLoC3 = new FlowLayout(FlowLayout.CENTER, 30, 0);
        FlowLayout fLoL = new FlowLayout(FlowLayout.LEFT, 0, 20);

        // 폰트 객체 생성
        Font titleFt = new Font("돋음", Font.BOLD, 24);
        Font mainFt = new Font("돋음", Font.BOLD, 14);

        // DB에 저장된 회원정보를 하나의 문자열로 받는 변수 생성
        String profile = cb.lookProfile(GetID.id);		// lookProfile(String) : 회원정보를 조회하는 메소드

        // profile을 토큰으로 분리하는 객체 생성
        StringTokenizer st = new StringTokenizer(profile, "/");		// "/"을 기준으로 분리

        // 다음 토큰을 기준으로 문자열을 각 변수에 저장
        String id = st.nextToken();
        String name = st.nextToken();
        String gender = st.nextToken();
        String email = st.nextToken();

        // 컨테이너 레이아웃 설정
        setLayout(fLoC1);

        // 현재 화면을 설명하는 패널
        JPanel titleJp = new JPanel(); 						// 객체 생성
        titleJp.setLayout(fLoC2);							// 레이아웃 설정
        titleJp.setPreferredSize(new Dimension(300, 100));	// 크기 설정 - new Dimension(너비, 높이)

        JLabel titleJl = new JLabel("내 회원정보 수정");			// 라벨 객체 생성
        titleJl.setFont(titleFt);							// 라벨 폰트 설정
        titleJp.add(titleJl);								// 패널에 라벨 추가

        // 회원정보를 가지는 패널
        JPanel profileJp = new JPanel();							// 객체 생성
        profileJp.setLayout(fLoL);									// 레이아웃 설정
        profileJp.setPreferredSize(new Dimension(300, 230));		// 크기 설정 - new Dimension(너비, 높이)

        JLabel idJl = new JLabel("아이디");							// 라벨 객체 생성
        idJl.setFont(mainFt);										// 라벨 폰트 설정
        idJl.setPreferredSize(new Dimension(100, 30));				// 라벨 크기 설정
        profileJp.add(idJl);										// 패널에 라벨 추가

        JTextField idJtf = new JTextField(id);						// 텍스트 필드 객체 생성, id 출력
        idJtf.setEditable(false);									// 텍스트 필드 수정 불가 설정
        idJtf.setPreferredSize(new Dimension(200, 30));				// 텍스트 필드 크기 설정
        profileJp.add(idJtf);										// 패널에 텍스트 필드 추가

        JLabel nameJl = new JLabel("이름");							// 라벨 객체 생성
        nameJl.setFont(mainFt);										// 라벨 폰트 설정
        nameJl.setPreferredSize(new Dimension(100, 30));			// 라벨 크기 설정
        profileJp.add(nameJl);										// 패널에 라벨 추가

        nameJtf = new JTextField(name);								// 텍스트 필드 객체 생성, name 출력
        nameJtf.setPreferredSize(new Dimension(200, 30));			// 텍스트 필드 크기 설정
        profileJp.add(nameJtf);										// 패널에 텍스트 필드 추가

        JLabel genderJl = new JLabel("성별");							// 라벨 객체 생성
        genderJl.setFont(mainFt);									// 라벨 폰트 설정
        genderJl.setPreferredSize(new Dimension(100, 30));			// 라벨 크기 설정
        profileJp.add(genderJl);									// 패널에 라벨 추가

        genderjrb[0] = new JRadioButton("남");						// 라디오 버튼 생성
        genderjrb[1] = new JRadioButton("여");
        ButtonGroup bg = new ButtonGroup();							// 버튼 그룹 생성
        for (int i=0; i<2; i++) {
            genderjrb[i].setPreferredSize(new Dimension(40, 30));	// 라디오 버튼 크기 설정
            bg.add(genderjrb[i]);									// 버튼 그룹에 라디오 버튼 추가
            profileJp.add(genderjrb[i]);							// 패널에 라디오 버튼 추가
            if (genderjrb[i].getText().equals(gender)) {			// 라디오 버튼의 이름이 gender와 같다면..
                genderjrb[i].setSelected(true);						// 라디오 버튼의 상태를 true로 설정
            }
        }

        JLabel blank = new JLabel();								// 라벨 객체 생성
        blank.setPreferredSize(new Dimension(120, 30));				// 라벨 크기 설정
        profileJp.add(blank);										// 패널에 라벨 추가

        JLabel emailJl = new JLabel("이메일");						// 라벨 객체 생성
        emailJl.setFont(mainFt);									// 라벨 폰트 설정
        emailJl.setPreferredSize(new Dimension(100, 30));			// 라벨 크기 설정
        profileJp.add(emailJl);										// 패널에 라벨 추가

        emailJtf = new JTextField(email);							// 텍스트 필드 객체 생성, email 출력
        emailJtf.setPreferredSize(new Dimension(200, 30));			// 텍스트 필드 크기 설정
        profileJp.add(emailJtf);									// 패널에 텍스트 필드 추가

        // 저장 버튼과 취소 버튼을 가지는 패널
        JPanel buttonJp = new JPanel();						// 객체 생성
        buttonJp.setLayout(fLoC3);							// 레이아웃 설정
        buttonJp.setPreferredSize(new Dimension(300, 50));	// 크기 설정 - Dimension(너비, 높이)

        JButton save = new JButton("저장");					// 저장 버튼 객체 생성
        save.setPreferredSize(new Dimension(120, 40));		// 크기 설정
        buttonJp.add(save);									// 패널에 저장 버튼 추가

        JButton cancel = new JButton("취소");					// 취소 버튼 객체 생성
        cancel.setPreferredSize(new Dimension(120, 40));	// 크기 설정
        buttonJp.add(cancel);								// 패널에 취소 버튼 추가

        // 버튼에 이벤트 리스너 추가
        save.addActionListener(this);
        cancel.addActionListener(this);

        // 컨테이너에 패널 추가
        add(titleJp);
        add(profileJp);
        add(buttonJp);
    }

    // 이벤트를 처리하는 메소드
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame mf = MainFrame.getInstance();		// 화면을 전환하는 메소드를 호출하기 위한 객체 생성

        switch (e.getActionCommand()) {
            case "저장":
                String gender_val = "";		// gender 라디오 버튼에서 선택된 값을 받는 변수 생성

                for (int i=0; i<2; i++) {
                    if(genderjrb[i].isSelected()) {				// 라디오 버튼이 선택되어 있으면 ..
                        gender_val = genderjrb[i].getText();	// 라디오 버튼의 이름을 gender_val에 저장
                    }
                }

                if (nameJtf.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "이름을 입력해주세요.");
                }
                else if (emailJtf.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "이메일을 입력해주세요.");
                }
                else {
                    cb.editProfile(GetID.id, nameJtf.getText(), gender_val, emailJtf.getText());
                    JOptionPane.showMessageDialog(null, "정상적으로 수정되었습니다.");
                    mf.ChangeGUI("회원정보");
                }
                break;
            case "취소":
                mf.ChangeGUI("회원정보");
                break;
        }
    }
}