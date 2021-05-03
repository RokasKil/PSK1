package lt.rokas.uzd1.usecase;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.inject.Named;

@Model
public class Expenses {
    @Getter
    @Setter
    private String test = "asdf";

    @PostConstruct
    public void init() {
        test = "asdf56";
    }

}
