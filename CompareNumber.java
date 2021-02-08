import java.util.*;
public class CompareNumber implements Comparator<Property> {
    public int compare(Property o1, Property o2) {
        if(o1.getNumber()>o2.getNumber())
            return 1;
        else if(o1.getNumber()<o2.getNumber())
            return -1;
        else
            return 0;
    }
}