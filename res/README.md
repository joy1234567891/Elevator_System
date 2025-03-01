# Building Elevator System

## About/Overview
This project is a Java application designed to build an MVC architecture of a building elevator system. 

## Features
- Feature 1: Steps the system through a single button press
- Feature 2: Stops the system through a single button press
- Feature 3: Restart the system through a single button press
- Feature 4: Displays the current state of the system
- Feature 5: Quits the system through a single button press
- Feature 6: Accepts user input to add requests for the elevator 

## How To Run
To run the jar file, use the following command:
java -jar building.jar

## How to Use the Program
There are several features in this program that can be used to interact with the elevator system:
1. Press the "Step" button to step the system once
2. Press the "Stop" button to stop the system
3. Press the "Restart" button to restart the system
5. Press the "Quit" button to quit the system
6. Press the "Request" button to add requests for the elevator. 
   After pressing the "Request" button, you will be prompted to enter the start floor number and end floor number.

## Design/Model Changes
In the preliminary design, we designed distributeUpRequests and distributeDownRequests. 
In the current implementation, we combined these two methods to just one method 
called "distributeRequests", in order to avoid code duplication.

## Assumptions
During the development and implementation of this program, we made the following assumptions:
- Assumption 1: The user will only enter valid floor numbers
- Assumption 2: The user will only enter valid input when prompted

## Limitations
The current version of the program has the following limitations:
- Limitation 1: not be able to start a new simulation with different parameters

## Citations
This project made use of the following resources:
[1] “Trail: Creating a GUI with swing,” Trail: Creating a GUI With Swing (The JavaTM Tutorials), https://docs.oracle.com/javase/tutorial/uiswing/ (accessed Apr. 15, 2024). 
