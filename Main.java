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

    private static final int STAGE_DELAY = 1500;
    private static final int STEP_DELAY = 600;

    public void runSimulation(Cycle cycle) {
        System.out.println();
        System.out.println("========================================================");
        System.out.println("       VAPOUR COMPRESSION REFRIGERATION CYCLE           ");
        System.out.println("             STEP-BY-STEP SIMULATION                    ");
        System.out.println("========================================================");
        waitFor(STAGE_DELAY);
        displayFlowDiagram();
        runStageOne(cycle);
        runStageTwo(cycle);
        runStageThree(cycle);
        runStageFour(cycle);
        System.out.println();
        System.out.println("========================================================");
        System.out.println("   SIMULATION COMPLETE - All 4 Stages Finished!         ");
        System.out.println("========================================================");
        waitFor(STAGE_DELAY);
    }

    private void displayFlowDiagram() {
        System.out.println();
        System.out.println("  REFRIGERATION CYCLE FLOW DIAGRAM:");
        System.out.println();
        System.out.println("  +-------------+       High Pressure        +-------------+");
        System.out.println("  |             | =========================> |             |");
        System.out.println("  | COMPRESSOR  |     (Hot Vapour)           |  CONDENSER  |");
        System.out.println("  |   Stage 1   |                            |   Stage 2   |");
        System.out.println("  |             |                            |             |");
        System.out.println("  +-------------+                            +-------------+");
        System.out.println("        ^                                           |");
        System.out.println("        |  Low Pressure                             | High Pressure");
        System.out.println("        |  (Cold Vapour)                            | (Liquid)");
        System.out.println("        |                                           v");
        System.out.println("  +-------------+       Low Pressure        +-------------+");
        System.out.println("  |             | <========================= |             |");
        System.out.println("  | EVAPORATOR  |     (Cold Liquid/Vapour)  |  EXPANSION  |");
        System.out.println("  |   Stage 4   |                            |   Stage 3   |");
        System.out.println("  |             |                            |             |");
        System.out.println("  +-------------+                            +-------------+");
        System.out.println();
        System.out.println("  Refrigerant flows:  1 -> 2 -> 3 -> 4 -> 1 -> ...");
        System.out.println();
        waitFor(STAGE_DELAY);
    }

    private void runStageOne(Cycle cycle) {
        System.out.println();
        System.out.println("--------------------------------------------------------");
        System.out.println("  STAGE 1: COMPRESSION");
        System.out.println("--------------------------------------------------------");
        waitFor(STEP_DELAY);
        System.out.println("  [>>] Low-pressure cold vapour enters the compressor...");
        waitFor(STEP_DELAY);
        System.out.println("  [>>] Compressor is running...");
        waitFor(STEP_DELAY);
        System.out.println("       Compressor  =====>  High Pressure Hot Vapour");
        waitFor(STEP_DELAY);
        System.out.println();
        System.out.printf("  Work Done by Compressor (Wc): %.2f kW%n", cycle.getWorkDoneByCompressor());
        System.out.printf("  Temperature difference      : %.2f K%n",
                cycle.getCondenserTemperature() - cycle.getEvaporatorTemperature());
        System.out.println();
        System.out.println("  [OK] Stage 1 Complete. Vapour is now hot and at high pressure.");
        waitFor(STAGE_DELAY);
    }

    private void runStageTwo(Cycle cycle) {
        System.out.println();
        System.out.println("--------------------------------------------------------");
        System.out.println("  STAGE 2: CONDENSATION");
        System.out.println("--------------------------------------------------------");
        waitFor(STEP_DELAY);
        System.out.println("  [>>] Hot high-pressure vapour enters the condenser...");
        waitFor(STEP_DELAY);
        System.out.println("  [>>] Heat is being rejected to the surroundings...");
        waitFor(STEP_DELAY);
        System.out.println("       Condenser  =====>  High Pressure Liquid");
        waitFor(STEP_DELAY);
        System.out.println();
        System.out.printf("  Condenser Temperature (Tc) : %.2f K%n", cycle.getCondenserTemperature());
        System.out.println("  Heat is released to the environment at this stage.");
        System.out.println();
        System.out.println("  [OK] Stage 2 Complete. Vapour has condensed into liquid.");
        waitFor(STAGE_DELAY);
    }

    private void runStageThree(Cycle cycle) {
        System.out.println();
        System.out.println("--------------------------------------------------------");
        System.out.println("  STAGE 3: EXPANSION");
        System.out.println("--------------------------------------------------------");
        waitFor(STEP_DELAY);
        System.out.println("  [>>] High-pressure liquid enters the expansion valve...");
        waitFor(STEP_DELAY);
        System.out.println("  [>>] Pressure drops rapidly (throttling process)...");
        waitFor(STEP_DELAY);
        System.out.println("       Expansion Valve  =====>  Low Pressure Cold Liquid");
        waitFor(STEP_DELAY);
        System.out.println();
        System.out.printf("  Target Evaporator Temp (Te): %.2f K%n", cycle.getEvaporatorTemperature());
        System.out.println("  Temperature drops sharply after passing through the valve.");
        System.out.println();
        System.out.println("  [OK] Stage 3 Complete. Liquid is now cold and at low pressure.");
        waitFor(STAGE_DELAY);
    }

    private void runStageFour(Cycle cycle) {
        System.out.println();
        System.out.println("--------------------------------------------------------");
        System.out.println("  STAGE 4: EVAPORATION");
        System.out.println("--------------------------------------------------------");
        waitFor(STEP_DELAY);
        System.out.println("  [>>] Cold low-pressure liquid enters the evaporator...");
        waitFor(STEP_DELAY);
        System.out.println("  [>>] Heat is absorbed from the refrigerated space...");
        waitFor(STEP_DELAY);
        System.out.println("       Evaporator  =====>  Low Pressure Cold Vapour");
        waitFor(STEP_DELAY);
        System.out.println();
        System.out.printf("  Cooling Effect (Qe)        : %.2f kW%n", cycle.getCoolingEffect());
        System.out.println("  This is the useful refrigeration effect produced.");
        System.out.println();
        System.out.println("  [OK] Stage 4 Complete. Vapour returns to the compressor.");
        waitFor(STAGE_DELAY);
    }

    private void waitFor(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            System.out.println("  (Simulation timing interrupted, continuing...)");
        }
    }
}

