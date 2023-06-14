package tothemars;

public class ShowPrice{
    void ShowPrice_Method(Coin obj){
        System.out.println("코인 이름 : "+obj.Name);
        System.out.println("\n-------호가-------\n");
        for (int i = -4; i <= 4; i++) {
            System.out.println((i+5)+"  "+(int)(obj.Price+obj.Price*0.001*i));
            //System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        }
    }
}
