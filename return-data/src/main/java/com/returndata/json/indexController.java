package com.returndata.json;

import com.returndata.object.SwiperData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class indexController {
    @GetMapping("/api/index/swiperdata")
    public SwiperData returnSwiperData(){
        SwiperData swiperData = new SwiperData();
        return swiperData;
    }
}
