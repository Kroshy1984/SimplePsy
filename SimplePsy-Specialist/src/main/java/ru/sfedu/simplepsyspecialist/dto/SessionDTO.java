package ru.sfedu.simplepsyspecialist.dto;

import java.time.LocalDateTime;

public class SessionDTO {

    private LocalDateTime date;
    private ClientDTO clientDTO;

    public SessionDTO() {
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public ClientDTO getClientDTO() {
        return clientDTO;
    }

    public void setClientDTO(ClientDTO clientDTO) {
        this.clientDTO = clientDTO;
    }
}
