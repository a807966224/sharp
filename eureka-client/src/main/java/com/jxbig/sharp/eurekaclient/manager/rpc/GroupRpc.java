package com.jxbig.sharp.eurekaclient.manager.rpc;

import com.jxbig.sharp.common.result.ResultCode;
import com.jxbig.sharp.common.result.ResultResponse;
import com.jxbig.sharp.eurekaclient.manager.service.ProductService;
import com.jxbig.sharp.eurekaclient.manager.vo.GroupVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/${request.prefix}/group")
public class GroupRpc {

    @Autowired
    private ProductService productService;
    @Autowired
    private ApplicationContext context;

    @GetMapping("/queryGroupByPage")
    public Object queryGroupByPage(@RequestParam(required = false) String name,
                                   @RequestParam(defaultValue = "1") Integer offset,
                                   @RequestParam(defaultValue = "10") Integer limit) {
        return ResultResponse.writeOut(ResultCode.OK, productService.queryGroupByPage(name, offset, limit));
    }

    @GetMapping("/save")
    public Object save(@RequestParam String name) {
        GroupVO vo = context.getBean("groupVO", GroupVO.class);
        vo.setName(name);
        vo.setNum(0);
        productService.insertGroup(vo);
        return ResultResponse.writeOut(ResultCode.OK);
    }

    @GetMapping("/update")
    public Object update(@RequestParam String id, @RequestParam String name) {
        GroupVO vo = context.getBean("groupVO", GroupVO.class);
        vo.setName(name);
        vo.setId(id);
        productService.updateGroupById(vo);
        return ResultResponse.writeOut(ResultCode.OK);
    }

    @GetMapping("/delete")
    public Object delete(@RequestParam String id) {
        productService.deleteById(id);
        return ResultResponse.writeOut(ResultCode.OK);
    }

    @GetMapping("/findGroupById")
    public Object findGroupById(@RequestParam String id) {
        return ResultResponse.writeOut(ResultCode.OK, productService.findGroupById(id));
    }
}
