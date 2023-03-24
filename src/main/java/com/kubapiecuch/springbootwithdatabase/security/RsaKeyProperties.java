package com.kubapiecuch.springbootwithdatabase.security;

import com.mysql.cj.jdbc.result.UpdatableResultSet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "rsa")
public class RsaKeyProperties {
    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;
}
