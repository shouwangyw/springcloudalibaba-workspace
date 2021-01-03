package com.yw.sca.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author yangwei
 * @date 2020-12-12 19:12
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Depart implements Serializable {
    private Integer id;
    private String name;
}
