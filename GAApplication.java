import java.util.ArrayList;
import java.util.Random;

public class GAApplication  {

    final static int POPULATION_SIZE = 100;
    final double MUTATION_PROB = 0.50;
    final double CROSSOVER_PROB = 0.80;
    final int NUMBER_OF_PARENTS = 40;

    //Lenght of the Target or lenght of binary incase of BinaryMaximiser
    protected int targetlen;
    //ArrayList to store the population
    protected ArrayList<String> population;
    //Arraylist to store the fitness of each chromosomes in the population
    protected ArrayList<Double> fitnessArray = new ArrayList<>();
    //Object of class Individual
    protected Individual individual = new Individual();

    //Setting targetlen
    public void settargetlen(int targetlen) {

        this.targetlen = targetlen;

    }


    //Setting population
    public void setpopulation( ArrayList<String> population) {

        this.population = population;

    }


    // The main run method
    protected void run() {

        for (String i: population) {

            individual.setChromosomes(i);
            fitnessArray.add(individual.getFitness());

        }

        sortByfit(fitnessArray,population);

        if (getBest().fitnessValue != (double) targetlen) {

            mutate();
            sortByfit(fitnessArray,population);
            getBest();

            if (getBest().fitnessValue != (double) targetlen) {

                crossover();
                sortByfit(fitnessArray,population);
                getBest();

            } else {

                getBest();

            }

        } else {

            getBest();

        }
    }


    //Crossover, which is same for all 3
    protected void crossover() {

        ArrayList<String> selectedParents = new ArrayList<>(population.subList(0,NUMBER_OF_PARENTS));
        Random rand = new Random();

        for (int r = 0, q = NUMBER_OF_PARENTS - 1; r <= (NUMBER_OF_PARENTS / 2) - 1; r++,q--) {

            String child1;
            String child2;
            double randomValue = 0.00 + (1.00 - 0.00) * rand.nextDouble();
            randomValue = Math.round(randomValue * 100.0) / 100.0;

            if (randomValue <= CROSSOVER_PROB) {

                int crossoverPoint = rand.nextInt(targetlen);

                child1 = selectedParents.get(r).substring(0,crossoverPoint) + selectedParents.get(q).substring(crossoverPoint);
                child2 = selectedParents.get(q).substring(0,crossoverPoint) + selectedParents.get(r).substring(crossoverPoint);

                population.remove(POPULATION_SIZE - 1);
                population.add(0,child1);

                population.remove(POPULATION_SIZE - 1);
                population.add(0,child2);

            }
        }

        fitnessArray.clear();

        for (String i: population) {

            individual.setChromosomes(i);
            fitnessArray.add(individual.getFitness());

        }

        sortByfit(fitnessArray,population);

    }


    //Mutation method for Binarymaximiser, it is overridden for Weasel and Maths
    protected void mutate() {

        Random rand = new Random();

        for (int y = 0; y < POPULATION_SIZE; y++) {

            double randomValue = 0.00 + (1.00 - 0.00) * rand.nextDouble();
            randomValue = Math.round(randomValue * 100.0) / 100.0;

            if (randomValue < MUTATION_PROB) {

                int mutatpoint = rand.nextInt(targetlen);

                if (population.get(y).charAt(mutatpoint) == '0') {

                    String temp = population.get(y);
                    char[] myNameChars = temp.toCharArray();
                    myNameChars[mutatpoint] = '1';
                    population.set(y,String.valueOf(myNameChars));

                } else if (population.get(y).charAt(mutatpoint) == '1') {

                    String temp = population.get(y);
                    char[] myNameChars = temp.toCharArray();
                    myNameChars[mutatpoint] = '0';
                    population.set(y,String.valueOf(myNameChars));

                }
            }
        }

        fitnessArray.clear();

        for (String i: population) {

            individual.setChromosomes(i);
            fitnessArray.add(individual.getFitness());

        }

        sortByfit(fitnessArray,population);
    
    }

    //Method to sort the fitness array in descending order but makin the same changes in the population array.
    protected void sortByfit(ArrayList<Double> fitnessArray, ArrayList<String> population) {

        double temp;
        String transf;

        for (int i=0; i < POPULATION_SIZE; i++) {

            for (int j=0; j < (POPULATION_SIZE - i - 1); j++) {

                if (Double.parseDouble(fitnessArray.get(j + 1).toString()) > Double.parseDouble(fitnessArray.get(j).toString())) {

                    temp = Double.parseDouble(fitnessArray.get(j).toString());
                    transf = population.get(j);

                    fitnessArray.set(j,fitnessArray.get(j + 1));
                    population.set(j,population.get(j + 1));

                    fitnessArray.set(j + 1,temp);
                    population.set(j + 1,transf);

                }
            }
        }
    }


    //Getbest method which returns the current best chromosomes individual
    public Individual getBest() {

        individual.chromosomes = population.get(0);
        individual.fitnessValue = fitnessArray.get(0) ;
        return individual;

    }
}


