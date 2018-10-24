package com.messagewikipro.first.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

import com.messagewikipro.first.DTO.*;
import com.messagewikipro.first.DAO.*;

/**
 * author: ChaoYue
 * fristTestController
 */

@RestController
public class firstTestController {

    @Autowired
    private dbTestDAO dbTestDAO;

    @GetMapping(value = "/first")
    public String firstTest(){
        return "hello messageWikiPro";
    }

    @GetMapping(value = "/dbTest")
    @ResponseBody
    public ArrayList<testDBDTO> getTestData(){

        ArrayList<testDBDTO> list = new ArrayList<>();
        ArrayList<Map<String, String>> maps = (ArrayList<Map<String, String>>)dbTestDAO.getTestData();

        for(int i = maps.size()-1; i >= 0; i--){
            Map<String, String> item = maps.get(i);
            String id = item.get("id");
            String name = item.get("name");

            testDBDTO testDBDTO = new testDBDTO();
            testDBDTO.setId(id);
            testDBDTO.setName(name);

            list.add(testDBDTO);
        }

        return list;
    }


}
