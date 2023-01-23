public class GameLoop implements Runnable {
    private Game game;
    private boolean running;
    private final double UPDATE_RATE = 1.0d / 60.0d; //pr sekund -> 60 updates
    private long nextStatTime;

    public GameLoop(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        running = true;
        double accumulator = 0; //keep track of the time it takes to update
        long currentTime, lastUpdate = System.currentTimeMillis(); //keep track of what time it is now, and when its last updated
        nextStatTime = System.currentTimeMillis() + 1000;

        while (running) {
            currentTime = System.currentTimeMillis();
            double lastRenderTimeInSeconds = (currentTime - lastUpdate) / 1000d; //turn from milisecond into seconds
            accumulator += lastRenderTimeInSeconds;
            System.out.println();
            lastUpdate = currentTime;

            if (accumulator >= UPDATE_RATE) {
                while (accumulator >= UPDATE_RATE) {
                    updateGameLoop();
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
