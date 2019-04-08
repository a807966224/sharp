package com.jxbig.sharp.servicefeign.manager.service.errorimpl;

import com.jxbig.sharp.servicefeign.manager.service.TestService;
import org.springframework.stereotype.Component;

@Component
public class TestErrorServiceImpl implements TestService {
    @Override
    public String twoHi() {
        return "twoHi error.....";
    }
}
