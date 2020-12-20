package com.yw.sca.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author yangwei
 * @date 2020-12-12 19:12
 */
@Data
@Accessors(chain = true)
public class Depart {
    private Integer id;
    private String name;
}
