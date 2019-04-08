package com.jxbig.sharp.servicefeign.manager.service;

import com.jxbig.sharp.servicefeign.manager.service.errorimpl.TestErrorServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "eureka-client1", fallback = TestErrorServiceImpl.class)
public interface TestService {

    @RequestMapping(value = "twoHi", method = RequestMethod.GET)
    String twoHi();

}
