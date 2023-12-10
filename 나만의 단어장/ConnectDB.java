/*
 * ���� : �����ͺ��̽��� �����ϴ� Ŭ����
 * 
 * �� Ŭ�������� �����ͺ��̽� ������ �ʿ��� ��, �� Ŭ�����κ��� ��ü�� �����Ͽ� �ʿ��� �޼ҵ带 ȣ����.
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectDB {
    // Ŭ���� ��ü ���� ����
    Connection conn;	// DB ���ῡ ���Ǵ� ��ü ����
    Statement stmt;		// SQL ��ɹ� ���࿡ ���Ǵ� ��ü ����
    ResultSet result;	// SQL ��ɹ� �������� �޴� ��ü ����

    // ������ �����ͺ��̽��� URL, ���̵�, �н����� ����
    String url = null;			// "jdbc:mysql://localhost:3306/[�����ͺ��̽� �̸�]?serverTimezone=UTC"
    String mysql_id = "root";	// MYSQL root ���̵�
    String mysql_pw = "1234";	// MYSQL ���� �� �Է��� �н�����

    // �Էµ� ���̵� DB�� ��ϵǾ� �ִ��� �˻��ϴ� �޼ҵ�
    public boolean checkID(String id) {
    	// ������ ���� ����
    	boolean check = false;
    	
    	// �����ͺ��̽� ����
        try {
            // DB ����
            url = "jdbc:mysql://localhost:3306/member?serverTimezone=UTC";	// 'member' DB�� �����ϴ� URL
            Class.forName("com.mysql.cj.jdbc.Driver");						// JDBC����̹� �ε�
            conn = DriverManager.getConnection(url, mysql_id, mysql_pw);	// �����ͺ��̽��� ����
            System.out.println("DB ���� �Ϸ�: checkID");						// DB ���� ���� ���θ� �ܼ� â�� ���

            // SQL ��ɹ��� ������ ���� ����
            String sql = "select exists(select * from user_list where id = '" + id + "')";

            // Statement ��ü ����
            stmt = conn.createStatement();

            // SQL ��ɹ� ���� �� ��� �� ó��
            result = stmt.executeQuery(sql);	// �Էµ� ID�� 'user_list' Table�� �ִ��� Ȯ��(������ 1, ������ 0 ��ȯ)
            result.next();
            int check_code = result.getInt(1);
            if (check_code == 1) {				// �Էµ� ID�� Table�� ������..
            	check = true;						// check�� true�� ����
            }

            // ���� ����(���� �ڿ��� ��ȯ)
            result.close();
            stmt.close();
            conn.close();
        }
        catch (ClassNotFoundException e) {
            System.out.println("JDBC ����̹� �ε� ����");
        }
        catch (SQLException e) {
            System.out.println("DB ���� ����: checkID");
        }
        
        // ��� �� ����
        return check;
    }

    // �Էµ� �н����尡 �ش� ID�� password�� ��ġ�ϴ��� �˻��ϴ� �޼ҵ�
    public boolean checkPW(String id, String pw) {
    	// ������ ���� ����
    	boolean check = false;
    	
    	// �����ͺ��̽� ����
        try {
            // DB ����
            url = "jdbc:mysql://localhost:3306/member?serverTimezone=UTC";	// 'member' DB�� �����ϴ� URL
            Class.forName("com.mysql.cj.jdbc.Driver");						// JDBC����̹� �ε�
            conn = DriverManager.getConnection(url, mysql_id, mysql_pw);	// �����ͺ��̽��� ����
            System.out.println("DB ���� �Ϸ�: checkPW");						// DB ���� ���� ���θ� �ܼ� â�� ���

            // SQL ��ɹ��� ������ ���� ����
            String sql = "select password from user_list where id = '" + id + "'";

            // Statement ��ü ����
            stmt = conn.createStatement();

            // SQL ��ɹ� ���� �� ��� �� ó��
            result = stmt.executeQuery(sql);		// �ش� ID�� password�� ��ȸ
            result.next();
            String password = result.getString(1);
            if (pw.equals(password)) {				// �Էµ� �н����尡 �ش� ID�� password�� ��ġ�ϸ�..
                check = true;							// check�� true�� ����
            }
            
            // ���� ����(���� �ڿ��� ��ȯ)
            result.close();
            stmt.close();
            conn.close();
        }
        catch (ClassNotFoundException e) {
            System.out.println("JDBC ����̹� �ε� ����");
        }
        catch (SQLException e) {
            System.out.println("DB ���� ����: checkPW");
        }
        
        // ��� �� ����
        return check;
    }

    // ���ο� ȸ���� �߰��ϴ� �޼ҵ�
    public void addMember(String id, String pw, String name, String gender, String email) {
    	// �����ͺ��̽� ����
        try {
            // DB ����
            url = "jdbc:mysql://localhost:3306/member?serverTimezone=UTC";	// 'member' DB�� �����ϴ� URL
            Class.forName("com.mysql.cj.jdbc.Driver");						// JDBC����̹� �ε�
            conn = DriverManager.getConnection(url, mysql_id, mysql_pw);	// �����ͺ��̽��� ����
            System.out.println("DB ���� �Ϸ�: addMember");						// DB ���� ���� ���θ� �ܼ� â�� ���

            // SQL ��ɹ��� ������ ���� ����
            String sql1 = "insert into user_list values('" + id + "', '" + pw + "', '" + name + "', '"
                    	  + gender + "', '" + email + "')";
            String sql2 = "create database " + id + "_db";
            String sql3 = "use " + id + "_db";
            String sql4 = "create table note_list ("
                    	  + "id int NOT NULL AUTO_INCREMENT PRIMARY KEY,"
                    	  + "name varchar(30) NOT NULL)";

            // Statement ��ü ����
            stmt = conn.createStatement();

            // SQL ��ɹ� ����
            stmt.executeUpdate(sql1);	// 'user_list' Table�� ���ο� ȸ������ ����
            stmt.executeUpdate(sql2);	// ȸ������ �ÿ� �Է��� ���̵�� ȸ�� ���� DB ����
            stmt.executeUpdate(sql3);	// ȸ�� ���� DB�� �̵�
            stmt.executeUpdate(sql4);	// ȸ�� ���� DB�� 'note_list' Table ����
            System.out.println("���� '" + id + "': ȸ������ �Ϸ�");

            // ���� ����(���� �ڿ��� ��ȯ)
            stmt.close();
            conn.close();
        }
        catch (ClassNotFoundException e) {
            System.out.println("JDBC ����̹� �ε� ����");
        }
        catch (SQLException e) {
            System.out.println("DB ���� ����: addMember");
        }
    }
    
    // ȸ�������� ��ȸ�ϴ� �޼ҵ�
    public String lookProfile(String id) {
    	// ������ ���� ����
    	String profile = null;
    	
    	// �����ͺ��̽� ����
        try {
            // DB ����
            url = "jdbc:mysql://localhost:3306/member?serverTimezone=UTC";	// 'member' DB�� �����ϴ� URL
            Class.forName("com.mysql.cj.jdbc.Driver");						// JDBC����̹� �ε�
            conn = DriverManager.getConnection(url, mysql_id, mysql_pw);	// �����ͺ��̽��� ����
            System.out.println("DB ���� �Ϸ�: lookProfile");					// DB ���� ���� ���θ� �ܼ� â�� ���

            // SQL ��ɹ��� ������ ���� ����
            String sql = "select * from user_list where id = '" + id + "'";

            // Statement ��ü ����
            stmt = conn.createStatement();

            // SQL ��ɹ� ���� �� ��� �� ó��
            result = stmt.executeQuery(sql);						// �α����� ID�� �ش��ϴ� ȸ�������� ��ȸ
            result.next();
            profile = result.getString("id");						// �� �÷����� '/'��ū���� �����ϸ� profile�� ����
            profile = profile + "/" + result.getString("name");
            profile = profile + "/" + result.getString("gender");
            profile = profile + "/" + result.getString("email");

            // ���� ����(���� �ڿ��� ��ȯ)
            result.close();
            stmt.close();
            conn.close();
        }
        catch (ClassNotFoundException e) {
            System.out.println("JDBC ����̹� �ε� ����");
        }
        catch (SQLException e) {
            System.out.println("DB ���� ����: lookProfile");
        }
        
        // ��� �� ����
        return profile;
    }
    
    // ������� �̸��� ��ȸ�ϴ� �޼ҵ�
    public String selectName(String id) {
    	// ������ ���� ����
    	String name = null;
    	
    	// �����ͺ��̽� ����
        try {
        	// DB ����
            url = "jdbc:mysql://localhost:3306/member?serverTimezone=UTC";	// 'member' DB�� �����ϴ� URL
            Class.forName("com.mysql.cj.jdbc.Driver");						// JDBC����̹� �ε�
            conn = DriverManager.getConnection(url, mysql_id, mysql_pw);	// �����ͺ��̽��� ����
            System.out.println("DB ���� �Ϸ�: selectName");					// DB ���� ���� ���θ� �ܼ� â�� ���
            
            // SQL ��ɹ��� ������ ���� ����
            String sql = "SELECT name FROM user_list WHERE id = '" + id + "'";

            // Statement ��ü ����
            stmt = conn.createStatement();

            // SQL ��ɹ� ���� �� ��� �� ó��
            result = stmt.executeQuery(sql);	// 'user_list' Table����, �α����� ID�� �ش��ϴ� 'name' ���� ���� ����
            result.next();
            name = result.getString(1);

            // ���� ����
            result.close();
            stmt.close();
            conn.close();
        }
        catch (ClassNotFoundException e) {
            System.out.println("JDBC ����̹� �ε� ����");
        }
        catch (SQLException e) {
            System.out.println("DB ���� ����: selectName");
        }
        
        // ��� �� ����
        return name;
    }

    // ȸ�������� �����ϴ� �޼ҵ�
    public void editProfile(String id, String name, String gender, String email) {
    	// �����ͺ��̽� ����
        try {
            // DB ����
            url = "jdbc:mysql://localhost:3306/member?serverTimezone=UTC";	// 'member' DB�� �����ϴ� URL
            Class.forName("com.mysql.cj.jdbc.Driver");						// JDBC����̹� �ε�
            conn = DriverManager.getConnection(url, mysql_id, mysql_pw);	// �����ͺ��̽��� ����
            System.out.println("DB ���� �Ϸ�: editProfile");					// DB ���� ���� ���θ� �ܼ� â�� ���

            // SQL ��ɹ��� ������ ���� ����
            String sql = "update user_list set name = '" + name + "', gender = '" + gender
            			 + "', email = '" + email + "' where id = '" + id + "'";

            // Statement ��ü ����
            stmt = conn.createStatement();
            
            // SQL ��ɹ� ����
            stmt.executeUpdate(sql);	// �α����� ID�� �ش��ϴ� ȸ�������� ����

            // ���� ����(���� �ڿ��� ��ȯ)
            stmt.close();
            conn.close();
        }
        catch (ClassNotFoundException e) {
            System.out.println("JDBC ����̹� �ε� ����");
        }
        catch (SQLException e) {
            System.out.println("DB ���� ����: editProfile");
        }
    }
    
    // ��й�ȣ�� �����ϴ� �޼ҵ�
    public void editPassword(String id, String pw) {
    	// �����ͺ��̽� ����
        try {
            // DB ����
            url = "jdbc:mysql://localhost:3306/member?serverTimezone=UTC";	// 'member' DB�� �����ϴ� URL
            Class.forName("com.mysql.cj.jdbc.Driver");						// JDBC����̹� �ε�
            conn = DriverManager.getConnection(url, mysql_id, mysql_pw);	// �����ͺ��̽��� ����
            System.out.println("DB ���� �Ϸ�: editPassword");					// DB ���� ���� ���θ� �ܼ� â�� ���

            // SQL ��ɹ��� ������ ���� ����
            String sql = "update user_list set password = '" + pw + "' where id = '" + id + "'";

            // Statement ��ü ����
            stmt = conn.createStatement();

            // SQL ��ɹ� ����
            stmt.executeUpdate(sql);	// �α����� ID�� �ش��ϴ� ��й�ȣ�� ����

            // ���� ����(���� �ڿ��� ��ȯ)
            stmt.close();
            conn.close();
        }
        catch (ClassNotFoundException e) {
            System.out.println("JDBC ����̹� �ε� ����");
        }
        catch (SQLException e) {
            System.out.println("DB ���� ����: editPassword");
        }
    }

    // ȸ���� �����ϴ� �޼ҵ�
    public void deleteMember(String id) {
    	// �����ͺ��̽� ����
        try {
            // DB ����
            url = "jdbc:mysql://localhost:3306/member?serverTimezone=UTC";	// 'member' DB�� �����ϴ� URL
            Class.forName("com.mysql.cj.jdbc.Driver");						// JDBC����̹� �ε�
            conn = DriverManager.getConnection(url, mysql_id, mysql_pw);	// �����ͺ��̽��� ����
            System.out.println("DB ���� �Ϸ�: deleteMember");					// DB ���� ���� ���θ� �ܼ� â�� ���

            // SQL ��ɹ��� ������ ���� ����
            String sql1 = "delete from user_list where id = '" + id + "'";
            String sql2 = "drop database " + id + "_db";

            // Statement ��ü ����
            stmt = conn.createStatement();

            // SQL ��ɹ� ����
            stmt.executeUpdate(sql1);	// �α����� ID�� �ش��ϴ� ȸ���� 'user_list' Table���� ����
            stmt.executeUpdate(sql2);	// �α����� ID�� �ش��ϴ� ȸ�� ���� DB�� ����
            System.out.println("���� '" + id + "': ȸ��Ż�� �Ϸ�");
            
            // ���� ����(���� �ڿ��� ��ȯ)
            stmt.close();
            conn.close();
        }
        catch (ClassNotFoundException e) {
            System.out.println("JDBC ����̹� �ε� ����");
        }
        catch (SQLException e) {
            System.out.println("DB ���� ����: deleteMember");
        }
    }

    // ���ο� ��Ʈ�� �߰��ϴ� �޼ҵ�
    public void addNote(String id, String note_name) {
    	// �����ͺ��̽� ����
        try {
            // DB ����
            url = "jdbc:mysql://localhost:3306/" + id + "_db?serverTimezone=UTC";	// ȸ�� ���� DB�� �����ϴ� URL
            Class.forName("com.mysql.cj.jdbc.Driver");								// JDBC����̹� �ε�
            conn = DriverManager.getConnection(url, mysql_id, mysql_pw);			// �����ͺ��̽��� ����
            System.out.println("DB ���� �Ϸ�: addNote");								// DB ���� ���� ���θ� �ܼ� â�� ���
            
            // SQL ��ɹ��� ������ ���� ����
            String sql1 = "CREATE TABLE `" + note_name + "` ("
            			 + "id INT AUTO_INCREMENT PRIMARY KEY," // ����
            			 + "word VARCHAR(255)," 				// �ܾ�
            			 + "mean VARCHAR(255)," 				// ��
            			 + "pos VARCHAR(50)" 					// ǰ��
            			 + ")";
            String sql2 = "insert into note_list (name) values ('" + note_name + "')";
            
            // Statement ��ü ����
            stmt = conn.createStatement();
            
            // SQL ��ɹ� ����
            stmt.executeUpdate(sql1);	// �� ��Ʈ�� �̸����� ���ο� Table ����
            System.out.println("��Ʈ '" + note_name + "': Table ���� �Ϸ�");
            stmt.executeUpdate(sql2);	// �� ��Ʈ�� �̸��� 'note_list' Table�� ����
            System.out.println("Table 'note_list': '" + note_name + "' ���� �Ϸ�");

            // ���� ����(���� �ڿ��� ��ȯ)
            stmt.close();
            conn.close();
        }
        catch (ClassNotFoundException e) {
            System.out.println("JDBC ����̹� �ε� ����");
        }
        catch (SQLException e) {
            System.out.println("DB ���� ����: addNote");
        }
    }
    
    // ��Ʈ ����� ��ȸ�ϴ� �޼ҵ�
    public String selectNoteList(String id) {
    	// �����ͺ��̽� ����
        try {
            // DB ����
            url = "jdbc:mysql://localhost:3306/" + id + "_db?serverTimezone=UTC";	// ȸ�� ���� DB�� �����ϴ� URL
            Class.forName("com.mysql.cj.jdbc.Driver");								// JDBC����̹� �ε�
            conn = DriverManager.getConnection(url, mysql_id, mysql_pw);			// �����ͺ��̽��� ����
            System.out.println("DB ���� �Ϸ�: selectNoteList");						// DB ���� ���� ���θ� �ܼ� â�� ���

            // SQL ��ɹ��� ������ ���� ����
            String sql = "select name from note_list";

            // Statement ��ü ����
            stmt = conn.createStatement();
            
            // SQL ��ɹ� ���� �� ��� �� ó��
            result = stmt.executeQuery(sql);				// �α����� ȸ���� �߰��� ��� ��Ʈ�� �̸��� ��ȸ
            String note = null;								// ��� ��Ʈ�� �̸��� ������ ���� ����
            if (result.next()) {								// DB�� ��Ʈ�� �ִٸ�..
                note = result.getString("name");					// note�� ��Ʈ �̸��� �߰�
                while (result.next()) {								// ����� �� ��° ����� ������ �����..
                    note = note + "|" + result.getString("name");		// �� ��Ʈ�� �̸��� '|'�� �����Ͽ� note�� �߰�
                }
            }

            // ���� ����(���� �ڿ��� ��ȯ)
            result.close();
            stmt.close();
            conn.close();
            
            // ��� ��Ʈ�� �̸��� ������ ���ڿ� ���� note�� ��ȯ
            return note;  //t|tt
        }
        catch (ClassNotFoundException e) {
            System.out.println("JDBC ����̹� �ε� ����");
            return null;
        }
        catch (SQLException e) {
            System.out.println("DB ���� ����: selectNoteList");
            return null;
        }
    }
    
    // ��Ʈ�� �� ������ ��ȸ�ϴ� �޼ҵ�
    public int getNoteSize(String id, String noteName) {
    	// ������ ���� ����
        int rowCount = 0;
        
        // �����ͺ��̽� ����
        try {
            // DB ����
            url = "jdbc:mysql://localhost:3306/" + id + "_db?serverTimezone=UTC";	// ȸ�� ���� DB�� �����ϴ� URL
            Class.forName("com.mysql.cj.jdbc.Driver");								// JDBC����̹� �ε�
            conn = DriverManager.getConnection(url, mysql_id, mysql_pw);			// �����ͺ��̽��� ����
            System.out.println("DB ���� �Ϸ�: getNoteSize");							// DB ���� ���� ���θ� �ܼ� â�� ���
            
            // SQL ��ɹ��� ������ ���� ����
            String Query="select count(*) from `" + noteName+"`";
            
            // Statement ��ü ����
            stmt = conn.createStatement();
            
            // SQL ��ɹ� ���� �� ��� �� ó��
            result = stmt.executeQuery(Query);	// ���̺� ���� ���� ��ȯ
            if (result.next()) {
                rowCount = result.getInt(1);
                System.out.println(noteName + "�� ���� ����: " + rowCount);
            }

            // ���� ����(���� �ڿ��� ��ȯ)
            result.close();
            stmt.close();
            conn.close();
        }
        catch (ClassNotFoundException e) {
            System.out.println("JDBC ����̹� �ε� ����");
        }
        catch (SQLException e) {
            System.out.println("DB ���� ����: getNoteSize");
        }
        
        // ��� �� ����
        return rowCount;
    }
    
    // ��Ʈ�� �����ϴ� �޼ҵ�
    public void deleteNote(String id, String note_Name) {
    	// �����ͺ��̽� ����
        try {
            // DB ����
            url = "jdbc:mysql://localhost:3306/" + id + "_db?serverTimezone=UTC";	// ȸ�� ���� DB�� �����ϴ� URL
            Class.forName("com.mysql.cj.jdbc.Driver");								// JDBC����̹� �ε�
            conn = DriverManager.getConnection(url, mysql_id, mysql_pw);			// �����ͺ��̽��� ����
            System.out.println("DB ���� �Ϸ�: deleteNote");							// DB ���� ���� ���θ� �ܼ� â�� ���
            
            // SQL ��ɹ��� ������ ���� ����
            String sql1 = "DROP TABLE `" + note_Name + "`";
            String sql2 = "delete from note_list where name = '" + note_Name + "';";
            
            // Statement ��ü ����
            stmt = conn.createStatement();
            
            // SQL ��ɹ� ����
            stmt.executeUpdate(sql1);	// �ش� ��Ʈ Table�� ����
            System.out.println("Delete Table: "+note_Name);
            stmt.executeUpdate(sql2);	// 'note_list' ���̺��� �ش� ��Ʈ �̸��� ����

            // ���� ����(���� �ڿ��� ��ȯ)
            stmt.close();
            conn.close();
        }
        catch (ClassNotFoundException e) {
            System.out.println("JDBC ����̹� �ε� ����");
        }
        catch (SQLException e) {
            System.out.println("DB ���� ����: deleteNote");
        }
    }
    
    // ���ο� ���ܾ ��Ʈ�� �߰��ϴ� �޼ҵ�
    public void insertWord(String id, String note_name, String word, String mean, List<String> pos) {
    	// �����ͺ��̽� ����
    	try {
            // DB ����
            url = "jdbc:mysql://localhost:3306/" + id + "_db?serverTimezone=UTC";	// ȸ�� ���� DB�� �����ϴ� URL
            Class.forName("com.mysql.cj.jdbc.Driver");								// JDBC����̹� �ε�
            conn = DriverManager.getConnection(url, mysql_id, mysql_pw);			// �����ͺ��̽��� ����
            System.out.println("DB ���� �Ϸ�: insertWord");							// DB ���� ���� ���θ� �ܼ� â�� ���
            
            // SQL ��ɹ��� ������ ���� ����
            String sql = "INSERT INTO `" + note_name + "` (word, mean, pos)"
            			 + "VALUES ('" + word + "', '" + mean + "','" + pos + "')";
            
            // Statement ��ü ����
            stmt = conn.createStatement();
            
            // SQL ��ɹ� ����
            stmt.execute(sql);	// �ش� ��Ʈ Table�� ���ο� �ܾ�, ��, ǰ�縦 �߰�
            System.out.println("Table '" + note_name + "': �� ���ܾ� ���� �Ϸ�");
            
            // ���� ����(���� �ڿ��� ��ȯ)
            stmt.close();
            conn.close();
    	}
        catch (ClassNotFoundException e) {
            System.out.println("JDBC ����̹� �ε� ����");
        }
        catch (SQLException e) {
            System.out.println("DB ���� ����: insertWord");
        }
    }
    
    // �ش� ��Ʈ�� �߰��� ��� ���ܾ ��ȸ�ϴ� �޼ҵ�
    public String[][] lookEnglish(String id, String note_name) {
    	// �����ͺ��̽� ����
        try {
            // DB ����
            url = "jdbc:mysql://localhost:3306/" + id + "_db?serverTimezone=UTC";	// ȸ�� ���� DB�� �����ϴ� URL
            Class.forName("com.mysql.cj.jdbc.Driver");								// JDBC����̹� �ε�
            conn = DriverManager.getConnection(url, mysql_id, mysql_pw);			// �����ͺ��̽��� ����
            System.out.println("DB ���� �Ϸ�: lookEnglish");							// DB ���� ���� ���θ� �ܼ� â�� ���

            // SQL ��ɹ��� ������ ���� ����
            String sql1 = "select count(*) from `" + note_name +"`";
            String sql2 = "select * from `" + note_name + "`";

            // Statement ��ü ����
            stmt = conn.createStatement();

            // SQL ��ɹ� ���� �� ��� �� ó��
            result = stmt.executeQuery(sql1);	// ��Ʈ�� �ִ� ���ܾ��� ������ ��ȸ
            result.next();						// Ŀ���� ���� ������ �̵�
            int count = result.getInt(1);		// ���ܾ��� ������ count�� ����
            String[][] english;					// ��� �ܾ�, ��, ǰ�縦 ������ 2���� �迭 ����
            if (count > 0) {					// ��Ʈ�� ���ܾ �ִٸ�..
                english = new String[count][3];		// count*3��ŭ�� ũ�⸦ ������ 2���� �迭 ����
                result = stmt.executeQuery(sql2);	// ��Ʈ�� �ִ� ��� �ܾ�, ��, ǰ�縦 ��ȸ
                for (int i=0; i<count; i++) {
                    result.next();						// Ŀ���� ���� ������ �̵�
                    for (int j=0; j<3; j++) {
                        english[i][j] = result.getString(j+2);	// ����� �ε��� ��ȣ�� �̿��Ͽ� �÷� ���� ����
                    }
                }
            }
            else {								// ��Ʈ�� �ܾ ���ٸ�..
                english = null;						// 2���� �迭 english�� �������� ����
            }

            // ���� ����(���� �ڿ��� ��ȯ)
            result.close();
            stmt.close();
            conn.close();

            // ��� �ܾ�, ��, ǰ�縦 ������ english�� ��ȯ
            return english;
        }
        catch (ClassNotFoundException e) {
            System.out.println("JDBC ����̹� �ε� ����");
            return null;
        }
        catch (SQLException e) {
            System.out.println("DB ���� ����: lookEnglish");
            return null;
        }
    }
    
    // �ش� ��Ʈ�� �߰��� ���ܾ��� �ܾ�� ���� ��ȸ�ϴ� �޼ҵ�
    public String[][] selectWordAndMeaning(String id, String noteName) {
        // ��ȸ�� �ܾ �ޱ� ���� �迭 ����
        String word_list[][];
        
        // �����ͺ��̽� ����
        try {
            // DB ����
            url = "jdbc:mysql://localhost:3306/" + id + "_db?serverTimezone=UTC";	// ȸ�� ���� DB�� �����ϴ� URL
            Class.forName("com.mysql.cj.jdbc.Driver");								// JDBC����̹� �ε�
            conn = DriverManager.getConnection(url, mysql_id, mysql_pw);			// �����ͺ��̽��� ����
            System.out.println("DB ���� �Ϸ�: selectWordAndMeaning");					// DB ���� ���� ���θ� �ܼ� â�� ���
            
            // SQL ��ɹ��� ������ ���� ����
            String sql1 = "select count(*) from `" + noteName +"`";
            String sql2 = "select word, mean from `" + noteName + "`";
            
            // Statement ��ü ����
            stmt = conn.createStatement();
            
            // SQL ��ɹ� ���� �� ��� �� ó��
            result = stmt.executeQuery(sql1);	// ��Ʈ�� �ִ� ���ܾ��� ������ ��ȸ
            result.next();						// Ŀ���� ���� ������ �̵�
            int count = result.getInt(1);		// ���ܾ��� ������ count�� ����

            if(count>0) { //�ܾ ������...
                word_list = new String[count][2];	//���ܾ� ������ŭ �迭 ����
                
                result = stmt.executeQuery(sql2);	// �������� ��ȯ ����
                
                for (int i=0; i<count; i++) {
                	result.next();						// Ŀ���� ���� ������ �̵�
                    for (int j=0; j<2; j++) {
                        word_list[i][j] = result.getString(j+1);	// ����� �ε��� ��ȣ�� �̿��Ͽ� �÷� ���� ����
                    }
                }
            }
            else{ //�ܾ ������...
                word_list=null;
            }
            
            // ���� ����(���� �ڿ��� ��ȯ)
            result.close();
            stmt.close();
            conn.close();
            
            // �ܾ�� ���� ������ 2���� �迭 ����
            return word_list;
        }
        catch (ClassNotFoundException e) {
            System.out.println("JDBC ����̹� �ε� ����");
            return null;
        }
        catch (SQLException e) {
            System.out.println("DB ���� ����: selectWordAndMeaning");
            return null;
        }
    }
    
    // �ܾ��� ��ġ�� �˻��ϴ� �޼ҵ�
    public String[] searchWord(String id, String search_word) {
    	// �����ͺ��̽� ����
        try {
            // DB ����
            url = "jdbc:mysql://localhost:3306/" + id + "_db?serverTimezone=UTC";	// ȸ�� ���� DB�� �����ϴ� URL
            Class.forName("com.mysql.cj.jdbc.Driver");								// JDBC����̹� �ε�
            conn = DriverManager.getConnection(url, mysql_id, mysql_pw);			// �����ͺ��̽��� ����
            System.out.println("DB ���� �Ϸ�: searchWord");							// DB ���� ���� ���θ� �ܼ� â�� ���

            // SQL ��ɹ��� ������ ���� ����
            String sql = "select name from note_list";

            // Statement ��ü ����
            stmt = conn.createStatement();

            // SQL ��ɹ� ���� �� ��� �� ó��
            result = stmt.executeQuery(sql);	// �α����� ȸ���� �߰��� ��� ��Ʈ�� �̸��� ��ȸ
            
            String[] word = new String[3];    //[�ܾ�,��,ã�� ��Ʈ �̸�]�� ��ȯ���� �迭
            ArrayList<String> noteNameList = new ArrayList<String>();	// ��� ��Ʈ�� �̸��� �迭 ����Ʈ�� ����
            ArrayList<String> searchedNoteList = new ArrayList<String>();	// ã�� ��Ʈ�� �迭 ����Ʈ�� ����
            
            while (result.next()) {	// DB�� ��Ʈ�� �ִٸ�, ���� ���� ���� ������ �ݺ�
                noteNameList.add(result.getString("name"));  // ��� ��Ʈ�� Ž���ϱ� ���� note�̸��� ����Ʈ�� ������
            }
            
            for (int i = 0; i < noteNameList.size(); i++) {		// �ܾ�, �� ��ȸ ����
                result = stmt.executeQuery("SELECT word, mean FROM `" + noteNameList.get(i) 
                						   + "` where word = '" + search_word + "'");	//�� ��Ʈ���� ��� �ܾ� ��ȸ
                while (result.next()) {
                    word[0] = result.getString("word");	// ���� ���� word �� �о����
                    word[1] = result.getString("mean");	// ���� ���� mean �� �о����
                    if(word[0].equals(search_word)) {
                    	searchedNoteList.add(noteNameList.get(i)); //ã�� �ܾ��� ��Ʈ�� ����
                        System.out.println(noteNameList.get(i) + "����ã�� �ܾ�:" + word[0]); //ã�� �ܾ �ܼ� â�� ���
                        break;
                    }
                    else {            // ��ã���� null�� ���� �迭�� word[null,null]��ȯ��
                        System.out.println(noteNameList.get(i) +"��ã�� �ܾ�:"+word[0]);  //��ã���� null��ȯ��
                    }
                }
            }
            
            word[2] = String.join(", ", searchedNoteList);	// ã�� ��Ʈ ����� ����
            
            // ���� ����(���� �ڿ��� ��ȯ)
            result.close();
            stmt.close();
            conn.close();
            
            // word�迭 ��ȯ
            return word;
        }
        catch (ClassNotFoundException e) {
            System.out.println("JDBC ����̹� �ε� ����");
            return null;
        }
        catch (SQLException e) {
            System.out.println("DB ���� ����: searchWord");
            return null;
        }
    }
    
    // ���õ� ���ܾ���� ��Ʈ���� �����ϴ� �޼ҵ�
    public void deleteWords(String id, String noteName, String word[]) {
    	// �����ͺ��̽� ����
        try {
            // DB ����
            url = "jdbc:mysql://localhost:3306/" + id + "_db?serverTimezone=UTC";	// ȸ�� ���� DB�� �����ϴ� URL
            Class.forName("com.mysql.cj.jdbc.Driver");								// JDBC����̹� �ε�
            conn = DriverManager.getConnection(url, mysql_id, mysql_pw);			// �����ͺ��̽��� ����
            System.out.println("DB ���� �Ϸ�: deleteWords");							// DB ���� ���� ���θ� �ܼ� â�� ���

            // Statement ��ü ����
            stmt = conn.createStatement();

            // SQL ��ɹ� ����
            for (int i = 0; i < word.length; i++) {
                stmt.execute("DELETE FROM `" + noteName 
                			 + "` WHERE word = '" + word[i] + "'");		// ���õ� ��� ���ܾ���� �ش� ��Ʈ Table���� ���� 
            }

            // ���� ����(���� �ڿ��� ��ȯ)
            stmt.close();
            conn.close();
            System.out.println("�ܾ� ���� �Ϸ�");
        }
        catch (ClassNotFoundException e) {
            System.out.println("JDBC ����̹� �ε� ����");
        }
        catch (SQLException e) {
            System.out.println("DB ���� ����: deleteWords");
        }
    }
}