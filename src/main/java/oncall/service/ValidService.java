package oncall.service;

import oncall.model.KoDay;

import java.util.List;

public class ValidService {
    public static void isValidFirstLine(String line) throws IllegalArgumentException {
        String[] split = line.split("[,|\\s]+");
        if (split.length != 2 && !split[0].matches("-?\\d+")) {
            throw new IllegalArgumentException("[ERROR] Invalid input: " + line);
        }

        try {
            KoDay koDay = KoDay.valueOf(split[1]);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("[ERROR] Invalid input: " + line);
        }
    }

    public static void isValidWorkerList(List<String> workerList) throws IllegalArgumentException {
        if (!(5 <= workerList.size() && workerList.size() <= 35)
                || workerList.size() != workerList.stream().distinct().count()) {
            throw new IllegalArgumentException("[ERROR] Invalid Input.");
        }

        for (String worker : workerList) {
            if (worker.length() > 5) {
                throw new IllegalArgumentException("[ERROR] Invalid Input.");
            }
        }
    }
}
