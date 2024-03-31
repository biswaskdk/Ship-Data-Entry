/*
 * Review of build new object by Aggregation to be used in Lab2
 *     This should be used as the super classe for the subClasses
 * author: C.Anderson
 * modified on 5-30-2021
 */

import java.io.*;
import java.util.*;
import javax.swing.*;

class Ship
{
    private String name;      // Ship name
    private String nation;    // Nation of registry
    private int yearBuilt;    // 4 digit year built 
    private int length;       // Length of ship at mean draft
    private int draft;        // average depth below sea level
    private int beam;         // side to side width at mean draft
   
    public Ship(String nam,  String nat,  int yer, int len, int dra, int bem)
    {
        name = nam;
        yearBuilt = yer;
        nation = nat;
        length = len;
        draft = dra;
        beam = bem;
    }
    
    public int calculateDisplacment()
    {
        return getLength()*getBeam()*getDraft();
    }
   
    public void setName(String n)
    {
        name = n;
    }

    public void setNation(String na)
    {
       nation = na;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getYearBuilt()
    {
        return yearBuilt;
    }
   
    public String getNation()
    {
        return nation;
    }        

    public String toString()
    {
        return getName() + "," +
             getNation() + "," +
             getYearBuilt()+"," +
             getLength() +"," +getBeam()+"," +getDraft();
    }

    public int getLength() {
        return length;
    }

    public int getDraft() {
        return draft;
    }

    public int getBeam() {
        return beam;
    }

    public void setYearBuilt(int yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setDraft(int draft) {
        this.draft = draft;
    }

    public void setBeam(int beam) {
        this.beam = beam;
    }
}
