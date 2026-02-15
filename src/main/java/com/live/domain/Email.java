package com.live.domain;

import com.live.common.exception.InvalidEmailException;
import jakarta.persistence.*;
import lombok.*;

import static com.live.common.exception.BusinessErrorMessage.EMAIL_INVALID;

@Getter
@EqualsAndHashCode
@Embeddable
public class Email {
    private static final String EMAIL_PATTERN =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    @Column(name = "email", nullable = false)
    private final String value;

    protected Email() {this.value = null;}

    public Email(String value) {
        validate(value);
        this.value = value;
    }

    private void validate(String email) {
        if (email == null || email.isBlank() || !email.matches(EMAIL_PATTERN)) {
            throw new InvalidEmailException(EMAIL_INVALID.getMessage());
        }
    }
}
