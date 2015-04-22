package com.desitum.shveetlife.objects.npc;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.desitum.shveetlife.data.Assets;
import com.desitum.shveetlife.network.ProcessData;
import com.desitum.shveetlife.world.GameInterface;

/**
 * Created by Zmyth97 on 4/21/2015.
 */
public class NPC extends Sprite {

    private GameInterface gameInterface;

    private int direction;
    private boolean moving;

    private float speed;

    public static final float WIDTH = 10;
    public static final float HEIGHT = 10;

    private final int RIGHT = 1;
    private final int LEFT = 2;
    private final int UP = 3;
    private final int DOWN = 4;

    public final float X;
    public final float Y;

    public NPC (GameInterface gi, float width, float height, float x, float y){
        super(Assets.npc, 0, 0, Assets.npc.getWidth(), Assets.npc.getHeight());

        this.X = x;
        this.Y = y;

        this.setSize(width, height);
        this.setPosition(x, y);

        this.setOriginCenter();
        this.speed = 30;

        this.gameInterface = gi;
    }

    public void update(float delta){
        float duration = (float)(Math.random() * 8000);
        if(duration <= 1000){
            duration += 1000;
        }
        float time = 0;
        if (time <= duration){
        if (moving) {
            if (direction == RIGHT) {
                setX(getX() + speed * delta);
            } else if (direction == LEFT) {
                setX(getX() - speed * delta);
            } else if (direction == UP) {
                setY(getY() + speed * delta);
            } else if (direction == DOWN) {
                setY(getY() - speed * delta);
            }
            updateSpeed();
            time += delta;
        }
        }
    }

    public void wantsToMove(){
        int randomChoice = (int)(Math.random() * 3);
        if (randomChoice == 0){
            direction = RIGHT;
            moving = true;
        } else if (randomChoice == 1){
            direction = LEFT;
            moving = true;
        } else if (randomChoice == 2){
            direction = UP;
            moving = true;
        } else if (randomChoice == 3){
            direction = DOWN;
            moving = true;
        } else {
            moving = false;
        }
    }

    public float getDamage(Class c){
        return 1;
    }

    public Vector3 getPositionInFront(){ //NOT USED YET, BUT WILL FOR CONTROLLER LATER
        if (direction == RIGHT){
            return new Vector3(getX() + getWidth() + 1, getY() + (getHeight()/2), 0);
        } else if (direction == LEFT){
            return new Vector3(getX() - 1, getY() + (getHeight()/2), 0);
        } else if (direction == UP){
            return new Vector3(getX() + (getWidth()/2), getY() + getHeight() + 1, 0);
        } else if (direction == DOWN){
            return new Vector3(getX() + (getWidth()/2), getY() - 1, 0);
        } else {
            return new Vector3(getX(), getY(), 0);
        }
    }

    public static NPC loadFromString(String data, GameInterface gi){
        String[] dataStrings = data.split(" ");

        float x = Float.parseFloat(dataStrings[1]);
        float y = Float.parseFloat(dataStrings[2]);
        float width = Float.parseFloat(dataStrings[3]);
        float height = Float.parseFloat(dataStrings[4]);

        return new NPC(gi, width, height, x, y);
    }


    @Override
    public String toString(){ //HAhahaHA WHAT NOW?
        return "NPC " + getX() + " " + getY() + " " + getWidth() + " " + getHeight();
    }

    public String getUpdateString(){ //BLERGGGGGG
        return ProcessData.EDIT + " " + ProcessData.PLAYER + " " + getX() + " " + getY();
    }

    private void updateSpeed(){
        speed = gameInterface.getTile(new Vector3(getOriginX(), getOriginY(), 0)).getPlayerSpeed();
    }

    public boolean isMoving(){
        return moving;
    }
}
