package io.ziyi.spider.showmax;

import common.config.tools.config.ConfigTools3;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication(
        scanBasePackages = {
                "io.ziyi.spider.showmax"
        },
        exclude = {
                HibernateJpaAutoConfiguration.class
        }
)
public class Main {

    public static void main(String[] args) {
        ConfigTools3.load("config");
        SpringApplication.run(Main.class, args);
    }
}