class Display {

    public void printWelcomeBanner() {
        System.out.println();
        System.out.println("########################################################");
        System.out.println("#                                                      #");
        System.out.println("#    VAPOUR COMPRESSION REFRIGERATION CYCLE (VCRC)    #");
        System.out.println("#              Simulation & Analysis Tool              #");
        System.out.println("#                                                      #");
        System.out.println("########################################################");
        System.out.println();
        System.out.println("  Welcome! This program simulates a basic VCRC system.");
        System.out.println("  You can calculate COP, cooling effect, and more.");
        System.out.println();
    }

    public void printMainMenu() {
        System.out.println();
        System.out.println("========================================================");
        System.out.println("                      MAIN MENU                         ");
        System.out.println("========================================================");
        System.out.println("  1. Run New Simulation");
        System.out.println("  2. View History");
        System.out.println("  3. Compare Two Results");
        System.out.println("  4. Exit");
        System.out.println("========================================================");
        System.out.print("  Enter your choice (1-4): ");
    }

    public void printCycleResult(Cycle cycle) {
        System.out.println();
        System.out.println("========================================================");
        System.out.println("  RESULTS FOR: " + cycle.getCycleLabel());
        System.out.println("========================================================");
        System.out.println();
        System.out.println("  --- INPUT PARAMETERS ---");
        System.out.printf("  Evaporator Temperature (Te) : %.2f K%n", cycle.getEvaporatorTemperature());
        System.out.printf("  Condenser Temperature  (Tc) : %.2f K%n", cycle.getCondenserTemperature());
        System.out.printf("  Mass Flow Rate         (m)  : %.2f kg/s%n", cycle.getMassFlowRate());
        System.out.printf("  Specific Heat          (Cp) : %.2f kJ/(kg.K)%n", cycle.getSpecificHeat());
        System.out.println();
        System.out.println("  --- CALCULATED RESULTS ---");
        System.out.printf("  Cooling Effect         (Qe) : %.2f kW%n", cycle.getCoolingEffect());
        System.out.printf("  Work by Compressor     (Wc) : %.2f kW%n", cycle.getWorkDoneByCompressor());
        System.out.printf("  Coeff. of Performance (COP) : %.2f%n", cycle.getCoefficientOfPerformance());
        System.out.println();
        System.out.println("  --- EFFICIENCY RATING ---");
        System.out.println("  Rating: " + cycle.getEfficiencyRating());
        printEfficiencyBar(cycle.getCoefficientOfPerformance());
        System.out.println();
        System.out.println("========================================================");
    }

