//import java.sql.*;
//
///*
//    새 db를 생성해야되는 상황
//    0.초기 셋팅 - 회원 계정을 담을 데이터 베이스 생성
//    1. 회원 계정 생성시 - 초기에 생성된 회원 데이터 베이스의 테이블에 ID,PS추가
//    2. 새 노트를 만들떄 - 단어 db 생성(내부 테이블까지)
//    //db를 읽어야 할떄
//    1. 로그인시 회원이 맞는지 검증할떄
//    2. 단어장을 출력 시킬떄
//
//    stmt.execute() : 테이블 생성, 수정, 삭제 등 DB 관리 명령어에 주로 사용.
//    stmt.executeUpdate() : 레코드 삽입, 수정, 삭제 등 데이터 관리 명령어에 주로 사용하며, SQL문이 실행된 후 그에 영향을 받은 레코드의 갯수를 int형으로 반환하는 메소드.
//    stmt.executeQuery() : Select한 결과 값들을 ResultSet 객체에 담아 반환하며, 레코드나 테이블 조회 류의 명령에 사용.
//
// */
//public class SQL_DEMO {
//    //DB로그인을 위한 정보
//    Connection conn;
////    String url = "jdbc:mysql://localhost:3306/userdatabase"; //먼저 db를 만들고 접속할떄
//    String url = "jdbc:mysql://localhost:3306/member"; //sql연결할떄 db없이
//    String user = "root"; //  접속자 id
//    String password = "495108"; // 접속자 pw
//
//    public SQL_DEMO() {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");  //jdc 객체화
//            conn = DriverManager.getConnection(url, user, password); // db와 연결하기
////            Statement stmt = conn.createStatement();  //sql 실행 도구 생성
//        } catch (ClassNotFoundException e) {
//            System.out.println("드라이버 연결 오류");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    //https://www.tutorialspoint.com/jdbc/jdbc-create-database.htm 참고
//    //String으로 sql명령문을 받으면 실행을 해주는 메소드
//    public void executeQuery(String query) {
//        try {
//            Statement stmt = conn.createStatement();
//            stmt.executeQuery(query);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    //INSERT, UPDATE, DELETE, CREATE TABLE
//    public void execute(String query) {
//        try {
//            Statement stmt = conn.createStatement();
////            stmt.executeUpdate(query);
//            stmt.execute(query);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    public void executeUpdate(String query) {
//        try {
//            Statement stmt = conn.createStatement();
//            stmt.executeUpdate(query);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void close() {
//        try {
//            conn.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
