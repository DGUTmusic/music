package com.example.demo.service.impl;

import com.example.demo.dao.RankMapper;
import com.example.demo.domain.Rank;
import com.example.demo.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankServiceImpl implements RankService {

    @Autowired
    private RankMapper rankMapper;

    @Override
    public int selectAverScore(Long songListId) {
        int num=rankMapper.selectRankNum(songListId);
        int score=rankMapper.selectScoreSum(songListId);
        if(num==0){
            return 0;
        }else{
            return score/num;
        }
    }

    @Override
    public boolean insert(Rank rank) {

        return rankMapper.insertSelective(rank) > 0 ? true:false;
    }

    @Override
    public Rank getRank(Integer consumerId,Integer songListId){
        return rankMapper.getRank(consumerId,songListId);
    }

    @Override
    public boolean updateScore(Integer consumerId, Integer songListId, Integer score) {
        return rankMapper.updateScore(consumerId,songListId,score)> 0 ? true:false;
    }
}
