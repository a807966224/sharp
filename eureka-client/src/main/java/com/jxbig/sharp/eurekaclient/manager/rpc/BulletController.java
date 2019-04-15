package com.jxbig.sharp.eurekaclient.manager.rpc;

import com.jxbig.sharp.eurekaclient.manager.vo.BulletMessageVO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class BulletController {

    @MessageMapping("/chat")
    @SendTo("/toAll/bulletScreen")
    public String say(BulletMessageVO clientMessage) {
        String result=null;
        if (clientMessage!=null){
            result=clientMessage.getUsername()+":"+clientMessage.getMessage();
        }
        return result;
    }

}
