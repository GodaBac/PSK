package mif.vu.lt.psktp1.mybatis.dao;

import java.util.List;
import mif.vu.lt.psktp1.mybatis.model.Library;
import org.mybatis.cdi.Mapper;

@Mapper
public interface LibraryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.LIBRARY
     *
     * @mbg.generated Wed May 11 13:09:26 EEST 2022
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.LIBRARY
     *
     * @mbg.generated Wed May 11 13:09:26 EEST 2022
     */
    int insert(Library record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.LIBRARY
     *
     * @mbg.generated Wed May 11 13:09:26 EEST 2022
     */
    Library selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.LIBRARY
     *
     * @mbg.generated Wed May 11 13:09:26 EEST 2022
     */
    List<Library> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.LIBRARY
     *
     * @mbg.generated Wed May 11 13:09:26 EEST 2022
     */
    int updateByPrimaryKey(Library record);
}