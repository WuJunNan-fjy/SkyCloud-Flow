package com.wjn.common.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JacksonTypeHandler extends BaseTypeHandler<List<String>> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType) throws SQLException {
        try {
            // 将 List<String> 转换为 JSON 字符串并存储到数据库中
            String json = objectMapper.writeValueAsString(parameter);
            ps.setString(i, json);  // 将 JSON 字符串作为 VARCHAR 存储
        } catch (JsonProcessingException e) {
            throw new SQLException("Failed to convert List<String> to JSON", e);
        }
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String json = rs.getString(columnName);
        if (json != null) {
            try {
                // 从 JSON 字符串恢复 List<String>
                return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, String.class));
            } catch (IOException e) {
                throw new SQLException("Failed to convert JSON to List<String>", e);
            }
        }
        return null;
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String json = rs.getString(columnIndex);
        if (json != null) {
            try {
                return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, String.class));
            } catch (IOException e) {
                throw new SQLException("Failed to convert JSON to List<String>", e);
            }
        }
        return null;
    }

    @Override
    public List<String> getNullableResult(java.sql.CallableStatement cs, int columnIndex) throws SQLException {
        String json = cs.getString(columnIndex);
        if (json != null) {
            try {
                return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, String.class));
            } catch (IOException e) {
                throw new SQLException("Failed to convert JSON to List<String>", e);
            }
        }
        return null;
    }
}
