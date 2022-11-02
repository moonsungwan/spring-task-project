package com.task.sample.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberLoginRequest {

    private String memberId;

    private String password;

}
