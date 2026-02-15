package com.live.common.exception;

import lombok.Getter;

@Getter
public enum BusinessErrorMessage {
    EMAIL_INVALID("값이 비어있거나 이메일 형식이 아닙니다"),
    NAME_INVALID("값이 비어있거나 2-10자 내로 입력해주세요"),
    EMAIL_DUPLICATED("이메일이 중복되었습니다.");
    private final String message;

    BusinessErrorMessage(String message) {
        this.message = message;
    }
}