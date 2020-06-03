//package com.xzjie.cms.demo;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.concurrent.Callable;
//
//@Slf4j
//@Component
//public class DemoThread implements Callable<Boolean> {
//
//    String url = "jdbc:mysql://10.210.100.110:4000/test"; //数据库连接地址
//    String driver = "com.mysql.jdbc.Driver";
//    String user = "root";
//    String password = ""; // 密码
//
//    private Connection connection = null;
//
//    //当前是属于第几段线程
//    private int index;
//    //此段数据的集合
//    private List<Demo> list;
//
//    public DemoThread() {
//        this.initialize();
//    }
//
//    public DemoThread(int index, List<Demo> list) {
//        this();
//        this.index = index;
//        this.list = list;
//    }
//
//    private void initialize() {
//        try {
//            Class.forName(driver);
//            connection = DriverManager.getConnection(url, user, password);
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void close() {
//        try {
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public Boolean call() throws Exception {
//        System.err.println(String.format("此批数据的段数为:%s 此段数据的数据条数为:%s", index, list.size()));
//        Boolean result = Boolean.TRUE;
//
//        String sql = "INSERT INTO t_demo (id,name,age) VALUES(?,?,?)";
//
////        StringBuffer sqls = new StringBuffer();
//
//        connection.setAutoCommit(false);
//        PreparedStatement statement = connection.prepareStatement(sql);
//
//        if (null != list && list.size() > 0) {
//            for (Demo demo : list) {
//                try {
//                    statement.setLong(1, demo.getId());
//                    statement.setString(2, demo.getName());
//                    statement.setInt(3, demo.getAge());
//                    statement.addBatch();
//                } catch (Exception e) {
//                    result = Boolean.FALSE;
//                    continue;
//                }
//            }
//            statement.executeBatch();
//            connection.commit();
//        }
//
//        statement.close();
//        close();
//        System.err.println(String.format("此批数据的段数为:%s 结束"));
//        return result;
//    }
//}
