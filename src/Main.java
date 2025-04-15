

public class Main {
    public static void main(String[] args) {
        Runnable runnable = new Runnable()
        {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                int count = 0;
                while (count < 10)
                    System.out.println(name + ": " + count++);
            }
        };

        Thread threadA = new Thread(runnable);
        Thread threadB = new Thread(runnable);
        System.out.println("Starting threads");
        threadA.start();
        threadB.start();
        try {
            threadA.join();
            threadB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End of Main.main()");
    }
}