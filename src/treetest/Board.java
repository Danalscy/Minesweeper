package treetest;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dan
 */
public class Board extends JFrame {
    private Matrix minefield; // macierz, która przechowuje wartości, 
    //które odzwierciedlają ilość sąsiadujących min z danym polem.
    private Matrix visited; // macierz, która przechowuje informacje, 
    //które pola zostały odwiedzone (bomb nie uwzględniamy) 
    private Matrix flagged; // macierz, która przechowuje informacje, 
    //które pola są oflagowane (podejrzane o bycie bombą)
    private Matrix button; // macierz która przechowuje przyciski
    //(żeby używać interfejsu graficznego)
  
    // wektory służące do przeszukiwania sąsiadów
    private Vector r;  //private int[] r = {-1, -1, -1, 0, 0, 1, 1, 1};
    private Vector c;  //private int[] c = {-1, 0, 1, -1, 1, -1, 0, 1};  
    private Vector icon; // wektor, który przechowuje ikony 
    
    private int numMine; //ilość min
    private int flaggedMine; // ilość pól do odflagowania 
    private int row; // liczba szeregów
    private int col; // liczba kolumn
    private int x,y; // współrzędne pierwszego klikniętego pola (do generacji min)
    
    private JButton reset; // przycisk resetu
    private JPanel bar; // pole, na którym znajduje się reset
    private JTextField mineDisplay; // pole tekstowe pokazujące ilość min do odflagowania
    private JPanel pole; // pole, na którym znajdują się przyciski gry 

    private MouseAction ma; // listener nasłuchujący przyciski do sapera
    private boolean isNew; // sprawdzenie czy zaczęta jest nowa gra
    //(czy zostało wybrane pierwsze pole) 

    public Board(int row, int col, int b){
        setTitle("Saper");
        this.row=row;
        this.col=col;
        this.reset = new JButton();
        this.numMine = b;
        this.pole = new JPanel();   
        this.mineDisplay=new JTextField();
        this.icon= new Vector();
        this.isNew = true;
        this.r=new Vector();
        this.c=new Vector();               
        setVectors();
        setField();
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                isNew=true;
                setField(); 
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
   
    }
    public void setVectors() {       
        String path;
        for (int i = 0; i <= 9; i++) {
            path = i + ".gif";
            ImageIcon tmp = new ImageIcon(path);            
            Image image = tmp.getImage(); 
            Image newimg = image.getScaledInstance(15, 15,  java.awt.Image.SCALE_SMOOTH); //ustawienie wielkości ikon
            if(i== 0) newimg = image.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH); //ikona "0.gif" nieco większa
            icon.Przypisz(i, new ImageIcon(newimg));  //przypisanie ikon do wektora
        }     
       
        //private int[] r = {-1, -1, -1, 0, 0, 1, 1, 1};
        //private int[] c = {-1, 0, 1, -1, 1, -1, 0, 1};  
        r.Przypisz(0,-1);
        r.Przypisz(1,-1);
        r.Przypisz(2,-1);
        r.Przypisz(3, 0);
        r.Przypisz(4, 0);
        r.Przypisz(5, 1);
        r.Przypisz(6, 1);
        r.Przypisz(7, 1);
        
