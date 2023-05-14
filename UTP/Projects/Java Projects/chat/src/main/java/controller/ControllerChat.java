package controller;

import view.PaginaPrincipalChat;
import view.TerceraGui;

public class ControllerChat {

    private final PaginaPrincipalChat paginaPrincipalChat;
    private final TerceraGui terceraGui;

    public ControllerChat() {
        this.paginaPrincipalChat = new PaginaPrincipalChat();
        this.terceraGui = new TerceraGui();
    }

    public void iniciarApp(){
        this.paginaPrincipalChat.iniciarApp();
    }
    

}
