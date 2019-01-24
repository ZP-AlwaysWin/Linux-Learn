package Test;


public class RedisLockTest {

    public static void main(String[] args) {
        for (int i = 0; i < 9; i++) {
            new Thread(new Runnable() {
                public void run() {
                    System.out.println();
                }
            }).start();
        }
    }
}