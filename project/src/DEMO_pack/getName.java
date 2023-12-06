//public class getName {
//    String name = "";
//
//    // 생성자
//    private getName() {
//
//    }
//    // 싱글톤 패턴 관련 블록(1/2)
//    public static getName getInstance() {
//        return getName.LazyHolder.INSTANCE;
//    }
//
//    // 싱글톤 패턴 관련 블록(2/2)
//    private static class LazyHolder {
//        public static final getName INSTANCE = new getName();
//    }
//
//    // 매개변수로 전달받은 문자열을 name에 저장하는 메소드
//    public void keepName(String id) {
//        // DB에 접근할 때 로그인한 id를 사용하기 위한 객체 생성
////        GetIDDEMO gi = GetIDDEMO.getInstance();//id를 가져오기
//        GetID gi=new GetID();
//        ConnectDB cd=new ConnectDB();
//        String name=cd.select_name(gi.id);
//        this.name = name;
//    }
//
//}
