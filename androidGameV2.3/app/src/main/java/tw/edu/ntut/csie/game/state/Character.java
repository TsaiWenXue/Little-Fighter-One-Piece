package tw.edu.ntut.csie.game.state;

import tw.edu.ntut.csie.game.core.MovingBitmap;
import tw.edu.ntut.csie.game.extend.Animation;
import tw.edu.ntut.csie.game.GameObject;

public class Character implements GameObject {
    private Animation ch;
    private Animation ch_r;
    private Animation chRun;
    private Animation chRun_r;
    private Animation chAttack;
    private Animation chAttack_r;
    private Animation chDefend;
    private Animation chDefend_r;
    private Animation chJump;
    private Animation chJump_r;

    private int px, py;
    private int py_jump;
    private boolean run = false, run_r = false;
    private boolean visible = false, visible_r = false;
    public boolean attack = false, attack_r = false;
    public boolean jump = false, jump_r = false;
    public int[] attackArea = new int[4];

    public Character() {
        ch = new Animation();
        ch_r = new Animation();

        chRun = new Animation();
        chRun_r = new Animation();
        chAttack = new Animation();
        chAttack_r = new Animation();
        chDefend = new Animation();
        chDefend_r = new Animation();
        chJump = new Animation();
        chJump_r = new Animation();

        px = 400; py = 200;
        py_jump = py - 137;
    }

    public boolean attack() {
        return attack;
    }



    //Character normal bitmap loadin
    public void addNormal(int resId) {
        ch.addFrame(resId);
    }
    public void addNormalReverse(int resId) {
        ch_r.addFrame(resId);
    }

    //Character Run add frame
    public void addRun(int resId) {
        chRun.addFrame(resId);
    }
    public void addRunReverse(int resId) {
        chRun_r.addFrame(resId);
    }

    //Character Attack add frame
    public void addAttack(int resId) {
        chAttack.addFrame(resId);
    }
    public void addAttackReverse(int resId) {
        chAttack_r.addFrame(resId);
    }

    //Character Defend add frame
    public void addDefend(int resId){
      chDefend.addFrame(resId);
    }
    public void addDefendReverse(int resId){
      chDefend_r.addFrame(resId);
    }

    public void addJump(int resId){
      chJump.addFrame(resId);
    }
    public void addJumpReverse(int resId){
      chJump_r.addFrame(resId);
    }

    public void initialize() {
        visible = true;
        visible_r = false;
        run = false;
        run_r = false;

        ch.setLocation(px, py);
        ch_r.setVisible(visible_r);

        ch.setDelay(5);
        ch_r.setDelay(5);
        chRun.setDelay(2);
        chRun_r.setDelay(2);
        chAttack.setDelay(1);
        chAttack_r.setDelay(1);
        chDefend.setDelay(2);
        chDefend_r.setDelay(2);
      //  chJump.setDelay(1);
      //  chJump_r.setDelay(1);

        chAttack.setRepeating(false);
        chAttack_r.setRepeating(false);
        chDefend.setRepeating(false);
        chDefend_r.setRepeating(false);
        chJump.setRepeating(false);
        chJump_r.setRepeating(false);

        chRun.setVisible(run);
        chRun_r.setVisible(run_r);
        chAttack.setVisible(Button.atPointerPressed);
        chAttack_r.setVisible(Button.atPointerPressed);
        chDefend.setVisible(Button.dfPointerPressed);
        chDefend_r.setVisible(Button.dfPointerPressed);
        chJump.setVisible(Button.jpPointerPressed);
        chJump_r.setVisible(Button.jpPointerPressed);

        chAttack.setCurrentFrameIndex(-1);
        chAttack_r.setCurrentFrameIndex(-1);
        chDefend.setCurrentFrameIndex(-1);
        chDefend_r.setCurrentFrameIndex(-1);
        chJump.setCurrentFrameIndex(-1);
        chJump_r.setCurrentFrameIndex(-1);

    }

    public int getX() {
        if (Navigation.controllerPx - Navigation.initialCtrlPx < 0)
            return ch_r.getX();
        else
            return ch.getX();
    }
    public int getY() {
        return ch.getY();
    }

