package com.project.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "files")
@NamedQueries({
        @NamedQuery(name = "DBFile.getAll", query = "SELECT p FROM DBFile p"),
        @NamedQuery(name = "DBFile.getDBFileById", query = "SELECT p FROM DBFile p WHERE p.id =:id"),
        @NamedQuery(name = "DBFile.getDBFileByIdTask", query = "SELECT p FROM DBFile p WHERE p.zadanie.zadanieId =:zadanieId"),
})
public class DBFile {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String fileName;

    private String fileType;

    @ManyToOne
    @JoinColumn(name = "zadanie_id")
    private Zadanie zadanie;

    @Lob
    private byte[] data;

    public DBFile() {

    }

    public DBFile(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Zadanie getZadanie() {
        return zadanie;
    }

    public void setZadanie(Zadanie zadanie) {
        this.zadanie = zadanie;
    }
}