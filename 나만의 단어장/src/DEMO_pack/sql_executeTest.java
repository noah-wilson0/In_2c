package DEMO_pack;

/*
    bManger.crete_DB("hello");를 하고나서 url을 바꿔야되지않나?
 */
public class sql_executeTest {
    public static void main(String[] args) {
        //db 생성 -성공
//        SQL_DEMO dbManger=new SQL_DEMO();
        //0초기셋팅 데이터 베이스 생성- 성공

//        dbManger.close(); //처음 연결한 db해제
//        //다시 jdbc연결
//        SQL_DEMO dbManger2=new SQL_DEMO(db_name);
        //0.초기 셋팅 - 회원 계정을 담을 테이블 생성-성공
        // uid int AUTO_INCREMENT PRIMARY KEY= 회원가입할떄마다 증가
        // id=8자리   ps=12자리
//        String create_student = "create table singers(id varchar(8), ps varchar(12),name varchar(3));";
//        dbManger.execute(create_student);

        //추가-성공
//        mysql> insert into singers (id,ps) values('1234567','12345678910');
//        Query OK, 1 row affected (0.01 sec)

        //조회
//        mysql> select * from singers;
//        +-----+---------+-------------+
//                | uid | id      | ps          |
//                +-----+---------+-------------+
//                |   1 | 1234567 | 12345678910 |
//                +-----+---------+-------------+
//                        1 row in set (0.00 sec)
//        mysql> insert into singers (id,ps) values('1234','1234567');
//        Query OK, 1 row affected (0.01 sec)
//
//        mysql> select * from singers;
//                   +-----+---------+-------------+
//                | uid | id      | ps          |
//                +-----+---------+-------------+
//                |   1 | 1234567 | 12345678910 |
//                |   2 | 1234    | 1234567     |
//                +-----+---------+-------------+
//                        2 rows in set (0.00 sec)
//        uid가 자동으로 증가한 모습
        //삭제-성공
//        mysql> drop table singers;
//        Query OK, 0 rows affected (0.01 sec)

//        dbManger.close();
    }
}
