package top.mowang.seckill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.mowang.seckill.service.KillServiceImpl;

import java.util.Random;

/**
 * Redis6-Study
 *
 * @author : Xuan Li
 * @website : https://mowangblog.top
 * @date : 2021/10/25 20:39
 **/
@RestController
public class KillController {
    @Autowired
    KillServiceImpl killService;
    @PostMapping("/kill")
    public String kill(String pid){
        int uid = new Random().nextInt(1000)+1000;
        if (killService.SecKill(uid+"", pid)) {
            return "success";
        }
        return "fail";
    }
}
