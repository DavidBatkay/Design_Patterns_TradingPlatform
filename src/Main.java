import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TradingSystemFacade system = new TradingSystemFacade();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n1. Register\n2. Login\n3. Logout\n4. View Market\n5. Buy\n6. Sell\n7. View Portfolio\n8. View History\n9. Set Auto Trade\n10. Sim Price Change\n0. Exit");
            System.out.print("Choose: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Username: ");
                    String regUser = scanner.nextLine();
                    System.out.print("Password: ");
                    String regPass = scanner.nextLine();
                    system.registerUser(regUser, regPass);
                    System.out.println("Registered.");
                    break;
                case "2":
                    System.out.print("Username: ");
                    String logUser = scanner.nextLine();
                    System.out.print("Password: ");
                    String logPass = scanner.nextLine();
                    if (system.loginUser(logUser, logPass)) {
                        System.out.println("Logged in.");
                    } else {
                        System.out.println("Invalid credentials.");
                    }
                    break;
                case "3":
                    system.logoutUser();
                    System.out.println("Logged out.");
                    break;
                case "4":
                    system.printMarket();
                    break;
                case "5":
                    System.out.print("Symbol: ");
                    String buySym = scanner.nextLine();
                    System.out.print("Quantity: ");
                    int buyQty = Integer.parseInt(scanner.nextLine());
                    System.out.print("Strategy (day/long): ");
                    String strat = scanner.nextLine();
                    system.buy(buySym, buyQty, strat);
                    break;
                case "6":
                    System.out.print("Symbol: ");
                    String sellSym = scanner.nextLine();
                    System.out.print("Quantity: ");
                    int sellQty = Integer.parseInt(scanner.nextLine());
                    system.sell(sellSym, sellQty);
                    break;
                case "7":
                    system.printPortfolio();
                    break;
                case "8":
                    system.printHistory();
                    break;
                case "9":
                    System.out.print("Symbol: ");
                    String autoSym = scanner.nextLine();
                    System.out.print("Buy Threshold: ");
                    double buyTh = Double.parseDouble(scanner.nextLine());
                    System.out.print("Sell Threshold: ");
                    double sellTh = Double.parseDouble(scanner.nextLine());
                    system.setAutoTrading(autoSym, buyTh, sellTh);
                    break;
                case "10":
                    System.out.print("Symbol: ");
                    String simSym = scanner.nextLine();
                    System.out.print("New Price: ");
                    double newPrice = Double.parseDouble(scanner.nextLine());
                    system.simulatePriceChange(simSym, newPrice);
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
        scanner.close();
    }
}
