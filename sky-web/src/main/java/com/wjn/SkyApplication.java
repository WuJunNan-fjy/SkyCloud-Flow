package com.wjn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 *
 * @author wjn
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class SkyApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(SkyApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  天云启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                "                     $$\\                 \n" +
                "                     $$ |                \n" +
                "            $$$$$$$\\ $$ |  $$\\ $$\\   $$\\ \n" +
                "           $$  _____|$$ | $$  |$$ |  $$ |\n" +
                "           \\$$$$$$\\  $$$$$$  / $$ |  $$ |\n" +
                "            \\____$$\\ $$  _$$<  $$ |  $$ |\n" +
                "           $$$$$$$  |$$ | \\$$\\ \\$$$$$$$ |\n" +
                "           \\_______/ \\__|  \\__| \\____$$ |\n" +
                "                               $$\\   $$ |\n" +
                "                               \\$$$$$$  |\n" +
                "                                \\______/ \n");
    }
}
