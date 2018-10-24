package com.messagewikipro.first.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import java.sql.SQLException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.sql.ResultSet;
import java.util.Map;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
/**
 * created by Chaoyue
 * register and login DAO
 */
@Component
public class RegisterLoginDAO implements Serializable {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 插入注册用户信息
     * @param username
     * @param password
     * @param email
     * @return
     */
    public int registerUserInfo(String username, String password, String email){

        int i = 0;
        i = jdbcTemplate.update(
                "insert into MessageWikiPro.UserInfo(username, passphare, email) " +
                        "values (\"" + username +"\", \""
                        + password +"\", \""
                        + email+"\")"
        );
        return i;//返回影响表的行数，此处调用成功返回1
    }

    /**
     * 查询密码
     * @param username
     * @return
     */
    public String getUserPassword(String username){

        String executeSql = "select passphare from MessageWikiPro.UserInfo where username = \""+ username +"\"";

        List list = (ArrayList) jdbcTemplate.query(executeSql,
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(java.sql.PreparedStatement preparedStatement) throws SQLException {

                    }
                },
                new RowMapperResultSetExtractor(new RowMapper() {
                    public Object mapRow(ResultSet rs, int index)
                            throws SQLException {
                        return rs.getString("passphare");
                    }
                }));

        String ret = new String();
        if(list.size() > 0)
        {
            ret = list.get(0).toString();
        }
        return ret;
    }

    /**
     * 查询用户是否存在，返回用户id
     * @param username
     * @return
     */
    public boolean userExist(String username){

        String executeSql = "select id from MessageWikiPro.UserInfo where username = \"" + username +"\"";
        List list = (ArrayList) jdbcTemplate.query(executeSql,
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(java.sql.PreparedStatement preparedStatement) throws SQLException {

                    }
                },
                new RowMapperResultSetExtractor(new RowMapper() {
                    public Object mapRow(ResultSet rs, int index)
                            throws SQLException {
                        return rs.getString("id");
                    }
                }));

        String ret = new String();
        if(list.size() > 0)
        {
            ret = list.get(0).toString();
            return true;
        }else
            return false;
    }

}
