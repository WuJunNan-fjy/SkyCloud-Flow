package com.wjn.framework.aspectj;

import com.wjn.common.annotation.TableField;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Aspect
@Component
@Slf4j
public class TableFieldAspect {

    // 拦截所有数据库操作方法，如 insert, update
    @Before("execution(* com.example.mapper.*.insert*(..)) || execution(* com.example.mapper.*.update*(..))")
    public void handleTableFieldAnnotation() throws Exception {
        // 获取方法中的实体参数
        Object[] args = getMethodArguments();

        for (Object arg : args) {
            if (arg == null) continue;

            Class<?> clazz = arg.getClass();

            // 遍历实体类的字段
            for (Field field : clazz.getDeclaredFields()) {
                TableField tableField = field.getAnnotation(TableField.class);

                if (tableField != null && tableField.exist()) {
                    // 如果存在 @TableField 注解，获取映射的数据库字段
                    String dbField = tableField.value();

                    // 这里可以根据需求生成 SQL 或处理字段值
                    // 例如: 打印字段名和字段值
                    field.setAccessible(true);
                    Object value = field.get(arg);
                    log.info("处理字段: " + dbField + " -> " + value);

                    // 你也可以根据注解的配置处理字段值或生成 SQL 等
                }
            }
        }
    }

    private Object[] getMethodArguments() {
        // 获取当前方法的参数
        // 这里可以根据具体场景调整，如获取 Mapper 中的参数等
        return new Object[]{};
    }
}
