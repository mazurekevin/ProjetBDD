import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;

public class GameOfLife extends JFrame {
    boolean[][] cellsMap;
    JButton[][] cells;

    //Setup la taille de la map avec swing
    public GameOfLife(int taille){
        Random rnd = new Random();

        cellsMap = new boolean[taille][taille];
        cells = new JButton[taille][taille];
        setSize(500,500);
        setLayout(new GridLayout(taille,taille));
        //Défini aléatoirement les cellules vivantes et morte
        for(int i = 0; i<taille;i++){
            for(int j = 0; j<taille;j++){
                cellsMap[i][j] = rnd.nextInt(100)<taille;
                JButton temp = new JButton();
                if(cellsMap[i][j]){
                    temp.setBackground(Color.GREEN);
                }else{
                    temp.setBackground(Color.WHITE);
                }
                add(temp);
                cells[i][j] = temp;
            }
        }

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Timer timer = new Timer(100, new ActionListener() {
            //Applique le scénario en fonction du nombre de voisin vivant
            @Override
            public void actionPerformed(ActionEvent e){
                boolean[][] temp = new boolean[taille][taille];
                for(int i = 0; i<taille; i++){
                    for (int j = 0;j<taille;j++){
                        int count = countNeigbours(i,j);
                        if(cellsMap[i][j]){
                            if(count<2)
                                temp[i][j] = false;

                            if(count==3||count==2)
                                temp[i][j] = true;

                            if(count>3)
                                temp[i][j] = false;

                        }else{
                            if(count==3)
                                temp[i][j] = true;
                        }
                    }
                }
                cellsMap = temp;
                //Change la couleur de la cellule
                for(int i = 0; i<taille; i++) {
                    for (int j = 0; j < taille; j++) {
                        if(cellsMap[i][j]){
                            cells[i][j].setBackground(new Color(rnd.nextInt(255),rnd.nextInt(255),rnd.nextInt(255)));
                        }else{
                            cells[i][j].setBackground(Color.WHITE);
                        }
                    }
                }
            }

        });

        timer.start();
    }

    //Permet de compter le nombre de voisin vivant autour de la cellule
    int countNeigbours(int x, int y){
        int count = 0;

        for(int i = x-1;i<=x+1;i++){
            for(int j = y-1;j<=y+1;j++){
                try{
                    if(cellsMap[i][j]){
                        count++;
                    }
                }catch(Exception ignored){

                }
            }
        }
        if(cellsMap[x][y]){
            count--;
        }
        return count;
    }

    //Début du programme et demande la taille de la map
    public static void main(String[] args){
        int taille = 50;
        boolean checked = false;
        System.out.println("Sélectionner la taille de votre map entre 15 et 75.");
        while(!checked){
            System.out.println("Taille :");
            Scanner scan = new Scanner(System.in);
            taille = scan.nextInt();
            checked = checkTaille(taille);
        }

        new GameOfLife(taille);
    }
    // Vérifie la taille de la map
    //En-dessous de 15, les cellules meurent tous de surpopulation
    //Au-dessus de 75, les cellules meurent tous de souspopulation
    private static boolean checkTaille(int taille) {
        if (taille>=15 && taille<=75){
            return true;
        }
        System.out.println("Mettre une taille entre 15 et 75.");
        return false;
    }
}
