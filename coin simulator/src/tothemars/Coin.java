package tothemars;


public abstract class Coin {
    public String Name;
    public int Price;
    public int Index;
}
class Bitcoin extends Coin{
    Bitcoin(){
        Name="BITCOIN";
        Price=35000000;
        Index=0;
    }
}

class Ethereum extends Coin{
    Ethereum(){
        Name="ETHEREUM";
        Price=2500000;
        Index=1;
    }
}

class Doge extends Coin{
    Doge(){
        Name="DOGE";
        Price=100;
        Index=2;
    }
}
class Luna extends Coin{
    Luna(){
        Name="LUNA";
        Price=100000;
        Index=3;
    }
}

class Wemix extends Coin{
    Wemix(){
        Name="WEMIX";
        Price=100000;
        Index=4;
    }
}








