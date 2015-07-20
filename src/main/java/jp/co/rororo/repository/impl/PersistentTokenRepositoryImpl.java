package jp.co.rororo.repository.impl;

import java.util.Date;
import java.util.List;

import jp.co.rororo.entity.RememberMeToken;
import jp.co.rororo.entity.User;
import jp.co.rororo.repository.RememberMeTokenRepository;
import jp.co.rororo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PersistentTokenRepositoryImpl implements PersistentTokenRepository {
	
	@Autowired
	private RememberMeTokenRepository rememberMeTokenRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		User user = userRepository.findByName(token.getUsername());
		if (user == null) {
			return;
		}
		RememberMeToken newToken = new RememberMeToken(token.getSeries(), token.getTokenValue(), user.getId());
		newToken.setSystemColums();
		this.rememberMeTokenRepository.save(newToken);
	}
	
	@Override
	public void updateToken(String series, String tokenValue, Date lastUsed) {
		RememberMeToken token = this.rememberMeTokenRepository.findOne(series);
		if (token != null) {
			token.setToken(tokenValue);
			token.setUpdateAt(lastUsed);
			this.rememberMeTokenRepository.save(token);
		}
	}
	
	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		RememberMeToken token = this.rememberMeTokenRepository.findOne(seriesId);
		User user = userRepository.findOne(token.getUserId());
		if (token == null || user == null) {
			return null;
		}
		return new PersistentRememberMeToken(user.getName(), token.getSeries(), token.getToken(), token.getUpdateAt());
	}
	
	@Override
	public void removeUserTokens(String username) {
		User user = userRepository.findByName(username);
		if (user == null) {
			return;
		}
		List<RememberMeToken> tokens = this.rememberMeTokenRepository.findByUserId(user.getId());
		this.rememberMeTokenRepository.delete(tokens);
	}
}