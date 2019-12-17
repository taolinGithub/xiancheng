package test3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test1 {
    public static void main(String[] args) {
        Resource resource =new Resource();

            /*new Thread(()->{
                try {
                    for (int i = 0; i <10 ; i++) {
                        resource.print5();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"A").start();


            new Thread(()->{
                try {
                    for (int i = 0; i <10 ; i++) {
                        resource.print10();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"B").start();


            new Thread(()->{
                try {
                    for (int i = 0; i <10 ; i++) {
                        resource.print15();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"C").start();*/
        for(int i=0;i<3;i++){
            new Thread(()->{
                try {
                    for (int j = 0; j <10 ; j++) {
                        resource.print();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },String.valueOf((char)(65+i))).start();
        }
    }
}

class Resource{

    private char flag='A';

    private Lock lock =new ReentrantLock();

    private Condition c =lock.newCondition();
//    private Condition c2 =lock.newCondition();
//    private Condition c3 =lock.newCondition();

//   public void print5() throws InterruptedException {
//        lock.lock();
//        try{
//            while(flag!=1){
//                c1.await();
//            }
//
//            for (int i = 1; i <=5 ; i++) {
//                System.out.println(Thread.currentThread().getName()+i);
//            }
//            flag=2;
//            c2.signal();
//        }finally{
//         lock.unlock();
//        }
//    }
//
//    public void print10() throws InterruptedException {
//        lock.lock();
//        try{
//            while(flag!=2){
//                c2.await();
//            }
//            for (int i = 1; i <=10 ; i++) {
//                System.out.println(Thread.currentThread().getName()+i);
//            }
//            flag=3;
//            c3.signal();
//        }finally{
//            lock.unlock();
//        }
//    }
//
//    public void print15() throws InterruptedException {
//        lock.lock();
//        try{
//            while(flag!=3){
//                c3.await();
//            }
//            for (int i = 1; i <=15 ; i++) {
//                System.out.println(Thread.currentThread().getName()+i);
//            }
//            flag=1;
//            c1.signal();
//        }finally{
//            lock.unlock();
//        }
//    }

    public void print() throws InterruptedException {
            String num =Thread.currentThread().getName();
            lock.lock();
            try {
                while(!String.valueOf(flag).equals(num)){
                  c.await();
                    }
                for (int i = 1; i <= (flag-64)*5; i++) {
                    System.out.println(Thread.currentThread().getName() + i);
                }
               if(flag=='C'){
                   flag='A';
               }else{
                   flag=(char)(flag+1);
               }
               c.signalAll();
            } finally {
                lock.unlock();
            }
   }
}