    private void printEfficiencyBar(double cop) {
        System.out.print("  COP Level : [");
        int barLength = (int) Math.min(cop * 5, 30);
        for (int i = 0; i < barLength; i++) {
            System.out.print("=");
        }
        for (int i = barLength; i < 30; i++) {
            System.out.print(" ");
        }
        System.out.printf("] %.2f%n", cop);
        System.out.println("  Zones     :  Poor (<2)    Moderate (2-4)    Good (>4)");
    }

    public void printHistory(Cycle[] history, int count) {
        System.out.println();
        System.out.println("========================================================");
        System.out.println("              SIMULATION HISTORY                        ");
        System.out.println("========================================================");
        if (count == 0) {
            System.out.println("  No simulations have been run yet.");
            System.out.println("  Please run a simulation first (Option 1).");
        } else {
            System.out.println();
            System.out.printf("  %-10s %-10s %-10s %-10s %-10s %-10s%n",
                    "Label", "Te (K)", "Tc (K)", "Qe (kW)", "Wc (kW)", "COP");
            System.out.println("  ---------------------------------------------------------------");
            for (int i = 0; i < count; i++) {
                Cycle c = history[i];
                System.out.printf("  %-10s %-10.2f %-10.2f %-10.2f %-10.2f %-10.2f%n",
                        c.getCycleLabel(),
                        c.getEvaporatorTemperature(),
                        c.getCondenserTemperature(),
                        c.getCoolingEffect(),
                        c.getWorkDoneByCompressor(),
                        c.getCoefficientOfPerformance());
            }
            System.out.println("  ---------------------------------------------------------------");
            System.out.println("  Total simulations stored: " + count);
        }
        System.out.println("========================================================");
    }

    public void printComparison(Cycle cycleA, Cycle cycleB) {
        System.out.println();
        System.out.println("========================================================");
        System.out.println("           COMPARISON OF TWO CYCLES                     ");
        System.out.println("========================================================");
        System.out.println();
        System.out.printf("  %-30s %-15s %-15s%n", "Parameter", cycleA.getCycleLabel(), cycleB.getCycleLabel());
        System.out.println("  ---------------------------------------------------------------");
        System.out.printf("  %-30s %-15.2f %-15.2f%n", "Evaporator Temp Te (K)",
                cycleA.getEvaporatorTemperature(), cycleB.getEvaporatorTemperature());
        System.out.printf("  %-30s %-15.2f %-15.2f%n", "Condenser Temp Tc (K)",
                cycleA.getCondenserTemperature(), cycleB.getCondenserTemperature());
        System.out.printf("  %-30s %-15.2f %-15.2f%n", "Mass Flow Rate (kg/s)",
                cycleA.getMassFlowRate(), cycleB.getMassFlowRate());
        System.out.printf("  %-30s %-15.2f %-15.2f%n", "Specific Heat Cp (kJ/kg.K)",
                cycleA.getSpecificHeat(), cycleB.getSpecificHeat());
        System.out.printf("  %-30s %-15.2f %-15.2f%n", "Cooling Effect Qe (kW)",
                cycleA.getCoolingEffect(), cycleB.getCoolingEffect());
        System.out.printf("  %-30s %-15.2f %-15.2f%n", "Work Done Wc (kW)",
                cycleA.getWorkDoneByCompressor(), cycleB.getWorkDoneByCompressor());
        System.out.printf("  %-30s %-15.2f %-15.2f%n", "COP",
                cycleA.getCoefficientOfPerformance(), cycleB.getCoefficientOfPerformance());
        System.out.printf("  %-30s %-15s %-15s%n", "Efficiency Rating",
                cycleA.getEfficiencyRating(), cycleB.getEfficiencyRating());
        System.out.println("  ---------------------------------------------------------------");
        System.out.println();
        System.out.println("  --- COMPARISON VERDICT ---");
        double copA = cycleA.getCoefficientOfPerformance();
        double copB = cycleB.getCoefficientOfPerformance();
        if (copA > copB) {
            System.out.printf("  %s has a higher COP (%.2f vs %.2f) -> More Efficient%n",
                    cycleA.getCycleLabel(), copA, copB);
        } else if (copB > copA) {
            System.out.printf("  %s has a higher COP (%.2f vs %.2f) -> More Efficient%n",
                    cycleB.getCycleLabel(), copB, copA);
        } else {
            System.out.println("  Both cycles have the same COP -> Equal Efficiency");
        }
        System.out.println();
        System.out.println("========================================================");
    }

