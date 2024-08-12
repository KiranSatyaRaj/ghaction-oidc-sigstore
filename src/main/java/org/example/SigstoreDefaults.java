package org.example;

import dev.sigstore.KeylessSigner;
import dev.sigstore.TrustedRootProvider;
import dev.sigstore.encryption.signers.Signers;
import dev.sigstore.fulcio.client.FulcioClient;
import dev.sigstore.oidc.client.OidcClients;
import dev.sigstore.rekor.client.RekorClient;
import dev.sigstore.tuf.SigstoreTufClient;

public class SigstoreDefaults {
    private KeylessSigner.Builder builder = new KeylessSigner.Builder();
    public KeylessSigner.Builder setOidcClients(OidcClients clients) {
        this.builder.oidcClients(clients);
        this.setDefaults();
        return builder;
    }
    private void setDefaults() {
        this.builder.signer(Signers.newEcdsaSigner())
                .trustedRootProvider(TrustedRootProvider.from(SigstoreTufClient.builder().usePublicGoodInstance()))
                .fulcioUrl(FulcioClient.PUBLIC_GOOD_URI)
                .rekorUrl(RekorClient.PUBLIC_GOOD_URI)
                .minSigningCertificateLifetime(KeylessSigner.DEFAULT_MIN_SIGNING_CERTIFICATE_LIFETIME);
    }
}
