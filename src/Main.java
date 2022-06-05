import java.lang.reflect.Constructor;
import java.lang.reflect.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.*;
import java.util.stream.Stream;

public class Main{

    public static int bfs(String start){
        String end = "12345678x";
        String[] q = new String[100010];
        int[] dx = {-1,0,1,0};
        int[] dy = {0,1,0,-1};
        Map<String,Integer> dist = new HashMap<>();
        int hh = 0,tt = 0;
        q[0] = start;
        dist.put(start,0);

        while(hh <= tt){
            String t = q[hh ++];
            char[] tArr = t.toCharArray();
            int distance = dist.getOrDefault(t,0);
            if(t.equals(end)) return distance;

            int k = t.indexOf('x');
            if(k == -1){
                System.out.println(t);
                return 0;
            }
            int x = k / 3,y = k % 3;
            for(int i = 0;i < 4;i ++){
                int nx = x + dx[i], ny = y + dy[i];
                if (nx >= 0 && nx < 3 && ny >= 0 && ny < 3){
                    char tc = tArr[nx * 3 + ny];
                    tArr[nx * 3 + ny] = tArr[k];
                    tArr[k] = tc;
                    t = new String(tArr);
                    if(dist.getOrDefault(t,0) == 0)
                    {
                        dist.put(t,distance + 1);
                        q[++ tt] = t;
                    }
                    tc = tArr[nx * 3 + ny];
                    tArr[nx * 3 + ny] = tArr[k];
                    tArr[k] = tc;
                    t = new String(tArr);
                }
            }
        }
        return -1;
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String s = "";
        for(int i = 0;i < 9;i ++) s += in.next();
        // System.out.println(s);
        System.out.println(bfs(s));
    }
}