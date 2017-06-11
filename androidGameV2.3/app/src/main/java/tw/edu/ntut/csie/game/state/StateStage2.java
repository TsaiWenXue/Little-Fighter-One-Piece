package tw.edu.ntut.csie.game.state;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tw.edu.ntut.csie.game.Character.*;
import tw.edu.ntut.csie.game.Enemy.*;
import tw.edu.ntut.csie.game.Game;
import tw.edu.ntut.csie.game.Pointer;
import tw.edu.ntut.csie.game.R;
import tw.edu.ntut.csie.game.core.Audio;
import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.engine.GameEngine;
import tw.edu.ntut.csie.game.extend.BitmapButton;

public class StateStage2 extends GameState{
    private Stage2BG bg;
    private Audio _music;

    private Navigation controller;
    private Button button;

    private EnemyObject en;

    private CharacterObject ch;


    public StateStage2(GameEngine engine) {
        super(engine);
    }

    @Override
    public void initialize(Map<String, Object> data){
        bg = new Stage2BG();

        _music = new Audio(R.raw.onepiece_op2);
        _music.setRepeating(true);
        _music.play();

        controller = new Navigation();
        button = new Button();
        controller.initialize();
        button.initialize();

        selectCharacter();
        en = new Marin();
        en.initialize();
    }

    @Override
    public void move() {
        bg.move(ch.getX());
        ch.move(bg.getX());
        en.move(ch, bg.getX());
        button.move();
    }

    @Override
    public void show(){
        bg.show();
        controller.show();
        button.show();
        ch.show();
        en.show();
    }

    @Override
    public void release() {
        bg.release();
        _music.release();
        controller.release();
        button.release();
        ch.release();
        en.release();
    }
    @Override
    public void keyPressed(int keyCode) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyReleased(int keyCode) {
        // TODO Auto-generated method stub
    }

    @Override
    public void orientationChanged(float pitch, float azimuth, float roll) {

    }

    @Override
    public void accelerationChanged(float dX, float dY, float dZ) {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean pointerPressed(List<Pointer> pointers) {
        controller.pointerPressed(pointers);
        button.pointerPressed(pointers,ch);
        return true;

    }

    @Override
    public boolean pointerMoved(List<Pointer> pointers) {
        controller.pointerMoved(pointers);
        button.pointerMoved(pointers, ch);
        return false;
    }

    @Override
    public boolean pointerReleased(List<Pointer> pointers) {
        controller.pointerReleased(pointers);
        button.pointerReleased(pointers);
        return false;
    }

    @Override
    public void pause() {
        _music.pause();
    }

    @Override
    public void resume() {
        _music.resume();
    }

    public void selectCharacter() {
        switch (CharacterSelectState.character) {
            case 0:
                ch = new Luffy();
                break;
            case 1:
                CharacterSelectState.character = 0;
                ch = new Zoro();
                break;
        }
        ch.initialize();
    }
}
