package org.radarcns.management.security.jwt;

import com.auth0.jwt.algorithms.Algorithm;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;

public class EcdsaJwtAlgorithm extends AssymmetricJwtAlgorithm {
    /** ECDSA JWT algorithm. */
    public EcdsaJwtAlgorithm(KeyPair keyPair) {
        super(keyPair, "SHA256withECDSA");
        if (!(keyPair.getPrivate() instanceof ECPrivateKey)) {
            throw new IllegalArgumentException(
                    "Cannot make EcdsaJwtAlgorithm with " + keyPair.getPrivate().getClass());
        }
    }

    @Override
    public Algorithm getAlgorithm() {
        return Algorithm.ECDSA256(
                (ECPublicKey)keyPair.getPublic(),
                (ECPrivateKey)keyPair.getPrivate());
    }

    @Override
    public String getEncodedStringHeader() {
        return "-----BEGIN EC PUBLIC KEY-----";
    }

    @Override
    public String getEncodedStringFooter() {
        return "-----END EC PUBLIC KEY-----";
    }
}
