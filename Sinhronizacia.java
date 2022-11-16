
package sinhronizacia;

public class Sinhronizacia {

    static int x = 0;
    static final Object monitor = new Object();
    
    public static void main(String[] args) throws InterruptedException {     
       
        Test test = new Test();
        
        Runnable runner = new Runnable() {
            @Override
            public void run() {//метод ран в потоке под наблюдением монитора должен изменять переменную х
                for (int i = 0; i < 10; i++) {
                    synchronized (monitor) {
                        x++; //в этом месте может обратиться только один поток                       
                    }           
                    test.increment();
                }
               
            }
        };
        
        Thread th1 = new Thread(runner);
        Thread th2 = new Thread(runner);
        
        th1.start();
        th2.start();
        
        th1.join();
        th2.join();
        
        System.out.println("x = " + x);
        
    }
    
}

class Test{
    int x;
    synchronized void increment(){//синхронизация по методу
        x++;
    }
}
