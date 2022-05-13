package io.ziyi.spider.viu;

import common.config.tools.config.ConfigTools3;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication(
        scanBasePackages = {
                "io.ziyi.spider.viu"
        },
        exclude = {
                HibernateJpaAutoConfiguration.class
        }
)
public class ViuSpiderApplication {

    public static void main(String[] args) {
        ConfigTools3.load("config");
        SpringApplication.run(ViuSpiderApplication.class, args);
    }
}
