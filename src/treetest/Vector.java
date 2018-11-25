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
public class Vector {
    private Tree drzewo;
    public Vector (){
        this.drzewo = new Tree();
    }
    public void Przypisz(int i, Object v){
        Node tmp = new Node(i,v);
        if(drzewo.getRoot() == null) {
            drzewo.setRoot(tmp);
        } 
        else {
           drzewo.setRoot(drzewo.Insert(drzewo.getRoot(),tmp));
        }          
    }
    public Tree getDrzewo(){
        return this.drzewo;
    }
    public Object Pobierz(int i){
        Node n = drzewo.Get(i,drzewo.getRoot());
        if(n == null) return 0;
        return n.getVal();   
    }
     public Node PobierzNode(int i){
        Node n = drzewo.Get(i,drzewo.getRoot());
        return n;   
    }
    public void dump(){
        if(drzewo.getRoot()!=null)
        drzewo.dump(drzewo.getRoot(),0);
    }
    public void remove(int key){
         drzewo.setRoot(drzewo.removeRec(drzewo.getRoot(), key));
    }
    public Set<Integer> getIndices(){
        Set<Integer> tablica = new HashSet<Integer>();
        if(drzewo.getRoot()!= null)
            drzewo.getIndicesRec(drzewo.getRoot(),tablica);
             
        return tablica;
    }
}
