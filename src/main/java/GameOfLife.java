import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameOfLife extends JFrame {
    int size = 50;
    boolean[][] cellsMap;
    JButton[][] cells;

    public GameOfLife(){
        Random rnd = new Random();

        cellsMap = new boolean[size][size];
        cells = new JButton[size][size];
        setSize(500,500);
        setLayout(new GridLayout(size,size));

        for(int i = 0; i<size;i++){
            for(int j = 0; j<50;j++){
                cellsMap[i][j] = rnd.nextInt(100)<50;
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
            @Override
            public void actionPerformed(ActionEvent e){
                boolean[][] temp = new boolean[size][size];
                for(int i = 0; i<size; i++){
                    for (int j = 0;j<50;j++){
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
                for(int i = 0; i<size; i++) {
                    for (int j = 0; j < 50; j++) {
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

    public static void main(String[] args){
        new GameOfLife();
    }
}
