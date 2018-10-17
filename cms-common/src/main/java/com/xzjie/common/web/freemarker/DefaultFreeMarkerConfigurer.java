package com.xzjie.common.web.freemarker;

import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.TemplateException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * Created by xzjie on 2017/7/8.
 */

public class DefaultFreeMarkerConfigurer extends FreeMarkerConfigurer {
	
    private final Logger LOG = LogManager.getLogger(getClass());

    private final String DEFAULT_TAG_NAME ="et";

    private String defaultTagName = null;

    private AbstractDefaultTags defaultTags = new AbstractDefaultTags();

    @Override
    public void afterPropertiesSet() throws IOException, TemplateException {
        super.afterPropertiesSet();
        this.getConfiguration().setSharedVariable("shiro", new ShiroTags());
        this.getConfiguration().setSharedVariable(getDefaultTagName(),defaultTags);
    }

    @PostConstruct
    public void init(){

       // LOG.info(">>>>>>>==================================");

//        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
//        annotationConfigApplicationContext.register(NavsTags.class);
//        annotationConfigApplicationContext.refresh();
        //InjectClass injectClass = annotationConfigApplicationContext.getBean(InjectClass.class);
        //injectClass.print();

//        String packages = "com.xzjie.et.cms.web.tags,com.xzjie.et.ad.web.tags";
//        System.out.println("检测前的package: " + packages);
//        System.out.println("检测后的package: " + StringUtils.join(checkPackage(packages), ","));

    }

    public String getDefaultTagName() {
    	if(defaultTagName==null||defaultTagName ==""){
    		return DEFAULT_TAG_NAME;
    	}
		return defaultTagName;
	}

	public void setDefaultTagName(String defaultTagName) {
		this.defaultTagName = defaultTagName;
	}

	/**
     * 排重、检测package父子关系，避免多次扫描
     * @param scanPackages
     * @return
     */
//    public Set<String> checkPackage(String scanPackages){
//        if(StringUtils.isBlank(scanPackages)){
//            return Collections.EMPTY_SET;
//        }
//
//        Set<String> packages=new HashSet<String>();
//        //排重路径
//        Collections.addAll(packages, scanPackages.split(","));
//        for (String pInArr : packages.toArray(new String[packages.size()])) {
//            if (StringUtils.isBlank(pInArr) || pInArr.equals(".") || pInArr.startsWith(".")) {
//                continue;
//            }
//            if (pInArr.endsWith(".")) {
//                pInArr = pInArr.substring(0, pInArr.length() - 1);
//            }
//            Iterator<String> packageIte = packages.iterator();
//            boolean needAdd = true;
//            while (packageIte.hasNext()) {
//                String pack = packageIte.next();
//                LOG.info(pack);
//                if (pInArr.startsWith(pack + ".")) {
//                    //如果待加入的路径是已经加入的pack的子集，不加入
//                    needAdd = false;
//                } else if (pack.startsWith(pInArr + ".")) {
//                    //如果待加入的路径是已经加入的pack的父集，删除已加入的pack
//                    packageIte.remove();
//                }
//            }
//            if (needAdd) {
//                packages.add(pInArr);
//            }
//        }
//        return packages;
//    }

    //扫描  scanPackages 下的文件的匹配符
    protected static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";

    public AbstractDefaultTags getDefaultTags() {
        return defaultTags;
    }

    public void setDefaultTags(AbstractDefaultTags defaultTags) {
        this.defaultTags = defaultTags;
    }


    /**
     * 结合spring的类扫描方式
     * 根据需要扫描的包路径及相应的注解，获取最终测method集合
     * 仅返回public方法，如果方法是非public类型的，不会被返回
     * 可以扫描工程下的class文件及jar中的class文件
     *
     * @param scanPackages
     * @param annotation
     * @return
     */
//    public static Set<Method> findClassAnnotationMethods(String scanPackages, Class<? extends Annotation> annotation) {
//        //获取所有的类
//        Set<String> clazzSet = findPackageClass(scanPackages);
//        Set<Method> methods = new HashSet<Method>();
//        //遍历类，查询相应的annotation方法
//        for (String clazz : clazzSet) {
//            try {
//                Set<Method> ms = findAnnotationMethods(clazz, annotation);
//                if (ms != null) {
//                    methods.addAll(ms);
//                }
//            } catch (ClassNotFoundException ignore) {
//            }
//        }
//        return methods;
//    }
//
//    /**
//     * 根据扫描包的,查询下面的所有类
//     *
//     * @param scanPackages 扫描的package路径
//     * @return
//     */
//    public Set<String> findPackageClass(String scanPackages) {
//        if (StringUtils.isBlank(scanPackages)) {
//            return Collections.EMPTY_SET;
//        }
//        //验证及排重包路径,避免父子路径多次扫描
//        Set<String> packages = checkPackage(scanPackages);
//        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
//        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourcePatternResolver);
//        Set<String> clazzSet = new HashSet<String>();
//        for (String basePackage : packages) {
//            if (StringUtils.isBlank(basePackage)) {
//                continue;
//            }
//            String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
//                    org.springframework.util.ClassUtils.convertClassNameToResourcePath(SystemPropertyUtils.resolvePlaceholders(basePackage)) + "/" + DEFAULT_RESOURCE_PATTERN;
//            try {
//                Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);
//                for (Resource resource : resources) {
//                    //检查resource，这里的resource都是class
//                    String clazz = loadClassName(metadataReaderFactory, resource);
//                    clazzSet.add(clazz);
//                }
//            } catch (Exception e) {
//                LOG.error("获取包下面的类信息失败,package:" + basePackage, e);
//            }
//
//        }
//        return clazzSet;
//    }
//
//
//
//
//    /**
//     * 加载资源，根据resource获取className
//     *
//     * @param metadataReaderFactory spring中用来读取resource为class的工具
//     * @param resource              这里的资源就是一个Class
//     * @throws IOException
//     */
//    private static String loadClassName(MetadataReaderFactory metadataReaderFactory, Resource resource) throws IOException {
//        try {
//            if (resource.isReadable()) {
//                MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
//                if (metadataReader != null) {
//                    return metadataReader.getClassMetadata().getClassName();
//                }
//            }
//        } catch (Exception e) {
//            LOG.error("根据resource获取类名称失败", e);
//        }
//        return null;
//    }
}
