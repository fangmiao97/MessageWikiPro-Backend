package com.messagewikipro.first.controller;

import com.messagewikipro.first.DAO.MessageDAO;
import com.messagewikipro.first.DTO.CommentDTO;
import com.messagewikipro.first.DTO.OneTopicMessageDTO;
import com.oracle.tools.packager.mac.MacAppBundler;
import com.sun.org.apache.bcel.internal.generic.ALOAD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriBuilder;

import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * created by Chaoyue
 * submit and get message module
 */

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@EnableAutoConfiguration
public class SubmitMessageController {


    @Autowired
    private MessageDAO messageDAO;


    @GetMapping(value = "/submitTopic")
    public int submitTopicMessage(HttpServletRequest request) throws UnsupportedEncodingException{

        int i = 0;

        String username = request.getParameter("username");
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        i = messageDAO.submitMessage(username, title, content);

        return i;
    }

    /**
     * 时间顺序得到主题
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    @GetMapping(value = "/getAllTopics")
    public List<OneTopicMessageDTO> getAllTopicMessage(HttpServletRequest request) throws UnsupportedEncodingException{

        ArrayList<OneTopicMessageDTO> list = new ArrayList<OneTopicMessageDTO>();

        ArrayList<Map<String, String>> maps = (ArrayList<Map<String, String>>)messageDAO.getAllTopicMessages();

        for (int i = maps.size()-1; i >= 0; i--){
            Map<String, String> item = maps.get(i);
            String id = item.get("id");

            ArrayList<Map<String, String>> maps1 = (ArrayList<Map<String, String>>)messageDAO.getTopicOtherData(id);
            Map<String, String> otherData = maps1.get(0);
            String collectionsNum = otherData.get("collectionsNum");
            String commentsNum = otherData.get("commentsNum");
            String likesNum = otherData.get("likesNum");
            String title = URLDecoder.decode(item.get("title"),"UTF-8");
            String username = URLDecoder.decode(item.get("username"),"UTF-8");
            String time = item.get("time");
            String content = URLDecoder.decode(item.get("content"), "UTF-8");

            OneTopicMessageDTO oneTopicMessageDTO = new OneTopicMessageDTO();
            oneTopicMessageDTO.setId(id);
            oneTopicMessageDTO.setTitle(title);
            oneTopicMessageDTO.setUsername(username);
            oneTopicMessageDTO.setTime(time);
            oneTopicMessageDTO.setContent(content);
            oneTopicMessageDTO.setCollectionsNum(collectionsNum);
            oneTopicMessageDTO.setCommentsNum(commentsNum);
            oneTopicMessageDTO.setLikesNum(likesNum);

            list.add(oneTopicMessageDTO);
        }
        return list;
    }

    /**
     * 最热留言（10个）
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    @GetMapping(value = "/getmostlikedtopics")
    public List<OneTopicMessageDTO> getMostLikedTopics(HttpServletRequest request) throws UnsupportedEncodingException{

        ArrayList<OneTopicMessageDTO> list = new ArrayList<OneTopicMessageDTO>();

        ArrayList<Map<String, String>> maps = (ArrayList<Map<String, String>>)messageDAO.getMostLikedTopic();

        for (int i = 0; i <= maps.size()-1; i++){

            Map<String, String> item = maps.get(i);
            String topicid = item.get("topicid");
            ArrayList<Map<String, String>> topicInfoMap = (ArrayList<Map<String, String>>)messageDAO.getTopicInfo(topicid);
            ArrayList<Map<String, String>> topicOtherDataMap = (ArrayList<Map<String, String>>)messageDAO.getTopicOtherData(topicid);

            Map<String, String> topicInfo = topicInfoMap.get(0);
            Map<String, String> topicOtherData = topicOtherDataMap.get(0);

            String id = topicInfo.get("id");
            String title = URLDecoder.decode(topicInfo.get("title"), "UTF-8");
            String username = URLDecoder.decode(topicInfo.get("username"), "UTF-8");
            String time = topicInfo.get("time");
            String content = URLDecoder.decode(topicInfo.get("content"), "UTF-8");
            String collectionsNum = topicOtherData.get("collectionsNum");
            String commentsNum = topicOtherData.get("commentsNum");
            String likesNum = topicOtherData.get("likesNum");

            OneTopicMessageDTO oneTopicMessageDTO = new OneTopicMessageDTO();
            oneTopicMessageDTO.setId(id);
            oneTopicMessageDTO.setUsername(username);
            oneTopicMessageDTO.setTitle(title);
            oneTopicMessageDTO.setTime(time);
            oneTopicMessageDTO.setContent(content);
            oneTopicMessageDTO.setCollectionsNum(collectionsNum);
            oneTopicMessageDTO.setCommentsNum(commentsNum);
            oneTopicMessageDTO.setLikesNum(likesNum);

            list.add(oneTopicMessageDTO);
        }

        return list;
    }

    /**
     * 最热留言
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    @GetMapping(value = "/getmostcommenttopics")
    public List<OneTopicMessageDTO> getMostCommentTopics(HttpServletRequest request) throws UnsupportedEncodingException{

        ArrayList<OneTopicMessageDTO> list = new ArrayList<OneTopicMessageDTO>();

        ArrayList<Map<String, String>> maps = (ArrayList<Map<String, String>>)messageDAO.getMostCommentTopic();

        for (int i = 0; i <= maps.size()-1; i++){

            Map<String, String> item = maps.get(i);
            String topicid = item.get("topicid");
            ArrayList<Map<String, String>> topicInfoMap = (ArrayList<Map<String, String>>)messageDAO.getTopicInfo(topicid);
            ArrayList<Map<String, String>> topicOtherDataMap = (ArrayList<Map<String, String>>)messageDAO.getTopicOtherData(topicid);

            Map<String, String> topicInfo = topicInfoMap.get(0);
            Map<String, String> topicOtherData = topicOtherDataMap.get(0);

            String id = topicInfo.get("id");
            String title = URLDecoder.decode(topicInfo.get("title"), "UTF-8");
            String username = URLDecoder.decode(topicInfo.get("username"), "UTF-8");
            String time = topicInfo.get("time");
            String content = URLDecoder.decode(topicInfo.get("content"), "UTF-8");
            String collectionsNum = topicOtherData.get("collectionsNum");
            String commentsNum = topicOtherData.get("commentsNum");
            String likesNum = topicOtherData.get("likesNum");

            OneTopicMessageDTO oneTopicMessageDTO = new OneTopicMessageDTO();
            oneTopicMessageDTO.setId(id);
            oneTopicMessageDTO.setUsername(username);
            oneTopicMessageDTO.setTitle(title);
            oneTopicMessageDTO.setTime(time);
            oneTopicMessageDTO.setContent(content);
            oneTopicMessageDTO.setCollectionsNum(collectionsNum);
            oneTopicMessageDTO.setCommentsNum(commentsNum);
            oneTopicMessageDTO.setLikesNum(likesNum);

            list.add(oneTopicMessageDTO);
        }

        return list;
    }

    /**
     * 我参与的
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    @GetMapping(value = "/getinvolvedtopics")
    public List<OneTopicMessageDTO> getInvolvedTopics(HttpServletRequest request) throws UnsupportedEncodingException{

        String usernamec = request.getParameter("username");
        ArrayList<OneTopicMessageDTO> list = new ArrayList<OneTopicMessageDTO>();

        ArrayList<Map<String, String>> maps = (ArrayList<Map<String, String>>)messageDAO.getInvolvedTopics(usernamec);

        for (int i = 0; i <= maps.size()-1; i++){

            Map<String, String> item = maps.get(i);
            String topicid = item.get("topicid");
            ArrayList<Map<String, String>> topicInfoMap = (ArrayList<Map<String, String>>)messageDAO.getTopicInfo(topicid);
            ArrayList<Map<String, String>> topicOtherDataMap = (ArrayList<Map<String, String>>)messageDAO.getTopicOtherData(topicid);

            Map<String, String> topicInfo = topicInfoMap.get(0);
            Map<String, String> topicOtherData = topicOtherDataMap.get(0);

            String id = topicInfo.get("id");
            String title = URLDecoder.decode(topicInfo.get("title"), "UTF-8");
            String username = URLDecoder.decode(topicInfo.get("username"), "UTF-8");
            String time = topicInfo.get("time");
            String content = URLDecoder.decode(topicInfo.get("content"), "UTF-8");
            String collectionsNum = topicOtherData.get("collectionsNum");
            String commentsNum = topicOtherData.get("commentsNum");
            String likesNum = topicOtherData.get("likesNum");

            OneTopicMessageDTO oneTopicMessageDTO = new OneTopicMessageDTO();
            oneTopicMessageDTO.setId(id);
            oneTopicMessageDTO.setUsername(username);
            oneTopicMessageDTO.setTitle(title);
            oneTopicMessageDTO.setTime(time);
            oneTopicMessageDTO.setContent(content);
            oneTopicMessageDTO.setCollectionsNum(collectionsNum);
            oneTopicMessageDTO.setCommentsNum(commentsNum);
            oneTopicMessageDTO.setLikesNum(likesNum);

            list.add(oneTopicMessageDTO);
        }

        return list;
    }

    /**
     * 提交评论
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    @GetMapping(value = "/submitcomment")
    public int submitComment(HttpServletRequest request) throws UnsupportedEncodingException{

        int i = 0;
        String topicid = request.getParameter("topicid");
        String username = request.getParameter("username");
        String commentcontent = request.getParameter("content");

        i = messageDAO.submitComment(topicid, username, commentcontent);

        return i;
    }

    @GetMapping(value = "/getonetopicinfo")
    public List<OneTopicMessageDTO> getOneTopicInfo(HttpServletRequest request) throws UnsupportedEncodingException{

        String topicid = request.getParameter("topicid");

        ArrayList<OneTopicMessageDTO> list = new ArrayList<OneTopicMessageDTO>();

        ArrayList<Map<String, String>> maps = (ArrayList<Map<String, String>>)messageDAO.getTopicInfo(topicid);

        for (int i = maps.size()-1; i >= 0; i--){
            Map<String, String> item = maps.get(i);
            String id = item.get("id");
            String title = URLDecoder.decode(item.get("title"),"UTF-8");
            String username = URLDecoder.decode(item.get("username"),"UTF-8");
            String time = item.get("time");
            String content = URLDecoder.decode(item.get("content"), "UTF-8");

            OneTopicMessageDTO oneTopicMessageDTO = new OneTopicMessageDTO();
            oneTopicMessageDTO.setId(id);
            oneTopicMessageDTO.setTitle(title);
            oneTopicMessageDTO.setUsername(username);
            oneTopicMessageDTO.setTime(time);
            oneTopicMessageDTO.setContent(content);

            list.add(oneTopicMessageDTO);
        }
        return list;
    }

    /**
     * 得到一条topic的所有评论
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    @GetMapping(value = "/gettopiccomments")
    public List<CommentDTO> getTopicComments(HttpServletRequest request) throws UnsupportedEncodingException{

        String topicid = request.getParameter("topicid");

        ArrayList<CommentDTO> list = new ArrayList<CommentDTO>();

        ArrayList<Map<String, String>> maps = (ArrayList<Map<String, String>>)messageDAO.getCommentsOfTopic(topicid);

        for (int i = 0; i <= maps.size()-1; i++){
            Map<String, String> item = maps.get(i);
            String commentid = item.get("id");
            String username = URLDecoder.decode(item.get("username"),"UTF-8");
            String content = URLDecoder.decode(item.get("content"),"UTF-8");
            String commmetTime = item.get("time");

            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setCommentId(commentid);
            commentDTO.setUsername(username);
            commentDTO.setContent(content);
            commentDTO.setCommentTime(commmetTime);

            list.add(commentDTO);
        }

        return list;
    }


    /**
     * 点赞主题
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    @GetMapping(value = "/liketopic")
    public int likeTopic(HttpServletRequest request) throws UnsupportedEncodingException{

        int i = 0;

        String username = request.getParameter("username");
        String topicid = request.getParameter("topicid");

        i = messageDAO.likeTopic(username, topicid);

        return i;
    }

    /**
     * 收藏主题
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    @GetMapping(value = "/collecttopic")
    public int collectTopic(HttpServletRequest request) throws UnsupportedEncodingException{

        int i = 0;

        String username = request.getParameter("username");
        String topicid = request.getParameter("topicid");

        i = messageDAO.collectTopic(username, topicid);

        return i;
    }

}
