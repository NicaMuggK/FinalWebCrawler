package com.example.finalwebcrawler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class FXController {
    @FXML
    private TextField urlBox1;
    @FXML
    private TextField urlBox2;
    @FXML
    private TextField urlBox3;
    @FXML
    private AnchorPane inputPane;
    @FXML
    private TextFlow resultsTextFlow1;
    @FXML
    private TextFlow resultsTextFlow2;
    @FXML
    private TextFlow resultsTextFlow3;
    @FXML AnchorPane resultsPane;

    private String[] urls = new String[3];
    private ArrayList<String> visited1;
    private ArrayList<String> visited2;
    private ArrayList<String> visited3;

    @FXML
    protected void OnStartCrawlClick(){
        if(urlBox1.getText()!="" && urlBox2.getText() != "" && urlBox3.getText() != ""){
            urls[0] = urlBox1.getText();
            urls[1] = urlBox2.getText();
            urls[2] = urlBox3.getText();
            inputPane.setVisible(false);
            resultsPane.setVisible(true);
            ShowCrawlResults();
        }
    }

    private void ShowCrawlResults(){
        int urlNumber = 1;
        for(String s:urls) {
            switch (urlNumber) {
                case 1:
                    this.visited1 = new ArrayList<String>();
                    Crawler crawl1 = new Crawler(s, visited1, resultsTextFlow1);
                    crawl1.Crawl(1, s);
                    break;
                case 2:
                    this.visited2 = new ArrayList<String>();
                    Crawler crawl2 = new Crawler(s, visited2, resultsTextFlow2);
                    crawl2.Crawl(1,s);
                    break;
                case 3:
                    this.visited3 = new ArrayList<String>();
                    Crawler crawl3 = new Crawler(s, visited3, resultsTextFlow3);
                    crawl3.Crawl(1,s);
                    break;
            }
            urlNumber++;
        }
        categorizeAndStoreURLsInDatabase();
    }

    private void categorizeAndStoreURLsInDatabase(){
        DatabaseController dbCont = new DatabaseController(visited1, 1, urls[0]);
    }

}
