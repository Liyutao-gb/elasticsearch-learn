package com.atguigu.es.test;

import lombok.Builder;
import lombok.Data;

/**
 * @author liyutao
 * @since 2021/8/3 22:38
 */
@Data
@Builder
public class User {
    private String name;

    private String sex;

    private String age;
}
