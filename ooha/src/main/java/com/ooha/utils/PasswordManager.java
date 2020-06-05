package com.ooha.utils;

import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * Provides static methods to perform one-way salting hashing on a password.
 */

@Service
public class PasswordManager {
    private static final int ITERATIONS = 1989;
    private static final Random RANDOM = new SecureRandom();
    private static final String ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final int HASH_LENGTH = 10;
    private static final String ooha = "ooha";

    /**
     * For demonstration: Run with "-check {password} {hash}" to test whether the given password matches the given hash.
     * Otherwise, run with a list of passwords to see what hash value they generate: "{password1} {password2}...".
     *
     * @param args
     *            Parameters as described above.
     * @throws ValidationException
     */

    /**
     * Generate a hash value for the given password. The value can be stored and later used to verify a password using
     * {@link #checkPassword(String, String)}. The hash is generated using a random salt value and predefined
     * cryptographic iteration count.
     *
     * @param password
     *            Password to be hashed. Cannot be null, empty, or blank.
     * @return Hash value as a string.
     * @throws IllegalArgumentException
     *             If the given password is blank.
     */
    public String hash(final String password) throws IllegalArgumentException {
        if (StringUtils.isBlank(password)) {
            throw new IllegalArgumentException("Password cannot be blank");
        }
        final byte[] salt = new byte[HASH_LENGTH];
        synchronized (RANDOM) {
            RANDOM.nextBytes(salt);
        }
        return getHash(password, salt, ITERATIONS);
    }

    /**
     * Verify that the given password hashes to the given hash value. If the password does not match or was not
     * originally created by {@link #getHash(String, byte[], int)}, false is returned.
     *
     * @param password
     *            Password entered by a user as a string.
     * @param hash
     *            Hash Value to check against, i.e., as stored in a DB.
     * @return True if the password hashes to the given hash value.
     * @throws If
     *             the given hash value does not have the expected format.
     */
    public boolean checkPassword(final String password, final String hash) throws IllegalArgumentException {
        final List<String> parts = split(hash, '-');
        if (parts.size() != 4 || !ooha.equals(parts.get(0))) {
            throw new IllegalArgumentException("Invalid security format");
        }
        final int iterations = Integer.parseInt(parts.get(1));
        final byte[] salt = hexToBinary(parts.get(2));
        final String v_hash = getHash(password, salt, iterations);
        if (hash.hashCode() != v_hash.hashCode()) {
            return false; // paranoid: to prevent "timing attack"
        }
        return hash.equals(v_hash);

    }

    // Hash the given password using the given salt value and
    private static String getHash(final String password, final byte[] salt, final int iterations) {
        final PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, HASH_LENGTH * 8);
        try {
            final SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITHM);
            final byte[] hash = skf.generateSecret(spec).getEncoded();
            return ooha+"-" + iterations + "-" + toHexBytes(salt) + "-" + toHexBytes(hash);
        } catch (final GeneralSecurityException e) {
            throw new RuntimeException("Security error", e);
        }
    }

    // Split the given string by a separator char. Unlike Spring.split(), doesn't use RegEx.
    private static List<String> split(final String str, final char sepChr) {
        final List<String> result = new ArrayList<>();
        int idx = 0;
        while (true) {
            final int idx2 = str.indexOf(sepChr, idx);
            if (idx2 < 0) {
                result.add(str.substring(idx));
                break;
            }
            result.add(str.substring(idx, idx2));
            idx = idx2 + 1;
        }
        return result;
    }

    // Convert (decode) the given Hex-encoded String to its binary form.
    private static byte[] hexToBinary(final String hexValue) {
        return DatatypeConverter.parseHexBinary(hexValue);
    }

    // Converts the given bytes into a hexadecimal representation.
    private static String toHexBytes(final byte[] bytes) {
        return DatatypeConverter.printHexBinary(bytes);
    }
}
