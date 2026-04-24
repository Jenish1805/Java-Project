# Vapour Compression Refrigeration Cycle Simulator (Java Console Application)

## Team Members
- Jenish Pancholi (IU2441230510)
- Eaktra Pandey (IU2441231862)
- Jainesh Patel (IU2441230479)

## Project Overview
This is a terminal-based Java application that simulates a Vapour Compression Refrigeration Cycle (VCRC).  
It is designed as an educational tool to demonstrate thermodynamic principles such as cooling effect, compressor work, and coefficient of performance (COP).

The program is menu-driven and allows users to run simulations, view history, and compare different cycle results.

## Features

- Menu-driven terminal interface
- Input validation for all user inputs
- Simulation of four refrigeration cycle stages:
  1. Compression
  2. Condensation
  3. Expansion
  4. Evaporation
- Step-by-step text-based simulation
- Calculation of:
  - Cooling Effect (Qe)
  - Work Done by Compressor (Wc)
  - Coefficient of Performance (COP)
- Efficiency rating system:
  - COP less than 2: Poor
  - COP between 2 and 4: Moderate
  - COP greater than 4: Good
- History storage of multiple simulations
- Comparison between two cycles
- Structured and formatted output

---

## Formulas Used

- Cooling Effect:
  Qe = m × Cp × Te

- Work Done by Compressor:
  Wc = m × Cp × (Tc − Te)

- Coefficient of Performance:
  COP = Qe / Wc

## System Requirements

- Java Development Kit (JDK) 8 or above
- Terminal / Command Prompt / PowerShell

Recommended:
- Java SE 8 for maximum compatibility with legacy environments

---

## Compilation commands mismatch 

- javac --release 8 Main.java
- java Main