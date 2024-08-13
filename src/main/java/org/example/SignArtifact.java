package org.example;

import dev.sigstore.KeylessSigner;
import dev.sigstore.KeylessSignerException;
import dev.sigstore.bundle.Bundle;
import dev.sigstore.oidc.client.GithubActionsOidcClient;
import dev.sigstore.oidc.client.OidcClients;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

public class SignArtifact {
    public static void main(String[] args) throws InvalidAlgorithmParameterException, CertificateException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, KeylessSignerException {
        Bundle result = signPayload();
        System.out.println(result.getMessageSignature().get().getSignature());
    }

    public static OidcClients retrieveOidcClients() {
        GithubActionsOidcClient oidcClient = GithubActionsOidcClient.builder().build();
        return OidcClients.of(oidcClient);
    }

    public static Bundle signPayload() throws KeylessSignerException, InvalidAlgorithmParameterException, CertificateException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException {
        KeylessSigner signer = new SigstoreDefaults().setOidcClients(retrieveOidcClients()).build();
        Path filePath = Paths.get("src/main/java/org/example/hello.txt");
        return signer.signFile(filePath);
    }
}
