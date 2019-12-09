package com.example.demo.service.impl;

import com.example.demo.dao.SongListMapper;
import com.example.demo.domain.SongList;
import com.example.demo.service.SongListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongListServiceImpl implements SongListService {

    @Autowired
    private SongListMapper songListMapper;

    @Override
    @CacheEvict(cacheNames = "songAlbum", allEntries=true)
    public boolean updateSongListMsg(SongList songList) {
        return songListMapper.updateSongListMsg(songList) >0 ?true:false;
    }

    @Override
    @CacheEvict(cacheNames = "songAlbum", allEntries=true)
    public boolean deleteSongList(Integer id) {
        return songListMapper.deleteSongList(id) >0 ?true:false;
    }

    @Override
    @Cacheable(cacheNames = "songList", unless = "#result == null ")
    public List<SongList> listSongLists()
    {
        return songListMapper.listSongLists();
    }

    @Override
    @Cacheable(cacheNames = "likeTitle", unless = "#result == null ")
    public List<SongList> likeTitle(String title)
    {
        return songListMapper.likeTitle(title);
    }

    @Override
    @Cacheable(cacheNames = "likeStyle", unless = "#result == null ")
    public List<SongList> likeStyle(String style)
    {
        return songListMapper.likeStyle(style);
    }

    @Override
    @Cacheable(cacheNames = "songAlbum", unless = "#result == null ")
    public List<SongList> songAlbum(String title)
    {
        return songListMapper.songAlbum(title);
    }

    @Override
    public boolean ifAdd(SongList songList)
    {
        return songListMapper.insertSelective(songList) > 0?true:false;
    }

    @Override
    @CacheEvict(cacheNames = "songAlbum", allEntries=true)
    public boolean updateSongListImg(SongList songList) {

        return songListMapper.updateSongListImg(songList) >0 ?true:false;
    }
}
