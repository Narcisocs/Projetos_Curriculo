package br.com.narcisocsales.trabalho2fa7;

/**
 * Created by Narciso on 23/08/2015.
 */
public class Musicas {
    private int image;
    private String nome;
    private String nomeBanda;
    private String nomeAlbum;
    private String path;
    private boolean playing;

    public Musicas(){

    }

    public Musicas(int image, String nome, String nomeBanda, String nomeAlbum){
        this.image = image;
        this.nome = nome;
        this.nomeBanda = nomeBanda;
        this.nomeAlbum = nomeAlbum;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String name) {
        this.nome = name;
    }

    public String getNomeBanda() {
        return nomeBanda;
    }

    public void setNomeBanda(String nomeBanda) {
        this.nomeBanda = nomeBanda;
    }

    public String getNomeAlbum() {
        return nomeAlbum;
    }

    public void setNomeAlbum(String nomeAlbum) {
        this.nomeAlbum = nomeAlbum;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }
}
