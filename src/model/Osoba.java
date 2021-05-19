package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Osoba {
    private IntegerProperty idProperty;
    private StringProperty nameProperty;
    private StringProperty surnameProperty;

    public Osoba(){
        idProperty = new SimpleIntegerProperty();
        nameProperty = new SimpleStringProperty();
        surnameProperty = new SimpleStringProperty();
    }

    public int getOsobaid(){
        return idProperty.getValue();
    }

    public IntegerProperty getIdProperty(){
        return idProperty;
    }

    public void setOsobaid(int id){
        idProperty.set(id);
    }

    public String getOsobaName(){
        return nameProperty.getValue();
    }

    public StringProperty getNameProperty(){
        return nameProperty;
    }

    public void setOsobaName(String name){
        nameProperty.set(name);
    }

    public String getOsobaSur(){
        return surnameProperty.getValue();
    }

    public StringProperty getSurProperty(){
        return surnameProperty;
    }

    public void setOsobaSur(String sur){
        surnameProperty.set(sur);
    }
}