        c.Przypisz(0,-1);
        c.Przypisz(1,0);
        c.Przypisz(2,1);
        c.Przypisz(3, -1);
        c.Przypisz(4, 1);
        c.Przypisz(5, -1);
        c.Przypisz(6, 0);
        c.Przypisz(7, 1);
        ImageIcon mainIc = (ImageIcon)icon.Pobierz(0); 
        setIconImage(mainIc.getImage()); // ustawienie głównej ikony programu
    }
    
    public void setField(){
        //tworzymy nowe instancje
        this.bar = new JPanel() ; 
        this.ma= new MouseAction();
        this.minefield = new Matrix(row,col);
        this.visited = new Matrix(row,col);
        this.flagged = new Matrix(row,col);
        this.button = new Matrix(row,col);
        //kasujemy wszystko z okna i pola
        getContentPane().removeAll();
        pole.removeAll();      
        button.reset();
        minefield.reset();
        visited.reset();
        flagged.reset(); //wszystkie macierze resetujemy
        setSize(220, 320);
        setResizable(false);
        //kosmetyczne dodatki
        pole.setLayout(new GridLayout(0, col));
        pole.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        bar.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        bar.add(reset); //dodanie przycisku reset do panelu bar 
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) { 
                JButton tmp = new JButton();
                tmp.addMouseListener(ma);
                 // dodanie do macierzy "button" w miejsce (i,j) nowego przycisku  
                button.insert(i, j, tmp); 
                // dodanie do panelu tego przycisku (pobranego już z macierzy 
                pole.add((Component) button.pobierz(i,j));
            }
        }
       
        pole.revalidate();
        pole.repaint(); //funkcje, które służą do odświeżenia panelu
        changeIcon(reset,0); // funkcja zmieniające ikony przycisków, opisanie później
        
        flaggedMine = numMine;  //początkowo ilośc min do oflagowania jest równa ilości min     
        mineDisplay = new JTextField("Mines Left: " + flaggedMine);
        mineDisplay.setEditable(false); // aby nie można edytować pola tekstowego
        mineDisplay.setFont(new Font("Helvetica.TTF", Font.BOLD, 15)); //zmieniony rodzaj czcionki
             
        //dodajemy obiekty w poszczególne miejsca w oknie
        getContentPane().add(pole, BorderLayout.CENTER);    
        getContentPane().add(bar, BorderLayout.NORTH);    
        getContentPane().add(mineDisplay, BorderLayout.PAGE_END); 
        setVisible(true);
    }
    
    public void changeIcon (JButton b, int i){
        ImageIcon ikonka=(ImageIcon)icon.Pobierz(i);
        b.setIcon(ikonka);
    }
    
    class MouseAction extends MouseAdapter{    
        @Override
        public void mouseClicked(MouseEvent e) {        
            if (isNew == true) { //jeżeli jest nowa gra (nie wybrano jeszcze żadnego pola)
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) {
                        if (e.getSource().equals(button.pobierz(i,j))) {
                            x = i;
                            y = j;
                            i = row;
                            break;
                        }
                    }
                }
                setMine(); //generowanie min
                calculate(); // obliczenie ile jest sąsiadujących min do każdego pola
                isNew = false;
                //print();              
            }     
            play(e);
            winner(); // za każdym kliknięciem sprawdzamy czy jest koniec gry         
        }       
    }
    
    public void setMine(){           
        //tworzymy tymczasową macierz, która przechowuje do tej pory wygenerowane miny
        Matrix generated = new Matrix(row,col); 
        Random rand = new Random(); // generator pseudolosowy   
        
        generated.insert(x, y, 1); // kliknęliśmy to pole, więc tu nie może być już mina
        visited.insert(x,y,1); // odwiedzamy nasze pierwsze wybrane pole
         
        for (int i = 0; i < numMine; i++) {
            //losowe wartości kolumn i wierszy
            int rowr = rand.nextInt(this.row);
            int colr = rand.nextInt(this.col);
            if (generated.pobierz(rowr,colr).equals(0)) { // jeżeli nie wygenerowaliśmy tutaj miny
                minefield.insert(rowr,colr,-1); // wstawiamy minę na wybrane pole
                visited.insert(rowr,colr,1); // miny uznajemy za "odwiedzone" 
                // przydatne do algorytmu szukania sąsiadów
                
                generated.insert(rowr, colr, 1); // w tym miejscu wygenerowaliśmy już minę                            
            } else {
                i--; // powtarzamy
            }
        }
    }
    public void calculate(){
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.col; j++) {
                int value = 0; // licznik sąsiadujących min
                int R, C;
                if (!minefield.pobierz(i,j).equals(-1)) {
                    for (int k = 0; k < 8; k++) {
                        R = i + (int)r.Pobierz(k);
                        C = j + (int)c.Pobierz(k);
                        if (R >= 0 && C >= 0 && R < this.row && C < this.row) {
                            if (minefield.pobierz(R,C).equals(-1)) {
                                value++; // znaleźliśmy minę-sąsiada
                            }
                        }                          
                    }
                    minefield.insert(i,j,value);
                }
            }
        }
    }
    
    public void play(MouseEvent e){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(e.getSource().equals(button.pobierz(i,j))){ // jeżeli źródłem eventu jest przycisk
                    JButton tmp = (JButton)button.pobierz(i,j);
                    if (SwingUtilities.isRightMouseButton(e)) { // jeżeli PPM                        
                        if(flagged.pobierz(i,j).equals(0)){ // jeżeli pole jest "puste"
                            if(flaggedMine != 0){  
                                changeIcon(tmp,9); // zamieniamy ikonę na flagę                             
                                flagged.insert(i,j,1); // pole oflagowane
                                flaggedMine--; // counter flag do odflagowania zmniejszamy
                            }
                        }                    
                        else{ // jeżeli oflagowane           
                            if(flaggedMine < numMine){    
                                tmp.setIcon(null); //kasujemy ikonę   
                                flagged.remove(i,j); //pole nie jest już oflagowane
                                flaggedMine++; // zwiększamy counter
                            }
                        }                               
                        mineDisplay.setText("Mines Left: " + flaggedMine);// zmieniamy tekst    
                    }
                    else{ // jeżeli LPM
                        if(flagged.pobierz(i,j).equals(0)){ // jeżeli pole nie jest oflagowane
                            tmp.removeMouseListener(ma); // usuwamy listener
                            tmp.setBackground(Color.GRAY); // zmieniamy background dla kosmetyki
                            int item=(int)minefield.pobierz(i,j);  
                            if(item == 0 ) 
                                search(i,j); // w przypadku pustego pola wywołujemy funckję search()
                            else if(item!=-1){ // jeżeli pole nie jest miną
                                changeIcon(tmp,item); // zmieniamy ikonę
                                visited.insert(i,j,1); // odwiedzamy
                            }           
                            else{ // natrafiliśmy na minę
                                loser();
                                tmp.setBackground(Color.RED);
                                end("PRZEGRANA");
                            }
                        }
                    }
                }
            }       
        }
    }
    public void winner(){
        boolean isOver = true;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (visited.pobierz(i,j).equals(0)) {
                    isOver = false;
                    break;
                }
            }
        }
        if (isOver) {
            end("BRAWO");          
        }
    }
    
    public void loser(){
        for (int i = 0; i< row; i++){
            for(int j = 0; j < col; j++){              
                if(minefield.pobierz(i,j).equals(-1)){
                    JButton tmp = (JButton)button.pobierz(i,j);
                    tmp.setBackground(Color.GRAY);
                    changeIcon(tmp,0);
                }
            }
        }
    }
    
    public void end(String napis){
         for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    JButton tmp = (JButton)button.pobierz(i,j);
                    tmp.removeMouseListener(ma);
                }
            }
         JOptionPane.showMessageDialog(this, napis);
    }
    
   
    public void search(int row, int col){
        // row, col - współrzędne naszego pustego pola
        visited.insert(row,col,1); // odwiedzamy puste pole
        JButton tmp = (JButton)button.pobierz(row,col);
        tmp.setBackground(Color.GRAY); // zmieniamy kolor odwiedzonego pola
        tmp.removeMouseListener(ma); // usuwamy listener
        for (int i = 0; i < 8; i++) {
            //współrzędne sąsiadów naszego pustego pola
            int R = row + (int)r.Pobierz(i); 
            int C = col + (int)c.Pobierz(i);  
            
            // dla każdego nieodwiedzonego i nieoflagowanego sąsiada 
            if (R >= 0 && C >= 0 && R < this.row && C < this.row && visited.pobierz(R,C).equals(0) && flagged.pobierz(R,C).equals(0)){
                JButton tmp1 = (JButton)button.pobierz(R,C);
                tmp1.setBackground(Color.GRAY);
                if (minefield.pobierz(R,C).equals(0)) { // jeżeli ów sąsiad jest też pusty
                    search(R, C); // szukamy dalej 
                } else { // sąsiad niepusty
                    changeIcon(tmp1,(int)minefield.pobierz(R,C)); //zmieniamy ikonę
                    tmp1.removeMouseListener(ma); // usuwamy listener
                    visited.insert(R,C,1); // odwiedzamy
                }
            }
        }
    }
    
    public void print(){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                 JButton tmp = (JButton)button.pobierz(i,j);
                 int item=(int)minefield.pobierz(i,j);
                 if(item == 0 );
                 else
                 if(item!=-1)
                     changeIcon(tmp,item);
                 else
                     changeIcon(tmp,0);
            }
        }
        minefield.show();
    }
           
}
