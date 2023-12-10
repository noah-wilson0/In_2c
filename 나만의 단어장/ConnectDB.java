/*
 * 설명 : 데이터베이스에 접근하는 클래스
 * 
 * 각 클래스에서 데이터베이스 접근이 필요할 때, 이 클래스로부터 객체를 생성하여 필요한 메소드를 호출함.
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectDB {
    // 클래스 객체 변수 선언
    Connection conn;	// DB 연결에 사용되는 객체 변수
    Statement stmt;		// SQL 명령문 실행에 사용되는 객체 변수
    ResultSet result;	// SQL 명령문 실행결과를 받는 객체 변수

    // 접속할 데이터베이스의 URL, 아이디, 패스워드 설정
    String url = null;			// "jdbc:mysql://localhost:3306/[데이터베이스 이름]?serverTimezone=UTC"
    String mysql_id = "root";	// MYSQL root 아이디
    String mysql_pw = "1234";	// MYSQL 설정 시 입력한 패스워드

    // 입력된 아이디가 DB에 등록되어 있는지 검사하는 메소드
    public boolean checkID(String id) {
    	// 리턴할 변수 생성
    	boolean check = false;
    	
    	// 데이터베이스 접근
        try {
            // DB 연결
            url = "jdbc:mysql://localhost:3306/member?serverTimezone=UTC";	// 'member' DB로 접속하는 URL
            Class.forName("com.mysql.cj.jdbc.Driver");						// JDBC드라이버 로드
            conn = DriverManager.getConnection(url, mysql_id, mysql_pw);	// 데이터베이스와 연결
            System.out.println("DB 연결 완료: checkID");						// DB 연결 성공 여부를 콘솔 창에 출력

            // SQL 명령문을 가지는 변수 생성
            String sql = "select exists(select * from user_list where id = '" + id + "')";

            // Statement 객체 생성
            stmt = conn.createStatement();

            // SQL 명령문 실행 및 결과 값 처리
            result = stmt.executeQuery(sql);	// 입력된 ID가 'user_list' Table에 있는지 확인(있으면 1, 없으면 0 반환)
            result.next();
            int check_code = result.getInt(1);
            if (check_code == 1) {				// 입력된 ID가 Table에 있으면..
            	check = true;						// check를 true로 지정
            }

            // 연결 해제(사용된 자원을 반환)
            result.close();
            stmt.close();
            conn.close();
        }
        catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버 로드 에러");
        }
        catch (SQLException e) {
            System.out.println("DB 연결 오류: checkID");
        }
        
        // 결과 값 리턴
        return check;
    }

    // 입력된 패스워드가 해당 ID의 password와 일치하는지 검사하는 메소드
    public boolean checkPW(String id, String pw) {
    	// 리턴할 변수 생성
    	boolean check = false;
    	
    	// 데이터베이스 접근
        try {
            // DB 연결
            url = "jdbc:mysql://localhost:3306/member?serverTimezone=UTC";	// 'member' DB로 접속하는 URL
            Class.forName("com.mysql.cj.jdbc.Driver");						// JDBC드라이버 로드
            conn = DriverManager.getConnection(url, mysql_id, mysql_pw);	// 데이터베이스와 연결
            System.out.println("DB 연결 완료: checkPW");						// DB 연결 성공 여부를 콘솔 창에 출력

            // SQL 명령문을 가지는 변수 생성
            String sql = "select password from user_list where id = '" + id + "'";

            // Statement 객체 생성
            stmt = conn.createStatement();

            // SQL 명령문 실행 및 결과 값 처리
            result = stmt.executeQuery(sql);		// 해당 ID의 password를 조회
            result.next();
            String password = result.getString(1);
            if (pw.equals(password)) {				// 입력된 패스워드가 해당 ID의 password와 일치하면..
                check = true;							// check를 true로 지정
            }
            
            // 연결 해제(사용된 자원을 반환)
            result.close();
            stmt.close();
            conn.close();
        }
        catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버 로드 에러");
        }
        catch (SQLException e) {
            System.out.println("DB 연결 오류: checkPW");
        }
        
        // 결과 값 리턴
        return check;
    }

    // 새로운 회원을 추가하는 메소드
    public void addMember(String id, String pw, String name, String gender, String email) {
    	// 데이터베이스 접근
        try {
            // DB 연결
            url = "jdbc:mysql://localhost:3306/member?serverTimezone=UTC";	// 'member' DB로 접속하는 URL
            Class.forName("com.mysql.cj.jdbc.Driver");						// JDBC드라이버 로드
            conn = DriverManager.getConnection(url, mysql_id, mysql_pw);	// 데이터베이스와 연결
            System.out.println("DB 연결 완료: addMember");						// DB 연결 성공 여부를 콘솔 창에 출력

            // SQL 명령문을 가지는 변수 생성
            String sql1 = "insert into user_list values('" + id + "', '" + pw + "', '" + name + "', '"
                    	  + gender + "', '" + email + "')";
            String sql2 = "create database " + id + "_db";
            String sql3 = "use " + id + "_db";
            String sql4 = "create table note_list ("
                    	  + "id int NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                    	  + "name varchar(30) NOT NULL)";

            // Statement 객체 생성
            stmt = conn.createStatement();

            // SQL 명령문 실행
            stmt.executeUpdate(sql1);	// 'user_list' Table에 새로운 회원정보 삽입
            stmt.executeUpdate(sql2);	// 회원가입 시에 입력한 아이디로 회원 개인 DB 생성
            stmt.executeUpdate(sql3);	// 회원 개인 DB로 이동
            stmt.executeUpdate(sql4);	// 회원 개인 DB에 'note_list' Table 생성
            System.out.println("유저 '" + id + "': 회원가입 완료");

            // 연결 해제(사용된 자원을 반환)
            stmt.close();
            conn.close();
        }
        catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버 로드 에러");
        }
        catch (SQLException e) {
            System.out.println("DB 연결 오류: addMember");
        }
    }
    
    // 회원정보를 조회하는 메소드
    public String lookProfile(String id) {
    	// 리턴할 변수 생성
    	String profile = null;
    	
    	// 데이터베이스 접근
        try {
            // DB 연결
            url = "jdbc:mysql://localhost:3306/member?serverTimezone=UTC";	// 'member' DB로 접속하는 URL
            Class.forName("com.mysql.cj.jdbc.Driver");						// JDBC드라이버 로드
            conn = DriverManager.getConnection(url, mysql_id, mysql_pw);	// 데이터베이스와 연결
            System.out.println("DB 연결 완료: lookProfile");					// DB 연결 성공 여부를 콘솔 창에 출력

            // SQL 명령문을 가지는 변수 생성
            String sql = "select * from user_list where id = '" + id + "'";

            // Statement 객체 생성
            stmt = conn.createStatement();

            // SQL 명령문 실행 및 결과 값 처리
            result = stmt.executeQuery(sql);						// 로그인한 ID에 해당하는 회원정보를 조회
            result.next();
            profile = result.getString("id");						// 각 컬럼들을 '/'토큰으로 구분하며 profile에 저장
            profile = profile + "/" + result.getString("name");
            profile = profile + "/" + result.getString("gender");
            profile = profile + "/" + result.getString("email");

            // 연결 해제(사용된 자원을 반환)
            result.close();
            stmt.close();
            conn.close();
        }
        catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버 로드 에러");
        }
        catch (SQLException e) {
            System.out.println("DB 연결 오류: lookProfile");
        }
        
        // 결과 값 리턴
        return profile;
    }
    
    // 사용자의 이름을 조회하는 메소드
    public String selectName(String id) {
    	// 리턴할 변수 생성
    	String name = null;
    	
    	// 데이터베이스 접근
        try {
        	// DB 연결
            url = "jdbc:mysql://localhost:3306/member?serverTimezone=UTC";	// 'member' DB로 접속하는 URL
            Class.forName("com.mysql.cj.jdbc.Driver");						// JDBC드라이버 로드
            conn = DriverManager.getConnection(url, mysql_id, mysql_pw);	// 데이터베이스와 연결
            System.out.println("DB 연결 완료: selectName");					// DB 연결 성공 여부를 콘솔 창에 출력
            
            // SQL 명령문을 가지는 변수 생성
            String sql = "SELECT name FROM user_list WHERE id = '" + id + "'";

            // Statement 객체 생성
            stmt = conn.createStatement();

            // SQL 명령문 실행 및 결과 값 처리
            result = stmt.executeQuery(sql);	// 'user_list' Table에서, 로그인한 ID에 해당하는 'name' 열의 값을 얻음
            result.next();
            name = result.getString(1);

            // 연결 해제
            result.close();
            stmt.close();
            conn.close();
        }
        catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버 로드 에러");
        }
        catch (SQLException e) {
            System.out.println("DB 연결 오류: selectName");
        }
        
        // 결과 값 리턴
        return name;
    }

    // 회원정보를 수정하는 메소드
    public void editProfile(String id, String name, String gender, String email) {
    	// 데이터베이스 접근
        try {
            // DB 연결
            url = "jdbc:mysql://localhost:3306/member?serverTimezone=UTC";	// 'member' DB로 접속하는 URL
            Class.forName("com.mysql.cj.jdbc.Driver");						// JDBC드라이버 로드
            conn = DriverManager.getConnection(url, mysql_id, mysql_pw);	// 데이터베이스와 연결
            System.out.println("DB 연결 완료: editProfile");					// DB 연결 성공 여부를 콘솔 창에 출력

            // SQL 명령문을 가지는 변수 생성
            String sql = "update user_list set name = '" + name + "', gender = '" + gender
            			 + "', email = '" + email + "' where id = '" + id + "'";

            // Statement 객체 생성
            stmt = conn.createStatement();
            
            // SQL 명령문 실행
            stmt.executeUpdate(sql);	// 로그인한 ID에 해당하는 회원정보를 수정

            // 연결 해제(사용된 자원을 반환)
            stmt.close();
            conn.close();
        }
        catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버 로드 에러");
        }
        catch (SQLException e) {
            System.out.println("DB 연결 오류: editProfile");
        }
    }
    
    // 비밀번호를 변경하는 메소드
    public void editPassword(String id, String pw) {
    	// 데이터베이스 접근
        try {
            // DB 연결
            url = "jdbc:mysql://localhost:3306/member?serverTimezone=UTC";	// 'member' DB로 접속하는 URL
            Class.forName("com.mysql.cj.jdbc.Driver");						// JDBC드라이버 로드
            conn = DriverManager.getConnection(url, mysql_id, mysql_pw);	// 데이터베이스와 연결
            System.out.println("DB 연결 완료: editPassword");					// DB 연결 성공 여부를 콘솔 창에 출력

            // SQL 명령문을 가지는 변수 생성
            String sql = "update user_list set password = '" + pw + "' where id = '" + id + "'";

            // Statement 객체 생성
            stmt = conn.createStatement();

            // SQL 명령문 실행
            stmt.executeUpdate(sql);	// 로그인한 ID에 해당하는 비밀번호를 변경

            // 연결 해제(사용된 자원을 반환)
            stmt.close();
            conn.close();
        }
        catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버 로드 에러");
        }
        catch (SQLException e) {
            System.out.println("DB 연결 오류: editPassword");
        }
    }

    // 회원을 삭제하는 메소드
    public void deleteMember(String id) {
    	// 데이터베이스 접근
        try {
            // DB 연결
            url = "jdbc:mysql://localhost:3306/member?serverTimezone=UTC";	// 'member' DB로 접속하는 URL
            Class.forName("com.mysql.cj.jdbc.Driver");						// JDBC드라이버 로드
            conn = DriverManager.getConnection(url, mysql_id, mysql_pw);	// 데이터베이스와 연결
            System.out.println("DB 연결 완료: deleteMember");					// DB 연결 성공 여부를 콘솔 창에 출력

            // SQL 명령문을 가지는 변수 생성
            String sql1 = "delete from user_list where id = '" + id + "'";
            String sql2 = "drop database " + id + "_db";

            // Statement 객체 생성
            stmt = conn.createStatement();

            // SQL 명령문 실행
            stmt.executeUpdate(sql1);	// 로그인한 ID에 해당하는 회원을 'user_list' Table에서 삭제
            stmt.executeUpdate(sql2);	// 로그인한 ID에 해당하는 회원 개인 DB를 삭제
            System.out.println("유저 '" + id + "': 회원탈퇴 완료");
            
            // 연결 해제(사용된 자원을 반환)
            stmt.close();
            conn.close();
        }
        catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버 로드 에러");
        }
        catch (SQLException e) {
            System.out.println("DB 연결 오류: deleteMember");
        }
    }

    // 새로운 노트를 추가하는 메소드
    public void addNote(String id, String note_name) {
    	// 데이터베이스 접근
        try {
            // DB 연결
            url = "jdbc:mysql://localhost:3306/" + id + "_db?serverTimezone=UTC";	// 회원 개인 DB로 접속하는 URL
            Class.forName("com.mysql.cj.jdbc.Driver");								// JDBC드라이버 로드
            conn = DriverManager.getConnection(url, mysql_id, mysql_pw);			// 데이터베이스와 연결
            System.out.println("DB 연결 완료: addNote");								// DB 연결 성공 여부를 콘솔 창에 출력
            
            // SQL 명령문을 가지는 변수 생성
            String sql1 = "CREATE TABLE `" + note_name + "` ("
            			 + "id INT AUTO_INCREMENT PRIMARY KEY," // 연번
            			 + "word VARCHAR(255)," 				// 단어
            			 + "mean VARCHAR(255)," 				// 뜻
            			 + "pos VARCHAR(50)" 					// 품사
            			 + ")";
            String sql2 = "insert into note_list (name) values ('" + note_name + "')";
            
            // Statement 객체 생성
            stmt = conn.createStatement();
            
            // SQL 명령문 실행
            stmt.executeUpdate(sql1);	// 새 노트의 이름으로 새로운 Table 생성
            System.out.println("노트 '" + note_name + "': Table 생성 완료");
            stmt.executeUpdate(sql2);	// 새 노트의 이름을 'note_list' Table에 삽입
            System.out.println("Table 'note_list': '" + note_name + "' 삽입 완료");

            // 연결 해제(사용된 자원을 반환)
            stmt.close();
            conn.close();
        }
        catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버 로드 에러");
        }
        catch (SQLException e) {
            System.out.println("DB 연결 오류: addNote");
        }
    }
    
    // 노트 목록을 조회하는 메소드
    public String selectNoteList(String id) {
    	// 데이터베이스 접근
        try {
            // DB 연결
            url = "jdbc:mysql://localhost:3306/" + id + "_db?serverTimezone=UTC";	// 회원 개인 DB로 접속하는 URL
            Class.forName("com.mysql.cj.jdbc.Driver");								// JDBC드라이버 로드
            conn = DriverManager.getConnection(url, mysql_id, mysql_pw);			// 데이터베이스와 연결
            System.out.println("DB 연결 완료: selectNoteList");						// DB 연결 성공 여부를 콘솔 창에 출력

            // SQL 명령문을 가지는 변수 생성
            String sql = "select name from note_list";

            // Statement 객체 생성
            stmt = conn.createStatement();
            
            // SQL 명령문 실행 및 결과 값 처리
            result = stmt.executeQuery(sql);				// 로그인한 회원이 추가한 모든 노트의 이름을 조회
            String note = null;								// 모든 노트의 이름을 저장할 변수 생성
            if (result.next()) {								// DB에 노트가 있다면..
                note = result.getString("name");					// note에 노트 이름을 추가
                while (result.next()) {								// 결과의 두 번째 행부터 마지막 행까지..
                    note = note + "|" + result.getString("name");		// 각 노트의 이름을 '|'로 구분하여 note에 추가
                }
            }

            // 연결 해제(사용된 자원을 반환)
            result.close();
            stmt.close();
            conn.close();
            
            // 모든 노트의 이름을 가지는 문자열 변수 note를 반환
            return note;  //t|tt
        }
        catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버 로드 에러");
            return null;
        }
        catch (SQLException e) {
            System.out.println("DB 연결 오류: selectNoteList");
            return null;
        }
    }
    
    // 노트의 행 개수를 조회하는 메소드
    public int getNoteSize(String id, String noteName) {
    	// 리턴할 변수 생성
        int rowCount = 0;
        
        // 데이터베이스 접근
        try {
            // DB 연결
            url = "jdbc:mysql://localhost:3306/" + id + "_db?serverTimezone=UTC";	// 회원 개인 DB로 접속하는 URL
            Class.forName("com.mysql.cj.jdbc.Driver");								// JDBC드라이버 로드
            conn = DriverManager.getConnection(url, mysql_id, mysql_pw);			// 데이터베이스와 연결
            System.out.println("DB 연결 완료: getNoteSize");							// DB 연결 성공 여부를 콘솔 창에 출력
            
            // SQL 명령문을 가지는 변수 생성
            String Query="select count(*) from `" + noteName+"`";
            
            // Statement 객체 생성
            stmt = conn.createStatement();
            
            // SQL 명령문 실행 및 결과 값 처리
            result = stmt.executeQuery(Query);	// 테이블 행의 갯수 반환
            if (result.next()) {
                rowCount = result.getInt(1);
                System.out.println(noteName + "의 행의 갯수: " + rowCount);
            }

            // 연결 해제(사용된 자원을 반환)
            result.close();
            stmt.close();
            conn.close();
        }
        catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버 로드 에러");
        }
        catch (SQLException e) {
            System.out.println("DB 연결 오류: getNoteSize");
        }
        
        // 결과 값 리턴
        return rowCount;
    }
    
    // 노트를 삭제하는 메소드
    public void deleteNote(String id, String note_Name) {
    	// 데이터베이스 접근
        try {
            // DB 연결
            url = "jdbc:mysql://localhost:3306/" + id + "_db?serverTimezone=UTC";	// 회원 개인 DB로 접속하는 URL
            Class.forName("com.mysql.cj.jdbc.Driver");								// JDBC드라이버 로드
            conn = DriverManager.getConnection(url, mysql_id, mysql_pw);			// 데이터베이스와 연결
            System.out.println("DB 연결 완료: deleteNote");							// DB 연결 성공 여부를 콘솔 창에 출력
            
            // SQL 명령문을 가지는 변수 생성
            String sql1 = "DROP TABLE `" + note_Name + "`";
            String sql2 = "delete from note_list where name = '" + note_Name + "';";
            
            // Statement 객체 생성
            stmt = conn.createStatement();
            
            // SQL 명령문 실행
            stmt.executeUpdate(sql1);	// 해당 노트 Table을 삭제
            System.out.println("Delete Table: "+note_Name);
            stmt.executeUpdate(sql2);	// 'note_list' 테이블에서 해당 노트 이름을 삭제

            // 연결 해제(사용된 자원을 반환)
            stmt.close();
            conn.close();
        }
        catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버 로드 에러");
        }
        catch (SQLException e) {
            System.out.println("DB 연결 오류: deleteNote");
        }
    }
    
    // 새로운 영단어를 노트에 추가하는 메소드
    public void insertWord(String id, String note_name, String word, String mean, List<String> pos) {
    	// 데이터베이스 접근
    	try {
            // DB 연결
            url = "jdbc:mysql://localhost:3306/" + id + "_db?serverTimezone=UTC";	// 회원 개인 DB로 접속하는 URL
            Class.forName("com.mysql.cj.jdbc.Driver");								// JDBC드라이버 로드
            conn = DriverManager.getConnection(url, mysql_id, mysql_pw);			// 데이터베이스와 연결
            System.out.println("DB 연결 완료: insertWord");							// DB 연결 성공 여부를 콘솔 창에 출력
            
            // SQL 명령문을 가지는 변수 생성
            String sql = "INSERT INTO `" + note_name + "` (word, mean, pos)"
            			 + "VALUES ('" + word + "', '" + mean + "','" + pos + "')";
            
            // Statement 객체 생성
            stmt = conn.createStatement();
            
            // SQL 명령문 실행
            stmt.execute(sql);	// 해당 노트 Table에 새로운 단어, 뜻, 품사를 추가
            System.out.println("Table '" + note_name + "': 새 영단어 삽입 완료");
            
            // 연결 해제(사용된 자원을 반환)
            stmt.close();
            conn.close();
    	}
        catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버 로드 에러");
        }
        catch (SQLException e) {
            System.out.println("DB 연결 오류: insertWord");
        }
    }
    
    // 해당 노트에 추가된 모든 영단어를 조회하는 메소드
    public String[][] lookEnglish(String id, String note_name) {
    	// 데이터베이스 접근
        try {
            // DB 연결
            url = "jdbc:mysql://localhost:3306/" + id + "_db?serverTimezone=UTC";	// 회원 개인 DB로 접속하는 URL
            Class.forName("com.mysql.cj.jdbc.Driver");								// JDBC드라이버 로드
            conn = DriverManager.getConnection(url, mysql_id, mysql_pw);			// 데이터베이스와 연결
            System.out.println("DB 연결 완료: lookEnglish");							// DB 연결 성공 여부를 콘솔 창에 출력

            // SQL 명령문을 가지는 변수 생성
            String sql1 = "select count(*) from `" + note_name +"`";
            String sql2 = "select * from `" + note_name + "`";

            // Statement 객체 생성
            stmt = conn.createStatement();

            // SQL 명령문 실행 및 결과 값 처리
            result = stmt.executeQuery(sql1);	// 노트에 있는 영단어의 개수를 조회
            result.next();						// 커서를 다음 행으로 이동
            int count = result.getInt(1);		// 영단어의 개수를 count에 저장
            String[][] english;					// 모든 단어, 뜻, 품사를 저장할 2차원 배열 선언
            if (count > 0) {					// 노트에 영단어가 있다면..
                english = new String[count][3];		// count*3만큼의 크기를 가지는 2차원 배열 생성
                result = stmt.executeQuery(sql2);	// 노트에 있는 모든 단어, 뜻, 품사를 조회
                for (int i=0; i<count; i++) {
                    result.next();						// 커서를 다음 행으로 이동
                    for (int j=0; j<3; j++) {
                        english[i][j] = result.getString(j+2);	// 결과의 인덱스 번호를 이용하여 컬럼 값을 저장
                    }
                }
            }
            else {								// 노트에 단어가 없다면..
                english = null;						// 2차원 배열 english를 생성하지 않음
            }

            // 연결 해제(사용된 자원을 반환)
            result.close();
            stmt.close();
            conn.close();

            // 모든 단어, 뜻, 품사를 가지는 english를 반환
            return english;
        }
        catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버 로드 에러");
            return null;
        }
        catch (SQLException e) {
            System.out.println("DB 연결 오류: lookEnglish");
            return null;
        }
    }
    
    // 해당 노트에 추가된 영단어의 단어와 뜻을 조회하는 메소드
    public String[][] selectWordAndMeaning(String id, String noteName) {
        // 조회한 단어를 받기 위한 배열 선언
        String word_list[][];
        
        // 데이터베이스 접근
        try {
            // DB 연결
            url = "jdbc:mysql://localhost:3306/" + id + "_db?serverTimezone=UTC";	// 회원 개인 DB로 접속하는 URL
            Class.forName("com.mysql.cj.jdbc.Driver");								// JDBC드라이버 로드
            conn = DriverManager.getConnection(url, mysql_id, mysql_pw);			// 데이터베이스와 연결
            System.out.println("DB 연결 완료: selectWordAndMeaning");					// DB 연결 성공 여부를 콘솔 창에 출력
            
            // SQL 명령문을 가지는 변수 생성
            String sql1 = "select count(*) from `" + noteName +"`";
            String sql2 = "select word, mean from `" + noteName + "`";
            
            // Statement 객체 생성
            stmt = conn.createStatement();
            
            // SQL 명령문 실행 및 결과 값 처리
            result = stmt.executeQuery(sql1);	// 노트에 있는 영단어의 개수를 조회
            result.next();						// 커서를 다음 행으로 이동
            int count = result.getInt(1);		// 영단어의 개수를 count에 저장

            if(count>0) { //단어가 있으면...
                word_list = new String[count][2];	//영단어 개수만큼 배열 생성
                
                result = stmt.executeQuery(sql2);	// 실행결과를 반환 받음
                
                for (int i=0; i<count; i++) {
                	result.next();						// 커서를 다음 행으로 이동
                    for (int j=0; j<2; j++) {
                        word_list[i][j] = result.getString(j+1);	// 결과의 인덱스 번호를 이용하여 컬럼 값을 저장
                    }
                }
            }
            else{ //단어가 없으면...
                word_list=null;
            }
            
            // 연결 해제(사용된 자원을 반환)
            result.close();
            stmt.close();
            conn.close();
            
            // 단어와 뜻을 저장한 2차원 배열 리턴
            return word_list;
        }
        catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버 로드 에러");
            return null;
        }
        catch (SQLException e) {
            System.out.println("DB 연결 오류: selectWordAndMeaning");
            return null;
        }
    }
    
    // 단어의 위치를 검색하는 메소드
    public String[] searchWord(String id, String search_word) {
    	// 데이터베이스 접근
        try {
            // DB 연결
            url = "jdbc:mysql://localhost:3306/" + id + "_db?serverTimezone=UTC";	// 회원 개인 DB로 접속하는 URL
            Class.forName("com.mysql.cj.jdbc.Driver");								// JDBC드라이버 로드
            conn = DriverManager.getConnection(url, mysql_id, mysql_pw);			// 데이터베이스와 연결
            System.out.println("DB 연결 완료: searchWord");							// DB 연결 성공 여부를 콘솔 창에 출력

            // SQL 명령문을 가지는 변수 생성
            String sql = "select name from note_list";

            // Statement 객체 생성
            stmt = conn.createStatement();

            // SQL 명령문 실행 및 결과 값 처리
            result = stmt.executeQuery(sql);	// 로그인한 회원이 추가한 모든 노트의 이름을 조회
            
            String[] word = new String[3];    //[단어,뜻,찾은 노트 이름]를 반환받을 배열
            ArrayList<String> noteNameList = new ArrayList<String>();	// 모든 노트의 이름을 배열 리스트에 저장
            ArrayList<String> searchedNoteList = new ArrayList<String>();	// 찾은 노트를 배열 리스트에 저장
            
            while (result.next()) {	// DB에 노트가 있다면, 다음 행이 있을 때까지 반복
                noteNameList.add(result.getString("name"));  // 모든 노트를 탐색하기 위해 note이름을 리스트에 저장함
            }
            
            for (int i = 0; i < noteNameList.size(); i++) {		// 단어, 뜻 조회 시작
                result = stmt.executeQuery("SELECT word, mean FROM `" + noteNameList.get(i) 
                						   + "` where word = '" + search_word + "'");	//각 노트별로 모든 단어 조회
                while (result.next()) {
                    word[0] = result.getString("word");	// 현재 행의 word 값 읽어오기
                    word[1] = result.getString("mean");	// 현재 행의 mean 값 읽어오기
                    if(word[0].equals(search_word)) {
                    	searchedNoteList.add(noteNameList.get(i)); //찾은 단어의 노트를 저장
                        System.out.println(noteNameList.get(i) + "에서찾은 단어:" + word[0]); //찾은 단어를 콘솔 창에 출력
                        break;
                    }
                    else {            // 못찾으면 null을 가진 배열을 word[null,null]반환함
                        System.out.println(noteNameList.get(i) +"못찾은 단어:"+word[0]);  //못찾으면 null반환됨
                    }
                }
            }
            
            word[2] = String.join(", ", searchedNoteList);	// 찾은 노트 목록을 저장
            
            // 연결 해제(사용된 자원을 반환)
            result.close();
            stmt.close();
            conn.close();
            
            // word배열 반환
            return word;
        }
        catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버 로드 에러");
            return null;
        }
        catch (SQLException e) {
            System.out.println("DB 연결 오류: searchWord");
            return null;
        }
    }
    
    // 선택된 영단어들을 노트에서 삭제하는 메소드
    public void deleteWords(String id, String noteName, String word[]) {
    	// 데이터베이스 접근
        try {
            // DB 연결
            url = "jdbc:mysql://localhost:3306/" + id + "_db?serverTimezone=UTC";	// 회원 개인 DB로 접속하는 URL
            Class.forName("com.mysql.cj.jdbc.Driver");								// JDBC드라이버 로드
            conn = DriverManager.getConnection(url, mysql_id, mysql_pw);			// 데이터베이스와 연결
            System.out.println("DB 연결 완료: deleteWords");							// DB 연결 성공 여부를 콘솔 창에 출력

            // Statement 객체 생성
            stmt = conn.createStatement();

            // SQL 명령문 실행
            for (int i = 0; i < word.length; i++) {
                stmt.execute("DELETE FROM `" + noteName 
                			 + "` WHERE word = '" + word[i] + "'");		// 선택된 모든 영단어들을 해당 노트 Table에서 삭제 
            }

            // 연결 해제(사용된 자원을 반환)
            stmt.close();
            conn.close();
            System.out.println("단어 삭제 완료");
        }
        catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버 로드 에러");
        }
        catch (SQLException e) {
            System.out.println("DB 연결 오류: deleteWords");
        }
    }
}