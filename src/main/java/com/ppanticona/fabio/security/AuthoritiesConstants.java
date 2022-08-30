package com.ppanticona.fabio.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String USER = "ROLE_USER";

    public static final String CAJERO = "ROLE_CAJERO";

    public static final String GERENTE = "ROLE_GERENTE";

    public static final String CONTADOR = "ROLE_CONTADOR";

    public static final String INVENTARIADOR = "ROLE_INVENTARIADOR";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    private AuthoritiesConstants() {}
}
