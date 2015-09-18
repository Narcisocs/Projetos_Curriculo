package br.com.narcisocsales.trabalho2fa7;

/**
 * Created by Narciso on 23/08/2015.
 */
public class Person {
    private int image;
    private String name;

    public Person(int image, String name){
        this.image = image;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
