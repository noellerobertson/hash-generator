/**
 * Java Practice Project, Hash Generator.
 * */

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class hashgenerator {

    // This portion converts a byte array into a hex string.
    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();

        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b); // mask to keep only last 8 bits
            if (hex.length() == 1) hexString.append('0'); // pad leading zero if needed
            hexString.append(hex);
        }

        return hexString.toString();
    }

    // Generate hash using specified algorithm.
    public static String generateHash(String input, String algorithm) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm); // loads the hashing algorithm
            byte[] digest = md.digest(input.getBytes()); // default charset UTF-8
            return bytesToHex(digest);

        } catch (NoSuchAlgorithmException e) {
            return "Invalid algorithm: " + algorithm;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String title = """
                _  _ ____ ____ _  _    ____ ____ _  _ ____ ____  ____ ___ ____ ____
                |__| |__| [__  |__|    | __ |___ |\\ | |___ |__/  |__|  |  |  | |__/
                |  | |  | ___] |  |    |__] |___ | \\| |___ |  \\  |  |  |  |__| |  \\
                """;
        System.out.println(title);
        System.out.println("Welcome to Hash Generator!\n");
        System.out.println("Type 'exit' to quit.\n");

        // Continuously ask for strings to hash until prompted to exit.
        while (true) {
            System.out.print("Enter a string to hash: ");
            String input = scanner.nextLine();

            System.out.print("Choose an algorithm (MD5, SHA-256, SHA-512): ");
            String algorithm = scanner.nextLine();

            // Exit.
            if (algorithm.equalsIgnoreCase("exit")) {
                System.out.println("Exiting Hash Generator...");
                break;
            }

            String hash = generateHash(input, algorithm);
            System.out.println(algorithm + " hash: " + hash);
        }

        scanner.close();
    }
}