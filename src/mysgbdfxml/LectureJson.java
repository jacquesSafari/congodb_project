package mysgbdfxml;


import com.google.gson.*;
import java.io.File;
import java.util.Scanner;

public class LectureJson {
    private static Gson gson = new GsonBuilder ()
            .setPrettyPrinting ()
            .create ();


    public static void lireId (String nomfichier, int idEntre, String idBase,String nomChampChampAAffi) throws Exception {

        JsonParser jsonParser = new JsonParser();

        Scanner scanner = new Scanner(new File(nomfichier));
        StringBuilder contentFile = new StringBuilder();

        while (scanner.hasNext()) {
            contentFile.append(scanner.next());
        }

        JsonElement jsonElement = jsonParser.parse(contentFile.toString());
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        JsonArray data = jsonObject.get("donnees").getAsJsonArray();

        for (JsonElement jsele: data) {
            if (jsele.getAsJsonObject().get(idBase).getAsInt()==idEntre){

                System.out.println(jsele.getAsJsonObject().get(nomChampChampAAffi).getAsString());
            }
            //System.out.println(jsele.getAsJsonObject().get("Id").getAsInt());
        }
    }
//    public static void lireConte (String nomfichier) throws Exception {
//        JsonParser jsonParser = new JsonParser();
//
//        Scanner scanner = new Scanner(new File(nomfichier));
//        StringBuilder contentFile = new StringBuilder();
//
//        while (scanner.hasNext()) {
//            contentFile.append(scanner.next());
//        }
//
//        JsonElement jsonElement = jsonParser.parse(contentFile.toString());
//        JsonObject jsonObject = jsonElement.getAsJsonObject();
//
//        JsonArray data = jsonObject.get("donnees").getAsJsonArray();
//
//        for (JsonElement jsele: data) {
//            System.out.println(jsele.getAsJsonObject().get("Id"));
//            System.out.println(jsele.getAsJsonObject().get("Postnom"));
//        }
//
////        for (JsonElement jsele: data) {
////            if (jsele.getAsJsonObject().get("Id").getAsInt()==1){
////                //jsele.getAsJsonObject().get("Postnom").getAsString()="Gaetan";
////                System.out.println(jsele.getAsJsonObject().get("telephone").getAsString());
////            }
////            //System.out.println(jsele.getAsJsonObject().get("Id").getAsInt());
////        }
//    }
//

    public static void main(String[] args) throws Exception{
        lireId("fichier.json", 1, "1","cham");
    }
}
