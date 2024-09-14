
# Algorithm Visualizer

**Algorithm Visualizer** is a JavaFX application that visualizes the execution of various algorithms, starting with the Bubble Sort algorithm. The user can control the speed and size of the array being sorted through sliders and can choose different algorithms using a dropdown menu.

## Features

- **Visualizes sorting algorithms**: Currently supports Bubble Sort, with more algorithms like Selection Sort and Insertion Sort planned.
- **Adjustable speed**: Users can control the speed of the animation using a slider.
- **Custom array size**: Users can set the size of the array to be sorted using a size slider.
- **Algorithm selection**: A dropdown menu allows users to select different sorting algorithms (currently only Bubble Sort is implemented).

## Project Structure

```bash
AlgorithmVisualizer/
└── src/
    ├── SortingAlgorithms/
    │   └── BubbleSort/
    │       └── BubbleSort.java
    └── Visualizer.java
```

### Key Components:

1. **Visualizer.java**:
   - The main entry point of the JavaFX application.
   - Handles the user interface and interaction, including selecting algorithms, setting the array size, and controlling the animation speed.
   - Uses sliders to control the speed and size of the sorting algorithm.
   - Currently supports visualizing the **Bubble Sort** algorithm.

2. **BubbleSort.java**:
   - Contains the logic for the Bubble Sort algorithm.
   - Visualizes the sorting process by creating a series of bars, where the height of each bar corresponds to a value in the array.
   - The bars are animated to reflect the sorting process.

## How to Run the Project

### Prerequisites

- **Java Development Kit (JDK 20)** or later.
- **JavaFX SDK 22** or later.

### Setting Up the Project

1. **Download the JavaFX SDK**:
   - Go to [JavaFX Downloads](https://gluonhq.com/products/javafx/) and download the latest version for your platform.
   - Extract the SDK to a directory on your machine (e.g., `/Users/yourusername/Development/javafx-sdk-22.0.2`).

2. **Clone the Repository**:
   - Clone this repository to your local machine.
     ```bash
     git clone https://github.com/yourusername/AlgorithmVisualizer.git
     ```

3. **Configure JavaFX in IntelliJ**:
   - Open the project in IntelliJ IDEA.
   - Set the JavaFX SDK:
     - Open **Project Structure** (`Cmd + ;` on macOS).
     - Under **Libraries**, click `+` and add the JavaFX SDK (select the `lib` folder inside the extracted JavaFX SDK directory).
   - Add the following **VM Options** to your run/debug configuration:
     ```bash
     --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml
     ```
     Replace `/path/to/javafx-sdk` with the actual path to your JavaFX SDK.

4. **Run the Application**:
   - In IntelliJ, click **Run** to start the application.

## Usage

1. **Select an Algorithm**: 
   - From the dropdown menu, select the "Bubble Sort" algorithm.

2. **Set the Array Size**: 
   - Use the size slider to adjust the number of bars in the array.

3. **Set the Speed**:
   - Use the speed slider to adjust how fast the sorting animation should run.

4. **Start the Sorting**:
   - Click the "Start Visualization" button to begin the sorting process.



