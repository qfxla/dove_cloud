package com.dove.authority.controller;

import com.dove.entity.GlobalException;
import com.dove.entity.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;

/**
 * @author run
 * @since 2021/3/22 20:37
 */
@RestController
@CrossOrigin
@RequestMapping("/dddd")
public class TestController extends BaseController {

    @GetMapping
    public Result test(){
        HashMap<String, Date> map = new HashMap<>();
        map.put("time",new Date());
        return Result.success().data(map);
    }

}
