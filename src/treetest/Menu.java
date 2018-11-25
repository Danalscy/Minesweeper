package treetest;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dan
 */
public class Menu {
    /*private Matrix a;
    private Matrix b;
    public Matrix getA(){
        return this.a;
    }
    public Matrix getB(){
        return this.b;
    }
    public Menu(String pathA, String pathB) {
        Matrix a1=createMatrix(pathA);
        Matrix b1=createMatrix(pathB);
        this.a=a1;
        this.b=b1;
    }
     
    public Matrix createMatrix(String path){
        File file=new File(path);
        try {
            Scanner sc = new Scanner(file);
            int row= sc.nextInt();
            int column=sc.nextInt();
            Matrix A = new Matrix(row,column);
            while (sc.hasNextLine()) {
                int i = sc.nextInt();
                int j = sc.nextInt();
                double val = sc.nextDouble();
             A.insert(i,j,val);
            }
            sc.close();
            return A;
        } 
        catch (FileNotFoundException e) {
           e.printStackTrace();
        }
        return null;
    }
 
    public int max(int x, int y){
        if(x>y)
            return x;
        else 
            return y;
    }
  
    public Matrix addition(){
       int n = max(a.getRow(),b.getRow());
       int m = max(a.getColumn(),b.getColumn());
       Matrix c=new Matrix(n,m);
       Set<Integer> arrayIda = new HashSet<Integer>();
       Set<Integer> arrayIdb = new HashSet<Integer>();
       int count=0;
       for(int i=0;i<n;i++){
            if (a.getRow() > i) {
                   arrayIda= a.getTabela()[i].getIndices() ;                  
               }
            if (b.getRow() > i) {
                   arrayIdb=b.getTabela()[i].getIndices();                   
              }
            for(Integer num : arrayIdb){
                if(!arrayIda.contains(num)) 
                    arrayIda.add(num);
            }

            for(Integer j : arrayIda){
                double wynik=0;
                count++;
                if (a.getColumn() > j && a.getRow() > i) {
                    wynik+=(double)a.getTabela()[i].Pobierz(j);

                }
                if (b.getColumn() > j && b.getRow() > i ) {
                    wynik+=(double)b.getTabela()[i].Pobierz(j);
                     
                }
                if(wynik!=0)
                    c.insert(i, j, wynik);
              }
              arrayIda.clear();
              arrayIdb.clear();
       }
       System.out.println("Ilosc sprawdzen w dodawaniu: "+count);
       return c;
    }
    public Matrix multiply() throws IllegalArgumentException{
       int aC = a.getColumn();
       int bR = b.getRow();
        if (aC != bR) {
            throw new IllegalArgumentException("Niepoprawne wymiary macierz");
        }
        int aR=a.getRow();
        int bC=b.getColumn();
        int count=0;
        Matrix c=new Matrix(aR,bC);
        Set<Integer> arrayId = new HashSet<Integer>();
            for(int i=0;i<aR;i++){
                arrayId=a.getTabela()[i].getIndices();
                for(int k : arrayId){                                   
                    double x=(double)a.getTabela()[i].Pobierz(k);                  
                        for(int j=0;j<bC;j++){
                            double y=(double)b.getTabela()[k].Pobierz(j);
                            if(y!=0){
                                c.insert(i,j, 0);
                                c.getTabela()[i].PobierzNode(j).getItem().add((x*y));
                                count++;
                            }
                        }                   
                }                   
            }
          System.out.println("Ilosc sprawdzen w mnożeniu: "+count);   
        return c;
    }*/
}
