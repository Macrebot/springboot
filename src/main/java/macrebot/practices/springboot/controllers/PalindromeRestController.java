package macrebot.practices.springboot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to verified palindromes.
 */
@RestController
public class PalindromeRestController {

    /**
     * Endpoint to verified if a word is a palindrome.
     * 
     * @param word The word to verified
     * @return A message that tells if a word is a palindrome or not.
     */
    @GetMapping("/palindrome/{word}")
    public String palindrome(@PathVariable String word) {
        if (isPalindrome(word)) {
            return "The word '" + word + "' is a palindrome.";
        }
        return "the word '" + word + "' is NOT  a palindrome.";
    }

    /**
     * Method to verified is a word is a palindrome
     * 
     * @param word The word to verified.
     * @return true if the word is a palindrome, false if it is not.
     */
    private boolean isPalindrome(String word) {
        int word_length = word.length();
        for (int i = 0; i <= word_length / 2; i++) {
            System.out.println("i: " + i + ". J: " + (word_length - i - 1));
            if (word.charAt(i) != word.charAt(word_length - i - 1)) {
                return false;
            }
        }
        return true;
    }

}
