package abdallah.qasem.basketballplayers.view.test;


public class Counter {


    boolean isLocked = false;
    Thread lockedBy = null;
    int lockedCount = 0;

    public synchronized void lock()
            throws InterruptedException {
        Thread callingThread = Thread.currentThread();
        while (isLocked && lockedBy != callingThread) {
            wait();
        }
        isLocked = true;
        lockedCount++;
        lockedBy = callingThread;
    }


    public synchronized void unlock() {
        isLocked = false;
        notify();


    }

}
