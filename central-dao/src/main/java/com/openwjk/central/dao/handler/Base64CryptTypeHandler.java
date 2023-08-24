package com.openwjk.central.dao.handler;

import org.apache.commons.codec.binary.Base64;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.nio.charset.StandardCharsets;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/24 17:27
 */
public class Base64CryptTypeHandler implements TypeHandler<String> {
    @Override
    public void setParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        if (Objects.nonNull(parameter))
            ps.setString(i, Base64.encodeBase64String(parameter.getBytes(StandardCharsets.UTF_8)));
        else
            ps.setNull(i, jdbcType.TYPE_CODE);
    }

    @Override
    public String getResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        if (Objects.nonNull(value))
            return new String(Base64.decodeBase64(value), StandardCharsets.UTF_8);
        return value;
    }

    @Override
    public String getResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        if (Objects.nonNull(value))
            return new String(Base64.decodeBase64(value), StandardCharsets.UTF_8);
        return value;
    }

    @Override
    public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        if (Objects.nonNull(value))
            return new String(Base64.decodeBase64(value), StandardCharsets.UTF_8);
        return value;
    }
}
