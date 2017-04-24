package fr.game.epis.engine.core;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import fr.game.epis.engine.R;

/**
 * Created by thomas on 23/04/2017.
 */

public class Renderer {

    private SurfaceHolder surfaceHolder;
    private Canvas canvas;
    private Paint paint;

    private boolean lockRenderer = true;

    public Renderer(Engine engine){
        this.surfaceHolder = engine.getHolder();
        this.paint = new Paint();
    }

    public void pushRenderer(){
        if(this.lockRenderer){
            if (this.surfaceHolder.getSurface().isValid()) {
                this.lockRenderer = false;
                this.canvas = this.surfaceHolder.lockCanvas();
            }
        }
    }

    public void popRenderer(){
        if(!this.lockRenderer){
            if (this.surfaceHolder.getSurface().isValid()){
                this.lockRenderer = true;
                this.surfaceHolder.unlockCanvasAndPost(this.canvas);
            }
        }
    }


    public void draw(Bitmap texture, float x, float y){
        this.canvas.drawBitmap(texture, x, y, this.paint);
    }

    public void draw(Bitmap texture, Rect rectangle, RectF rectangleFrame){
        this.canvas.drawBitmap(texture, rectangle, rectangleFrame, this.paint);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public boolean isActive(){
        if(this.surfaceHolder.getSurface().isValid()&& !this.lockRenderer){
            return true;
        }
        else return false;
    }
}
