package com.live.user.domain;

import org.springframework.data.repository.ListCrudRepository;

public interface UserRepository extends ListCrudRepository<User, Long> {
    boolean existsByEmail(Email email);
}
