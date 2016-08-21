package com.apidomainhelper
/**
 * Created by bhushan on 16/12/16.
 */
class DomainErrorProvider {

    public static def handerError(def errorResults) {
        errorResults =  errorResults.toList()
         def errors = []
        for(error in errorResults) {
            errors.add([
                    'field' : error.field,
                    'rejected_value' : error.rejectedValue,
                    'message' : error.defaultMessage
            ])
        }
        return errors
    }

}
