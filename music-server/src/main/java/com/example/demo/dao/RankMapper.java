package com.example.demo.dao;

import com.example.demo.domain.Rank;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RankMapper {

    int insert(Rank record);

    int insertSelective(Rank record);

    /**
     * 查总分
     * @param songListId
     * @return
     */
    int selectScoreSum(Long songListId);

    /**
     * 查总评分人数
     * @param songListId
     * @return
     */
    int selectRankNum(Long songListId);

    Rank getRank(@Param("consumerId") Integer consumerId,@Param("songListId") Integer songListId);

    int updateScore
            (@Param("consumerId") Integer consumerId,@Param("songListId") Integer songListId,@Param("score")Integer score);
}