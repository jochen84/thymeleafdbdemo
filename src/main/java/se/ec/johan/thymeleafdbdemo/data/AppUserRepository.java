package se.ec.johan.thymeleafdbdemo.data;

import org.springframework.data.repository.CrudRepository;
import se.ec.johan.thymeleafdbdemo.entity.AppUser;

import java.util.Optional;

public interface AppUserRepository extends CrudRepository<AppUser, Integer> {

    Optional<AppUser> findByEmailIgnoreCase(String email);

}
