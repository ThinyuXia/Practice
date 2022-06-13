import java.util.*;
public class Main{
    public static void main(String[] args){
        Set<String> set = new HashSet<>();
        for(int i = 100;i <= 3000000;i ++){
            if(Math.sqrt(i) == (int)Math.sqrt(i)) set.add(String.valueOf(i).substring(String.valueOf(i).length() - 2));
        }
        System.out.println(set.size());
//        for(String s : set) System.out.println(s);
    }
}