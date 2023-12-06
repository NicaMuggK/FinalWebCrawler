module com.example.finalwebcrawler {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jsoup;


    opens com.example.finalwebcrawler to javafx.fxml;
    exports com.example.finalwebcrawler;
}