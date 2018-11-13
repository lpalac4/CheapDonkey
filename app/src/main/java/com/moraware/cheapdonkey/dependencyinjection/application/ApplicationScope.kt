package com.moraware.cheapdonkey.dependencyinjection.application

import javax.inject.Scope

/**
 * Annotating this RetentionPolicy will make sure an injected method or object is always using the same instance.
 */
@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScope
