package jp.co.rororo.repository;

import java.util.List;

import jp.co.rororo.entity.RememberMeToken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RememberMeTokenRepository extends JpaRepository<RememberMeToken, String> {
	
	List<RememberMeToken> findByUserId(Long userId);
}
