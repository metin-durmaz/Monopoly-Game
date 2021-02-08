import java.io.*;
import java.util.*;
public class Property implements Comparable<Property>{
    private int id;
    private String name;
    private double cost;
    private String owner;
    private int number;
    public Property(String name,double cost,String owner,int id,int number){
        this.name=name;
        this.cost=cost;
        this.owner=owner;
        this.id=id;
        this.number=number;
    }
    public String getName() {
        return name;
    }
    public double getCost() {
        return cost;
    }
    public String getOwner() {
        return owner;
    }
    public int getId() {
        return id;
    }
    public int getNumber() {
        return number;
    }
    public int compareTo(Property property) {
        if(this.getId()>property.getId())
            return 1;
        else if(this.getId()<property.getId())
            return -1;
        else
            return 0;
    }
    public int action(List<Property> propertyList,String name,String player1Name,String player2Name,User player1,User player2,
                       FileWriter outputFile,int dice,int cost,User banker,int control,int control2) throws IOException {
        return 0;
    }
}