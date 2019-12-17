package test1;

import java.util.concurrent.TimeUnit;

public class Lock8 {
    public static void main(String[] args) {
        Phone p1 =new Phone();
        Phone p2 =new Phone();
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    p1.Eamil();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //p2.SmS();
                p2.SmS();
              //  p1.say();
            }
        }).start();

    }
}

class Phone{

    public static synchronized  void Eamil() throws  Exception{
        TimeUnit.SECONDS.sleep(3);
        System.out.println("Email*************");
    }

    public  synchronized  void SmS(){
        System.out.println("SmS*************");
    }

    public void say(){
        System.out.println("hello");
    }

}
