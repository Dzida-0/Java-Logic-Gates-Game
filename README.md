# Logic Gates Puzzle Game

This is a JavaFX-based logic puzzle game where players must solve logic puzzles by connecting inputs and outputs using various logic gates. The game features three difficulty levels—Easy, Medium, and Hard—and includes a challenge mode that limits the number of gates you can use to solve each puzzle.

## Features
- **Logic gates**: Use AND, OR, NOT, XOR, NAND, NOR, and XNOR gates to create circuits.
- **Three difficulty levels**: Easy, Medium, and Hard.
- **Challenge mode**: Limits the number of usable gates.
- **Interactive UI**: Players can drag gates onto the canvas, connect them with inputs and outputs, and test their circuits in real time.
- **Real-time feedback**: Outputs are color-coded to show if they are correct (green), incorrect (red/orange), or off (gray).
- **Timer**: Tracks how long it takes to solve each puzzle.

## Instructions

### Creating and Managing Gates
- **Create new gates**: Press the corresponding buttons (e.g., AND, OR, NOT, etc.) on the toolbar to add gates to the canvas.
- **Drag gates**: Right-click and drag gates to reposition them on the canvas.
- **Delete gates**: Left-click on a gate to delete it.

### Connections
- **Create connections**: Drag a cable from a gate's output to another gate's input to connect them.
- **Delete connections**: Left-click on a connection to delete it.

### Inputs and Outputs
- **Inputs**: The input buttons (located on the left sidebar) can be clicked to toggle between ON and OFF states. The goal is to connect the inputs to the correct outputs using the logic gates.

- **Outputs**:
    - **Gray**: The output is OFF and should be OFF.
    - **Orange**: The output is OFF but should be ON.
    - **Green**: The output is ON and should be ON.
    - **Red**: The output is ON but should be OFF.

Your objective is to use the available gates to adjust the outputs so that all outputs are either **green** (correctly ON) or **gray** (correctly OFF).

## To Do
- [ ] Improve UI/UX design
- [ ] Implement saving and loading games
- [ ] Implement scoring system
