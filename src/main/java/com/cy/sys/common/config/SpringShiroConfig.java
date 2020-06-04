package com.cy.sys.common.config;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;


/**
 * @Configuration 注解描述的类为一个配置对象,
 * 此对象也会交给spring管理
 */
@Configuration
public class SpringShiroConfig {
    /**
     * 配置shiro中的核心对象:安全管理器
     *
     * @return：SecurityManager
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager defaultwebSecurityManager = new DefaultWebSecurityManager();
        return defaultwebSecurityManager;
    }

    /**
     * 配置shiroFilterFactoryBean对象，
     * 基于此对象创建过滤工厂，通过过滤工厂创建过滤器，通过过滤器对请求进行过滤
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactory(SecurityManager securityManager) {
        ShiroFilterFactoryBean sfBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        sfBean.setSecurityManager(securityManager);
        //设置登陆界面url
        sfBean.setLoginUrl("/doLoginUI");
        //定义map指定请求过滤规则(哪些资源允许匿名访问,哪些必须认证访问)
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        //静态资源允许匿名访问:"anon"
        map.put("/bower_components/**", "anon");
        map.put("/build/**", "anon");
        map.put("/dist/**", "anon");
        map.put("/plugins/**", "anon");
        map.put("/user/doLogin", "anon");
        map.put("/doLogout", "logout");
        //除了匿名访问的资源,其它都要认证("authc")后访问
        map.put("/**","user");//记住密码需要user认证
        sfBean.setFilterChainDefinitionMap(map);
        return sfBean;
    }

    @Bean
    public SecurityManager securityManager(
            Realm realm,
            CacheManager cacheManager,
            RememberMeManager rememberManager,
            SessionManager sessionManager) {
        DefaultWebSecurityManager sManager = new DefaultWebSecurityManager();
        sManager.setRealm(realm);
        sManager.setCacheManager(cacheManager);
        sManager.setRememberMeManager(rememberManager);
        sManager.setSessionManager(sessionManager);
        return sManager;
    }

    //================================授权配置==============================================
    //shiro框架中的授权是基于sping中的AOP规范做的实现
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    //================================缓存配置========================================================
    @Bean
    public CacheManager shiroCacheManager() {
        return new MemoryConstrainedCacheManager();
    }

    @Bean
    public RememberMeManager rememberMeManager() {
        CookieRememberMeManager cManager= new CookieRememberMeManager();
        SimpleCookie cookie=new SimpleCookie("rememberMe");
        cookie.setMaxAge(7*24*60*60);
        cManager.setCookie(cookie);
        return cManager;
    }

    //================================Shiro会话时长配置============================================
    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager sManager= new DefaultWebSessionManager();
        sManager.setGlobalSessionTimeout(60*60*1000);
        return sManager;
    }


}
