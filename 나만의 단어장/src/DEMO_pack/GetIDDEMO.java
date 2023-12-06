package DEMO_pack;

// DB에 접근할 때 필요한 id를 갖고 있는 클래스 (싱글톤 패턴 - 다른 클래스로부터 문자열을 전달받아 저장하는 id 변수를 모든 클래스에서 사용하기 위한 목적)
class GetIDDEMO {
    // 로그인한 id를 가지는 변수 생성
    String id = "";

    // 생성자
    private GetIDDEMO() {

    }

    // 싱글톤 패턴 관련 블록(1/2)
    public static GetIDDEMO getInstance() {
        return LazyHolder.INSTANCE;
    }

    // 싱글톤 패턴 관련 블록(2/2)
    private static class LazyHolder {
        public static final GetIDDEMO INSTANCE = new GetIDDEMO();
    }

    // 매개변수로 전달받은 문자열을 id에 저장하는 메소드
    public void keepId(String id) {
        this.id = id;
    }
}