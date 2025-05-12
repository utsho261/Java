package utsho;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FootballTeamManager manager = new FootballTeamManager();

        while (true) {
            System.out.println("\n--- Football Team Manager ---");
            System.out.println("1. Add Player");
            System.out.println("2. Read Players");
            System.out.println("3. Update Player");
            System.out.println("4. Delete Player");
            System.out.println("5. Exit");
            System.out.print("Choose an option (1-5): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter player name to add: ");
                    String newPlayer = scanner.nextLine();
                    manager.addPlayer(newPlayer);
                    break;
                case 2:
                    manager.readPlayers();
                    break;
                case 3:
                    System.out.print("Enter the index of the player to update: ");
                    int updateIndex = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new player name: ");
                    String updatedPlayerName = scanner.nextLine();
                    manager.updatePlayer(updateIndex - 1, updatedPlayerName);
                    break;
                case 4:
                    System.out.print("Enter the index of the player to delete: ");
                    int deleteIndex = scanner.nextInt();
                    manager.deletePlayer(deleteIndex - 1);
                    break;
                case 5:
                    System.out.println("Exiting program...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }
}
