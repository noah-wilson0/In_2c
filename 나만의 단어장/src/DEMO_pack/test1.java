//import java.sql.*;
//import java.util.ArrayList;
//
//public class test1 {
//    Connection conn;	// DB 연결에 사용되는 객체 변수
//    Statement stmt;		// sql 명령어 실행에 사용되는 객체 변수
//    ResultSet result;	// sql 명령의 실행결과를 받는 객체 변수
//
//    // 접속할 데이터베이스의 URL, 아이디, 패스워드 설정
//    String url;					// "jdbc:mysql://localhost:3306/[데이터베이스 이름]?serverTimezone=UTC"
//    String mysql_id = "root";	// DB root 아이디
//    String mysql_pw = "495108";	// MYSQL 설정 시 입력한 패스워드
//    public test1(String id,String searchWord){
//        try {
//            // DB 연결
//            url = "jdbc:mysql://localhost:3306/" + id + "_db?serverTimezone=UTC";	// 회원 개인 DB로 접속하는 URL
//            Class.forName("com.mysql.cj.jdbc.Driver");								// JDBC드라이버 로드
//            conn = DriverManager.getConnection(url, mysql_id, mysql_pw);			// 데이터베이스와 연결
//            System.out.println("DB 연결 완료:select_words");										// DB 연결 성공 여부를 콘솔 창에 출력
//            String[] word = new String[2];    //[단어,뜻]를 반환받을 배열
//
//            // sql 명령어를 가지는 문자열 변수 생성
//            String sql = "select name from note_list";
//
//            // stmt 객체 변수 활성화
//            stmt = conn.createStatement();
//
//            // 로그인한 회원이 추가한 모든 노트의 이름을 조회
//            result = stmt.executeQuery(sql);
//
//            // 모든 노트의 이름을 배열에 저장
//            ArrayList<String> noteNameList=new ArrayList<String>();
//                // DB에 노트가 있다면..
//                while (result.next()) {								// 결과의 두 번째 행부터 마지막 행까지..
////                    System.out.println("wile노트:"+result.getString("name"));
//                    noteNameList.add(result.getString("name"));  //성공
////                    System.out.println("리스트:"+noteNameList);  //[test1, test2, test3]
//                }
//            System.out.println(searchWord);  //hand 출력됨 - 성공
//
//            //단어,뜻 조회 시작
//            for (int i = 0; i < noteNameList.size(); i++) {
//                //sql명령어
//                String sql2 = "SELECT word,mean FROM " + noteNameList.get(i) + " where word = '" + searchWord+"'";
//                result = stmt.executeQuery(sql2); //각 노트별로 모든 단어 조회
//                while (result.next()) {
////                System.out.println(result.getString("word출력"+word[0]));
//                    // 현재 행의 word와 mean 값을 읽어오기
//                    word[0] = result.getString("word");
//                    word[1] = result.getString("mean");
//                    if(word[0].equals(searchWord)) {
//                        System.out.println("찾은 단어:" + word[0]); //있는 단어 검색시 :  찾은 단어:hand
//                    }
//                }
//            }
//            //보류  일단 못찾으면 null을 가진 배열을 word[null,null]반환함
//            //단어를 못찾으면 word[0]과 word[1]이 null이기 때문에 방지함
////            if(word[0].equals(null) || word[1].equals(null)){  //찾은 단어가 업다면?
////
////            }
//            System.out.println("못찾음:"+word[0]);  //못찾으면 null반환됨
//            // 연결 해제(사용된 자원을 반환)
//            result.close();
//            stmt.close();
//            conn.close();
//            // word,mean둘다 리턴해야되는데
//
//        }
//        catch (ClassNotFoundException e) {
//            System.out.println("JDBC 드라이버 로드 에러");
//            e.printStackTrace();
//
//        }
//        catch (SQLException e) {
//            System.out.println("DB 연결 오류: select_words");
//            e.printStackTrace();
//        }
//    }
//
//}
