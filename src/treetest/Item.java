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
public class Item {
    private Object value; 
    private int index;
    public Item (int i, Object v){
        this.value=v;
        this.index=i;
    }
    public int getId(){
        return this.index;
    }
    public Object getVal(){
        return this.value;
    }
     public void setId(int key){
      this.index=key;
    }
     public void add(double x){
         double c=(double)value+ (double)x;
         setVal(c);
     }
     public void setVal(Object x){
         this.value=x;
     }
}
