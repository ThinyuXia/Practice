import java.util.*;

/**
 * @Description: 学习源码
 * @author: xiaxinyu
 * @Email: xiaxinyuxy@163.com
 * @date: 2022年11月11日 15:30
 * @Copyright: 个人版权所有
 * @version: 1.0.0
 */

public class Main {
    public static void main(String[] args) {
        List<String> a = new ArrayList<>();
        Map<String,String> map = new HashMap<>();
        a.add("1");
        a.add("2");
        a.add("3");
        System.out.println(a.size());
        Iterator<String> it = a.iterator();
        while(it.hasNext()){
            String temp = it.next();
            System.out.println("temp: " + temp);
            if("1".equals(temp)){
                a.remove(temp);
            }
        }
    }
}