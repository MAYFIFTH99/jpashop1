package jpa.jpashop1.controller;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberForm {

    @NotEmpty(message ="필수로 입력해야 합니다.")
    private String name;

    private String city;
    private String street;
    private String zipcode;
}
