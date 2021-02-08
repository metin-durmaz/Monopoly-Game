import java.io.*;
import java.util.*;
public class Company extends Property {
    public Company(String name, double cost,String owner,int id,int number) {
        super(name, cost,owner,id,number);
    }
    public int action(List<Property> propertyList,String companyName,String player1Name,String player2Name,User player1,
                       User player2,FileWriter outputFile,int dice,int companyCost,User banker,int control,int control2) throws IOException {
        int flag=0,flag1=0;
        for(Property i:propertyList){
            if(i.getName().equals(companyName) && i.getOwner().equals(player2Name)){
                int rent=4*dice;
                if(rent<=player1.getMoney()){
                    player1.setMoney(player1.getMoney()-rent);
                    player2.setMoney(player2.getMoney()+rent);
                    flag1=1;
                }
                if(control==0){
                    if(flag1==1){
                        if(player1.getName().equals("Player 1"))
                            outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+player1.getName()+" paid rent for "+companyName+"\n");
                        else if(player1.getName().equals("Player 2"))
                            outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player2.getMoney()+"\t"+player1.getMoney()+"\t"+player1.getName()+" paid rent for "+companyName+"\n");
                    }
                    else if(flag1==0){
                        if(player1.getName().equals("Player 1"))
                            outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+player1.getName()+" goes bankrupt\n");
                        else if(player1.getName().equals("Player 2"))
                            outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player2.getMoney()+"\t"+player1.getMoney()+"\t"+player1.getName()+" goes bankrupt\n");
                        flag=1;
                        player1.setControlMoney(1);
                        break;
                    }
                }
                else if(control==1){
                    if(flag1==1){
                        if(player1.getName().equals("Player 1"))
                            outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+player1.getName()+" draw Go back 3 spaces "+player1.getName()+" paid rent for "+companyName+"\n");
                        else if(player1.getName().equals("Player 2"))
                            outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player2.getMoney()+"\t"+player1.getMoney()+"\t"+player1.getName()+" draw Go back 3 spaces "+player1.getName()+" paid rent for "+companyName+"\n");
                    }
                    else if(flag1==0){
                        if(player1.getName().equals("Player 1"))
                            outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+player1.getName()+" goes bankrupt\n");
                        else if(player1.getName().equals("Player 2"))
                            outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player2.getMoney()+"\t"+player1.getMoney()+"\t"+player1.getName()+" goes bankrupt\n");
                        flag=1;
                        player1.setControlMoney(1);
                        break;
                    }
                }
                flag=1;
                break;
            }
            else if(i.getName().equals(companyName)&& i.getOwner().equals(player1Name)){
                if(control==0){
                    if(player1.getName().equals("Player 1"))
                        outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+player1.getName()+" has "+companyName+"\n");
                    else if(player1.getName().equals("Player 2"))
                        outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player2.getMoney()+"\t"+player1.getMoney()+"\t"+player1.getName()+" has "+companyName+"\n");
                }
                else if(control==1){
                    if(player1.getName().equals("Player 1"))
                        outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+player1.getName()+" draw Go back 3 spaces"+player1.getName()+" has "+companyName+"\n");
                    else if(player1.getName().equals("Player 2"))
                        outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player2.getMoney()+"\t"+player1.getMoney()+"\t"+player1.getName()+" draw Go back 3 spaces"+player1.getName()+" has "+companyName+"\n");
                }
                flag=1;
                break;
            }
        }
        if(flag==0){
            if(player1.getMoney()>=companyCost){
                player1.setMoney(player1.getMoney()-companyCost);
                banker.setMoney(banker.getMoney()+companyCost);
                if(control==0){
                    if(player1.getName().equals("Player 1"))
                        outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+player1.getName()+" bought "+companyName+"\n");
                    else if(player1.getName().equals("Player 2"))
                        outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player2.getMoney()+"\t"+player1.getMoney()+"\t"+player1.getName()+" bought "+companyName+"\n");
                }
                else if(control==1){
                    if(player1.getName().equals("Player 1"))
                        outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+player1.getName()+" draw Go back 3 spaces "+player1.getName()+" bought "+companyName+"\n");
                    else if(player1.getName().equals("Player 2"))
                        outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player2.getMoney()+"\t"+player1.getMoney()+"\t"+player1.getName()+" draw Go back 3 spaces "+player1.getName()+" bought "+companyName+"\n");
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