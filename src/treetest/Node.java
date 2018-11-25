package treetest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nguyend1
 */
public class Node {
    private Item item;
    private Node left;
    private Node right;
   
    public Node (int i,Object v){
        this.item=new Item(i,v);
        this.left=null;
        this.right=null;
    }
    public void setLeft(Node n){
        this.left=n;
    }
    public void setRight(Node n){
        this.right=n;
    }
    public Item getItem(){
        return this.item;
    }
    public Node getLeft(){
        return this.left;
    }
    public Node getRight(){
        return this.right;
    }
    public Object getVal(){
        return item.getVal();
    }
    public int getId(){
        return item.getId();
    }
}