    public void printError(String message) {
        System.out.println();
        System.out.println("  [ERROR] " + message);
        System.out.println();
    }

    public void printInfo(String message) {
        System.out.println("  [INFO] " + message);
    }

    public void printGoodbye() {
        System.out.println();
        System.out.println("########################################################");
        System.out.println("#  Thank you for using the VCRC Simulation Tool!       #");
        System.out.println("#  Goodbye!                                            #");
        System.out.println("########################################################");
        System.out.println();
    }
}

class InputHandler {

    private Scanner scanner;

    public InputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public double readEvaporatorTemperature() {
        double value = 0.0;
        boolean isValid = false;
        while (isValid == false) {
            System.out.print("  Enter Evaporator Temperature Te (in Kelvin, e.g. 263): ");
            if (scanner.hasNextDouble()) {
                value = scanner.nextDouble();
                scanner.nextLine();
                if (value > 0) {
                    isValid = true;
                } else {
                    System.out.println("  [ERROR] Temperature must be greater than 0 K. Try again.");
                }
            } else {
                scanner.nextLine();
                System.out.println("  [ERROR] Invalid input. Please enter a numeric value.");
            }
        }
        return value;
    }

    public double readCondenserTemperature(double evaporatorTemp) {
        double value = 0.0;
        boolean isValid = false;
        while (isValid == false) {
            System.out.print("  Enter Condenser Temperature Tc (in Kelvin, e.g. 303): ");
            if (scanner.hasNextDouble()) {
                value = scanner.nextDouble();
                scanner.nextLine();
                if (value > evaporatorTemp) {
                    isValid = true;
                } else {
                    System.out.printf("  [ERROR] Condenser temperature must be greater than Te (%.2f K). Try again.%n",
                            evaporatorTemp);
                }
            } else {
                scanner.nextLine();
                System.out.println("  [ERROR] Invalid input. Please enter a numeric value.");
            }
        }
        return value;
    }

    public double readMassFlowRate() {
        double value = 0.0;
        boolean isValid = false;
        while (isValid == false) {
            System.out.print("  Enter Mass Flow Rate m (in kg/s, e.g. 0.5): ");
            if (scanner.hasNextDouble()) {
                value = scanner.nextDouble();
                scanner.nextLine();
                if (value > 0) {
                    isValid = true;
                } else {
                    System.out.println("  [ERROR] Mass flow rate must be greater than 0. Try again.");
                }
            } else {
                scanner.nextLine();
                System.out.println("  [ERROR] Invalid input. Please enter a numeric value.");
            }
        }
        return value;
    }

    public double readSpecificHeat() {
        double value = 1.0;
        boolean isValid = false;
        while (isValid == false) {
            System.out.print("  Enter Specific Heat Cp in kJ/(kg.K) [Press Enter for default = 1.0]: ");
            String inputLine = scanner.nextLine().trim();
            if (inputLine.isEmpty()) {
                value = 1.0;
                System.out.println("  [INFO] Using default Cp = 1.0 kJ/(kg.K)");
                isValid = true;
            } else {
                try {
                    value = Double.parseDouble(inputLine);
                    if (value > 0) {
                        isValid = true;
                    } else {
                        System.out.println("  [ERROR] Specific heat must be greater than 0. Try again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("  [ERROR] Invalid input. Please enter a numeric value or press Enter.");
                }
            }
        }
        return value;
    }

    public int readMenuChoice() {
        int choice = 0;
        boolean isValid = false;
        while (isValid == false) {
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice >= 1 && choice <= 4) {
                    isValid = true;
                } else {
                    System.out.print("  [ERROR] Please enter a number between 1 and 4: ");
                }
            } else {
                scanner.nextLine();
                System.out.print("  [ERROR] Invalid input. Enter 1, 2, 3, or 4: ");
            }
        }
        return choice;
    }

