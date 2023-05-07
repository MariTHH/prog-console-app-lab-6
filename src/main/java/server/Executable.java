package server;


import common.network.CommandResult;
import common.network.Request;

/**
 * Interface for commands
 */
public interface Executable {
    CommandResult execute(Request<?> request);
}