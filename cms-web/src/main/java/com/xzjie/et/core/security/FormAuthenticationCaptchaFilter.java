package com.xzjie.et.core.security;

import com.google.code.kaptcha.Constants;
import com.xzjie.core.utils.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author xzjie
 * @version V0.0.1
 * @className FormAuthenticationCaptchaFilter.java
 * @description TODO
 * @create 2016年6月3日 下午11:11:50
 */
public class FormAuthenticationCaptchaFilter extends FormAuthenticationFilter {

    private final Logger LOG = LogManager.getLogger(getClass());

    public static final String DEFAULT_CAPTCHA_PARAM = "captcha";
    public static final String DEFAULT_STYPE_PARAM = "stype";
    public static final String DEFAULT_IS_MOBILE_PARAM = "isMobile";
    public static final String DEFAULT_IS_CAPTCHA_PARAM = "isCaptcha";

    /**
     * 默认在 session 中存储的登录错误次数的名称
     */
    private static final String DEFAULT_LOGIN_INCORRECT_NUMBER_KEY_ATTRIBUTE = "incorrectNumber";

    private String captchaParam = DEFAULT_CAPTCHA_PARAM;
    private String stypeParam = DEFAULT_STYPE_PARAM;
    private String isMobileParam = DEFAULT_IS_MOBILE_PARAM;
    private String isCaptchaParam = DEFAULT_IS_CAPTCHA_PARAM;

    //在session中存储的登录错误次数名称
    private String loginIncorrectNumberKeyAttribute = DEFAULT_LOGIN_INCORRECT_NUMBER_KEY_ATTRIBUTE;
    //允许登录错误次数，当登录次数大于该数值时，会在页面中显示验证码
    private Integer allowIncorrectNumber = 1;
    private boolean isCaptchaVlaue = true;


    /**
     * 获得验证码
     *
     * @param request
     * @return
     */
    protected String getCaptcha(ServletRequest request) {

        return WebUtils.getCleanParam(request, getCaptchaParam());

    }

    /**
     * 获得用户类型
     *
     * @param request
     * @return
     */
    protected String getStype(ServletRequest request) {
        return WebUtils.getCleanParam(request, getStypeParam());
    }

    /**
     * 获得是否手机端
     *
     * @param request
     * @return
     */
    protected boolean isMobile(ServletRequest request) {
        return WebUtils.isTrue(request, getRememberMeParam());
    }

    protected boolean isCaptcha(ServletRequest request) {
        return isCaptchaVlaue;//WebUtils.isTrue(request, getIsCaptchaParam());
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return super.onAccessDenied(request, response);
    }

    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {

        String username = getUsername(request);
        String password = getPassword(request);
        String captcha = getCaptcha(request);
        String stype = getStype(request);
        boolean isMobile = isMobile(request);
        boolean isCaptcha = isCaptcha(request);
        boolean rememberMe = isRememberMe(request);
        String host = getHost(request);

        return new UsernamePasswordCaptchaToken(username, password == null ? null : password.toCharArray(), rememberMe, host, captcha, stype, isMobile, isCaptcha);

    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
                                     ServletResponse response) throws Exception {
        // TODO Auto-generated method stub
        Session session = subject.getSession();

        session.removeAttribute(getLoginIncorrectNumberKeyAttribute());
        //session.setAttribute(SessionVariable.DEFAULT_SESSION_KEY, subject.getPrincipal());
        return super.onLoginSuccess(token, subject, request, response);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        //return super.onLoginFailure(token, e, request, response);
        Session session = SecurityUtils.getSubject().getSession();
        Integer number = (Integer) session.getAttribute(getLoginIncorrectNumberKeyAttribute());
        session.setAttribute(getLoginIncorrectNumberKeyAttribute(), ++number);
        request.setAttribute(getFailureKeyAttribute(), e.getClass().getName());
        request.setAttribute("message", e.getMessage());
        return true;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        Session session = SecurityUtils.getSubject().getSession();
        //获取登录错误次数
        Integer number = (Integer) session.getAttribute(getLoginIncorrectNumberKeyAttribute());

        //首次登录，将该数量记录在session中
        if (number == null) {
            number = 1;
            session.setAttribute(getLoginIncorrectNumberKeyAttribute(), number);
        }
        //如果登录次数大于allowIncorrectNumber，需要判断验证码是否一致
        if (number > getAllowIncorrectNumber()) {
            //获取当前验证码
            String currentCaptcha = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
            //获取用户输入的验证码
            String submitCaptcha = getCaptcha(request);
            //如果验证码不匹配，登录失败
            if (StringUtils.isEmpty(submitCaptcha) || !StringUtils.equals(currentCaptcha, submitCaptcha.toLowerCase())) {
                return onLoginFailure(this.createToken(request, response), new CaptchaException(), request, response);
            }
        }
        return super.executeLogin(request, response);
    }

    /**
     * 重写父类方法，当登录失败将异常信息设置到 request 的 attribute 中
     */
    @Override
    protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
        if (ae instanceof CaptchaException) {
            request.setAttribute(getFailureKeyAttribute(), "验证码不正确");
        } else if (ae instanceof IncorrectCredentialsException) {
            request.setAttribute(getFailureKeyAttribute(), "用户名或密码错误");
        } else if (ae instanceof DisabledAccountException) {
            request.setAttribute(getFailureKeyAttribute(), "你的账户已被禁用");
        } else {
            request.setAttribute(getFailureKeyAttribute(), "服务器出现异常");
        }
    }

    public String getCaptchaParam() {
        return captchaParam;
    }

    public void setCaptchaParam(String captchaParam) {
        this.captchaParam = captchaParam;
    }

    public String getStypeParam() {
        return stypeParam;
    }

    public void setStypeParam(String stypeParam) {
        this.stypeParam = stypeParam;
    }

    public String getIsMobileParam() {
        return isMobileParam;
    }

    public void setIsMobileParam(String isMobileParam) {
        this.isMobileParam = isMobileParam;
    }

    public String getIsCaptchaParam() {
        return isCaptchaParam;
    }

    public void setIsCaptchaParam(String isCaptchaParam) {
        this.isCaptchaParam = isCaptchaParam;
    }

    public boolean isCaptchaVlaue() {
        return isCaptchaVlaue;
    }

    public void setCaptchaVlaue(boolean isCaptchaVlaue) {
        this.isCaptchaVlaue = isCaptchaVlaue;
    }

    /**
     * 获取登录错误次数的 key 属性名称
     *
     * @return String
     */
    public String getLoginIncorrectNumberKeyAttribute() {
        return loginIncorrectNumberKeyAttribute;
    }

    /**
     * 设置登录错误次数的 key 属性名称
     *
     * @param loginIncorrectNumberKeyAttribute 属性名称
     */
    public void setLoginIncorrectNumberKeyAttribute(String loginIncorrectNumberKeyAttribute) {
        this.loginIncorrectNumberKeyAttribute = loginIncorrectNumberKeyAttribute;
    }

    /**
     * 获取允许登录次数
     *
     * @return Integer
     */
    public Integer getAllowIncorrectNumber() {
        return allowIncorrectNumber;
    }

    /**
     * 设置允许登录次数，当登录次数大于该数值时，会在页面中显示验证码
     *
     * @param allowIncorrectNumber 允许登录次数
     */
    public void setAllowIncorrectNumber(Integer allowIncorrectNumber) {
        this.allowIncorrectNumber = allowIncorrectNumber;
    }

}
