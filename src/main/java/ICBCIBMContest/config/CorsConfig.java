package ICBCIBMContest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 该类是为了解决本地开发时所带来的跨域调用 API 问题所实现的类
 * 根据 WebStorm 定义的服务器来适配
 *
 * @author Mr.Hu
 */
public class CorsConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("*")
                .allowedOrigins("http://localhost:63343", "null")
                .allowedMethods("POST", "GET")
                .allowCredentials(true);
    }

}
