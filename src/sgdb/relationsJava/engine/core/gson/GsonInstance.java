package sgdb.relationsJava.engine.core.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonInstance {
    private static Gson gson = null;
    private GsonInstance() {
    }

    public static Gson getInstance() {

        if (gson == null) {
            gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
        }

        return gson;
    }
}
