package com.rohith.ppmtool.config.security;

public class SecurityConstants {
    public static final String SIGN_UP_URLS="/api/auth/**";
    public static final String H2_URL = "h2-console/**";
    public static final String SECRET = "s3cretK3y";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER = "Authorization";
    public static final long EXPIRATION_TIME = 300_000;
}

