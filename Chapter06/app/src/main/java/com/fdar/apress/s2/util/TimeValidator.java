package com.fdar.apress.s2.util;

import com.opensymphony.xwork2.validator.validators.FieldValidatorSupport;
import com.opensymphony.xwork2.validator.ValidationException;

import java.util.Calendar;

/**
 * Validates a time to be of the following format -
 * <ul>
 *  <li>required: A digit 0..12</li>
 *  <li>optional: a colon and digit 00..59</li>
 *  <li>optional: space</li>
 *  <li>required: case insensitive, either "am" or "pm"</li>
 * </ul>
 *
 * Example:
 * <ul>
 *  <li>12 am</li>
 *  <li>12:30am</li>
 * </ul>
 */
public class TimeValidator extends FieldValidatorSupport {

    /**
     * @see Validator#validate(java.lang.Object)
     */
    public void validate( Object obj ) throws ValidationException {

        String fieldName = getFieldName();
        Object value = this.getFieldValue(fieldName, obj);

        if( value == null || !(value instanceof String) || !( new TimeUtil( (String)value ).isValid() ) ) {
            addFieldError( fieldName, obj );
        }

    }


 
}

