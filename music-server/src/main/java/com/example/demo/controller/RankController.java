package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.Rank;
import com.example.demo.service.impl.RankServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class RankController {

    @Autowired
    private RankServiceImpl rankService;

//    提交评分
    @RequestMapping(value = "/api/pushRank", method = RequestMethod.POST)
    public Object signup(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String songListId = req.getParameter("songListId").trim();
        String consumerId = req.getParameter("consumerId").trim();
        String score = req.getParameter("score").trim();
        boolean res;
        if(rankService.getRank(Integer.valueOf(consumerId),Integer.valueOf(songListId))==null){

            Rank rank = new Rank();
            rank.setSongListId(Long.parseLong(songListId));
            rank.setConsumerId(Long.parseLong(consumerId));
            rank.setScore(Integer.parseInt(score));
            res = rankService.insert(rank);

        }else {
            res=rankService.updateScore(Integer.valueOf(consumerId),Integer.valueOf(songListId),Integer.valueOf(score));
        }
        if (res){
            jsonObject.put("code", 1);
            jsonObject.put("msg", "评价成功");
            return jsonObject;
        }else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "评价失败");
            return jsonObject;
        }
    }

//    获取指定歌单的评分
    @RequestMapping(value = "/api/getRank", method = RequestMethod.GET)
    public Object ranks(HttpServletRequest req){
        String songListId = req.getParameter("songListId");
        return rankService.selectAverScore(Long.parseLong(songListId));
    }

    @GetMapping("/api/getScore")
    public int getRankScore(Integer consumerId, Integer songListId) {
        Rank rank=rankService.getRank(consumerId,songListId);
        if(rank==null){
            return 0;
        }else{
            return rank.getScore();
        }
    }
}
