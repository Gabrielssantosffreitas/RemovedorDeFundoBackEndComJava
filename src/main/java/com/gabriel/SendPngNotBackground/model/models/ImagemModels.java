package com.gabriel.SendPngNotBackground.model.models;


public class ImagemModels implements  Comparable<ImagemModels>{
    private String imagemBase64;
    private String mensagem;

    public ImagemModels() {
    }

    public ImagemModels(String imagemBase64, String mensagem) {
        this.imagemBase64 = imagemBase64;
        this.mensagem = mensagem;
    }

    @Override
    public String toString() {
        return "ImagemModels{" +
                "imagemBase64='" + imagemBase64 + '\'' +
                ", mensagem='" + mensagem + '\'' +
                '}';
    }

    @Override
    public int compareTo(ImagemModels o) {
        return (this.mensagem + this.imagemBase64).compareTo(o.getMensagem()+o.getImagemBase64());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        ImagemModels that = (ImagemModels) o;
        return imagemBase64.equals(that.imagemBase64) && mensagem.equals(that.mensagem);
    }

    @Override
    public int hashCode() {
        int result = imagemBase64.hashCode();
        result = 31 * result + mensagem.hashCode();
        return result;
    }

    public String getImagemBase64() {
        return imagemBase64;
    }

    public void setImagemBase64(String imagemBase64) {
        this.imagemBase64 = imagemBase64;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }


}
