package com.example.numberguessgame;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Controller
public class GameController {

    private int targetNumber;
    private int attemptsLeft;

    public GameController() {
        resetGame();
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("attemptsLeft", attemptsLeft);
        return "index";
    }

    @PostMapping("/guess")
    public String guess(@RequestParam int guess, Model model) {
        String message;
        if (guess == targetNumber) {
            message = "Congratulations! You guessed it right!";
            resetGame();
        } else {
            attemptsLeft--;
            if (attemptsLeft == 0) {
                message = "Sorry, you've run out of attempts. The number was " + targetNumber;
                resetGame();
            } else if (guess < targetNumber) {
                message = "Too low!";
            } else {
                message = "Too high!";
            }
        }
        model.addAttribute("message", message);
        model.addAttribute("attemptsLeft", attemptsLeft);
        return "index";
    }

    private void resetGame() {
        targetNumber = new Random().nextInt(100) + 1;
        attemptsLeft = 10;
    }
}
