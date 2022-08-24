package settings;

import org.json.simple.JSONObject;

import static utils.FilesUtils.read;
import static utils.JsonUtils.readJson;

public class Settings {
    public static JSONObject config = readJson(read("src/main/resources/config.json"));
}