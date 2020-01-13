import java.util.Scanner;

public class runApp {
	
    private static final int EXP_LENGHT = 5;
	private static final int MATH_TARGET = 65;
    private static final int MAX_GENERATIONS = 10000;
    private static final int MIN_GENERATIONS = 5;
    private static final int LARGEST_NUM = 2147483647;


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

		while(true) {

			System.out.println("Welcome to this simple Evolutionary Algorithm\n");
			System.out.println("Enter 1 to run the algorithm to get the maximum number of a specified lenght in binary form\n");
			System.out.println("Enter 2 to run the algorithm to get the word 'weasel' from a random population \n");
			System.out.println("Enter 3 to run the algorithm to get a mathematical expression that evaluates to the target\n");
			System.out.println("Enter 4 to Exit\n");

			int opt = scanner.nextInt();

			if(opt == 1) {

			    System.out.println("Please enter the lenght of the binary number you wish to have: ");
			    int binaryGoal = scanner.nextInt();

                BinaryMaximiser bm = new BinaryMaximiser(binaryGoal);
                int gens = 0;

                do {

                    bm.run();
                    System.out.println(bm.getBest());

                } while (bm.getBest().getFitness() != binaryGoal && (gens++) < MAX_GENERATIONS);

                if (gens <= MIN_GENERATIONS) {

                    System.out.println("The GA might not be working if it finds the goal that quickly.");

                }

                System.out.println("Num generations: " + gens);

            } else if(opt == 2) {

            	System.out.println("Please enter the String, you wish to generate using the Algorithm: ");
            	Scanner newScanner = new Scanner(System.in);
            	String inputWord = newScanner.nextLine();

                Weasel weasel = new Weasel(inputWord);
                int gens = 0;

                do{

                    weasel.run();
                    System.out.println(weasel.getBest());

                } while(weasel.getBest().getFitness() != LARGEST_NUM && (gens++) < MAX_GENERATIONS);

                if (gens <= MIN_GENERATIONS) {

                    System.out.println("The GA might not be working if it finds the goal that quickly.");

                }

                System.out.println("Num generations: " + gens);
 
            } else if(opt == 3) {

                System.out.println("Please enter the lenght of the mathematical expression: ");
                int expLenght = scanner.nextInt();
                System.out.println("Now enter the target you want the expression to evaluate to: ");
                int target = scanner.nextInt();

                Maths maths = new Maths(target, expLenght);
                int gens = 0;

                do{

                    maths.run();
                    System.out.println(maths.getBest());

                } while(maths.getBest().getFitness() != target && (gens++) < MAX_GENERATIONS);

                if (gens <= MIN_GENERATIONS) {

                    System.out.println("The GA might not be working if it finds the goal that quickly.");

                }

                System.out.println("Num generations: " + gens);

            } else {

			    break;

            }

        }
    }
}
