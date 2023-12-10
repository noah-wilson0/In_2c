/*
 * 설명 : 비밀번호를 변경하는 GUI 클래스
 * 
 * 컨테이너 형태로 작성됨.
 * 이벤트는 GUI 클래스 내에서 처리됨.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class EditPassword extends Container implements ActionListener {
	// DB에 접근하는 메소드를 호출하기 위한 객체 생성
    ConnectDB cb = new ConnectDB();

    // 컴포넌트 객체 선언
    JTextField nowPwJtf;	// 현재 비밀번호를 입력받는 텍스트 필드
    JTextField newPwJtf;	// 새 비밀번호를 입력받는 텍스트 필드

    // 컨테이너 화면을 구성하는 생성자
    public EditPassword() {
        // 레이아웃 객체 생성
        FlowLayout fLoC1 = new FlowLayout(FlowLayout.CENTER, 0, 0);
        FlowLayout fLoC2 = new FlowLayout(FlowLayout.CENTER, 0, 20);
        FlowLayout fLoC3 = new FlowLayout(FlowLayout.CENTER, 30, 0);
        FlowLayout fLoL = new FlowLayout(FlowLayout.LEFT, 0, 20);

        // 폰트 객체 생성
        Font titleFt = new Font("돋음", Font.BOLD, 24);
        Font mainFt = new Font("돋음", Font.BOLD, 14);

        // 컨테이너 레이아웃 설정
        setLayout(fLoC1);

        // 현재 화면을 설명하는 패널
        JPanel titleJp = new JPanel(); 						// 객체 생성
        titleJp.setLayout(fLoC2);							// 레이아웃 설정
        titleJp.setPreferredSize(new Dimension(300, 100));	// 크기 설정 - new Dimension(너비, 높이)

        JLabel titleJl = new JLabel("비밀번호 변경");			// 라벨 객체 생성
        titleJl.setFont(titleFt);							// 라벨 폰트 설정
        titleJp.add(titleJl);								// 패널에 라벨 추가

        // 비밀번호를 입력받는 패널
        JPanel passwordJp = new JPanel(); 						// 객체 생성
        passwordJp.setLayout(fLoL);								// 레이아웃 설정
        passwordJp.setPreferredSize(new Dimension(300, 160));	// 크기 설정 - new Dimension(너비, 높이)

        JLabel nowPwJl = new JLabel("현재 비밀번호");				// 라벨 객체 생성
        nowPwJl.setFont(mainFt);								// 라벨 폰트 설정
        nowPwJl.setPreferredSize(new Dimension(120, 30));		// 라벨 크기 설정
        passwordJp.add(nowPwJl);								// 패널에 라벨 추가

        nowPwJtf = new JTextField();							// 텍스트 필드 객체 생성
        nowPwJtf.setPreferredSize(new Dimension(180, 30));		// 텍스트 필드 크기 설정
        passwordJp.add(nowPwJtf);								// 패널에 텍스트 필드 추가

        JLabel newPwJl = new JLabel("새 비밀번호");					// 라벨 객체 생성
        newPwJl.setFont(mainFt);								// 라벨 폰트 설정
        newPwJl.setPreferredSize(new Dimension(120, 30));		// 라벨 크기 설정
        passwordJp.add(newPwJl);								// 패널에 라벨 추가

        newPwJtf = new JTextField();							// 텍스트 필드 객체 생성
        newPwJtf.setPreferredSize(new Dimension(180, 30));		// 텍스트 필드 크기 설정
        passwordJp.add(newPwJtf);								// 패널에 텍스트 필드 추가

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
        add(passwordJp);
        add(buttonJp);
    }

    // 이벤트를 처리하는 메소드
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame mf = MainFrame.getInstance();		// 화면을 전환하는 메소드를 호출하기 위한 객체 생성

        switch (e.getActionCommand()) {
            case "저장":
                if (nowPwJtf.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "현재 비밀번호를 입력해주세요.");
                }
                else if (newPwJtf.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "새 비밀번호를 입력해주세요.");
                }
                else {
                    if (cb.checkPW(GetID.id, nowPwJtf.getText())) {
                        cb.editPassword(GetID.id, newPwJtf.getText());
                        JOptionPane.showMessageDialog(null, "정상적으로 변경되었습니다.");
                        mf.ChangeGUI("회원정보");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "현재 비밀번호가 틀렸습니다.");
                        nowPwJtf.setText("");
                    }
                }
                break;

            case "취소":
                mf.ChangeGUI("회원정보");
                break;
        }
    }
}