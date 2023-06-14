package tothemars;

import java.util.ArrayList;

abstract class User {
	protected String id;
	protected String passwd;
	public int wallet;
	public abstract void makeCoinWallet(int length, MainUser user);
}
class MainUser extends User{
	protected ArrayList<Integer> WalletCoinCount = new ArrayList<Integer>();
	protected ArrayList<Integer> WalletCoinCount_Short = new ArrayList<Integer>();
	protected ArrayList<Integer>  TotalPrice= new ArrayList<Integer>();
	protected ArrayList<Integer>  TotalPrice_Short= new ArrayList<Integer>();
	public MainUser(){
		id="";
		passwd="";
		wallet=0;
	}
	@Override
	public void makeCoinWallet(int length, MainUser user){
		for (int i = 0; i <length ; i++) {
			user.WalletCoinCount.add(0);//몇개를 가지고있는가(롱)
			user.WalletCoinCount_Short.add(0);//몇개를 가지고있는가(숏)
			user.TotalPrice.add(0);//구매했을때 기준 총 가격
			user.TotalPrice_Short.add(0);//구매했을때 기준 총 가격
		}
	}
}

