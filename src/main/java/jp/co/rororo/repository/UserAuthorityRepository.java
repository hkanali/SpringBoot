package jp.co.rororo.repository;

import java.util.List;

import jp.co.rororo.entity.UserAuthority;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthorityRepository extends JpaRepository<UserAuthority, Long> {
	
	List<UserAuthority> findByUserId(Long userId);
}
