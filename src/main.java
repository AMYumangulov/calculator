import java.util.Scanner;
import java.util.Arrays;

public class main {
    public static char[] rimNmbArr = {'I', 'V', 'X'};
    public static int[] arabNmbArr = {1, 5, 10};
//    public static String[] rimStrArr = {"I", "II", "e", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XII"};

    public static String arab2Roman(int arab) {
        String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};

        String te = tens[arab / 10 % 10];
        String o = ones[arab % 10];

        return te + o;
    }

    public static int roman2Arab(String roman) {
        int[] indexRim = new int[5];
        int rimExt = 0;
        for (int i = 0; i < roman.length(); i++) {
            for (int r = 0; r < rimNmbArr.length; r++) {
                if (rimNmbArr[r] == roman.charAt(i)) {
                    indexRim[i] = arabNmbArr[r];
                }
            }
        }
        for (int r = 0; r < indexRim.length - 1; ++r) {
            if (indexRim[r] < indexRim[r + 1]) {
                rimExt = rimExt - indexRim[r];
            } else {
                rimExt = rimExt + indexRim[r];
            }
        }
        return rimExt;
    }

    public static void main(String[] args) {

        char[] exprArr = {'/', '*', '-', '+'};
        char[] numbArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        Scanner in = new Scanner(System.in);
        String expression = in.nextLine();
        String first = "";
        int firstInt;
        String exprStr = "";
        String second = "";
        int secondInt;
        int[] romanNumb = new int[2];
        int qtError = 0;

        for (int i = 0; i < expression.length(); i++) {
            for (int j = 0; j < numbArr.length; j++) {
                if (numbArr[j] == expression.charAt(i)) {
                    if (exprStr == "") {
                        first = first + numbArr[j];
                        romanNumb[0] = 0;
                        break;
                    } else {
                        second = second + numbArr[j];
                        romanNumb[1] = 0;
                        break;
                    }
                }
            }

            for (int j = 0; j < rimNmbArr.length; j++) {
                if (rimNmbArr[j] == expression.charAt(i)) {
                    if (exprStr == "") {
                        first = first + rimNmbArr[j];
                        romanNumb[0] = 1;
                        break;
                    } else {
                        second = second + rimNmbArr[j];
                        romanNumb[1] = 1;
                        break;
                    }
                }
            }

            for (int j = 0; j < exprArr.length; j++) {
                if (exprArr[j] == expression.charAt(i)) {
                    if (exprStr != "") {
                        throw new UnsupportedOperationException("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
                    }
                    exprStr = exprStr + exprArr[j];
                    break;
                }
            }

        }

        if (romanNumb[0] != romanNumb[1]) {
            qtError = 1;
        }

        if (qtError > 0) {
            throw new UnsupportedOperationException("используются одновременно разные системы счисления!");
        }
        if (romanNumb[0] == 1) {
            firstInt = roman2Arab(first);
            secondInt = roman2Arab(second);
        } else {


            try {
                firstInt = Integer.parseInt(first);
                secondInt = Integer.parseInt(second);
            } catch (NumberFormatException e) {
                firstInt = 0;
                secondInt = 0;
            }
        }

        try {
            if (exprStr.charAt(0) == '+') {
                if (romanNumb[0] == 1) {
                    System.out.println(arab2Roman(firstInt + secondInt));
                } else {
                    System.out.println(firstInt + secondInt);
                }

            }
        } catch (StringIndexOutOfBoundsException s) {
            throw new UnsupportedOperationException("строка не является математической операцией");
        }

        if (exprStr.charAt(0) == '-') {
            if (firstInt - secondInt < 0) {
                throw new UnsupportedOperationException("в римской системе нет отрицательных чисел");
            }
            if (romanNumb[0] == 1) {
                System.out.println(arab2Roman(firstInt - secondInt));
            } else {
                System.out.println(firstInt - secondInt);
            }
        }
        if (exprStr.charAt(0) == '*') {
            if (romanNumb[0] == 1) {
                System.out.println(arab2Roman(firstInt * secondInt));
            } else {
                System.out.println(firstInt * secondInt);
            }
        }
        if (exprStr.charAt(0) == '/') {
            if (romanNumb[0] == 1) {
                System.out.println(arab2Roman(firstInt / secondInt));
            } else {
                System.out.println(firstInt / secondInt);
            }
        }

        in.close();
    }
}
