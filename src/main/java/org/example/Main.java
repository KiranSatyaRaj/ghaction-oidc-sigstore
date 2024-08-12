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

public class Main {
    public static void main(String[] args) throws InvalidAlgorithmParameterException, CertificateException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, KeylessSignerException {
        GithubActionsOidcClient oidcClient = GithubActionsOidcClient.builder().build();
        Map<String, String> env = System.getenv();
        OidcClients clients = OidcClients.of(oidcClient);

        KeylessSigner signer = new SigstoreDefaults().setOidcClients(clients).build();
        Path filePath = Paths.get("src/main/java/org/example/hello.txt");
        Bundle result = signer.signFile(filePath);
        System.out.println(result.getMessageSignature().get().getSignature());
    }
}
