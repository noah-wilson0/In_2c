/*
 * 설명 : 주관식 뜻 퀴즈를 푸는 GUI 클래스
 *
 * 컨테이너 형태로 작성됨.
 * 이벤트는 GUI 클래스 내에서 처리됨.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MeaningQuiz extends Container implements ActionListener {
    String noteName="";
    JTextField answerJtf; //입력받을  JTextField 선언
    ConnectDB cb=new ConnectDB();   //db명령어 사용하기
    String wordList[][]; //db에서 받을 배열 선언
    String  word; //단어 문자열 선언
    JLabel meanJl; //뜻 레이블 선언
    String mean; //뜻 문자열 선언
    JLabel rightAnswerJl;  //정답 레이블 선언

    //현재 단어,뜻 배열의 인덱스
    int currentIndex=0;

    // 컨테이너 화면을 구성하는 생성자
    public MeaningQuiz(String noteName) {
        this.noteName=noteName;

        // 레이아웃 객체 생성
        FlowLayout fLoC1 = new FlowLayout(FlowLayout.CENTER, 0, 0);
        FlowLayout fLoL1 = new FlowLayout(FlowLayout.LEFT, 0, 30);
        FlowLayout fLoL2 = new FlowLayout(FlowLayout.LEFT, 0, 0);
        BorderLayout bLo1 = new BorderLayout(10,10);

        // 폰트 객체 생성
        Font titleFt = new Font("돋음", Font.BOLD, 24);
        Font mainFt = new Font("돋음", Font.PLAIN, 14);
        Font meanFt = new Font("돋음", Font.BOLD, 18);
        Font guideFt = new Font("돋음", Font.BOLD, 14);

        // 컨테이너 레이아웃 설정
        setLayout(fLoC1);

        // 현재 화면을 설명하는 패널
        JPanel titleJp = new JPanel(); 								// 객체 생성
        titleJp.setLayout(fLoL1);									// 레이아웃 설정
        titleJp.setPreferredSize(new Dimension(300, 180));			// 크기 설정 - new Dimension(너비, 높이)

        JLabel titleJl = new JLabel("주관식 뜻 퀴즈");					// 라벨 객체 생성
        titleJl.setFont(titleFt);									// 라벨 폰트 설정
        titleJl.setPreferredSize(new Dimension(300, 30));			// 라벨 크기 설정
        titleJp.add(titleJl);										// 패널에 라벨 추가

        JLabel noteJl = new JLabel("노트 : \"" + noteName + "\"");	// 라벨 객체 생성
        noteJl.setFont(mainFt);										// 라벨 폰트 설정
        noteJl.setPreferredSize(new Dimension(300, 20));			// 라벨 크기 설정
        titleJp.add(noteJl);										// 패널에 라벨 추가

        // 뜻을 보여주는 패널
        JPanel meanJp = new JPanel(); 						// 객체 생성
        meanJp.setLayout(fLoC1);							// 레이아웃 설정
        meanJp.setPreferredSize(new Dimension(300, 100));	// 크기 설정 - new Dimension(너비, 높이)

        wordList =cb.selectWordAndMeaning(GetID.id,noteName);  	//현재 가지고 있는 단어를 배열로 생성

            mean = wordList[currentIndex][1];
            String meanList[]= mean.split(", "); // ["뜻1","뜻2",...] 뜻을 배열로 재생성
            String result=String.join(", ", meanList); // 뜻1,뜻2... meanList를 문자열로 변환
            meanJl = new JLabel(result);  //단어에 해당하는 뜻
            meanJl.setFont(meanFt);			// 라벨 폰트 설정
            meanJp.add(meanJl);				// 패널에 라벨 추가

            word = wordList[currentIndex][0]; //현재 뜻에 해당하는 단어

        // 답안을 입력받는 패널
        JPanel answerJp = new JPanel(); 						// 객체 생성
        answerJp.setLayout(fLoL2);								// 레이아웃 설정
        answerJp.setPreferredSize(new Dimension(300, 80));		// 크기 설정 - new Dimension(너비, 높이)

        JLabel guideJl = new JLabel("단어 쓰기");					// 라벨 객체 생성
        guideJl.setFont(guideFt);								// 라벨 폰트 설정
        guideJl.setPreferredSize(new Dimension(300, 30));		// 라벨 크기 설정
        answerJp.add(guideJl);									// 패널에 라벨 추가

        answerJtf = new JTextField();							// 텍스트 필드 객체 생성
        answerJtf.setPreferredSize(new Dimension(300, 30));		// 텍스트 필드 크기 설정
        answerJp.add(answerJtf);								// 패널에 텍스트 필드 추가

        // 정답을 보여주는 패널
        JPanel rightAnswerJp = new JPanel(); 						// 객체 생성
        rightAnswerJp.setLayout(fLoL2);								// 레이아웃 설정
        rightAnswerJp.setPreferredSize(new Dimension(300, 100));	// 크기 설정 - new Dimension(너비, 높이)

        rightAnswerJl = new JLabel("정답 : ");						// 라벨 객체 생성
        rightAnswerJl.setFont(guideFt);								// 라벨 폰트 설정
        rightAnswerJl.setPreferredSize(new Dimension(300, 30));		// 라벨 크기 설정
        rightAnswerJp.add(rightAnswerJl);							// 패널에 라벨 추가

        // 버튼을 가지는 패널
        JPanel buttonJp = new JPanel(); 					// 객체 생성
        buttonJp.setLayout(bLo1);							// 레이아웃 설정
        buttonJp.setPreferredSize(new Dimension(300, 40));	// 크기 설정 - new Dimension(너비, 높이)

        JButton exit = new JButton("나가기");					// 버튼 객체 생성
        buttonJp.add(exit, BorderLayout.WEST);				// 패널에 버튼 추가

        JButton next = new JButton("다음 문제");				// 버튼 객체 생성
        buttonJp.add(next, BorderLayout.CENTER);			// 패널에 버튼 추가

        JButton check = new JButton("정답 확인");				// 버튼 객체 생성
        buttonJp.add(check, BorderLayout.EAST);				// 패널에 버튼 추가

        // 버튼에 이벤트 리스너 추가
        exit.addActionListener(this);
        next.addActionListener(this);
        check.addActionListener(this);

        // 컨테이너에 패널 추가
        add(titleJp);
        add(meanJp);
        add(answerJp);
        add(rightAnswerJp);
        add(buttonJp);
    }

    // 이벤트를 처리하는 메소드
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame mf = MainFrame.getInstance();		// 화면을 전환하는 메소드를 호출하기 위한 객체 생성
        String answer=answerJtf.getText(); //답변 문자열로 반환

        if(e.getActionCommand().equals("정답 확인")) {
            if(answer.equals(word)) {  //입력한 단어가 맞으면
                JOptionPane.showMessageDialog(this, "정답입니다!", "정답", JOptionPane.INFORMATION_MESSAGE);
                rightAnswerJl.setText("정답: "+word);
            }
            else {  // 입력한 뜻이 틀렸을 때
                JOptionPane.showMessageDialog(this, "틀렸습니다. 정답은 " + word + "입니다.", "오답",
                        JOptionPane.INFORMATION_MESSAGE);
                rightAnswerJl.setText("정답: " + word);
            }
        }
        else if (e.getActionCommand().equals("다음 문제")) {
            if (wordList != null && wordList.length > 0 &&currentIndex< wordList.length-1) {
                currentIndex = currentIndex + 1; // 다음 뜻 인덱스 계산
                answerJtf.setText(""); // answerJtf 초기화
                rightAnswerJl.setText("정답: "); //rightAnswerJl 초기화
                meanJl.setText(wordList[currentIndex][1]);  // 다음 뜻 표시
                word = wordList[currentIndex][0];
            }
            else if(currentIndex>= wordList.length-1){
                JOptionPane.showMessageDialog(this, "다음 단어가 없습니다.(단어를 더 추가하세요)", "단어 퀴즈 완료",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else if (e.getActionCommand().equals("나가기")) {
            mf.ChangeGUI("퀴즈");
        }
    }
}