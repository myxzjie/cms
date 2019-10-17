//package com.xzjie.cms.quartz;
//
//import org.quartz.spi.TriggerFiredBundle;
//import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
//import org.springframework.scheduling.quartz.AdaptableJobFactory;
//import org.springframework.stereotype.Component;
//
//@Component
//public class JobFactory extends AdaptableJobFactory {
//    /**
//     * AutowireCapableBeanFactory接口是BeanFactory的子类
//     * 可以连接和填充那些生命周期不被Spring管理的已存在的bean实例
//     */
//    private AutowireCapableBeanFactory factory;
//
//    public JobFactory(AutowireCapableBeanFactory factory) {
//        this.factory = factory;
//    }
//
//    /**
//     * 创建Job实例
//     */
//    @Override
//    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
//
//        // 实例化对象
//        Object job = super.createJobInstance(bundle);
//        // 进行注入（Spring管理该Bean）
//        factory.autowireBean(job);
//        //返回对象
//        return job;
//    }
//}
