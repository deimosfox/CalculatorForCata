import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.TreeMap;
public class Main {

    public static int numbertranslatore(String number){
        String trueNumber = number.toUpperCase();
        switch (trueNumber){
            case ("I"):
                return 1;
            case ("II"):
                return 2;
            case ("III"):
                return 3;
            case ("IV"):
                return 4;
            case ("V"):
                return 5;
            case ("VI"):
                return 6;
            case ("VII"):
                return 7;
            case ("VIII"):
                return 8;
            case ("IX"):
                return 9;
            case ("X"):
                return 10;
            default:
                return -1;
        }

    }

    public static int operations(String operation, int num1, int num2) throws IOException {
        switch (operation){
            case ("+"):
                return num1 + num2;
            case ("-"):
                return num1 - num2;
            case ("*"):
                return num1 * num2;
            case ("/"):
                return num1 / num2;
            default:
                throw new IOException("Введена несуществующая арифметическая операция");
        }

    }

    public static String arabToRome(int num){
        String answer = "";
        HashMap<Integer,String> map = new HashMap<Integer,String>();
        map.put(1,"I");
        map.put(4,"IV");
        map.put(5,"V");
        map.put(9,"IX");
        map.put(10,"X");
        map.put(40,"XL");
        map.put(50,"L");
        map.put(90,"XC");
        map.put(100,"C");
        TreeMap<Integer, String> sortedMap = new TreeMap<>(map);

        while (num > 0){
            Integer prevKey = 0;
            String prevValue = "";
            for (HashMap.Entry<Integer,String> entry: sortedMap.entrySet()){
                Integer tmp = entry.getKey();
                if(tmp > num){
                    num -= prevKey;
                    answer += prevValue;
                    break;
                } else if (tmp == num) {
                    num -= tmp;
                    answer += entry.getValue();
                    break;
                } else {
                    prevKey = entry.getKey();
                    prevValue = entry.getValue();
                }
            }
            }

        return answer;
    }
    public static String calc(String input) throws IOException {
        String answ = "";
        //флажки 0 - Римская цифра, 1 - арабская
        int n1f = 0;
        int n2f = 0;

        // парсим строку по пробелам
        String[] subStr;
        subStr = input.split(" ");

        //Приводим строку к числу и выставляем флажок
        int n1 = numbertranslatore(subStr[0]);
        if(n1 == -1){
            try {
                n1 = Integer.parseInt(subStr[0]);
                if(n1 > 10){
                    throw  new IOException("Введеное число не должно превышать 10!!!");
                }
                if(n1 < 1){
                    throw  new IOException("Введеное число не должно быть менее единицы!!!");
                }
            }catch (NumberFormatException e){
                throw new NumberFormatException("Введенный 1ый аргумент не является числом");
            }

            n1f=1;
        }

        int n2 = numbertranslatore(subStr[2]);
        if(n2 == -1){
            try {
                n2 = Integer.parseInt(subStr[2]);
                if(n2 > 10){
                    throw  new IOException("Введеное число не должно превышать 10!!!");
                }
                if(n2 < 1){
                    throw  new IOException("Введеное число не должно быть менее единицы!!!");
                }
            }catch (NumberFormatException e){
                throw new NumberFormatException("Введенный 2ой аргумент не является числом");
            }
                n2f=1;
        }


        //выводим ответ
        if(n1f == n2f){
            int answnum = operations(subStr[1],n1,n2);
            if(n1f == 1){
            answ = Integer.toString(answnum) ;
            }
            else {
                if (answnum < 1){throw new NumberFormatException("Римские цифры не имеют отрицательных значений и нуля :(");}
                //преобразовать арабское число в римское
                answ = arabToRome(answnum);
            }
        } else {
            //сделать через исключение
            throw new IOException("Нельзя выполнить операцию между римской и Арабскими цифрами одновременно");

        }
     return answ;
    }


    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        System.out.println(calc(input));
    }
}