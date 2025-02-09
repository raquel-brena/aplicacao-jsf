package com.rb.esig.infra.utils;
import org.mindrot.jbcrypt.BCrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
public class passwordEncoder {

    public static String encoder(String senha) {
        return BCrypt.hashpw(senha, BCrypt.gensalt(12));
    }
    public static boolean verify(String senha, String senhaCriptografada) {
        return BCrypt.checkpw(senha, senhaCriptografada);
    }
}
