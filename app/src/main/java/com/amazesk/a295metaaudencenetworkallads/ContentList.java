package com.amazesk.a295metaaudencenetworkallads;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ContentList {

    public static ArrayList<HashMap<String,String>> getContentList() {

        ArrayList<HashMap<String, String>> profiles = new ArrayList<>();
        HashMap<String, String> hashMap;

        // ------------------------
        // Manually add profile items
        // ------------------------
        hashMap = new HashMap<>();
        hashMap.put("type", "profile");
        hashMap.put("name", "Shuvo");
        profiles.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type", "profile");
        hashMap.put("name", "Bepoy");
        profiles.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type", "profile");
        hashMap.put("name", "Ayman");
        profiles.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type", "profile");
        hashMap.put("name", "Sakib");
        profiles.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type", "profile");
        hashMap.put("name", "Jahid");
        profiles.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type", "profile");
        hashMap.put("name", "Hasan");
        profiles.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type", "profile");
        hashMap.put("name", "Ashikur");
        profiles.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type", "profile");
        hashMap.put("name", "Saiful");
        profiles.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type", "profile");
        hashMap.put("name", "Himal");
        profiles.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type", "profile");
        hashMap.put("name", "Kamruzzman");
        profiles.add(hashMap);

        // ------------------------
        // Shuffle profile items
        // ------------------------
        // ------------------------
        // Manually add profile items
        // ------------------------
        hashMap = new HashMap<>();
        hashMap.put("type", "profile");
        hashMap.put("name", "Shuvo");
        profiles.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type", "profile");
        hashMap.put("name", "Kamrul");
        profiles.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type", "profile");
        hashMap.put("name", "Karim");
        profiles.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type", "profile");
        hashMap.put("name", "Sakib");
        profiles.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type", "profile");
        hashMap.put("name", "Jahid");
        profiles.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type", "profile");
        hashMap.put("name", "Hasan");
        profiles.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type", "profile");
        hashMap.put("name", "Karim2");
        profiles.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type", "profile");
        hashMap.put("name", "Rahim2");
        profiles.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type", "profile");
        hashMap.put("name", "Shuvo2");
        profiles.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type", "profile");
        hashMap.put("name", "Sakib2");
        profiles.add(hashMap);

        // ------------------------
        // Shuffle profile items
        // ------------------------
        // ------------------------
        // Manually add profile items
        // ------------------------
        hashMap = new HashMap<>();
        hashMap.put("type", "profile");
        hashMap.put("name", "Shuvo");
        profiles.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type", "profile");
        hashMap.put("name", "Rahim");
        profiles.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type", "profile");
        hashMap.put("name", "Karim");
        profiles.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type", "profile");
        hashMap.put("name", "Sakib");
        profiles.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type", "profile");
        hashMap.put("name", "Jahid");
        profiles.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type", "profile");
        hashMap.put("name", "Hasan");
        profiles.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type", "profile");
        hashMap.put("name", "Karim2");
        profiles.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type", "profile");
        hashMap.put("name", "Rahim2");
        profiles.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type", "profile");
        hashMap.put("name", "Shuvo2");
        profiles.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type", "profile");
        hashMap.put("name", "Sakib2");
        profiles.add(hashMap);

        // ------------------------
        // Shuffle profile items
        // ------------------------
        Collections.shuffle(profiles);

        // ------------------------
        // Insert ads automatically every 5 profiles
        // ------------------------
        ArrayList<HashMap<String, String>> finalList = new ArrayList<>();
        int profileCount = 0;
        int adCount = 0;

        for (HashMap<String, String> item : profiles) {
            finalList.add(item);

            if ("profile".equals(item.get("type"))) {
                profileCount++;

                // Insert ad every 5 profiles
                if (profileCount % 5 == 0) {
                    hashMap = new HashMap<>();
                    hashMap.put("type", "ads");
                    finalList.add(hashMap);
                    adCount++;
                    // && adCount<2

                }
            }
        }

        return finalList;
    }

}
