package com.yw.sca.fallback;

import com.yw.sca.entity.Depart;

import java.util.Collections;
import java.util.List;

/**
 * 自定义降级类
 *
 * @author yangwei
 * @date 2020-12-25 22:47
 */
public class DepartServiceFallback {
    public static Depart findByIdFallback(int id, Throwable e) {
        return new Depart()
                .setId(id)
                .setName("degrade-class-" + id + "-" + e.getMessage());
    }

    public static List<Depart> listFallback() {
        return Collections.singletonList(new Depart().setName("no any depart"));
    }
}
