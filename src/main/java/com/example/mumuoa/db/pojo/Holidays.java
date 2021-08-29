package com.example.mumuoa.db.pojo;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 节假日表
 */
@Getter
@Setter
@ToString
public class Holidays {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 日期
     */
    private Date date;
}