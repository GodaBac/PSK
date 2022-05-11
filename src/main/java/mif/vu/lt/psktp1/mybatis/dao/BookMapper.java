package mif.vu.lt.psktp1.mybatis.dao;

import java.util.List;
import mif.vu.lt.psktp1.mybatis.model.Book;
import org.mybatis.cdi.Mapper;

@Mapper
public interface BookMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.BOOK
     *
     * @mbg.generated Wed May 11 13:09:26 EEST 2022
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.BOOK
     *
     * @mbg.generated Wed May 11 13:09:26 EEST 2022
     */
    int insert(Book record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.BOOK
     *
     * @mbg.generated Wed May 11 13:09:26 EEST 2022
     */
    Book selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.BOOK
     *
     * @mbg.generated Wed May 11 13:09:26 EEST 2022
     */
    List<Book> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.BOOK
     *
     * @mbg.generated Wed May 11 13:09:26 EEST 2022
     */
    int updateByPrimaryKey(Book record);

    Book findByName(String name);

    int getResultCountByBookTitle(String name);
}