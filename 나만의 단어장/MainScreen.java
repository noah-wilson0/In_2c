/*
 * 설명 : 메인화면으로서, 노트 목록를 조회하고 다양한 기능들을 선택할 수 있는 GUI 클래스
 * 
 * 컨테이너 형태로 작성됨.
 * 노트 선택 / 노트 추가 / 노트 삭제 / 단어 검색 / 회원정보 조회 화면으로 이동 / 홈 화면으로 이동 / 게임 화면으로 이동 기능을 포함함.
 * 노트 선택과 노트추가·노트삭제·단어검색 버튼의 이벤트는 내부 클래스 내에서 처리됨.
 * 회원정보·홈·게임 버튼의 이벤트는  GUI 클래스 내에서 처리됨.
 */

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import static java.awt.BorderLayout.*;
import static java.awt.BorderLayout.SOUTH;

public class MainScreen extends Container implements ActionListener {
	//폰트 객체 생성
    Font main_btn_Font = new Font("돋음", Font.PLAIN, 16);
    
    JPanel Home_jp;
    JPanel Menu_jp;
    
    //노트를 생성할때마다 노트 이름을 담을 배열
    ArrayList<String> note_NameLists = new ArrayList<String>();
    
    //Note클래스에서 넘겨 받을 노트 제목
    String clicked_Note="";  //Note_jp에서 클릭한 노트 이름을 저장할 변수
    
    //한 프레임에 13개가 최대 출력량
    //JList를 이용한 노트 기능 구현
    DefaultListModel<String> myList = new DefaultListModel<>();
    //DefaultListModel을 사용하면 데이터의 추가 또는 삭제가 발생할 때마다 자동으로 모델을 업데이트 해줌
    //예를 들어 ArrayList 사용시 데이터의 추가 또는 삭제가 발생할때 마다 수동적으로 모델을 업데이트 해야함
    JList<String> myLists = new JList<>(myList);
    //JList는 Swing 컴포넌트 중 하나로, 여러 항목을 나타내는 리스트를 구현할 때 사용함

    ArrayList<String> noteList = new ArrayList<>();

    //팝업창으로 전달받은 노트 이름 저장
    String add_note_Name ="";
    
    //팝업창으로 전달받은 삭제할 노트 이름 저장
    String del_note_Name ="";
    
    //db명령어 사용을 위한 db객체 생성
    ConnectDB cb=new ConnectDB();
    
