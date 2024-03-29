package application;

import java.util.*;

public class DealOrNoDeal {
    private final double[] caseValues = {0.01, 1, 5, 10, 25, 50, 75, 100, 200, 300, 400, 500, 750, 1000, 5000, 10000, 25000, 50000, 75000, 100000, 200000, 300000, 400000, 500000, 750000, 1000000};

    // Ich benutze eine HashMap, um die Koffer und ihre Werte zu speichern.
    private final Map<Integer, Double> cases = new HashMap<>();

    private boolean debugMode = false;
    private int chosenCase = -1;

    public void initGame() {
        // Ich initialisiere das Spiel.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to play in debug mode? (yes/no)");
        String debugInput = scanner.nextLine();
        if (debugInput.equalsIgnoreCase("yes")) {
            debugMode = true;
        }
        if (debugMode) {
            for (int i = 1; i <= 26; i++) {
                cases.put(i, caseValues[i - 1]);
            }
        } else {
            List<Double> shuffledValues = new ArrayList<>();
            for (double val : caseValues) {
                shuffledValues.add(val);
            }
            Collections.shuffle(shuffledValues);
            for (int i = 1; i <= 26; i++) {
                cases.put(i, shuffledValues.get(i - 1));
            }
        }
    }

    public void chooseInitialCase() {
        // Der Spieler wählt seinen ersten Koffer.
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose your initial case (1-26):");
            try {
                int caseNumber = Integer.parseInt(scanner.nextLine());
                if (caseNumber >= 1 && caseNumber <= 26) {
                    chosenCase = caseNumber;
                    System.out.println("You've chosen case #" + caseNumber);
                    break;
                } else {
                    System.out.println("Invalid choice. Please choose a number between 1 and 26.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    public void playRound(int round) {
        // Ich führe eine Runde des Spiels durch.
        int[] casesToEliminate = {6, 5, 4, 3, 2, 1, 1, 1, 1};
        int casesThisRound = casesToEliminate[round - 1];
        System.out.println("Round " + round);
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < casesThisRound; i++) {
            while (true) {
                System.out.println("Choose a case to eliminate (1-26):");
                try {
                    int caseNumber = Integer.parseInt(scanner.nextLine());
                    if (cases.containsKey(caseNumber) && caseNumber != chosenCase) {
                        System.out.println("Eliminated case #" + caseNumber + ": $" + cases.get(caseNumber));
                        cases.remove(caseNumber);
                        break;
                    } else {
                        System.out.println("Invalid choice. Please choose an available number between 1 and 26 that you haven't chosen yet.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                }
            }
        }
        double offer = calculateBankOffer(round);
        System.out.println("Bank offer: $" + offer);

        // Ich zeige die verbleibenden Koffer an.
        System.out.println("Remaining cases: " + cases.keySet());

        while (true) {
            System.out.println("Deal or No Deal? (deal/no)");
            String dealInput = scanner.nextLine();
            if (dealInput.equalsIgnoreCase("deal")) {
                System.out.println("Congratulations! You've won $" + offer);
                System.exit(0);
            } else if (dealInput.equalsIgnoreCase("no")) {
                System.out.println("No deal! Let's continue.");
                break;
            } else {
                System.out.println("Invalid choice. Please choose 'deal' or 'no'.");
            }
        }
    }

    private double calculateBankOffer(int round) {
        // Ich berechne das Angebot der Bank.
        double totalValue = 0;
        for (double value : cases.values()) {
            totalValue += value;
        }
        double expectedValue = totalValue / cases.size();
        return (expectedValue * round) / 10;
    }

    public void finalStage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Final Stage! Only two cases remain.");
        System.out.println("Would you like to switch your case? (yes/no)");
        String switchInput = scanner.nextLine();
        if (switchInput.equalsIgnoreCase("yes")) {
            for (int caseNumber : cases.keySet()) {
                if (caseNumber != chosenCase) {
                    chosenCase = caseNumber;
                    break;
                }
            }
        }
        System.out.println("Congratulations! You've won $" + cases.get(chosenCase));
    }

}
