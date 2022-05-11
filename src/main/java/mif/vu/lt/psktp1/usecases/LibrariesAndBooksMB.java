package mif.vu.lt.psktp1.usecases;

import lombok.Getter;
import lombok.Setter;
import mif.vu.lt.psktp1.mybatis.dao.AuthorMapper;
import mif.vu.lt.psktp1.mybatis.dao.BookMapper;
import mif.vu.lt.psktp1.mybatis.dao.LibraryMapper;
import mif.vu.lt.psktp1.mybatis.model.Book;
import mif.vu.lt.psktp1.mybatis.model.Library;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Model
public class LibrariesAndBooksMB implements Serializable {

    @Inject
    private LibraryMapper libraryMapper;

    @Getter
    private List<Library> allLibraries;

    @Getter
    @Setter
    private Library libraryToCreate = new Library();

    @PostConstruct
    private void init() {
        loadAllLibraries();
    }

    @Transactional
    public String createLibrary() {
        libraryMapper.insert(libraryToCreate);
        return "/myBatis/librariesAndBooks?faces-redirect=true";
    }

    private void loadAllLibraries() {
        allLibraries = libraryMapper.selectAll();
    }
}
