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
    private final String email;

    protected Email() {this.email = null;}

    public Email(String email) {
        validate(email);
        this.email = email;
    }

    private void validate(String email) {
        if (!email.matches(EMAIL_PATTERN)) {
            throw new InvalidEmailException(EMAIL_INVALID.getMessage());
        }
    }
}
