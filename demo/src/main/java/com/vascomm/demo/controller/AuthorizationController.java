package com.vascomm.demo.controller;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@RestController
public class AuthorizationController {

    // Replace privateKeyString with your private key string
    private final String privateKeyString = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCJs6F3hLCF/+bU" +
            "zYkrkoUMHnwOdM5KLei/3UVjYsoPo1y2alrGkoNjhIZ71pSCPDyTYek7xBed2gOR" +
            "JGBNQGmVRaWPt0dd0pHDzXPoumPS4Yrk0CyLocUANGRi0c5h0EFzAwxzFawq6Wlg" +
            "kHTK+n90hTuBIXZDM8e272g+tkyrgO/PLqvggiHwPQp6mtOHPwUgX348c4YRhEin" +
            "R2gNi6qGGK4eoRAGC78P+DjhtD9yFCHVG+uINkdH+jFBYah9/E42TYQoBS5VBOut" +
            "G4C68E/fB9aO7vfZl1F2XXF+atSR19tnX+Yp0VNx7eX/rRXwpn7qHmvN87+EMpuG" +
            "mEZJA9H3AgMBAAECggEBAIYeh645dxWM4e9zIZ9m3l+ncX1DF4n4nTIC+oqKtfP1" +
            "UV/6xt+R5JcRhUso/Qy8TuPeAaVMoRQGPGMp4wJbMntJkrAf+0rOxVrIyZRrtr63" +
            "HukuObTmo1JHtV7qqvFqPdUfqwiMm1xQa2q6SIdK5MlA22wyg1WBIYx0MjyiopqH" +
            "UJP4EWgPeqWIQ1iYS1695WezYHpfzrJeC4/RQV+OXZgKY+l/97reniidv7Rn4A3D" +
            "6LknGLLJXbThAaYHxasHjMBdwXaaDxr2CZ5OvLonofj9YTs19v5FtJ60xk5IpoOM" +
            "BNts2P9e2gKpE2WqZoNoYaUhOuDZ7IKP4UaoN9GXUAECgYEA3HK9pH22LWSot7yY" +
            "UlsZliPY7GuSAcXO3N7r62qgAAjn3syH5Qso234TOdv7nP6ncLBxjP8es5A3h2qt" +
            "/fBDNvRzjhyjbj1A3PB6FiBAdWgBFNRLvmLZvPs1C4T181iztlOHJ67GQYeD2+XF" +
            "J60A6zAopknoTGBwOAlFKkoFfQECgYEAn+it1sce2ZtBxFlY08sN5jBe4XU/S28L" +
            "5XRfpy1Ezq+5LxvL1wlMeVeU8dvzAo12CNdX35IZd8/DumXjVBuDq+ddJWpWRf4D" +
            "gHU94QYF82yJlllMG2JIt7lU2NLdCbqPDD8fFkc6UxKadlvAaxSl2HX2SOym8MN0" +
            "/HDetsNaNvcCgYEAvmndpNdxJIRtt0cPyxMlZqpQFOZVl3Jk3CMxZIcU7PLEVY4V" +
            "U6HwCKc9tBxvmYvc1VmSu1ciDbfkPvPGljGR3UYJChdykyYVgMG2sqXpB4Bubq9x" +
            "PWHk8XeKugh+6VMU58f+ViiA1tlW6/nr8fldcciHVBtCq7YhOEXJmF2XoAECgYBa" +
            "MF6Tq3yv2MwwFQBve6/cK13oJTvMrNX11Tv5yyNqu1tjwXanUrxKF+aOvrIBt41i" +
            "ESqjkwBAPfMzlzGvB1GtS/GSH7aHBWwuMHH1D3OdxpKXNMgjDbzbunCrVcqLKShz" +
            "DMrYwtWXcv+hT8Bn5J5hRq7glHDXjDXYB0TlqaqhIwKBgQCKFMRBjyYATHymtj1C" +
            "UOmprlsPR7wV5chX9HGGIzrB4/Ud93GZXFdPYp1+Fl8r7lzhbGh60IdfCP4q5HC0" +
            "/ZNLgDNkW89I0DcY4aMPn+yaMyP510DlZJ0aoAmEkYD6AJC3lrv6bdLxokjZfQ7z" +
            "S5FpdK5rKTKh/N70viPk9JrsPQ==";

    @PostMapping("/access/generate")
    public ResponseEntity<?> generateJwtToken(@RequestBody Map<String, Object> payload) {
        // Extract UPN from payload
        String upn = (String) payload.get("role");

        // Create JWT token
        String jwtToken = generateJwt(upn);

        // Return JWT token
        return ResponseEntity.ok(Map.of("jwtToken", jwtToken));
    }

    private String generateJwt(String upn) {
        try {
            // Decode private key string
            byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyString);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

            // Generate JWT token
            long currentTimeMillis = System.currentTimeMillis();
            long expirationTimeMillis = currentTimeMillis + 3600000; // 1 hour
            Date expirationDate = new Date(expirationTimeMillis);

            String jwtToken = Jwts.builder()
                    .setSubject(upn)
                    .setExpiration(expirationDate)
                    .signWith(privateKey, SignatureAlgorithm.RS256)
                    .compact();

            return jwtToken;
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exception
            return null;
        }
    }
}