package mif.vu.lt.psktp1.mybatis.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Book {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.BOOK.ID
     *
     * @mbg.generated Wed May 11 13:09:26 EEST 2022
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.BOOK.TITLE
     *
     * @mbg.generated Wed May 11 13:09:26 EEST 2022
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.BOOK.AUTHOR_ID
     *
     * @mbg.generated Wed May 11 13:09:26 EEST 2022
     */
    private Long authorId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.BOOK.ID
     *
     * @return the value of PUBLIC.BOOK.ID
     *
     * @mbg.generated Wed May 11 13:09:26 EEST 2022
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.BOOK.ID
     *
     * @param id the value for PUBLIC.BOOK.ID
     *
     * @mbg.generated Wed May 11 13:09:26 EEST 2022
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.BOOK.TITLE
     *
     * @return the value of PUBLIC.BOOK.TITLE
     *
     * @mbg.generated Wed May 11 13:09:26 EEST 2022
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.BOOK.TITLE
     *
     * @param title the value for PUBLIC.BOOK.TITLE
     *
     * @mbg.generated Wed May 11 13:09:26 EEST 2022
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.BOOK.AUTHOR_ID
     *
     * @return the value of PUBLIC.BOOK.AUTHOR_ID
     *
     * @mbg.generated Wed May 11 13:09:26 EEST 2022
     */
    public Long getAuthorId() {
        return authorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.BOOK.AUTHOR_ID
     *
     * @param authorId the value for PUBLIC.BOOK.AUTHOR_ID
     *
     * @mbg.generated Wed May 11 13:09:26 EEST 2022
     */
    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    private List<Library> libraries;
}