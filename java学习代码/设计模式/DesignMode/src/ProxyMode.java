/**
 * @Description: 代理模式的Java实现(为其他对象提供一种代理以控制对这个对象的访问)
 * @author: xiaxinyu
 * @Email: xiaxinyuxy@163.com
 * @date: 2022年11月05日 19:27
 * @Copyright: 个人版权所有
 * @version: 1.0.0
 */

public class ProxyMode {
    public static void main(String[] args) {
        Proxy proxy = new Proxy();
        proxy.request();
    }
}

/**
 * 定义了RealSubject和Proxy的共有方法，这样就可以在任何使用RealSubject的地方使用Proxy
 */
interface Subject{
    void request();
}

/**
 * 定义Proxy所代表的真实实体
 */
class RealSubject implements Subject{
    @Override
    public void request() {
        System.out.println("真实的请求");
    }
}

class Proxy implements Subject{

    private RealSubject realSubject;

    @Override
    public void request() {
        if(realSubject == null) realSubject = new RealSubject();
        realSubject.request();
    }
}
