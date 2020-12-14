package com.cui.service;

import com.cui.dao.RadarDao;
import com.cui.pojo.Radar;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
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
    public int insertRadar(Radar radar) {
        return radarDao.insertRadar((Radar) radar);
    }

    //删除一个雷达
    public int deleteRadarById(String id) {
        return radarDao.deleteRadarById(id);
    }

    //更新一个雷达
    public int updateRadar(Radar radar) {
        return radarDao.updateRadar((Radar) radar);
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
