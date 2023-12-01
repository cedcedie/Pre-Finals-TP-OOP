import java.util.Random;
import java.util.Scanner;

interface Player {
    String getName();

    void setName(String name);
}

interface StoryMode {
    void playStory();
}

interface SurvivalMode {
    void playSurvival();
}

class Game implements Player, StoryMode, SurvivalMode {
    private String name;
    private int health = 100;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private boolean Monster(int monsterLevel) {
        int monsterHealth = monsterLevel * 20;
        int pHealth = health;

        while (pHealth > 0) {
            System.out.println("\n--- Monster Level " + monsterLevel + " ---");
            System.out.println("Player Health: " + pHealth);

            System.out.println("Choose your action:");
            System.out.println("1. Attack");
            System.out.println("2. Defend");

            Scanner sc = new Scanner(System.in);
            int playerChoice = sc.nextInt();

            if (playerChoice == 1) {
                int playerDamage = Math.max(new Random().nextInt(monsterLevel * 7) + 1, 1);
                System.out.println("You dealt " + playerDamage + " damage!");
                monsterHealth -= playerDamage;
            } else if (playerChoice == 2) {
                System.out.println("You chose to defend. Taking reduced damage next turn.");
            } else {
                System.out.println("Invalid choice. Please try again.");
            }

            System.out.println("Monster Health: " + monsterHealth);

            int monsterAttackBaseChance = 13;
            int monsterAttackChance = monsterAttackBaseChance + (monsterLevel * 2);
            int monsterDamage = (new Random().nextInt(100) < monsterAttackChance) ? monsterLevel * 10 : 0;

            System.out.println("The monster dealt " + monsterDamage + " damage!");
            pHealth -= monsterDamage;

            if (pHealth > 0 && monsterHealth <= 0) {
                monsterLevel++;
                monsterHealth = monsterLevel * 20;
                System.out.println("You defeated the monster! Moving on to Level " + monsterLevel + ".");
            }
        }

        System.out.println("You were defeated by the monster at Level " + monsterLevel + ".");
        return false;
    }

    private void tryAgain() {
        System.out.println("1. Try again");
        System.out.println("2. Exit");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        if (choice == 1) {
            playStory();
        } else if (choice == 2) {
            System.out.println("Exiting the game.");
            sc.close();
            System.exit(0);
        }
    }

    public void playStory() {
        Dialogue("*I feel dizzy*");
        Dialogue("*What am I doing?*");
        Dialogue("*Feels like I am supposed to do something*");
        Dialogue("*Huh, what is that voice?*");
        Dialogue("Voice is beginning to be louder");
        Dialogue("Wake up Adventurer " + getName() + "! Quick, we are surrounded by monsters. You have to get up.");

        if (!Monster(1)) {
            System.out.println("Game Over. You were defeated. Try again?");
            tryAgain();
            return;
        }

        Dialogue("*What am I supposed to do? I really forgot.*");
        Dialogue("Another soldier says that we have to get to the tower to save the princess because it's crawled with monsters.");

        if (!Monster(2)) {
            System.out.println("Game Over. You were defeated. Try again?");
            tryAgain();
            return;
        }

        Dialogue("*These monsters are not a joke.*");
        Dialogue("Princess screams, 'Coming, princess! I'll save you!'");

        if (!Monster(3)) {
            System.out.println("Game Over. You were defeated. Try again?");
            tryAgain();
            return;
        }

        Dialogue("*I'm near, I can feel it. Hold on a little longer, princess.*");

        if (!Monster(4)) {
            System.out.println("Game Over. You were defeated. Try again?");
            tryAgain();
            return;
        }

        Dialogue("*Horde of goblins? I have no time for this.*");
        Dialogue("Backup soldiers arrive and say, 'Go, " + getName() + ", save the princess. We'll take it from here.'");
        Dialogue("Zeromus: You cannot have the princess. This is all for the plan of destruction of the world. Don't interfere.");
        Dialogue("*I won't allow you.*");
        Dialogue("Zeromus: You'll regret your answer, child.");

        if (!Monster(6)) {
            System.out.println("Game Over. You were defeated. Try again?");
            tryAgain();
            return;
        }

        Dialogue("*At last, Baron Castle is now safe.*");

        System.out.println("Princess: Thank you for saving me, noble hero.");
        System.out.println("You are awarded the achievement of Noble Hero.");
        System.out.println("Choose:");
        System.out.println("1. Accept");
        System.out.println("2. Do not accept");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        if (choice == 1) {
            System.out.println("Princess: Thank you for accepting. We are in debt to you, noble hero.");
           System.out.println("You receive the achievement: Noble Hero.");
        } else if (choice == 2) {
            System.out.println("Princess: Please accept it; you deserve it.");
            System.out.println("You receive the achievement: Noble Hero.");
        }
    }

    public void playSurvival() {
        System.out.println("Welcome to the Survival Mode, " + getName() + "!");

        int monsterLevel = 1;
        int pHealth = 100;
        int monsterHealth = monsterLevel * 20;

        while (pHealth > 0) {
            System.out.println("\n--- Monster Level " + monsterLevel + " ---");
            System.out.println("Player Health: " + pHealth);

            // 
            System.out.println("Choose your action:");
            System.out.println("1. Attack");
            System.out.println("2. Defend");
            System.out.println("3. Flee");

            Scanner scanner = new Scanner(System.in);
            int playerChoice = scanner.nextInt();

            if (playerChoice == 1) {
                int playerDamage = Math.max(new Random().nextInt(monsterLevel * 10) + 1, 1);
                System.out.println("You dealt " + playerDamage + " damage!");
                monsterHealth -= playerDamage;
            } else if (playerChoice == 2) {
                System.out.println("You chose to defend. Taking reduced damage next turn.");
            } else if (playerChoice == 3) {
                if (new Random().nextInt(100) < 20) {
                    System.out.println("You successfully fled!");
                    return;
                } else {
                    System.out.println("Failed to flee. The monster attacks!");
                }
            } else {
                System.out.println("Invalid choice. Please try again.");
            }

            System.out.println("Monster Health: " + monsterHealth);

            int monsterAttackBaseChance = 15;
            int monsterAttackChance = monsterAttackBaseChance + (monsterLevel * 2);
            int monsterDamage = (new Random().nextInt(100) < monsterAttackChance) ? monsterLevel * 10 : 0;

            System.out.println("The monster dealt " + monsterDamage + " damage!");
            pHealth -= monsterDamage;

            if (pHealth > 0 && monsterHealth <= 0) {
                monsterLevel++;
                monsterHealth = monsterLevel * 20;
                System.out.println("You defeated the monster! Moving on to Level " + monsterLevel + ".");
            }
        }

        System.out.println("Game Over. You were defeated by the monster at Level " + monsterLevel + ".");
    }

    // Method to handle dialogue display
    private void Dialogue(String message) {
        System.out.println(message);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Enter your name: ");
            String playerName = sc.nextLine();

            Game game = new Game();
            game.setName(playerName);

            System.out.println("1. Story Mode");
            System.out.println("2. Survival Mode");
            System.out.println("3. Exit");

            System.out.print("Select Mode:");
            int modeChoice = sc.nextInt();

            if (modeChoice == 1) {
                game.playStory();
            } else if (modeChoice == 2) {
                game.playSurvival();
            } else if (modeChoice == 3) {
                System.out.println("Exiting the game. Goodbye!");
                sc.close();
                break;
            }
        }
    }
}
 
