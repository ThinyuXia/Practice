package threadlocal;

/**
 *使用ThreadLocal避免传递参数的麻烦
 */
public class ThreadLocalNormalUsage06 {
    public static void main(String[] args) {
        new Service1().process();
        for(int i = 0;i < 10;i ++){
            int finalI = i;
            new Thread(new Runnable(){
                @Override
                public void run() {
                    TokenContextHolder.holder.set("token" + finalI);
                    new TokenUtil().getTokenStr();
//                    System.out.println(111);
                }
            }).start();
        }
    }
}

class UserContextHolder{
    public static ThreadLocal<User> holder = new ThreadLocal<>();

}

class Service1{
    public void process(){
        User user = new User("超哥");
        UserContextHolder.holder.set(user);
        new Service2().process();
    }
}

class Service2{
    public void process(){
        User user = UserContextHolder.holder.get();
        System.out.println("Service2拿到用户名：" + user.name);
        UserContextHolder.holder.remove();
        User user1 = new User("王姐");
        UserContextHolder.holder.set(user1);
        new Service3().process();
    }
}
class Service3{
    public void process(){
        User user = UserContextHolder.holder.get();
        System.out.println("Service3拿到用户名：" + user.name);
    }
}
class User{
    String name;

    public User(String name) {
        this.name = name;
    }
}

class TokenContextHolder{
    public static ThreadLocal<String> holder = new ThreadLocal<>();
}

class GetToken{
    public void getToken(){
        String token = TokenContextHolder.holder.get();
        System.out.println("GetToken : " + token);
    }
}

class TokenUtil{
    public void getTokenStr(){
        String token = TokenContextHolder.holder.get();
        System.out.println("TokenUtil : " + token);
        new GetToken().getToken();
    }
}

