package mif.vu.lt.psktp1.usecases;

import lombok.Getter;
import lombok.Setter;
import mif.vu.lt.psktp1.entities.Library;
import mif.vu.lt.psktp1.persistence.LibraryDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
public class LogInLibrary {

    @Inject
    private LibraryDAO libraryDAO;

    @Getter
    @Setter
    private Library libraryToLogin = new Library();

    @Transactional
    public String logInLibrary() {
        Library attempt = libraryDAO.findByName(this.libraryToLogin.getName());

        if (attempt != null) {
            return "index?faces-redirect=true";
        } else {
            return "index?faces-redirect=true";
        }
    }

}
