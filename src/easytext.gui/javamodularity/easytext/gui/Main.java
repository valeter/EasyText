package javamodularity.easytext.gui;

import javafx.application.Application;
import javafx.scene.text.Text;
import javafx.scene.control.TextArea;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.Insets;

import java.util.stream.Collectors;
import java.util.Map;

import javamodularity.easytext.analysis.api.TextAnalyzer;


public class Main extends Application {
    private static ComboBox<String> algorithm;
    private static TextArea input;
    private static Text output;

    public static void main(String... args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("EasyText");
        Button btn = new Button();
        btn.setText("Calculate");
        btn.setOnAction(event ->
            output.setText(analyze(input.getText(), (String) algorithm.getValue()))
        );

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(3));
        vbox.setSpacing(3);
        Text title = new Text("Choose an algorithm:");
        algorithm = new ComboBox<>();
        for (String analyzer: getAnalyzers().keySet()) {
            algorithm.getItems().add(analyzer);
        }

        vbox.getChildren().add(title);
        vbox.getChildren().add(algorithm);
        vbox.getChildren().add(btn);

        input = new TextArea();
        output = new Text();
        BorderPane pane = new BorderPane();
        pane.setRight(vbox);
        pane.setCenter(input);
        pane.setBottom(output);
        primaryStage.setScene(new Scene(pane, 300, 250));
        primaryStage.show();
    }

    private Map<String, TextAnalyzer> getAnalyzers() {
        return TextAnalyzer.analyzers().collect(Collectors.toMap(TextAnalyzer::getName, a -> a));
    }

    private String analyze(String input, String algorithm) {
        TextAnalyzer analyzer = getAnalyzers().get(algorithm);
        for (int i = 0; i < input.length(); i++) {
            analyzer.addChar(input.charAt(i));
        }
        return analyzer.getName() + " score: " + analyzer.score();
    }
}