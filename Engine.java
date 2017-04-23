package fr.game.epis.engine.core;

import android.content.Context;
import android.util.Log;
import android.view.SurfaceView;

import java.util.ArrayList;

import fr.game.epis.engine.core.view.View;

/**
 * Created by thomas on 22/04/2017.
 */

public class Engine extends SurfaceView implements Runnable{


    private boolean debug = true;

    private Thread engineThread;
    private volatile boolean running;
    private long timeThisFrame;
    private long fps;

    private ArrayList<View> screens;
    private int currentScreen;

    private Renderer renderer;
    private Debugger debugger;

    public Engine(Context context){
        super(context);
        this.running = false;
        this.screens = new ArrayList<View>();
        this.currentScreen = 0;
        this.renderer = new Renderer(this);
        this.debugger = new Debugger(this);
    }

    public void start(){
        this.running = true;
    }

    @Override
    public void run(){
        while(this.running){
            long startFrameTime = System.currentTimeMillis();
            if(this.screens.size() > 0){
                this.update();
                this.draw();
            }

            this.timeThisFrame = System.currentTimeMillis() - startFrameTime;
            if (this.timeThisFrame > 0) {
                this.fps = 1000 / this.timeThisFrame;
            }
        }
    }

    private void update(){
        if(this.debug){
            this.debugger.update();
        }
        this.screens.get(this.currentScreen).update();
    }

    private void draw(){
        this.renderer.pushRenderer();
        if(this.renderer.isActive()){
            this.screens.get(this.currentScreen).draw(this.renderer);
            if(this.debug){
                this.debugger.draw();
            }
        }
        this.renderer.popRenderer();
    }

    public void pause(){
        this.running = false;
        try{
            this.engineThread.join();
        }
        catch(InterruptedException e){
            Log.e("Error: ","joining thread");
        }
    }

    public void resume(){
        this.running = true;
        this.engineThread = new Thread(this);
        this.engineThread.start();

    }

    public void addScreen(View screen){
        this.screens.add(screen);
    }

    public void setCurrentScreen(int index){
        if(this.screens.size() > index){
            this.currentScreen = index;
        }

    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public long getFps() {
        return fps;
    }

    public void setFps(long fps) {
        this.fps = fps;
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }
}
