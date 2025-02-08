package org.example;

import org.apache.pulsar.client.impl.conf.ClientConfigurationData;
import org.apache.pulsar.client.impl.conf.ConfigurationDataUtils;

import java.util.Map;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        // Define valid keys for Pulsar ClientConfigurationData
        Set<String> validKeys = Set.of(
                "connectionTimeoutMs", "authPluginClassName", "authParams", "tlsTrustCertsFilePath"
                // Add other valid configuration keys here
        );

        Map<String, Object> incorrectConfig = Map.of("konnektionTimeoutMs", 11111);  // Invalid key (typo)

        // Check if the config contains any invalid keys
        for (String key : incorrectConfig.keySet()) {
            if (!validKeys.contains(key)) {
                throw new RuntimeException("Invalid configuration key found: " + key);
            }
        }

        try {
            ClientConfigurationData conf = ConfigurationDataUtils.loadData(
                    incorrectConfig, new ClientConfigurationData(), ClientConfigurationData.class);
            System.out.println("Configuration loaded successfully.");
        } catch (RuntimeException e) {
            System.out.println("Runtime exception was thrown as expected.");
            throw new RuntimeException(e);
        }
    }
}
