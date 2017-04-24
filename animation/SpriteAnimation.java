package fr.game.epis.engine.core.animation;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;

import fr.game.epis.engine.core.Renderer;
import fr.game.epis.engine.core.maths.Vector2;

public class SpriteAnimation {


    private String name;
    private Bitmap texture;
    private Vector2 position;
    private int frameWidth;
    private int frameHeight;
    private int frameCount;
    private int currentFrame;
    private long lastFrameChangeTime;
    private int frameLengthInMilliseconds;
    private Rect frameToDraw;
    private boolean run;
    private RectF whereToDraw;

    public SpriteAnimation(String name, Bitmap texture,int frameWidth, int frameHeight, int frameCount, long lastFrameChangeTime, int frameLengthInMilliseconds){
        this.name = name;
        this.texture = texture;
        this.position = new Vector2(0,0);
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.frameCount = frameCount;
        this.lastFrameChangeTime = lastFrameChangeTime;
        this.frameLengthInMilliseconds = frameLengthInMilliseconds;
        this.frameToDraw = new Rect(0, 0, frameWidth, frameHeight);
        this.run = false;
        this.currentFrame = 0;
        this.whereToDraw = new RectF(this.position.x, 0, this.position.x + this.frameWidth, this.frameHeight);

    }

    private void getCurrentFrame(){

        long time  = System.currentTimeMillis();
        if(this.run) {
            if ( time > this.lastFrameChangeTime + this.frameLengthInMilliseconds) {
                this.lastFrameChangeTime = time;
                this.currentFrame++;
                if (this.currentFrame >= this.frameCount) {
                    this.currentFrame = 0;
                }
            }
        }
        this.frameToDraw.left = this.currentFrame * this.frameWidth;
        this.frameToDraw.right = this.frameToDraw.left + this.frameWidth;

    }

    public void update(Vector2 position){
        this.position= position;
        this.whereToDraw.set((int)this.position.x, 0 , this.position.x + this.frameWidth, this.frameHeight);
        this.getCurrentFrame();
    }

    public void draw(Renderer renderer){
        renderer.draw(this.texture, frameToDraw, whereToDraw);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getTexture() {
        return texture;
    }

    public void setTexture(Bitmap texture) {
        this.texture = texture;
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public void setFrameWidth(int frameWidth) {
        this.frameWidth = frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public void setFrameHeight(int frameHeight) {
        this.frameHeight = frameHeight;
    }

    public int getFrameLengthInMilliseconds() {
        return frameLengthInMilliseconds;
    }

    public void setFrameLengthInMilliseconds(int frameLengthInMilliseconds) {
        this.frameLengthInMilliseconds = frameLengthInMilliseconds;
    }

    public boolean isRun() {
        return run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }
}
