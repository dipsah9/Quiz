package com.dip.quiz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.sql.SQLException;
import java.util.function.Consumer;

public class CategorySelector {
    private static mainController controller;
    private  String category;

    public String getCategory(){
        return  this.category;
    }
    //setter for the category
    public void setCategory(String cat){
        this.category = cat;
    }
    @FXML
    private Button History;
    @FXML
    private Button commonSense;
    @FXML
    private Button videoGame;
    @FXML
    private Button mathematik;
    @FXML
    private Button science;

    private Consumer<String> onCategorySelectedListener;

    public void setOnCategorySelectedListener(Consumer<String> listener) {
        this.onCategorySelectedListener = listener;
    }

    /**
     * When the button on the Category selection window is pressed. The selected category is taken as input
     * for the category selection.
     * @param event
     */

    @FXML
    private  void handleCategorySelection(ActionEvent event) {
        Button selectedButton = (Button) event.getSource();
        String selectedCategory = selectedButton.getText();
        Main.setCategory(selectedCategory);
        if (onCategorySelectedListener != null) {
            onCategorySelectedListener.accept(selectedCategory);
        }
    }

    public void setController(mainController mController) {
        this.controller = mController;
    }
}
