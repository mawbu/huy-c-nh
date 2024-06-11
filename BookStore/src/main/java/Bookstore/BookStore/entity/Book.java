package Bookstore.BookStore.entity;


import Bookstore.BookStore.validator.annotation.ValidCategoryId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "book") // Specify the table name (optional if it matches the class name)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key

    @Column(name = "title")
    @NotEmpty(message = "Title must not be empty")
    @Size(max = 50, min = 1, message = "Title must be less than 50 characters")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "price")
    @NotNull(message = "Price is required")
    private Double price;


    @ManyToOne // Many books can belong to one category
    @JoinColumn(name = "category_id") // Specify the foreign key column
    @ValidCategoryId
    private Category category; // Assuming you have a Category entity

}
