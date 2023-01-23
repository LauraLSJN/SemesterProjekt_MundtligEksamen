public class GameLoop implements Runnable {
    private Game game;
    private boolean running;
    private final double UPDATE_RATE = 1.0d / 60.0d; //1.0d / 60.0d;
    private long nextStatTime;

    public GameLoop(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        running = true;
        double accumulator = 0;
        long currentTime, lastUpdate = System.currentTimeMillis();
        nextStatTime = System.currentTimeMillis() + 1000;

        while (running) {
            currentTime = System.currentTimeMillis();
            double lastRenderTimeInSeconds = (currentTime - lastUpdate) / 1000d;
            accumulator += lastRenderTimeInSeconds;
            lastUpdate = currentTime;

            if (accumulator >= UPDATE_RATE) {
                while (accumulator >= UPDATE_RATE) {
                    updateGameLoop();
                    System.out.println("Opdaterer gameloop");
                    System.out.println(nextStatTime);
                    accumulator -= UPDATE_RATE;
                }
                renderGameLoop();
            }
            printStats();
        }
    }

    private void printStats() {
        if (System.currentTimeMillis() > nextStatTime) {
            nextStatTime = System.currentTimeMillis() + 1000;
        }
    }

    private void renderGameLoop() {
        game.renderGame();
    }

    private void updateGameLoop() {
        game.updateGame();
    }
}
