package com.krugercorp.employeesvaccination.service.impl;

import com.google.gson.Gson;
import com.krugercorp.employeesvaccination.commons.enumerations.EnumResponse;
import com.krugercorp.employeesvaccination.commons.response.InfoResponse;
import com.krugercorp.employeesvaccination.commons.util.Constants;
import com.krugercorp.employeesvaccination.entity.Role;
import com.krugercorp.employeesvaccination.entity.Users;
import com.krugercorp.employeesvaccination.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	private Logger logger = LoggerFactory.getLogger(JpaUserDetailsService.class);

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.debug("jpaUserDetailsService : loadUserByUsername");
		Users user = userRepository.findByUsername(username);

		if (user == null) {
			logger.error("Error en el Login: no existe el usuario '" + username + "' en el sistema!");
			InfoResponse infoRespuestaVO = new InfoResponse();
			infoRespuestaVO.setMessage(EnumResponse.AUTH_ERROR.getMessage());
			infoRespuestaVO.setCode(EnumResponse.AUTH_ERROR.getCode());
			Map<String, Object> response = new HashMap<>();
			response.put(Constants.Messages.INFO_RESPONSE, infoRespuestaVO);
			throw new UsernameNotFoundException("Username: " + username + " no existe en el sistema!");
		}

		List<GrantedAuthority> authorities = new ArrayList<>();

		/*for (Role role : user.getRoles()) {
			//logger.debug("Role: ".concat(role.getAuthority()));
			authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
		}*/
		user.getRoles().forEach(rol -> authorities.add(new SimpleGrantedAuthority(rol.getAuthority())));

		if (authorities.isEmpty()) {
			logger.error("Error en el Login: Usuario '" + username + "' no tiene roles asignados!");
			throw new UsernameNotFoundException(
					"Error en el Login: usuario '" + username + "' no tiene roles asignados!");
		}

		return new User(user.getUsername(), user.getPassword(), user.getEnabled(), true, true, true, authorities);
	}

}
