package tothemars;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PriceMovement priceMovement = new PriceMovement();

        ArrayList<Coin> CoinList = new ArrayList<>();
        CoinList.add(new Bitcoin());
        CoinList.add(new Ethereum());
        CoinList.add(new Doge());
        CoinList.add(new Luna());
        CoinList.add(new Wemix());

        ArrayList<MainUser> UserList = new ArrayList<>();
        Log_in log_in = new Log_in();
        Trade trade = new Trade();
        Add_User add_user = new Add_User();

        String flag="-1";
        int UserIndex=-1;

        System.out.println("Welcome To Silla Bit!");
        while (!flag.equals("0")){
            System.out.println("================================");
            System.out.println("1. 회원가입    2. 로인     0. 종료");
            flag=scanner.next();
            switch (flag){
                case "0":
                    System.out.println("프로그램 종료");
                    flag="0";
                    break;
                case "1":
                    add_user.sign_in(UserList);
                    break;
                case "2":
                    UserIndex=log_in.Log_In(UserList);
                    break;
                default:
                    System.out.println("잘못된 입력입니다.");
                    continue;
            }
            if (log_in.flag==1) {
                continue;
            }else if (log_in.flag!=1 && flag!="0"){
                MainUser logInUser=UserList.get(UserIndex);
                logInUser.makeCoinWallet(CoinList.size(), logInUser);
                flag=Integer.toString(trade.execute(logInUser, CoinList));

            }
        }
    }
}