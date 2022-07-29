package threadlocal;

import java.util.UUID;

public class Test {
    public static void main(String[] args) {
        for(int i = 0;i < 10;i ++){
            int finalI = i;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    PutUUID putUUID = new PutUUID();
                    putUUID.put(finalI);
                }
            });
            thread.start();
        }
    }
}

class UUIDContextHolder{
    public static ThreadLocal<String> holder = ThreadLocal.withInitial(() -> new String());
}

class GetUUID{
    public void getUuid(){
       String uuid = UUIDContextHolder.holder.get();
       System.out.println(uuid);
    }
}

class UpdateUUID{
    public void update(){
        String uuid = UUIDContextHolder.holder.get();
        System.out.println("更新前 " + uuid);
        UUIDContextHolder.holder.remove();
        UUIDContextHolder.holder.set(uuid + " 最新 ");
        GetUUID getUUID = new GetUUID();
        getUUID.getUuid();
    }
}

class PutUUID{
    public void put(int num){
        String uuid = UUID.randomUUID().toString().replaceAll("-","") + "-" + num;
        UUIDContextHolder.holder.set(uuid);
        UpdateUUID updateUUID = new UpdateUUID();
        updateUUID.update();
    }
}