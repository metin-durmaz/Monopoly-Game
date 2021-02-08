import java.io.*;
import java.util.*;
public class Land extends Property {
    public Land(String name, double cost,String owner,int id,int number) {
        super(name, cost,owner,id,number);
    }
    public int action(List<Property> propertyList, String landName, String player1Name, String player2Name, User player1,
                           User player2, FileWriter outputFile, int dice, int landCost, User banker, int control, int control2) throws IOException {
        int flag=0,flag1=0,flag2=0,flag3=0;
        for(Property i:propertyList){
            if(i.getName().equals(landName) && i.getOwner().equals(player2Name)){
                if(i.getCost()>=0 && i.getCost()<=2000){
                    int rent=(int)i.getCost()*40/100;
                    if(rent<=player1.getMoney()){
                        player1.setMoney(player1.getMoney()-rent);
                        player2.setMoney(player2.getMoney()+rent);
                        flag1=1;
                    }
                }
                else if(i.getCost()>=2001 && i.getCost()<=3000){
                    int rent=(int)i.getCost()*30/100;
                    if(rent<=player1.getMoney()){
                        player1.setMoney(player1.getMoney()-rent);
                        player2.setMoney(player2.getMoney()+rent);
                        flag2=1;
                    }
                }
                else if (i.getCost()>=3001 && i.getCost()<=4000){
                    int rent=(int)i.getCost()*35/100;
                    if(rent<=player1.getMoney()){
                        player1.setMoney(player1.getMoney()-rent);
                        player2.setMoney(player2.getMoney()+rent);
                        flag3=1;
                    }
                }
                if(flag1==1 || flag2==1 || flag3==1){
                    if(control==0 && control2==0){
                        if(player1.getName().equals("Player 1"))
                            outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+player1.getName()+" paid rent for "+landName+"\n");
                        else if(player1.getName().equals("Player 2"))
                            outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player2.getMoney()+"\t"+player1.getMoney()+"\t"+player1.getName()+" paid rent for "+landName+"\n");
                    }
                    else if(control2==1){
                        if(player1.getName().equals("Player 1"))
                            outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+player1.getName()+" draw Advance to Leicester Square "+player1.getName()+" paid rent for "+landName+"\n");
                        else if(player1.getName().equals("Player 2"))
                            outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player2.getMoney()+"\t"+player1.getMoney()+"\t"+player1.getName()+" draw Advance to Leicester Square "+player1.getName()+" paid rent for "+landName+"\n");
                    }
                    else if(control==1){
                        if(player1.getName().equals("Player 1"))
                            outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+player1.getName()+" draw Go back 3 spaces "+player1.getName()+" paid rent for "+landName+"\n");
                        else if(player1.getName().equals("Player 2"))
                            outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player2.getMoney()+"\t"+player1.getMoney()+"\t"+player1.getName()+" draw Go back 3 spaces "+player1.getName()+" paid rent for "+landName+"\n");
                    }
                }
                else{
                    if(player1.getName().equals("Player 1"))
                        outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+player1.getName()+" goes bankrupt\n");
                    else if(player1.getName().equals("Player 2"))
                        outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player2.getMoney()+"\t"+player1.getMoney()+"\t"+player1.getName()+" goes bankrupt\n");
                    player1.setControlMoney(1);
                    flag=1;
                    break;
                }
                flag=1;
                break;
            }
            else if(i.getName().equals(landName) && i.getOwner().equals(player1Name)){
                if(control==0 && control2==0){
                    if(player1.getName().equals("Player 1"))
                        outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+player1.getName()+" has "+landName+"\n");
                    else if(player1.getName().equals("Player 2"))
                        outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player2.getMoney()+"\t"+player1.getMoney()+"\t"+player1.getName()+" has "+landName+"\n");
                }
                else if(control2==1){
                    if(player1.getName().equals("Player 1"))
                        outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+player1.getName()+" draw Advance to Leicester Square "+player1.getName()+" has "+landName+"\n");
                    else if(player1.getName().equals("Player 2"))
                        outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player2.getMoney()+"\t"+player1.getMoney()+"\t"+player1.getName()+" draw Advance to Leicester Square "+player1.getName()+" has "+landName+"\n");
                }
                else if(control==1){
                    if(player1.getName().equals("Player 1"))
                        outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+player1.getName()+" draw Go back 3 spaces "+player1.getName()+" has "+landName+"\n");
                    else if(player1.getName().equals("Player 2"))
                        outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player2.getMoney()+"\t"+player1.getMoney()+"\t"+player1.getName()+" draw Go back 3 spaces "+player1.getName()+" has "+landName+"\n");
                }
                flag=1;
                break;
            }
        }
        if(flag==0){
            if(player1.getMoney()>=landCost){
                player1.setMoney(player1.getMoney()-landCost);
                banker.setMoney(banker.getMoney()+landCost);
                if(control==0 && control2==0){
                    if(player1.getName().equals("Player 1"))
                        outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+player1.getName()+" bought "+landName+"\n");
                    else if(player1.getName().equals("Player 2"))
                        outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player2.getMoney()+"\t"+player1.getMoney()+"\t"+player1.getName()+" bought "+landName+"\n");
                }
                else if(control2==1){
                    if(player1.getName().equals("Player 1"))
                        outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+player1.getName()+" draw Advance to Leicester Square "+player1.getName()+" bought "+landName+"\n");
                    else if(player1.getName().equals("Player 2"))
                        outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player2.getMoney()+"\t"+player1.getMoney()+"\t"+player1.getName()+" draw Advance to Leicester Square "+player1.getName()+" bought "+landName+"\n");
                }
                else if(control==1){
                    if(player1.getName().equals("Player 1"))
                        outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+player1.getName()+" draw Go back 3 spaces "+player1.getName()+" bought "+landName+"\n");
                    else if(player1.getName().equals("Player 2"))
                        outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player2.getMoney()+"\t"+player1.getMoney()+"\t"+player1.getName()+" draw Go back 3 spaces "+player1.getName()+" bought "+landName+"\n");
                }
                return 1;
            }
            else {
                if(player1.getName().equals("Player 1"))
                    outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+player1.getName()+" goes bankrupt\n");
                else if(player1.getName().equals("Player 2"))
                    outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player2.getMoney()+"\t"+player1.getMoney()+"\t"+player1.getName()+" goes bankrupt\n");
                player1.setControlMoney(1);
            }
        }
        return 0;
    }
}