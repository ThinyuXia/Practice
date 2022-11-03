package com.xiaxinyu.concurrent;


import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test7")
/**
 * 两阶段终止模式
 */
public class Test7 {
    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTermination twoPhaseTermination = new TwoPhaseTermination();
        twoPhaseTermination.start();

        Thread.sleep(3500);
        twoPhaseTermination.stop();

    }
}

@Slf4j(topic = "c.TwoPhaseTermination")
class TwoPhaseTermination{
    private Thread monitor;

    /**
     * 启动监控线程
     */
    public void start(){
        monitor = new Thread(() -> {
            while(true){
                boolean interrupted = Thread.currentThread().isInterrupted();
                if(interrupted){
                    log.debug("料理后事");
                    break;
                }
                try {
                    Thread.sleep(1000); //情况1
                    log.debug("执行监控记录");  //情况2
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt(); //线程由sleep状态被打断后需要重新设置打断标记
                }
            }
        });

        monitor.start();
    }

    /**
     * 停止监控线程
     */
    public void stop(){
        monitor.interrupt();
    }
}
