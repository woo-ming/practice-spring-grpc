package io.wooming.practicespringgrpc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GrpcServerApplication

fun main(args: Array<String>) {
    runApplication<GrpcServerApplication>(*args)
}
