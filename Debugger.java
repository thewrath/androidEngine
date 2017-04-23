package fr.game.epis.engine.core;

/**
 * Created by thomas on 23/04/2017.
 */

public class Debugger {

    private Engine engine;

    public Debugger(Engine engine){
        this.engine = engine;
    }

    public void update(){

    }

    public void draw(){

        this.engine.getRenderer().getCanvas().drawText("FPS:" + this.engine.getFps(), 20, 40, this.engine.getRenderer().getPaint());

    }
}
