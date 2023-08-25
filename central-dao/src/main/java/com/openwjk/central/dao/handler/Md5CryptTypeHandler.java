package com.openwjk.central.dao.handler;

import com.openwjk.commons.utils.EncryptUtil;
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
public class Md5CryptTypeHandler implements TypeHandler<String> {
    @Override
    public void setParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        if (Objects.nonNull(parameter))
            ps.setString(i, EncryptUtil.md5(parameter));
        else
            ps.setNull(i, jdbcType.TYPE_CODE);
    }

    @Override
    public String getResult(ResultSet rs, String columnName) throws SQLException {
        return rs.getString(columnName);
    }

    @Override
    public String getResult(ResultSet rs, int columnIndex) throws SQLException {
        return rs.getString(columnIndex);
    }

    @Override
    public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return cs.getString(columnIndex);
    }
}