    @Override
    public void show(){
        ch.show();
        ch_r.show();
        chRun.show();
        chRun_r.show();
        chAttack.show();
        chAttack_r.show();
        chDefend.show();
        chDefend_r.show();
        chJump.show();
        chJump_r.show();
    }

    @Override
    public void move() {
        ch.move();
        ch_r.move();
        chRun.move();
        chRun_r.move();
        chAttack.move();
        chAttack_r.move();
        chDefend.move();
        chDefend_r.move();
        chJump.move();
        chJump_r.move();

        //Let Character move base on navigation
        if (chAttack_r.getCurrentFrameIndex() == -1 && chAttack.getCurrentFrameIndex() == -1) {
            py += (Navigation.controllerPy - Navigation.initialCtrlPy)/10;
            if (py < 175 || py > 375)
                py -= (Navigation.controllerPy - Navigation.initialCtrlPy)/10;
            if (Stage1BG.roadPx == 800 || Stage1BG.roadPx == -800)
                px += (Navigation.controllerPx -  Navigation.initialCtrlPx)/5;
            if (px > 750 || px < 0)
                px -= (Navigation.controllerPx -  Navigation.initialCtrlPx)/5;
            else if (Stage1BG.roadPx < 800 && px < 400)
                px++;
            else if (Stage1BG.roadPx > -800 && px > 400)
                px--;
        }

        ch.setLocation(px, py);
        ch_r.setLocation(px, py);
        chRun.setLocation(px, py);
        chRun_r.setLocation(px, py);
        chAttack.setLocation(px,py);
        chDefend.setLocation(px,py);
        chDefend_r.setLocation(px,py);
        chJump.setLocation(px,py-137);
        chJump_r.setLocation(px,py-137);

        if(chAttack_r.getCurrentFrameIndex() >= 0)
            chAttack_r.setLocation( (px - chAttack_r.getWidth() + ch_r.getWidth()) ,py);
        else
            chAttack_r.setLocation(px, py);

        //Character stop running
        if (chAttack_r.getCurrentFrameIndex() == -1 && chAttack.getCurrentFrameIndex() == -1) {
            if (Navigation.controllerPx - Navigation.initialCtrlPx == 0 && run_r == true) {
                visible = false;
                visible_r = true;
                run = false;
                run_r = false;

                ch.setVisible(visible);
                chRun.setVisible(run);
                chRun_r.setVisible(run_r);
                ch_r.setVisible(visible_r);
            }
            else if (Navigation.controllerPx - Navigation.initialCtrlPx == 0 && run == true) {
                visible = true;
                visible_r = false;
                run = false;
                run_r = false;

                ch_r.setVisible(visible_r);
                chRun.setVisible(run);
                chRun_r.setVisible(run_r);
                ch.setVisible(visible);
            }

            //decide Character face direction with Navigation
            if (Navigation.controllerPx - Navigation.initialCtrlPx < 0) {
                visible = false;
                visible_r = false;
                run_r = true;
                run = false;

                ch_r.setVisible(visible_r);
                ch.setVisible(visible);
                chRun.setVisible(run);
                chRun_r.setVisible(run_r);
            }
            else if (Navigation.controllerPx - Navigation.initialCtrlPx > 0) {
                visible = false;
                visible_r = false;
                run = true;
                run_r = false;

                ch.setVisible(visible);
                ch_r.setVisible(visible_r);
                chRun_r.setVisible(run_r);
                chRun.setVisible(run);
            }
        }
        //Character Attack perform
        if (Button.atPointerPressed == true && (visible == true || run == true)){
            visible = false;
            visible_r = false;
            run = false;
            run_r = false;
            chAttack.setVisible(Button.atPointerPressed);
            ch.setVisible(visible);
            ch_r.setVisible(visible_r);
            chRun_r.setVisible(run_r);
            chRun.setVisible(run);
            chAttack.reset();
            attack = true;
        }
        else if (Button.atPointerPressed == true && (visible_r == true || run_r == true)){
            visible = false;
            visible_r = false;
            run = false;
            run_r = false;

            chAttack_r.setVisible(Button.atPointerPressed);
            ch.setVisible(visible);
            ch_r.setVisible(visible_r);
            chRun_r.setVisible(run_r);
            chRun.setVisible(run);
            chAttack_r.reset();
            attack_r = true;
        }

        if ( chAttack.isLastFrame() && (visible_r == false)) {
            visible = true;
            ch.setVisible(visible);
            attack = false;
        }
        else if ( chAttack_r.isLastFrame() ) {
            visible_r = true;
            ch_r.setVisible(visible_r);
            attack_r = false;
        }


        //Set Attack Area
        if (attack) {
            attackArea[0] = chAttack.getX();
            attackArea[1] = chAttack.getY();
            attackArea[2] = chAttack.getY() + chAttack.getHeight();
            attackArea[3] = chAttack.getX() + chAttack.getWidth();
        }
        else if (attack_r) {
            attackArea[0] = chAttack_r.getX();
            attackArea[1] = chAttack_r.getY();
            attackArea[2] = chAttack_r.getY() + chAttack_r.getHeight();
            attackArea[3] = chAttack_r.getX() + chAttack_r.getWidth();
        }
        else {
            for(int i = 0; i < 4; i++) {
                attackArea[i] = 0;
            }
        }

        //Character Defend perform
        if (Button.dfPointerPressed == true && (visible == true || run == true)){
            visible = false;
            visible_r = false;
            run = false;
            run_r = false;
            chDefend.setVisible(Button.dfPointerPressed);
            ch.setVisible(visible);
            ch_r.setVisible(visible_r);
            chRun_r.setVisible(run_r);
            chRun.setVisible(run);
            chDefend.reset();
        }
        else if (Button.dfPointerPressed == true && (visible_r == true || run_r == true)){
            visible = false;
            visible_r = false;
            run = false;
            run_r = false;

            chDefend_r.setVisible(Button.dfPointerPressed);
            ch.setVisible(visible);
            ch_r.setVisible(visible_r);
            chRun_r.setVisible(run_r);
            chRun.setVisible(run);
            chDefend_r.reset();
        }

        if ( chDefend.isLastFrame() && (visible_r == false)) {
            visible = true;
            ch.setVisible(visible);
        }
        else if ( chDefend_r.isLastFrame() ) {
            visible_r = true;
            ch_r.setVisible(visible_r);
        }
        //character jump perform
        if (Button.jpPointerPressed == true && (visible == true || run == true)){
            visible = false;
            visible_r = false;
            run = false;
            run_r = false;
            chJump.setVisible(Button.jpPointerPressed);
            ch.setVisible(visible);
            ch_r.setVisible(visible_r);
            chRun_r.setVisible(run_r);
            chRun.setVisible(run);
            chJump.reset();
        }
        else if (Button.jpPointerPressed == true && (visible_r == true || run_r == true)){
            visible = false;
            visible_r = false;
            run = false;
            run_r = false;

            chJump_r.setVisible(Button.jpPointerPressed);
            ch.setVisible(visible);
            ch_r.setVisible(visible_r);
            chRun_r.setVisible(run_r);
            chRun.setVisible(run);
            chJump_r.reset();
        }

        if ( chJump.isLastFrame() && (visible_r == false)) {
            visible = true;
            ch.setVisible(visible);
        }
        else if ( chJump_r.isLastFrame() ) {
            visible_r = true;
            ch_r.setVisible(visible_r);
        }

    }

    @Override
    public void release(){
        ch.release();
        ch_r.release();
        chRun.release();
        chRun_r.release();
        chAttack.release();
        chAttack_r.release();
        chDefend.release();
        chDefend_r.release();
        chJump.release();
        chJump_r.release();

        ch = null;
        ch_r = null;
        chRun = null;
        chRun_r = null;
        chAttack = null;
        chAttack_r = null;
        chDefend = null;
        chDefend_r = null;
        chJump = null;
        chJump_r = null;
    }
}
