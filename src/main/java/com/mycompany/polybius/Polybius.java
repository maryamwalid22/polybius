 package com.mycompany.polybius;

import java.util.Scanner;

public class Polybius {
    // Define the table for the Playfair cipher
    static char[][] table = new char[5][5];
    
    // Method to create the Playfair cipher table
    static void createTable() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                // Skip "J" since it is not included in the table
                if (i * 5 + j + 65 != 74) {
                    table[i][j] = (char) (i * 5 + j + 65);
                }
            }
        }
        // Add "K" to the table
        table[1][4] = 'K';
        // Fill the rest of the table with the remaining letters
        for (int i = 2; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                table[i][j] = (char) (i * 5 + j + 65 + 1);
            }
        }
    }
    
    // Method to encrypt plaintext using the Playfair cipher
    static String encrypt(String pt) {
        String encpt = "";
        char[] encpt1 = pt.toCharArray();
        for (int i = 0; i < pt.length(); i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if (encpt1[i] == 'J') {
                        // Replace "J" with "I" (represented by "24")
                        encpt = encpt + "24";
                        break;
                    }
                    if (table[j][k] == encpt1[i]) {
                        // Replace the letter with its location in the table
                        encpt = encpt + Integer.toString(j + 1) + Integer.toString(k + 1);
                        break;
                    }
                }
            }
        }
        return encpt;
    }
    
    // Method to decrypt ciphertext using the Playfair cipher
    static String decrypt(String encpt) {
        String decpt = "";
        char[] decpt1 = encpt.toCharArray();
        for (int i = 0; i < encpt.length(); i = i + 2) {
            int row = Integer.parseInt(String.valueOf(decpt1[i]));
            int col = Integer.parseInt(String.valueOf(decpt1[i + 1]));
            // Look up the letter in the table using its location
            decpt = decpt + Character.toString(table[row - 1][col - 1]);
        }
        return decpt;
    }
    
    public static void main(String[] args) {
        String pt, encpt, decpt;
         // Create the Playfair cipher table
        createTable();
        
        System.out.print("Enter plaintext: ");
        Scanner scanner = new Scanner(System.in);
        pt = scanner.nextLine().toUpperCase();
        
       
        // Encrypt the plaintext and print the ciphertext
        encpt = encrypt(pt);
        System.out.println("Encrypted text: " + encpt);
        
        // Decrypt the ciphertext and print the plaintext
        decpt = decrypt(encpt);
        System.out.println("Decrypted text: " + decpt);
    }
}