    // 컨테이너 화면을 구성하는 생성자
    public MainScreen() {
        setLayout(new BorderLayout());        // 레이아웃 설정

        // 1.어떤 단어장인지...(최상단)
        Font fnt = new Font("돋음", Font.BOLD, 20); // 내 단어장 폰트
        // Width: 384, Height: 43
        Home_jp = new JPanel(); // 왼쪽 정렬을 하여 Note_Name을 왼쪽에 표시
        Home_jp.setBackground(Color.white); //배경색을 하얀색으로

        String user_name=cb.selectName(GetID.id);
        JLabel Note_Name = new JLabel(user_name+"의 단어장");
        Note_Name.setFont(fnt);

        // 글로벌 단어 검색 버튼(24x24)
        ImageIcon img_search = new ImageIcon("./btn_img/btn_search.png");
        JButton btn_serch = new JButton(img_search);
        btn_serch.setPreferredSize(new Dimension(30, 30));
        btn_serch.addActionListener(new serchWord());

        // 노트 추가 버튼
        ImageIcon img_notePlus = new ImageIcon("./btn_img/btn_notePlus.png");
        JButton btn_notePlus = new JButton(img_notePlus);
        btn_notePlus.setFont(main_btn_Font);
        btn_notePlus.setPreferredSize(new Dimension(30, 30));
        
        //노트 추가 이벤트
        btn_notePlus.addActionListener(new add_NoteTable());

        //노트 삭제 버튼
        ImageIcon img_delNote = new ImageIcon("./btn_img/btn_noteDel.png"); //노트 삭제 아이콘
        JButton btn_delNote=new JButton(img_delNote);//노트 삭제 아이콘
        btn_delNote.setPreferredSize(new Dimension(30, 30));

        //노트 삭제 이벤트
        btn_delNote.addActionListener(new del_NoteTable());

        //회원정보 버튼
        ImageIcon img_Info = new ImageIcon("./btn_img/btn_info.png");
        JButton btn_Info = new JButton(img_Info);
        btn_Info.setPreferredSize(new Dimension(30, 30));
        btn_Info.setActionCommand("회원정보");
        btn_Info.addActionListener(this);

        // Box 레이아웃으로 전체를 수직(vertical)으로 배치
        Box verticalBox = Box.createVerticalBox();

        // 위쪽에 위치할 Box
        Box topBox = Box.createHorizontalBox();
        topBox.add(Note_Name);

        // 아래쪽에 위치할 Box
        Box bottomBox = Box.createHorizontalBox();
        bottomBox.add(Box.createHorizontalGlue());  // 왼쪽 끝에 빈 공간
        bottomBox.add(Box.createHorizontalStrut(230));
        bottomBox.add(btn_serch);
        bottomBox.add(Box.createHorizontalStrut(10));  // 중간에 공간 추가 (10 픽셀 간격)
        bottomBox.add(btn_notePlus);
        bottomBox.add(Box.createHorizontalStrut(10));  // 중간에 공간 추가 (10 픽셀 간격)
        bottomBox.add(btn_delNote);
        bottomBox.add(Box.createHorizontalStrut(10)); // 중간에 공간 추가 (10 픽셀 간격)
        bottomBox.add(btn_Info);
        
        // 전체를 수직으로 배치
        verticalBox.add(topBox);
        verticalBox.add(bottomBox);
        
        // JFrame에 추가
        Home_jp.add(verticalBox);

        //2.노트처럼 보이는 공간(제목)의 일련(중단)
        JScrollPane Note_jp=new JScrollPane(myLists);  //한 프레임에 13개가 최대 출력량
        Note_jp.setBackground(Color.white); //배경색을 하얀색으로

        // JList의 글자색 설정
        myLists.setForeground(Color.black); // 텍스트의 글자색을 검정색으로 변경

        // Border 생성 -Note_jp에 적용하기 위함
        Border border = BorderFactory.createLineBorder(Color.black, 2); // 테두리 색상을 빨간색으로, 두께를 2로 설정

        // JList에 Border 설정
        Note_jp.setBorder(border);

        //렌더러를 사용하여 JList의 셀 레이아웃을 수정
        myLists.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, 
            											  boolean isSelected, boolean cellHasFocus) {
            	// DefaultListCellRenderer의 getListCellRendererComponent 메서드를 호출
            	Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, 
            															cellHasFocus);
                /*
                list: 현재 렌더러가 속한 JList입니다. 이를 통해 현재 JList의 속성이나 데이터에 접근할 수 있다.
                value: 현재 셀에 표시되는 데이터입니다. 보통 JList에서 각 항목은 텍스트 또는 사용자 지정 객체일 수 있다.
                index: 현재 셀의 인덱스입니다. 몇 번째 항목인지를 나타낸다.
                isSelected: 현재 셀이 선택되었는지 여부를 나타내는 불리언 값이다. 이를 통해 선택된 항목에 대한 시각적 효과를 구현할 수 있다.
                cellHasFocus: 현재 셀이 포커스를 가졌는지 여부를 나타내는 boolean 값. 포커스가 있는 셀에 대해 특별한 스타일을 적용할 수 있다.
                */
            	
            	// 원하는 폰트와 스타일로 수정
                setFont(main_btn_Font);
                
