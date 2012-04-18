package de.bastianhofmann.oauth2.examples.webapp;

import com.google.api.client.auth.oauth2.MemoryCredentialStore;

public class MemoryCredentialStoreFactory {

    private static MemoryCredentialStore store;

    public static MemoryCredentialStore get() {
        if (store == null) {
            store = new MemoryCredentialStore();
        }

        return store;

    }
}
