package com.kul.pojo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Emp implements Serializable {

    private Integer eid;

    private String empName;

    private Integer age;

    private String sex;

    private String email;

    private String did;

    private Dept dept;
}
