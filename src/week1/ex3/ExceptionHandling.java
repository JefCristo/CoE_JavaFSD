package week1.ex3;

import java.util.Scanner;

public class ExceptionHandling {
    public static void processInput() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter a number: ");
            double number = Double.parseDouble(scanner.nextLine());
            if (number == 0) {
                throw new ArithmeticException("Cannot calculate reciprocal of zero");
            }
            System.out.println("Reciprocal: " + (1 / number));
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        processInput();
    }
}
