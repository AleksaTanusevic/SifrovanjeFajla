package org.example;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Key;
import java.util.Scanner;

public class Main {

    public static byte[] sifrovanje(byte[] unos, byte[] skljuc) throws Exception {

        Key kljuc = new SecretKeySpec(skljuc, "ARCFOUR");
        Cipher c = Cipher.getInstance("ARCFOUR");
        c.init(Cipher.ENCRYPT_MODE, kljuc);
        byte[] sifrVr = c.doFinal(unos);
        return sifrVr;

    }

    public static void main(String[] args) throws Exception {

        Scanner input = new Scanner(System.in);

        Path unosFajl = Path.of("Fajlovi\\Tekst.txt");
        String unosT = Files.readString(unosFajl);


        System.out.print("Unesite kljuc: ");
        String unosK = input.next();

        byte[] tekst = unosT.getBytes();
        byte[] kljuc = unosK.getBytes();

        byte[] sifrovano = sifrovanje(tekst, kljuc);

        /*      ** Proba ispisa u programu **

        byte[] desifrovano = sifrovanje(sifrovano, kljuc);

        System.out.println("Sifrovana poruka: " + new String(sifrovano));
        System.out.println("Originalna poruka: " + new String(desifrovano));
        */

        Files.deleteIfExists(Paths.get("Fajlovi\\SifrovanaPoruka.txt"));
        
        PrintWriter out = new PrintWriter("Fajlovi\\SifrovanaPoruka.txt");
        out.println(new String(sifrovano));
        out.close();

    }
}