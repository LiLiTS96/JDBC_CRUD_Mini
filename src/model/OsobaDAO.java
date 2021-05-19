package model;

import connection.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OsobaDAO {
    public static void addOsobe(String name, String sur) throws SQLException {
        String sql = "INSERT INTO dane (imie, nazwisko) VALUES('" + name + "','" + sur + "');";
        try {
            DatabaseConnection.executeDML(sql);
            System.out.println(sql);
        } catch (SQLException throwables) {
            System.out.println("Błąd sql " + sql);
            throwables.printStackTrace();
            throw throwables;
        }
    }

    public static ObservableList<Osoba> getListOsoba() throws SQLException {
        String sql = "SELECT * FROM dane";
        ObservableList<Osoba> list = null;

        try {
            ResultSet resultSet = DatabaseConnection.getData(sql);
            list = makeObservableList(resultSet);
        } catch (SQLException throwables) {
            System.out.println("Blad odczytu listy Osob z db");
            throwables.printStackTrace();
            throw throwables;
        }
        return list;
    }

    private static ObservableList<Osoba> makeObservableList(ResultSet resultSet) {
        ObservableList<Osoba> list = FXCollections.observableArrayList();

        try {
            while (resultSet.next()){
                Osoba osoba = new Osoba();
                osoba.setOsobaid(resultSet.getInt("id"));
                osoba.setOsobaName(resultSet.getString("imie"));
                osoba.setOsobaSur(resultSet.getString("nazwisko"));
                list.add(osoba);
            }
        }catch (SQLException e) {
            System.out.println("Blad podczas tworzenia listy obserwowanej...");
            e.printStackTrace();
        }
        return list;
    }

    public static void updateOsoba(int id, String name, String sur) throws SQLException {
        String sql = "UPDATE dane SET imie = '" + name + "', nazwisko = '" + sur + "' WHERE id = " + id + ";";
        try {
            DatabaseConnection.executeDML(sql);
            System.out.println(sql);
        } catch (SQLException throwables) {
            System.out.println("Błąd sql " + sql);
            throwables.printStackTrace();
            throw throwables;
        }
    }

    public static void deleteOsoba(int id) throws SQLException {
        String sql = "DELETE FROM dane WHERE id = " + id + ";";
        try {
            DatabaseConnection.executeDML(sql);
            System.out.println(sql);
        } catch (SQLException throwables) {
            System.out.println("Błąd sql " + sql);
            throwables.printStackTrace();
            throw throwables;
        }
    }
}