                // 화면 가로폭을 가득 채우도록 렌더러 수정
                renderer.setPreferredSize(new Dimension(360, 40)); // 노트들이 화면의 가로폭을 가득 채우게 기존(380,40)
                setBorder(BorderFactory.createLineBorder(Color.black)); // 테두리 추가
                return this;
            }
        });
        
        //Jlist의 아이템을 클릭하면...
        myLists.addMouseListener(new MyListsMouseListener());
        
        //DB에 있는 노트 이름을 note_NameLists, myList에 추가
        String note_names = cb.selectNoteList(GetID.id);
        if (note_names != null) {
        	String noteList[] = note_names.split("\\|");  //"|"로 구분된  note를 다시 "|"로 구분하여 배열 생성
            for (int i = 0; i < noteList.length; i++) {
            	note_NameLists.add(noteList[i]);
            	myList.addElement(noteList[i]);
            }
        }

        //3.홈화면, 게임을 클릭하면 다른 화면으로(하단)
        Menu_jp=new JPanel();
        Menu_jp.setBackground(Color.white); //배경색을 하얀색으로

        Box Menu_Box = Box.createHorizontalBox();

        //메뉴 아이콘 가져오기(24x24)
        ImageIcon img_home = new ImageIcon("./btn_img/btn_home.png");
        ImageIcon img_game = new ImageIcon("./btn_img/btn_game.png");
        
        //메뉴 구성-홈(누르면 홈화면), 게임(영단어퀴즈)
        JButton btn_home = new JButton(img_home);
        JButton btn_quiz = new JButton(img_game);
        
        //버튼 사이즈 조정
        btn_home.setPreferredSize(new Dimension(30, 30));
        btn_quiz.setPreferredSize(new Dimension(30, 30));
        
        // 박스에 추가
        Menu_Box.add(btn_home);
        Menu_Box.add(btn_quiz);
        
        //버튼을 메뉴 패널에 추가
        Menu_jp.add(Menu_Box);
        Menu_jp.setBorder(BorderFactory.createEmptyBorder(20 , 10 , 10 , 10));
        
        //홈 화면 구성
        add(Home_jp, NORTH);
        add(Note_jp, CENTER);
        add(Menu_jp, SOUTH);
        
        //버튼 클릭시...
        btn_home.setActionCommand("메인화면");
        btn_quiz.setActionCommand("퀴즈");
        
        //버튼 클릭시 이벤트 발생...
        btn_home.addActionListener(this);
        btn_quiz.addActionListener(this);
    }
    
    // 이벤트 처리 루틴 작성 - 회원정보, 홈, 게임 버튼
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame mf = MainFrame.getInstance();		// 메인 프레임 클래스로부터 객체 생성 (생성자 호출 X - 싱글톤 패턴)
        
        if (e.getActionCommand().equals("퀴즈")) {	// 퀴즈 버튼 클릭 시
        	String note_names = cb.selectNoteList(GetID.id);	// DB에 추가된 노트들을 조회
        	if (note_names == null) {							// 노트가 존재하지 않으면..
        		JOptionPane.showMessageDialog(null, "퀴즈를 풀기 위한 노트가 존재하지 않습니다.\n노트를 추가해주세요.");
        	}
        	else {												// 노트가 존재하면..
        		mf.ChangeGUI("퀴즈");		// 퀴즈 선택 화면으로 이동
        	}
        }
        else {										// 퀴즈 외 다른 버튼 클릭 시
        	mf.ChangeGUI(e.getActionCommand());			// 해당 화면으로 이동
        }
    }
    
    // 이벤트 리스너를 내부 클래스로 구현 - 노트 리스트
    private class MyListsMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            int selectedIndex = myLists.getSelectedIndex();
            
            if (selectedIndex == -1) { // 선택된 요소가 없으면 아무것도 하지 않음
                myLists.clearSelection(); // 선택한 Jlist 커서를 해제함
                return;
            }
            else if (e.getClickCount() == 1 || e.getClickCount() == 2) { // 마우스 클릭 횟수가 1 또는 2번인 경우에만 처리
                int note_size = myLists.getModel().getSize();

                if (selectedIndex < note_size) {
                    // 선택한 Jlist가 잘 출력되는지
                    // 클릭한 노트 가져오기
                    clicked_Note = myLists.getModel().getElementAt(selectedIndex);

                    // 클릭한 노트 프레임 불러오기
                    MainFrame mf = MainFrame.getInstance();
                    mf.ChangeGUI("노트", clicked_Note);
                    myLists.clearSelection(); // 선택한 Jlist 커서를 해제함
                }
            }
            else if (e.getClickCount() > 2) {
                System.out.println("노트 클릭: 오류발생");
                myLists.clearSelection(); // 선택한 Jlist 커서를 해제함
            }
        }
    }

    //노트 추가 버튼 클릭시... 노트 테이블 생성(이벤트 처리를 위한 내부 클래스 구현)
    private class add_NoteTable implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	ConnectDB cb = new ConnectDB();
            // 여기에 노트 추가 기능을 구현
            // 새 노트를 생성하고 노트 목록에 추가하는 코드를 작성함
            //새 노트의 이름 입력받기
            String newNoteName="";
            add_note_Name = JOptionPane.showInputDialog(null,"노트 이름을 입력하세요:", "노트 이름 입력", 
            											JOptionPane.PLAIN_MESSAGE);
            if (add_note_Name != null && !add_note_Name.isEmpty()) {
                newNoteName = add_note_Name; // 새 노트의 이름
                //노트 추가
                cb.addNote(GetID.id,newNoteName); //사용자의 노트에 table추가
                note_NameLists.add(newNoteName);   //note_NameLists에 새로 생성된 노트이름 추가
                myList.addElement(newNoteName);
            }
            else{
                JOptionPane.showMessageDialog(null, "노트 이름을 입력하지 않았습니다.", "경고", 
                							  JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    //노트 삭제 버튼 클릭시... 노트 테이블 삭제(이벤트 처리를 위한 내부 클래스 구현)
    private class del_NoteTable implements ActionListener {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        //노트 삭제 버튼 클릭시.. 어떤 노트를 삭제할지 노트 이름을 팝업창으로 받음
	        del_note_Name = JOptionPane.showInputDialog(null,"노트 이름을 입력하세요:", "노트 이름 입력", 
	        											JOptionPane.PLAIN_MESSAGE);
	
	        if (del_note_Name != null && !del_note_Name.isEmpty() ) {
	            //실제로 존재하는 노트인지 검사
	            if(note_NameLists.contains(del_note_Name)) {
	                //노트 삭제 이벤트
	                ConnectDB cb = new ConnectDB();
	                cb.deleteNote(GetID.id, del_note_Name);
	                // 삭제 완료시 리스트에서 노트 이름 삭제
	                myList.removeElement(del_note_Name);
	            }
	            else{
	                JOptionPane.showMessageDialog(null, "삭제할 노트 이름을 바르게 입력하시오.", "경고", 
	                							  JOptionPane.WARNING_MESSAGE);
	            }
	        }
	        else{
	            JOptionPane.showMessageDialog(null, "삭제할 노트 이름을 입력하지 않았습니다.", "경고", 
	            							  JOptionPane.WARNING_MESSAGE);
	        }
	    }
    }
    
    //버튼 search 버튼 클릭시...(이벤트 처리를 위한 내부 클래스 구현)
    private class serchWord extends Component implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //찾을 단어 입력 받기
            String searchingWord;
            searchingWord = JOptionPane.showInputDialog(this, "검색할 단어를 입력하세요.", "단어 검색", 
            											JOptionPane.INFORMATION_MESSAGE);
            
            if (searchingWord != null) { //찾을 단어를 입력 받으면...
                String[] result_word;
                result_word = cb.searchWord(GetID.id, searchingWord);
                if (result_word[0] != null) {
                    System.out.println("찾은 단어:"+result_word[0]);
                    JOptionPane.showMessageDialog(null,"단어를 찾았습니다.("+result_word[2]+"에 있습니다.)");
                }
                else {
                    System.out.println("찾은 단어 없음");
                    JOptionPane.showMessageDialog(null,"찾은 단어가 없습니다.(입력한 단어:"+searchingWord+")");
                }
            }
            else {
                System.out.println("사용자가 취소하거나 빈 문자열을 입력했습니다.");
            }
        }
    }
}