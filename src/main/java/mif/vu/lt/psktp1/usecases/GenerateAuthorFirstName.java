package mif.vu.lt.psktp1.usecases;

import mif.vu.lt.psktp1.interceptors.LoggedInvocation;
import mif.vu.lt.psktp1.services.FirstNameGenerator;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class GenerateAuthorFirstName implements Serializable {

    @Inject
    FirstNameGenerator firstNameGenerator;

    private CompletableFuture<String> firstNameGenerationTask = null;

    @LoggedInvocation
    public String generateNewAuthorFirstName(){
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        firstNameGenerationTask = CompletableFuture.supplyAsync(() -> firstNameGenerator.generateFirstName());

        return "authorDetails?faces-redirect=true&authorId=" + requestParameters.get("authorId");
    }

    public boolean isFirstNameGenerationRunning(){
        return firstNameGenerationTask != null && !firstNameGenerationTask.isDone();
    }

    public String getFirstNameGeneratorStatus() throws ExecutionException, InterruptedException {
        if (firstNameGenerationTask == null) {
            return null;
        } else if (isFirstNameGenerationRunning()){
            return "First Name Generator in progress";
        }
        return "Suggested First name: " + firstNameGenerationTask.get();
    }
}
