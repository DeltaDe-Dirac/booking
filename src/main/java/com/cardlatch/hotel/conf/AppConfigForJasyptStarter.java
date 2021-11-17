package com.cardlatch.hotel.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:encrypted.properties")
public class AppConfigForJasyptStarter {

}
