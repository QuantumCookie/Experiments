package utilities;

public final class GTimer // Singleton class
{
    private static GTimer timer = null;

    private GListener listener;
    private Thread thread;
    private boolean isRunning;

    private final double targetFPS, targetTPS;
    private final double minimumFPS;
    
    private float TPS = 0;

    private float deltaTime, lastFrameTime;

    private GTimer(double tfps, double ttps, double mfps, GListener lis)
    {
        targetFPS = tfps;
        targetTPS = ttps;
        minimumFPS = mfps;
        listener = lis;
        thread = new Thread("Game Loop")
        {
            public void run()
            {
                gameLoop();
            }
        };
        isRunning = false;

        lastFrameTime = System.nanoTime();
    }
    
    private GTimer(GListener lis)
    {
        this(60, 60, 60, lis);
    }
    
    public static void initialize(GListener lis)
    {
        timer = new GTimer(lis);
    }
    
    public static void initialize(double tfps, double ttps, double mfps, GListener lis)
    {
        timer = new GTimer(tfps, ttps, mfps, lis);
    }
    
    public static void start()
    {
        timer.startTimer();
    }
    
    public static void stop()
    {
        timer.stopTimer();
    }
    
    public static float TPS()
    {
        return timer.TPS;
    }
    
    private void startTimer()
    {
        if(isRunning)return;
        isRunning = true;
        thread.start();
    }
    
    private void stopTimer()
    {
        if(!isRunning)return;
        isRunning = false;
        try
        {
            thread.join();
        }
        catch(InterruptedException e){}
    }
    
    private boolean isRunning(){return isRunning;}
    
    private void gameLoop()
    {
        double timePerTick = 1000000000L / targetTPS;
        double timePerFrame = 1000000000L / targetFPS;
        int maxFrameSkip = (int)((1000000000L / minimumFPS) / timePerTick);
        
        int achievedFPS = 0, achievedTPS = 0;
        long timer = System.nanoTime() / 1000000L;
        
        int loops = 0, achievedLoops = 0;
        
        long currentTime = 0L, loopTime = 0L;
        
        long accumulatorFPS = 0L, accumulatorTPS = 0L;
        
        long lastTime = System.nanoTime();
        
        while(isRunning)
        {
            currentTime = System.nanoTime();
            loopTime = currentTime - lastTime;
            lastTime = currentTime;
            
            loops = 0;
            
            accumulatorFPS += loopTime;
            accumulatorTPS += loopTime;
            
            while(accumulatorTPS >= timePerTick && loops < maxFrameSkip)
            {
                listener.update();
                achievedTPS++;
                accumulatorTPS -= timePerTick;
                loops++;
            }
            
            if(accumulatorFPS >= timePerFrame)
            {
                listener.render();
                achievedFPS++;
                accumulatorFPS -= timePerFrame;

                deltaTime = (System.nanoTime() - lastFrameTime) / 1000000000L;
                lastFrameTime = System.nanoTime();
            }
            
            TPS = achievedTPS;
            
            if((System.nanoTime() / 1000000L) - timer > 1000)
            {
                timer += 1000;
                //System.out.printf("Updates : %d, Frames : %d, Loops : %d\n", achievedTPS, achievedFPS, achievedLoops);
                achievedFPS = achievedTPS = achievedLoops = 0;
            }
            achievedLoops++;
        }
    }

    public static float deltaTime()
    {
        return timer.deltaTime;
    }
}