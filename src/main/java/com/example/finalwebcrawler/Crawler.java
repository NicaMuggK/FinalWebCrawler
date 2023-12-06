package com.example.finalwebcrawler;

import javafx.scene.control.TableColumn;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;


public class Crawler {

    private ArrayList<String> visited;
    private TextFlow tflow;



    public Crawler (String url, ArrayList<String> visited, TextFlow tflow){
        this.visited = visited;
        this.tflow = tflow;
    }

    public void Crawl(int level, String url){
        if(level <= 1) {
            Document doc = request(url);
            if(doc != null){
                for(Element link : doc.select("a[href]")) {
                    String next_link = link.absUrl("href");
                    if(visited.contains(next_link) == false) {
                        Crawl(level++, next_link);
                    }
                    visited.add(next_link);
                }
            }
        }
    }

    private Document request (String url){
        try{
            Connection con = Jsoup.connect(url);
            Document doc = con.get();

            if(con.response().statusCode() == 200){
                Text text = new Text(url +"\n");
                tflow.getChildren().add(text);
                visited.add(url);
                return doc;
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return null;
    }

    public ArrayList<String> getVisited (){
        return visited;
    }
}
