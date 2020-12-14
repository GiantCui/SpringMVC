package generate;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * radarlog
 * @author 
 */
@Data
public class Radarlog implements Serializable {
    /**
     * 错误编号
     */
    private String lognum;

    /**
     * 雷达ID
     */
    private String radarid;

    /**
     * 开始时间
     */
    private Date starttime;

    /**
     * 结束时间
     */
    private Date endtime;

    /**
     * 错误日志
     */
    private String log;

    private static final long serialVersionUID = 1L;
}