package Stuff;

/**
 * Created by Alex on 16.04.2017.
 */
public class chestgame {
    public static void main(String[] args) {
        int capital = 500;
        playrandom(3000, 250);
        playlocked(3000, 250);
    }

    public static void playrandom (int capital,int anzahl){
        System.out.print("Play with random answer for "+anzahl+ " rounds with "+capital+" Rupees"+'\n');
        for (int i =0 ;i < anzahl; i++){
            if (capital <= 0){
                break;
            }
            capital=capital - 100;
            int randomguess = 1 + (int)(Math.random() * ((3 - 1) + 1));
            int randomhit = 1 + (int)(Math.random() * ((3 - 1) + 1));
            if (randomguess == randomhit){
                capital+=300;
            }
            System.out.print("Round : "+(i+1)+" Rupees : "+capital+ "        Guess was : "+randomguess+ " Rupees were in : "+randomhit+'\n');

        }
    }
    public static void playlocked (int capital,int anzahl){
        System.out.print("Play with one answer for "+anzahl+ " rounds with "+capital+" Rupees"+'\n');
        for (int i =0 ;i < anzahl; i++){
            if (capital <= 0){
                break;
            }
            capital=capital - 100;
            int randomguess = 1;
            int randomhit = 1 + (int)(Math.random() * ((3 - 1) + 1));
            if (randomguess == randomhit){
                capital+=300;
            }
            System.out.print("Round : "+(i+1)+" Rupees : "+capital+ "        Guess was : "+randomguess+ " Rupees were in : "+randomhit+'\n');

        }
    }
}
