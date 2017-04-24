package fr.game.epis.engine.core.object;

import android.graphics.Bitmap;

import java.util.ArrayList;

import fr.game.epis.engine.core.Renderer;
import fr.game.epis.engine.core.animation.SpriteAnimation;
import fr.game.epis.engine.core.maths.Vector2;

/**
 * Created by thomas on 24/04/2017.
 */

public class Sprite {

    private Vector2 position;
    private Vector2 size;

    private Bitmap texture;
    private ArrayList<SpriteAnimation> animations;
    private int currentAnimationIndex;

    public Sprite(Bitmap texture){
        this.position = new Vector2(0,0);
        this.size = new Vector2(texture.getWidth(), texture.getHeight());
        this.texture = texture;
        this.animations = new ArrayList<SpriteAnimation>();
        this.currentAnimationIndex = 0;

    }

    public Sprite(Vector2 position, Bitmap texture){
        this.position = position;
        this.size = new Vector2(texture.getWidth(), texture.getHeight());
        this.texture = texture;
        this.animations = new ArrayList<SpriteAnimation>();
        this.currentAnimationIndex = 0;
    }

    public void update(){
        if(this.animations.isEmpty()){
            this.animations.get(this.currentAnimationIndex).update(this.position);
        }
    }

    public void draw(Renderer renderer){
        if(this.animations.isEmpty()){
            renderer.draw(this.texture,this.position.x, this.position.y);
        }
        else{
            this.animations.get(this.currentAnimationIndex).draw(renderer);
        }
    }

    public void addAnimation(SpriteAnimation animation){
        this.animations.add(animation);
    }

    public void setAnimation(String animationName){
        for(int i = 0; i < this.animations.size(); i++){
            if(animationName.equals(this.animations.get(i).getName())){
                this.currentAnimationIndex = i;
                break;
            }
        }
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getSize() {
        return size;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }

    public Bitmap getTexture() {
        return texture;
    }

    public void setTexture(Bitmap texture) {
        this.texture = texture;
    }
}
