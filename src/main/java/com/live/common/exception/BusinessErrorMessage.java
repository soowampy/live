package com.live.common.exception;

import lombok.Getter;

@Getter
public enum BusinessErrorMessage {
    EMAIL_INVALID("이메일 형식으로 입력해주세요"),
    NAME_INVALID("이름은 2~20자로 입력해주세요");
    private final String message;

    BusinessErrorMessage(String message) {
        this.message = message;
    }
}