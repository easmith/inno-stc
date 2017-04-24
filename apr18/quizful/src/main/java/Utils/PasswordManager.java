package Utils;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Created by eku on 24.04.17.
 */
public class PasswordManager {

    public static String createHash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(10));
    }

    public static boolean checkHash(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}
