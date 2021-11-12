package com.niluogege.server.controller;

import com.niluogege.server.pojo.ChatMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Api(tags ="WsController")
@Controller
public class WsController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/ws/chat")
    @ApiOperation("handdleMsg")
    public void handdleMsg(ChatMsg chatMsg){

        simpMessagingTemplate.convertAndSendToUser("2","/queue/chat",chatMsg);
    }

}
