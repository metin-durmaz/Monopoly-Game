import java.io.*;
public class CommunityChestList extends Card {
    public CommunityChestList(String item) {
        super(item);
    }
    public void action(User player,User player2,User banker,int dice,FileWriter outputFile,int control) throws IOException {
        if(getItem().equals("Advance to Go (Collect $200)")){
            player.setCurrentPosition(1);
            player.setMoney(player.getMoney()+200);
            banker.setMoney(banker.getMoney()-200);
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
        else if(getItem().equals("Bank error in your favor - collect $75")){
            player.setMoney(player.getMoney()+75);
            banker.setMoney(banker.getMoney()-75);
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
        else if(getItem().equals("Doctor's fees - Pay $50")){
            if(player.getMoney()>=50){
                player.setMoney(player.getMoney()-50);
                banker.setMoney(banker.getMoney()+50);
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
            else{
                if(player.getName().equals("Player 1"))
                    outputFile.write(player.getName() + "\t" + dice + "\t" + player.getCurrentPosition() + "\t" + player.getMoney() + "\t" + player2.getMoney() + "\t" + player.getName() + " goes bankrupt\n");
                else if(player.getName().equals("Player 2"))
                    outputFile.write(player.getName() + "\t" + dice + "\t" + player.getCurrentPosition() + "\t" + player2.getMoney() + "\t" + player.getMoney() + "\t" + player.getName() + " goes bankrupt\n");
                player.setControlMoney(1);
            }
        }
        else if(getItem().equals("It is your birthday Collect $10 from each player")){
            if(player2.getMoney()>=10){
                player.setMoney(player.getMoney()+10);
                player2.setMoney(player2.getMoney()-10);
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
            else{
                if(player.getName().equals("Player 1"))
                    outputFile.write(player.getName() + "\t" + dice + "\t" + player.getCurrentPosition() + "\t" + player.getMoney() + "\t" + player2.getMoney() + "\t" + player2.getName() + " goes bankrupt\n");
                else if(player.getName().equals("Player 2"))
                    outputFile.write(player.getName() + "\t" + dice + "\t" + player.getCurrentPosition() + "\t" + player2.getMoney() + "\t" + player.getMoney() + "\t" + player2.getName() + " goes bankrupt\n");
                player2.setControlMoney(1);
            }
        }
        else if(getItem().equals("Grand Opera Night - collect $50 from every player for opening night seats")){
            if(player2.getMoney()>=50){
                player.setMoney(player.getMoney()+50);
                player2.setMoney(player2.getMoney()-50);
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
            else{
                if(player.getName().equals("Player 1"))
                    outputFile.write(player.getName() + "\t" + dice + "\t" + player.getCurrentPosition() + "\t" + player.getMoney() + "\t" + player2.getMoney() + "\t" + player2.getName() + " goes bankrupt\n");
                else if(player.getName().equals("Player 2"))
                    outputFile.write(player.getName() + "\t" + dice + "\t" + player.getCurrentPosition() + "\t" + player2.getMoney() + "\t" + player.getMoney() + "\t" + player2.getName() + " goes bankrupt\n");
                player2.setControlMoney(1);
            }
        }
        else if(getItem().equals("Income Tax refund - collect $20")){
            player.setMoney(player.getMoney()+20);
            banker.setMoney(banker.getMoney()-20);
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
        else if(getItem().equals("Life Insurance Matures - collect $100")){
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
        else if(getItem().equals("Pay Hospital Fees of $100")){
            if(player.getMoney()>=100){
                player.setMoney(player.getMoney()-100);
                banker.setMoney(banker.getMoney()+100);
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
            else{
                if(player.getName().equals("Player 1"))
                    outputFile.write(player.getName() + "\t" + dice + "\t" + player.getCurrentPosition() + "\t" + player.getMoney() + "\t" + player2.getMoney() + "\t" + player.getName() + " goes bankrupt\n");
                else if(player.getName().equals("Player 2"))
                    outputFile.write(player.getName() + "\t" + dice + "\t" + player.getCurrentPosition() + "\t" + player2.getMoney() + "\t" + player.getMoney() + "\t" + player.getName() + " goes bankrupt\n");
                player.setControlMoney(1);
            }
        }
        else if(getItem().equals("Pay School Fees of $50")){
            if(player.getMoney()>=50){
                player.setMoney(player.getMoney()-50);
                banker.setMoney(banker.getMoney()+50);
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
            else{
                if(player.getName().equals("Player 1"))
                    outputFile.write(player.getName() + "\t" + dice + "\t" + player.getCurrentPosition() + "\t" + player.getMoney() + "\t" + player2.getMoney() + "\t" + player.getName() + " goes bankrupt\n");
                else if(player.getName().equals("Player 2"))
                    outputFile.write(player.getName() + "\t" + dice + "\t" + player.getCurrentPosition() + "\t" + player2.getMoney() + "\t" + player.getMoney() + "\t" + player.getName() + " goes bankrupt\n");
                player.setControlMoney(1);
            }
        }
        else if(getItem().equals("You inherit $100")){
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
        else if(getItem().equals("From sale of stock you get $50")){
            player.setMoney(player.getMoney()+50);
            banker.setMoney(banker.getMoney()-50);
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