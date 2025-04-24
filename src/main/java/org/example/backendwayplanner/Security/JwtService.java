package org.example.backendwayplanner.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.example.backendwayplanner.Entidades.Usuario;
import org.example.backendwayplanner.Servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
@Service
public class JwtService {
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    @Lazy
    @Autowired
    private UsuarioService usuarioService;

    public String generateToken(Usuario usuario) {
        TokenDataDTO tokenDataDTO = new TokenDataDTO(
                usuario.getId(),
                usuario.getUsername(),
                System.currentTimeMillis(),
                System.currentTimeMillis() + 1000 * 60 * 60 * 3
        );

        return Jwts
                .builder()
                .claim("tokenDataDTO", tokenDataDTO)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    private Claims extractDatosToken(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    public TokenDataDTO extractTokenData(String token) {
        Claims claims = extractDatosToken(token);
        Map<String, Object> mapa = (LinkedHashMap<String, Object>) claims.get("tokenDataDTO");

        return new TokenDataDTO(
                ((Number) mapa.get("id")).longValue(),
                (String) mapa.get("username"),
                (Long) mapa.get("fecha_creacion"),
                (Long) mapa.get("fecha_expiracion"),
                (String) mapa.get("rol")
        );
    }


    /**
     * Método que me dice si el token a expirado
     * @param token
     * @return
     */
    public boolean isExpired(String token){
        return new Date(extractTokenData(token).getFecha_expiracion()).before(new Date()) ;
    }

    private Key getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public Usuario extraerUsuarioToken(String token){
        String tokenSinCabecera = token.substring(7);
        TokenDataDTO tokenDataDTO = extractTokenData(tokenSinCabecera);
        Usuario usuarioLogueado = (Usuario) usuarioService.loadUserByUsername(tokenDataDTO.getUsername());
        return usuarioLogueado;
    }
}
