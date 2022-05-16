package mif.vu.lt.psktp1.alternatives;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Alternative;

@Dependent
@Alternative
public class AltBookMessage implements Message{

    @Override
    public String WriteMessage() {
        return "Alternative book created";
    }

    public AltBookMessage() {}
}
