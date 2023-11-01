/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.ahm.ga.vms.app031.util.validator;

import java.util.Collection;

/**
 *
 * @author Ahmad Mukaram Aziz
 */
public class Vms031AppUtil {
    /**
     * Checks if is collection empty.
     *
     * @param collection the collection
     * @return true, if is collection empty
     */
    private static boolean isCollectionEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * Checks if is object empty.
     *
     * @param object the object
     * @return true, if is object empty
     */
    public static boolean isObjectEmpty(Object object) {
        if (object == null) return true;
        else if (object instanceof String) {
            return ((String) object).trim().length() == 0;
        } else if (object instanceof Collection) {
            return isCollectionEmpty((Collection<?>) object);
        }
        return false;
    }
}
