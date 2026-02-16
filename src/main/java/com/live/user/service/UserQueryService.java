package com.live.user.service;

import com.live.common.exception.UserNotFoundException;
import com.live.user.domain.User;
import com.live.user.domain.UserRepository;
import com.live.user.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.live.common.exception.BusinessErrorMessage.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserQueryService {
    private final UserRepository userRepository;

    public UserDto getUserDto(Long id) {
        User user = getUser(id);
        return new UserDto(
                user.getId(),
                user.getEmail().getValue(),
                user.getName().getValue(),
                user.getCreatedAt()
        );
    }

    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND.getMessage()));
    }
}
