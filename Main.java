import java.util.Scanner;

class Cycle {

    private double evaporatorTemperature;
    private double condenserTemperature;
    private double massFlowRate;
    private double specificHeat;
    private double coolingEffect;
    private double workDoneByCompressor;
    private double coefficientOfPerformance;
    private String cycleLabel;

    public Cycle(String label, double te, double tc, double mass, double cp) {
        this.cycleLabel = label;
        this.evaporatorTemperature = te;
        this.condenserTemperature = tc;
        this.massFlowRate = mass;
        this.specificHeat = cp;
    }

    public void calculate() {

        coolingEffect = massFlowRate * specificHeat * evaporatorTemperature;

        workDoneByCompressor = massFlowRate * specificHeat
                * (condenserTemperature - evaporatorTemperature);

        if (workDoneByCompressor == 0) {
            coefficientOfPerformance = 0;
        } else {
            coefficientOfPerformance = coolingEffect / workDoneByCompressor;
        }
    }

    public String getEfficiencyRating() {
        if (coefficientOfPerformance < 2.0) return "Poor";
        else if (coefficientOfPerformance <= 4.0) return "Moderate";
        else return "Good";
    }

    public String getCycleLabel() { return cycleLabel; }
    public double getEvaporatorTemperature() { return evaporatorTemperature; }
    public double getCondenserTemperature() { return condenserTemperature; }
    public double getMassFlowRate() { return massFlowRate; }
    public double getSpecificHeat() { return specificHeat; }
    public double getCoolingEffect() { return coolingEffect; }
    public double getWorkDoneByCompressor() { return workDoneByCompressor; }
    public double getCoefficientOfPerformance() { return coefficientOfPerformance; }
}

class Simulator {

    public void runSimulation(Cycle cycle) {

        System.out.println();
        System.out.println("Stage 1 - Compression");
        System.out.println("Compressor increases pressure");
        System.out.println("Work done: " + cycle.getWorkDoneByCompressor());

        System.out.println();

        System.out.println("Stage 2 - Condensation");
        System.out.println("Heat is released in condenser");
        System.out.println("Condenser temp: " + cycle.getCondenserTemperature());

        System.out.println();

        System.out.println("Stage 3 - Expansion");
        System.out.println("Pressure drops through valve");

        System.out.println();

        System.out.println("Stage 4 - Evaporation");
        System.out.println("Heat is absorbed in evaporator");
        System.out.println("Cooling effect: " + cycle.getCoolingEffect());

        System.out.println();
    }
}

class Display {

    public void printWelcome() {
        System.out.println("Vapour Compression Refrigeration Cycle Simulator");
        System.out.println();
    }

    public void menu() {
        System.out.println("1. New simulation");
        System.out.println("2. History");
        System.out.println("3. Compare");
        System.out.println("4. Exit");
        System.out.print("Choose option: ");
    }

    public void showResult(Cycle c){
        System.out.println();
        System.out.println("Result - " + c.getCycleLabel());
        System.out.println();
        System.out.println("Evaporator temp: " + c.getEvaporatorTemperature());
        System.out.println("Condenser temp: " + c.getCondenserTemperature());
        System.out.println("Mass flow rate: " + c.getMassFlowRate());
        System.out.println("Specific heat: " + c.getSpecificHeat());
        System.out.println();
        System.out.println("Cooling effect: " + c.getCoolingEffect());
        System.out.println("Work done: " + c.getWorkDoneByCompressor());
        System.out.println("COP: " + c.getCoefficientOfPerformance());
        System.out.println("Efficiency: " + c.getEfficiencyRating());
        System.out.println();
    }

    public void history(Cycle[] h, int count) {

        System.out.println();
        System.out.println("History");

        if (count == 0) {
            System.out.println("No data yet");
            return;
        }

        for (int i = 0; i < count; i++) {
            Cycle c = h[i];

            System.out.println(
                    (i + 1) + ". " + c.getCycleLabel() +
                    " Te=" + c.getEvaporatorTemperature() +
                    " Tc=" + c.getCondenserTemperature() +
                    " COP=" + c.getCoefficientOfPerformance()
            );
        }

        System.out.println();
    }

    public void compare(Cycle a, Cycle b) {

        System.out.println();
        System.out.println("Comparison");

        System.out.println(a.getCycleLabel() + " COP: " + a.getCoefficientOfPerformance());
        System.out.println(b.getCycleLabel() + " COP: " + b.getCoefficientOfPerformance());

        if (a.getCoefficientOfPerformance() > b.getCoefficientOfPerformance()) {
            System.out.println(a.getCycleLabel() + " is more efficient");
        } else if (b.getCoefficientOfPerformance() > a.getCoefficientOfPerformance()) {
            System.out.println(b.getCycleLabel() + " is more efficient");
        } else {
            System.out.println("Both are equal");
        }

        System.out.println();
    }

    public void error(String msg) {
        System.out.println("Error: " + msg);
    }

    public void bye() {
        System.out.println("Program finished");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Display d = new Display();

        Cycle[] history = new Cycle[20];
        int count = 0;
        int cycleNo = 0;

        d.printWelcome();

        int choice = 0;

        while(choice != 4){
            d.menu();
            choice = sc.nextInt();

            if(choice == 1){

                System.out.print("Evaporator temp (K): ");
                double te = sc.nextDouble();

                System.out.print("Condenser temp (K): ");
                double tc = sc.nextDouble();

                System.out.print("Mass flow rate: ");
                double mass = sc.nextDouble();

                System.out.print("Specific heat: ");
                double cp = sc.nextDouble();

                cycleNo++;
                Cycle c = new Cycle("Cycle " + cycleNo, te, tc, mass, cp);
                c.calculate();

                d.showResult(c);

                if(count < 20){
                    history[count++] = c;
                }
            } 

            else if(choice == 2) {
                d.history(history, count);
            } 

            else if(choice == 3){

                if (count < 2) {
                    d.error("Not enough data");
                    continue;
                }

                d.history(history, count);

                System.out.print("First cycle: ");
                int a = sc.nextInt() - 1;

                System.out.print("Second cycle: ");
                int b = sc.nextInt() - 1;

                d.compare(history[a], history[b]);
            }
        }
    }
}