package Stuff;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Alex on 02.05.2017.
 */
public class Fightsim {
    private String name;
    private int[] bab;// All Attackbonuses in order
    private int dmg;
    public int[] roll;//which dice to roll for 2d4 write 4 two times
    private int critrange=20;

    public Fightsim(int[] bab,int dmg,int[] roll,int critrange,String name){
        this.bab=bab;
        this.dmg=dmg;
        this.roll=roll;
        this.critrange=critrange;
        this.name=name;
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
                    if (d20 >= critrange) {
                        erg += ThreadLocalRandom.current().nextInt(1, roll[c] + 1);
                    }
                }
                if(d20>=critrange){
                    erg+=dmg*2;
                    total+=erg;
                    System.out.println(name+" rolled a "+d20 +"("+(d20+bab[i])+")(crit) and hit with the "+(i+1)+".Attack for "+erg+" dmg.");
                }else{
                    erg+=dmg;
                    total+=erg;
                    System.out.println(name+" rolled a "+d20 +"("+(d20+bab[i])+")and hit with the "+(i+1)+".Attack for "+erg+" dmg.");
                }

            }
        }
        System.out.println(name+" did a total of "+total+" dmg.");

    }
}

