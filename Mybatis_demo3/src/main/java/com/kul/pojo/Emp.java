package com.kul.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emp {

    private Integer eid;

    private String empName;

    private String age;

    private String sex;

    private String email;

    private String did;
}
