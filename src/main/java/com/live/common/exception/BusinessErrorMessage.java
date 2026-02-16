package com.live.common.exception;

import lombok.Getter;

@Getter
public enum BusinessErrorMessage {
    EMAIL_INVALID("값이 비어있거나 이메일 형식이 아닙니다"),
    NAME_INVALID("값이 비어있거나 2-10자 내로 입력해주세요"),
    EMAIL_DUPLICATED("이메일이 중복되었습니다."),
    USER_NOT_FOUND("해당하는 유저가 존재하지 않습니다"),
    COUPON_NOT_FOUND("해당하는 쿠폰이 존재하지 않습니다"),
    COUPON_EXPIRE("해당 쿠폰은 만료되었습니다"),
    COUPON_DUPLICATED("해당 쿠폰은 이미 등록되어있습니다");
    private final String message;

    BusinessErrorMessage(String message) {
        this.message = message;
    }
}