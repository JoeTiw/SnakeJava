package main;

import javafx.animation.AnimationTimer;
import view.Game;

public class Animation extends AnimationTimer {

    public long lastTick=0;

    @Override
    public void handle(long now) {
        if(lastTick == 0){
            lastTick=now;
            Drawer.draw(Game.canvas.getGraphicsContext2D());
            return;
        }
            if (now -lastTick>1000000000 /Game.speed){
                lastTick=now;
                Drawer.draw(Game.canvas.getGraphicsContext2D());
            }
    }
}
