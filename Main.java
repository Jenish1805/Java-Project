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
        this.coolingEffect = this.massFlowRate * this.specificHeat * this.evaporatorTemperature;

        this.workDoneByCompressor = this.massFlowRate * this.specificHeat
                * (this.condenserTemperature - this.evaporatorTemperature);

        if (this.workDoneByCompressor == 0) {
            this.coefficientOfPerformance = 0;
        } else {
            this.coefficientOfPerformance = this.coolingEffect / this.workDoneByCompressor;
        }
    }

    public String getEfficiencyRating() {
        if (this.coefficientOfPerformance < 2.0) {
            return "Poor";
        } else if (this.coefficientOfPerformance <= 4.0) {
            return "Moderate";
        } else {
            return "Good";
        }
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

        System.out.println("Stage 1: Compression");
        System.out.println("Compressor increases pressure of refrigerant.");
        System.out.println("Work Done by Compressor: " + cycle.getWorkDoneByCompressor());
        System.out.println();

        System.out.println("Stage 2: Condensation");
        System.out.println("Refrigerant releases heat in condenser.");
        System.out.println("Condenser Temperature: " + cycle.getCondenserTemperature());
        System.out.println();

        System.out.println("Stage 3: Expansion");
        System.out.println("Pressure drops in expansion valve.");
        System.out.println();

        System.out.println("Stage 4: Evaporation");
        System.out.println("Refrigerant absorbs heat in evaporator.");
        System.out.println("Cooling Effect: " + cycle.getCoolingEffect());
        System.out.println();
    }
}

class Display {

    public void printWelcomeBanner() {
        System.out.println("Vapour Compression Refrigeration Cycle Simulator");
    }

    public void printMainMenu() {
        System.out.println();
        System.out.println("1. Run New Simulation");
        System.out.println("2. View History");
        System.out.println("3. Compare Two Results");
        System.out.println("4. Exit");
        System.out.print("Enter choice: ");
    }

    public void printCycleResult(Cycle cycle) {
        System.out.println();
        System.out.println("Results for " + cycle.getCycleLabel());
        System.out.println("Evaporator Temperature: " + cycle.getEvaporatorTemperature());
        System.out.println("Condenser Temperature: " + cycle.getCondenserTemperature());
        System.out.println("Mass Flow Rate: " + cycle.getMassFlowRate());
        System.out.println("Specific Heat: " + cycle.getSpecificHeat());
        System.out.println("Cooling Effect: " + cycle.getCoolingEffect());
        System.out.println("Work Done: " + cycle.getWorkDoneByCompressor());
        System.out.println("COP: " + cycle.getCoefficientOfPerformance());
        System.out.println("Efficiency: " + cycle.getEfficiencyRating());
    }

    public void printHistory(Cycle[] history, int count) {
        System.out.println();
        System.out.println("Simulation History");

        if (count == 0) {
            System.out.println("No simulations available.");
            return;
        }

        for (int i = 0; i < count; i++) {
            Cycle c = history[i];
            System.out.println((i + 1) + ". " + c.getCycleLabel() +
                    " Te=" + c.getEvaporatorTemperature() +
                    " Tc=" + c.getCondenserTemperature() +
                    " COP=" + c.getCoefficientOfPerformance());
        }
    }

    public void printComparison(Cycle a, Cycle b) {
        System.out.println();
        System.out.println("Comparison");

        System.out.println(a.getCycleLabel() + " COP: " + a.getCoefficientOfPerformance());
        System.out.println(b.getCycleLabel() + " COP: " + b.getCoefficientOfPerformance());

        if (a.getCoefficientOfPerformance() > b.getCoefficientOfPerformance()) {
            System.out.println(a.getCycleLabel() + " is more efficient.");
        } else if (b.getCoefficientOfPerformance() > a.getCoefficientOfPerformance()) {
            System.out.println(b.getCycleLabel() + " is more efficient.");
        } else {
            System.out.println("Both have equal efficiency.");
        }
    }

    public void printError(String message) {
        System.out.println("Error: " + message);
    }

    public void printInfo(String message) {
        System.out.println(message);
    }

    public void printGoodbye() {
        System.out.println("Program ended.");
    }
}

class InputHandler {

    private Scanner scanner;

    public InputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public double readEvaporatorTemperature() {
        System.out.print("Enter Evaporator Temperature (K): ");
        return scanner.nextDouble();
    }

    public double readCondenserTemperature(double te) {
        System.out.print("Enter Condenser Temperature (K): ");
        return scanner.nextDouble();
    }

    public double readMassFlowRate() {
        System.out.print("Enter Mass Flow Rate: ");
        return scanner.nextDouble();
    }

    public double readSpecificHeat() {
        System.out.print("Enter Specific Heat (or 1.0 default): ");
        return scanner.nextDouble();
    }

    public int readMenuChoice() {
        return scanner.nextInt();
    }

    public int readCycleIndex(String prompt, int count) {
        System.out.print(prompt + ": ");
        return scanner.nextInt() - 1;
    }

    public boolean readYesOrNo(String prompt) {
        System.out.print(prompt + " (1 for yes, 0 for no): ");
        return scanner.nextInt() == 1;
    }
}

public class Main {

    private static final int MAX_HISTORY = 20;
    private static Cycle[] history = new Cycle[MAX_HISTORY];
    private static int historyCount = 0;
    private static int cycleCounter = 0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        InputHandler input = new InputHandler(scanner);
        Display display = new Display();

        display.printWelcomeBanner();

        int choice = 0;

        while (choice != 4) {

            display.printMainMenu();
            choice = input.readMenuChoice();

            if (choice == 1) {

                double te = input.readEvaporatorTemperature();
                double tc = input.readCondenserTemperature(te);
                double mass = input.readMassFlowRate();
                double cp = input.readSpecificHeat();

                cycleCounter++;
                Cycle c = new Cycle("Cycle " + cycleCounter, te, tc, mass, cp);
                c.calculate();

                display.printCycleResult(c);

                if (historyCount < MAX_HISTORY) {
                    history[historyCount++] = c;
                }

            } else if (choice == 2) {
                display.printHistory(history, historyCount);

            } else if (choice == 3) {

                if (historyCount < 2) {
                    display.printError("Not enough data");
                    continue;
                }

                display.printHistory(history, historyCount);

                int a = input.readCycleIndex("Select first", historyCount);
                int b = input.readCycleIndex("Select second", historyCount);

                display.printComparison(history[a], history[b]);

            } else if (choice == 4) {
                display.printGoodbye();
            }
        }

        scanner.close();
    }
}