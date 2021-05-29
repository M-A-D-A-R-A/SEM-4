import java.util.*;

public class lab8 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Buffer Size of Queue : ");
        int Qsize = sc.nextInt();
        System.out.println("Enter the Number of Producers :");
        int Pnum = sc.nextInt();
        System.out.println("Enter the Number of Consumers :");
        int Cnum = sc.nextInt();
        System.out.println("\n\n Output :- \n");
        sc.close();
        // Creation of queue (buffer)
        // that will be shared between consumer and producer
        Vector<Integer> QueueShared = new Vector<Integer>();
        // thread creation
        Thread producerThread = new Thread(new Producer(QueueShared, Qsize, Pnum), "Producer");
        Thread consumerThread = new Thread(new Consumer(QueueShared, Qsize, Cnum), "Consumer");
        // thread start
        producerThread.start();
        consumerThread.start();
    }
}

class Producer implements Runnable {
    private final Vector<Integer> QueueShared;
    private final int Qsize;
    private final int Pnum;

    public Producer(Vector<Integer> QueueShared, int Qsize, int Pnum) {
        this.QueueShared = QueueShared;
        this.Qsize = Qsize;
        this.Pnum = Pnum;
    }

    @Override
    public void run() {
        int X = 1;
        while (X <= Pnum) {
            for (int i = 0; i < Qsize; i++) {
                System.out.println("Producer " + X + " produced item : " + i);
                try {
                    produce(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            X++;
        }
    }

    private void produce(int i) throws InterruptedException {
        // Wait if the queue is found FULL
        while (QueueShared.size() == Qsize) {
            synchronized (QueueShared) {
                QueueShared.wait();
            }
        }
        // Producing the element and notify consumers
        synchronized (QueueShared) {
            QueueShared.add(i);
            QueueShared.notifyAll();
        }
        ;
    }
}

class Consumer implements Runnable {
    private final Vector<Integer> QueueShared;
    private final int Qsize;
    private final int Cnum;

    public Consumer(Vector<Integer> QueueShared, int Qsize, int Cnum) {
        this.QueueShared = QueueShared;
        this.Qsize = Qsize;
        this.Cnum = Cnum;
    }

    @Override
    public void run() {
        int Y = 1;
        while (Y <= Cnum) {
            for (int i = 0; i < Qsize; i++) {
                System.out.println("Consumer " + Y + " consumed item : " + i);
                try {
                    consume();
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Y++;
        }
    }

    private int consume() throws InterruptedException {
        // Wait if the queue is foumd EMPTY
        while (QueueShared.isEmpty()) {
            synchronized (QueueShared) {
                QueueShared.wait();
            }
        }
        // else consume the element and notify the waiting producer
        synchronized (QueueShared) {
            QueueShared.notifyAll();
            return (Integer) QueueShared.remove(0);
        }
    }
}
