package net.cmas.system.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    public static final String CMAS= "CMAS";
    public static final String SYSTEMS= "systems";
    public static final String CMAS_SYSTEMS= "CMASsystems";
    public static final String GOOD= "good";
    public static final String INTEGER = "integer";
    public static final Map<Integer, String> factorMap= new LinkedHashMap<>();
    // to add entries in a specific order
    static {
        factorMap.put(3, CMAS);
        factorMap.put(5, SYSTEMS);
    }

    public static final String WORD_FOR_THREE = "good";
}
