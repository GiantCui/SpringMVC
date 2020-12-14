package generate;

import generate.Radar;

public interface RadarDao {
    int deleteByPrimaryKey(String radarid);

    int insert(Radar record);

    int insertSelective(Radar record);

    Radar selectByPrimaryKey(String radarid);

    int updateByPrimaryKeySelective(Radar record);

    int updateByPrimaryKey(Radar record);
}