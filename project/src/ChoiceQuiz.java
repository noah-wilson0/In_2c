/*
 * 설명 : 객관식 퀴즈를 푸는 GUI 클래스
 *
 * 컨테이너 형태로 작성됨.
 * 이벤트는 GUI 클래스 내에서 처리됨.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class ChoiceQuiz extends Container implements ActionListener {
    // 노트에 있는 모든 단어와 뜻을 저장할 2차원 배열 선언
    String[][] english;

    // 노트에 있는 영단어의 개수를 저장할 변수 생성
    int count = 0;

    // 현재 문제가 몇 번째 문제인지 알려주기 위한 변수 생성
    int now_count = 1;

    // 문제로 출력될 단어를 저장하는 변수 생성
    String quiz = null;

    // 문제의 정답을 검색하기 위한 HashMap 객체 생성
    HashMap<String, String> answerHm = new HashMap<String, String>();

    // 문제를 하나씩 출력하기 위한 LinkedList 객체 생성(큐 구현)
    LinkedList<String> quizQueue = new LinkedList<String>();

    // 중복되지 않는 5개의 뜻을 저장하기 위한 HashSet 객체 생성
    HashSet<String> optionHs = new HashSet<String>();

    // HashSet을 순차적으로 처리하기 위한 Iterator 객체 선언
    Iterator<String> optionIt;

    // 5지 선다 보기를 저장하기 위한 ArrayList 객체 생성
    ArrayList<String> optionAl = new ArrayList<String>();

    // 객관식 보기를 가지는 라디오 버튼 배열 객체 생성
    JRadioButton[] option = new JRadioButton[5];

    // 버튼 그룹 객체 생성
    ButtonGroup bg = new ButtonGroup();

    // 라벨 객체 선언
    JLabel countJl;			// 문제 수를 보여주는 라벨
    JLabel wordJl;			// 단어를 보여주는 라벨
    JLabel guideJl;			// 문제와 정답 여부를 안내하는 라벨
    JLabel rightAnswerJl;	// 정답을 알려주는 라벨

    // 정답을 확인하는 버튼 객체 선언
    JButton check;

    // 컨테이너 화면을 구성하는 생성자
    public ChoiceQuiz(String note_name) {
        // 레이아웃 객체 생성
        FlowLayout fLoC1 = new FlowLayout(FlowLayout.CENTER, 0, 0);
        FlowLayout fLoC2 = new FlowLayout(FlowLayout.CENTER, 0, 50);
        FlowLayout fLoL1 = new FlowLayout(FlowLayout.LEFT, 0, 30);
        FlowLayout fLoL2 = new FlowLayout(FlowLayout.LEFT, 0, 5);
        BorderLayout bLo1 = new BorderLayout();

        // 폰트 객체 생성
        Font titleFt = new Font("돋음", Font.BOLD, 24);
        Font mainFt1 = new Font("돋음", Font.PLAIN, 14);
        Font mainFt2 = new Font("돋음", Font.BOLD, 14);
        Font wordFt = new Font("돋음", Font.BOLD, 32);

        // 퀴즈 구성(1/4) - 해당 노트의 모든 영단어들을 조회
        ConnectDB cd = new ConnectDB();								// DB에 접근하는 메소드를 호출하기 위한 객체 생성
        english = cd.selectWordAndMeaning(GetID.id, note_name);		// 2차원 배열 english에 모든 단어와 뜻을 저장
        count = english.length;										// 영단어의 개수를 count에 저장

        // 퀴즈 구성(2/4) - 모든 단어를 알맞은 뜻과 일치화
        for (int i=0; i<count; i++) {
            answerHm.put(english[i][0], english[i][1]);		// 단어(키)와 뜻(값)으로 이루어진 요소를 answerHm에 저장
        }

        // 퀴즈 구성(3/4) - 문제를 무작위 순서로 배치한 후 큐에 추가
        ArrayList<String> wordAl = new ArrayList<String>();		// ArrayList 객체 생성
        for (int i=0; i<count; i++) {
            wordAl.add(english[i][0]);							// 모든 단어를 wordAl에 추가
        }
        Collections.shuffle(wordAl);							// 모든 단어의 순서를 무작위로 재배치

        for (int i=0; i<count; i++) {
            quizQueue.offer(wordAl.get(i));						// 무작위로 섞인 단어들을 quizQueue에 추가
        }
        quiz = quizQueue.poll();								// quizQueue의 첫 번째 요소를 quiz에 저장한 후 삭제

        // 컨테이너 레이아웃 설정
        setLayout(fLoC1);

        // 현재 화면을 설명하는 패널
        JPanel titleJp = new JPanel(); 								// 객체 생성
        titleJp.setLayout(fLoL1);									// 레이아웃 설정
        titleJp.setPreferredSize(new Dimension(300, 100));			// 크기 설정 - new Dimension(너비, 높이)

        JLabel titleJl = new JLabel("객관식 퀴즈");						// 제목 라벨 객체 생성
        titleJl.setFont(titleFt);									// 제목 라벨 폰트 설정
        titleJl.setPreferredSize(new Dimension(300, 20));			// 제목 라벨 크기 설정
        titleJp.add(titleJl);										// 패널에 제목 라벨 추가

        JLabel noteJl = new JLabel("노트 : \"" + note_name + "\"");	// 노트 라벨 객체 생성
        noteJl.setFont(mainFt1);									// 노트 라벨 폰트 설정
        noteJl.setPreferredSize(new Dimension(240, 20));			// 노트 라벨 크기 설정
        titleJp.add(noteJl);										// 패널에 노트 라벨 추가

        countJl = new JLabel(now_count + "/" + count);				// 문제 번호 라벨 객체 생성
        countJl.setHorizontalAlignment(JLabel.RIGHT);				// 문제 번호 라벨 텍스트 우측 정렬
        countJl.setFont(mainFt1);									// 문제 번호 라벨 폰트 설정
        countJl.setPreferredSize(new Dimension(60, 20));			// 문제 번호 라벨 크기 설정
        titleJp.add(countJl);										// 패널에 문제 번호 라벨 추가

        // 단어를 보여주는 패널
        JPanel wordJp = new JPanel(); 						// 객체 생성
        wordJp.setLayout(fLoC2);							// 레이아웃 설정
        wordJp.setPreferredSize(new Dimension(300, 150));	// 크기 설정 - new Dimension(너비, 높이)
        wordJp.setBackground(Color.white);					// 배경색 설정

        wordJl = new JLabel(quiz);							// 단어 라벨 객체 생성 - quizQueue의 첫 번째 단어 출력
        wordJl.setFont(wordFt);								// 단어 라벨 폰트 설정
        wordJp.add(wordJl);									// 패널에 단어 라벨 추가

        // 객관식 보기를 보여주는 패널
        JPanel questionJp = new JPanel(); 							// 객체 생성
        questionJp.setLayout(fLoL2);								// 레이아웃 설정
        questionJp.setPreferredSize(new Dimension(300, 220));		// 크기 설정 - new Dimension(너비, 높이)

        guideJl = new JLabel("Q. 단어의 뜻을 선택하세요.");				// 가이드 라벨 객체 생성
        guideJl.setFont(mainFt2);									// 가이드 라벨 폰트 설정
        guideJl.setPreferredSize(new Dimension(300, 20));			// 가이드 라벨 크기 설정
        questionJp.add(guideJl);									// 패널에 가이드 라벨 추가

        JLabel numberJl[] = new JLabel[5];							// 보기 번호 라벨 객체 배열 생성
        for (int i=0; i<5; i++) {
            numberJl[i] = new JLabel((i+1) + ". ");					// 보기 번호 라벨 객체 생성
            numberJl[i].setFont(mainFt2);							// 보기 번호 라벨 폰트 설정
            numberJl[i].setPreferredSize(new Dimension(16, 24));	// 보기 번호 라벨 크기 설정
            questionJp.add(numberJl[i]);							// 패널에 보기 번호 라벨 추가

            option[i] = new JRadioButton();							// 보기 라디오 버튼 생성
            option[i].setFont(mainFt1);								// 보기 라디오 버튼 폰트 설정
            option[i].setPreferredSize(new Dimension(284, 24));		// 보기 라디오 버튼 크기 설정
            bg.add(option[i]);										// 버튼 그룹에 보기 라디오 버튼 추가
            questionJp.add(option[i]);								// 패널에 보기 라디오 버튼 추가
        }
        setOption();												// 객관식 보기 내용 설정

        rightAnswerJl = new JLabel();								// 정답 라벨 객체 생성
        rightAnswerJl.setFont(mainFt2);								// 정답 라벨 폰트 설정
        rightAnswerJl.setPreferredSize(new Dimension(300, 20));		// 정답 라벨 크기 설정
        questionJp.add(rightAnswerJl);								// 패널에 정답 라벨 추가

        // 버튼을 가지는 패널
        JPanel buttonJp = new JPanel(); 					// 객체 생성
        buttonJp.setLayout(bLo1);							// 레이아웃 설정
        buttonJp.setPreferredSize(new Dimension(300, 40));	// 크기 설정 - new Dimension(너비, 높이)

        JButton exit = new JButton("나가기");					// 나가기 버튼 객체 생성
        buttonJp.add(exit, BorderLayout.WEST);				// 패널에 나가기 버튼 추가

        check = new JButton("정답 확인");						// 정답 확인 버튼 객체 생성
        buttonJp.add(check, BorderLayout.EAST);				// 패널에 정답 확인 버튼 추가

        // 버튼에 이벤트 리스너 추가
        exit.addActionListener(this);
        check.addActionListener(this);

        // 컨테이너에 패널 추가
        add(titleJp);
        add(wordJp);
        add(questionJp);
        add(buttonJp);
    }

    // 객관식 보기 내용을 설정하는 메소드
    public void setOption() {
        // 퀴즈 구성(4/4) - 1개의 알맞은 뜻과 4개의 다른 뜻을 무작위로 배치하여 보기를 구현
        optionHs.clear();									// optionHs 초기화
        optionAl.clear();									// optionAl 초기화

        optionHs.add(answerHm.get(quiz));					// quiz에 저장된 단어가 가리키는 뜻을 optionHs에 추가
        int randomIndex = 0;								// 난수(정수)를 저장할 변수 생성
        while (optionHs.size() < 5) {
            randomIndex = (int)(Math.random() * count);		// 0 이상 count 미만의 난수를 randomIndex에 저장
            optionHs.add(english[randomIndex][1]);			// 무작위로 선택된 뜻을 중복 없이 optionHs에 추가
        }

        optionIt = optionHs.iterator();						// optionHs을 순차적으로 처리하기 위해 Iterator 사용
        while (optionIt.hasNext()) {
            optionAl.add(optionIt.next());					// optionHs의 요소를 optionAl에 순차적으로 추가
        }
        Collections.shuffle(optionAl);						// 5개의 뜻을 가지는 보기의 순서를 무작위로 재배치

        for (int i=0; i<5; i++) {
            option[i].setText(optionAl.get(i));				// 라디오 버튼의 Text를 optionAl의 요소로 설정
            option[i].setActionCommand(optionAl.get(i));	// 라디오 버튼의 ActionCommand를 optionAl의 요소로 설정
        }
    }

    // 이벤트를 처리하는 메소드
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "나가기":
                MainFrame mf = MainFrame.getInstance();		// 화면을 전환하는 메소드를 호출하기 위한 객체 생성
                mf.ChangeGUI("퀴즈");							// 현재 화면을 퀴즈 선택 화면으로 전환
                break;

            case "정답 확인":
                ButtonModel answerBm = bg.getSelection();	// 선택된 라디오 버튼을 반환받기 위한 객체 생성

                if (answerBm == null) {									// 보기를 선택하지 않았으면..
                    JOptionPane.showMessageDialog(null, "답을 선택하세요.");		// 해당 메세지 박스 출력
                    break;													// case 블록 종료
                }

                for (int i=0; i<5; i++) {
                    option[i].setEnabled(false);	// 모든 라디오 버튼 비활성화
                }

                if (answerBm.getActionCommand().equals(answerHm.get(quiz))) {	// 선택한 보기가 정답이라면..
                    guideJl.setForeground(Color.blue);								// 가이드 라벨 폰트 색상 변경
                    guideJl.setText("O 정답입니다!");									// 가이드 라벨 텍스트 변경
                }
                else {															// 선택한 보기가 오답이라면..
                    guideJl.setForeground(Color.red);								// 가이드 라벨 폰트 색상 변경
                    guideJl.setText("X 오답입니다!");									// 가이드 라벨 텍스트 변경
                }

                rightAnswerJl.setText("정답 : "+(optionAl.indexOf(answerHm.get(quiz))+1)+"번");	// 정답 공개

                check.setText("다음 문제 >");			// check 버튼 Text 변경
                check.setActionCommand("다음 문제");	// check 버튼 ActionCommand 변경
                break;

            case "다음 문제":
                if (quizQueue.isEmpty()) {					// 마지막 문제라면..
                    JOptionPane.showMessageDialog(null, "마지막 문제입니다.");
                }
                else {										// 마지막 문제가 아니라면..
                    quiz = quizQueue.poll();					// 현재 quizQueue의 첫 번째 요소를 quiz에 저장한 후 삭제
                    wordJl.setText(quiz);						// quiz에 저장된 단어를 화면에 출력

                    now_count++;								// 현재 문제 번호를 1만큼 증가
                    countJl.setText(now_count + "/" + count);	// 문제 번호 라벨 텍스트 변경

                    guideJl.setForeground(Color.black);			// 가이드 라벨 폰트 색상 변경
                    guideJl.setText("Q. 단어의 뜻을 선택하세요.");		// 가이드 라벨 텍스트 변경

                    rightAnswerJl.setText("");					// 정답 라벨 텍스트 변경

                    bg.clearSelection();						// 모든 라디오 버튼의 선택을 해제
                    setOption();								// 객관식 보기 내용 재설정
                    for (int i=0; i<5; i++) {
                        option[i].setEnabled(true);				// 모든 라디오 버튼 활성화
                    }

                    check.setText("정답 확인");			// check 버튼 Text 변경
                    check.setActionCommand("정답 확인");	// check 버튼 ActionCommand 변경
                }
                break;
        }
    }
}