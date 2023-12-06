/*
 * 설명 : 데이터베이스에 접근할 때 필요한 사용자의 ID를 가지는 클래스
 *
 * 각 클래스에서 로그인한 사용자의 ID가 필요할 때, 이 클래스를 사용함.
 */

public class GetID {
    // 사용 중인 ID를 보관하는 클래스 변수 생성 - 클래스 이름으로 접근하여 해당 변수를 하나로 공유할 수 있음.
    static String id = null;

    // 사용 중인 ID를 id에 저장하는 클래스 메소드 - 클래스 이름으로 접근하여 해당 메소드를 호출할 수 있음.
    public static void keepID(String id) {
        GetID.id = id;
    }
}