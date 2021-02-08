public class User {
    private int jailCounter=1;
    private int jailFlag=0;
    private int currentPosition=1;
    private String name;
    private int money;
    private int controlMoney;
    private int railroadCount=0;
    public User(String name,int money){
        this.name=name;
        this.money=money;
    }
    public int getControlMoney() {
        return controlMoney;
    }
    public void setControlMoney(int controlMoney) {
        this.controlMoney = controlMoney;
    }
    public String getName() {
        return name;
    }
    public int getMoney() {
        return money;
    }
    public void setMoney(int money) {
        this.money = money;
    }
    public int getCurrentPosition() {
        return currentPosition;
    }
    public void setCurrentPosition(int currentPosition) {
        if(currentPosition<=40 && currentPosition>=1)
            this.currentPosition = currentPosition;
        else if(currentPosition>40)
            this.currentPosition=currentPosition-40;
        else if(currentPosition<0)
            this.currentPosition=currentPosition+40;
    }
    public int getRailroadCount() {
        return railroadCount;
    }
    public void setRailroadCountd() {
        this.railroadCount++;
    }
    public int getJailFlag() {
        return jailFlag;
    }
    public void setJailFlag(int jailFlag) {
        this.jailFlag = jailFlag;
    }
    public void setJailCounterd(){
        if(this.jailCounter<3){
            this.jailCounter++;
        }
        else if(this.jailCounter==3){
            this.jailCounter=1;
            this.jailFlag=0;
        }
    }
    public int getJailCounter() {
        return jailCounter;
    }
}