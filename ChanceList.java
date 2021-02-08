import org.json.simple.*;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.util.*;
public class ChanceList extends Card {
    public ChanceList(String item) {
        super(item);
    }
    public void action(User player,User player2,User banker,int dice, List<String> chanceList,List<String> communityChestList,
                       FileWriter outputFile,JSONArray land,List<Property> propertyList,int control) throws IOException, ParseException {
        String playerName=player.getName();
        String player2Name=player2.getName();
        if(getItem().equals("Advance to Go (Collect $200)")){
            player.setCurrentPosition(1);
            player.setMoney(player.getMoney()+200);
            banker.setMoney(banker.getMoney()-200);
            if(control==0){
                if(player.getName().equals("Player 1"))
                    outputFile.write(player.getName()+"\t"+dice+"\t"+player.getCurrentPosition()+"\t"+player.getMoney()+"\t"+player2.getMoney()+"\t"+player.getName()+" draw "+getItem()+"\n");
                else if(player.getName().equals("Player 2"))
                    outputFile.write(player.getName()+"\t"+dice+"\t"+player.getCurrentPosition()+"\t"+player2.getMoney()+"\t"+player.getMoney()+"\t"+player.getName()+" draw "+getItem()+"\n");
            }
            else if(control==1){
                if(player.getName().equals("Player 1"))
                    outputFile.write(player.getName()+"\t"+dice+"\t"+player.getCurrentPosition()+"\t"+player.getMoney()+"\t"+player2.getMoney()+"\t"+player.getName()+" draw Go back 3 spaces "+player.getName()+" draw "+getItem()+"\n");
                else if(player.getName().equals("Player 2"))
                    outputFile.write(player.getName()+"\t"+dice+"\t"+player.getCurrentPosition()+"\t"+player2.getMoney()+"\t"+player.getMoney()+"\t"+player.getName()+" draw Go back 3 spaces "+player.getName()+" draw "+getItem()+"\n");
            }
        }
        else if(getItem().equals("Go back 3 spaces")){
            int firstPosition=player.getCurrentPosition();
            player.setCurrentPosition(player.getCurrentPosition()-3);
            int lastPosition=player.getCurrentPosition();
            if(lastPosition>firstPosition||lastPosition==1){
                player.setMoney(player.getMoney()+200);
                banker.setMoney(banker.getMoney()-200);
            }
            Main.player(player,player2,banker,dice,propertyList,chanceList,communityChestList,outputFile,1);
        }
        else if(getItem().equals("Advance to Leicester Square")){
            int firstPosition=player.getCurrentPosition();
            int id = 0,cost=0;
            for (Object i : land) {
                if (String.valueOf(((JSONObject) i).get("name")).equals("Leicester Square")) {
                    id = Integer.parseInt(String.valueOf(((JSONObject) i).get("id")));
                    cost=Integer.parseInt(String.valueOf(((JSONObject) i).get("cost")));
                }
            }
            player.setCurrentPosition(id);
            int lastPosition=player.getCurrentPosition();
            Main.go(firstPosition,lastPosition,player,banker);
            Property landObj=new Land("Leicester Square",cost,player.getName(),id,1);
            int turn=landObj.action(propertyList,"Leicester Square",playerName,player2Name,player,player2,outputFile,dice,cost,banker,control,1);
            if(turn==1)
                propertyList.add(landObj);
        }
        else if(getItem().equals("Pay poor tax of $15")){
            if(player.getMoney()>=15){
                player.setMoney(player.getMoney()-15);
                banker.setMoney(banker.getMoney()+15);
                if(control==0){
                    if(player.getName().equals("Player 1"))
                        outputFile.write(player.getName()+"\t"+dice+"\t"+player.getCurrentPosition()+"\t"+player.getMoney()+"\t"+player2.getMoney()+"\t"+player.getName()+" draw "+getItem()+"\n");
                    else if(player.getName().equals("Player 2"))
                        outputFile.write(player.getName()+"\t"+dice+"\t"+player.getCurrentPosition()+"\t"+player2.getMoney()+"\t"+player.getMoney()+"\t"+player.getName()+" draw "+getItem()+"\n");
                }
                else if(control==1){
                    if(player.getName().equals("Player 1"))
                        outputFile.write(player.getName()+"\t"+dice+"\t"+player.getCurrentPosition()+"\t"+player.getMoney()+"\t"+player2.getMoney()+"\t"+player.getName()+" draw Go back 3 spaces "+player.getName()+" draw "+getItem()+"\n");
                    else if(player.getName().equals("Player 2"))
                        outputFile.write(player.getName()+"\t"+dice+"\t"+player.getCurrentPosition()+"\t"+player2.getMoney()+"\t"+player.getMoney()+"\t"+player.getName()+" draw Go back 3 spaces "+player.getName()+" draw "+getItem()+"\n");
                }
            }
            else{
                if(player.getName().equals("Player 1"))
                    outputFile.write(player.getName()+"\t"+dice+"\t"+player.getCurrentPosition()+"\t"+player.getMoney()+"\t"+player2.getMoney()+"\t"+player.getName()+" goes bankrupt\n");
                else if(player.getName().equals("Player 2"))
                    outputFile.write(player.getName()+"\t"+dice+"\t"+player.getCurrentPosition()+"\t"+player2.getMoney()+"\t"+player.getMoney()+"\t"+player.getName()+" goes bankrupt\n");
                player.setControlMoney(1);
            }
        }
        else if(getItem().equals("Your building loan matures - collect $150")){
            player.setMoney(player.getMoney()+150);
            banker.setMoney(banker.getMoney()-150);
            if(control==0){
                if(player.getName().equals("Player 1"))
                    outputFile.write(player.getName()+"\t"+dice+"\t"+player.getCurrentPosition()+"\t"+player.getMoney()+"\t"+player2.getMoney()+"\t"+player.getName()+" draw "+getItem()+"\n");
                else if(player.getName().equals("Player 2"))
                    outputFile.write(player.getName()+"\t"+dice+"\t"+player.getCurrentPosition()+"\t"+player2.getMoney()+"\t"+player.getMoney()+"\t"+player.getName()+" draw "+getItem()+"\n");
            }
            else if(control==1){
                if(player.getName().equals("Player 1"))
                    outputFile.write(player.getName()+"\t"+dice+"\t"+player.getCurrentPosition()+"\t"+player.getMoney()+"\t"+player2.getMoney()+"\t"+player.getName()+" draw Go back 3 spaces "+player.getName()+" draw "+getItem()+"\n");
                else if(player.getName().equals("Player 2"))
                    outputFile.write(player.getName()+"\t"+dice+"\t"+player.getCurrentPosition()+"\t"+player2.getMoney()+"\t"+player.getMoney()+"\t"+player.getName()+" draw Go back 3 spaces "+player.getName()+" draw "+getItem()+"\n");
            }
        }
        else if(getItem().equals("You have won a crossword competition - collect $100 ")){
            player.setMoney(player.getMoney()+100);
            banker.setMoney(banker.getMoney()-100);
            if(control==0){
                if(player.getName().equals("Player 1"))
                    outputFile.write(player.getName() + "\t" + dice + "\t" + player.getCurrentPosition() + "\t" + player.getMoney() + "\t" + player2.getMoney() + "\t" + player.getName() + " draw " + getItem()+"\n");
                else if(player.getName().equals("Player 2"))
                    outputFile.write(player.getName() + "\t" + dice + "\t" + player.getCurrentPosition() + "\t" + player2.getMoney() + "\t" + player.getMoney() + "\t" + player.getName() + " draw " + getItem()+"\n");
            }
            else if(control==1){
                if(player.getName().equals("Player 1"))
                    outputFile.write(player.getName() + "\t" + dice + "\t" + player.getCurrentPosition() + "\t" + player.getMoney() + "\t" + player2.getMoney() + "\t" + player.getName() + " draw Go back 3 spaces "+player.getName()+" draw " + getItem()+"\n");
                else if(player.getName().equals("Player 2"))
                    outputFile.write(player.getName() + "\t" + dice + "\t" + player.getCurrentPosition() + "\t" + player2.getMoney() + "\t" + player.getMoney() + "\t" + player.getName() + " draw Go back 3 spaces "+player.getName()+" draw " + getItem()+"\n");
            }
        }
    }
}