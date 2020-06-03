//package com.xzjie.cms.demo;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.Future;
//
//@Service
//public class DemoService {
//
//    @Qualifier("taskExecutor")
//    @Autowired
//    private ThreadPoolTaskExecutor taskExecutor;
//    private DemoThread bookThread;
//    private int size = 260000;
//
//    public void ReceivePsrJobRun() {
//
//
////        bookList = getBookList();
//        //接收集合各段的 执行的返回结果
//        List<Future<Boolean>> futureList = new ArrayList<>();
//        //集合总条数
////        int size = bookList.size();
//        //将集合切分的段数(2*CPU的核心数)
//        int sunSum = 20 * Runtime.getRuntime().availableProcessors();
//        int listStart, listEnd;
//        //当总条数不足sunSum条时 用总条数 当做线程切分值
////        if (sunSum > size) {
////            sunSum = size;
////        }
//
//        //定义子线程
//        /*BookThread bookThread;*/
//
//        //将list 切分多份 多线程执行
//        for (int i = 0; i < sunSum; i++) {
////            //计算切割  开始和结束
////            listStart = size / sunSum * i;
////            listEnd = size / sunSum * (i + 1);
////            //最后一段线程会 出现与其他线程不等的情况
////            if (i == sunSum - 1) {
////                listEnd = size;
////            }
////            //线程切断**/
////            List<Demo> sunList = bookList.subList(listStart, listEnd);
//
//            List<Demo> list = new ArrayList<>();
//            for (int j = 10000; j < size; j++) {
//
//                long id = ((i + 1) * size) + (j + 1);
//                Demo demo = new Demo(id, "demo" + id, 10);
//                list.add(demo);
//            }
//
//            //子线程初始化
//            bookThread = new DemoThread(i, list);
//            //多线程执行
//            futureList.add(taskExecutor.submit(bookThread));
//        }
//        System.out.println("----------1111111111");
//
//        //对各个线程段结果进行解析
//        for (Future<Boolean> future : futureList) {
//            try {
//                Boolean str;
//                if (null != future) {
//                    str = future.get();
//                    System.out.println("##############current thread id =" + Thread.currentThread().getName() + ",result=" + str);
//                } else {
//                    System.err.println("失败");
//                }
//            } catch (InterruptedException | ExecutionException e) {
//
//                e.printStackTrace();
//
//            }
//        }
//        System.out.println("----------2222");
//
//
//    }
//
//}
