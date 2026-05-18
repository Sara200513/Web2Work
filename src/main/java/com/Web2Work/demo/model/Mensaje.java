package com.Web2Work.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;


@Entity
@Table(name = "mensajes")
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "from_user_id", nullable = false)
    private Usuario fromUser;

    @ManyToOne
    @JoinColumn(name = "to_user_id", nullable = false)
    private Usuario toUser;

    private String conversacionId;

    @Column(nullable = false)
    private String asunto;

    @Column(nullable = false)
    private String cuerpo;

    @Column(nullable = false)
    private Boolean leido = false;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
 // GETTERS
    public Long getId() { return id; }
    public Usuario getFromUser() { return fromUser; }
    public Usuario getToUser() { return toUser; }
    public String getConversacionId() { return conversacionId; }
    public String getAsunto() { return asunto; }
    public String getCuerpo() { return cuerpo; }
    public Boolean getLeido() { return leido; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // SETTERS
    public void setId(Long id) { this.id = id; }
    public void setFromUser(Usuario fromUser) { this.fromUser = fromUser; }
    public void setToUser(Usuario toUser) { this.toUser = toUser; }
    public void setConversacionId(String conversacionId) { this.conversacionId = conversacionId; }
    public void setAsunto(String asunto) { this.asunto = asunto; }
    public void setCuerpo(String cuerpo) { this.cuerpo = cuerpo; }
    public void setLeido(Boolean leido) { this.leido = leido; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
}