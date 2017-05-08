package Stuff;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Alex on 02.05.2017.
 */
public class FightSimAnimated extends Frame implements ActionListener,WindowListener {
    private String name;
    private int[] bab;
    private int dmg;
    private int[] roll;
    private int critRange =20;
    private Button b;
    private JTextField nameIn = new HintTextField("Input your name");
    private JTextField babIn = new HintTextField("Input your base attack bonus");
    private JTextField dmgIn = new HintTextField("Input your overall damage bonus");
    private JTextField rollIn = new HintTextField("Input your dice to roll for one attack");
    private JTextField critIn = new HintTextField("Input the start of your critrange");
    private JTextArea log = new JTextArea(200,300);


    public FightSimAnimated(){
        nameIn.addActionListener(this);
        nameIn.setToolTipText("Input the name of your character");
        babIn.addActionListener(this);
        babIn.setToolTipText("Input your bab like this : 10 10 10");
        dmgIn.addActionListener(this);
        rollIn.addActionListener(this);
        rollIn.setToolTipText("If you attack with 2d4s and 1d6 type it like this: 4 4 6");
        critIn.addActionListener(this);
        critIn.setToolTipText("If you have a critrange of 15-20 type 15");
        log.setBackground(Color.LIGHT_GRAY);
        add(nameIn);
        add(babIn);
        add(dmgIn);
        add(rollIn);
        add(log);
        setLayout(new FlowLayout());
        addWindowListener(this);
        b = new Button("Fight");
        add(b);
        b.addActionListener(this);

    }
    public void addBuff(int attackBonus,int dmg){
        this.dmg=this.dmg+dmg;
        for(int i = 0;i<bab.length;i++){
            this.bab[i]=this.bab[i]+attackBonus;
        }
    }
    public void fight(int armorclass){
        int total=0;
        for(int i =0;i<bab.length;i++){
            int d20  = ThreadLocalRandom.current().nextInt(1, 20 + 1);
            if(d20+bab[i]>=armorclass){
                int erg =0;
                for(int c =0;c< roll.length;c++) {
                    erg += ThreadLocalRandom.current().nextInt(1, roll[c] + 1);
                    if (d20 >= critRange) {
                        erg += ThreadLocalRandom.current().nextInt(1, roll[c] + 1);
                    }
                }
                if(d20>= critRange){
                    erg+=dmg*2;
                    total+=erg;
                    log.append(name+" rolled a "+d20 +"("+(d20+bab[i])+")(crit) and hit with the "+(i+1)+".Attack for "+erg+" dmg.");
                }else{
                    erg+=dmg;
                    total+=erg;
                    log.append(name+" rolled a "+d20 +"("+(d20+bab[i])+")and hit with the "+(i+1)+".Attack for "+erg+" dmg.");
                }

            }
        }
        log.append(name+" did a total of "+total+" dmg.");

    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()== nameIn){
            this.name= nameIn.getText();
        }
        if(e.getSource()== babIn){
            this.bab =Toolbar.createArrayDirectFrom(babIn.getText());
        }
        if(e.getSource()== dmgIn){
            this.dmg = Integer.parseInt(dmgIn.getText());
        }
        if(e.getSource()== rollIn){
            this.roll =Toolbar.createArrayDirectFrom(rollIn.getText());
        }
        if(e.getSource()== critIn){
            this.critRange = Integer.parseInt(critIn.getText());
        }
        if(e.getSource()== b){
            fight(20);
        }
    }

    public void windowClosing(WindowEvent e) {
        dispose();
        System.exit(0);
    }
    public void windowOpened(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}
}

