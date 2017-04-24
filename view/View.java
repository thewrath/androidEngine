package fr.game.epis.engine.core.view;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import fr.game.epis.engine.R;
import fr.game.epis.engine.core.Renderer;
import fr.game.epis.engine.core.animation.SpriteAnimation;
import fr.game.epis.engine.core.maths.Vector2;
import fr.game.epis.engine.core.object.Sprite;

/**
 * Created by thomas on 22/04/2017.
 */

public class View{
    private Sprite bob;

    public View(Context context){
        bob = new Sprite(new Vector2(150,50), BitmapFactory.decodeResource(context.getResources(), R.drawable.scifiunit_11));
        bob.addAnimation(new SpriteAnimation("play", BitmapFactory.decodeResource(context.getResources(), R.drawable.bob), 100,50, 5, 0, 100));
    }

    public void update(){
        this.bob.update();
    }

    public void draw(Renderer renderer){
        renderer.getCanvas().drawColor(Color.argb(255,  26, 128, 182));
        renderer.getPaint().setColor(Color.argb(255,  249, 129, 0));
        renderer.getPaint().setTextSize(45);
        bob.draw(renderer);

    }
}


