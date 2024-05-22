package com.xinan.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "xinan.baidu")
@Data
public class BaiduProperties {
    private String ak;
}
