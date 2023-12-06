/*
 * 설명 : 영단어를 추가하는 GUI 클래스
 *
 * 컨테이너 형태로 작성됨.
 * 이벤트는 GUI 클래스 내에서 처리됨.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import static java.lang.String.join;

public class AddEnglish extends Container implements ActionListener {
    //이벤트에서 쓰기 위함
    JTextField wordJtf;
    String noteName;
    JTextField meanJtf[];
    JCheckBox partCheck[];

    // 컨테이너 화면을 구성하는 생성자
    public AddEnglish(String noteName) {
        this.noteName=noteName;
        // 레이아웃 객체 생성
        FlowLayout fLoC1 = new FlowLayout(FlowLayout.CENTER, 0, 0);
        FlowLayout fLoC2 = new FlowLayout(FlowLayout.CENTER, 0, 10);
        FlowLayout fLoC3 = new FlowLayout(FlowLayout.CENTER, 30, 20);
        FlowLayout fLoL = new FlowLayout(FlowLayout.LEFT, 0, 0);

        // 폰트 객체 생성 - new Font("글꼴", Font.효과, 크기) (효과 : PLAIN-보통, BOLD-진하게, ITALIC-기울이기)
        Font titleFt = new Font("맑은 고딕", Font.BOLD, 24);
        Font informFt = new Font("맑은 고딕", Font.PLAIN, 14);
        Font mainFt = new Font("맑은 고딕", Font.BOLD, 14);

        setLayout(fLoC1);				// 레이아웃 설정
        // 현재 화면을 설명하는 패널
        JPanel titleJp = new JPanel(); 							// 객체 생성
        titleJp.setLayout(fLoC2);								// 레이아웃 설정
        titleJp.setPreferredSize(new Dimension(320, 80));		// 크기 설정 - new Dimension(너비, 높이)

        JLabel titleJl = new JLabel("영단어 추가하기");				// 라벨 객체 생성
        titleJl.setFont(titleFt);								// 라벨 폰트 설정
        titleJp.add(titleJl);									// 패널에 라벨 추가

        // 단어를 추가하는 방법을 알려주는 패널
        JPanel informJp = new JPanel();											// 객체 생성
        informJp.setLayout(fLoC1);												// 레이아웃 설정
        informJp.setPreferredSize(new Dimension(320, 80));						// 크기 설정 - Dimension(너비, 높이)

        JLabel informJl1 = new JLabel("* 단어와 뜻을 입력한 후 품사를 선택해주세요.");		// 라벨1 객체 생성
        informJl1.setFont(informFt);											// 라벨1 폰트 설정
        informJl1.setPreferredSize(new Dimension(320, 20));						// 라벨1 크기 설정
        informJp.add(informJl1);												// 패널에 라벨1 추가

        JLabel informJl2 = new JLabel("* 뜻은 최대 5개까지 입력하실 수 있습니다.");		// 라벨2 객체 생성
        informJl2.setFont(informFt);											// 라벨2 폰트 설정
        informJl2.setPreferredSize(new Dimension(320, 20));						// 라벨2 크기 설정
        informJp.add(informJl2);												// 패널에 라벨2 추가

        JLabel informJl3 = new JLabel("* 품사는 필요한 만큼 모두 선택하실 수 있습니다.");	// 라벨3 객체 생성
        informJl3.setFont(informFt);											// 라벨3 폰트 설정
        informJl3.setPreferredSize(new Dimension(320, 20));						// 라벨3 크기 설정
        informJp.add(informJl3);												// 패널에 라벨3 추가

        // 단어를 입력받는 패널
        JPanel wordJp = new JPanel();						// 객체 생성
        wordJp.setLayout(fLoL);								// 레이아웃 설정
        wordJp.setPreferredSize(new Dimension(320, 40));	// 크기 설정 - new Dimension(너비, 높이)

        JLabel wordJl = new JLabel("새로운 단어 입력 ");			// 라벨 객체 생성
        wordJl.setFont(mainFt);								// 라벨 폰트 설정
        wordJl.setPreferredSize(new Dimension(130, 40));	// 라벨 크기 설정
        wordJp.add(wordJl);									// 패널에 라벨 추가
        //wordJl.setBorder(new TitledBorder(""));

        wordJtf = new JTextField();				// 텍스트 필드 객체 생성
        wordJtf.setPreferredSize(new Dimension(150, 30));	// 텍스트 필드 크기 설정
        wordJp.add(wordJtf);								// 패널에 텍스트 필드 추가

        // 뜻을 입력받는 패널
        JPanel meanJp = new JPanel();								// 객체 생성
        meanJp.setLayout(fLoL);										// 레이아웃 설정
        meanJp.setPreferredSize(new Dimension(320, 200));			// 크기 설정 - Dimension(너비, 높이)

        JLabel meanJl[] = new JLabel[5];							// 라벨 객체 배열 생성
        meanJtf = new JTextField[5];					// 텍스트 필드 객체 배열 생성
        for (int i=0; i<5; i++) {
            meanJl[i] = new JLabel("뜻" + (int)(i+1) + " 입력 ");		// 라벨 객체 생성
            meanJl[i].setFont(mainFt);								// 라벨 폰트 설정
            meanJl[i].setPreferredSize(new Dimension(130, 40));		// 라벨 크기 설정
            meanJp.add(meanJl[i]);									// 패널에 라벨 추가

            meanJtf[i] = new JTextField();							// 텍스트 필드 객체 생성
            meanJtf[i].setPreferredSize(new Dimension(150, 30));	// 텍스트 필드 크기 설정
            meanJp.add(meanJtf[i]);									// 패널에 텍스트 필드 추가
        }

        // 품사를 입력받는 패널
        JPanel partJp = new JPanel();								// 객체 생성
        partJp.setLayout(fLoC1);									// 레이아웃 설정
        partJp.setPreferredSize(new Dimension(320, 98));			// 크기 설정 - Dimension(너비, 높이)

        JLabel partJl = new JLabel("[품사 선택] ");					// 라벨 객체 생성
        partJl.setFont(mainFt);										// 라벨 폰트 설정
        partJl.setPreferredSize(new Dimension(320, 40));			// 라벨 크기 설정
        partJp.add(partJl);											// 패널에 라벨 추가

        JPanel partChoiceJp1 = new JPanel();						// 체크박스 앞부분을 가지는 패널1 객체 생성
        partChoiceJp1.setLayout(fLoL);								// 패널1 레이아웃 설정
        partChoiceJp1.setPreferredSize(new Dimension(320, 28));		// 패널1 크기 설정

        JPanel partChoiceJp2 = new JPanel();						// 체크박스 뒷부분을 가지는 패널2 객체 생성
        partChoiceJp2.setLayout(fLoL);								// 패널2 레이아웃 설정
        partChoiceJp2.setPreferredSize(new Dimension(320, 28));		// 패널2 크기 설정

        String partList[] = {"동사", "명사", "부사", "형용사", "대명사", "전치사", "접속사", "감탄사", "숙어"};
        partCheck = new JCheckBox[9];								// 체크박스 객체 배열 생성
        for (int i=0; i<9; i++) {
            partCheck[i] = new JCheckBox(partList[i]);					// 체크박스 객체 생성
            partCheck[i].setPreferredSize(new Dimension(64, 20));		// 크기 설정
            if (i < 4) {
                partChoiceJp1.add(partCheck[i]);							// 패널1에 체크박스 앞부분 4개 추가
            }
            else {
                partChoiceJp2.add(partCheck[i]);							// 패널2에 체크박스 뒷부분 4개 추가
            }
        }
        partJp.add(partChoiceJp1);									// 패널에 패널1 추가
        partJp.add(partChoiceJp2);									// 패널에 패널2 추가

        // 저장 버튼과 취소 버튼을 가지는 패널
        JPanel buttonJp = new JPanel();						// 객체 생성
        buttonJp.setLayout(fLoC3);							// 레이아웃 설정
        buttonJp.setPreferredSize(new Dimension(320, 62));	// 크기 설정 - Dimension(너비, 높이)

        JButton save = new JButton("저장");					// 저장 버튼 객체 생성
        save.setPreferredSize(new Dimension(120, 40));		// 크기 설정
        buttonJp.add(save);									// 패널에 저장 버튼 추가

        JButton cancel = new JButton("취소");					// 취소 버튼 객체 생성
        cancel.setPreferredSize(new Dimension(120, 40));	// 크기 설정
        buttonJp.add(cancel);								// 패널에 취소 버튼 추가

        //이벤트 주기 - 저장, 취소
        save.addActionListener(this);
        cancel.addActionListener(this);
        // 컨테이너에 패널 추가
        add(titleJp);	// 현재 화면을 설명하는 패널
        add(informJp);	// 단어를 추가하는 방법을 알려주는 패널
        add(wordJp);		// 단어를 입력받는 패널
        add(meanJp);		// 뜻을 입력받는 패널
        add(partJp);		// 품사를 입력받는 패널
        add(buttonJp);	// 저장 버튼과 취소 버튼을 가지는 패널
    }

    // 이벤트를 처리하는 메소드
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame mf = MainFrame.getInstance();     // 메인 프레임 클래스로부터 객체 생성 (생성자 호출 X - 싱글톤 패턴)
        //입력한 뜻을 입력한 만큼 동적 배열로 받는다.
        List<String> meanList = new ArrayList<>();

        //체크한 품사만큼 동적 배열로 받는다.
        List<String> pos = new ArrayList<>();
        //"저장" 버튼을 누르면...
        if(e.getActionCommand().equals("저장")){
            //입력된 뜻 만큼 정적 배열에 추가
            for (int i=0; i<5; i++) {
                String mean=meanJtf[i].getText();
                if(!mean.isEmpty()){  //입력한 뜻만  meanList에 저장후, insert할 예정;
                    meanList.add(mean);
                }
            }
            //meanList를 ", "로 구분해 문자열로 만들기
            String mean=join(", ",meanList);

            //체크한 품사만큼 배열 생성
            for (int i = 0; i < partCheck.length; i++) {
                if (partCheck[i].isSelected()) {
                    pos.add(partCheck[i].getText());
                }
            }

            //해당 노트 table 에insert하기 위한 db명령어 사용
            ConnectDB cb=new ConnectDB();
            cb.insertWord(GetID.id,noteName,wordJtf.getText(),mean,pos);  //id,영단어,뜻,품사
            mf.ChangeGUI("노트", noteName); 	// 컨테이너를 전환하는 메소드에 클릭한 버튼의 이름을 매개변수로 전달
        }
        else if (e.getActionCommand().equals("취소")) {
            mf.ChangeGUI("노트", noteName);	// 컨테이너를 전환하는 메소드에 클릭한 버튼의 이름을 매개변수로 전달
        }
        else{ //"취소"
            //노트화면으로 돌아가기
            //클릭한 노트 프레임 불러오기
            System.out.println("단어 추가 에러");
            mf.ChangeGUI("노트", noteName);	// 컨테이너를 전환하는 메소드에 클릭한 버튼의 이름을 매개변수로 전달
        }
    }
}