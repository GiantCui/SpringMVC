package generate;

import java.io.Serializable;
import lombok.Data;

/**
 * radar
 * @author 
 */
@Data
public class Radar implements Serializable {
    /**
     * 雷达id
     */
    private String radarid;

    /**
     * 雷达ip
     */
    private String radarip;

    /**
     * 端口号
     */
    private String port;

    /**
     * 雷达编号
     */
    private String sirialnum;

    /**
     * 工作状态
     */
    private Boolean workstate;

    /**
     * 异物检测状态
     */
    private Boolean foreignmatter;

    /**
     * 屏蔽门状态
     */
    private Boolean safetydoor;

    /**
     * 雷达异常
     */
    private Boolean radarerror;

    /**
     * 最近日志编号
     */
    private String lastlog;

    /**
     * 备注
     */
    private String comment;

    private static final long serialVersionUID = 1L;
}