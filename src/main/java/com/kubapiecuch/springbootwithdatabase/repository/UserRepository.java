package com.kubapiecuch.springbootwithdatabase.repository;

import com.kubapiecuch.springbootwithdatabase.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    public User save(User user);

    @Override
    public Optional<User> findById(Long id);

    @Override
    public List<User> findAll();

    @Override
    public void deleteById(Long id);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
