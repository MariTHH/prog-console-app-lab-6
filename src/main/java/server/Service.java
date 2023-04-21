package server;

import common.DataManager;
import common.network.CommandResult;
import common.network.Request;


import java.util.HashMap;

public class Service {
    private HashMap<String, Executable> commands = new HashMap<>();
    private DataManager dataManager;

    public Service(DataManager dataManager) {
        this.dataManager = dataManager;
        initCommands();
    }

    private void initCommands() {
        commands.put("add", dataManager::add);
        commands.put("add_if_max", dataManager::addIfMax);
        commands.put("add_if_min", dataManager::addIfMin);
        commands.put("show", dataManager::show);
        commands.put("clear", dataManager::clear);
        commands.put("info", dataManager::info);
        commands.put("help", dataManager::help);
        commands.put("count_greater_than_eye_color", dataManager:: countEyeColor);
        commands.put("filter_greater_than_location", dataManager::filterGreater);
        commands.put("print_unique_location", dataManager::printUniqueLocation);
        commands.put("remove_by_id", dataManager::remove_by_id);
        commands.put("remove_greater", dataManager::removeGreater);
        commands.put("update", dataManager::update);
        /**
          */
    }

    public CommandResult executeCommand(Request<?> request) {
        if (!commands.containsKey(request.command))
            return new CommandResult(false, "Такой команды на сервере нет.");
        return commands.get(request.command).execute(request);
    }
}