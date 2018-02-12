package com.brevitaz.RecruitmentManagementModule.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author dhvanan on 8/2/18 Thursday
 * @project RecruitmentManagementModule
 **/

@EnableConfigurationProperties
@ConfigurationProperties(prefix = "")
@Configuration("")
public class YAMLConfig {
    private String hostname;

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
}
