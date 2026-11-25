import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

public class GamePanel extends JPanel {

    // Game settings
    final int WIDTH = 600;
    final int HEIGHT = 600;
    final int UNIT_SIZE = 20;

    // DSA: Deque stores snake body positions
    Deque<Point> snake = new LinkedList<>();

    // Food position
    Point food;

    // Random food generation
    Random random = new Random();

    // Game variables
    String direction = "RIGHT";

    int score = 0;

    boolean gameOver = false;

    Timer timer;

    // Constructor
    GamePanel() {

        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        setBackground(Color.BLACK);

        setFocusable(true);

        // Keyboard controls
        addKeyBinding(KeyEvent.VK_UP, "UP");
        addKeyBinding(KeyEvent.VK_DOWN, "DOWN");
        addKeyBinding(KeyEvent.VK_LEFT, "LEFT");
        addKeyBinding(KeyEvent.VK_RIGHT, "RIGHT");

        // Restart game using R
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(
            KeyStroke.getKeyStroke(KeyEvent.VK_R, 0),
            "RESTART"
        );

        getActionMap().put("RESTART", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameOver) {
                    restartGame();
                }
            }
        });

        startGame();
    }

    // Initialize game
    public void startGame() {

        snake.clear();

        snake.add(new Point(100, 100));
        snake.add(new Point(80, 100));
        snake.add(new Point(60, 100));

        direction = "RIGHT";

        score = 0;

        gameOver = false;

        generateFood();

        // Snake moves every 150 milliseconds
        if (timer != null) {
            timer.stop();
        }

        timer = new Timer(150, e -> moveSnake());

        timer.start();

        repaint();
    }

    // Keyboard controls
    private void addKeyBinding(int key, String newDirection) {

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(
            KeyStroke.getKeyStroke(key, 0),
            newDirection
        );

        getActionMap().put(newDirection, new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (gameOver) {
                    return;
                }

                // Prevent reverse movement
                if (newDirection.equals("UP") &&
                    !direction.equals("DOWN")) {

                    direction = "UP";
                }

                else if (newDirection.equals("DOWN") &&
                         !direction.equals("UP")) {

                    direction = "DOWN";
                }

                else if (newDirection.equals("LEFT") &&
                         !direction.equals("RIGHT")) {

                    direction = "LEFT";
                }

                else if (newDirection.equals("RIGHT") &&
                         !direction.equals("LEFT")) {

                    direction = "RIGHT";
                }
            }
        });
    }

    // Snake movement
    public void moveSnake() {

        if (gameOver) {
            return;
        }

        // Get current head
        Point head = snake.peekFirst();

        Point newHead = new Point(head);

        // Change position
        switch (direction) {

            case "UP":
                newHead.y -= UNIT_SIZE;
                break;

            case "DOWN":
                newHead.y += UNIT_SIZE;
                break;

            case "LEFT":
                newHead.x -= UNIT_SIZE;
                break;

            case "RIGHT":
                newHead.x += UNIT_SIZE;
                break;
        }

        // Check food
        boolean eatingFood = newHead.equals(food);

        // Check wall collision
        if (newHead.x < 0 ||
            newHead.x >= WIDTH ||
            newHead.y < 0 ||
            newHead.y >= HEIGHT) {

            endGame();
            return;
        }

        // Check self collision
        Point tail = snake.peekLast();

        for (Point part : snake) {

            if (part.equals(newHead) &&
                (eatingFood || !part.equals(tail))) {

                endGame();
                return;
            }
        }

        // Add new head
        snake.addFirst(newHead);

        if (eatingFood) {

            // Increase score
            score += 10;

            // Generate new food
            generateFood();

        } else {

            // Remove tail if food is not eaten
            snake.removeLast();
        }

        repaint();
    }

    // Generate food randomly
    public void generateFood() {

        do {

            int x = random.nextInt(WIDTH / UNIT_SIZE)
                     * UNIT_SIZE;

            int y = random.nextInt(HEIGHT / UNIT_SIZE)
                     * UNIT_SIZE;

            food = new Point(x, y);

        } while (snake.contains(food));
    }

    // Game over
    public void endGame() {

        gameOver = true;

        timer.stop();

        repaint();
    }

    // Restart
    public void restartGame() {

        startGame();
    }

    // Draw game
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // Draw grid
        g2.setColor(new Color(40, 40, 40));

        for (int x = 0; x < WIDTH; x += UNIT_SIZE) {
            g2.drawLine(x, 0, x, HEIGHT);
        }

        for (int y = 0; y < HEIGHT; y += UNIT_SIZE) {
            g2.drawLine(0, y, WIDTH, y);
        }

        // Draw food
        g2.setColor(Color.RED);

        g2.fillOval(
            food.x,
            food.y,
            UNIT_SIZE,
            UNIT_SIZE
        );

        // Draw snake
        for (Point part : snake) {

            if (part.equals(snake.peekFirst())) {
                g2.setColor(Color.GREEN);
            } else {
                g2.setColor(new Color(0, 150, 0));
            }

            g2.fillRect(
                part.x,
                part.y,
                UNIT_SIZE,
                UNIT_SIZE
            );
        }

        // Draw score
        g2.setColor(Color.WHITE);

        g2.setFont(new Font("Arial", Font.BOLD, 20));

        g2.drawString("Score: " + score, 10, 25);

        // Game over screen
        if (gameOver) {

            g2.setColor(Color.WHITE);

            g2.setFont(new Font("Arial", Font.BOLD, 40));

            String message = "GAME OVER";

            FontMetrics metrics = g2.getFontMetrics();

            int x = (WIDTH - metrics.stringWidth(message)) / 2;

            g2.drawString(message, x, HEIGHT / 2);

            g2.setFont(new Font("Arial", Font.PLAIN, 20));

            String restart = "Press R to Restart";

            int restartX =
                (WIDTH - g2.getFontMetrics().stringWidth(restart)) / 2;

            g2.drawString(restart, restartX, HEIGHT / 2 + 40);
        }
    }
}