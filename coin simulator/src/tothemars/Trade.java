package tothemars;


import java.util.List;
import java.util.Scanner;
//1.거래 2.
public class Trade{
    private Event event = new Event();
    private Scanner scanner = new Scanner(System.in);
    private PriceMovement priceMovement = new PriceMovement();
    private ShowPrice showPrice = new ShowPrice();
    public int execute(MainUser user, List<Coin> coinList){
        double price1;
        double price2;
        String temp="0";
        while (true){
            System.out.println("\n현재 각 코인 가격 : \n");
            for (int i = 0; i < coinList.size(); i++) {
                price1=coinList.get(i).Price;
                priceMovement.priceMove(coinList.get(i));
                event.event(coinList, i);
                price2=coinList.get(i).Price;
                temp=String.format("%+5.2f", priceMovement.priceMovement(price1, price2));
                System.out.printf("%-10s 코인 가격 : %10d   변동률 : %s%%\n", coinList.get(i).Name, coinList.get(i).Price, temp);
            }
            System.out.println("\n현재 내 지갑 잔액 : "+user.wallet);
            int sum=0;
            for (int i = 0; i < coinList.size(); i++) {
                int appraisedValue2 = user.WalletCoinCount.get(coinList.get(i).Index)*coinList.get(i).Price;//실시간 현재 코인 가격
                int appraisedValue = user.TotalPrice.get(coinList.get(i).Index);//구매했었던 그때의 코인 토탈 가격
                System.out.printf("현재 보유중인 %10s    개수(롱) : %4d", coinList.get(i).Name, user.WalletCoinCount.get(coinList.get(i).Index));
                temp= String.format("%+5.2f", priceMovement.priceMovement(appraisedValue, appraisedValue2));
                if (user.WalletCoinCount.get(coinList.get(i).Index)==0){
                    temp="0";
                }
                System.out.printf("   평가금액 : %10d   수익률 : %s%% \n", appraisedValue2, temp);
                sum+=appraisedValue2;
            }
            System.out.println("총 평가금액(롱) : "+sum);
            sum=0;
            System.out.println();
            for (int i = 0; i < coinList.size(); i++) {
                int appraisedValue2 = user.WalletCoinCount_Short.get(coinList.get(i).Index)*coinList.get(i).Price;
                int appraisedValue = user.TotalPrice_Short.get(coinList.get(i).Index);//
                System.out.printf("현재 보유중인 %10s    개수(숏) : %4d", coinList.get(i).Name, user.WalletCoinCount_Short.get(coinList.get(i).Index));
                temp= String.format("%+5.2f", -1*priceMovement.priceMovement(appraisedValue, appraisedValue2));
                appraisedValue=(int)(appraisedValue-((appraisedValue*priceMovement.priceMovement(appraisedValue, appraisedValue2))/100));
                //appraisedValue=(int)(user.WalletCoinCount_Short.get(coinList.get(i).Index)*((appraisedValue/user.WalletCoinCount_Short.get(coinList.get(i).Index))*priceMovement.priceMovement(appraisedValue, appraisedValue2))/100);
                if (user.WalletCoinCount_Short.get(coinList.get(i).Index)==0){
                    appraisedValue=0;
                    temp="0";
                }
                System.out.printf("   평가금액 : %10d   수익률 : %s%% \n", appraisedValue, temp);
                sum+=appraisedValue;
            }
            System.out.println("총 평가금액(숏) : "+sum);
            System.out.println("\n무엇을 하시겠습니까?\n");
            System.out.println("1.거래   2.입금   3.로그아웃  4.종료  5.새로고침(가격 갱신)");
            int flag= scanner.nextInt();
            if (flag==1){
                System.out.println("어떤 코인을 거래하시겠습니까?");
                System.out.println("1.비트코인 2.이더리움 3.도지 4.루나 5.위믹스");
                int choice= scanner.nextInt();
                TradeCoin(coinList.get(choice-1), user);
            } else if (flag==2) {
                InputMoney(user);
            } else if (flag==3) {
                break;
            } else if (flag==4) {
                return 0;
            } else if (flag==5) {
                //event.event(coinList);
                System.out.println("가격을 갱신합니다.");
            } else {
                System.out.println("잘못된 입력입니다 처음부터 다시 시도하세요");
                continue;
            }
        }
    return -1;
    }
    void InputMoney(MainUser mainUser){
        int money=0;
        while (true){
            System.out.println("얼마를 입금하시겠습니까?");
            money=scanner.nextInt();
            if (money<=0){
                System.out.println("잘못된 금액 입력!");
                System.out.println("처음부터 다시 시도하세요.");
                continue;
            }
            if (money>0){
                mainUser.wallet+=money;
                break;
            }
        }
    }

    void TradeCoin(Coin coin, MainUser user){
        while (true){
            System.out.println(coin.Name+" 호가 : ");
            showPrice.ShowPrice_Method(coin);
            System.out.println("\n1.매수   2.매도   3.종료");
            int choice = scanner.nextInt();
            if (choice==1){
                BuyCoin(coin, user);
            }else if (choice==2){
                SellCoin(coin, user);
            }else if (choice==3){
                break;
            }
        }
    }

