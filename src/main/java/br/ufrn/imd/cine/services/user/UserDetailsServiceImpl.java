package br.ufrn.imd.cine.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.ufrn.imd.cineframework.models.user.User;
import br.ufrn.imd.cineframework.models.user.UserDetailsImpl;
import br.ufrn.imd.cineframework.repositories.user.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
		return new UserDetailsImpl(user);
	}

}