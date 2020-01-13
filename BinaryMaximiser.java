import java.util.ArrayList;
import java.util.Random;

public class BinaryMaximiser extends GAApplication {

    //Variable to store lenght of binary
    protected int targetlen;


    //Constructor
    public BinaryMaximiser(int targetlen) {

        this.targetlen = targetlen;

        settargetlen(targetlen);
        setpopulation(createPopulation());

        individual.setWhchclass(0);

    }

    //Creating Population
    public ArrayList<String> createPopulation() {

        ArrayList<String> popu = new ArrayList<>();
        Random rand = new Random();

        for (int j = 0; j < POPULATION_SIZE; j++) {

            String newchromo = "";

            for (int i = 0; i < targetlen; i++) {

                double x = 0.00 + (1.0 - 0.00) * rand.nextDouble();

                if (x < 0.5) {

                    newchromo += Integer.toString(0);

                } else {

                    newchromo += Integer.toString(1);

                }
            }

            popu.add(j, newchromo);

        }

        return popu;
        
    }
}
