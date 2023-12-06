package br.com.leomaia.services

import br.com.leomaia.data.vo.v1.BookVO
import br.com.leomaia.exceptions.ResourceNotFoundException
import br.com.leomaia.mapper.DozerMapper
import br.com.leomaia.repository.BookRepository
import br.com.leomaia.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

import java.util.logging.Logger

@Service
class UserService(@field: Autowired var userRepository: UserRepository): UserDetailsService {

    private val logger = Logger.getLogger(UserService::class.java.name)

    override fun loadUserByUsername(username: String?): UserDetails {
        logger.info("Finding one User by Username ID: $username")
        val user = userRepository.findByUserName(username)

        return user ?: throw UsernameNotFoundException("Username $username not found")
    }


}