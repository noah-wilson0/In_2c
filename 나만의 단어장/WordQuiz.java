/*
 * 설명 : 주관식 단어 퀴즈를 푸는 GUI 클래스
 * 
 * 컨테이너 형태로 작성됨.
 * 이벤트는 GUI 클래스 내에서 처리됨.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class WordQuiz extends Container implements ActionListener {
    String noteName="";
    JTextField answerJtf; //입력받을  JTextField 선언
    ConnectDB cb=new ConnectDB();   //db명령어 사용하기
    String wordList[][]; //db에서 받을 배열 선언
    JLabel wordJl; //단어 레이블 선언
    String mean; //뜻 문자열 선언;
    JLabel rightAnswerJl;  //정답 레이블 선언
    
    //현재 단어,뜻 배열의 인덱스
    int currentIndex=0;
    
    // 컨테이너 화면을 구성하는 생성자
    public WordQuiz(String noteName) {
        this.noteName=noteName;
        
        // 레이아웃 객체 생성
        FlowLayout fLoC1 = new FlowLayout(FlowLayout.CENTER, 0, 0);
        FlowLayout fLoL1 = new FlowLayout(FlowLayout.LEFT, 0, 30);
        FlowLayout fLoL2 = new FlowLayout(FlowLayout.LEFT, 0, 0);
        BorderLayout bLo1 = new BorderLayout(10,10);

        // 폰트 객체 생성
        Font titleFt = new Font("돋음", Font.BOLD, 24);
        Font mainFt = new Font("돋음", Font.PLAIN, 14);
        Font wordFt = new Font("돋음", Font.BOLD, 32);
        Font guideFt = new Font("돋음", Font.BOLD, 14);

        // 컨테이너 레이아웃 설정
        setLayout(fLoC1);

        // 현재 화면을 설명하는 패널
        JPanel titleJp = new JPanel(); 								// 객체 생성
        titleJp.setLayout(fLoL1);									// 레이아웃 설정
        titleJp.setPreferredSize(new Dimension(300, 180));			// 크기 설정 - new Dimension(너비, 높이)

        JLabel titleJl = new JLabel("주관식 단어 퀴즈");					// 라벨 객체 생성
        titleJl.setFont(titleFt);									// 라벨 폰트 설정
        titleJl.setPreferredSize(new Dimension(300, 30));			// 라벨 크기 설정
        titleJp.add(titleJl);										// 패널에 라벨 추가

        JLabel noteJl = new JLabel("노트 : \"" + noteName + "\"");	// 라벨 객체 생성
        noteJl.setFont(mainFt);										// 라벨 폰트 설정
        noteJl.setPreferredSize(new Dimension(300, 20));			// 라벨 크기 설정
        titleJp.add(noteJl);										// 패널에 라벨 추가

        // 단어를 보여주는 패널
        JPanel wordJp = new JPanel(); 							// 객체 생성
        wordJp.setLayout(fLoC1);								// 레이아웃 설정
        wordJp.setPreferredSize(new Dimension(300, 100));		// 크기 설정 - new Dimension(너비, 높이)

        wordList =cb.selectWordAndMeaning(GetID.id,noteName);	//현재 가지고 있는 단어를 배열로 생성
        
        wordJl = new JLabel(wordList[currentIndex][0]);			// 라벨 객체 생성 - 영단어
        mean=wordList[currentIndex][1];  //영단어에 해당하는 뜻 저장
        wordJl.setFont(wordFt);								// 라벨 폰트 설정
        wordJp.add(wordJl);									// 패널에 라벨 추가

        // 답안을 입력받는 패널
        JPanel answerJp = new JPanel(); 								// 객체 생성
        answerJp.setLayout(fLoL2);										// 레이아웃 설정
        answerJp.setPreferredSize(new Dimension(300, 100));				// 크기 설정 - new Dimension(너비, 높이)

        JLabel guideJl1 = new JLabel("뜻 쓰기");							// 라벨 객체 생성
        guideJl1.setFont(guideFt);										// 라벨 폰트 설정
        guideJl1.setPreferredSize(new Dimension(300, 20));				// 라벨 크기 설정
        answerJp.add(guideJl1);											// 패널에 라벨 추가
        
        JLabel guideJl2 = new JLabel("(뜻이 여러 개인 경우 하나만 작성하세요.)");	// 라벨 객체 생성
        guideJl2.setFont(guideFt);										// 라벨 폰트 설정
        guideJl2.setPreferredSize(new Dimension(300, 30));				// 라벨 크기 설정
        answerJp.add(guideJl2);											// 패널에 라벨 추가

        answerJtf = new JTextField();									// 텍스트 필드 객체 생성
        answerJtf.setPreferredSize(new Dimension(300, 30));				// 텍스트 필드 크기 설정
        answerJp.add(answerJtf);										// 패널에 텍스트 필드 추가

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
        add(wordJp);
        add(answerJp);
        add(rightAnswerJp);
        add(buttonJp);

    }

    // 이벤트를 처리하는 메소드
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame mf = MainFrame.getInstance();		// 화면을 전환하는 메소드를 호출하기 위한 객체 생성
        String meanList[]=mean.split(", "); // ["뜻1","뜻2",...] 뜻을 배열로 재생성
        String answer=answerJtf.getText(); //답변 문자열로 반환
        boolean correctAnswer = false; //정답을 맞히면 true
        
        if(e.getActionCommand().equals("정답 확인")){
            for (int i = 0; i < meanList.length; i++) { //뜻 길이만큼 반복
                if(answer.equals(meanList[i])){  //입력한 뜻이 맞으면
                    correctAnswer=true;
                    JOptionPane.showMessageDialog(this, "정답입니다!", "정답", JOptionPane.INFORMATION_MESSAGE);
                    rightAnswerJl.setText("정답: "+mean);
                    break; //입력한 뜻이 맞으면 반복문 중료
                }
            }
            if(!correctAnswer) {  // 입력한 뜻이 틀렸을 때
                if(meanList.length==1) {
                    JOptionPane.showMessageDialog(this, "틀렸습니다. 정답은 " + mean + "입니다.", "오답", 
                    							  JOptionPane.INFORMATION_MESSAGE);
                    rightAnswerJl.setText("정답: " + mean);
                }
                else if(meanList.length>1) {
                    JOptionPane.showMessageDialog(this, "틀렸습니다. 정답은 " + mean + "입니다.", "오답", 
                    							  JOptionPane.INFORMATION_MESSAGE);
                    rightAnswerJl.setText("정답: " + mean);
                }
            }
        }
        else if (e.getActionCommand().equals("다음 문제")) {
            if (wordList != null && wordList.length > 0 &&currentIndex<wordList.length-1) {
                currentIndex = currentIndex + 1; // 다음 단어 인덱스 계산
                answerJtf.setText(""); // answerJtf 초기화
                rightAnswerJl.setText("정답: "); //rightAnswerJl 초기화
                wordJl.setText(wordList[currentIndex][0]);  // 다음 단어 표시
                mean = wordList[currentIndex][1];
            }
            else if(currentIndex>=wordList.length-1){
                JOptionPane.showMessageDialog(this, "다음 단어가 없습니다.(단어를 더 추가하세요)", "단어 퀴즈 완료", 
                							  JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else if (e.getActionCommand().equals("나가기")) {
            mf.ChangeGUI("퀴즈");
        }
    }
}