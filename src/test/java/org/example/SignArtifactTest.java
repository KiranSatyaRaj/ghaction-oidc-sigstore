package org.example;

import dev.sigstore.KeylessSignerException;
import dev.sigstore.bundle.Bundle;
import dev.sigstore.oidc.client.OidcClients;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;

public class SignArtifactTest {

    @Test
    public void retrieveOidcClient() {
        OidcClients oidcClients = new SignArtifact().retrieveOidcClients();
        Assert.assertNotNull(oidcClients.toString());
    }

    @Test
    public void signPayload() throws InvalidAlgorithmParameterException, CertificateException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, KeylessSignerException {
        Bundle result = new SignArtifact().signPayload();
        Assert.assertNotNull(result.toJson());
    }
}
