package com.example.mumuoa.db.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 疫情城市列表
 */
@Getter
@Setter
@ToString
public class City {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 城市名称
     */
    private String city;

    /**
     * 拼音简称
     */
    private String code;
}