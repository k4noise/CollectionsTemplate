package ru.naumen.collection.task2;

import java.util.Arrays;
import java.util.Objects;

/**
 * Пользователь
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class User {
    private String username;
    private String email;
    private byte[] passwordHash;

    @Override
    public int hashCode() {
        return Objects.hash(username, email) + Arrays.hashCode(passwordHash);
    }

    @Override
    public boolean equals(Object userObject) {
        if (this == userObject) return true;
        if (userObject == null || getClass() != userObject.getClass()) return false;
        User user = (User) userObject;
        return username.equals(user.username)
                && email.equals(user.email)
                && Arrays.equals(passwordHash, user.passwordHash);
    }
}
