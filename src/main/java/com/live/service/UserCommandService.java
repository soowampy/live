package com.live.service;

import com.live.common.exception.DuplicateEmailException;
import com.live.controller.dto.UserCreateRequest;
import com.live.controller.dto.UserCreateResponse;
import com.live.domain.User;
import com.live.domain.UserRepository;
import com.live.service.dto.UserCreateDto;
import com.live.service.dto.UserCreatedDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.live.common.exception.BusinessErrorMessage.EMAIL_DUPLICATED;

@Service
@RequiredArgsConstructor
public class UserCommandService {
    private final UserRepository userRepository;

    @Transactional
    public UserCreatedDto signUp(UserCreateDto dto) {
        validateDuplicateEmail(dto);
        User user = createUser(dto);
        try {
            User saved = userRepository.save(user);
            return new UserCreatedDto(
                    saved.getId(),
                    saved.getEmail().getEmail(),
                    saved.getName().getName(),
                    saved.getCreatedAt());
        } catch (DataIntegrityViolationException e) {
            // 동시성 방지
            throw new DuplicateEmailException(EMAIL_DUPLICATED.getMessage());
        }

    }

    private User createUser(UserCreateDto dto) {
        return new User(
                dto.email(),
                dto.name()
        );
    }

    private void validateDuplicateEmail(UserCreateDto dto) {
        if (userRepository.existsByEmail(dto.email())) {
            throw new DuplicateEmailException(EMAIL_DUPLICATED.getMessage());
        }
    }
}
