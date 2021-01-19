package com.cui.service;

import com.cui.dao.RadarDao;
import com.cui.pojo.Radar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RadarServiceImpl implements RadarService{
    //service调dao层
    @Autowired
    private RadarDao radarDao;

    public void setRadarDao(RadarDao radarDao) {
        this.radarDao = radarDao;
    }

    //新增一个雷达
    public void insertRadar(Radar radar) {
        radarDao.insertRadar((Radar) radar);
    }

    // 初始化雷达
    public void initRadar(Radar radar){
        System.out.println("service层初始化雷达");

        radarDao.initRadar(radar);
    }

    //删除一个雷达
    public void deleteRadarById(String id) {
        radarDao.deleteRadarById(id);
    }

    //更新一个雷达
    public void updateRadar(Radar radar) {
        radarDao.updateRadar((Radar) radar);
    }

    //查询一个雷达
    public Radar queryRadarById(String id) {
        return radarDao.queryRadarById(id);
    }

    //查询全部雷达
    public List<Radar> queryAllRadar() {
        return radarDao.queryAllRadar();
    }

}
