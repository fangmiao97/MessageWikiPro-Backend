package com.messagewikipro.first.DAO;


import com.messagewikipro.first.DTO.OneTopicMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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
 * about message module
 */
@Component
public class MessageDAO implements Serializable{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 插入一个主题message
     * @param username
     * @param title
     * @param content
     * @return
     */
    public int submitMessage(String username, String title, String content){
        int i= 0;
        i = jdbcTemplate.update(
                "insert into MessageWikiPro.TopicMessage(username, title, content) " +
                        "values(\"" +username+ "\", \"" +title+ "\", \"" +content+ "\")"
        );
        return i;
    }

    /**
     * 得到所有主题，二期做得到分类的所有主题
     */
    public List<Map<String, String>> getAllTopicMessages(){
        String executeSql = "SELECT * FROM MessageWikiPro.TopicMessage";

        List list = (ArrayList) jdbcTemplate.query(executeSql,
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(java.sql.PreparedStatement preparedStatement) throws SQLException {

                    }
                },
                new RowMapperResultSetExtractor(new RowMapper() {
                    public Object mapRow(ResultSet rs, int index)
                            throws SQLException {
                        Map u = new HashMap(); //可以是自己的JavaBean值对象(简单Java对象POJO)
                        u.put("id", rs.getString("idTopicMessage"));
                        u.put("title", rs.getString("title"));
                        u.put("username", rs.getString("username"));
                        u.put("content", rs.getString("content"));
                        u.put("time", rs.getString("createTime"));
                        return u;
                    }
                }));

