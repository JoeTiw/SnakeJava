package main;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import view.Game;

import static javafx.scene.text.TextAlignment.RIGHT;

public class Drawer {

    public static void draw(GraphicsContext gc){

        if (Game.gameover){
            gc.setFill(Color.RED);
            gc.setFont(new Font("", 50));
            gc.fillText("GAME OVER", 100, 250);
            gc.setFill(Color.GOLD);
            gc.setFont(new Font("", 30));
            gc.fillText("SCORE : "+(Game.snake.size()-3), 100, 280);
            Game.animation.stop();
            Game.displayButton();
            return;
        }



        int x,y;

        for (int i=Game.snake.size()-1;i>=1;i-- ){
            x=Game.snake.get(i-1).getX();
            y=Game.snake.get(i-1).getY();
            Game.snake.get(i).setX(x);
            Game.snake.get(i).setY(y);

        }

        switch (Game.direction){
            case up:
                y= Game.snake.get(0).getY();
                y--;
                Game.snake.get(0).setY(y);
                if(y<0){
                    Game.gameover=true;
                }

                break;

            case down:
                y= Game.snake.get(0).getY();
                y++;
                Game.snake.get(0).setY(y);
                if(y>(Game.height-1)){
                    Game.gameover=true;
                }

                break;

            case left:
                x= Game.snake.get(0).getX();
                x--;
                Game.snake.get(0).setX(x);
                    if(x<0){
                        Game.gameover=true;
                    }
                break;

            case right:
                x= Game.snake.get(0).getX();
                x++;
                Game.snake.get(0).setX(x);
                    if (x>Game.width){
                        Game.gameover=true;
                    }
                break;
        }



        //check collision

        if (Game.snake.get(0).getX()==Game.foodX && Game.snake.get(0).getY()==Game.foodY){
            Game.snake.add(new Body(-1,-1));
            Game.newFood();
        }

        //draw background
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0, Game.width*Game.cornerSize,Game.height*Game.cornerSize);


        //draw item
        gc.setFill(Color.RED);
        gc.fillOval(Game.foodX*Game.cornerSize,Game.foodY*Game.cornerSize,Game.cornerSize,Game.cornerSize );


        //score
        gc.setFill(Color.GOLD);
        gc.setFont(new Font("",30));
        gc.fillText("Score "+(Game.snake.size()-3), 350, 30);


        //self collision
        for(int i=1;i<=Game.snake.size()-1;i++){
            if(Game.snake.get(0).getY()==Game.snake.get(i).getY()
                    && Game.snake.get(0).getX()==Game.snake.get(i).getX()){
                Game.gameover=true;
            }
        }


        //draw snake
        for(Body c:Game.snake){
            gc.setFill(Color.GREEN);
            gc.fillRect(c.getX()*Game.cornerSize, c.getY()*Game.cornerSize, Game.cornerSize-1, Game.cornerSize-1);
        }
    }
}
