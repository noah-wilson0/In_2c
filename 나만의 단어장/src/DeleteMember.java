/*
 * 설명 : 회원을 탈퇴하는 GUI 클래스
 *
 * 컨테이너 형태로 작성됨.
 * 이벤트는 GUI 클래스 내에서 처리됨.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class DeleteMember extends Container implements ActionListener {
    // DB에 접근하는 메소드를 호출하기 위한 객체 생성
    ConnectDB cb = new ConnectDB();

    // 비밀번호 텍스트 필드 객체 생성
    JTextField passwordJtf;

    // 컨테이너 화면을 구성하는 생성자
    public DeleteMember() {
        // 레이아웃 객체 생성
        FlowLayout fLoC1 = new FlowLayout(FlowLayout.CENTER, 0, 0);
        FlowLayout fLoC2 = new FlowLayout(FlowLayout.CENTER, 0, 20);
        FlowLayout fLoC3 = new FlowLayout(FlowLayout.CENTER, 30, 20);
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

        JLabel titleJl = new JLabel("회원탈퇴");				// 라벨 객체 생성
        titleJl.setFont(titleFt);							// 라벨 폰트 설정
        titleJp.add(titleJl);								// 패널에 라벨 추가

        // 비밀번호를 입력받는 패널
        JPanel passwordJp = new JPanel(); 						// 객체 생성
        passwordJp.setLayout(fLoL);								// 레이아웃 설정
        passwordJp.setPreferredSize(new Dimension(300, 100));	// 크기 설정 - new Dimension(너비, 높이)

        JLabel passwordJl = new JLabel("비밀번호 입력");			// 라벨 객체 생성
        passwordJl.setFont(mainFt);								// 라벨 폰트 설정
        passwordJl.setPreferredSize(new Dimension(120, 30));	// 라벨 크기 설정
        passwordJp.add(passwordJl);								// 패널에 라벨 추가

        passwordJtf = new JTextField();							// 텍스트 필드 객체 생성
        passwordJtf.setPreferredSize(new Dimension(180, 30));	// 텍스트 필드 크기 설정
        passwordJp.add(passwordJtf);							// 패널에 텍스트 필드 추가

        // 탈퇴 버튼과 취소 버튼을 가지는 패널
        JPanel buttonJp = new JPanel();							// 객체 생성
        buttonJp.setLayout(fLoC3);								// 레이아웃 설정
        buttonJp.setPreferredSize(new Dimension(300, 120));		// 크기 설정 - Dimension(너비, 높이)

        JButton delete = new JButton("탈퇴");						// 탈퇴 버튼 객체 생성
        delete.setPreferredSize(new Dimension(140, 40));		// 크기 설정
        buttonJp.add(delete);									// 패널에 저장 버튼 추가

        JButton cancel = new JButton("취소");						// 취소 버튼 객체 생성
        cancel.setPreferredSize(new Dimension(140, 40));		// 크기 설정
        buttonJp.add(cancel);									// 패널에 취소 버튼 추가

        // 버튼에 이벤트 리스너 추가
        delete.addActionListener(this);
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
            case "탈퇴":
                if (passwordJtf.getText().equals("")) {				// 비밀번호를 입력하지 않았으면..
                    JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.");
                }
                else {												// 비밀번호를 입력했으면..
                    if (cb.checkPW(GetID.id, passwordJtf.getText())) {	// 비밀번호가 맞았으면..
                        int answer = JOptionPane.showConfirmDialog(null, "정말로 탈퇴하시겠습니까?", "Message",
                                JOptionPane.YES_NO_OPTION);
                        if (answer == 0) {									// 메시지 창에서 Yes를 선택했으면..
                            cb.deleteMember(GetID.id);
                            JOptionPane.showMessageDialog(null, "정상적으로 탈퇴되었습니다.");
                            mf.ChangeGUI("로그인");
                        }
                    }
                    else {												// 비밀번호가 틀렸으면..
                        JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다.");
                        passwordJtf.setText("");
                    }
                }
                break;

            case "취소":
                mf.ChangeGUI("회원정보");		// 현재 화면을 회원정보 조회 화면으로 전환
                break;
        }
    }
}