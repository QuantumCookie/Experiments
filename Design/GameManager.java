import utilities.Input;
import utilities.State;

import java.awt.*;
import java.awt.event.*;

import static utilities.State.*;

public class GameManager
{
    private Test game;

    private MainMenu mainMenu;
    private PauseMenu pauseMenu;

    GameManager(Test g)
    {
        game = g;

        mainMenu = new MainMenu(this);
        pauseMenu = new PauseMenu(this);
    }

    void update()
    {
        switch(game.getState())
        {
            case MAINMENU:
                mainMenu.update();
                break;
            case PAUSED:
                pauseMenu.update();
                checkPauseToggle();
                break;
            case PLAYING:
                checkPauseToggle();
            case GAMEOVER:
        }
    }

    void render(Graphics2D g)
    {
        switch(game.getState())
        {
            case MAINMENU:
                mainMenu.render(g);
                break;
            case PAUSED:
                pauseMenu.render(g);
                break;
            case PLAYING:
            case GAMEOVER:
        }
    }

    private void checkPauseToggle()
    {
        if(Input.keyUp(KeyEvent.VK_ESCAPE))togglePause();
    }

    public void togglePause()
    {
        if(game.getState() == State.PLAYING)game.setState(State.PAUSED);
        else game.setState(State.PLAYING);
    }

    public void mainMenu()
    {
        game.setState(State.MAINMENU);
    }

    public void newGame()
    {
        game.setState(State.PLAYING);
        /* TODO: init game objects */
        //System.out.println("New Game");
    }

    public void quit()
    {
        System.exit(0);
    }
}
