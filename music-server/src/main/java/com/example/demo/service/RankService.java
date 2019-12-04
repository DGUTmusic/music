package com.example.demo.service;

import com.example.demo.domain.Rank;

import java.util.List;

public interface RankService {

    int selectAverScore(Long songListId);

    boolean insert(Rank rank);

    Rank getRank(Integer consumerId,Integer songListId);

    boolean updateScore(Integer consumerId,Integer songListId,Integer score);
}
