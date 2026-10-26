import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class War {

    // ADD ADDITIONAL CONSTANTS HERE, IF DESIRED...

    static Pile pile1;
    static Pile pile2;
    static int numTurns;

    // ADD ADDITIONAL VARIABLES HERE, IF DESIRED...
    public static int Comparer(Card p1, Card p2) {

        if(p1.compareTo(p2)>0) {
            pile1.enqueue(p1);
            pile1.enqueue(p2);
            return 1;
        } else if (p1.compareTo(p2)<0){
            pile2.enqueue(p2);
            pile2.enqueue(p1);
            return 2;
        } else if(p1.compareTo(p2)==0) {
            return -1;
        }
        numTurns++;
        return 3;
    }


    public static void printPiles() {

        // For your convenience, this prints the cards in
        // pile1 and pile2 along with the turn number, as specified
        // by numTurns

        System.out.println("Turn: " + numTurns + System.lineSeparator());
        System.out.println("player1:");
        System.out.println(pile1.toString() + System.lineSeparator());
        System.out.println("player2:");
        System.out.println(pile2.toString());
        System.out.println("--------------------------------------");
    }

    public static void printWar() {

        // Call this method every time there is a tie in the ranks
        // of cards compared

        System.out.println("                                        *** WAR! ***" + System.lineSeparator());
    }

    // ADD ADDITIONAL METHODS HERE, IF DESIRED...

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Usage: java War <deck-file>");
            return;
        }

        // args[0] is the deck filename.
        try {
            Scanner scanner = new Scanner(new File(args[0]));
            System.out.println("Loading deck from: " + args[0]);
            pile1 = new Pile(scanner.nextLine());
            pile2 = new Pile(scanner.nextLine());
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found: " + args[0]);
            return;
        }

        numTurns = 1;

        // TODO: ADD CODE HERE TO COMPLETE THIS METHOD...

        while(!pile1.isEmpty()||!pile2.isEmpty()) {
            Card p1 = pile1.dequeue();
            Card p2 = pile2.dequeue();
            int i = Comparer(p1,p2);
            while(i==-1) {
                printWar();
                Card p1a = pile1.dequeue();
                Card p1b = pile1.dequeue();
                Card p1c = pile1.dequeue();
                Card p2a = pile2.dequeue();
                Card p2b = pile2.dequeue();
                Card p2c = pile2.dequeue();

                Card p1d = pile1.dequeue();
                Card p2d = pile2.dequeue();
                i = Comparer(p1d, p2d);
                if(i==1) {
                    pile1.enqueue(p1a);
                    pile1.enqueue(p1b);
                    pile1.enqueue(p1c);
                    pile1.enqueue(p2a);
                    pile1.enqueue(p2b);
                    pile1.enqueue(p2c);
                }
                if(i==2) {
                    pile2.enqueue(p1a);
                    pile2.enqueue(p1b);
                    pile2.enqueue(p1c);
                    pile2.enqueue(p2a);
                    pile2.enqueue(p2b);
                    pile2.enqueue(p2c);
                }
            }
        }

    }
}
