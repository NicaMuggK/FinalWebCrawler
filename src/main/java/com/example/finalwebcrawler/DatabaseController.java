package com.example.finalwebcrawler;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DatabaseController {
    private ArrayList<String> Internal;
    private ArrayList<String> External;
    private int URLIndex;
    private String url;

    public DatabaseController(ArrayList arrList, int index, String URL){
        this.Internal = new ArrayList<>();
        this.External = new ArrayList<>();
        this.URLIndex = index;
        this.url = URL;
        CategorizeLists(arrList);
    }

    private void CategorizeLists(ArrayList<String> arrList){
        Internal.clear();
        External.clear();
        Iterator i = arrList.iterator();
        Regex reg = new Regex(url);
        while(i.hasNext()) {
            try {
                boolean isInternal = reg.isSameDomain(i.next().toString());
                if (isInternal) {
                    Internal.add(i.next().toString());
                } else {
                    External.add(i.next().toString());
                }
            }catch (NoSuchElementException e){
                System.out.println(e.getMessage());
            }
        }
        print();
    }

    public void setURLIndex(int URLIndex) {
        this.URLIndex = URLIndex;
    }

    public void setDomain(String url){
        try {
            this.url = new URI(url).getHost();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public void print(){
        System.out.println("INTERNAL ::::::");
        Iterator i = Internal.iterator();
        while(i.hasNext()){
            System.out.println(i.next());
        }
        System.out.println("EXTERNAL :::::::");
        i = External.iterator();
        while(i.hasNext()){
            System.out.println(i.next());
        }
    }
}
