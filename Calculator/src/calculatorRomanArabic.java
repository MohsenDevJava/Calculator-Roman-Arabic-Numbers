import java.util.*;

public class calculatorRomanArabic {

    public static void main(String[] args) {
        String[] parts = get_Data().split(" ");
        String firstNumber = String.valueOf((parts[0]));
        char operAtor = parts[1].charAt(0);
        String secoundNumber = String.valueOf((parts[2]));

        if (isInt(firstNumber) == isInt(secoundNumber)) {
            if (parts.length > 3) {
                throw new ArithmeticException("The format of the mathematical operation does not satisfy the task" +
                        " - two operands and one operator (+, -, /, *)");
            }
            do {
                try {
                    int int1 = Integer.parseInt(firstNumber);
                    int int2 = Integer.parseInt(secoundNumber);
                    if (int1 > 10 || int2 > 10) {
                        System.out.println("The calculator must accept numbers from 1 to 10 inclusive");
                    } else {
                        int result = calculate(int1, int2, operAtor);
                        System.out.printf("The result is: %d", result);
                    }
                    break;
                } catch (Exception e) {
                    int num1 = romanToInteger(firstNumber);
                    int num2 = romanToInteger(secoundNumber);
                    int resultRoman = calculate(num1, num2, operAtor);
                    String finResult = arabicToRoman(resultRoman);
                    System.out.println("The result is: " + finResult);
                    break;
                }
            } while (true);
        } else {
            System.out.println("Different number systems are used at the same time");
        }
    }

    public static int romanToInteger(String romanNumber) {
        Map<Character, Integer> numbersMap = new HashMap<>();
        numbersMap.put('I', 1);
        numbersMap.put('V', 5);
        numbersMap.put('X', 10);
        numbersMap.put('L', 50);
        numbersMap.put('C', 100);
        numbersMap.put('D', 500);
        numbersMap.put('M', 1000);

        int result = 0;

        for (int i = 0; i < romanNumber.length(); i++) {
            char ch = romanNumber.charAt(i);

            if (i > 0 && numbersMap.get(ch) > numbersMap.get(romanNumber.charAt(i - 1))) {
                result += numbersMap.get(ch) - 2 * numbersMap.get(romanNumber.charAt(i - 1));
            } else
                result += numbersMap.get(ch);
        }
        return result;
    }

    public static String get_Data() {
        Scanner keyboard = new Scanner(System.in);
        String firstInput;
        System.out.println("Enter a Number and Use spaces between numbers and press Enter.");
        firstInput = keyboard.nextLine();
        firstInput = firstInput.toUpperCase();

        return firstInput;
    }

    public static int calculate(int number1, int number2, char operAtor) {
        int resualt = 0;
        double resualtDiv = 0;
        switch (operAtor) {
            case '+': {
                resualt = number1 + number2;
                break;
            }
            case '-': {
                resualt = number1 - number2;
                break;
            }
            case '*': {
                resualt = number1 * number2;
                break;
            }
            case '/': {
                resualtDiv = (double) number1 / number2;
                return (int) resualtDiv;
            }
            default:
                System.out.println("Invalid operator!!!");
                break;
        }
        return resualt;
    }

    public static String arabicToRoman(int number) {
        if (number < 0 || 4000 <= number) {
            throw new IllegalArgumentException();
        }
        String[] nums = {"I", "V", "X", "L", "C", "D", "M"};
        int numCounter = 0;
        String result = "";
        StringBuilder partResult = new StringBuilder();
        int numeral;
        String stringNumber = String.valueOf(number);
        for (int i = stringNumber.length() - 1; i >= 0; i--) {
            partResult.delete(0, partResult.length());
            numeral = Integer.parseInt(stringNumber.substring(i, i + 1));
            if (1 <= numeral && numeral < 4) {
                for (int j = 0; j < numeral; j++) {
                    partResult.append(nums[numCounter]);
                }
                numCounter += 2;
            } else if (4 <= numeral && numeral < 9) {
                numCounter += 2;
                if (numeral == 4) {
                    partResult.append(nums[numCounter - 2]);
                }
                partResult.append(nums[numCounter - 1]);
                for (int j = 0; j < (numeral - 5); j++) {
                    partResult.append(nums[numCounter - 2]);
                }
            } else if (numeral == 9) {
                numCounter += 2;
                partResult.append(nums[numCounter - 2] + nums[numCounter]);
            } else if (numeral == 0) {
                numCounter += 2;
            }
            result = partResult.append(result).toString();
        }
        return result;
    }

    public static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

