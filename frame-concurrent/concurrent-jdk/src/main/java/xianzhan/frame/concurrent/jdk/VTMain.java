package xianzhan.frame.concurrent.jdk;

public class VTMain {

    public static void main(String[] args) throws InterruptedException {
        Runnable r = () -> {
            Thread vt = Thread.currentThread();
            System.out.println(vt);
            System.out.printf("isVirtual: %s%n", vt.isVirtual());
        };

        Thread pt = Thread.ofPlatform().unstarted(r);
        pt.start();

        // 虚拟线程
        // 适合 IO 密集型应用
        Thread vt = Thread.ofVirtual().unstarted(r);
        vt.start();
        Thread.ofVirtual().start(r);
        Thread.startVirtualThread(r);

        pt.join();
        vt.join();
    }
}
