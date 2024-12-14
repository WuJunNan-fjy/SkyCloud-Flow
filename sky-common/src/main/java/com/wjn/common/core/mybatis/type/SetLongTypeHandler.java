package com.wjn.common.core.mybatis.type;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;

public class SetLongTypeHandler extends BaseTypeHandler<Set<Long>> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Set<Long> parameter, JdbcType jdbcType) throws SQLException {
        try {
            // 将 Set<Long> 转换为 JSON 字符串
            String json = objectMapper.writeValueAsString(parameter);
            ps.setString(i, json);  // 将 JSON 字符串插入数据库
        } catch (Exception e) {
            throw new SQLException("Error converting Set<Long> to JSON", e);
        }
    }

    @Override
    public Set<Long> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String json = rs.getString(columnName);
        try {
            // 将 JSON 字符串反序列化为 Set<Long>
            return objectMapper.readValue(json, HashSet.class);
        } catch (Exception e) {
            throw new SQLException("Error converting JSON to Set<Long>", e);
        }
    }

    @Override
    public Set<Long> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String json = rs.getString(columnIndex);
        try {
            // 将 JSON 字符串反序列化为 Set<Long>
            return objectMapper.readValue(json, HashSet.class);
        } catch (Exception e) {
            throw new SQLException("Error converting JSON to Set<Long>", e);
        }
    }

    @Override
    public Set<Long> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return Collections.emptySet();
    }
}
