import java.util.ArrayList;
import java.util.Random;

public class Weasel extends GAApplication {

    final double MUTATION_PROB = 0.006;

    //Target word or phrase
    protected String target;
    //Lenght of the Target
    protected int targetlen;

    //Constructor
    public Weasel(String target) {

        this.target = target;
        this.targetlen = target.length();

        settargetlen(targetlen);
        setpopulation(createWeaselPopulation());

        individual.setTarget(target);
        individual.setTargetlenIndiv(targetlen);
        individual.setWhchclass(1);

    }


    //Creating population
    public ArrayList<String> createWeaselPopulation() {

        ArrayList<String> popu = new ArrayList<>();
        Random rand = new Random();

        for (int j = 0; j<POPULATION_SIZE; j++) {

            String newchromo = "";

            for (int i = 0; i < targetlen; i++) {

                int ranascii = rand.nextInt((126 - 32) + 1) + 32;
                newchromo += Character.toString ((char) ranascii);

            }

            popu.add(j,newchromo);

        }

        return popu;

    }

    //Overridden Mutation method
    @Override
    public void mutate() {

        Random rand = new Random();

        for (int y = 0; y<POPULATION_SIZE; y++) {

            for (int x = 0; x<population.get(y).length();x++) {

                double randomValue = Math.random();

                if (randomValue <= MUTATION_PROB) {

                    char[] myNameChars = population.get(y).toCharArray();
                    int incdec = rand.nextInt(10);

                    if (incdec <= 4) {

                        int ascii = ((int) myNameChars[x]) + 1;

                        if (ascii >= 32 && ascii <= 126) {

                            myNameChars[x] = (char) ascii;

                        } else if (ascii == 127) {

                            myNameChars[x] = (char) 32;

                        }

                        population.set(y,String.valueOf(myNameChars));

                    } else if (incdec > 4) {

                        int ascii = ((int) myNameChars[x]) - 1;

                        if (ascii >= 32 && ascii <= 126) {

                            myNameChars[x] = (char) ascii;

                        } else if (ascii == 31) {

                            myNameChars[x] = (char) 126;

                        }

                        population.set(y,String.valueOf(myNameChars));

                    }
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
