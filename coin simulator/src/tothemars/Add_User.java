package tothemars;
import java.util.Scanner;
import java.util.List;

public class Add_User {
	private int uid =0;
	private Scanner scanner =new Scanner(System.in);
	private String retype_passwd="";
	public void sign_in(List<MainUser> UserList) {
		System.out.println("회원가입을 시작합니다");
		int idCheck=3;
		UserList.add(new MainUser());
		while(true){
			System.out.print("id:");
			UserList.get(uid).id= scanner.next();
			for (int j = 0; j < uid; j++) {
				if (UserList.get(j).id.equals(UserList.get(uid).id)){
					System.out.println("이미 사용중인 아이디입니다");
					idCheck=1;
					break;
				}else {
					idCheck=0;
				}
			}
			if (idCheck==1){
				continue;
			}else {
				System.out.println("사용가능한 아이디입니다.");
				break;
			}
		}
		while(true) {
			//ps입력
			System.out.print("passwd:");
			UserList.get(uid).passwd= scanner.next();
			//ps확인
			System.out.print("retype_passwd:");
			retype_passwd= scanner.next();
			//id,ps맞으면 break
			if(UserList.get(uid).passwd.equals(retype_passwd)) {
				System.out.println("회원가입 성공");
				uid++;
				break;
			}
			else{
				System.out.println("재입력한 비밀번호가 다릅니다. 처음부터 비밀번호를 생성하십시오");
				continue;
			}
		}
	}
}
