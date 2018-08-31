package com.quiz.core;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Main class, REST API root
 */
@ApplicationPath("/rest")
public class MainApplication extends Application {

    public MainApplication() {
    }
}
