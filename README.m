🐍 Snake Game (Java + DSA)

A classic Snake Game built with Java Swing, using a Deque implemented with LinkedList to manage the snake's body and movement.

📚 Data Structures and Algorithms (DSA) course project, Rungta International Skills University, Bhilai.

---

📌 Features

- Smooth automatic snake movement (moves every 150 ms).
- Arrow-key controls, with reverse movement blocked (e.g. you can't go Left while moving Right).
- Random food generation that never spawns on the snake.
- Score tracking: +10 points for each food eaten, and the snake grows by one block.
- Collision detection with the walls and the snake's own body.
- GAME OVER screen with restart by pressing "R".
- Grid background, red food, and a brighter green head to tell it apart from the body.

---

🧠 Data Structures Used

Data Structure| Purpose
"Deque<Point>" (implemented with LinkedList)| Stores the snake's body positions, head at the front and tail at the back
"Point"| Holds the x and y position of each body part and the food
"Random"| Generates the food position

How Movement Works

On every tick, a new head is created in the current direction and added to the front using "addFirst()".

- If the snake ate food, the tail is kept (the snake grows and the score increases).
- Otherwise, the tail is removed using "removeLast()".

Both operations are O(1) on a LinkedList-backed Deque.

---

📁 Project Structure

File| Description
"SnakeGame.java"| Entry point; creates the 600×600 game window ("JFrame")
"GamePanel.java"| Game logic and drawing: movement, food, score, collisions, game over, restart

---

🎮 Controls

Key| Action
⬆️ Up Arrow| Move up
⬇️ Down Arrow| Move down
⬅️ Left Arrow| Move left
➡️ Right Arrow| Move right
"R"| Restart after Game Over

---

⚙️ Requirements

- JDK: 8 or above
- IDE (optional): VS Code or IntelliJ IDEA
- OS: Windows 10/11 (any OS with Java should work)

---

🚀 How to Run

1. Clone the Repository

git clone https://github.com/gunjanrokade-3016/DSA_GUNJAN_10537.git
cd DSA_GUNJAN_10537

2. Compile

javac SnakeGame.java GamePanel.java

3. Run

java SnakeGame

Alternatively, open the folder in VS Code / IntelliJ IDEA and run "SnakeGame.java".

---

⚙️ Game Settings

You can tweak these settings in "GamePanel.java":

Setting| Default| Meaning
WIDTH / HEIGHT| 600| Board size in pixels
UNIT_SIZE| 20| Size of one grid cell
Timer delay| 150 ms| Lower value = faster snake

---

🔮 Future Scope

- Multiple difficulty levels.
- High score storage and leaderboard.
- Sound effects and different themes.
- Speed increase as the score rises.
- Multiplayer mode.
- Android version with touch controls.

---

👨‍💻 Author

Gunjan Rokade

B.Tech CSE (AI & ML) in association with Google

Rungta International Skills University, Bhilai, Chhattisgarh

Guide: Mr. SuryaMani Verma, DSA Trainer

---

📚 References

- "GeeksforGeeks" (https://www.geeksforgeeks.org/)
- "W3Schools" (https://www.w3schools.com/)
- "Programiz" (https://www.programiz.com/)

---

⭐ If you like this project, don't forget to give it a star!
