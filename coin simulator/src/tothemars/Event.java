package tothemars;



import java.util.List;
import java.util.Random;


public class Event {
    private Random r = new Random();
    public void event(List<Coin> coinList, int i) {
        int eventRate = r.nextInt(10);
        int selec = r.nextInt(6);
        int currentPrice;

        if (eventRate == 0) {	// 10%의 확률로 이벤트 발생
            if (selec == 0 && i==2){		//
                System.out.println("====================================================");
                System.out.println("일론머스크 트위터 : Doge is Good.\n도지코인 20% 상승!");
                System.out.println("====================================================");
                currentPrice = coinList.get(i).Price;
                coinList.get(i).Price = (int)(currentPrice + (currentPrice * 0.2));
            }
            else if (selec == 1 && i==2) {
                System.out.println("====================================================");
                System.out.println("뉴스속보) 트위터 로고 시바견으로 변경...\n도지코인 30%상승");
                System.out.println("====================================================");
                currentPrice = coinList.get(i).Price;
                coinList.get(i).Price = (int)(currentPrice + (currentPrice * 0.3));
            }
            else if (selec == 2 && i==2) {
                System.out.println("====================================================");
                System.out.println("뉴스속보) 일론머스크 도지 대량매도...\n도지코인 -30% 하락!");
                System.out.println("====================================================");
                currentPrice = coinList.get(i).Price;
                coinList.get(i).Price = (int)(currentPrice - (currentPrice * 0.3));
            }
            else if (selec == 3 && i==3) {
                System.out.println("====================================================");
                System.out.println("뉴스속보) 개발자 2인 뱅크런!!\n루나코인 -상장폐지");
                System.out.println("====================================================");
                coinList.get(i).Price = 0;
            }
            else if (selec == 4 && i==4) {
                System.out.println("====================================================");
                System.out.println("뉴스속보) 김남국 의원 체포!! 위믹스 유통량 사기 본격 수사...\n위믹스코인 상장폐지!!");
                System.out.println("====================================================");
                coinList.get(i).Price = 0;
            }
            else if (selec == 5) {
                System.out.println("====================================================");
                System.out.println("뉴스속보) 암호화폐시장 규제 확대..\n코인전체 -20% 하락");
                System.out.println("====================================================");
                for (int j=0; j < coinList.size(); j++) {
                    currentPrice = coinList.get(j).Price;
                    coinList.get(j).Price = (int)(currentPrice - (currentPrice * 0.2));
                }
            }
        }


    }
}
