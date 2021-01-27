package com.cui.dao;

import com.cui.pojo.Radar;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RadarDao {
    //新增一个雷达
    int insertRadar(Radar radar);

    //初始化雷达
    int initRadar(Radar radar);

    //删除一个雷达
    int deleteRadarById(@Param("radarid") String id);

    //更新一个雷达
    int updateRadar(Radar radar);

    //查询一个雷达
    Radar queryRadarById(String id);

    Radar queryRadarBySirialnum(String id);

    //查询全部雷达
    List<Radar> queryAllRadar();

}