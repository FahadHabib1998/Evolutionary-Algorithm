import java.util.ArrayList;
import java.util.Random;

public class Maths extends GAApplication {

    //Target
    protected int target;
    //Lenght of expression
    protected int explen;

    //Constructor
    public Maths(int target, int explen) {

        this.explen = explen;
        this.target = target;

        settargetlen(explen);
        setpopulation(createMathsPopulation());

        individual.setTarget(Integer.toString(target));
        individual.setTargetlenIndiv(explen);
        individual.setWhchclass(2);

    }


    //Creating Population
    protected ArrayList<String> createMathsPopulation() {

        ArrayList<String> popu = new ArrayList<>();
        Random rand = new Random();

        do {

            String newchromo = "";

            for (int i = 0; i < explen; i++) {

                int ranascii = rand.nextInt((57 - 42) + 1) + 42;

                if ((Character.toString ((char) ranascii).equals(".") || Character.toString ((char) ranascii).equals(",")) && i != 0) {
                    
                    i--;

                } else if ((Character.toString ((char) ranascii).equals(".") || Character.toString ((char) ranascii).equals(",")) && i == 0){
                    
                    i = -1;
                
                } else {

                    newchromo += Character.toString ((char) ranascii);

                }
            }

            popu.add(newchromo);

        } while (popu.size() != POPULATION_SIZE);

        return popu;

    }


    //Overridden Mutation method
    @Override
    public void mutate() {

        Random rand = new Random();

        for (int y = 0; y<POPULATION_SIZE; y++) {

            double randomValue = 0.00 + (1.00 - 0.00) * rand.nextDouble();
            randomValue = Math.round(randomValue * 100.0) / 100.0;

            if (randomValue <= MUTATION_PROB) {

                int mutatpoint = rand.nextInt(targetlen);
                char[] myNameChars = population.get(y).toCharArray();
                int incdec = rand.nextInt(10);

                if (incdec <= 4) {

                    int ascii = ((int) myNameChars[mutatpoint]) + 1;

                    if (ascii >= 42 && ascii <= 57 && ascii != 44 && ascii != 46) {

                        myNameChars[mutatpoint] = (char) ascii;

                    } else if ( ascii == 58) {

                        myNameChars[mutatpoint] = (char) 42;

                    } else if (ascii == 44) {

                        myNameChars[mutatpoint] = (char) 45;

                    } else if (ascii == 46) {

                        myNameChars[mutatpoint] = (char) 47;

                    }

                    population.set(y,String.valueOf(myNameChars));

                } else if (incdec > 4) {

                    int ascii = ((int) myNameChars[mutatpoint]) - 1;

                    if (ascii >= 42 && ascii <= 57 && ascii != 44 && ascii != 46) {

                        myNameChars[mutatpoint] = (char) ascii;

                    } else if(ascii == 41) {

                        myNameChars[mutatpoint] = (char) 57;

                    } else if (ascii == 44) {

                        myNameChars[mutatpoint] = (char) 43;

                    } else if (ascii == 46) {

                        myNameChars[mutatpoint] = (char) 45;

                    }

                    population.set(y,String.valueOf(myNameChars));

                }
            }
        }

        fitnessArray.clear();

        for (String i: population) {

            individual.setChromosomes(i);
            fitnessArray.add(individual.getFitness());
            
        }
    }
}



