public  class Individual {

    private static final int LARGEST_NUM = 2147483647;

    //Variable to store the chromosome
    protected String chromosomes;
    //Variable to store the fitnessvalue of the choromosome
    protected double fitnessValue;
    //Lenght of the Target or lenght of binary incase of BinaryMaximiser
    protected int targetlen;
    //Target
    protected String target;
    //This variable is initialise in the constructor of the 3 classes. and helps us get the fitness of the chromosomes.
    protected int whchclass;


    //Setter for whchclass
    public void setWhchclass(int whchclass) {

        this.whchclass = whchclass;

    }
    //Setter for chromosomes
    protected void setChromosomes(String chromosomes) {

        this.chromosomes = chromosomes;

    }
    //Setter for target
    protected void setTarget(String target) {

        this.target = target;

    }
    //Setter for targetlen
    protected void setTargetlenIndiv(int targetlen) {

        this.targetlen = targetlen;

    }

    //Method to get the fitness of the chromosomes.
    public double getFitness() {

        if (whchclass == 0) {

            //Getfitness for BinaryMaximiser
            char someChar = '1';
            double count = 0.0;

            for (int i = 0; i < chromosomes.length(); i++) {

                if (chromosomes.charAt(i) == someChar) {

                    count++;

                }
            }

            return count;

        } else if (whchclass == 1) {

            //Getfitness for Weasel
            double fitval = 0.0;

            for (int i = 0; i < targetlen; i++) {

                int chromascii = (int) chromosomes.charAt(i);
                int targetascii = (int) target.charAt(i);
                fitval += Math.abs(chromascii - targetascii);

            }

            return LARGEST_NUM - fitval;

        } else if (whchclass == 2) {

            //Getfitness for Maths
            if (chromosomes.matches("([0-9]+[+*/-][0-9]+)+")) {

                int inttarget = Integer.parseInt(target);
                double diff = 1;

                try {

                    diff = Math.abs(inttarget - evalstring(chromosomes));

                } catch (ArithmeticException e) {

                    return 0.0;

                }

                return Double.parseDouble(target)-diff;

            } else {

             return 0.0;

            }
        }

        return 0.0;

    }


    //Method used to evaluate the expressions produced for Maths using recurrsion.
    public double evalstring(String word) throws ArithmeticException {

        String[] splitadd = word.split("[+]");

        if (splitadd.length > 1) {

            double result = evalstring(splitadd[0]);

            for (int x = 1; x < splitadd.length; x++) {

                result = result + evalstring(splitadd[x]);

            }

            return result;

        }

        String[] splitsub = word.split("[-]");

        if (splitsub.length > 1) {

            double result = evalstring(splitsub[0]);

            for (int x = 1; x < splitsub.length; x++) {

                result = result - evalstring(splitsub[x]);

            }

            return result;

        }

        String[] splitmul = word.split("[*]");

        if (splitmul.length > 1) {

            double result = evalstring(splitmul[0]);

            for (int x = 1; x < splitmul.length; x++) {

                result = result * evalstring(splitmul[x]);

            }

            return result;

        }

        String[] splitdiv = word.split("[/]");

        if (splitdiv.length > 1) {

            double result = evalstring(splitdiv[0]);

            for (int x = 1; x < splitdiv.length; x++) {

                result = result / evalstring(splitdiv[x]);
                return result;

            }
        }

            return Double.parseDouble(word);

    }


    //ToString method for individual
    public String toString() {

        return  chromosomes ;

    }

}



