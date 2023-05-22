package com.bluethink.dev.reporepository;

import com.bluethink.dev.enitity.User;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
}
