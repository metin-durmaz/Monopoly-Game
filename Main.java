import java.util.*;
import java.io.*;
import org.json.simple.parser.*;
import org.json.simple.*;
public class Main{
    public static void main(String[] args) throws IOException, ParseException {
        int gameOver=0;
        JSONParser parser = new JSONParser();
        JSONObject card = (JSONObject) parser.parse(new FileReader("list.json"));
        JSONArray chanceArray=(JSONArray)card.get("chanceList");
        JSONArray communityChestArray=(JSONArray)card.get("communityChestList");
        List<String> chanceList=new ArrayList<>();
        List<String> communityChestList=new ArrayList<>();
        for(Object i:chanceArray)
            chanceList.add((String)((JSONObject)i).get("item"));
        for(Object i:communityChestArray)
            communityChestList.add((String)((JSONObject)i).get("item"));
        List<Property> propertyList=new ArrayList<>();
        User player1=new Player("Player 1",15000);
        User player2=new Player("Player 2",15000);
        User banker=new Banker("Banker",100000);
        Scanner commandFile=new Scanner(new FileReader(args[0]));
        FileWriter outputFile=new FileWriter("output.txt");
        while (commandFile.hasNextLine()){
            String commandLine=commandFile.nextLine();
            if(commandLine.contains(";")){
                String playerName=commandLine.substring(0,commandLine.indexOf(";"));
                int dice=Integer.parseInt(commandLine.substring(commandLine.indexOf(";")+1));
                if(playerName.equals(player1.getName()))
                    player(player1,player2,banker,dice,propertyList,chanceList,communityChestList,outputFile,0);
                else if(playerName.equals(player2.getName()))
                    player(player2,player1,banker,dice,propertyList,chanceList,communityChestList,outputFile,0);
            }
            else if((!commandLine.contains(";")&&commandLine.equals("show()"))){ // show command
                show(player1,player2,banker,propertyList,outputFile);
                outputFile.write("-----------------------------------------------------------------------------------------------------------\n");
            }
            if((player1.getMoney()<0)||(player2.getMoney()<0)||(player1.getControlMoney()==1)||player2.getControlMoney()==1){ // game over
                show(player1,player2,banker,propertyList,outputFile);
                outputFile.write("-----------------------------------------------------------------------------------------------------------");
                gameOver=1;
                break;
            }
        }
        if(!commandFile.hasNextLine() && gameOver==0){ // end of file
            show(player1,player2,banker,propertyList,outputFile);
            outputFile.write("-----------------------------------------------------------------------------------------------------------");
        }
        commandFile.close();
        outputFile.close();
    }
    public static void player(User player1,User player2,User banker,int dice,List<Property> propertyList,List<String> chanceList,
                              List<String> communityChestList,FileWriter outputFile,int control) throws IOException, ParseException { // process function
        String player1Name=player1.getName();
        String player2Name=player2.getName();
        JSONParser parser = new JSONParser();
        JSONObject property = (JSONObject)parser.parse(new FileReader("property.json"));
        JSONArray land=(JSONArray) property.get("1");
        JSONArray railRoad=(JSONArray) property.get("2");
        JSONArray company=(JSONArray) property.get("3");
        if(player1.getJailFlag()==0){
            int firstPosition=player1.getCurrentPosition();
            if(control==0)
                player1.setCurrentPosition(player1.getCurrentPosition()+dice);
            int lastPosition=player1.getCurrentPosition();
            int isLand=0,isRailRoad=0,isCompany=0;
            String landName="",railRoadName="",companyName="";
            int landCost=0,railRoadCost=0,companyCost=0;
            int landId=0,railRoadId=0,companyId=0;
            for(Object i:land){
                if(String.valueOf(((JSONObject)i).get("id")).equals(String.valueOf(player1.getCurrentPosition()))){
                    isLand=1;
                    landName+=String.valueOf(((JSONObject)i).get("name"));
                    landCost=Integer.parseInt(String.valueOf(((JSONObject)i).get("cost")));
                    landId=Integer.parseInt(String.valueOf(((JSONObject)i).get("id")));
                    break;
                }
            }
            for(Object i:railRoad){
                if(String.valueOf(((JSONObject)i).get("id")).equals(String.valueOf(player1.getCurrentPosition()))){
                    isRailRoad=1;
                    railRoadName+=String.valueOf(((JSONObject)i).get("name"));
                    railRoadCost=Integer.parseInt(String.valueOf(((JSONObject)i).get("cost")));
                    railRoadId=Integer.parseInt(String.valueOf(((JSONObject)i).get("id")));
                    break;
                }
            }
            for(Object i:company){
                if(String.valueOf(((JSONObject)i).get("id")).equals(String.valueOf(player1.getCurrentPosition()))){
                    isCompany=1;
                    companyName+=String.valueOf(((JSONObject)i).get("name"));
                    companyCost=Integer.parseInt(String.valueOf(((JSONObject)i).get("cost")));
                    companyId=Integer.parseInt(String.valueOf(((JSONObject)i).get("id")));
                    break;
                }
            }
            if(isLand==1){ // land square
                go(firstPosition,lastPosition,player1,banker);
                Property landObj=new Land(landName,landCost,player1.getName(),landId,1);
                int turn=landObj.action(propertyList,landName,player1Name,player2Name,player1,player2,outputFile,dice,landCost,banker,control,0);
                if(turn==1)
                    propertyList.add(landObj);
            }
            else if(isCompany==1){ // company square
                go(firstPosition,lastPosition,player1,banker);
                Property companyObj=new Company(companyName,companyCost,player1.getName(),companyId,3);
                int turn=companyObj.action(propertyList,companyName,player1Name,player2Name,player1,player2,outputFile,dice,companyCost,banker,control,-1);
                if(turn==1)
                    propertyList.add(companyObj);
            }
            else if(isRailRoad==1){ // railroad square
                go(firstPosition,lastPosition,player1,banker);
                Property railRoadObj=new RailRoad(railRoadName,railRoadCost,player1.getName(),railRoadId,2);
                int turn=railRoadObj.action(propertyList,railRoadName,player1Name,player2Name,player1,player2,outputFile,dice,railRoadCost,banker,control,-1);
                if(turn==1)
                    propertyList.add(railRoadObj);
            }
            else if(player1.getCurrentPosition()==3||player1.getCurrentPosition()==18||player1.getCurrentPosition()==34){ // community chest square
                go(firstPosition,lastPosition,player1,banker);
                String chest=communityChestList.get(0);
                CommunityChestList action=new CommunityChestList(chest);
                action.action(player1,player2,banker,dice,outputFile,control);
                communityChestList.remove(0);
                communityChestList.add(chest);
            }
            else if(player1.getCurrentPosition()==5||player1.getCurrentPosition()==39){ // tax square
                go(firstPosition,lastPosition,player1,banker);
                if(player1.getMoney()>=100){
                    player1.setMoney(player1.getMoney()-100);
                    banker.setMoney(banker.getMoney()+100);
                    if(control==0){
                        if(player1.getName().equals("Player 1"))
                            outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+player1.getName()+" paid Tax\n");
                        else if(player1.getName().equals("Player 2"))
                            outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player2.getMoney()+"\t"+player1.getMoney()+"\t"+player1.getName()+" paid Tax\n");
                    }
                    else if(control==1){
                        if(player1.getName().equals("Player 1"))
                            outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+player1.getName()+" draw Go back 3 spaces "+player1.getName()+" paid Tax\n");
                        else if(player1.getName().equals("Player 2"))
                            outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player2.getMoney()+"\t"+player1.getMoney()+"\t"+player1.getName()+" draw Go back 3 spaces "+player1.getName()+" paid Tax\n");
                    }
                }
                else {
                    if(player1.getName().equals("Player 1"))
                        outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+player1.getName()+" goes bankrupt\n");
                    else if(player1.getName().equals("Player 2"))
                        outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player2.getMoney()+"\t"+player1.getMoney()+"\t"+player1.getName()+" goes bankrupt\n");
                    player1.setControlMoney(1);
                }
            }
            else if(player1.getCurrentPosition()==8||player1.getCurrentPosition()==23||player1.getCurrentPosition()==37){ // chance square
                String chance=chanceList.get(0);
                go(firstPosition,lastPosition,player1,banker);
                ChanceList action=new ChanceList(chance);
                action.action(player1,player2,banker,dice,chanceList,communityChestList,outputFile,land,propertyList,control);
                chanceList.remove(0);
                chanceList.add(chance);
            }
            else if(player1.getCurrentPosition()==11){ // jail sqaure
                player1.setJailFlag(1);
                if(control==0){
                    if(player1.getName().equals("Player 1"))
                        outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+player1.getName()+" went to jail\n");
                    else if(player1.getName().equals("Player 2"))
                        outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player2.getMoney()+"\t"+player1.getMoney()+"\t"+player1.getName()+" went to jail\n");
                }
                else if(control==1){
                    if(player1.getName().equals("Player 1"))
                        outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+player1.getName()+" draw Go back 3 spaces "+player1.getName()+" went to jail\n");
                    else if(player1.getName().equals("Player 2"))
                        outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player2.getMoney()+"\t"+player1.getMoney()+"\t"+player1.getName()+" draw Go back 3 spaces "+player1.getName()+" went to jail\n");
                }
            }
            else if(player1.getCurrentPosition()==21){ // free parking square
                go(firstPosition,lastPosition,player1,banker);
                if(control==0){
                    if(player1.getName().equals("Player 1"))
                        outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+player1.getName()+" is in Free Parking\n");
                    else if(player1.getName().equals("Player 2"))
                        outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player2.getMoney()+"\t"+player1.getMoney()+"\t"+player1.getName()+" is in Free Parking\n");
                }
                else if(control==1){
                    if(player1.getName().equals("Player 1"))
                        outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+player1.getName()+" draw Go back 3 spaces "+player1.getName()+" is in Free Parking\n");
                    else if(player1.getName().equals("Player 2"))
                        outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player2.getMoney()+"\t"+player1.getMoney()+"\t"+player1.getName()+" draw Go back 3 spaces "+player1.getName()+" is in Free Parking\n");
                }
            }
            else if(player1.getCurrentPosition()==31){ // go to jail square
                go(firstPosition,lastPosition,player1,banker);
                player1.setCurrentPosition(11);
                player1.setJailFlag(1);
                if(control==0){
                    if(player1.getName().equals("Player 1"))
                        outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+player1.getName()+" went to jail\n");
                    else if(player1.getName().equals("Player 2"))
                        outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player2.getMoney()+"\t"+player1.getMoney()+"\t"+player1.getName()+" went to jail\n");
                }
                else if(control==1){
                    if(player1.getName().equals("Player 1"))
                        outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+player1.getName()+" draw Go back 3 spaces "+player1.getName()+" went to jail\n");
                    else if(player1.getName().equals("Player 2"))
                        outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player2.getMoney()+"\t"+player1.getMoney()+"\t"+player1.getName()+" draw Go back 3 spaces "+player1.getName()+" went to jail\n");
                }
            }
            else if(player1.getCurrentPosition()==1){ // go square
                go(firstPosition,lastPosition,player1,banker);
                if(control==0){
                    if(player1.getName().equals("Player 1"))
                        outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+player1.getName()+" is in GO square\n");
                    else if(player1.getName().equals("Player 2"))
                        outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player2.getMoney()+"\t"+player1.getMoney()+"\t"+player1.getName()+" is in GO square\n");
                }
                else if(control==1){
                    if(player1.getName().equals("Player 1"))
                        outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+player1.getName()+" draw Go back 3 spaces\n");
                    else if(player1.getName().equals("Player 2"))
                        outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player2.getMoney()+"\t"+player1.getMoney()+"\t"+player1.getName()+" draw Go back 3 spaces\n");
                }
            }
        }
        else if(player1.getJailFlag()==1){ // in jail
            if(player1.getName().equals("Player 1"))
                outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player1.getMoney()+"\t"+player2.getMoney()+"\t"+player1.getName()+" in jail (count="+(player1.getJailCounter())+")\n");
            else if(player1.getName().equals("Player 2"))
                outputFile.write(player1.getName()+"\t"+dice+"\t"+player1.getCurrentPosition()+"\t"+player2.getMoney()+"\t"+player1.getMoney()+"\t"+player1.getName()+" in jail (count="+(player1.getJailCounter())+")\n");
            player1.setJailCounterd();
        }
    }
    public static void show(User player1,User player2,User banker,List<Property>propertyList,
                            FileWriter outputFile) throws IOException { // show function
        List<Property> player1Property=new ArrayList<>();
        List<Property> player2Property=new ArrayList<>();
        for(Property i:propertyList){
            if(i.getOwner().equals(player1.getName()))
                player1Property.add(i);
            else if(i.getOwner().equals(player2.getName()))
                player2Property.add(i);
        }
        Collections.sort(player1Property);
        Collections.sort(player1Property,new CompareNumber());
        Collections.sort(player2Property);
        Collections.sort(player2Property,new CompareNumber());
        outputFile.write("-----------------------------------------------------------------------------------------------------------\n");
        outputFile.write(player1.getName()+"\t"+player1.getMoney()+"\thave:");
        if(player1Property.size()>1){
            outputFile.write(" ");
            for(int i=0;i<player1Property.size()-1;i++)
                outputFile.write(player1Property.get(i).getName()+",");
            outputFile.write(player1Property.get(player1Property.size()-1).getName()+"\n");
        }
        else if(player1Property.size()==1)
            outputFile.write(" "+player1Property.get(0).getName()+"\n");
        else if(player1Property.size()==0)
            outputFile.write("\n");
        outputFile.write(player2.getName()+"\t"+player2.getMoney()+"\thave:");
        if(player2Property.size()>1){
            outputFile.write(" ");
            for(int i=0;i<player2Property.size()-1;i++)
                outputFile.write(player2Property.get(i).getName()+",");
            outputFile.write(player2Property.get(player2Property.size()-1).getName()+"\n");
        }
        else if(player2Property.size()==1)
            outputFile.write(" "+player2Property.get(0).getName()+"\n");
        else if(player2Property.size()==0)
            outputFile.write("\n");
        outputFile.write(banker.getName()+"\t"+banker.getMoney()+"\n");
        if(player1.getMoney()>player2.getMoney())
            outputFile.write("Winner "+player1.getName()+"\n");
        else if(player1.getMoney()<player2.getMoney())
            outputFile.write("Winner "+player2.getName()+"\n");
        else if(player1.getMoney()==player2.getMoney())
            outputFile.write("Sroceless\n");
    }
    public static void go(int firstPosition,int lastPosition,User player,User banker){ // passing go function
        if(lastPosition<firstPosition){
            player.setMoney(player.getMoney()+200);
            banker.setMoney(banker.getMoney()-200);
        }
    }
}