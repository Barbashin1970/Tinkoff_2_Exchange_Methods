import java.util.Scanner;

public class Main { // Задача 2

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("===================================================================");
        System.out.println("Введите курсы валют - целые числа через пробел - usd eur gbp ");
        System.out.println("===================================================================");
        int usd = scanner.nextInt();
        int eur = scanner.nextInt();
        int gbp = scanner.nextInt();
        System.out.println("==================================================================");
        System.out.println("Наличность - целые числа через пробел - usdCash eurCash gbpCash ");
        System.out.println("==================================================================");
        int usdCash = scanner.nextInt();
        int eurCash = scanner.nextInt();
        int gbpCash = scanner.nextInt();


        int count = 0; // счетчик для подсчета вариантов троек баланса

        int[] massiv = new int[7]; // создаем массив для обменных операций
        int[] massivNew;
        massiv[0] = usdCash;
        massiv[1] = usd;
        massiv[2] = eurCash;
        massiv[3] = eur;
        massiv[4] = gbpCash;
        massiv[5] = gbp;
        massiv[6] = count;
        System.out.println("================================================");
        System.out.println("=== До обменных операций ваш баланс такой ======");
        System.out.println("================================================");
        printMassiv(massiv);
        System.out.println("================================================");

        // перенесем-сконвертируем все банкноты в 1-ю валюту
        massivNew = changeBA(changeCB(massiv));
        int usdMax = massivNew[0]; // запомним максимум баланса поля 1 валюты

        // перенесем-сконвертируем все банкноты в 3-ю валюту
        //  - будем считать это базовым вариантом
        massivNew = changeBC(changeAB(massiv)); // откатим все в 3 валюту
        massivNew[6] = 0; // обнуляем счетчик вариантов count
        int change = 1; // 1 вариант у нас уже есть
        // теперь считаем количество обменов
        while (massivNew[0] < usdMax) {
            massivNew = changeCB(massivNew); // перенесли все банкноты из 3 в 2
            massivNew = changeOneStepBA(massivNew); // перенесли 1 банкноту в 1
            change = change + massivNew[6]; // запомним счетчик обменов
            massivNew[6] = 0; // обнулим счетчик в массиве
            massivNew = changeBC(massivNew); // все что осталось во 2 валюте переносим снова в 3 валюту
        }
        massivNew[6] = change;
        System.out.println("================================================");
        System.out.println("=== Провели обмен и теперь такая ситуация ======");
        System.out.println("================================================");
        printMassiv(massivNew);
        System.out.println("======================================================================");
        System.out.println("======= мы насчитали " + change + " вариантов троек Вашего баланса =======");
        System.out.println("======================================================================");
    }

    public static void printMassiv(int[] massiv) {
        System.out.println("У вас наличных USD = " + massiv[0]);
        System.out.println("Курс USD = " + massiv[1]);
        System.out.println("У вас наличных EUR = " + massiv[2]);
        System.out.println("Курс EUR = " + massiv[3]);
        System.out.println("У вас наличных GBP = " + massiv[4]);
        System.out.println("Курс GBP = " + massiv[5]);
        System.out.println("Проведено обменов валют = " + massiv[6]);
    }

    public static int[] changeAB(int[] massiv) {
        int[] mass = new int[7];
        int usdCash = massiv[0];
        int usd = massiv[1];
        int eurCash = massiv[2];
        int eur = massiv[3];
        int gbpCash = massiv[4];
        int gbp = massiv[5];
        int count = massiv[6];

        while (usdCash >= usd) {
            usdCash = usdCash - usd;
            eurCash = eurCash + eur;
        }

        mass[0] = usdCash;
        mass[1] = usd;
        mass[2] = eurCash;
        mass[3] = eur;
        mass[4] = gbpCash;
        mass[5] = gbp;
        mass[6] = count;
        return mass;
    }

    public static int[] changeBA(int[] massiv) {
        int[] mass = new int[7];
        int usdCash = massiv[0];
        int usd = massiv[1];
        int eurCash = massiv[2];
        int eur = massiv[3];
        int gbpCash = massiv[4];
        int gbp = massiv[5];
        int count = massiv[6];

        while (eurCash >= eur) {
            eurCash = eurCash - eur;
            usdCash = usdCash + usd;
            count = count + 1; // счетчик тут нужен посчитать варианты балансов троек валют
        }

        mass[0] = usdCash;
        mass[1] = usd;
        mass[2] = eurCash;
        mass[3] = eur;
        mass[4] = gbpCash;
        mass[5] = gbp;
        mass[6] = count;
        return mass;
    }

    public static int[] changeOneStepBA(int[] massiv) {
        int[] mass = new int[7];
        int usdCash = massiv[0];
        int usd = massiv[1];
        int eurCash = massiv[2];
        int eur = massiv[3];
        int gbpCash = massiv[4];
        int gbp = massiv[5];
        int count = massiv[6];

        eurCash = eurCash - eur;
        usdCash = usdCash + usd;
        count = count + 1; // счетчик тут нужен посчитать варианты балансов троек валют

        mass[0] = usdCash;
        mass[1] = usd;
        mass[2] = eurCash;
        mass[3] = eur;
        mass[4] = gbpCash;
        mass[5] = gbp;
        mass[6] = count;
        return mass;
    }

    public static int[] changeBC(int[] massiv) {
        int[] mass = new int[7];
        int usdCash = massiv[0];
        int usd = massiv[1];
        int eurCash = massiv[2];
        int eur = massiv[3];
        int gbpCash = massiv[4];
        int gbp = massiv[5];
        int count = massiv[6];

        while (eurCash >= eur) {
            eurCash = eurCash - eur;
            gbpCash = gbpCash + gbp;
        }

        mass[0] = usdCash;
        mass[1] = usd;
        mass[2] = eurCash;
        mass[3] = eur;
        mass[4] = gbpCash;
        mass[5] = gbp;
        mass[6] = count;
        return mass;
    }

    public static int[] changeCB(int[] massiv) {
        int[] mass = new int[7];
        int usdCash = massiv[0];
        int usd = massiv[1];
        int eurCash = massiv[2];
        int eur = massiv[3];
        int gbpCash = massiv[4];
        int gbp = massiv[5];
        int count = massiv[6];

        while (gbpCash >= gbp) {
            gbpCash = gbpCash - gbp;
            eurCash = eurCash + eur;
            count = count + 1; // счетчик тут нужен посчитать варианты балансов троек валют
        }

        mass[0] = usdCash;
        mass[1] = usd;
        mass[2] = eurCash;
        mass[3] = eur;
        mass[4] = gbpCash;
        mass[5] = gbp;
        mass[6] = count;
        return mass;
    }

}
