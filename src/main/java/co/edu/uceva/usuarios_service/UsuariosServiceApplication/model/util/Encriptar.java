package co.edu.uceva.usuarios_service.UsuariosServiceApplication.model.util;

import java.security.MessageDigest;

public class Encriptar {

    public static String md5(String contrasena) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(contrasena.getBytes());
            byte[] digest = md.digest();
            System.out.println(digest);
            return bytesToHex(digest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}

