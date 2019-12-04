package com.example.demo;

import com.example.demo.domain.Consumer;
import com.example.demo.service.ConsumerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    StringRedisTemplate stringRedisTemplate;  //操作k-v都是字符串的
    @Autowired
    RedisTemplate redisTemplate;  //k-v都是对象的

    @Autowired
    ConsumerService consumerService;
    Logger logger = LoggerFactory.getLogger(getClass());
    @Test
    public void test01(){
//        给redis中保存数据
        stringRedisTemplate.opsForValue().append("msg","hello");
        String msg = stringRedisTemplate.opsForValue().get("msg");
        System.out.println(msg);
        stringRedisTemplate.opsForList().leftPush("mylist","1");
        stringRedisTemplate.opsForList().leftPush("mylist","2");
        stringRedisTemplate.opsForList().leftPush("mylist","zyr");
        stringRedisTemplate.opsForList().leftPush("mylist","渔人");
        System.out.println(stringRedisTemplate.opsForList().leftPop("mylist"));
        System.out.println(stringRedisTemplate.opsForList().rightPop("mylist"));
    }

    @Test
    public void test04(){
        String ip="0:0:0:0:0:0:0:1";
        if(stringRedisTemplate.hasKey("IP:"+ip)){
            stringRedisTemplate.opsForValue().increment("IP:"+ip);
            if(Integer.parseInt(stringRedisTemplate.opsForValue().get("IP:"+ip))>=3){
                System.out.println("IP访问次数超过，防止洪水攻击！！！");
            }
        }else {
            stringRedisTemplate.opsForValue().set("IP:"+ip,"0");
        }

    }
    @Test
    public void test02(){
        List<Consumer> list=consumerService.allUser();
        redisTemplate.opsForValue().set("list",list);
    }

    @Test
    public void contextLoads() {
        //System.out.println();

        //日志的级别；
        //由低到高   trace<debug<info<warn<error
        //可以调整输出的日志级别；日志就只会在这个级别以以后的高级别生效
        logger.trace("这是trace日志...");
        logger.debug("这是debug日志...");
        //SpringBoot默认给我们使用的是info级别的，没有指定级别的就用SpringBoot默认规定的级别；root级别
        logger.info("这是info日志...");
        logger.warn("这是warn日志...");
        logger.error("这是error日志...");
    }
    @Test
    public void testFilePath(){
        File file=new File("");
        System.out.println(file.getAbsoluteFile());
    }
}