        return list;
    }

    /**
     * 插入一条评论
     * @param topicId
     * @param username
     * @param commmentContent
     * @return
     */
    public int submitComment(String topicId, String username, String commmentContent){
        int i= 0;
        i = jdbcTemplate.update(
                "insert into MessageWikiPro.TopicComments(topicid, username, commentcontent) " +
                        "values("+topicId+", \""+username+"\", \""+ commmentContent+"\")"
        );
        return i;
    }

    /**
     * 得到一条topic的主体信息
     * @param id
     * @return
     */
    public List<Map<String, String>> getTopicInfo(String id){

        String executeSql = "select * from MessageWikiPro.TopicMessage where idTopicMessage =" + id;

        List list = (ArrayList) jdbcTemplate.query(executeSql,
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(java.sql.PreparedStatement preparedStatement) throws SQLException {

                    }
                },
                new RowMapperResultSetExtractor(new RowMapper() {
                    public Object mapRow(ResultSet rs, int index)
                            throws SQLException {
                        Map u = new HashMap(); //可以是自己的JavaBean值对象(简单Java对象POJO)
                        u.put("id", rs.getString("idTopicMessage"));
                        u.put("title", rs.getString("title"));
                        u.put("username", rs.getString("username"));
                        u.put("content", rs.getString("content"));
                        u.put("time", rs.getString("createTime"));
                        return u;
                    }
                }));

        return list;
    }

    /**
     * 得到特定topic的评论内容
     * @param id
     * @return
     */
    public List<Map<String, String>> getCommentsOfTopic(String id){
        String executeSql = "select * from MessageWikiPro.TopicComments where topicid =" + id;

        List list = (ArrayList) jdbcTemplate.query(executeSql,
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(java.sql.PreparedStatement preparedStatement) throws SQLException {

                    }
                },
                new RowMapperResultSetExtractor(new RowMapper() {
                    public Object mapRow(ResultSet rs, int index)
                            throws SQLException {
                        Map u = new HashMap(); //可以是自己的JavaBean值对象(简单Java对象POJO)
                        u.put("id", rs.getString("idTopicComments"));
                        u.put("username", rs.getString("username"));
                        u.put("time", rs.getString("conmmenttime"));
                        u.put("content", rs.getString("commentcontent"));
                        return u;
                    }
                }));

        return list;
    }

    /**
     * 得到一个topic的评论、收藏、点赞数目
     * @param topicid
     * @return
     */
    public List<Map<String, String>> getTopicOtherData(String topicid){
        String executeSql = "SELECT * FROM MessageWikiPro.TopicCollectionCommentsInfo where topicid = " + topicid;

        List list = (ArrayList) jdbcTemplate.query(executeSql,
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(java.sql.PreparedStatement preparedStatement) throws SQLException {

                    }
                },
                new RowMapperResultSetExtractor(new RowMapper() {
                    public Object mapRow(ResultSet rs, int index)
                            throws SQLException {
                        Map u = new HashMap(); //可以是自己的JavaBean值对象(简单Java对象POJO)
                        u.put("collectionsNum", rs.getString("collectionsNum"));
                        u.put("commentsNum", rs.getString("commentsNum"));
                        u.put("likesNum", rs.getString("likesNum"));
                        return u;
                    }
                }));

        return list;
    }

    /**
     * 插入一个点赞记录
     * @param username
     * @param topicid
     * @return
     */
    public int likeTopic(String username, String topicid){

        int i= 0;
        i = jdbcTemplate.update(
                "insert into MessageWikiPro.TopicLike(username, topicid) values(\""+username+"\", "+topicid+")"
        );
        return i;
    }

    /**
     * 插入一个收藏记录
     * @param username
     * @param topicid
     * @return
     */
    public int collectTopic(String username, String topicid){
        int i= 0;
        i = jdbcTemplate.update(
                "insert into MessageWikiPro.TopicCollections(username, topicid) values(\""+username+"\", "+topicid+")"
        );
        return i;
    }

    /**
     * 得到点赞数最多的十个topic
     */
    public List<Map<String, String>> getMostLikedTopic(){

        String executeSql = "select topicid  from MessageWikiPro.TopicCollectionCommentsInfo order by likesNum desc limit 10";

        List list = (ArrayList) jdbcTemplate.query(executeSql,
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(java.sql.PreparedStatement preparedStatement) throws SQLException {

                    }
                },
                new RowMapperResultSetExtractor(new RowMapper() {
                    public Object mapRow(ResultSet rs, int index)
                            throws SQLException {
                        Map u = new HashMap(); //可以是自己的JavaBean值对象(简单Java对象POJO)
                        u.put("topicid", rs.getString("topicid"));
                        return u;
                    }
                }));

        return list;
    }


    /**
     * 得到十个评论最多的topic
     * @return
     */
    public List<Map<String, String>> getMostCommentTopic(){

        String executeSql = "select topicid  from MessageWikiPro.TopicCollectionCommentsInfo order by commentsNum desc limit 10";

        List list = (ArrayList) jdbcTemplate.query(executeSql,
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(java.sql.PreparedStatement preparedStatement) throws SQLException {

                    }
                },
                new RowMapperResultSetExtractor(new RowMapper() {
                    public Object mapRow(ResultSet rs, int index)
                            throws SQLException {
                        Map u = new HashMap(); //可以是自己的JavaBean值对象(简单Java对象POJO)
                        u.put("topicid", rs.getString("topicid"));
                        return u;
                    }
                }));

        return list;
    }

    /**
     * 得到某用户参与的topicid
     * @param username
     * @return
     */
    public List<Map<String, String>> getInvolvedTopics(String username){
        String executeSql = "select distinct topicid from MessageWikiPro.TopicComments where username = \""+username+"\"";

        List list = (ArrayList) jdbcTemplate.query(executeSql,
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(java.sql.PreparedStatement preparedStatement) throws SQLException {

                    }
                },
                new RowMapperResultSetExtractor(new RowMapper() {
                    public Object mapRow(ResultSet rs, int index)
                            throws SQLException {
                        Map u = new HashMap(); //可以是自己的JavaBean值对象(简单Java对象POJO)
                        u.put("topicid", rs.getString("topicid"));
                        return u;
                    }
                }));

        return list;
    }
}
