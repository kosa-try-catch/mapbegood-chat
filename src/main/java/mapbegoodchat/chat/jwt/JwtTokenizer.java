package mapbegoodchat.chat.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;

@Getter
@Component
public class JwtTokenizer {
    @Value("${jwt.key}")
    private String secretKey;

    public String encodeBase64SecretKey(String secretKey) {
        return Encoders.BASE64.encode(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public void verifySignature(String jws, String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        Jwts.parser()
                .verifyWith((SecretKey) key)
                .build()
                .parseSignedClaims(jws);
    }

    public void validateToken(String jwt) throws Exception {
        if(!jwt.startsWith("Bearer ")) {
            throw new SignatureException("");
        }
        String jws = jwt.replace("Bearer ", "");
        String base64EncodedSecretKey = this.encodeBase64SecretKey(secretKey);
        this.verifySignature(jws, base64EncodedSecretKey);
    }

    private Key getKeyFromBase64EncodedKey(String base64EncodedSecretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(base64EncodedSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
