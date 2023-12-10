/*
 * 설명 : 영단어를 삭제하는 GUI 클래스
 * 
 * 컨테이너 형태로 작성됨.
 * 이벤트는 GUI 클래스 내에서 처리됨.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class DeleteEnglish extends Container implements ActionListener {
    String noteName="";
    ConnectDB cb=new ConnectDB();
    JTextArea jta;

    // 컨테이너 화면을 구성하는 생성자
    public DeleteEnglish(String noteName, ArrayList<String> del_words){
        this.noteName=noteName;
        setLayout(new FlowLayout(FlowLayout.CENTER));

        //제목 패널
        JPanel titleJp=new JPanel();
        titleJp.setPreferredSize(new Dimension(320, 100));

        //제목
        JLabel title=new JLabel(noteName+": 단어 삭제하기");
        title.setFont(new Font("돋음", Font.BOLD, 20));  //제목 폰트 설정
        titleJp.add(title);
        add(titleJp);

        // 체크한 단어들을  JTextArea 에 추가하기
        jta=new JTextArea(10,20);

        jta.setEditable(false); // 읽기 전용으로 설정

        for (String word : del_words) {		// word에 del_words의 요소를 차례대로 저장
            jta.append(word+"\n");				// word를 텍스트 에어리어에 출력
        }
        
        //체크한 단어들을 스크롤팬에 출력
        JScrollPane delEnglishScrollPane = new JScrollPane(jta);
        add(delEnglishScrollPane);

        //버튼 패널
        JPanel btnJp=new JPanel();
        btnJp.setPreferredSize(new Dimension(320,100));

        //삭제하기
        JButton btn_del=new JButton("삭제");
        btn_del.addActionListener(this);
        btnJp.add(btn_del);

        //노트화면으로 돌아가기
        JButton btn_cancel=new JButton("취소");
        btn_cancel.addActionListener(this);
        btnJp.add(btn_cancel);

        add(btnJp);
    }

    // 이벤트를 처리하는 메소드
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame mf = MainFrame.getInstance();     // 메인 프레임 클래스로부터 객체 생성 (생성자 호출 X - 싱글톤 패턴)
        if(e.getActionCommand().equals("삭제")){
            //삭제할 단어를 가져오기
            String selected_Word=jta.getText();
            String[] del_wordList=selected_Word.split("\n");
            cb.deleteWords(GetID.id, noteName, del_wordList);

            mf.ChangeGUI("노트", noteName); 	// 컨테이너를 전환하는 메소드에 클릭한 버튼의 이름을 매개변수로 전달
        }
        else if (e.getActionCommand().equals("취소")) {
            mf.ChangeGUI("노트", noteName); 	// 컨테이너를 전환하는 메소드에 클릭한 버튼의 이름을 매개변수로 전달
        }
    }
}