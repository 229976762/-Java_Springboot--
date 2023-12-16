package com.ghy.form;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePwFrom {

    private Long id;

    private String oldPass;

    private String pass;

}
