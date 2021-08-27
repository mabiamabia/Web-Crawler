package com.axreng.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static spark.Spark.*;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        get("/crawl/:id", (req, res) ->
                "GET /crawl/" + req.params("id"));
        post("/crawl", (req, res) ->
                "POST /crawl" + System.lineSeparator() + req.body());
        LOGGER.info("HTTP API initialized");
    }
}
