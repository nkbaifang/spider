package io.ziyi.spider.showmax.config;

import common.config.tools.config.ConfigTools3;
import io.ziyi.spider.showmax.common.BaseComponent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class RedisConfiguration extends BaseComponent {

    @Bean(name = "redisConnectionFactory")
    public RedisConnectionFactory redisConnectionFactory() {
        String host = ConfigTools3.getString("spider.showmax.redis.host");
        int port = ConfigTools3.getInt("spider.showmax.redis.port", 6379);
        int database = ConfigTools3.getInt("spider.showmax.redis.database", 0);

        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(host, port);
        config.setDatabase(database);
        String username = ConfigTools3.getString("spider.showmax.redis.username");
        String password = ConfigTools3.getString("spider.showmax.redis.password");
        if ( StringUtils.isNotEmpty(username) ) {
            config.setUsername(username);
        }
        if ( StringUtils.isNotEmpty(password) ) {
            config.setPassword(password);
        }
        return new LettuceConnectionFactory(config);
    }

    @Bean(name = "redisTemplate")
    public StringRedisTemplate redisTemplate(@Autowired RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        template.setEnableTransactionSupport(true);
        template.afterPropertiesSet();
        return template;
    }
}
