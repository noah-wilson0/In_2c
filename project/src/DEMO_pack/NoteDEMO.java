//package DEMO_pack;/*
//       단어 추가,삭제 버튼을 클릭시... -db 구현되어야함
// */
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import static java.awt.BorderLayout.*;
//
//public class NoteDEMO extends Container implements ActionListener {
//    //폰트
//    //노트 이름 폰트
//    //단어 폰트
//    //노트 삭제 단어 추가 단어삭제들은 아이콘을 넣을지 글자로 할지 추후에 결정 후 폰트 작성함
//
//    //상단(위쪽 센터)- 노트이름
//    //상단(아래) - 노트 삭제, [단어 추가(EnglishAdd  프레임으로 변환)],단어 삭제
//    //중단 - 삭제할때(JCheckBox)를 눌러서 삭제할 품목 체크, 단어 조회(스크롤팬 사용)
//    //하단 - 홈,퀴즈 버튼
//    String noteName; //이벤트에서 쓰기 위함
//    GetID gi=new GetID();
//    public  NoteDEMO(String noteName){
//        this.noteName=noteName;
//        setLayout(new BorderLayout());
//        //상단 패널 - top_jp
//        JPanel top_jp=new JPanel();
////        top_jp.setBackground(Color.white); //배경색을 하얀색으로
//        //상단(위쪽 센터)- 노트이름
//        JLabel note_title = new JLabel("현재 노트: " + noteName);
//        note_title.setFont(new Font("돋음", Font.BOLD, 20));
//        //상단(아래) - 노트 삭제, [단어 추가(EnglishAdd  프레임으로 변환)],단어 삭제
//        //이미지
//        ImageIcon img_addWord = new ImageIcon("./btn_img/btn_addWord.png"); //단어 추가 아이콘
//        ImageIcon img_delWord = new ImageIcon("./btn_img/btn_delWord.png"); //단어 삭제 아이콘
//        //버튼
//        JButton btn_addWord=new JButton(img_addWord);//단어 추가 아이콘
//        JButton btn_delWord=new JButton(img_delWord);//단어 삭제 아이콘
//
//        //버튼 사이즈 30x30 조정
//        btn_addWord.setPreferredSize(new Dimension(30, 30));
//        btn_delWord.setPreferredSize(new Dimension(30, 30));
//
//        // top_jp 레이아웃 : Box
//        // Box 레이아웃으로 전체를 수직(vertical)으로 배치
//        Box verticalBox = Box.createVerticalBox();
//         //박스의 상단(위쪽)에 위치할 Box
//        Box topBox = Box.createHorizontalBox();
//        topBox.add(note_title);
//
//        // 박스의 하단(아래쪽)에 위치할 Box
//        Box bottomBox = Box.createHorizontalBox();
//        bottomBox.add(Box.createHorizontalGlue());  // 왼쪽 끝에 빈 공간
//        bottomBox.add(Box.createHorizontalStrut(260));
//        bottomBox.add(Box.createHorizontalStrut(10));  // 중간에 공간 추가 (10 픽셀 간격)
//        bottomBox.add(btn_addWord); //단어 추가 버튼
//        bottomBox.add(Box.createHorizontalStrut(10)); // 중간에 공간 추가 (10 픽셀 간격)
//        bottomBox.add(btn_delWord); //단어 삭제 버튼
//        // 전체를 수직으로 배치
//        verticalBox.add(topBox);
//        verticalBox.add(bottomBox);
//        // JFrame에 추가
//        top_jp.add(verticalBox);
//        //상단 패널 컨테이너에 추가
//        add(top_jp);
//
//        //단어 추가,삭제 버튼을 클릭시... -db 구현되어야함
//        //단어 추가/삭제 버튼 반환값 변경
//        btn_addWord.setActionCommand("단어추가");
//        btn_delWord.setActionCommand("단어삭제");
//        //단어 추가/삭제 버튼 이벤트
//        btn_addWord.addActionListener(this);
//        btn_delWord.addActionListener(this);
//
//        //중단 - 단어 조회(스크롤팬 사용)
//
//        //콘텐츠 - 단어 출력
//        ConnectDB cb=new ConnectDB();  //db연결
//
//        // 폰트 객체 생성
//        Font wordFt = new Font("돋음", Font.BOLD, 24);
//        Font mainFt = new Font("돋음", Font.PLAIN, 13);
//
//        // 색상 객체 생성
//        Color mainCr = new Color(100, 100, 100);
//        Color lineCr = new Color(240, 240, 240);
//
//        // 영단어를 보여주는 패널
//        JPanel englishJp = new JPanel(); 						// 객체 생성
//        englishJp.setLayout(new FlowLayout(FlowLayout.CENTER));		// 레이아웃 설정
//
//        englishJp.setBackground(Color.white);		// 배경색 설정
//
//        JScrollPane scrollPane = new JScrollPane(englishJp, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        scrollPane.setBorder(BorderFactory.createLineBorder(Color.black,2));
//
//        String[][] english = cb.lookEnglish(gi.id, noteName);		// 노트 테이블에 있는 모든 영단어들을 전달받는 2차원 배열 생성
//        if (english != null) {									// 노트에 영단어가 있다면..
//            int count = english.length;							// 영단어의 개수를 count에 저장
//
//            englishJp.setPreferredSize(new Dimension(360, 131*count));	// 크기 설정 - new Dimension(너비, 높이)
//            JLabel[] wordJl = new JLabel[count];				// 라벨 배열 객체 생성 (단어)
//            JLabel[] partJl = new JLabel[count];				// 라벨 배열 객체 생성 (품사)
//            JLabel[] meanJl = new JLabel[count];				// 라벨 배열 객체 생성 (뜻)
//            JLabel[] line = new JLabel[count];					// 라벨 배열 객체 생성 (경계선)
//
//            for (int i=0; i<count; i++) {						// 노트에 있는 영단어의 개수만큼 반복
//                wordJl[i] = new JLabel(english[i][0]);  // 라벨 객체 생성 (단어 출력)
//                wordJl[i].setFont(wordFt);  // 폰트 설정
//                wordJl[i].setPreferredSize(new Dimension(386, 50));  // 크기 설정
//                wordJl[i].setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 0));  // 안 여백 지정 (상, 좌, 하, 우)
//                englishJp.add(wordJl[i]);  // 패널에 라벨 추가
//
//                partJl[i] = new JLabel(english[i][2]);  // 라벨 객체 생성 (품사 출력)
//                partJl[i].setFont(mainFt);  // 폰트 설정
//                partJl[i].setForeground(mainCr);  // 폰트 색상 설정
//                partJl[i].setPreferredSize(new Dimension(386, 30));  // 크기 설정
//                partJl[i].setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));  // 안 여백 지정 (상, 좌, 하, 우)
//                englishJp.add(partJl[i]);  // 패널에 라벨 추가
//
//                meanJl[i] = new JLabel(english[i][1]);  // 라벨 객체 생성 (뜻 출력)
//                meanJl[i].setFont(mainFt);  // 폰트 설정
//                meanJl[i].setForeground(mainCr);  // 폰트 색상 설정
//                meanJl[i].setPreferredSize(new Dimension(386, 30));  // 크기 설정
//                meanJl[i].setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 0));  // 안 여백 지정 (상, 좌, 하, 우)
//                englishJp.add(meanJl[i]);  // 패널에 라벨 추가
//
//                line[i] = new JLabel();  // 라벨 객체 생성 (경계선 출력)
//                line[i].setPreferredSize(new Dimension(386, 1));  // 크기 설정
//                line[i].setOpaque(true);  // 불투명 여부 설정 (투명하지 않아야 배경색 표현 가능)
//                line[i].setBackground(lineCr);  // 배경색 설정
//                englishJp.add(line[i]);  // 패널에 라벨 추가
//            }
//        }
//
//        //하단
//        JPanel button_jp=new JPanel();
////        Menu_jp = new JPanel(new FlowLayout());  //메뉴 패널 배치
//        button_jp.setBackground(Color.white); //배경색을 하얀색으로
//
//        Box Menu_Box = Box.createHorizontalBox();
//
//        //메뉴 아이콘 가져오기(24x24)
//        ImageIcon img_home = new ImageIcon("./btn_img/btn_home.png");
//        ImageIcon img_game = new ImageIcon("./btn_img/btn_game.png");
//        //메뉴 구성-홈(누르면 홈화면), 게임(단어퀴즈, 미니게임)
//        JButton btn_home = new JButton(img_home);
//        JButton btn_quiz = new JButton(img_game);
//        //버튼 사이즈 조정
//        btn_home.setPreferredSize(new Dimension(30, 30));
//        btn_quiz.setPreferredSize(new Dimension(30, 30));
//        // 박스에 추가
//        Menu_Box.add(btn_home);
//        Menu_Box.add(btn_quiz);
//        //버튼을 메뉴 패널에 추가
//        button_jp.add(Menu_Box);
//        button_jp.setBorder(BorderFactory.createEmptyBorder(20 , 10 , 10 , 10));
//        //홈 화면 구성
//
//        add(top_jp, NORTH);  //성공
//        add(scrollPane,CENTER);
//        add(button_jp, SOUTH);
//        //버튼 클릭시...
//        btn_home.setActionCommand("메인화면");
//        btn_quiz.setActionCommand("퀴즈");
//        //버튼 클릭시 이벤트 발생...
//        btn_home.addActionListener(this);
//        btn_quiz.addActionListener(this);
//    }
//    // 이벤트 처리 루틴 작성 -this
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        MainFrame mf = MainFrame.getInstance();		// 메인 프레임 클래스로부터 객체 생성 (생성자 호출 X - 싱글톤 패턴)
//        switch (e.getActionCommand()){
//            case "메인화면":
//                mf.ChangeGUI(e.getActionCommand());		// 컨테이너를 전환하는 메소드에 클릭한 버튼의 이름을 매개변수로 전달
//                break;
//            case "퀴즈":
//                mf.ChangeGUI(e.getActionCommand());		// 컨테이너를 전환하는 메소드에 클릭한 버튼의 이름을 매개변수로 전달
//                break;
//            case "단어추가":
//                mf.ChangeGUI("단어추가",noteName);
//                break;
//            case "단어삭제":
//                mf.ChangeGUI("단어삭제",noteName);
//                break;
//
//        }
//
//    }
//
//}
