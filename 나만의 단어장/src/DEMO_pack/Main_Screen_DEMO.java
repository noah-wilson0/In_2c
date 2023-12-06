package DEMO_pack;/*
단어장 메인 화면
    1.CardLayout사용해서 원할한 메뉴(패널) 전환 적용- 홈 화면 하면 홈 패널 /
    2. 기본 화면 구성하기
    3. 노트 클릭시 나올 화면 클래스로 구현
    4. 일련의 노트 출력시킬 클래스
    5.

 */

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static java.awt.BorderLayout.*;

public class Main_Screen_DEMO extends JFrame {
    //상단 패널, 중단패널,하단 패널
    JPanel Home_jp;
    JPanel Note_jp;
    JPanel Menu_jp;
    public Main_Screen_DEMO(){
        //홈
        Container ct=getContentPane();




        //1.어떤 단어장인지...(최상단)
        Home_jp=new JPanel(new BorderLayout());
        JLabel Note_Name=new JLabel("내 단어장");
        Home_jp.add(Note_Name, NORTH);

        //2.노트처럼 보이는 공간(제목)의 일련(중단)
        Note_jp=new JPanel(new FlowLayout());
        //노트를 생성할때마다 담을 배열
        ArrayList<String> Note_name = new ArrayList<String>();
        //노트 테스트
        Note_name.add("노트 1번");
//        Note_name.add("노트 2번");
        //JList를 이용한 노트 기능 구현
        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> myList = new JList<>(listModel);
//        listModel.addElement(String.valueOf(Note_name));
        // 렌더러를 사용하여 JList의 셀 레이아웃을 수정
        myList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                // 화면 가로폭을 가득 채우도록 렌더러 수정
                renderer.setPreferredSize(new Dimension(400, 40)); // 이 예시에서는 화면 가로폭을 300으로 설정했습니다.
                return renderer;
            }
        });
        //입력받은 노트이름 만큼 출력
        for (String note : Note_name) {
            listModel.addElement(note);
        }
        Note_jp.add(myList);
        //예를 들어 10개단위로 페이지 넘길거였을떄 구현 / 스크롤로 구현할수도있음
        




        //노트 클릭시... 단어장 오픈됨


        //3.홈화면, 게임을 클릭하면 다른 화면으로(하단)

        Menu_jp=new JPanel(new FlowLayout());  //메뉴 패널 배치

        //메뉴 아이콘 가져오기(24x24)
        ImageIcon img_home=new ImageIcon("./btn_img/btn_home.png");
        ImageIcon img_game=new ImageIcon("./btn_img/btn_game.png");
        //메뉴 구성-홈(누르면 홈화면), 게임(단어퀴즈, 미니게임)
        JButton btn_home=new JButton(img_home);
        JButton btn_game=new JButton(img_game);
        //버튼 사이즈 조정
        btn_home.setPreferredSize(new Dimension(30, 30));
        btn_game.setPreferredSize(new Dimension(30, 30));
        //버튼 클릭시 이벤트 발생...

        //버튼을 메뉴 패널에 추가
        Menu_jp.add(btn_home);
        Menu_jp.add(btn_game);






        //홈 화면 구성
        ct.add(Home_jp,NORTH);
        ct.add(Note_jp,CENTER);
        ct.add(Menu_jp, SOUTH);

        //홈 환경 설정
        setTitle("나만의 단어장");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 700);
        setVisible(true);

    }



}
