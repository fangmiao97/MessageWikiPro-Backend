package com.messagewikipro.first.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.sql.SQLException;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.Map;

/**
 * created by Chaoyue
 */

@Component
public class dbTestDAO implements Serializable{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, String>> getTestData(){

        String executeSql = "SELECT * FROM MessageWikiPro.testTable limit 10";

        List dataList = (ArrayList) jdbcTemplate.query(executeSql,
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(java.sql.PreparedStatement preparedStatement) throws SQLException {
                    }
                },
                new RowMapperResultSetExtractor(new RowMapper() {
                    public Object mapRow(ResultSet rs, int index)
                            throws SQLException {
                        Map u = new HashMap();
                        u.put("id", rs.getString("id"));
                        u.put("name", rs.getString("name"));
                        return u;
                    }
                }));

        return dataList;
    }
}