    public int readCycleIndex(String prompt, int count) {
        int choice = 0;
        boolean isValid = false;
        while (isValid == false) {
            System.out.print("  " + prompt + " (Enter number 1 to " + count + "): ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice >= 1 && choice <= count) {
                    isValid = true;
                } else {
                    System.out.println("  [ERROR] Please enter a number between 1 and " + count);
                }
            } else {
                scanner.nextLine();
                System.out.println("  [ERROR] Invalid input. Please enter a whole number.");
            }
        }
        return choice - 1;
    }

    public boolean readYesOrNo(String prompt) {
        boolean answer = false;
        boolean isValid = false;
        while (isValid == false) {
            System.out.print("  " + prompt + " (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y") || input.equals("yes")) {
                answer = true;
                isValid = true;
            } else if (input.equals("n") || input.equals("no")) {
                answer = false;
                isValid = true;
            } else {
                System.out.println("  [ERROR] Please type 'y' for yes or 'n' for no.");
            }
        }
        return answer;
    }
}

public class Main {

    private static final int MAX_HISTORY = 20;
    private static Cycle[] history = new Cycle[MAX_HISTORY];
    private static int historyCount = 0;
    private static int cycleCounter = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputHandler inputHandler = new InputHandler(scanner);
        Display display = new Display();
        Simulator simulator = new Simulator();

        display.printWelcomeBanner();

        int userChoice = 0;

        while (userChoice != 4) {
            display.printMainMenu();
            userChoice = inputHandler.readMenuChoice();

            if (userChoice == 1) {
                handleNewSimulation(inputHandler, display, simulator);
            } else if (userChoice == 2) {
                handleViewHistory(display);
            } else if (userChoice == 3) {
                handleComparison(inputHandler, display);
            } else if (userChoice == 4) {
                display.printGoodbye();
            } else {
                display.printError("Unexpected menu choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void handleNewSimulation(InputHandler inputHandler, Display display, Simulator simulator) {
        System.out.println();
        System.out.println("========================================================");
        System.out.println("               RUN NEW SIMULATION                       ");
        System.out.println("========================================================");
        System.out.println("  Please enter the following values:");
        System.out.println();

        double te = inputHandler.readEvaporatorTemperature();
        double tc = inputHandler.readCondenserTemperature(te);
        double mass = inputHandler.readMassFlowRate();
        double cp = inputHandler.readSpecificHeat();

        cycleCounter = cycleCounter + 1;
        String label = "Cycle #" + cycleCounter;

        Cycle newCycle = new Cycle(label, te, tc, mass, cp);
        newCycle.calculate();

        System.out.println();
        boolean runAnimation = inputHandler.readYesOrNo("Run the animated stage-by-stage simulation?");

        if (runAnimation == true) {
            simulator.runSimulation(newCycle);
        }

        display.printCycleResult(newCycle);

        if (historyCount < MAX_HISTORY) {
            history[historyCount] = newCycle;
            historyCount = historyCount + 1;
            display.printInfo("Result saved to history as: " + label);
        } else {
            display.printError("History is full (" + MAX_HISTORY + " entries). Cannot save this result.");
            display.printInfo("Consider exiting and restarting to clear history.");
        }
    }

    private static void handleViewHistory(Display display) {
        display.printHistory(history, historyCount);
    }

    private static void handleComparison(InputHandler inputHandler, Display display) {
        System.out.println();
        System.out.println("========================================================");
        System.out.println("              COMPARE TWO RESULTS                       ");
        System.out.println("========================================================");

        if (historyCount < 2) {
            display.printError("You need at least 2 simulations in history to compare.");
            display.printInfo("Currently stored: " + historyCount + " simulation(s).");
            display.printInfo("Please run more simulations first (Option 1).");
            return;
        }

        System.out.println("  Available simulations in history:");
        System.out.println();

        for (int i = 0; i < historyCount; i++) {
            System.out.printf("  %d. %s  (Te=%.2f K, Tc=%.2f K, COP=%.2f)%n",
                    (i + 1),
                    history[i].getCycleLabel(),
                    history[i].getEvaporatorTemperature(),
                    history[i].getCondenserTemperature(),
                    history[i].getCoefficientOfPerformance());
        }

        System.out.println();

        int indexA = inputHandler.readCycleIndex("Select first cycle to compare", historyCount);

        int indexB = -1;
        boolean validSelection = false;

        while (validSelection == false) {
            indexB = inputHandler.readCycleIndex("Select second cycle to compare", historyCount);
            if (indexB == indexA) {
                display.printError("You selected the same cycle twice. Please choose a different one.");
            } else {
                validSelection = true;
            }
        }

        Cycle cycleA = history[indexA];
        Cycle cycleB = history[indexB];

        display.printComparison(cycleA, cycleB);
    }
}