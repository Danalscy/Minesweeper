package treetest;

import java.util.HashSet;
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
public class Matrix {
    private int row;
    private int column;
    private Vector[] tabela;
    
    public Matrix(int n, int m){
        this.row=n;
        this.column=m;
        tabela=new Vector[n];
        for(int a=0;a<this.row;a++){
            tabela[a]=new Vector();
        }
    }   
    public void insert(int n, int m, Object v){
        tabela[n].Przypisz(m,v);
    }
    public void dump(){
         for(int i=0;i<tabela.length;i++){
             if(tabela[i].getDrzewo().getRoot()!=null)
             System.out.println("Wiersz " +i);
             tabela[i].dump();
         }
          System.out.println();
    }
    public void show(){
        for(int i=0;i<this.row;i++){
            for(int j=0;j<this.column;j++){
               System.out.print((int)tabela[i].Pobierz(j) + "  "); 
            }          
             System.out.println("\n"); 
        }
       // System.out.println("lub tylko niepuste elementy:");
        //dump();
    }
    public Matrix transpose() {
        Matrix A = new Matrix(getColumn(), getRow());
        Set<Integer> arrayId = new HashSet<Integer>();
        for (int i = 0; i < getRow(); i++){    
            arrayId= getTabela()[i].getIndices();             
            for (int j : arrayId){
                A.insert(j, i, tabela[i].Pobierz(j));
            }   
        }    
        return A;
    }
    public int getRow(){
        return this.row;
    }
    public int getColumn(){
        return this.column;
    }
    public Vector[] getTabela(){
        return this.tabela;
    }
    public Object pobierz(int i,int j){
        return tabela[i].Pobierz(j);
    }
    public void remove(int i,int j){
        tabela[i].remove(j);
    }
    public void reset(){
        tabela=new Vector[this.row];
        for(int a=0;a<this.row;a++){
            tabela[a]=new Vector();
        }
    }

}
