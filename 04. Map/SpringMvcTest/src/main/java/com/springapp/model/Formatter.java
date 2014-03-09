/**
 *  “Measuring programming progress by lines of code is like measuring aircraft building progress by weight.”
 * - Bill Gates
 * User: Danyang
 * Date: 11/9/13
 * Time: 6:24 PM
 */

package com.springapp.model;

import java.lang.reflect.Field;


public class Formatter {
    public static String toString(Object aObject) {
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        
        result.append( aObject.getClass().getName() );
        result.append( " Object {" );
        result.append(newLine);
        
        //determine fields declared in aObject class only (no fields of superclass)
        Field[] fields = aObject.getClass().getDeclaredFields();
        
        //print field names paired with their values
        for ( Field field : fields  ) {
            result.append("  ");
            try {
                field.setAccessible(true);
                result.append( field.getName() );
                result.append(": ");
                //requires access to private field:
                result.append( field.get(aObject) );
            } catch ( IllegalAccessException ex ) {
                System.out.println(ex);
            }
            result.append(newLine);
        }
        result.append("}");
        
        return result.toString();
    }

}
