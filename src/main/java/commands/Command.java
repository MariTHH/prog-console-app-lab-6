package commands;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * an abstract class containing the method responsible for the execution of commands
 */
public abstract class Command {
    private Object argument;

    /**
     * execute for every command
     *
     * @param args
     * @throws JAXBException
     * @throws IOException
     * @throws FileNotFoundException
     */
    public abstract void execute(String[] args) throws JAXBException, IOException, FileNotFoundException;

    public Object getArgument() {
        return argument;
    }

    public void setArgument(Object argument) {
        this.argument = argument;
    }

}