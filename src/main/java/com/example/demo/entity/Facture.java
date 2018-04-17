package com.example.demo.entity;

import com.example.demo.dto.ClientDTO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Kayne on 09/04/2018.
 */
@Entity
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "facture")
    private Set<LigneFacture> ligneFactures = new HashSet<>();

    private Set<ClientDTO> clients;

    public Set<ClientDTO> getClients() {
        return clients;
    }

    public void setClients(Set<ClientDTO> clients) {
        this.clients = clients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<LigneFacture> getLigneFactures() {
        return ligneFactures;
    }

    public void setLigneFactures(Set<LigneFacture> ligneFactures) {
        this.ligneFactures = ligneFactures;
    }
}
