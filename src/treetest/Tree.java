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
 * @author nguyend1
 */
public class Tree {    
    private Node root;
    
    public Tree(){
        this.root=null;
    }
    public Node getRoot(){
        return this.root;
    }
     public void setRoot(Node n){
        this.root=n;
    }
    public Node Insert(Node current, Node n){
        if(current == null) {
            return n;
        } 
        else if(n.getId() > current.getId()){
            current.setRight(Insert(current.getRight(), n));
        } 
        else if(n.getId() < current.getId()){
            current.setLeft(Insert(current.getLeft(), n));
        }
        return current;
    }    
    public void dump(Node n,int i){
         if(n.getLeft() != null)
            dump(n.getLeft(),i+1);
         System.out.println("Id: "+ n.getId()+ " Value: " +n.getVal() + " Depth: "+ i );
         if(n.getRight() != null)
            dump(n.getRight(),i+1);
    }
    
    public Node Get(int i, Node n){
        if(n != null){
            if(n.getId() == i){
                return n;
            }else{
                Node tmp = Get(i, n.getLeft());
                if(tmp != null){
                    return tmp;
                }
                return Get(i, n.getRight());
            }
        } else{
            return null;
        }
    }    
    public Node removeRec(Node n, int key){       
        if (n == null)  return n;     
        if (key < n.getId())
            n.setLeft(removeRec(n.getLeft(), key));
        else if (key > n.getId())
            n.setRight(removeRec(n.getRight(), key)); 
        else
        {           
            if (n.getLeft() == null)
                return n.getRight();
            else if (n.getRight() == null)
                return n.getLeft();           
            n.getItem().setId(minValue(n.getRight()));
            n.setRight(removeRec(n.getRight(), n.getId()));
        } 
        return n;
    }
 
    public int minValue(Node n){
        int minv = n.getId();
        while (n.getLeft() != null)
        {
            minv = n.getLeft().getId();
            n = n.getLeft();
        }
        return minv;
    }

    public void getIndicesRec(Node n, Set x){
         if(n!= null)
         x.add(n.getId());
         if(n.getLeft() != null){    
             getIndicesRec(n.getLeft(),x);
         }        
         if(n.getRight() != null){
             getIndicesRec(n.getRight(),x);
         }
    }
}