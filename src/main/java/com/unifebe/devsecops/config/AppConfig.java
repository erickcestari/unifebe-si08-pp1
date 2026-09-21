package com.unifebe.devsecops.config;

/**
 * Credenciais lidas de variaveis de ambiente, nunca do codigo-fonte.
 * Em producao elas sao injetadas por um cofre de segredos (Vault, AWS
 * Secrets Manager etc.). O GITHUB_TOKEN nao serve para isso: e um segredo
 * de CI/build, nao de runtime.
 */
public final class AppConfig {

    private AppConfig() {
    }

    public static String dbPassword() {
        return requireEnv("DB_PASSWORD");
    }

    public static String awsAccessKeyId() {
        return requireEnv("AWS_ACCESS_KEY_ID");
    }

    public static String awsSecretAccessKey() {
        return requireEnv("AWS_SECRET_ACCESS_KEY");
    }

    public static String paymentGatewayApiKey() {
        return requireEnv("PAYMENT_GATEWAY_API_KEY");
    }

    // Leitura sob demanda: a aplicacao sobe sem as variaveis e falha so em quem usa o segredo.
    private static String requireEnv(String name) {
        String value = System.getenv(name);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException("Variavel de ambiente obrigatoria ausente: " + name);
        }
        return value;
    }
}
