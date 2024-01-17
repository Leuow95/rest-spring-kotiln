package br.com.leomaia.security.jwt

import br.com.leomaia.data.vo.v1.TokenVO
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.Base64
import java.util.Date

@Service
class JwtTokenProvider {

    @Value("\${security.jwt.token.secret-key:secret}")
    private var secretKey = "secret"

    @Value("\${security.jwt.token.expire-length:3600000}")
    private var validityInMilliseconds: Long = 3_600_000 //1h

    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    private lateinit var algorithm: Algorithm

    @PostConstruct
    protected fun init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.toByteArray())
        algorithm = Algorithm.HMAC256(secretKey.toByteArray())
    }



    fun createAccessToken(userName: String, roles: List<String>?): TokenVO{
        val now = Date()
        val validity = Date(now.time + validityInMilliseconds)
        val accessToken = getAccessToken(userName, roles, now, validity)
        val refreshToken = getRefreshToken(userName, roles, now)
        return TokenVO(
            userName = userName,
            authenticated = true,
            accessToken = accessToken,
            refreshToken = refreshToken,
            created = now,
            expiration = validity
        );
    }

    private fun getAccessToken(userName: String, roles: List<String>?, now: Date,validity: Date): Any {

    val issuerUrl: String = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString()

    return JWT.create()
        .withClaim("roles", roles)
        .withIssuedAt(now)
        .withExpiresAt(validity)
        .withIssuer(issuerUrl)
        .sign(algorithm)
        .trim()
    }


    private fun getRefreshToken(userName: String, roles: List<String>?, now: Date): Any {

    }

}