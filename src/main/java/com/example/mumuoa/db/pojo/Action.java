package com.example.mumuoa.db.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 行为表
 */
@Getter
@Setter
@ToString
public class Action {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 行为编号
     */
    private String actionCode;

    /**
     * 行为名称
     */
    private String actionName;
}