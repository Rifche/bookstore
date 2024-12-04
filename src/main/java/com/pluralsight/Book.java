package com.pluralsight;

import jakarta.json.bind.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "T_BOOK")
@Schema(name = "Book", description = "Pojo that represents a book")
public class Book {

    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 200)

    @NotNull
    private String title;

    @Column(length = 50)
    private String isbn;

    @Column(length = 1000)
    @Size(min=10, max=1000)
    private String description;

    @Min(1)
    @JsonbNumberFormat("$#0.00")
    private BigDecimal price;

    @Column(name = "publication_date")
    @Past
    @JsonbDateFormat("dd-MM-yyyy")
    @JsonbProperty("publication-date")
    private LocalDate publicationDate;

    @Column(name = "nb_of_pages")
    @Min(10)
    @JsonbProperty("nb-of-pages")
    private Integer nbOfPages;

    @Column(name = "image_url")
    @JsonbTransient
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public Integer getNbOfPages() {
        return nbOfPages;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setNbOfPages(Integer nbOfPages) {
        this.nbOfPages = nbOfPages;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
