package generate;

import generate.Radarlog;

public interface RadarlogDao {
    int deleteByPrimaryKey(String lognum);

    int insert(Radarlog record);

    int insertSelective(Radarlog record);

    Radarlog selectByPrimaryKey(String lognum);

    int updateByPrimaryKeySelective(Radarlog record);

    int updateByPrimaryKey(Radarlog record);
}