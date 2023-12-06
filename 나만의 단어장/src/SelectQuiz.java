/*
 * 설명 : 퀴즈를 풀 노트와 퀴즈 형식을 선택하는 GUI 클래스
 *
 * 컨테이너 형태로 작성됨.
 * 이벤트는 GUI 클래스 내에서 처리됨.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class SelectQuiz extends Container implements ActionListener {
    // DB에 접근하는 메소드를 호출하기 위한 객체 생성
    ConnectDB cd = new ConnectDB();

    // 콤보박스 객체 생성
    JComboBox<String> note_combo = new JComboBox<String>();		// 노트 목록이 추가되는 콤보박스

    // 컨테이너 화면을 구성하는 생성자
    public SelectQuiz() {
        // 레이아웃 객체 생성
        FlowLayout fLoC1 = new FlowLayout(FlowLayout.CENTER, 0, 0);
        FlowLayout fLoC2 = new FlowLayout(FlowLayout.CENTER, 0, 40);
        FlowLayout fLoL = new FlowLayout(FlowLayout.LEFT, 0, 20);

        // 폰트 객체 생성
        Font titleFt = new Font("돋음", Font.BOLD, 32);
        Font mainFt = new Font("돋음", Font.PLAIN, 14);
        Font buttonFt = new Font("돋음", Font.BOLD, 18);

        // 컨테이너 레이아웃 설정
        setLayout(fLoC1);

        // 현재 화면을 설명하는 패널
        JPanel titleJp = new JPanel(); 						// 패널 객체 생성
        titleJp.setLayout(fLoL);							// 패널 레이아웃 설정
        titleJp.setPreferredSize(new Dimension(320, 100));	// 패널 크기 설정 - new Dimension(너비, 높이)

        JLabel titleJl = new JLabel("퀴즈 선택");				// 제목 라벨 객체 생성
        titleJl.setFont(titleFt);							// 제목 라벨 폰트 설정
        titleJp.add(titleJl);								// 패널에 제목 라벨 추가

        // 노트를 선택하는 패널
        JPanel noteJp = new JPanel(); 									// 패널 객체 생성
        noteJp.setLayout(fLoL);											// 패널 레이아웃 설정
        noteJp.setPreferredSize(new Dimension(320, 50));				// 패널 크기 설정 - new Dimension(너비, 높이)

        JLabel guideJl = new JLabel("퀴즈를 풀 노트를 선택해주세요.  ");			// 가이드 라벨 객체 생성
        guideJl.setFont(mainFt);										// 가이드 라벨 폰트 설정
        noteJp.add(guideJl);											// 패널에 가이드 라벨 추가

        String noteList[] = cd.selectNoteList(GetID.id).split("\\|");	//'|'로 구분된  note를 다시 '|'로 구분하여 배열 생성
        for (int i = 0; i < noteList.length; i++) {
            note_combo.addItem(noteList[i]);								// 노트 콤보박스에 아이템 추가
        }
        noteJp.add(note_combo);											// 패널에 노트 콤보박스 추가

        // 퀴즈 형식을 선택하는 패널
        JPanel quizTypeJp = new JPanel(); 						// 패널 객체 생성
        quizTypeJp.setLayout(fLoC2);							// 패널 레이아웃 설정
        quizTypeJp.setPreferredSize(new Dimension(320, 300));	// 패널 크기 설정 - new Dimension(너비, 높이)

        JButton choice_quiz = new JButton("객관식 퀴즈");			// 객관식 퀴즈 버튼 객체 생성
        choice_quiz.setFont(buttonFt);							// 객관식 퀴즈 버튼 폰트 설정
        choice_quiz.setPreferredSize(new Dimension(240, 60));	// 객관식 퀴즈 버튼 크기 설정
        quizTypeJp.add(choice_quiz);							// 패널에 객관식 퀴즈 버튼 추가

        JButton word_quiz = new JButton("주관식 퀴즈 (단어)");		// 주관식 단어 퀴즈 버튼 객체 생성
        word_quiz.setFont(buttonFt);							// 주관식 단어 퀴즈 버튼 폰트 설정
        word_quiz.setPreferredSize(new Dimension(240, 60));		// 주관식 단어 퀴즈 버튼 크기 설정
        quizTypeJp.add(word_quiz);								// 패널에 주관식 단어 퀴즈 버튼 추가

        JButton meaning_quiz = new JButton("주관식 퀴즈 (뜻)");		// 주관식 뜻 퀴즈 버튼 객체 생성
        meaning_quiz.setFont(buttonFt);							// 주관식 뜻 퀴즈 버튼 폰트 설정
        meaning_quiz.setPreferredSize(new Dimension(240, 60));	// 주관식 뜻 퀴즈 버튼 크기 설정
        quizTypeJp.add(meaning_quiz);							// 패널에 주관식 뜻 퀴즈 버튼 추가

        // 메인으로 이동하는 버튼을 가지는 패널
        JPanel mainButtonJp = new JPanel(); 					// 패널 객체 생성
        mainButtonJp.setLayout(fLoC2);							// 패널 레이아웃 설정
        mainButtonJp.setPreferredSize(new Dimension(320, 80));	// 패널 크기 설정 - new Dimension(너비, 높이)

        JButton to_main = new JButton("메인으로");					// 메인화면 버튼 객체 생성
        to_main.setPreferredSize(new Dimension(90, 40));		// 메인화면 버튼 크기 설정
        mainButtonJp.add(to_main);								// 패널에 메인화면 버튼 추가

        // 버튼에 이벤트 리스너 추가
        choice_quiz.addActionListener(this);
        word_quiz.addActionListener(this);
        meaning_quiz.addActionListener(this);
        to_main.addActionListener(this);

        // 컨테이너에 패널 추가
        add(titleJp);
        add(noteJp);
        add(quizTypeJp);
        add(mainButtonJp);
    }

    // 이벤트를 처리하는 메소드
    @Override
    public void actionPerformed(ActionEvent e) {
        int count;
        MainFrame mf = MainFrame.getInstance();		// 화면을 전환하는 메소드를 호출하기 위한 객체 생성
        String note_name = (String)note_combo.getSelectedItem();	// 콤보박스에서 선택된 노트의 이름을 받는 변수 생성

        switch (e.getActionCommand()) {
            case "객관식 퀴즈":
                count = cd.getNoteSize(GetID.id, note_name);		// 해당 노트에 있는 단어의 개수를 조회

                if (count < 5) {										// 단어가 개수가 5개 미만이면..
                    JOptionPane.showMessageDialog(null, "퀴즈를 풀기 위한 단어가 부족합니다!\n필요한 단어는 최소 5개 이상입니다.");
                }
                else {													// 단어가 개수가 5개 이상이면..
                    mf.ChangeGUI("객관식퀴즈", note_name);						// 객관식 퀴즈 화면으로 이동
                }
                break;
            case "주관식 퀴즈 (단어)":
                    count = cd.getNoteSize(GetID.id, note_name);		// 해당 노트에 있는 단어의 개수를 조회

                if (count < 1) {										// 단어가 개수가 5개 미만이면..
                    JOptionPane.showMessageDialog(null, "퀴즈를 풀기 위한 단어가 부족합니다!\n필요한 단어는 최소 1개 이상입니다.");
                }
                else {                                                    // 단어가 개수가 5개 이상이면..
                    mf.ChangeGUI("주관식단어퀴즈", note_name);    // 주관식 단어 퀴즈 화면으로 이동
                }
                break;
            case "주관식 퀴즈 (뜻)":
                count = cd.getNoteSize(GetID.id, note_name);		// 해당 노트에 있는 단어의 개수를 조회

                if (count < 1) {										// 단어가 개수가 5개 미만이면..
                    JOptionPane.showMessageDialog(null, "퀴즈를 풀기 위한 단어가 부족합니다!\n필요한 단어는 최소 1개 이상입니다.");
                }
                else {                                                    // 단어가 개수가 5개 이상이면..
                    mf.ChangeGUI("주관식뜻퀴즈", note_name);	// 주관식 뜻 퀴즈 화면으로 이동
                }
                break;
            case "메인으로":
                mf.ChangeGUI("메인화면");		// 메인화면으로 이동
                break;
        }
    }
}