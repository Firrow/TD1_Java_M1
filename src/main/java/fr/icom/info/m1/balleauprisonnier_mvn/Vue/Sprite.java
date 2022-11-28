package fr.icom.info.m1.balleauprisonnier_mvn.Vue;

import javafx.animation.*;
import javafx.beans.property.*;
import javafx.geometry.*;
import javafx.scene.image.*;
import javafx.util.Duration;

public class Sprite extends ImageView {
    private final Rectangle2D[] walkClips;
    private final Rectangle2D[] shootClips;
    private int numCells;
    private int numCellsWalk;
    private int numCellsShoot;
    private Timeline walkTimeline;
    private IntegerProperty frameCounter = new SimpleIntegerProperty(0);
    private Timeline shootTimeline;
    private Timeline timeline;
    public boolean isRunning;


/*
 * Accesseurs et mutateurs
 */
    public Rectangle2D[] getWalkClips() {return walkClips;}
    public Rectangle2D[] getShootClips() {return shootClips;}
    public int getNumCells() {return numCells;}
    public int getNumCellsWalk() {return numCellsWalk;}
    public int getNumCellsShoot() {return numCellsShoot;}
    public Timeline getWalkTimeline() {return walkTimeline;}
    public int getFrameCounter() {return frameCounter.get();}
    public void setFrameCounter(int frameCounter) {this.frameCounter.set(frameCounter);}
    public IntegerProperty frameCounterProperty() {return frameCounter;}
    public Timeline getShootTimeline() {return shootTimeline;}
    public Timeline getTimeline() {return timeline;}
    public void setTimeline(Timeline timeline) {this.timeline = timeline;}


    public Sprite(Image animationImage, int numCells, int numRows, Duration frameTime, String side) {
        this.numCells = numCells;

        double cellWidth  = 64;//animationImage.getWidth() / numCells; //64x64
        double cellHeight = 64;//animationImage.getHeight() / numRows;


        numCellsWalk = 9;

        int lineNumber = 8;
        if(side == "top"){
            lineNumber += 2;
        }

        walkClips = new Rectangle2D[numCellsWalk];
        for (int i = 0; i < numCellsWalk; i++) {
            walkClips[i] = new Rectangle2D(
                    i * cellWidth, cellHeight*lineNumber,
                    cellWidth, cellHeight
            );
        }

        setImage(animationImage);
        setViewport(walkClips[0]);

        walkTimeline = new Timeline(
                new KeyFrame(frameTime, event -> {
                    frameCounter.set((frameCounter.get() + 1) % numCellsWalk);
                    setViewport(walkClips[frameCounter.get()]);
                })
        );

        numCellsShoot = 13;
        lineNumber += 8;

        shootClips = new Rectangle2D[numCellsShoot];
        for (int i = 0; i < numCellsShoot; i++){
            shootClips[i] = new Rectangle2D(
                    i * cellWidth, cellHeight*lineNumber,
                    cellWidth, cellHeight
            );
        }

        shootTimeline = new Timeline(
                new KeyFrame(frameTime, event -> {
                    frameCounter.set((frameCounter.get() + 1) % numCellsShoot);
                    setViewport(shootClips[frameCounter.get()]);
                }));

        timeline = walkTimeline;
        isRunning = false;
    }


    public void playContinuously() {
        isRunning = true;
        frameCounter.set(0);
        timeline = walkTimeline;
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.stop();
        timeline.playFromStart();
    }

    public void playShoot(){
        setFrameCounter(0);
        getTimeline().stop();
        setTimeline(shootTimeline);
        getTimeline().setCycleCount(numCellsShoot);
        getTimeline().setOnFinished(e -> playContinuously());
        getTimeline().playFromStart();
    }

    public void stop() {
        frameCounter.set(0);
        setViewport(walkClips[frameCounter.get()]);
        walkTimeline.stop();
    }
}
