package com.jxbig.sharp.eurekaclient.manager.rpc;

import com.jxbig.sharp.common.result.ResultCode;
import com.jxbig.sharp.common.result.ResultResponse;
import com.jxbig.sharp.eurekaclient.manager.service.PersonService;
import com.jxbig.sharp.eurekaclient.manager.vo.PersonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/${request.prefix}/person")
public class PersonRpc {

    @Autowired
    private PersonService personService;
    @Autowired
    private ApplicationContext context;

    @GetMapping("/queryPersonByPage")
    public Object queryPersonByPage(@RequestParam String groupId,
                                   @RequestParam(defaultValue = "1") Integer offset,
                                   @RequestParam(defaultValue = "10") Integer limit) {
        return ResultResponse.writeOut(ResultCode.OK, personService.queryPersonByPage(groupId, offset, limit));
    }

    @GetMapping("save")
    public Object save(@RequestParam String groupId,
                        @RequestParam String nickName,
                       @RequestParam String sex) {
        PersonVO personVO = context.getBean("personVO", PersonVO.class);
        personVO.setGroupId(groupId);
        personVO.setNickName(nickName);
        personVO.setSex(sex);
        personService.insertPerson(personVO);
        return ResultResponse.writeOut(ResultCode.OK);
    }



}
