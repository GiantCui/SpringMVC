package com.cui.service;

import com.cui.pojo.Radar;

import java.util.List;

public interface RadarService {
    //新增一个雷达
    void insertRadar(Radar radar);

    // 初始化一个雷达
    void initRadar(Radar radar);

    //删除一个雷达
    void deleteRadarById(String id);

    //更新一个雷达
    void updateRadar(Radar radar);

    //查询一个雷达
    Radar queryRadarById(String id);

    Radar queryRadarBySirialnum(String id);

    //查询全部雷达
    List<Radar> queryAllRadar();

}
