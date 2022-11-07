package go.nkrcd.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        CacheControl cacheControl = CacheControl.noCache().mustRevalidate().cachePrivate().sMaxAge(Duration.ZERO);
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:/data/images/")
                .setCacheControl(cacheControl);
    }
}
