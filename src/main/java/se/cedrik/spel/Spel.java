package se.cedrik.spel;

import se.cedrik.spel.model.Burglar;
import se.cedrik.spel.model.Resident;

import java.util.Scanner;

public class Spel {
    private static final String HALL = "hall";
    private static final String KONTOR = "kontor";
    private static final String VARDAGSRUM = "vardagsrum";
    private static final String KÖK = "kök";
    private static final String SOVRUM = "sovrum";

    private String currentLocation = VARDAGSRUM;
    private boolean running = true;
    private boolean fryingPanFound = false;
    private boolean burglarKnockedOut = false;

    private Resident resident = new Resident("boende", 12, 3);
    private Burglar burglar = new Burglar("inbrottstjuv", 12, 4);

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        welcomeMenu();

        while (running && resident.isConscious()) {
            System.out.println("Du är i: " + currentLocation);
            Location();

            if (running) {
                System.out.print("Vad vill du göra? ");
                String userInput = scanner.nextLine().toLowerCase();

                switch (currentLocation) {
                    case VARDAGSRUM -> vardagsrum(userInput);
                    case HALL -> hall(userInput);
                    case KONTOR -> kontor(userInput);
                    case KÖK -> kök(userInput);
                    case SOVRUM -> sovrum(userInput);
                    default -> System.out.println("Ogiltigt kommando, försök igen.");
                }
            }
        }

        scanner.close();
        System.out.println("Spelet är avslutat.");
    }

    private void Location() {
        switch (currentLocation) {
            case HALL -> {
                if (!burglarKnockedOut) {
                    System.out.println("Du ser en inbrottstjuv här!");
                } else {
                    System.out.println("Tjuven är knockad gå till kontor för att ringa polisen");
                }
            }
            case KONTOR -> {
                if (burglarKnockedOut) {
                    System.out.println("Du ringer polisen och spelet är över");
                    running = false;
                } else {
                    System.out.println("du kan inte ringa polisen just nu.");
                }
            }
            case KÖK -> {
                if (!fryingPanFound) {
                    System.out.println("Du hittade en stekpanna och kan göra mer skada.");
                    resident.addDamage(3);
                    fryingPanFound = true;
                } else {
                    System.out.println("stek panna är taken.");
                }
            }
            case SOVRUM -> System.out.println("Sovrummet är tomt ingen tjuv här.");
        }
    }

    private void vardagsrum(String userInput) {
        switch (userInput) {
            case HALL -> currentLocation = HALL;
            case KONTOR -> currentLocation = KONTOR;
            case KÖK -> currentLocation = KÖK;
            case SOVRUM -> currentLocation = SOVRUM;
            case "quit" -> {
                System.out.println("Du avslutar spelet.");
                running = false;
            }
            default -> System.out.println("Ogiltigt, försök igen.");
        }
    }

    private void hall(String userInput) {
        if (userInput.equals("fight") && !burglarKnockedOut) {
            fightOneRound();
        } else if (userInput.equals(VARDAGSRUM)) {
            currentLocation = VARDAGSRUM;
        } else if (userInput.equals(KONTOR) && burglarKnockedOut) {
            currentLocation = KONTOR;
        } else if (burglarKnockedOut) {
            System.out.println("Tjuven är knockad gå till kontoret för att ringa polsen");
        } else {
            System.out.println("du kan bara slåss genom att skriva genom att skriva 'fight'.");
        }
    }

    private void kontor(String userInput) {
        if (userInput.equals(VARDAGSRUM)) {
            currentLocation = VARDAGSRUM;
        } else if (burglarKnockedOut) {
            System.out.println("Du ringer polisen och spelet är över");
            running = false;
        } else {
            System.out.println("Du kan inte ringa just nu.");
        }
    }

    private void kök(String userInput) {
        if (userInput.equals(VARDAGSRUM)) {
            currentLocation = VARDAGSRUM;
        } else {
            System.out.println("Du kan bara gå tillbaka till vardagsrummet.");
        }
    }

    private void sovrum(String userInput) {
        if (userInput.equals(VARDAGSRUM)) {
            currentLocation = VARDAGSRUM;
        } else {
            System.out.println("Du kan bara gå tillbaka till vardagsrummet.");
        }
    }

    private void fightOneRound() {
        while (resident.isConscious() && burglar.isConscious()) {
            resident.punch(burglar);
            System.out.println("Du slår tjuven och gör " + resident.getDamage() + " skada.");
            System.out.println("Tjuvens hälsa: " + burglar.getHealth());

            if (!burglar.isConscious()) {
                System.out.println("Du har knockat tjuven Gå till kontoret och ring polisen.");
                burglarKnockedOut = true;
                return;
            }

            burglar.punch(resident);
            System.out.println("Tjuven slår tillbaka och gör " + burglar.getDamage() + " skada.");
            System.out.println("Din hälsa: " + resident.getHealth());

            if (!resident.isConscious()) {
                System.out.println("Du blev knockad, Spelet är över.");
                running = false;
                return;
            }
        }
    }

    private void welcomeMenu() {
        System.out.println("Välkommen till Äventyrspelet!");
        System.out.println("Navigera mellan rummen för att knocka tjuven och ringa polisen.");
        System.out.println("Skriv 'hall', 'kontor', 'kök', 'sovrum' eller 'vardagsrum' för att röra dig.");
        System.out.println("Skriv 'fight' i hallen för att slåss mot tjuven.");
        System.out.println("___________________________________________________");
        System.out.println("Du vaknar upp på soffan i vardagsrummet och du hör ett ljud, en inbrottstjuv är i ett av rummen.");
    }
}
