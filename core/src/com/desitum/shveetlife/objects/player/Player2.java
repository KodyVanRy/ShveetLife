package com.desitum.shveetlife.objects.player;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.desitum.shveetlife.data.Assets;
import com.desitum.shveetlife.data.GameKeys;
import com.desitum.shveetlife.world.GameInterface;

/**
 * Created by kody on 4/7/15.
 * can be used by kody and people in []
 */
public class Player2 extends Sprite {

    private GameInterface gameInterface;
    private PlayerAnimator playerAnimator;

    private int direction;
    private boolean moving;

    private static final int SPEED = 30;

    public Player2(GameInterface gi, float width, float height, float x, float y){
        //super(texture, 0, 0, texture.getWidth(), texture.getHeight());
        super(Assets.player2, 0, 0, Assets.player2.getWidth(), Assets.player2.getHeight());

        this.setSize(width, height);
        this.setPosition(x, y);

        this.setOriginCenter();

        this.gameInterface = gi;
    }

    public void update(float delta){
        if (moving){
            System.out.println("okk.....");
            if (direction == GameKeys.RIGHT){
                System.out.println("AYAY");
                setX(getX() + SPEED * delta);
            } else if (direction == GameKeys.LEFT){
                setX(getX() - SPEED * delta);
            } else if (direction == GameKeys.UP){
                setY(getY() + SPEED * delta);
            } else if (direction == GameKeys.DOWN){
                setY(getY() - SPEED * delta);
            }
        }
    }

    public void useKey(int key){
        switch (key){
        }
    }

    public void useDirectionalKey(int key){
        if (key == Input.Keys.DPAD_RIGHT){
            direction = GameKeys.RIGHT;
            moving = true;
        } else if (key == Input.Keys.DPAD_LEFT){
            direction = GameKeys.LEFT;
            moving = true;
        } else if (key == Input.Keys.DPAD_UP){
            direction = GameKeys.UP;
            moving = true;
        } else if (key == Input.Keys.DPAD_DOWN){
            direction = GameKeys.DOWN;
            moving = true;
        } else {
            moving = false;
        }
    }

    public float getDamage(Class c){
        return 1; //TODO should be directly related to the item held and object being attacked
    }

    public Vector3 getPositionInFront(){
        if (direction == GameKeys.RIGHT){
            return new Vector3(getX() + getWidth() + 1, getY() + (getHeight()/2), 0);
        } else if (direction == GameKeys.LEFT){
            return new Vector3(getX() - 1, getY() + (getHeight()/2), 0);
        } else if (direction == GameKeys.UP){
            return new Vector3(getX() + (getWidth()/2), getY() + getHeight() + 1, 0);
        } else if (direction == GameKeys.DOWN){
            return new Vector3(getX() + (getWidth()/2), getY() - 1, 0);
        } else {
            return new Vector3(getX(), getY(), 0);
        }
    }
}