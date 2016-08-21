package rest.demo

import grails.util.Environment

class LoggingFilters {

    def filters = {
        traceUris(uri:'/api/**') {
            before = {
               /* def map = [:]
                map << params
                log.debug("${new Date()}")
                map.each { k, v -> log.debug("${k}:${v}") }*//*
                 do some preprocessing request*/
            }
            after = {
                // do some postprocessing
            }

        }
    }
}