    void BuyCoin(Coin coin, MainUser user){
        while (true){
            System.out.println("1.롱   2.숏   3.종료");
            int choice = scanner.nextInt();
            if (choice==1){
                System.out.println("몇개를 구매하시겠습니까?(롱)");
                int buyCount = scanner.nextInt();
                System.out.println("구매하실 코인 개수 : "+buyCount+"개");
                System.out.println("현재 코인 가격은 : "+coin.Price);
                System.out.println("총 매수 가격은 : "+coin.Price*buyCount+"원 입니다.");
                System.out.println("정말 매수하시겠습니까?(롱) \n1.예 2.아니오");
                choice= scanner.nextInt();
                if (choice==2){
                    System.out.println("매수를 취소하셨습니다.");
                    break;
                }
                if (user.wallet<coin.Price*buyCount){
                    System.out.println("잔액이 부족합니다");
                    continue;
                }
                user.WalletCoinCount.set(coin.Index, user.WalletCoinCount.get(coin.Index)+buyCount);
                user.TotalPrice.set(coin.Index, user.TotalPrice.get(coin.Index)+coin.Price*buyCount);
                System.out.println("현재 보유중인 "+coin.Name+" 개수 : "+user.WalletCoinCount.get(coin.Index));
                System.out.println("평가금액 : "+user.TotalPrice.get(coin.Index));
                user.wallet-=buyCount*coin.Price;
            }else if (choice==2){
                System.out.println("몇개를 구매하시겠습니까?(숏)");
                int buyCount = scanner.nextInt();
                System.out.println("구매하실 코인 개수 : "+buyCount+"개");
                System.out.println("현재 코인 가격은 : "+coin.Price);
                System.out.println("총 매수 가격은 : "+coin.Price*buyCount+"원 입니다.");
                System.out.println("정말 매수하시겠습니까?(숏) \n1.예 2.아니오");
                choice= scanner.nextInt();
                choice--;
                if (choice==1){
                    System.out.println("매수를 취소하셨습니다.");
                    break;
                }
                if (user.wallet<coin.Price*buyCount){
                    System.out.println("잔액이 부족합니다");
                    continue;
                }
                if (user.WalletCoinCount_Short.get(coin.Index)==0){
                    user.TotalPrice_Short.set(coin.Index, coin.Price*buyCount);
                    user.WalletCoinCount_Short.set(coin.Index, user.WalletCoinCount_Short.get(coin.Index)+buyCount);//유저의 코인 개수를 갱신
                }else {
                    user.WalletCoinCount_Short.set(coin.Index, user.WalletCoinCount_Short.get(coin.Index)+buyCount);//유저의 코인 개수를 갱신
                    int avgPrice = user.TotalPrice_Short.get(coin.Index)/user.WalletCoinCount_Short.get(coin.Index);
                    user.TotalPrice_Short.set(coin.Index, user.TotalPrice_Short.get(coin.Index)+(2*avgPrice-coin.Price)*buyCount);//코인 숏 평가금액 갱신
                }
                System.out.println("현재 보유중인 "+coin.Name+" 개수 : "+user.WalletCoinCount_Short.get(coin.Index));
                System.out.println("평가금액 : "+user.TotalPrice_Short.get(coin.Index));//갱신된 평가금액 출력
                user.wallet-=buyCount*coin.Price;//유저의 지갑에 구매한만큼의 돈을 차감
                System.out.println("거래완료");
            }else if (choice==3){
                break;
            }else {
                System.out.println("잘못된 입력입니다.");
            }
        }

    }

    void SellCoin(Coin coin, MainUser user){
        while (true){
            System.out.println("1. 매도(롱)    2. 매도(숏)   3. 종료");
            int choice= scanner.nextInt();
            int sellCount=0;
            String temp;
            if (choice==1){
                System.out.println("몇개를 매도하시겠습니까?");
                System.out.println(coin.Name+" 현재보유량(롱) : "+user.WalletCoinCount.get(coin.Index)+"개");
                sellCount= scanner.nextInt();
                if (sellCount>user.WalletCoinCount.get(coin.Index)){
                    System.out.println("현재보유량보다 많은 값을 입력하셨습니다 다시 시도하세요");
                    continue;
                }
                user.WalletCoinCount.set(coin.Index, user.WalletCoinCount.get(coin.Index)-sellCount);//코인 카운터 갱신. 판 갯수만큼 카운터-
                user.TotalPrice.set(coin.Index, user.TotalPrice.get(coin.Index)-sellCount*coin.Price);//그러면 토탈가격은 최종가격-판매갯수*현재가격
                user.wallet+=(coin.Price*sellCount);//판만큼 지갑에 현금 더해주기
                System.out.println("판매완료");
            }else if (choice==2){
                System.out.println("몇개를 매도하시겠습니까?");
                System.out.println(coin.Name+" 현재보유량(숏) : "+user.WalletCoinCount_Short.get(coin.Index)+"개");
                sellCount= scanner.nextInt();
                if (sellCount>user.WalletCoinCount_Short.get(coin.Index)){
                    System.out.println("현재보유량보다 많은 값을 입력하셨습니다 다시 시도하세요");
                    continue;
                }
                int avgPrice = user.TotalPrice_Short.get(coin.Index)/user.WalletCoinCount_Short.get(coin.Index);//구 평균단가 구함
                user.WalletCoinCount_Short.set(coin.Index, user.WalletCoinCount_Short.get(coin.Index)-sellCount);//코인 카운터 갱신. 판 갯수만큼 카운터-
                user.TotalPrice_Short.set(coin.Index, user.TotalPrice_Short.get(coin.Index)-(2*avgPrice-coin.Price)*sellCount);
                //그러면 토탈가격은 구 평가금액 - (현재 코인단가-구 코인단가)
                user.wallet+=(2*avgPrice-coin.Price)*sellCount;//판만큼 지갑에 현금 더해주기
                System.out.println("판매완료");
            }else if (choice==3){
                break;
            }else {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }
}
