package br.com.leomaia.exceptions

import java.util.Date

class ExceptionResponse (
    val timesTamp: Date,
    val message: String,
    val details: String
    )