package com.wjn.common.config;

import com.wjn.common.utils.StringUtils;
import com.wjn.common.utils.YamlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 读取项目相关配置
 *
 * @author wjn
 */
@Component
@ConfigurationProperties(prefix = "sky")
public class Global
{
	private static final Logger log = LoggerFactory.getLogger(Global.class);

	private static String NAME = "application.yml";

	/**
     * 当前对象实例
     */
    private static Global global;

    /**
     * 保存全局属性值
     */
    private static Map<String, String> map = new HashMap<String, String>();

    private Global()
    {
    }

    /**
     * 静态工厂方法
     */
    public static synchronized Global getInstance()
    {
        if (global == null)
        {
            global = new Global();
        }
        return global;
    }

    /**
     * 获取生效文件的名称
     * @return
     */
    public static String getRealActive(){
        String active_value = NAME;
        String active="";
        try {
            active = String.valueOf(YamlUtil.getProperty(YamlUtil.loadYaml(NAME), "spring.profiles.active"));
            String[] activeArray = active.split(",");
            if(Arrays.asList(activeArray).contains("dev")) {
                active_value = "application-dev.yml";
            }else if(Arrays.asList(activeArray).contains("prod")) {
                active_value = "application-prod.yml";
            }else if(Arrays.asList(activeArray).contains("test")) {
                active_value = "application-test.yml";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return active_value;
    }

    /**
     * 获取当前环境
     * @return
     */
    public static String getActiveEnv(){
        String active="";
        String active_value = "";
        try {
            active = String.valueOf(YamlUtil.getProperty(YamlUtil.loadYaml(NAME), "spring.profiles.active"));
            String[] activeArray = active.split(",");
            if(Arrays.asList(activeArray).contains("dev")) {
                active_value = "dev";
            }else if(Arrays.asList(activeArray).contains("prod")) {
                active_value = "prod";
            }else if(Arrays.asList(activeArray).contains("test")) {
                active_value = "test";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return active_value;
    }

    /**
     * 获取配置
     */
    public static String getConfig(String key)
    {
        String value = map.get(key);
        if (value == null)
        {
            Map<?, ?> yamlMap = null;
            try
            {
                //yamlMap = YamlUtil.loadYaml(NAME);
                yamlMap = YamlUtil.loadYaml(getRealActive());
                value = String.valueOf(YamlUtil.getProperty(yamlMap, key));
                map.put(key, value != null ? value : StringUtils.EMPTY);
            }
            catch (FileNotFoundException e)
            {
                log.error("获取全局配置异常 {}", key);
            }
        }
        return value;
    }





    /** 项目名称 */
    private String name;

    /** 版本 */
    private String version;

    /** 版权年份 */
    private String copyrightYear;

    /** 实例演示开关 */
    private boolean demoEnabled;

    /** 上传路径 */
    private static String profile;

    /** 获取地址开关 */
    private static boolean addressEnabled;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public String getCopyrightYear()
    {
        return copyrightYear;
    }

    public void setCopyrightYear(String copyrightYear)
    {
        this.copyrightYear = copyrightYear;
    }

    public boolean isDemoEnabled()
    {
        return demoEnabled;
    }

    public void setDemoEnabled(boolean demoEnabled)
    {
        this.demoEnabled = demoEnabled;
    }

    public static String getProfile()
    {
        return profile;
    }
    @Value("${sky.profile}")
    public void setProfile(String profile)
    {
        Global.profile = profile;
    }

    public static boolean isAddressEnabled()
    {
        return addressEnabled;
    }

    public void setAddressEnabled(boolean addressEnabled)
    {
        Global.addressEnabled = addressEnabled;
    }

    /**
     * 获取头像上传路径
     */
    public static String getAvatarPath()
    {
        return getProfile() + "/publicfile/avatar";
    }

    /**
     * 获取下载路径
     */
    public static String getDownloadPath()
    {
        return getProfile() + "/download/";
    }

    /**
     * 获取上传路径
     */
    public static String getUploadPath()
    {
        return getProfile() + "/upload";
    }
}
