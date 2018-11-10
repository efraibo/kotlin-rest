package com.sourceinformation.kotlinrest.service.Exceptions

class ObjectNotFoundException: RuntimeException {
    constructor(msg: String): super(msg){}
}