package sample;

import javafx.beans.property.IntegerProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import connection.DatabaseConnection;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Osoba;
import model.OsobaDAO;

import java.sql.SQLException;
import java.util.Calendar;

public class Controller {
    public TextArea text;
    public TextField TextName;
    public TextField TextSurname;
    public TableColumn<Osoba, Integer> colID;
    public TableColumn<Osoba, String> colName;
    public TableColumn<Osoba, String> colSurname;
    public TableView<Osoba> tabelaOsob;

    public void initialize(){
        try {
            DatabaseConnection.getConnection();
            text.setText("Połaczono z bazą danych..." + "\n" + text.getText());
        } catch (SQLException throwables) {
        }

        colID.setCellValueFactory(c -> c.getValue().getIdProperty().asObject());
        colName.setCellValueFactory(c -> c.getValue().getNameProperty());
        colSurname.setCellValueFactory(c->c.getValue().getSurProperty());
        try {
            fillTableView(OsobaDAO.getListOsoba());
        } catch (SQLException throwables) {
            text.setText("Problem z pobraniem listy..." + Calendar.getInstance().getTime() + "\n" + text.getText());
        }
    }

    private void fillTableView(ObservableList<Osoba> list){
        tabelaOsob.setItems(list);
    }

    public void connect(ActionEvent actionEvent) {
        try {
            DatabaseConnection.getConnection();
            text.setText("Connected to the database." + "\n" + text.getText());
        } catch (SQLException throwables) {
            text.setText("Failed to connect to the database." + "\n" + text.getText());
            throwables.printStackTrace();
        }
    }

    public void disconnect(ActionEvent actionEvent) {
        try {
            DatabaseConnection.closeConnection();
            text.setText("Disonnected from the database." + "\n" + text.getText());
        } catch (SQLException throwables) {
            text.setText("Failed to connect from the database." + "\n" + text.getText());
            throwables.printStackTrace();
        }
    }

    public void doddajOsobe(ActionEvent actionEvent) {
        String name = TextName.getText();
        String surname = TextSurname.getText();
        try {
            OsobaDAO.addOsobe(name, surname);
            text.setText("Dodano osobe: "+name + " "+ surname+ ". " + Calendar.getInstance().getTime() + "\n" + text.getText());
        } catch (SQLException throwables) {
            text.setText("Błąd dodawania osoby: "+name + " "+ surname+ ". " + Calendar.getInstance().getTime() + "\n" + text.getText());
        }

        try {
            fillTableView(OsobaDAO.getListOsoba());
        } catch (SQLException throwables) {
            text.setText("Problem z pobraniem listy osób..." + Calendar.getInstance().getTime() + "\n" + text.getText());
        }
    }

    public void updateOsoba(ActionEvent actionEvent) {
        String name = TextName.getText();
        String surname = TextSurname.getText();
        int id =tabelaOsob.getSelectionModel().getSelectedItem().getIdProperty().getValue();

        try {
            OsobaDAO.updateOsoba(id, name , surname);
            text.setText("Zmodyfikowano osobe: "+name + " "+ surname+ ". " + Calendar.getInstance().getTime() + "\n" + text.getText());
        } catch (SQLException throwables) {
            text.setText("Błąd modyfikacji osoby: "+name + " "+ surname+ ". " + Calendar.getInstance().getTime() + "\n" + text.getText());
        }

        try {
            fillTableView(OsobaDAO.getListOsoba());
        } catch (SQLException throwables) {
            text.setText("Problem z pobraniem listy osób..." + Calendar.getInstance().getTime() + "\n" + text.getText());
        }

    }

    public void deleteOsoba(ActionEvent actionEvent) {

        String name = TextName.getText();
        String surname = TextSurname.getText();
        int id =tabelaOsob.getSelectionModel().getSelectedItem().getIdProperty().getValue();

        try {
            OsobaDAO.deleteOsoba(id);
            text.setText("Usunięto osobe: "+name + " "+ surname+ ". " + Calendar.getInstance().getTime() + "\n" + text.getText());
        } catch (SQLException throwables) {
            text.setText("Błąd usuwania osoby: "+name + " "+ surname+ ". " + Calendar.getInstance().getTime() + "\n" + text.getText());
        }

        try {
            fillTableView(OsobaDAO.getListOsoba());
        } catch (SQLException throwables) {
            text.setText("Problem z pobraniem listy osób..." + Calendar.getInstance().getTime() + "\n" + text.getText());
        }
    }

    public void handleMouseAction(MouseEvent mouseEvent) {
        Osoba osoba = tabelaOsob.getSelectionModel().getSelectedItem();

        TextName.setText(osoba.getOsobaName());
        TextSurname.setText(osoba.getOsobaSur());
    }
}
