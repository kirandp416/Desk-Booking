package uk.ac.cf.nsa.team2.deskbookingapp.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.ac.cf.nsa.team2.deskbookingapp.utils.XssFilter;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: yiting zheng
 * @time: 2021/12/2
 */
@Configuration
public class XssFilterConfigurer {
    @Bean
    public FilterRegistrationBean xssFilterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new XssFilter());
        // Set the execution order. The smaller the value, the earlier the execution.
        filterRegistrationBean.setOrder(Integer.MAX_VALUE - 1);
        // Enable filter
        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.addUrlPatterns("/*");
        Map<String, String> initParameters = new HashMap();
        // initParameters
        initParameters.put("excludes", "/favicon.ico,/img/*,/js/*,/css/*");
        initParameters.put("isIncludeRichText", "true");
        filterRegistrationBean.setInitParameters(initParameters);
        return filterRegistrationBean;
    }
}
