package com.xzjie.cms.store.oss.configure;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Vito
 * @since 2022/3/11 5:56 下午
 */
@Configuration
public class OssConfiguration {
    @Autowired
    private OssConfigurationProperties properties;

    @Bean
    public OSS ossClient() {
        ClientBuilderConfiguration configuration = new ClientBuilderConfiguration();
        configuration.setSupportCname(properties.isSupportCname());
        OSS ossClient = new OSSClientBuilder().build(properties.getEndpoint(), properties.getAccessKeyId(), properties.getAccessKeySecret(), configuration);
        return ossClient;
    }

//    public static Logger logger = LoggerFactory.getLogger(OssUtil.class);
//    public static final String ACCESS_KEY_ID = "ACCESS_KEY_ID";
//    public static final String ACCESS_KEY_SECRET = "ACCESS_KEY_SECRET";
//    public static final String SECURITY_TOKEN = "SECURITY_TOKEN";
//    public static final String EXPIRATION = "EXPIRATION";
//    //这里使用cn-hangzhou区域，具体根据实际情况而定
//    private static final String REGION = "cn-hangzhou";
//    private static final String STS_API_VERSION = "2015-04-01";
//
//    public static JSONObject getCredit(String userName, String roleArn, String accessKeyId, String accessKeySecret, String bucketName) throws ClientException {
//        // 用于阿里云后台审计使用的临时名称，可根据实际业务传输，具体内容不影响服务使用
//        String roleSessionName = userName;
//        //运行时的策略权限，这里将权限放到了最大，可根据实际情况而定。在运行时，实际权限为这里设置的权限和第一步中角色配置的策略权限的交集
//        JSONObject policyObject = new JSONObject();
//        policyObject.fluentPut("Version", "1");
//        List<JSONObject> statements = new ArrayList<>();
//        statements.add(new JSONObject().fluentPut("Effect", "Allow").fluentPut("Action", Arrays.asList("oss:PutObject")).fluentPut("Resource", Arrays.asList("acs:oss:*:*:" + bucketName, "acs:oss:*:*:" + bucketName + "/*")));
//        policyObject.fluentPut("Statement", statements);
//        logger.info("ali policy：{}", policyObject.toString());
//
//        //执行角色授权
//        IClientProfile profile = DefaultProfile.getProfile(REGION, accessKeyId, accessKeySecret);
//        DefaultAcsClient client = new DefaultAcsClient(profile);
//        final AssumeRoleRequest request = new AssumeRoleRequest();
//        request.setVersion(STS_API_VERSION);
//        request.setRoleArn(roleArn);
//        request.setRoleSessionName(roleSessionName);
//        request.setPolicy(policyObject.toJSONString());
//        //临时授权有效实践,从900到3600
//        request.setDurationSeconds(900L);
//        final AssumeRoleResponse response = client.getAcsResponse(request);
//
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put(ACCESS_KEY_ID, response.getCredentials().getAccessKeyId());
//        jsonObject.put(ACCESS_KEY_SECRET, response.getCredentials().getAccessKeySecret());
//        jsonObject.put(SECURITY_TOKEN, response.getCredentials().getBizSecurityToken());
//        jsonObject.put(EXPIRATION, response.getCredentials().getExpiration());
//        return jsonObject;
//    }

}
