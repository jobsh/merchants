package com.merchant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动程序
 * 
 * @author hanke
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@ComponentScan(basePackages = {"com.merchant","org.n3r.idworker"})
public class MerchantApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(MerchantApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  merchant启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                " __  __ ______ _____   _____ _    _          _   _ _______\n" +
                "|  \\/  |  ____|  __ \\ / ____| |  | |   /\\   | \\ | |__   __|\n" +
                "| \\  / | |__  | |__) | |    | |__| |  /  \\  |  \\| |  | |\n" +
                "| |\\/| |  __| |  _  /| |    |  __  | / /\\ \\ | . ` |  | |\n" +
                "| |  | | |____| | \\ \\| |____| |  | |/ ____ \\| |\\  |  | |\n" +
                "|_|  |_|______|_|  \\_\\______|_|  |_/_/    \\_|_| \\_|  |_|\n");

    }
}
