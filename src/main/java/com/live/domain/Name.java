package com.live.domain;


import com.live.common.exception.BusinessErrorMessage;
import com.live.common.exception.InvalidNameException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@Embeddable
public class Name {
    private static final String NAME_PATTERN = "^[가-힣a-zA-Z]{2,10}$";

    @Column(name = "name", nullable = false)
    private final String name;

    protected Name() {this.name = null;}

    public Name(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        if (name == null || name.isBlank() || !name.matches(NAME_PATTERN)) {
            throw new InvalidNameException(BusinessErrorMessage.NAME_INVALID.getMessage());
        }
    }
}
