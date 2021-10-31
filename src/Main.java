import java.util.Scanner;

public class Main {
    private static int minIncome = 200000;               // минимальный доход
    private static int maxIncome = 900000;               // максимальный доход

    private static int officeRentCharge = 140000;        // аренда офиса
    private static int telephonyCharge = 12000;          // оплата за телефон
    private static int internetAccessCharge = 7200;      // оплата за интернет

    private static int assistantSalary = 45000;          // зарплата помошнику
    private static int financeManagerSalary = 90000;     // зарплата бухгалтеру

    private static double mainTaxPercent = 0.24;         // процент налогов
    private static double managerPercent = 0.15;         // процент менеджера

    private static double minInvestmentsAmount = 100000; // мин. сумма инвестиций

    public static void main(String[] args) {
        while (true) {
            System.out.println("Введите сумму доходов компании за месяц " + // вывод в консоль
                "(от 200 до 900 тысяч рублей): ");                          // значение диапазона дохода
            int income = (new Scanner(System.in)).nextInt();                // ввод в консоль суммы доходов

            if (!checkIncomeRange(income)) {                    // если не верный ввод диапазона дохода
                continue;                                       // начать итерацию цикла сначала
            }

            double managerSalary = income * managerPercent; // зарплата менеджера = доход * 0.15
            double pureIncome = income - managerSalary -    // чистый доход = доход - зарплата менеджера -
                calculateFixedCharges();                    // фиксированные платежи
            double taxAmount = mainTaxPercent * pureIncome; // сумма налогов = процент налогов
            double pureIncomeAfterTax = pureIncome - taxAmount; // чистый доход после уплаты налогов = чистый доход - сумма налогов

            boolean canMakeInvestments = pureIncomeAfterTax >= // возможность инвестиции = чистый доход после уплаты налогов >= мин. сумма инвестиций
                minInvestmentsAmount;

            System.out.println("Зарплата менеджера: " + managerSalary); // вывод зарплаты менеджера
            System.out.println("Общая сумма налогов: " +                // вывод общей суммы налогов
                (taxAmount > 0 ? taxAmount : 0));                       // сумма налогов > 0 иначе 0
            System.out.println("Компания может инвестировать: " +       // возможность инвестиции
                (canMakeInvestments ? "да" : "нет"));                   // да нет
            if (pureIncome < 0) {                                                   // если чистый доход < 0
                System.out.println("Бюджет в минусе! Нужно срочно зарабатывать!");  // то вывод
            }
        }
    }

    private static boolean checkIncomeRange(int income) {         // проверка ввода диапазона дохода
        if (income < minIncome) {                                 // если ввели меньше минимального дохода
            System.out.println("Доход меньше нижней границы");    // то вывести в консоль
            return false;                                         // вернуть ложь
        }
        if (income > maxIncome) {                                 // если ввели больше максимального дохода
            System.out.println("Доход выше верхней границы");     // то вывести в консоль

            return false;                                         // вернуть ложь
        }
        return true;                                              // вернуть истину
    }

    private static int calculateFixedCharges() {                  // фиксированные платежи
        return officeRentCharge +                                 // вернуть аренда офиса +
            telephonyCharge +                                     // оплата за телефон +
            internetAccessCharge +                                // оплата за интернет +
            assistantSalary +                                     // зарплата помошнику +
            financeManagerSalary;                                 // зарплата бухгалтеру
    }
}
