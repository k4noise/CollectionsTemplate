package ru.naumen.collection.task2;

import java.util.*;

/**
 * Дано:
 * <pre>
 * public class User {
 *     private String username;
 *     private String email;
 *     private byte[] passwordHash;
 *     …
 * }
 * </pre>
 * Нужно реализовать метод
 * <pre>
 * public static List<User> findDuplicates(Collection<User> collA, Collection<User> collB);
 * </pre>
 * <p>который возвращает дубликаты пользователей, которые есть в обеих коллекциях.</p>
 * <p>Одинаковыми считаем пользователей, у которых совпадают все 3 поля: username,
 * email, passwordHash. Дубликаты внутри коллекций collA, collB можно не учитывать.</p>
 * <p>Метод должен быть оптимален по производительности.</p>
 * <p>Пользоваться можно только стандартными классами Java SE.
 * Коллекции collA, collB изменять запрещено.</p>
 *
 * См. {@link User}
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class Task2
{
    /**
     * Возвращает дубликаты пользователей, которые есть в обеих коллекциях
     * O(2n+size(collB))
     */
    public static List<User> findDuplicates(Collection<User> collA, Collection<User> collB) {
        /**
         * По заданию разрешено игнорировать дубликаты в коллекциях
         * Выбран HashSet для операций вставки и поиска за O(1),
         * так как явно объявили хэш-функцию, не вызывающую коллизий
         * Инициализация - O(n)
         */
        HashSet<User> collAUsers = new HashSet<>(collA);
        int maxDuplicatesSize = Math.min(collA.size(), collB.size());
        /**
         * Выбран ArrayList для операций вставки в конец за O(1),
         * так как память выделена заранее
         */
        ArrayList<User> usersDuplicate = new ArrayList<>(maxDuplicatesSize);
        /** Перебор коллекции - O(n) */
        for (User collBUsers : collB) {
            if (collAUsers.contains(collBUsers)) {
                usersDuplicate.add(collBUsers);
            }
        }
        return usersDuplicate;
    }
}
