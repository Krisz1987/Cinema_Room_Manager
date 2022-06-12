package cinema;

import java.util.Scanner;

public class Cinema {

    public static Scanner scanner;
    public static int commandNumber;
    public static int row;
    public static int column;
    public static String[][] cinema;
    public static int seats;
    public static int price;
    public static boolean end;
    public static int numberOfTickets;
    public static double percentage;
    public static int currentIncome;
    public static int totalIncome;


    public static void main(String[] args) {
        getStartingValue();
        System.out.println(totalIncome);

        do {
            commandList();
        } while (!end);

    }

    private static void getStartingValue() {
        scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        row = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        column = scanner.nextInt();
        seats = row * column;
        price = 0;
        end = false;
        cinema = new String[row][column];
        arrayFill();
        numberOfTickets = 0;
        percentage = 0;
        currentIncome = 0;
        totalIncome = calculateIncome();
    }

    private static int calculateIncome() {
        if (seats <= 60) {
            totalIncome = seats * 10;
        } else {
            totalIncome = ((row - row / 2) * column * 8) + (row / 2 * column * 10);
        }
        return totalIncome;
    }

    private static void arrayFill() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                cinema[i][j] = "S";
            }
        }
    }

    private static void commandList() {
        System.out.println();
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");

        commandNumber = scanner.nextInt();
        selectCommand();
    }

    private static void getStatistics() {
        System.out.println("Number of purchased tickets: " + numberOfTickets);
        System.out.printf("Percentage: %.2f%% %n", percentage);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }

    private static void selectCommand() {
        switch (commandNumber) {
            case 1: drawCinema(row, column, cinema);
            break;
            case 2: buyTicket();
            break;
            case 3: getStatistics();
            break;
            case 0: exit();
            break;
            default:
                System.out.println("Wrong input!");
        }
    }

    private static void exit() {
        end = true;
    }

    private static void buyTicket() {
        int selectedRow = 0;
        int selectedSeat = 0;
        boolean isFree = false;
        do {
            System.out.println();
            System.out.println("Enter a row number:");
            selectedRow = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            selectedSeat = scanner.nextInt();
            if (selectedRow > row || selectedSeat > column) {
                System.out.println("Wrong input!");
                isFree= false;
            }else if (cinema[selectedRow - 1][selectedSeat - 1].equals("B")) {
                System.out.println("That ticket has already been purchased!");
                isFree = false;
            } else {
                isFree = true;
            }
        } while (!isFree);

        cinema[selectedRow - 1][selectedSeat - 1] = "B";

        if (seats <= 60 || selectedRow <= row / 2) {
            price = 10;
        } else {
            price = 8;
        }

        numberOfTickets++;
        currentIncome += price;
        percentage = ((double)numberOfTickets / seats * 100);
        System.out.println();
        System.out.println("Ticket price: $" + price);
    }

    private static void drawCinema(int row, int column, String[][] cinema) {
        System.out.println();
        System.out.println("Cinema:");
        for (int i = 0; i <= column; i++) {
            if (i > 0) {
                System.out.print(i + " ");
            } else {
                System.out.print("  ");
            }
        }
        System.out.println();
        for (int i = 0; i < row; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < column; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
    }
}